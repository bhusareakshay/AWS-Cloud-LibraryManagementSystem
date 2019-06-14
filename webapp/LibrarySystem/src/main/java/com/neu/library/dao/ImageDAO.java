package com.neu.library.dao;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Date;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import org.apache.commons.io.FilenameUtils;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.neu.library.model.Book;
import com.neu.library.model.Image;

@Service
public class ImageDAO {	

	@PersistenceContext
	private EntityManager entityManager;

	private static String UPLOADED_FOLDER = System.getProperty("user.dir") + "//images//";
	
	@Transactional
	public Image saveImageToLocal(MultipartFile file, Book book) {
		Image image = null;
		String filename;
		try {
			Files.createDirectories(Paths.get(UPLOADED_FOLDER));

				String fileNameWithOutExt = FilenameUtils.removeExtension(file.getOriginalFilename());
				filename = fileNameWithOutExt + "_" + new Date().getTime() + "."
						+ FilenameUtils.getExtension(file.getOriginalFilename());
				// System.out.println("filename::"+filename);

				Path path = Paths.get(UPLOADED_FOLDER + filename);
				Files.write(path, file.getBytes());
				image = new Image(path.toString(), book);
				this.entityManager.persist(image);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return image;
	}
	
	@Transactional
	public Image getImageFromId(String id) {
		Image imageToBeDeleted = this.entityManager.find(Image.class, id);
		return imageToBeDeleted;
	}
	
	
	@Transactional
	public void deleteImageFromLocal(String id) 
	{
		Image imageToBeDeleted = this.entityManager.find(Image.class, id);
		boolean successfullyDeleted = deleteFromMemory(imageToBeDeleted);
		if (successfullyDeleted)
		{
			this.entityManager.remove(imageToBeDeleted);
			flushAndClear();
		}
	}
	
	@Transactional
	public boolean deleteFromMemory(Image imageToBeDeleted) {
		String path = imageToBeDeleted.getUrl();

		try {
			java.io.File fileToBeDeleted = new java.io.File((path));
			if (fileToBeDeleted.delete()) {
				return true;
			} else {
				return false;
			}
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}

	}
	
	@Transactional
	public Image updateAttachmentFromLocal(String id, Image image,MultipartFile file, Book book) 
	{
		//delete actual file from local
		boolean successfullyDeleted = deleteFromMemory(image);
		
		if (successfullyDeleted) 
		{ 
			saveAttachmentToLocalMemory(file, book); 
		}
		 

		Image attachmentToBeUpdated1 =null;
		if (successfullyDeleted)
		{
			//update entry from DB
			String fileNameWithOutExt = FilenameUtils.removeExtension(file.getOriginalFilename());
			String filename = fileNameWithOutExt + "_" + new Date().getTime() + "."
					+ FilenameUtils.getExtension(file.getOriginalFilename());
			Path path = Paths.get(UPLOADED_FOLDER + filename);
			image = new Image(path.toString(), book);
			attachmentToBeUpdated1 =updateInDB(id, image);
			
		}

		return attachmentToBeUpdated1;
	}
	
	
	private void saveAttachmentToLocalMemory(MultipartFile file, Book book) {
		String filename ="";
		try {
			Files.createDirectories(Paths.get(UPLOADED_FOLDER));
			String fileNameWithOutExt = FilenameUtils.removeExtension(file.getOriginalFilename());
			filename = fileNameWithOutExt + "_" + new Date().getTime() + "."
					+ FilenameUtils.getExtension(file.getOriginalFilename());
			Path path = Paths.get(UPLOADED_FOLDER + filename);
			Files.write(path, file.getBytes());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Transactional
	public Image updateInDB (String id, Image image)
	{
		Image imageToBeUpdated1 = this.entityManager.find(Image.class, id);
	      imageToBeUpdated1.setUrl(image.getUrl());
		  flushAndClear();
		  return imageToBeUpdated1;
		
	}
	
	
	
	private void flushAndClear() {
		this.entityManager.flush();
		this.entityManager.clear();
	}
	
	
	
	

}
