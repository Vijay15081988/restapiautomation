package net.vijay.amlosafe.API.util;

import java.lang.reflect.Method;
import java.sql.SQLOutput;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import org.aeonbits.owner.ConfigFactory;
import org.apache.xmlbeans.impl.xb.xsdschema.Public;
import org.testng.ITestContext;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;

public class APISetup {

    public static ExtentReports extentReport;
    public static ThreadLocal<ExtentTest> classLevelLog = new ThreadLocal<ExtentTest>();
    public static ThreadLocal<ExtentTest> testLevelLog = new ThreadLocal<ExtentTest>();
    public static ExtentTest test = null;
    protected static ConfigProperty configProperty;
    public static List fileWriteList = new ArrayList();
    public static List<Object[] > requestInfo = new ArrayList<>();
    
    public void setupEnvironmentInstance() {

        System.out.println("Root Instance selected:- " + System.getProperty("root_env"));
        if (null == System.getProperty("root_env"))
            System.setProperty("root_env", "1k-qa");

        System.out.println("Setting default Root Instance: " + System.getProperty("root_env"));
        ConfigFactory.setProperty("environment", System.getProperty("root_env"));
        configProperty = ConfigFactory.create(ConfigProperty.class);
       
    }

    

    public void manageExtent() {
        TestUtil.archiveTestReport();
        extentReport = Extentmanager
                .GetExtent(configProperty.getTestReportFilepath() + configProperty.getTestReportName());
    }

    @BeforeSuite
    public void beforeSuiteSetup() {
        setupEnvironmentInstance();
        manageExtent();
    }

    @BeforeClass
    public void beforeClass() {
        ExtentTest classLevelTest = extentReport.createTest(getClass().getSimpleName());
        classLevelLog.set(classLevelTest);
    }

    @BeforeMethod
    public void beforeMethod(Method method) {
        test = classLevelLog.get().createNode(method.getName());
        testLevelLog.set(test);
    }

    @AfterMethod
    public void afterMethod(ITestResult result) {
    }

    @AfterSuite
    public void afterSuite(ITestContext result) {

       

    }
}
