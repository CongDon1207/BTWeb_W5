package vn.iotstar.models;

import java.io.Serializable;

public class Category implements Serializable{
	
	
	private static final long serialVersionUID = 1L;
	private int categoryId;
	private String categoryname;
	private String images;
	private int status;
	
	public Category(int categoryId, String categoryname, String images, int status) {
		super();
		this.categoryId = categoryId;
		this.categoryname = categoryname;
		this.images = images;
		this.status = status;
	}

	public Category() {
		super();
	}
	
	

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public int getCategoryId() {
		return categoryId;
	}

	public void setCategoryId(int categoryId) {
		this.categoryId = categoryId;
	}

	public String getCategoryname() {
		return categoryname;
	}

	public void setCategoryname(String categoryname) {
		this.categoryname = categoryname;
	}

	public String getImages() {
		return images;
	}

	public void setImages(String images) {
		this.images = images;
	}
	
	
}
