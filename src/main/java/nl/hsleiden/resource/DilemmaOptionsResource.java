package nl.hsleiden.resource;

import com.fasterxml.jackson.annotation.JsonView;
import nl.hsleiden.View;
import nl.hsleiden.service.DilemmaService;

import javax.inject.Inject;
import javax.inject.Singleton;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.util.Collection;

@Singleton
@Path("/dilemmaoptions")
@Produces(MediaType.APPLICATION_JSON)
public class DilemmaOptionsResource {

    private final DilemmaService service;

    @Inject
    public DilemmaOptionsResource(DilemmaService service) {
        this.service = service;
    }

    @GET
    @JsonView(View.Public.class)
    public Collection retrieveAll() {
        return service.getAll();
    }
}
