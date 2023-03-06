package com.smart.config;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Properties;

import org.apache.commons.io.FileUtils;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

import io.appium.java_client.android.AndroidDriver;

public class Utils {

	static ExtentReports extent;
	public HashMap<String, LinkedHashMap<String, String>> testcaseData;
	
	public Properties readPropertiesFile(){
		Properties prop = new Properties();
		FileInputStream fis = null;
		try {
			fis = new FileInputStream(System.getProperty("user.dir")+"//src//main//java//smart//resourcres//config.properties");
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		try {
			prop.load(fis);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return  prop;
	}
	
	public static ExtentReports getReporterObject() {
		String path = System.getProperty("usr.dir")+"\\reports\\Testreport.html";
		ExtentSparkReporter reporter = new ExtentSparkReporter(path);
		reporter.config().setReportName("Demo");
		reporter.config().setDocumentTitle("smart");
		
		extent = new ExtentReports();
		extent.attachReporter(reporter);
		
		return extent;
	}
	
	public String getScrenshotPath(AndroidDriver mobDriver, String testcaseName) throws IOException {
		File source =mobDriver.getScreenshotAs(OutputType.FILE);
		String destinationFilePath  = System.getProperty("usr.dir")+"//reports"+testcaseName+".png";
		FileUtils.copyFile(source,new File(destinationFilePath));
		return destinationFilePath;
	}
	
	public String getScrenshotPath(WebDriver driver, String testcaseName) throws IOException {
		TakesScreenshot scrShot =((TakesScreenshot)driver);
		File source=scrShot.getScreenshotAs(OutputType.FILE);
		String destinationFilePath  = System.getProperty("usr.dir")+"//reports"+testcaseName+".png";
		FileUtils.copyFile(source,new File(destinationFilePath));
		return destinationFilePath;
	}
	
	@SuppressWarnings("resource")
	public HashMap<String, LinkedHashMap<String, String>> readExcel() throws IOException {
		File file = new File(System.getProperty("usr.dir")+"//src//main//java//smart//resourcres//DataSheet.xlsx");
		FileInputStream fis = new FileInputStream(file);
		XSSFWorkbook workBook = new XSSFWorkbook(fis);
		XSSFSheet sheet = workBook.getSheetAt(0);
		GetSet obj = new GetSet();
		testcaseData = new HashMap<String, LinkedHashMap<String, String>>();
		
		int row = sheet.getPhysicalNumberOfRows();
		int col = sheet.getRow(0).getLastCellNum();
		
			for(int i=0; i<row; i++) {
				LinkedHashMap<String, String> list = new LinkedHashMap<String, String>();
					for(int j=0; j<col; j++) 
					{
						String columnName= sheet.getRow(0).getCell(j).toString();
						String columnValue = sheet.getRow(i).getCell(j).toString();
						if(columnName.equalsIgnoreCase("TC ID")){
							obj.setColumnName(columnValue);
						}
						list.put(columnName, columnValue);
					}
					testcaseData.put(obj.getColumnName(), list);
			}
			return testcaseData;
	}
}
