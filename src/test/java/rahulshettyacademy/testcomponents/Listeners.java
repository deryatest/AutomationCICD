package rahulshettyacademy.testcomponents;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import org.openqa.selenium.WebDriver;
import org.testng.ITestListener;
import org.testng.ITestResult;
import org.testng.ITestContext;
import rahulshettyacademy.resources.ExtentReporterNG;

import java.io.IOException;

public class Listeners extends BaseTests implements ITestListener {
   ExtentTest test;
   ExtentReports extent = ExtentReporterNG.getReportObject();
   ThreadLocal<ExtentTest> extentTest = new ThreadLocal<ExtentTest>();

    @Override
    public void onTestStart(ITestResult result){
        test = extent.createTest(result.getMethod().getMethodName());
        extentTest.set(test);//gives a unique thread id via ThreadLocal
    }

    @Override
    public void onTestSuccess(ITestResult result){
// TODO Auto-generated method stub
        extentTest.get().log(Status.PASS, "Test Passed");
        }

    @Override
    public void onTestFailure(ITestResult result){
// TODO Auto-generated method stub
        extentTest.get().fail(result.getThrowable());
        try {
            driver =(WebDriver) result.getTestClass().getRealClass().getField("driver").get(result.getInstance());
        }catch (Exception e1){
            e1.printStackTrace();
        }


        String filePath = null;
        try {
            filePath = getScreenshot(result.getMethod().getMethodName(),driver);
        } catch (IOException e){
            e.printStackTrace();
        }
        extentTest.get().addScreenCaptureFromPath(filePath,result.getMethod().getMethodName());
    }

    @Override
    public void onTestSkipped(ITestResult result){
// TODO Auto-generated method stub
    }

    @Override
    public void onTestFailedButWithinSuccessPercentage(ITestResult result){
// TODO Auto-generated method stub
    }

    @Override
    public void onStart(ITestContext context){
// TODO Auto-generated method stub
    }

    @Override
    public void onFinish(ITestContext context){
        extent.flush();
    }

}
