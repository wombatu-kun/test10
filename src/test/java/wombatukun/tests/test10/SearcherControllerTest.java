package wombatukun.tests.test10;

import org.hamcrest.Matchers;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import wombatukun.tests.test10.controllers.SearcherController;
import wombatukun.tests.test10.dto.AuthorDto;
import wombatukun.tests.test10.dto.QuestionDto;
import wombatukun.tests.test10.dto.ResponseDto;
import wombatukun.tests.test10.services.SearcherService;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static wombatukun.tests.test10.Const.*;


@RunWith(SpringRunner.class)
@WebMvcTest(SearcherController.class)
public class SearcherControllerTest {

	@Autowired
	private MockMvc mvc;

	@MockBean
	private SearcherService searcherService;

	@Test
	public void success_returnOk() throws Exception {
		given(searcherService.searchQuestionsByTitle(STRING, PAGE, PAGE_SIZE)).willReturn(createSuccessResponse());

		mvc.perform(post("/search").contentType(MediaType.APPLICATION_JSON)
				.content("{\"value\":\"" + STRING + "\", \"page\":" + PAGE + ", \"pageSize\":" + PAGE_SIZE + "}"))
				.andExpect(status().isOk()).andExpect(jsonPath("$.searchString", Matchers.is(STRING)))
				.andExpect(jsonPath("$.errorMessage", Matchers.nullValue()));
	}

	@Test
	public void emptyResult_returnOk() throws Exception {
		given(searcherService.searchQuestionsByTitle("hahaha", PAGE, PAGE_SIZE)).willReturn(createEmptyResponse());

		mvc.perform(post("/search").contentType(MediaType.APPLICATION_JSON)
				.content("{\"value\":\"hahaha\", \"page\":" + PAGE + ", \"pageSize\":" + PAGE_SIZE + "}"))
				.andExpect(status().isOk()).andExpect(jsonPath("$.searchString", Matchers.is("hahaha")))
				.andExpect(jsonPath("$.total", Matchers.is(0)))
				.andExpect(jsonPath("$.page", Matchers.is(1)))
				.andExpect(jsonPath("$.pageSize", Matchers.is(PAGE_SIZE)))
				.andExpect(jsonPath("$.items", Matchers.notNullValue()))
				.andExpect(jsonPath("$.hasMore", Matchers.is(false)))
				.andExpect(jsonPath("$.errorMessage", Matchers.nullValue()));
	}

	@Test
	public void notReadableException_returnBadRequest() throws Exception {
		mvc.perform(post("/search")
				.contentType(MediaType.APPLICATION_JSON).content("{\"value\":\"haha\", \"page\":\"y\", \"pageSize\":103}"))
				.andExpect(status().isBadRequest()).andExpect(jsonPath("$.errorMessage", Matchers.notNullValue()));
	}

	@Test
	public void noValue_returnBadRequest() throws Exception {
		mvc.perform(post("/search")
				.contentType(MediaType.APPLICATION_JSON).content("{\"page\":1, \"pageSize\":10}"))
				.andExpect(status().isBadRequest()).andExpect(jsonPath("$.errorMessage", Matchers.notNullValue()));
	}

	@Test
	public void invalidPage_returnBadRequest() throws Exception {
		mvc.perform(post("/search")
				.contentType(MediaType.APPLICATION_JSON).content("{\"value\":\"haha\", \"page\":0, \"pageSize\":10}"))
				.andExpect(status().isBadRequest()).andExpect(jsonPath("$.errorMessage", Matchers.notNullValue()));
	}

	@Test
	public void invalidPageSize_returnBadRequest() throws Exception {
		mvc.perform(post("/search")
				.contentType(MediaType.APPLICATION_JSON).content("{\"value\":\"haha\", \"page\":1, \"pageSize\":103}"))
				.andExpect(status().isBadRequest()).andExpect(jsonPath("$.errorMessage", Matchers.notNullValue()));
	}

	private ResponseDto createSuccessResponse() {
		List<QuestionDto> items = new ArrayList<>();
		for (long i=1; i<=2; i++) {
			AuthorDto author = new AuthorDto("name" + i, "link" + i);
			QuestionDto item = new QuestionDto(i, "title"+i, new Date(), "link"+i, i%2 == 0, author);
			items.add(item);
		}
		return new ResponseDto(items, false, PAGE, PAGE_SIZE, TOTAL,null);
	}

	private ResponseDto createEmptyResponse() {
		return new ResponseDto(new ArrayList<>(), false, 1, PAGE_SIZE, 0L,null);
	}

}
