package configs.listeners;

import org.openqa.selenium.TimeoutException;
import org.testng.Assert;
import org.testng.IInvokedMethod;
import org.testng.IInvokedMethodListener;
import org.testng.ITestResult;

public class TimeoutExceptionListener implements IInvokedMethodListener {

    @Override
    public void beforeInvocation(IInvokedMethod method, ITestResult testResult) {
        // No action needed before invocation
    }

public void afterInvocation(IInvokedMethod method, ITestResult testResult) {
    if (testResult.getThrowable() instanceof TimeoutException) {
//        String errorMessage = "Test failed due to TimeoutException: " + ((TimeoutException) testResult.getThrowable()).getMessage();
//        testResult.setStatus(ITestResult.FAILURE);
//        testResult.setThrowable(new AssertionError(errorMessage));
        Assert.fail( "Test failed due to TimeoutException: " + testResult.getThrowable().getMessage());
    }
}
}

