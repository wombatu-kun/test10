package wombatukun.tests.test10.services;

import wombatukun.tests.test10.dto.ResponseDto;

public interface SearcherService {

	ResponseDto searchQuestionsByTitle(String value, Integer page, Integer pageSize);

}
