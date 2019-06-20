package wombatukun.tests.test10.services;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import wombatukun.tests.test10.api.StackExchangeApi;
import wombatukun.tests.test10.api.dto.ApiRequestDto;
import wombatukun.tests.test10.api.dto.ApiResponseDto;
import wombatukun.tests.test10.dto.ResponseDto;
import wombatukun.tests.test10.mappers.StackExchangeDtoMapper;

@Service
public class SearcherServiceImpl implements SearcherService {
	private static final Logger LOGGER = LoggerFactory.getLogger(SearcherServiceImpl.class);

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
