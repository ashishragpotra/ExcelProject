/**
 * 
 */
package net.codejava.fileupload.helper;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

import org.apache.commons.io.IOUtils;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Row;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import com.gizbel.excel.enums.ExcelFactoryType;
import com.gizbel.excel.factory.Parser;

import net.codejava.fileupload.dto.GeologicalSectionsDTO;
import net.codejava.fileupload.model.GeologicalSections;

/**
 * @author aragpotra
 *
 */
public class GeologicalSectionsHelper {

	public static List<GeologicalSections> uploadExcelInDatabase(CommonsMultipartFile file) throws Exception {
		List<GeologicalSections> geologicalSectionsList = new ArrayList<>();

		File filesw = createTempFile(file.getInputStream());
		Parser parser = new Parser(GeologicalSectionsDTO.class, ExcelFactoryType.COLUMN_NAME_BASED_EXTRACTION);
		List<GeologicalSectionsDTO> result = setGeologicalSectionsUploadDtoList(parser.parse(filesw));
		for (GeologicalSectionsDTO uploadExcelDTO : result) {
			GeologicalSections geologicalSections = new GeologicalSections();
			geologicalSections.setSectionName(uploadExcelDTO.getSectionName());
			geologicalSections.setClass1Code(uploadExcelDTO.getClass1Code());
			geologicalSections.setClass1Name(uploadExcelDTO.getClass1Name());
			geologicalSections.setClass2Code(uploadExcelDTO.getClass2Code());
			geologicalSections.setClass2Name(uploadExcelDTO.getClass2Name());
			geologicalSectionsList.add(geologicalSections);
		}
		return geologicalSectionsList;
	}

	public static File createTempFile(InputStream in) throws IOException {
		final File tempFile = File.createTempFile(UUID.randomUUID().toString(), ".tmp");
		tempFile.deleteOnExit();
		try (FileOutputStream out = new FileOutputStream(tempFile, true)) {
			IOUtils.copy(in, out);
		}
		return tempFile;
	}

	public static List<GeologicalSectionsDTO> setGeologicalSectionsUploadDtoList(List<Object> objList)
			throws IllegalAccessException {
		List<GeologicalSectionsDTO> geologicalSectionsDTOList = new ArrayList<>(1);
		for (Object object : objList) {
			for (Field field : object.getClass().getDeclaredFields()) {
				field.setAccessible(true);
				if (Objects.isNull(field.get(object))) {
					continue;
				}
				geologicalSectionsDTOList.add((GeologicalSectionsDTO) object);
				break;
			}
		}
		return geologicalSectionsDTOList;
	}

	public static HSSFWorkbook download(List<GeologicalSections> list) throws IOException {
		HSSFWorkbook workbook = new HSSFWorkbook(); // new HSSFWorkbook() for generating `.xls` file
		HSSFSheet sheet = workbook.createSheet("Sheet1");
		//sheet.addMergedRegion(new CellRangeAddress(0, 0,0,6));
		HSSFRow headerRow = sheet.createRow(0);

		String[] columns = { "id", "Section Name", "Class 1 Name", "Class 1 Code", "Class 2 Name", "Class 2 Code" };

		for (int i = 0; i < columns.length; i++) {
			HSSFCell cell = headerRow.createCell(i);
			cell.setCellValue(columns[i]);
		}

		int rowNum = 1;
		int colNum = 0;
		for (GeologicalSections geologicalSections : list) {
			Row row = sheet.createRow(rowNum++);
			row.createCell(colNum++).setCellValue(geologicalSections.getId());
			sheet.autoSizeColumn(colNum);
			row.createCell(colNum++).setCellValue(geologicalSections.getSectionName());
			sheet.autoSizeColumn(colNum);
			row.createCell(colNum++).setCellValue(geologicalSections.getClass1Name());
			sheet.autoSizeColumn(colNum);
			row.createCell(colNum++).setCellValue(geologicalSections.getClass1Code());
			sheet.autoSizeColumn(colNum);
			row.createCell(colNum++).setCellValue(geologicalSections.getClass2Name());
			sheet.autoSizeColumn(colNum);
			row.createCell(colNum++).setCellValue(geologicalSections.getClass2Code());
			sheet.autoSizeColumn(colNum);
			colNum = 0;
		}
		return workbook;
	}

}
