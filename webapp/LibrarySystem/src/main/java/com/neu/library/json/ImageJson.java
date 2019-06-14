package com.neu.library.json;

import com.neu.library.model.Image;

public class ImageJson {
	
	private String Id;
	private String url;
	
	
	public ImageJson(Image image) {
		this.Id = image.getId();
		this.url = image.getUrl();
	}


	public String getId() {
		return Id;
	}


	public void setId(String id) {
		Id = id;
	}


	public String getUrl() {
		return url;
	}


<<<<<<< HEAD
	public void setUrl(String url) {
=======
	public void setFile(String url) {
>>>>>>> 1b005e93f48af11b7cc33354b360f23794317fcb
		this.url = url;
	}


	
	
	
	

}
