package net.vijay.amlosafe.listeners;

import java.io.IOException;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;

import net.vijay.amlosafe.API.util.TestUtil;


public class TestListener implements ITestListener {
    //Extent Report Declarations
    private static ExtentReports extent = ExtentManager.createInstance();
    private static ThreadLocal<ExtentTest> test = new ThreadLocal<ExtentTest>();
    //ExtentTest test;
    TestUtil util = new TestUtil();

    public synchronized void onStart(ITestContext context) {
        //extentTest.get().log(Status.PASS, "Test Passed");

    }


    public synchronized void onFinish(ITestContext context) {
        System.out.println(("Extent Reports Version 3  Test Suite is ending!"));
        extent.flush();
    }


    public synchronized void onTestStart(ITestResult result) {
        System.out.println((result.getMethod().getMethodName() + " started!"));
        ExtentTest extentTest = extent.createTest(result.getMethod().getMethodName(), result.getMethod().getDescription());
        test.set(extentTest);
    }


    public synchronized void onTestSuccess(ITestResult result) {
        System.out.println((result.getMethod().getMethodName() + " passed!"));
        test.get().pass(result.toString());

    }


    public synchronized void onTestFailure(ITestResult result) {
        String FailedTestname = result.getMethod().getMethodName();
        //util.screenShot(FailedTestname);
        String screenshotPath = System.getProperty("user.dir") + "/target/FailureScreenshots/failedTestScreenshot_"
                + FailedTestname + ".jpg";
        System.out.println((result.getMethod().getMethodName() + " failed!"));
        //test.get().fail(result.getThrowable());
        ExtentTest extentTest = extent.createTest(result.getMethod().getMethodName(),
                result.getMethod().getDescription());
        try {
            extentTest.fail("Screenshot", MediaEntityBuilder.createScreenCaptureFromPath(screenshotPath).build());

        } catch (IOException e) {
            e.printStackTrace();
        }

    }


    public synchronized void onTestSkipped(ITestResult result) {
        System.out.println((result.getMethod().getMethodName() + " skipped!"));
        test.get().skip(result.getThrowable());
    }


    public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
        System.out.println(("onTestFailedButWithinSuccessPercentage for " + result.getMethod().getMethodName()));
    }
}
