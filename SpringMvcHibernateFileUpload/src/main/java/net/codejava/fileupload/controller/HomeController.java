package net.codejava.fileupload.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import net.codejava.fileupload.dao.FileUploadDAO;
import net.codejava.fileupload.model.UploadFile;

/**
 * Handles requests for the file upload page.
 */
@Controller
public class HomeController {

	@Autowired
	private FileUploadDAO fileUploadDao;

	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String showUploadForm(HttpServletRequest request) {
		List<UploadFile> filesList = fileUploadDao.getListOfUploadFiles();
		request.setAttribute("filesList", filesList);
		return "Upload";
	}

}
