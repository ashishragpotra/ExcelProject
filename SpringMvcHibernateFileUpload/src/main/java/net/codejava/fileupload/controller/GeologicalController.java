/**
 * 
 */
package net.codejava.fileupload.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import net.codejava.fileupload.dao.GeologicalSectionDAO;

/**
 * @author aragpotra
 *
 */
@Controller
public class GeologicalController {

	@Autowired
	private GeologicalSectionDAO geologicalSectionDAO;

	@RequestMapping(method = { RequestMethod.GET }, value = "file/{fileId}/geologicalSectionId/{geologicalId}")
	public String deleteRowFromGeological(HttpServletRequest request, HttpServletResponse response,
			@PathVariable Long fileId, @PathVariable Long geologicalId) {

		int deletedRow = geologicalSectionDAO.deleteFromGeologicalSections(fileId,geologicalId);
		System.out.println("row deleted count is " + deletedRow);
		
		request.setAttribute("fileId", fileId);
		request.setAttribute("geologicalSectionsList", geologicalSectionDAO.getGeologicalSectionsWithFileId(fileId));
		return "editPage";
	}

	@RequestMapping(method = { RequestMethod.GET }, value = "/file/{id}")
	public String viewFileGeologicalSections(HttpServletRequest request,HttpServletResponse response,@PathVariable Long id) {
		request.setAttribute("fileId", id);
		request.setAttribute("geologicalSectionsList", geologicalSectionDAO.getGeologicalSectionsWithFileId(id));
		return "editPage";
	}
	
}
