package com.neu.library.dao;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Date;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
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
				System.out.println("I am here");
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
	private int checkIfImage(String bookId) {
		
		int result = 0;
		try {
			Query query = this.entityManager.createQuery("SELECT COUNT(*) FROM Image i WHERE i.book_id = ?1");
			query.setParameter(1, bookId);
			Long resultInLong = (Long) query.getSingleResult();
			
			result = Math.toIntExact(resultInLong);
		} catch (Exception e) {
			
			result = 0;
		}

		return result;
}
	
	public boolean checkIfImageExsists (String bookId )
	{
		int count= this.checkIfImage(bookId);
		if (count>0)
			return true;
		else
			return false;
		
		
	}
	

}
