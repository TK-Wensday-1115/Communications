package pl.edu.agh.student.tk;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

@Path("/sensor")
public class RestService {

    @GET
    @Path("/{name}")
    @Produces(MediaType.TEXT_PLAIN)
    public String hello(@PathParam("name") String name) {
        return "Hello " + name + "!";
    }

    @POST
    @Path("/{name}")
    @Consumes(MediaType.APPLICATION_JSON)
    public void update(@PathParam("name") String name, ReadingUpdate update) {
        CommunicationsServer.broadcastUpdate(name, update);
    }

}
