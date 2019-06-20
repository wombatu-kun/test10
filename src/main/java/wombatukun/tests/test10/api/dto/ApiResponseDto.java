package wombatukun.tests.test10.api.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.google.common.base.Objects;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class ApiResponseDto {

	@JsonProperty("items")
	private List<ApiQuestionDto> items;

	@JsonProperty("has_more")
	private boolean hasMore;

	private Long total;

	private Integer page;

	@JsonProperty("page_size")
	private Integer pageSize;

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

	@JsonProperty("error_id")
	private Integer errorId;

	@JsonProperty("error_message")
	private String errorMessage;

	@JsonProperty("error_name")
	private String errorName;

	public ApiResponseDto() {
	}

	public ApiResponseDto(List<ApiQuestionDto> items, boolean hasMore, Integer page, Integer pageSize, Long total,
	                      Integer errorId, String errorName, String errorMessage, Integer quotaMax, Integer quotaRemaining,
	                      Integer backoff) {
		this.items = items;
		this.hasMore = hasMore;
		this.total = total;
		this.page = page;
		this.pageSize = pageSize;
		this.quotaMax = quotaMax;
		this.quotaRemaining = quotaRemaining;
		this.backoff = backoff;
		this.errorId = errorId;
		this.errorMessage = errorMessage;
		this.errorName = errorName;
	}

	public String errorDescription() {
		return errorId + " " + errorName + ": " + errorMessage;
	}

	public List<ApiQuestionDto> getItems() { return items; }

	public void setItems(List<ApiQuestionDto> items) { this.items = items; }

	public boolean isHasMore() { return hasMore; }

	public void setHasMore(boolean hasMore) { this.hasMore = hasMore; }

	public Long getTotal() { return total; }

	public void setTotal(Long total) { this.total = total; }

	public Integer getPage() { return page; }

	public void setPage(Integer page) { this.page = page; }

	public Integer getPageSize() { return pageSize; }

	public void setPageSize(Integer pageSize) {	this.pageSize = pageSize; }

	public Integer getQuotaMax() { return quotaMax; }

	public void setQuotaMax(Integer quotaMax) { this.quotaMax = quotaMax; }

	public Integer getQuotaRemaining() { return quotaRemaining; }

	public void setQuotaRemaining(Integer quotaRemaining) {	this.quotaRemaining = quotaRemaining; }

	public Integer getBackoff() { return backoff; }

	public void setBackoff(Integer backoff) { this.backoff = backoff; }

	public Integer getErrorId() { return errorId; }

	public void setErrorId(Integer errorId) { this.errorId = errorId; }

	public String getErrorMessage() { return errorMessage; }

	public void setErrorMessage(String errorMessage) { this.errorMessage = errorMessage; }

	public String getErrorName() { return errorName; }

	public void setErrorName(String errorName) { this.errorName = errorName; }

	@Override
	public String toString() {
		return Objects.toStringHelper(this)
				.add("items", items)
				.add("hasMore", hasMore)
				.add("total", total)
				.add("page", page)
				.add("pageSize", pageSize)
				.add("quotaMax", quotaMax)
				.add("quotaRemaining", quotaRemaining)
				.add("backoff", backoff)
				.add("errorId", errorId)
				.add("errorMessage", errorMessage)
				.add("errorName", errorName)
				.toString();
	}

}
