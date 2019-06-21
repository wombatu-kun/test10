package wombatukun.tests.test10.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data @NoArgsConstructor @AllArgsConstructor
public class ResponseDto {

	private List<QuestionDto> items;
	private boolean hasMore;
	private Integer page;
	private Integer pageSize;
	private Long total;
	private String errorMessage;
	private String searchString;

}
