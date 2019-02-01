package nl.hsleiden.resource;

import com.fasterxml.jackson.annotation.JsonView;
import com.google.inject.Singleton;
import nl.hsleiden.View;
import nl.hsleiden.model.Couple;
import nl.hsleiden.service.CoupleService;

import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

/**
 * Handles requests from clients and sends them to the server
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