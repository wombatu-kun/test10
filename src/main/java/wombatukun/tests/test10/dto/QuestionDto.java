package wombatukun.tests.test10.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.google.common.base.Objects;

import java.util.Date;

public class QuestionDto {

	private Long id;

	private String title;

	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "HH:mm:ss dd.MM.yyyy")
	private Date creationDate;

	private String link;

	@JsonProperty("isAnswered")
	private boolean answered;

	private AuthorDto author;

	public QuestionDto() {
	}

	public QuestionDto(Long id, String title, Date creationDate, String link, boolean answered, AuthorDto author) {
		this.id = id;
		this.title = title;
		this.creationDate = creationDate;
		this.link = link;
		this.answered = answered;
		this.author = author;
	}

	public Long getId() { return id; }

	public void setId(Long id) { this.id = id; }

	public String getTitle() { return title; }

	public void setTitle(String title) { this.title = title; }

	public Date getCreationDate() {	return creationDate; }

	public void setCreationDate(Date creationDate) { this.creationDate = creationDate; }

	public String getLink() { return link; }

	public void setLink(String link) { this.link = link; }

	public boolean isAnswered() { return answered; }

	public void setAnswered(boolean answered) {	this.answered = answered; }

	public AuthorDto getAuthor() { return author; }

	public void setAuthor(AuthorDto author) { this.author = author;	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		QuestionDto that = (QuestionDto) o;
		return Objects.equal(id, that.id);
	}

	@Override
	public int hashCode() {
		return Objects.hashCode(id);
	}

	@Override
	public String toString() {
		return Objects.toStringHelper(this)
				.add("id", id)
				.add("title", title)
				.add("creationDate", creationDate)
				.add("link", link)
				.add("answered", answered)
				.add("author", author)
				.toString();
	}
}
