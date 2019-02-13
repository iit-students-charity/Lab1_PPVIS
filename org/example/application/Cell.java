package org.example.application;

public class Cell {
	
	private String value;
	
	private String left;
	
	private String right;
	
	public Cell(String value) {
		this.value = value;
		left = value;
		right = "";
	}
	
	public String getValue() {
		return value;
	}
	
	public String getLeft() {
		return left;
	}
	
	public String getRight() {
		return right;
	}

	public void toLeft() {
		left = value;
		right = "";
	}

	public void toRight() {
		left = "";
		right = value;
	}
}
