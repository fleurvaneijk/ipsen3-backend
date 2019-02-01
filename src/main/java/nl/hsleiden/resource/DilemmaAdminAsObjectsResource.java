package nl.hsleiden.resource;

import com.codahale.metrics.annotation.Timed;
import com.fasterxml.jackson.annotation.JsonView;
import io.dropwizard.hibernate.UnitOfWork;
import nl.hsleiden.View;
import nl.hsleiden.model.Dilemma;
import nl.hsleiden.model.DilemmaAdminAsAttributes;
import nl.hsleiden.model.DilemmaAdminAsObjects;
import nl.hsleiden.service.DilemmaAdminAsObjectsService;

import javax.annotation.security.RolesAllowed;
import javax.inject.Inject;
import javax.inject.Singleton;
import javax.validation.Valid;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.Collection;
import java.util.List;

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
    @RolesAllowed({"BEHEERDER", "MEDEWERKER"})
    @JsonView(View.Public.class)
    public DilemmaAdminAsObjects retrieveAll() {
        return dilemmaAdminAsObjectsService.getAll();
    }

    @POST
    @Timed
    @Path("/delete")
    @Consumes
    @RolesAllowed({"BEHEERDER", "MEDEWERKER"})
    @UnitOfWork
    @JsonView(View.Public.class)
    public void delete(DilemmaAdminAsAttributes dilemmaAdminAsAttributes) {

        dilemmaAdminAsObjectsService.deleteDilemmaAdmin(dilemmaAdminAsAttributes.get_dilemmaId());
    }

    @POST
    @Timed
    @Consumes
    @RolesAllowed({"BEHEERDER", "MEDEWERKER"})
    @UnitOfWork
    @JsonView(View.Public.class)
    public void updateDilemmaAdminAsAttributes(@Valid DilemmaAdminAsAttributes dilemmaAdminAsAttributes) {

        dilemmaAdminAsObjectsService.AddDilemmaAdmin(dilemmaAdminAsAttributes);
    }


}
