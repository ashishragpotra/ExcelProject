/**
 * 
 */
package net.codejava.fileupload.controller;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import net.codejava.fileupload.dao.FileUploadDAO;
import net.codejava.fileupload.dao.GeologicalSectionDAO;
import net.codejava.fileupload.helper.GeologicalSectionsHelper;
import net.codejava.fileupload.model.GeologicalSections;

/**
 * @author aragpotra
 *
 */
@Controller
public class GeologicalController {

	@Autowired
	private GeologicalSectionDAO geologicalSectionDAO;


	@Autowired
	private FileUploadDAO fileUploadDao;

	@RequestMapping(method = { RequestMethod.GET }, value = "file/{fileId}/geologicalSectionId/{geologicalId}")
	public void deleteRowFromGeological(HttpServletRequest request, HttpServletResponse response,
			@PathVariable Long fileId, @PathVariable Long geologicalId) throws IOException {

		int deletedRow = geologicalSectionDAO.deleteFromGeologicalSections(fileId, geologicalId);
		System.out.println("row deleted count is " + deletedRow);

		request.setAttribute("fileId", fileId);
		request.setAttribute("geologicalSectionsList", geologicalSectionDAO.getGeologicalSectionsWithFileId(fileId));
		// response.sendRedirect("/file/"+fileId);
		// return viewFileGeologicalSections(request, response, fileId);
	}

	@RequestMapping(method = { RequestMethod.GET }, value = "/file/{id}")
	public String viewFileGeologicalSections(HttpServletRequest request, HttpServletResponse response,
			@PathVariable Long id) {
		request.setAttribute("fileId", id);
		request.setAttribute("geologicalSectionsList", geologicalSectionDAO.getGeologicalSectionsWithFileId(id));
		return "editPage";
	}

	@RequestMapping(method = { RequestMethod.GET }, value = "file/{fileId}/downloadExcel")
	public void downloadExcel(HttpServletRequest request, HttpServletResponse response, @PathVariable Long fileId) throws IOException {
		request.setAttribute("fileId", fileId);

		String fileNameFromDB= fileUploadDao.getFileName(fileId);
		String fileNameArray[] = fileNameFromDB.split("\\.");
		String fileName = fileNameArray[0];
		
		List<GeologicalSections> list = geologicalSectionDAO.getGeologicalSectionsWithFileId(fileId);
		HSSFWorkbook workbook =null;
		if (!list.isEmpty()) {
			workbook = GeologicalSectionsHelper.download(list);
		}
		
		ByteArrayOutputStream outByteStream = new ByteArrayOutputStream();
		if(null!=workbook) {
			workbook.write(outByteStream);
			byte [] outArray = outByteStream.toByteArray();
			response.setContentType("application/ms-excel");
			response.setContentLength(outArray.length); 
			response.setHeader("Expires:", "0"); // eliminates browser caching
			response.setHeader("Content-Disposition", "attachment; filename="+fileName+".xls");
			OutputStream outStream = response.getOutputStream();
			outStream.write(outArray);
			outStream.flush();
		}
	}

}
