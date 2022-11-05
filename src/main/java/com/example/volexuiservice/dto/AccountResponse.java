package com.example.volexuiservice.dto;

import java.util.List;

import com.example.volexuiservice.model.Account;

public class AccountResponse {

	private List<Account> rows;
	private int page;
	private int size;
	private long count;
	private int totalPages;
	private boolean lastPage;
	private boolean success;
	
	

	public AccountResponse() {
		super();
	}

	public List<Account> getRows() {
		return rows;
	}

	public void setRows(List<Account> rows) {
		this.rows = rows;
	}

	public int getPage() {
		return page;
	}

	public void setPage(int page) {
		this.page = page;
	}

	public int getSize() {
		return size;
	}

	public void setSize(int size) {
		this.size = size;
	}

	public long getCount() {
		return count;
	}

	public void setCount(long count) {
		this.count = count;
	}

	public int getTotalPages() {
		return totalPages;
	}

	public void setTotalPages(int totalPages) {
		this.totalPages = totalPages;
	}

	public boolean isLastPage() {
		return lastPage;
	}

	public void setLastPage(boolean lastPage) {
		this.lastPage = lastPage;
	}

	public boolean isSuccess() {
		return success;
	}

	public void setSuccess(boolean success) {
		this.success = success;
	}

}
