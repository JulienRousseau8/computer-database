package com.excilys.model;

import java.util.List;

public class Pagination {

	private int pageNum;
	private int pageMax;
	private int pageTaille;

	public Pagination(int nbComputers, int pageTaille) {
		this.pageNum = 0;
		this.pageTaille = pageTaille;
		this.pageMax = nbComputers / this.pageTaille;
	}
	
	public void nextPage() {
		if (this.pageNum < this.pageMax) {
			this.pageNum++;
		}
	}

	public void prevPage() {
		if (this.pageNum > 0) {
			this.pageNum--;
		}
	}

	public void selectPage(int nPage) {
		this.pageNum = nPage;
	}
	
	public void displayPageContent(List<Computer> pageContent) {
		System.out.println("*-------------------------------------------------------------*");
		for (Computer computer : pageContent) {
			System.out.println(computer.toString());
		}
		System.out.println("*-------------------------------------------------------------*");
	}
	
	@Override
	public String toString() {
		return "Pagination [pageNum=" + pageNum + ", pageMax=" + pageMax
		        + ", pageTaille=" + pageTaille + "]";
	}

	public int getPageNum() {
		return pageNum;
	}

	public int getPageMax() {
		return pageMax;
	}

	public int getPageTaille() {
		return pageTaille;
	}

	public void setPageNum(int pageNum) {
		this.pageNum = pageNum;
	}

	public void setPageMax(int pageMax) {
		this.pageMax = pageMax;
	}

	public void setPageTaille(int pageTaille) {
		this.pageTaille = pageTaille;
	}

}
