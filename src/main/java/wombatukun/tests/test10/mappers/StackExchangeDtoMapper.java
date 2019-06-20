package wombatukun.tests.test10.mappers;

import wombatukun.tests.test10.api.dto.ApiRequestDto;
import wombatukun.tests.test10.api.dto.ApiResponseDto;
import wombatukun.tests.test10.dto.ResponseDto;

public interface StackExchangeDtoMapper {

	ApiRequestDto createRequest(String value, Integer page, Integer pageSize);

	ResponseDto mapResponse(ApiResponseDto apiResponse);

}
