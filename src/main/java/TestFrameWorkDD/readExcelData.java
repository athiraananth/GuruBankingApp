package testFrameWorkDD;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.util.NumberToTextConverter;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class readExcelData {
	
	public static String path;
	public static XSSFWorkbook workbook;
	public static FileInputStream fInput;
	public static XSSFSheet sheet;
	public static XSSFCell cell;
	
	public readExcelData (String path){
		
		this.path=path;
		try {
			fInput= new FileInputStream(path);
		}
		catch(IOException e) {
			
		}
	}
	
	public static ArrayList<String> getData(String col) throws IOException {
		fInput= new FileInputStream("F:\\Athira\\Selenium\\TestData\\TestDataLogin.xlsx");
		workbook= new XSSFWorkbook(fInput);
		ArrayList<String> tData= new ArrayList<String>();
		int sheetCount=workbook.getNumberOfSheets();
		for(int i=0;i<sheetCount;i++) {	
			if(workbook.getSheetName(i).equalsIgnoreCase("testdata1")) {
				sheet=workbook.getSheetAt(i);
				Iterator<Row> rows=sheet.iterator();
				Row firstRow=rows.next();
				Iterator<Cell> cells= firstRow.cellIterator();
				int k =0,column;
				while(cells.hasNext()) {
					Cell value=cells.next();
					if(value.getStringCellValue().equalsIgnoreCase(col)) {
						column=k;
					}
					k++;
				}
				
				while(rows.hasNext()) {
					
					Row r= rows.next();
					if(r.getCell(k).getStringCellValue().equalsIgnoreCase("Login")) {
						Iterator<Cell> cv=r.cellIterator();
						while(cv.hasNext()) {
							if(cv.next().getCellTypeEnum()== CellType.STRING) {
							tData.add(cv.next().getStringCellValue());
							}
							else {
								tData.add(NumberToTextConverter.toText(cv.next().getNumericCellValue()));
							}
						}
					}
				}
				
				
			}
		}
		return tData;
		
		
	}
	
	public String getTestData(String sheetName, String Column, int rowNum) throws IOException{
		
		workbook=new XSSFWorkbook(fInput);
		int sheetCount=workbook.getNumberOfSheets();
		String tData="";
		for(int i=0;i<sheetCount;i++) {
			
			if(workbook.getSheetName(i).equalsIgnoreCase(sheetName)) {
				sheet=workbook.getSheetAt(i);
				
				Iterator<Row> rows=sheet.iterator();
				Row firstRow= rows.next();
				Iterator<Cell> col=firstRow.iterator();
				int column,k=0;
				while(col.hasNext()) {
					Cell val=col.next();
					if(val.getStringCellValue().equalsIgnoreCase(Column)) {
						column=k;
						break;
					}
					k++;
				}
				
				Row r=sheet.getRow(rowNum);
				Cell cv=r.getCell(k);
				if(cv.getCellType()==CellType.STRING) {
					tData=cv.getStringCellValue();
				}
				else if(cv.getCellType()==CellType.NUMERIC) {
					tData=String.valueOf(cv.getNumericCellValue());
				}
				
			}
		}
		
		return tData;
	}

	
	public HashMap<String,String> getLoginData() throws IOException{
		
		//Load work book
		workbook= new XSSFWorkbook(fInput);
		sheet=workbook.getSheetAt(0);
		HashMap<String,String> tData= new HashMap<String,String>();
		for(int i=1;i<=sheet.getLastRowNum();i++) {
			String uName ="", password="";
			cell=sheet.getRow(i).getCell(0);
			if(cell.getCellType() == CellType.STRING) {
				uName=cell.getStringCellValue();
			}
			else if(cell.getCellType()==CellType.NUMERIC) {
				uName= String.valueOf(cell.getNumericCellValue());
			}
			cell= sheet.getRow(i).getCell(1);
			if(cell.getCellType() == CellType.STRING) {
				password=cell.getStringCellValue();
			}
			else if(cell.getCellType()==CellType.NUMERIC) {
				password= String.valueOf(cell.getNumericCellValue());
			}
			tData.put(uName, password);
			FileOutputStream fOutPut= new FileOutputStream(path);
			sheet.getRow(i).createCell(3).setCellValue("Data Imported Successfully");
			workbook.write(fOutPut);
			fOutPut.close();
			
		}	
		return tData;
		
		
	}
}
