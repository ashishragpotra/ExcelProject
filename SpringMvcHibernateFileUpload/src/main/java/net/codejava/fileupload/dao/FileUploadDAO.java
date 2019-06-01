package net.codejava.fileupload.dao;

import java.util.List;

import net.codejava.fileupload.model.UploadFile;

public interface FileUploadDAO {
	void save(UploadFile uploadFile);

	List<UploadFile> getListOfUploadFiles();

	int deleteFileAndGeologicalSections(Long fileId);
	
	String getFileName(Long fileId);

}
