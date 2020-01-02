import client.APIException;
import org.testng.IAlterSuiteListener;
import org.testng.xml.XmlSuite;
import utils.Common;

import java.io.IOException;
import java.util.ArrayList;
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
            suite.setIncludedGroups(new ArrayList<>(testCases.values()));
            ;
            for (Map.Entry<String, String> entry : testCases.entrySet()) {
                parameters.put(entry.getValue(), entry.getKey());
            }
            suite.setParameters(parameters);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (APIException e) {
            e.printStackTrace();
        }

    }

}
