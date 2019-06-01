/**
 * 
 */
package net.codejava.fileupload.dto;

import com.gizbel.excel.annotations.ExcelBean;
import com.gizbel.excel.annotations.ExcelColumnHeader;

/**
 * @author aragpotra
 *
 */
@ExcelBean
public class GeologicalSectionsDTO {

	@ExcelColumnHeader(columnHeader = "Section Name", dataType = "String", defaultValue = "")
	private String sectionName;
	
	@ExcelColumnHeader(columnHeader = "Class 1 Name", dataType = "String", defaultValue = "")
	private String class1Name;
	
	@ExcelColumnHeader(columnHeader = "Class 1 Code", dataType = "String", defaultValue = "")
	private String class1Code;
	
	@ExcelColumnHeader(columnHeader = "Class 2 Name", dataType = "String", defaultValue = "")
	private String class2Name;
	
	@ExcelColumnHeader(columnHeader = "Class 2 Code", dataType = "String", defaultValue = "")
	private String class2Code;

	public String getSectionName() {
		return sectionName;
	}

	public void setSectionName(String sectionName) {
		this.sectionName = sectionName;
	}

	public String getClass1Code() {
		return class1Code;
	}

	public void setClass1Code(String class1Code) {
		this.class1Code = class1Code;
	}

	public String getClass2Name() {
		return class2Name;
	}

	public void setClass2Name(String class2Name) {
		this.class2Name = class2Name;
	}

	public String getClass2Code() {
		return class2Code;
	}

	public void setClass2Code(String class2Code) {
		this.class2Code = class2Code;
	}

	public String getClass1Name() {
		return class1Name;
	}
	
	public void setClass1Name(String class1Name) {
		this.class1Name = class1Name;
	}
	
	
	@Override
	public String toString() {
		return "GeologicalSectionsDTO [sectionName=" + sectionName + ", class1Name=" + class1Name + ", class1Code="
				+ class1Code + ", class2Name=" + class2Name + ", class2Code=" + class2Code + "]";
	}
	
	
}
