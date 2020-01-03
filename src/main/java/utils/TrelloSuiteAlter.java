package utils;

import client.APIException;
import org.apache.commons.text.CaseUtils;
import org.testng.IAlterSuiteListener;
import org.testng.xml.XmlSuite;
import org.testng.xml.XmlTest;

import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * @author ajith.athithyan
 * @project Trello
 */
public class TrelloSuiteAlter implements IAlterSuiteListener {

    @Override
    public void alter(List<XmlSuite> suites) {
        XmlSuite suite = suites.get(0);
        Map<String, String> parameters = new LinkedHashMap<>();
        try {
            Map<String, String> testCases = Common.testRailTests();
            for (Map.Entry<String, String> entry : testCases.entrySet()) {
                String camelCaseName = CaseUtils.toCamelCase(entry.getValue(), false, ' ', '_', '-', '#', '.', '@');
                parameters.put(camelCaseName, entry.getKey());
            }
            suite.setParameters(parameters);
        } catch (IOException | APIException e) {
            e.printStackTrace();
        }

    }

}
