package nl.hsleiden.resource;

import com.fasterxml.jackson.annotation.JsonView;
import com.google.inject.Singleton;
import nl.hsleiden.View;
import nl.hsleiden.model.Couple;
import nl.hsleiden.service.CoupleService;

import javax.inject.Inject;
import javax.validation.Valid;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.Collection;
import java.util.List;

/**
 * Meer informatie over resources:
 * https://jersey.java.net/documentation/latest/user-guide.html#jaxrs-resources
 *
 * @author Peter van Vliet
 */
@Singleton
@Path("/secondcouples")
@Produces(MediaType.APPLICATION_JSON)
public class SecondCoupleResource {
    private final CoupleService service;

    @Inject
    public SecondCoupleResource(CoupleService service) {
        this.service = service;
    }

    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    @JsonView(View.Public.class)
    public void updateCouplePregnant(Couple couple) {

        service.updateFullCouple(couple);
    }
}