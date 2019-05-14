package com.turbopro.MethodsLibrary;

import org.testng.IRetryAnalyzer;
import org.testng.ITestResult;

public class RetryFailedCases implements IRetryAnalyzer {
    private int retryCnt = 0;

    //You could mention maxRetryCnt (Maximiun Retry Count) as per your requirement. Here I took 1, If any failed testcases then it runs two times
    private final int maxRetryCnt = 1;

    //This method will be called everytime a test fails. It will return TRUE if a test fails and need to be retried, else it returns FALSE
    @Override
    public boolean retry(ITestResult result) {
        if (retryCnt < maxRetryCnt) {
            System.out.println("\n   >> Retrying " + result.getName() + " again.");
            retryCnt++;
            return true;
        }
        return false;
    }

}