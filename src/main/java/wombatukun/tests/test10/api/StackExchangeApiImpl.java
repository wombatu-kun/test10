package wombatukun.tests.test10.api;

import com.google.common.base.Strings;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import wombatukun.tests.test10.api.dto.ApiRequestDto;
import wombatukun.tests.test10.api.dto.ApiResponseDto;

import java.util.ArrayList;

@Slf4j
@Service
@ConfigurationProperties(prefix = "stackexchange.api")
public class StackExchangeApiImpl implements StackExchangeApi {

	public static final int DEFAULT_ERROR_ID = -23;

	private RestTemplate restTemplate;
	private String url;

	@Autowired
	public StackExchangeApiImpl(RestTemplate restTemplate) {
		this.restTemplate = restTemplate;
	}

	@Override
	@HystrixCommand(
			fallbackMethod = "buildFailureResponse",
			threadPoolKey = "searchQuestionsByTitleThreadPool"
	) /* fallback, threadPool and circuitBreaker configured in application properties */
	public ApiResponseDto searchQuestionsByTitle(ApiRequestDto request) {
		String apiUrl = url  + request.toSearchQuery();
		log.debug("Request query: {}", apiUrl);
		ApiResponseDto response = restTemplate.getForObject(apiUrl, ApiResponseDto.class);
		log.debug("Response object: {}", response);
		return response;
	}

	private ApiResponseDto buildFailureResponse(ApiRequestDto request, Throwable throwable) {
		log.warn("Fallback: {}", throwable.toString());
		ApiResponseDto  failure = new ApiResponseDto();
		failure.setItems(new ArrayList<>());
		failure.setErrorId(DEFAULT_ERROR_ID);
		failure.setErrorName(throwable.getClass().getName());
		failure.setErrorMessage(Strings.nullToEmpty(throwable.getMessage()));
		return failure;
	}

	public void setUrl(String url) { this.url = url; }

}
