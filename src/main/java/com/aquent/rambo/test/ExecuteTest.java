package com.aquent.rambo.test;

import java.util.Properties;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.Test;

import com.aquent.rambo.objectRepository.ReadObject;

public class ExecuteTest {

	@Test
	public void testLogin() throws Exception {
		
		System.setProperty("webdriver.chrome.driver", "lib/chromedriver.exe");
		WebDriver webdriver = new ChromeDriver();
		ReadExcel file = new ReadExcel();
		ReadObject object = new ReadObject();
		Properties allObjects = object.getObjectRepository();
		UIOperation operation = new UIOperation(webdriver);
		//Read keyword sheet
		Sheet dataSheet = file.readExcel(System.getProperty("user.dir")+"\\","TestData.xls" , "Sheet1");
		System.out.println(dataSheet);
		//Find number of rows in excel file
		int rowCount = dataSheet.getLastRowNum()-dataSheet.getFirstRowNum();
		//Create a loop over all the rows of excel file to read it
		for (int i = 1; i < rowCount+1; i++) {
			//Loop over all the rows
			Row row = dataSheet.getRow(i);
			//Check if the first cell contain a value, if yes, That means it is the new testcase name
			if(row.getCell(0).toString().length()==0){
				//Print testcase detail on console
				System.out.println(row.getCell(1).toString()+"----"+ row.getCell(2).toString()+"----"+
						row.getCell(3).toString()+"----"+ row.getCell(4).toString());
				//Call perform function to perform operation on UI
				operation.perform(allObjects, row.getCell(1).toString(), row.getCell(2).toString(),
						row.getCell(3).toString(), row.getCell(4).toString());
			}
			else{
				//Print the new testcase name when it started
				System.out.println("New Testcase->"+row.getCell(0).toString() +" Started");
			}
		}
	}
}
