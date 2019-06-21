package wombatukun.tests.test10.api.dto;

import lombok.Data;

@Data
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

}
