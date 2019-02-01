package nl.hsleiden.resource;

import com.fasterxml.jackson.annotation.JsonView;
import nl.hsleiden.View;
import nl.hsleiden.service.DilemmaOptionsService;

import javax.inject.Inject;
import javax.inject.Singleton;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.util.Collection;

@Singleton
@Path("/dilemmaoptions")
@Produces(MediaType.APPLICATION_JSON)
public class DilemmaOptionResource {

    private final DilemmaOptionsService service;

    @Inject
    public DilemmaOptionResource(DilemmaOptionsService service) {
        this.service = service;
    }

    @GET
    @Path("/{id}")
    @JsonView(View.Public.class)
    public Collection retrieveAll(@PathParam("id") int id) {
        return service.getAll(id);
    }
}
