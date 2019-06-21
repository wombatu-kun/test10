package wombatukun.tests.test10.services;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import wombatukun.tests.test10.api.StackExchangeApi;
import wombatukun.tests.test10.api.dto.ApiRequestDto;
import wombatukun.tests.test10.api.dto.ApiResponseDto;
import wombatukun.tests.test10.dto.ResponseDto;
import wombatukun.tests.test10.mappers.StackExchangeDtoMapper;

@Slf4j
@Service
public class SearcherServiceImpl implements SearcherService {

	private StackExchangeApi stackExchangeApi;
	private StackExchangeDtoMapper stackExchangeDtoMapper;

	@Autowired
	public SearcherServiceImpl(StackExchangeApi stackExchangeApi, StackExchangeDtoMapper stackExchangeDtoMapper) {
		this.stackExchangeApi = stackExchangeApi;
		this.stackExchangeDtoMapper = stackExchangeDtoMapper;
	}

	@Override
	@Cacheable(value = "searchedQuestionsCache", unless = "#result.getErrorMessage() != null")
	public ResponseDto searchQuestionsByTitle(String value, Integer page, Integer pageSize) {
		ApiRequestDto apiRequest = stackExchangeDtoMapper.createRequest(value, page, pageSize);
		ApiResponseDto apiResponse = stackExchangeApi.searchQuestionsByTitle(apiRequest);
		return stackExchangeDtoMapper.mapResponse(apiResponse);
	}

}
