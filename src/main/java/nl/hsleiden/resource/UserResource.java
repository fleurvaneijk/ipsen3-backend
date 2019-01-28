package nl.hsleiden.resource;

import com.fasterxml.jackson.annotation.JsonView;
import com.google.inject.Singleton;
import io.dropwizard.auth.Auth;
import java.util.Collection;
import javax.inject.Inject;
import javax.validation.Valid;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import nl.hsleiden.View;
import nl.hsleiden.model.User;
import nl.hsleiden.service.UserService;

/**
 * Meer informatie over resources:
 *  https://jersey.java.net/documentation/latest/user-guide.html#jaxrs-resources
 * 
 * @author Peter van Vliet
 */
@Singleton
@Path("/users")
@Produces(MediaType.APPLICATION_JSON)
public class UserResource
{
    private final UserService service;
    
    @Inject
    public UserResource(UserService service)
    {
        this.service = service;
    }
    
    @GET
    @JsonView(View.Public.class)
//    @RolesAllowed("PARENT")
    public Collection<User> retrieveAll()
    {
        return service.getAll();
    }

    @GET
    @Path("/parents")
    @JsonView(View.Public.class)
    public Collection<User> retrieveAllParents()
    {
        return service.getAllParents();
    }
    
    @GET
    @Path("/{id}")
    @JsonView(View.Public.class)
//    @RolesAllowed("PARENT")
    public User retrieve(@PathParam("id") String id)
    {
        return service.get(id);
    }
    
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @JsonView(View.Protected.class)
    public void create(@Valid User[] user)
    {
        service.add(user);
    }
    
    @PUT
    @Path("/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    @JsonView(View.Protected.class)
//    @RolesAllowed("PARENT")
    public void update(@PathParam("id") String id, @Auth User authenticator, User user)
    {
        service.update(authenticator, id, user);
    }
    
    @DELETE
    @Path("/{id}")
//    @RolesAllowed("ADMIN")
    public void delete(@PathParam("id") String id)
    {
        service.delete(id);
    }
    
    @GET
    @Path("/me")
    @JsonView(View.Private.class)
    public User authenticate(@Auth User authenticator)
    {
        return authenticator;
    }
}
