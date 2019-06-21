package wombatukun.tests.test10.controllers;

import com.google.common.base.Joiner;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;
import wombatukun.tests.test10.dto.RequestDto;
import wombatukun.tests.test10.dto.ResponseDto;
import wombatukun.tests.test10.services.SearcherService;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@RestController
public class SearcherController {

	private SearcherService searcherService;

	@Autowired
	public SearcherController(SearcherService searcherService) {
		this.searcherService = searcherService;
	}

	@PostMapping(value = "search", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseDto search(@Valid @RequestBody RequestDto request) {
		ResponseDto response = searcherService.searchQuestionsByTitle(request.getValue(), request.getPage(), request.getPageSize());
		response.setSearchString(request.getValue());
		return response;
	}

	@ExceptionHandler
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	public ResponseDto handleException(MethodArgumentNotValidException exception) {
		List<String> errors = exception.getBindingResult()
				.getFieldErrors()
				.stream()
				.map(e -> e.getField() + "=" + e.getRejectedValue() + ": " + e.getDefaultMessage())
				.collect(Collectors.toList());
		String errorMessage = Joiner.on("; ").join(errors);

		return new ResponseDto(new ArrayList<>(), false, null, null, null, errorMessage, null);
	}

	@ExceptionHandler
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	public ResponseDto handleException(HttpMessageNotReadableException exception) {
		return new ResponseDto(new ArrayList<>(), false, null, null, null, exception.getMessage(), null);
	}

}
