package utils.Listeners;

import Main.GetScreenshot;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;
import pageObjects.PaymentPage;

import java.io.IOException;

public class LisinerTest implements ITestListener {

    @Override
    public void onTestStart(ITestResult iTestResult) {


    }
    @Override
    public void onTestSuccess(ITestResult iTestResult) {
        try {
            GetScreenshot.capture(iTestResult.getName());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    @Override
    public void onTestFailure(ITestResult iTestResult) {
        try {
            GetScreenshot.capture(iTestResult.getName());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    @Override
    public void onTestSkipped(ITestResult iTestResult) {

    }
    @Override
    public void onTestFailedButWithinSuccessPercentage(ITestResult iTestResult) {

    }
    @Override
    public void onStart(ITestContext iTestContext) {

    }
    @Override
    public void onFinish(ITestContext iTestContext) {


    }
}
