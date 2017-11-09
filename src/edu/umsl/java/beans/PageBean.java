package edu.umsl.java.beans;

public class PageBean {

	private int totalRecords;
	private int totalPages;
	private int previousPage;
	private int currentPage;
	private int nextPage;
	private int recordsPerPage=10;
	private String sortBy;
	
	
	public String getSortBy() {
		return sortBy;
	}
	public void setSortBy(String sortBy) {
		this.sortBy = sortBy;
	}
	public int getRecordsPerPage() {
		return recordsPerPage;
	}
	public void setRecordsPerPage(int recordsPerPage) {
		this.recordsPerPage = recordsPerPage;
	}
	public int getTotalRecords() {
		return totalRecords;
	}
	public void setTotalRecords(int totalRecords) {
		this.totalRecords = totalRecords;
	}
	public int getTotalPages() {
		
		return totalPages;
	}
	public void setTotalPages(int totalPages) {
		
		double temp=Math.ceil((double) totalRecords/recordsPerPage);
		totalPages = (int) temp;
		this.totalPages = totalPages;
	}
	public int getPreviousPage() {
		return previousPage;
	}
	public void setPreviousPage(int previousPage) {
		this.previousPage = previousPage;
	}
	public int getCurrentPage() {
		return currentPage;
	}
	public void setCurrentPage(int currentPage) {
		this.currentPage = currentPage;
	}
	public int getNextPage() {
		return nextPage;
	}
	public void setNextPage(int nextPage) {
		this.nextPage = nextPage;
	}
	
	
}
