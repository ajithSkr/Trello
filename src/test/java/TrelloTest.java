import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import common.RestClient;
import org.json.simple.JSONObject;
import org.testng.Assert;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import resources.TrelloAPIClient;
import utils.TrelloConstant;

import javax.ws.rs.core.Response;

/**
 * @author ajith.athithyan
 * @project Trello
 */

public class TrelloTest {

    TrelloAPIClient trelloAPIClient = RestClient.clientFactory(TrelloAPIClient.class, TrelloConstant.URL);
    static String result = "";
    static int testId;

    @Parameters("Create board")
    @Test(description = "Create board", priority = 1, groups = "Create board")
    public void createBoard(String id) {
        testId = Integer.parseInt(id);
        Response response = trelloAPIClient.createBoard(TrelloConstant.key, TrelloConstant.token, "payments");
        Assert.assertEquals(response.getStatus(), 200);
        JSONObject jsonObject = response.readEntity(JSONObject.class);
        result = "Board Id = " + jsonObject.get("id").toString();
    }

    @Parameters("Create List")
    @Test(description = "Create List", priority = 2, groups = "Create List")
    public void createList(String id) throws JsonProcessingException {
        testId = Integer.parseInt(id);
        Response response = trelloAPIClient.createBoard(TrelloConstant.key, TrelloConstant.token, "payments");
        Assert.assertEquals(response.getStatus(), 200);
        String responseData = response.readEntity(String.class);
        ObjectNode jsonNode = new ObjectMapper().readValue(responseData, ObjectNode.class);
        response = trelloAPIClient.createList(TrelloConstant.key, TrelloConstant.token, jsonNode.get("id"
        ).asText(), "unicorn");
        Assert.assertEquals(response.getStatus(), 200);
        JSONObject jsonObject = response.readEntity(JSONObject.class);
        result = "List Id = " + jsonObject.get("id").toString();


    }

    @Parameters("Create Card")
    @Test(description = "Create Card", priority = 3, groups = "Create Card")
    public void createCard(String id) throws JsonProcessingException {
        testId = Integer.parseInt(id);
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
        JSONObject jsonObject = response.readEntity(JSONObject.class);
        result = "Card Id = " + jsonObject.get("id").toString();
    }

    @Parameters("Update Card")
    @Test(description = "Update the card name", priority = 4, groups = "Update Card")
    public void updateCardName(String id) throws JsonProcessingException {
        testId = Integer.parseInt(id);
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
        JSONObject jsonObject = response.readEntity(JSONObject.class);
        result = "Updated card name = " + jsonObject.get("name").toString();
    }


}
