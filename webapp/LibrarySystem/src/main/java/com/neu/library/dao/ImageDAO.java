package com.neu.library.dao;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Date;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

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
	
	private Image saveImageToLocal(MultipartFile file, Book book) {
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
	
	
	
	

}
