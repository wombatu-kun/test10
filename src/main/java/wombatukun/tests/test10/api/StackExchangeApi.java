package wombatukun.tests.test10.api;

import wombatukun.tests.test10.api.dto.ApiRequestDto;
import wombatukun.tests.test10.api.dto.ApiResponseDto;

public interface StackExchangeApi {

	ApiResponseDto searchQuestionsByTitle(ApiRequestDto request);

}
