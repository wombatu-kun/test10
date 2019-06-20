package wombatukun.tests.test10.dto;

import com.google.common.base.Objects;

public class AuthorDto {

	private String name;
	private String link;

	public AuthorDto() {
	}

	public AuthorDto(String name, String link) {
		this.name = name;
		this.link = link;
	}

	public String getName() { return name; }

	public void setName(String name) { this.name = name; }

	public String getLink() { return link; }

	public void setLink(String link) { this.link = link; }

	@Override
	public String toString() {
		return Objects.toStringHelper(this)
				.add("name", name)
				.add("link", link)
				.toString();
	}

}
