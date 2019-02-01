package nl.hsleiden.resource;

import com.fasterxml.jackson.annotation.JsonView;
import nl.hsleiden.View;
import nl.hsleiden.service.EmailService;

import javax.annotation.security.RolesAllowed;
import javax.inject.Inject;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;

/**
 * Handles requests from clients and sends them to the server
 * @author Fleur van Eijk
 */
@Path("/email")
public class EmailResource {

    private final EmailService service;

    @Inject
    public EmailResource(EmailService service) {
        this.service = service;
    }

    @PUT
    @Path("/sendMail")
    @JsonView(View.Public.class)
    @RolesAllowed({"BEHEERDER", "MEDEWERKER"})
    public void SendEmail(String email) {
        service.readyMail(email);
    }
}
