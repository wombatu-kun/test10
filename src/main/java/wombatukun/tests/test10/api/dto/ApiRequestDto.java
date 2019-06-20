package wombatukun.tests.test10.api.dto;

import com.google.common.base.Objects;

public class ApiRequestDto {

	/**
	 * String to search in title of questions. Must be set.
	 */
	private String intitle;
	/**
	 * Page number, may be absent, default=1
	 */
	private Integer page;
	/**
	 * Page size, may be absent, default=30, max=100
	 */
	private Integer pageSize;

	/**
	 * Sort order, values: asc/desc. May be absent.
	 */
	private String order;
	/**
	 * The sorts operate on the following fields of the question object:
	 * activity – last_activity_date
	 * creation – creation_date
	 * votes – score
	 * relevance – matches the relevance tab on the site itself
	 * May be absent. default=activity
	 */
	private String sort;
	/**
	 * Place to search: stackoverflow, serverfault, webmasters, ... Must be set.
	 */
	private String site;
	/**
	 * Preconfigured filter id. May be absent.
	 */
	private String filter;
	/**
	 * StackExchange App key. May be absent.
	 */
	private String key;
	/**
	 * Preconfigured Stack Exchange OAuth access token (with scope=no_expiry). May be absent.
	 */
	private String accessToken;

	public ApiRequestDto(String intitle, Integer page, Integer pageSize) {
		this.intitle = intitle;
		this.page = page;
		this.pageSize = pageSize;
	}

	public String toSearchQuery() {
		StringBuilder sb = new StringBuilder("?");
		sb.append("intitle=").append(intitle);
		if (page != null) sb.append("&page=").append(page);
		if (pageSize != null) sb.append("&pagesize=").append(pageSize);
		if (order != null) sb.append("&order=").append(order);
		if (sort != null) sb.append("&sort=").append(sort);
		if (site != null) sb.append("&site=").append(site);
		if (filter != null) sb.append("&filter=").append(filter);
		if (key != null) sb.append("&key=").append(key);
		if (accessToken != null) sb.append("&access_token=").append(accessToken);
		return sb.toString();
	}

	public String getIntitle() { return intitle; }

	public void setIntitle(String intitle) { this.intitle = intitle; }

	public Integer getPage() { return page;	}

	public void setPage(Integer page) { this.page = page; }

	public Integer getPageSize() { return pageSize; }

	public void setPageSize(Integer pageSize) {	this.pageSize = pageSize; }

	public String getOrder() { return order; }

	public void setOrder(String order) { this.order = order; }

	public String getSort() { return sort; }

	public void setSort(String sort) { this.sort = sort; }

	public String getSite() { return site; }

	public void setSite(String site) { this.site = site; }

	public String getFilter() {	return filter; }

	public void setFilter(String filter) { this.filter = filter; }

	public String getKey() { return key; }

	public void setKey(String key) { this.key = key; }

	public String getAccessToken() { return accessToken; }

	public void setAccessToken(String accessToken) { this.accessToken = accessToken; }

	@Override
	public String toString() {
		return Objects.toStringHelper(this)
				.add("intitle", intitle)
				.add("page", page)
				.add("pageSize", pageSize)
				.add("order", order)
				.add("sort", sort)
				.add("site", site)
				.add("filter", filter)
				.add("key", key)
				.add("accessToken", accessToken)
				.toString();
	}
}
