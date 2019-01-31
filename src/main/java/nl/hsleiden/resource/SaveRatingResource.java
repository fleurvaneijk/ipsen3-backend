package nl.hsleiden.resource;

import com.fasterxml.jackson.annotation.JsonView;
import nl.hsleiden.View;
import nl.hsleiden.model.SaveRating;
import nl.hsleiden.service.SaveRatingService;

import javax.inject.Inject;
import javax.inject.Singleton;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;


@Singleton
@Path("/rating")
@Produces(MediaType.APPLICATION_JSON)
public class SaveRatingResource {
    private final SaveRatingService service;

    @Inject
    public SaveRatingResource(SaveRatingService service) {
        this.service = service;
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @JsonView(View.Protected.class)
    public void create(SaveRating saveRating) {

        service.add(saveRating);
    }
}
