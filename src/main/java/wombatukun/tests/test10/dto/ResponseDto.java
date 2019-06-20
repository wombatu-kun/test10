package wombatukun.tests.test10.dto;

import com.google.common.base.Objects;

import java.util.List;

public class ResponseDto {

	private List<QuestionDto> items;
	private boolean hasMore;
	private Integer page;
	private Integer pageSize;
	private Long total;
	private String errorMessage;
	private String searchString;

	public ResponseDto() {
	}

	public ResponseDto(List<QuestionDto> items, boolean hasMore, Integer page, Integer pageSize, Long total, String errorMessage) {
		this.items = items;
		this.hasMore = hasMore;
		this.page = page;
		this.pageSize = pageSize;
		this.total = total;
		this.errorMessage = errorMessage;
	}

	public List<QuestionDto> getItems() { return items;	}

	public void setItems(List<QuestionDto> items) {	this.items = items;	}

	public boolean isHasMore() { return hasMore; }

	public void setHasMore(boolean hasMore) { this.hasMore = hasMore; }

	public Long getTotal() { return total; }

	public void setTotal(Long total) { this.total = total; }

	public String getErrorMessage() { return errorMessage; }

	public void setErrorMessage(String errorMessage) { this.errorMessage = errorMessage; }

	public String getSearchString() { return searchString; }

	public void setSearchString(String searchString) { this.searchString = searchString; }

	public Integer getPage() { return page; }

	public void setPage(Integer page) { this.page = page; }

	public Integer getPageSize() { return pageSize; }

	public void setPageSize(Integer pageSize) { this.pageSize = pageSize; }

	@Override
	public String toString() {
		return Objects.toStringHelper(this)
				.add("items", items)
				.add("hasMore", hasMore)
				.add("page", page)
				.add("pageSize", pageSize)
				.add("total", total)
				.add("errorMessage", errorMessage)
				.add("searchString", searchString)
				.toString();
	}

}
