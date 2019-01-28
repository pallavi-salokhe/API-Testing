package EXCELOPERATIONS;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Hashtable;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class READDATA {

	public static FileInputStream fs = null;
		
	public static FileInputStream getFileHandle(String filename) throws FileNotFoundException
	{
		fs = new FileInputStream(new File(filename));
		return fs;
	}
		
	public static int GetTotalRowCount(FileInputStream fs,int sheetnum) throws IOException
	{		
		XSSFWorkbook workbook = new XSSFWorkbook(fs);
		XSSFSheet sheet =workbook.getSheetAt(sheetnum);
		
		int rnum = sheet.getLastRowNum();
		System.out.println(Integer.toString(rnum));
		
		return rnum;		
	}
	
	public static Hashtable<String,String> getDataFromExcel(FileInputStream fs,int rownum,int colcnt) throws IOException
	{
		//FileInputStream fs = new FileInputStream(new File(Filepath));
		
		XSSFWorkbook workbook = new XSSFWorkbook(fs);
		XSSFSheet sheet =workbook.getSheetAt(0);
		
		Row row = sheet.getRow(rownum);
		Hashtable<String,String> exceldata=new Hashtable<String,String>();  
		for (int i=0;i<colcnt;i++)
		{
			Cell cell = row.getCell(i);
			exceldata.put(sheet.getRow(0).getCell(i).getStringCellValue(),cell.getStringCellValue());  
			System.out.println(sheet.getRow(0).getCell(i)+" , "+cell.getStringCellValue());
			
		}
				
		 return exceldata;
		// System.out.println(sheet.getRow(2).getCell(0));
		 	
	}
	
	public static int GetTotalColumnCount(FileInputStream fs,int rownum) throws IOException
	{
		XSSFWorkbook workbook = new XSSFWorkbook(fs);
		XSSFSheet sheet =workbook.getSheetAt(0);
		int cnum = sheet.getRow(rownum).getLastCellNum();
		System.out.println(Integer.toString(cnum));
		
		return cnum;		
	}
}
