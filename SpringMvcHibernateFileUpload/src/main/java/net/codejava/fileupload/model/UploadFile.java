package net.codejava.fileupload.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "uploadfile")
public class UploadFile {
	private long id;
	private String fileName;
	private List<GeologicalSections> geologicalSectionsList = new ArrayList<>();

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}
	
	@Column(name = "file_name")
	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	@OneToMany(mappedBy = "upload", cascade = {CascadeType.ALL},orphanRemoval = true)
	public List<GeologicalSections> getGeologicalSectionsList() {
		return geologicalSectionsList;
	}

	public void setGeologicalSectionsList(List<GeologicalSections> geologicalSectionsList) {
		this.geologicalSectionsList = geologicalSectionsList;
	}

}
