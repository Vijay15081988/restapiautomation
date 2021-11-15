package net.vijay.amlosafe.API.util;

import java.io.File;

import com.aventstack.extentreports.AnalysisStrategy;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;

public class Extentmanager extends APISetup {

    private static ExtentReports extent; // private static ExtentTest test;
    private static ExtentHtmlReporter htmlReporter; // private static String

    public static ExtentReports GetExtent(String filePath) {
        if (extent != null) {
            return extent;
        } else {
            extent = new ExtentReports();
            extent.attachReporter(getHtmlReporter(filePath));
            extent.setSystemInfo("Host Name", "Harshit Garg");

            extent.setAnalysisStrategy(AnalysisStrategy.CLASS);
            return extent;
        }
    }

    public static ExtentHtmlReporter getHtmlReporter(String filePath) {

        htmlReporter = new ExtentHtmlReporter(filePath);
        // htmlReporter.setAppendExisting(false);
        String filepath = System.getProperty("user.dir") + File.separator + "src" + File.separator + "main" + File.separator + "resources" + File.separator + "configXMLFiles" + File.separator + "ReportsConfig.xml";
        System.out.println("filepath: " + filepath);
        htmlReporter.loadXMLConfig(filepath);

        return htmlReporter;
    }
}
