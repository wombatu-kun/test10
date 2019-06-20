package wombatukun.tests.test10.api.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.google.common.base.Objects;

@JsonIgnoreProperties(ignoreUnknown = true)
public class OwnerDto {

	@JsonProperty("display_name")
	private String displayName;

	private String link;

	public OwnerDto() {
	}

	public OwnerDto(String displayName, String link) {
		this.displayName = displayName;
		this.link = link;
	}

	public String getDisplayName() { return displayName; }

	public void setDisplayName(String displayName) { this.displayName = displayName; }

	public String getLink() { return link; }

	public void setLink(String link) { this.link = link; }

	@Override
	public String toString() {
		return Objects.toStringHelper(this)
				.add("displayName", displayName)
				.add("link", link)
				.toString();
	}
}
