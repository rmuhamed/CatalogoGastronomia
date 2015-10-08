package com.rmuhamed.catalogogastronomia.MODEL;

import java.util.List;

public class SearchResult {
	private int Matches;
	private int Page;
	private int TotalPages;
	private boolean ShowMore;

	private List<Branch> Branches;

	public int getMatches() {
		return Matches;
	}

	public void setMatches(int matches) {
		Matches = matches;
	}

	public int getPage() {
		return Page;
	}

	public void setPage(int page) {
		Page = page;
	}

	public int getTotalPages() {
		return TotalPages;
	}

	public void setTotalPages(int totalPages) {
		TotalPages = totalPages;
	}

	public boolean isShowMore() {
		return ShowMore;
	}

	public void setShowMore(boolean showMore) {
		ShowMore = showMore;
	}

	public List<Branch> getBranches() {
		return Branches;
	}

	public void setBranches(List<Branch> branches) {
		Branches = branches;
	}
}
