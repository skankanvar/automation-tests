package utils;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

public class XlsDataProvider{ 

	static Workbook book;
	static Sheet sheet;

//	public static String testdata_sheet_path = "C:\\Users\\vishal mittal\\Desktop\\testdata.xlsx";

	public static Object[][] getTestData(String sheetName, String fileLocation)
	{

		FileInputStream file= null;
		try
		{
			file= new FileInputStream(fileLocation);
		}
		catch(FileNotFoundException e)
		{
			e.printStackTrace();
		}
		try {
			book = WorkbookFactory.create(file);
		}
		catch(IOException e)
		{
			e.printStackTrace();
		}
		sheet = book.getSheet(sheetName);
		
		int noOfRows = sheet.getLastRowNum();
		int noOfColumns = sheet.getRow(0).getLastCellNum();

		Object[][] inputData= new Object[noOfRows][noOfColumns];
		for(int i=0; i<noOfRows;i++)
		{
			for( int j=0; j<noOfColumns;j++)
			{
				inputData[i][j]= sheet.getRow(i+1).getCell(j).toString();
			}
		}
		return inputData;

	}
}