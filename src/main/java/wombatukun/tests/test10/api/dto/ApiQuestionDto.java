package wombatukun.tests.test10.api.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data @NoArgsConstructor @AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class ApiQuestionDto {

	@JsonProperty("question_id")
	private Long questionId;

	private String title;

	/**
	 * Unix epoch time in SECONDS! Conversion to util.Date must be as: x -> new Date(x*1000)
	 */
	@JsonProperty("creation_date")
	private Long creationDate;

	private String link;

	@JsonProperty("is_answered")
	private boolean isAnswered;

	private OwnerDto owner;

}
