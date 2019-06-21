package wombatukun.tests.test10.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data @NoArgsConstructor @AllArgsConstructor
public class QuestionDto {

	private Long id;

	private String title;

	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "HH:mm:ss dd.MM.yyyy")
	private Date creationDate;

	private String link;

	@JsonProperty("isAnswered")
	private boolean answered;

	private AuthorDto author;

}
