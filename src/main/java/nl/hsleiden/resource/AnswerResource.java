package nl.hsleiden.resource;

import com.fasterxml.jackson.annotation.JsonView;
import nl.hsleiden.View;
import nl.hsleiden.model.Answer;
import nl.hsleiden.service.AnswerService;

import javax.inject.Inject;
import javax.inject.Singleton;
import javax.validation.Valid;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.awt.*;

@Singleton
@Path("/answer")
@Produces(MediaType.APPLICATION_JSON)
public class AnswerResource {
    private final AnswerService service;

    @Inject
    public AnswerResource(AnswerService service) {
        this.service = service;
    }

    @GET
    @Path("/{id}")
    @JsonView(View.Public.class)
    public Answer retrieve(@PathParam("id") String id) {
        return service.get(id);
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @JsonView(View.Protected.class)
    public void create(Answer answer) {
        System.out.println("==========================================");
        System.out.println(answer);
        System.out.println("==========================================");
        service.add(answer);
    }
}
