/**
 * 
 */
package net.codejava.fileupload.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * @author aragpotra
 *
 */
@Entity
@Table(name = "geological_sections")
public class GeologicalSections {

	private long id;
	private String sectionName;
	private String class1Name;
	private String class1Code;
	private String class2Name;
	private String class2Code;
	private UploadFile upload;
	
	public void setId(long id) {
		this.id = id;
	}

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	public long getId() {
		return id;
	}

	@Column(name = "section_name")
	public String getSectionName() {
		return sectionName;
	}

	public void setSectionName(String sectionName) {
		this.sectionName = sectionName;
	}

	@Column(name = "class1_name")
	public String getClass1Name() {
		return class1Name;
	}

	public void setClass1Name(String class1Name) {
		this.class1Name = class1Name;
	}

	@Column(name = "class1_code")
	public String getClass1Code() {
		return class1Code;
	}

	public void setClass1Code(String class1Code) {
		this.class1Code = class1Code;
	}

	@Column(name = "class2_name")
	public String getClass2Name() {
		return class2Name;
	}

	public void setClass2Name(String class2Name) {
		this.class2Name = class2Name;
	}

	@Column(name = "class2_code")
	public String getClass2Code() {
		return class2Code;
	}

	public void setClass2Code(String class2Code) {
		this.class2Code = class2Code;
	}

	@ManyToOne
	public UploadFile getUpload() {
		return upload;
	}

	public void setUpload(UploadFile upload) {
		this.upload = upload;
	}

}
