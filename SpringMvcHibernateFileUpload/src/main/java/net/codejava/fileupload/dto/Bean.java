package net.codejava.fileupload.dto;

import com.gizbel.excel.annotations.ExcelBean;
import com.gizbel.excel.annotations.ExcelColumnIndex;

@ExcelBean
public class Bean {

    @ExcelColumnIndex(columnIndex = "0", dataType = "double", defaultValue = "2.356")
    private Double fee;

    @ExcelColumnIndex(columnIndex = "1", dataType = "double")
    private Double totalCost;

    @ExcelColumnIndex(columnIndex = "2", dataType = "string")
    private String reference;

    @ExcelColumnIndex(columnIndex = "3", dataType = "string")
    private String invoiceNumber;
}