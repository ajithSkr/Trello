package utils;

import lombok.extern.slf4j.Slf4j;
import org.testng.Assert;
import org.testng.IInvokedMethod;
import org.testng.IInvokedMethodListener;
import org.testng.ITestResult;

/**
 * @author ajith.athithyan
 * @project Trello
 */
@Slf4j
public class TrelloInvokedMethodListener implements IInvokedMethodListener {
    @Override
    public void beforeInvocation(IInvokedMethod iInvokedMethod, ITestResult iTestResult) {

        try {

            Assert.assertNotNull(iTestResult.getTestContext().getSuite().getParameter(iTestResult.getName()), "Test " +
                    "not defined in TestRail :");

        } catch (NullPointerException e) {
            e.printStackTrace();

        }

    }

    @Override
    public void afterInvocation(IInvokedMethod iInvokedMethod, ITestResult iTestResult) {


    }
}
