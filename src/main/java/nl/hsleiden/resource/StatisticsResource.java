package nl.hsleiden.resource;

import com.fasterxml.jackson.annotation.JsonView;
import com.google.inject.Singleton;
import nl.hsleiden.View;
import nl.hsleiden.service.StatisticsService;

import javax.annotation.security.RolesAllowed;
import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.util.Collection;
import java.util.List;

/**
 * @author Fleur van Eijk
 */
@Singleton
@Path("/statistics")
@Produces(MediaType.APPLICATION_JSON)
public class StatisticsResource {

    private final StatisticsService service;

    @Inject
    public StatisticsResource(StatisticsService service)
    {
        this.service = service;
    }

    @GET
    @Path("/ratingPerSubject")
    @JsonView(View.Public.class)
    @RolesAllowed({"ADMIN", "MEDEWERKER"})
    public List retrieveRatingPerSubject()
    {
        return service.getRatingPerSubject();
    }

}
