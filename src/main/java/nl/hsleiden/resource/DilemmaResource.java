package nl.hsleiden.resource;

import com.fasterxml.jackson.annotation.JsonView;
import io.dropwizard.hibernate.UnitOfWork;
import nl.hsleiden.View;
import nl.hsleiden.model.Dilemma;
import nl.hsleiden.service.DilemmaService;

import javax.inject.Inject;
import javax.inject.Singleton;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.util.Collection;

@Singleton
@Path("/dilemma")
@Produces(MediaType.APPLICATION_JSON)
public class DilemmaResource {

    private final DilemmaService service;

    @Inject
    public DilemmaResource(DilemmaService service) {
        this.service = service;
    }

    @GET
    @UnitOfWork
    @JsonView(View.Public.class)
    public Collection<Dilemma> retrieveAll() {
        System.out.println("GET WERKT !!!!!!");
        return service.getAll();
    }

    @GET
    @UnitOfWork
    @Path("/{id}")
    @JsonView(View.Public.class)
    public Dilemma retrieve(@PathParam("id") int id) {
        return service.get(id);
    }

}
