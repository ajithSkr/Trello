import client.APIException;
import helpers.TestRailTestResult;
import org.testng.ITestResult;
import org.testng.TestListenerAdapter;
import utilities.Status;

import java.io.IOException;

/**
 * @author ajith.athithyan
 * @project Trello
 */
public class TrelloTestListener extends TestListenerAdapter {

    @Override
    public void onTestSuccess(ITestResult iTestResult) {

        TestRailTestResult testRailTestResult = new TestRailTestResult();
        try {
            int testId = (Integer) iTestResult.getTestContext().getAttribute("testId");
            String result = iTestResult.getTestContext().getAttribute("result").toString();
            testRailTestResult.addTestResult(testId, Status.PASSED.getStatus(), "Passed", result);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (APIException e) {
            e.printStackTrace();
        }

    }

    @Override
    public void onTestFailure(ITestResult iTestResult) {
        TestRailTestResult testRailTestResult = new TestRailTestResult();
        try {
            int testId = (Integer) iTestResult.getTestContext().getAttribute("testId");
            String result = "Error";
            testRailTestResult.addTestResult(testId, Status.FAILED.getStatus(), "Failed", result);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (APIException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onTestSkipped(ITestResult iTestResult) {
        TestRailTestResult testRailTestResult = new TestRailTestResult();
        try {
            int testId = (Integer) iTestResult.getTestContext().getAttribute("testId");
            String result ="Error";
            testRailTestResult.addTestResult(testId, Status.BLOCKED.getStatus(), "Skipped", result);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (APIException e) {
            e.printStackTrace();
        }
    }
}
