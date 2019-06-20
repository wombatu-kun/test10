package wombatukun.tests.test10;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import wombatukun.tests.test10.api.dto.ApiQuestionDto;
import wombatukun.tests.test10.api.dto.ApiRequestDto;
import wombatukun.tests.test10.api.dto.ApiResponseDto;
import wombatukun.tests.test10.api.dto.OwnerDto;
import wombatukun.tests.test10.dto.QuestionDto;
import wombatukun.tests.test10.dto.ResponseDto;
import wombatukun.tests.test10.mappers.StackExchangeDtoMapper;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.junit.Assert.*;
import static wombatukun.tests.test10.Const.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class StackExchangeDtoMapperTest {

	private static final Integer ID = 406;
	private static final String NAME = "error name";
	private static final String MSG = "error msg";

	@Autowired
	private StackExchangeDtoMapper stackExchangeDtoMapper;

	@Test
	public void createRequestTest() {
		ApiRequestDto request = stackExchangeDtoMapper.createRequest(STRING, PAGE, PAGE_SIZE);
		assertEquals(STRING, request.getIntitle());
		assertEquals(PAGE, request.getPage());
		assertEquals(PAGE_SIZE, request.getPageSize());
		assertNotNull(request.getSite());
	}

	@Test
	public void mapSuccessResponseTest() {
		ApiResponseDto source = createSuccessResponse(new Date());
		ResponseDto target = stackExchangeDtoMapper.mapResponse(source);
		assertResponses(source, target);
		assertNull(target.getErrorMessage());
		assertEquals(source.getItems().size(), target.getItems().size());
		for(int i=1; i<=2; i++) {
			assertItems(source.getItems().get(i-1), target.getItems().get(i-1));
		}
	}

	@Test
	public void mapEmptyResponseTest() {
		ApiResponseDto source = createEmptyResponse();
		ResponseDto target = stackExchangeDtoMapper.mapResponse(source);
		assertResponses(source, target);
		assertNull(target.getErrorMessage());
		assertNotNull(target.getItems());
		assertEquals(0, target.getItems().size());
	}

	@Test
	public void mapErrorResponseTest() {
		ApiResponseDto source = createFailureResponse();
		ResponseDto target = stackExchangeDtoMapper.mapResponse(source);
		assertFalse(target.isHasMore());
		assertEquals(source.errorDescription(), target.getErrorMessage());
		assertNull(target.getItems());
	}

	private void assertResponses(ApiResponseDto source, ResponseDto target) {
		assertEquals(source.getTotal(), target.getTotal());
		assertEquals(source.getPage(), target.getPage());
		assertEquals(source.getPageSize(), target.getPageSize());
		assertEquals(source.isHasMore(), target.isHasMore());
	}

	private void assertItems(ApiQuestionDto sourceItem, QuestionDto targetItem) {
		assertEquals((new Date(sourceItem.getCreationDate()*1000)).toString(), targetItem.getCreationDate().toString());
		assertEquals(sourceItem.getLink(), targetItem.getLink());
		assertEquals(sourceItem.getTitle(), targetItem.getTitle());
		assertEquals(sourceItem.getOwner().getDisplayName(), targetItem.getAuthor().getName());
		assertEquals(sourceItem.getOwner().getLink(), targetItem.getAuthor().getLink());
		assertEquals(sourceItem.isAnswered(), targetItem.isAnswered());
		assertEquals(sourceItem.getQuestionId(), targetItem.getId());
	}

	private ApiResponseDto createSuccessResponse(Date date) {
		List<ApiQuestionDto> items = new ArrayList<>();
		for (long i=1; i<=2; i++) {
			OwnerDto owner = new OwnerDto("name" + i, "link" + i);
			ApiQuestionDto item = new ApiQuestionDto(i, "title"+i, date.getTime()/1000, "link"+i, i%2 == 0, owner);
			items.add(item);
		}
		return new ApiResponseDto(items, true, PAGE, PAGE_SIZE, TOTAL, null, null, null, null, null, null);
	}


	private ApiResponseDto createFailureResponse() {
		return new ApiResponseDto(null, false, null, null, null, ID, MSG, NAME, null, null, null);
	}

	private ApiResponseDto createEmptyResponse() {
		return new ApiResponseDto(null, false, 1, PAGE_SIZE, 0L, null, null, null, null, null, null);
	}

}
