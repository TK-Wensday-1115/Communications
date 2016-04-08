package pl.edu.agh.student.tk;

import com.sun.net.httpserver.HttpServer;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;

@Path("/sensor")
public class RestResource {

    @POST
    @Path("/{name}")
    @Consumes(MediaType.APPLICATION_JSON)
    public void update(@PathParam("name") String name, @Context HttpServer server, ReadingUpdate update) {
        CommunicationsServer.broadcastUpdate(name, update);
    }

}
