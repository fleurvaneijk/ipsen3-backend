package nl.hsleiden.resource;

import com.fasterxml.jackson.annotation.JsonView;
import nl.hsleiden.View;
import nl.hsleiden.service.EmailService;

import javax.inject.Inject;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;

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
    public void SendEmail(String email) {
        service.readyMail(email);
    }
}
