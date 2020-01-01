import client.APIException;
import helpers.TestRailTestResult;
import org.json.simple.JSONObject;
import org.testng.ITestResult;
import org.testng.TestListenerAdapter;
import utilities.Status;
import utils.Common;

import java.io.IOException;
import java.lang.reflect.Method;
import java.util.Arrays;

/**
 * @author ajith.athithyan
 * @project Trello
 */
public class TrelloTestListener extends TestListenerAdapter {

    @Override
    public void onTestSuccess(ITestResult iTestResult) {

        TestRailTestResult testRailTestResult=new TestRailTestResult();
        try {
            testRailTestResult.addTestResult(TrelloTest.testId,Status.PASSED.getStatus(),"Passed",TrelloTest.result);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (APIException e) {
            e.printStackTrace();
        }

    }

    @Override
    public void onTestFailure(ITestResult iTestResult) {
        TestRailTestResult testRailTestResult=new TestRailTestResult();
        try {
            testRailTestResult.addTestResult(TrelloTest.testId,Status.FAILED.getStatus(),"Passed","");
        } catch (IOException e) {
            e.printStackTrace();
        } catch (APIException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onTestSkipped(ITestResult iTestResult) {
        TestRailTestResult testRailTestResult=new TestRailTestResult();
        try {
            testRailTestResult.addTestResult(TrelloTest.testId,Status.BLOCKED.getStatus(),"Passed","");
        } catch (IOException e) {
            e.printStackTrace();
        } catch (APIException e) {
            e.printStackTrace();
        }
    }
}
