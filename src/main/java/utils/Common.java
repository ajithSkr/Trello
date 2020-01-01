package utils;

import client.APIException;
import helpers.*;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import java.io.File;
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
        String projectName = "Test_trello";
        TestRailProject testRailProject = new TestRailProject();
        testRailProject.addProject(projectName, "Sample", true);
        TestRailSuite testRailSuite = new TestRailSuite(projectName);
        String suiteName = testRailSuite.addSuite().get("name").toString();
        TestRailSection testRailSection = new TestRailSection(suiteName, projectName);
        String sectionName = testRailSection.addSection().get("name").toString();
        TestRailTestCase testRailTestCase = new TestRailTestCase(sectionName, suiteName, projectName);
        testRailTestCase.addTestCaseFromExcel(new File("src/test/resources/Trello .xlsx"));
        TestRailTestRun testRailTestRun = new TestRailTestRun(sectionName, suiteName, projectName);
        JSONObject testRun = testRailTestRun.addTestRun("Added test run");
        int runId = Integer.parseInt(testRun.get("id").toString());
        JSONArray testCase = testRailTestRun.getTestAutomated(runId);
        for (int i = 0; i < testCase.size(); i++) {
            JSONObject tests = (JSONObject) testCase.get(i);
            testCases.put(tests.get("id").toString(), tests.get("title").toString());
        }
        return testCases;
    }
}
