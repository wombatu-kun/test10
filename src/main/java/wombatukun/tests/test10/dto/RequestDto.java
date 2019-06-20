package wombatukun.tests.test10.dto;

import com.google.common.base.Objects;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

public class RequestDto {

	@NotBlank @Size(min=2, max=50)
	private String value;

	@Min(1)
	private Integer page;

	@Min(1) @Max(100)
	private Integer pageSize;

	public RequestDto() {
	}

	public RequestDto(String value, Integer page, Integer pageSize) {
		this.value = value;
		this.page = page;
		this.pageSize = pageSize;
	}

	public String getValue() { return value; }

	public void setValue(String value) { this.value = value; }

	public Integer getPage() { return page;	}

	public void setPage(Integer page) {	this.page = page; }

	public Integer getPageSize() { return pageSize;	}

	public void setPageSize(Integer pageSize) {	this.pageSize = pageSize; }

	@Override
	public String toString() {
		return Objects.toStringHelper(this)
				.add("value", value)
				.add("page", page)
				.add("pageSize", pageSize)
				.toString();
	}

}
