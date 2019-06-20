package wombatukun.tests.test10.api.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.google.common.base.Objects;

@JsonIgnoreProperties(ignoreUnknown = true)
public class ApiQuestionDto {

	/**
	 * Unix epoch time in SECONDS! Conversion to util.Date must be as: x -> new Date(x*1000)
	 */
	@JsonProperty("creation_date")
	private Long creationDate;

	@JsonProperty("question_id")
	private Long questionId;

	private String title;

	private OwnerDto owner;

	@JsonProperty("is_answered")
	private boolean isAnswered;

	private String link;


	public String getTitle() { return title; }

	public void setTitle(String title) { this.title = title; }

	public OwnerDto getOwner() { return owner; }

	public void setOwner(OwnerDto owner) { this.owner = owner; }

	public boolean isAnswered() { return isAnswered; }

	public void setAnswered(boolean answered) { isAnswered = answered; }

	public Long getCreationDate() { return creationDate; }

	public void setCreationDate(Long creationDate) { this.creationDate = creationDate; }

	public Long getQuestionId() { return questionId; }

	public void setQuestionId(Long questionId) { this.questionId = questionId; }

	public String getLink() { return link; }

	public void setLink(String link) { this.link = link; }

	@Override
	public String toString() {
		return Objects.toStringHelper(this)
				.add("title", title)
				.add("owner", owner)
				.add("isAnswered", isAnswered)
				.add("creationDate", creationDate)
				.add("questionId", questionId)
				.add("link", link)
				.toString();
	}
}
