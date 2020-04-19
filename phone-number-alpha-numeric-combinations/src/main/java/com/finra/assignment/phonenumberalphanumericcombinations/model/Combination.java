package com.finra.assignment.phonenumberalphanumericcombinations.model;

import java.util.List;


public class Combination {

	private int totalCount;
	private int pageNo;
	private int totalPages;
	private List<String> combinations;
	
	public Combination() {
		super();
	}
	
	public Combination(int totalCount, int pageNo, int totalPages, List<String> combinations) {
		super();
		this.totalCount = totalCount;
		this.pageNo = pageNo;
		this.totalPages = totalPages;
		this.combinations = combinations;
	}

	public int getTotalCount() {
		return totalCount;
	}
	public void setTotalCount(int totalCount) {
		this.totalCount = totalCount;
	}
	
	public int getPageNo() {
		return pageNo;
	}
	public void setPageNo(int pageNo) {
		this.pageNo = pageNo;
	}
	
	public List<String> getCombinations() {
		return combinations;
	}
	public void setCombinations(List<String> combinations) {
		this.combinations = combinations;
	}

	public int getTotalPages() {
		return totalPages;
	}
	public void setTotalPages(int totalPages) {
		this.totalPages = totalPages;
	}

}
