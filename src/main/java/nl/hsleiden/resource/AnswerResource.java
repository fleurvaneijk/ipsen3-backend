package nl.hsleiden.resource;

import com.fasterxml.jackson.annotation.JsonView;
import nl.hsleiden.View;
import nl.hsleiden.model.Answer;
import nl.hsleiden.service.AnswerService;

import javax.inject.Inject;
import javax.inject.Singleton;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.Collection;


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
    @JsonView(View.Public.class)
    public Collection retrieveAllAnswers() { return service.getAllAnswers(); }

    @GET
    @Path("/{email}/{id}")
    @JsonView(View.Public.class)
    public Collection retrieveAnswer(@PathParam("email") String email, @PathParam("id") int id) {
        return service.getSingleAnswer(email, id);
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @JsonView(View.Protected.class)
    public void create(Answer answer) {
        System.out.println("DILEMMA BOYSSSSSS");
        service.add(answer);
    }
}
