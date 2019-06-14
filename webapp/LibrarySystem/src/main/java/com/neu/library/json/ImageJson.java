package com.neu.library.json;

import com.neu.library.model.Image;

public class ImageJson {
	
	private String Id;
	private String file;
	
	
	public ImageJson(Image image) {
		this.Id = image.getId();
		this.file = image.getUrl();
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
