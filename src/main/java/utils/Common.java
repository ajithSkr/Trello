package utils;

import client.APIException;
import helpers.TestRailTestRun;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * @author ajith.athithyan
 * @project Trello
 */
public class Common {

    public static Map<String, String> testRailTests() throws IOException, APIException {
        Map<String, String> testCases = new LinkedHashMap<>();
        String projectName = "Trello";
        String suiteName = "Trello Suite";
        int testId = Integer.parseInt(TestRailTestRun.addTestRun(projectName, suiteName).get("id").toString());
        JSONArray testRunTestAutomatedTests = TestRailTestRun.getTestsThatAreAutomated(testId);
        for (Object testRunTestAutomatedTest : testRunTestAutomatedTests) {
            JSONObject test = (JSONObject) testRunTestAutomatedTest;
            testCases.put(test.get("id").toString(), test.get("title").toString());
        }
        return testCases;
    }


}
