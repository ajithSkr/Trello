package resources;

import javax.ws.rs.*;
import javax.ws.rs.core.Response;

/**
 * @author ajith.athithyan
 * @project Trello
 */

@Path("/1")
public interface TrelloAPIClient {

    @POST
    @Path("/boards")
    Response createBoard(@QueryParam("key") String key, @QueryParam("token") String token,
                         @QueryParam("name") String name);

    @POST
    @Path("/list")
    Response createList(@QueryParam("key") String key, @QueryParam("token") String token
            , @QueryParam("idBoard") String idBoard, @QueryParam("name") String name);

    @POST
    @Path("/cards/")
    Response createCard(@QueryParam("key") String key, @QueryParam("token") String token
            , @QueryParam("idList") String idList, @QueryParam("name") String name);

    @PUT
    @Path("/cards/{id}")
    Response updateCard(@PathParam("id") String idCard,@QueryParam("key") String key, @QueryParam("token") String token
            , @QueryParam("name") String newName);

}
