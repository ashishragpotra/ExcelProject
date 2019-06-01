/**
 * 
 */
package net.codejava.fileupload.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import net.codejava.fileupload.dao.FileUploadDAO;
import net.codejava.fileupload.dao.GeologicalSectionDAO;
import net.codejava.fileupload.helper.GeologicalSectionsHelper;
import net.codejava.fileupload.model.GeologicalSections;
import net.codejava.fileupload.model.UploadFile;

/**
 * @author aragpotra
 *
 */
@Controller
public class UploadController {

	@Autowired
	private FileUploadDAO fileUploadDao;
	
	@RequestMapping(value = "/doUpload", method = RequestMethod.POST)
	public String handleFileUpload(HttpServletRequest request, @RequestParam CommonsMultipartFile[] fileUpload)
			throws Exception {

		if (fileUpload != null && fileUpload.length > 0) {
			for (CommonsMultipartFile aFile : fileUpload) {

				UploadFile uploadFile = new UploadFile();
				uploadFile.setFileName(aFile.getOriginalFilename());

				System.out.println("Saving file: " + aFile.getOriginalFilename());
				List<GeologicalSections> geologicalSectionsList = GeologicalSectionsHelper.uploadExcelInDatabase(aFile);
				geologicalSectionsList.stream().forEach(geoSection -> {
					geoSection.setUpload(uploadFile);
				});

				uploadFile.getGeologicalSectionsList().addAll(geologicalSectionsList);
				fileUploadDao.save(uploadFile);
			}
		}

		return "Success";
	}

	@RequestMapping(method = { RequestMethod.GET }, value = "deleteFile/{fileId}")
	public String deleteFileAndGeologicalSections(HttpServletRequest request, HttpServletResponse response,
			@PathVariable Long fileId) {
		int deletedRow = fileUploadDao.deleteFileAndGeologicalSections(fileId);
		System.out.println("row deleted count is " + deletedRow);
		return "Upload";
	}
	
	
}
