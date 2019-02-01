package nl.hsleiden.resource;

import org.glassfish.jersey.media.multipart.FormDataParam;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.io.*;

@Path("/upload")
public class ImageResource {

    @POST
    @Consumes({MediaType.MULTIPART_FORM_DATA})
    @Produces({MediaType.TEXT_PLAIN})
    public boolean uploadFile(
                              @FormDataParam("file") InputStream imageData,
                              @FormDataParam("filename") String fileName) {

        String path = ""; 
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
