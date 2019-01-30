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
 *  https://jersey.java.net/documentation/latest/user-guide.html#jaxrs-resources
 *
 * @author Peter van Vliet
 */
@Singleton
@Path("/couples")
@Produces(MediaType.APPLICATION_JSON)
public class CoupleResource
{
    private final CoupleService service;

    @Inject
    public CoupleResource(CoupleService service)
    {
        this.service = service;
    }

    @GET
    @JsonView(View.Public.class)
    public Collection<Couple> retrieveAll()
    {
        return service.getAll();
    }

    @GET
    @Path("/{id}")
    @JsonView(View.Public.class)
    public Couple retrieve(@PathParam("id") String id)
    {
        return service.get(id);
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @JsonView(View.Protected.class)
    public void create(@Valid Couple couple)
    {
        service.add(couple);
    }

    @PUT
    @Path("/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    @JsonView(View.Protected.class)
    public void update(@PathParam("id") String id, Couple couple)
    {
        service.update(id, couple);
    }

    @DELETE
    @Path("/{id}")
    public void delete(@PathParam("id") int id)
    {
        service.delete(id);
    }

    @GET
    @Path("/coupleTableInfo")
    @JsonView(View.Public.class)
    public List retrieveCoupleTableInfo()
    {
        return service.getCoupleTableInfo();
    }

    @PUT
    @Path("/updatePregnant")
    @Consumes(MediaType.APPLICATION_JSON)
    @JsonView(View.Public.class)
    public void updatePregnant(List list) {
        String email = list.get(0).toString();
        boolean pregnant = Boolean.valueOf(list.get(1).toString());
        service.updatePregnant(email, pregnant);
    }

    @PUT
    @Path("/updatePregnantWeeks")
    @Consumes(MediaType.APPLICATION_JSON)
    @JsonView(View.Public.class)
    public void updatePregnantWeeks(List list) {
        String email = list.get(0).toString();
        int weeks = Integer.parseInt(list.get(1).toString());
        System.out.println(weeks);
        service.updatePregnantWeeks(email, weeks);
    }
}

