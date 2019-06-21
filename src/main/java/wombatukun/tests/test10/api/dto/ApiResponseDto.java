package wombatukun.tests.test10.api.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data @NoArgsConstructor @AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class ApiResponseDto {

	@JsonProperty("items")
	private List<ApiQuestionDto> items;

	@JsonProperty("has_more")
	private boolean hasMore;

	private Integer page;

	@JsonProperty("page_size")
	private Integer pageSize;

	private Long total;

	@JsonProperty("error_id")
	private Integer errorId;

	@JsonProperty("error_name")
	private String errorName;

	@JsonProperty("error_message")
	private String errorMessage;

	/**
	 * Not used. Just for information.
	 */
	@JsonProperty("quota_max")
	private Integer quotaMax;
	/**
	 * Not used. Just for information.
	 */
	@JsonProperty("quota_remaining")
	private Integer quotaRemaining;
	/**
	 * Field is only set when the API detects the request took an unusually long time to run
	 * Not used. Just for information.
	 */
	private Integer backoff;

	public String errorDescription() {
		return errorId + " " + errorName + ": " + errorMessage;
	}

}
