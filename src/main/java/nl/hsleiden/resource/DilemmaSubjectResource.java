package nl.hsleiden.resource;

import com.fasterxml.jackson.annotation.JsonView;
import nl.hsleiden.View;
import nl.hsleiden.service.DilemmaSubjectsService;

import javax.inject.Inject;
import javax.inject.Singleton;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.util.Collection;

@Singleton
@Path("/dilemmasubjects")
@Produces(MediaType.APPLICATION_JSON)
public class DilemmaSubjectResource {

    private final DilemmaSubjectsService service;

    @Inject
    public DilemmaSubjectResource(DilemmaSubjectsService service) {
        this.service = service;
    }

    @GET
    @JsonView(View.Public.class)
    public Collection retrieveAllSubjects() { return service.getAllSubjects(); }

    @GET
    @Path("/{subject}")
    @JsonView(View.Public.class)
    public Collection retrieveSubject(@PathParam("subject") String subject) { return service.getSubject(subject); }
}
