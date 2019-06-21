package wombatukun.tests.test10.mappers;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;
import wombatukun.tests.test10.api.dto.ApiQuestionDto;
import wombatukun.tests.test10.api.dto.ApiRequestDto;
import wombatukun.tests.test10.api.dto.ApiResponseDto;
import wombatukun.tests.test10.api.dto.OwnerDto;
import wombatukun.tests.test10.dto.AuthorDto;
import wombatukun.tests.test10.dto.QuestionDto;
import wombatukun.tests.test10.dto.ResponseDto;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Slf4j
@Component
@ConfigurationProperties(prefix = "stackexchange.request")
public class StackExchangeDtoMapperImpl implements StackExchangeDtoMapper {

	private String order;
	private String sort;
	private String site;
	private String filter;
	private String key;
	private String accessToken;

	@Override
	public ApiRequestDto createRequest(String value, Integer page, Integer pageSize) {
		ApiRequestDto request = new ApiRequestDto(value, page, pageSize);
		request.setOrder(order);
		request.setSort(sort);
		request.setSite(site);
		request.setFilter(filter);
		request.setKey(key);
		request.setAccessToken(accessToken);
		return request;
	}

	@Override
	public ResponseDto mapResponse(ApiResponseDto apiResponse) {
		ResponseDto response = new ResponseDto();
		response.setHasMore(apiResponse.isHasMore());
		response.setPage(apiResponse.getPage());
		response.setPageSize(apiResponse.getPageSize());
		if (apiResponse.getErrorId() != null) {
			response.setErrorMessage(apiResponse.errorDescription());
		} else {
			response.setTotal(apiResponse.getTotal());
			List<QuestionDto> items = new ArrayList<>();
			if (apiResponse.getItems() != null) {
				apiResponse.getItems().forEach(item -> items.add(mapItem(item)));
			}
			response.setItems(items);
		}
		return response;
	}

	private QuestionDto mapItem(ApiQuestionDto item) {
		return new QuestionDto(item.getQuestionId(), item.getTitle(), new Date(item.getCreationDate()*1000),
				item.getLink(), item.isAnswered(), mapOwner(item.getOwner()));
	}

	private AuthorDto mapOwner(OwnerDto owner) {
		return new AuthorDto(owner.getDisplayName(), owner.getLink());
	}

	public void setOrder(String order) { this.order = order; }

	public void setSort(String sort) { this.sort = sort; }

	public void setSite(String site) { this.site = site; }

	public void setFilter(String filter) { this.filter = filter; }

	public void setKey(String key) { this.key = key; }

	public void setAccessToken(String accessToken) { this.accessToken = accessToken; }

}
