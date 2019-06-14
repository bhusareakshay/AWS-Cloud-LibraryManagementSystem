package com.neu.library.json;

public class ImageJson {
	
	private String Id;
	private String file;
	
	
	public ImageJson(String id, String file) {
		this.Id = id;
		this.file = file;
	}


	public String getId() {
		return Id;
	}


	public void setId(String id) {
		Id = id;
	}


	public String getFile() {
		return file;
	}


	public void setFile(String file) {
		this.file = file;
	}
	
	
	
	

}
