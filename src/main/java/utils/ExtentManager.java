package utils;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

public class ExtentManager {
	
	private static ExtentReports extentReport;
	
	public static ExtentReports reportHTML() {
		if(extentReport == null) {
			String filePathHTML = System.getProperty("user.dir") + File.separator + "ExtentReport" + File.separator + "HTMLReport.html";
			
			ExtentSparkReporter sparkReporter = new ExtentSparkReporter(filePathHTML);
			sparkReporter.config().setDocumentTitle("RahulShettyAcademy");
			sparkReporter.config().setReportName("RahulShettyAcademy - Report");

			extentReport = new ExtentReports();
			extentReport.attachReporter(sparkReporter);
			extentReport.setSystemInfo("Tester", "Karun Yadav");

			String windowSTime = new SimpleDateFormat("YYYY-MM-DD:HH:MM:SS").format(new Date());
			extentReport.setSystemInfo("Execution Time (Windows)", windowSTime);
		}
		return extentReport;
	}
	
}
