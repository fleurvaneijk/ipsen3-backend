package nl.hsleiden.resource;

import com.fasterxml.jackson.annotation.JsonView;
import io.dropwizard.hibernate.UnitOfWork;
import nl.hsleiden.View;
import nl.hsleiden.model.Dilemma;
import nl.hsleiden.model.DilemmaAdminAsObjects;
import nl.hsleiden.service.DilemmaAdminAsObjectsService;

import javax.inject.Inject;
import javax.inject.Singleton;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.util.Collection;

@Singleton
@Path("/dilemma/admin")
@Produces(MediaType.APPLICATION_JSON)
public class DilemmaAdminAsObjectsResource {

    private final DilemmaAdminAsObjectsService dilemmaAdminAsObjectsService;

    @Inject
    public DilemmaAdminAsObjectsResource(DilemmaAdminAsObjectsService dilemmaAdminAsObjectsService) {
        this.dilemmaAdminAsObjectsService = dilemmaAdminAsObjectsService;
    }

    @GET
    @UnitOfWork
    @JsonView(View.Public.class)
    public DilemmaAdminAsObjects retrieveAll() {
        return dilemmaAdminAsObjectsService.getAll();
    }


}
