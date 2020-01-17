import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import common.RestClient;
import common.TrelloConstant;
import org.testng.Assert;
import org.testng.ITestContext;
import org.testng.annotations.Test;
import resources.TrelloAPIClient;

import javax.ws.rs.core.Response;

/**
 * @author ajith.athithyan
 * @project Trello
 */

public class TrelloTest {

    TrelloAPIClient trelloAPIClient = RestClient.clientFactory(TrelloAPIClient.class, TrelloConstant.URL);

    @Test(description = "Create board", priority = 1)
    public void createBoard(ITestContext iTestContext) {
        Response response = trelloAPIClient.createBoard(TrelloConstant.key, TrelloConstant.token, "payments");
        Assert.assertEquals(response.getStatus(), 200);
    }

    @Test(description = "Create List", priority = 2)
    public void createList(
            ITestContext iTestContext) throws JsonProcessingException {
        Response response = trelloAPIClient.createBoard(TrelloConstant.key, TrelloConstant.token, "payments");
        Assert.assertEquals(response.getStatus(), 200);
        String responseData = response.readEntity(String.class);
        ObjectNode jsonNode = new ObjectMapper().readValue(responseData, ObjectNode.class);
        response = trelloAPIClient.createList(TrelloConstant.key, TrelloConstant.token, jsonNode.get("id"
        ).asText(), "unicorn");
        Assert.assertEquals(response.getStatus(), 200);
    }

    @Test(description = "Create Card", priority = 3)
    public void createCard(ITestContext iTestContext) throws JsonProcessingException {
        Response response = trelloAPIClient.createBoard(TrelloConstant.key, TrelloConstant.token, "payments");
        Assert.assertEquals(response.getStatus(), 200);
        String responseData = response.readEntity(String.class);
        ObjectNode jsonNode = new ObjectMapper().readValue(responseData, ObjectNode.class);
        response = trelloAPIClient.createList(TrelloConstant.key, TrelloConstant.token, jsonNode.get("id").asText(),
                "unicorn");
        responseData = response.readEntity(String.class);
        Assert.assertEquals(response.getStatus(), 200);
        jsonNode = new ObjectMapper().readValue(responseData, ObjectNode.class);
        response = trelloAPIClient.createCard(TrelloConstant.key, TrelloConstant.token, jsonNode.get("id").asText(),
                "create payment token");

    }

    @Test(description = "Update the card name", priority = 4)
    public void updateCardName(ITestContext iTestContext) throws JsonProcessingException {
        Response response = trelloAPIClient.createBoard(TrelloConstant.key, TrelloConstant.token, "payments");
        Assert.assertEquals(response.getStatus(), 200);
        String responseData = response.readEntity(String.class);
        ObjectNode jsonNode = new ObjectMapper().readValue(responseData, ObjectNode.class);
        response = trelloAPIClient.createList(TrelloConstant.key, TrelloConstant.token, jsonNode.get("id").asText(),
                "unicorn");
        responseData = response.readEntity(String.class);
        Assert.assertEquals(response.getStatus(), 200);
        jsonNode = new ObjectMapper().readValue(responseData, ObjectNode.class);
        response = trelloAPIClient.createCard(TrelloConstant.key, TrelloConstant.token, jsonNode.get("id").asText(),
                "create payment token");
        Assert.assertEquals(response.getStatus(), 200);
        responseData = response.readEntity(String.class);
        jsonNode = new ObjectMapper().readValue(responseData, ObjectNode.class);
        response = trelloAPIClient.updateCard(jsonNode.get("id").asText(), TrelloConstant.key, TrelloConstant.token,
                "Payment options");
        Assert.assertEquals(response.getStatus(), 200);

    }


}
