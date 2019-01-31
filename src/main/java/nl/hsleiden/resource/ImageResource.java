package nl.hsleiden.resource;

import io.dropwizard.auth.Auth;
import nl.hsleiden.model.User;
import org.glassfish.jersey.media.multipart.FormDataParam;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.io.*;

@Path("/upload")
public class ImageResource {

    @POST
    @Consumes({MediaType.MULTIPART_FORM_DATA})
    @Produces({MediaType.TEXT_PLAIN})
    public boolean uploadFile(
//            @Auth User authenticator,
                              @FormDataParam("file") InputStream imageData,
                              @FormDataParam("filename") String fileName) {
//        String role = authenticator.getRole();
//        if (!role.equals("MEDEWERKER") && !role.equals("ADMIN")) {
//            throw new NotAuthorizedException("");
//        }
        String path = ""; // TODO
        return saveFile(imageData, path);
    }

    private boolean saveFile(InputStream imageData, String path) {
        int read;
        final int BUFFER_LENGTH = 1024;
        final byte[] buffer = new byte[BUFFER_LENGTH];
        try {
            OutputStream out = new FileOutputStream(new File(path));
            while ((read = imageData.read(buffer)) != -1) {
                out.write(buffer, 0, read);
            }
            out.flush();
            out.close();
        } catch (IOException e) {
            return false;
        }
        return true;
    }
}
