package com.example.service;

import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.jboss.resteasy.annotations.providers.multipart.MultipartForm;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.IOException;

@Path("/document")
@Consumes({MediaType.MULTIPART_FORM_DATA})
@Produces(MediaType.APPLICATION_JSON)
public class DocumentResource {

    private final DocumentProcessingService documentProcessingService = new DocumentProcessingService();

    @POST
    @Path("/process")
    public Response processDocument(@MultipartForm FileUploadSupport form) {
        InputStream fileInputStream = form.getFileInputStream();
        String fileName = form.getFileName();
        if (fileInputStream == null || fileName == null) {
            return Response.status(Response.Status.BAD_REQUEST)
                    .entity("File or filename is missing")
                    .build();
        }
        File tempFile = new File("/tmp/" + fileName);

        try (FileOutputStream outputStream = new FileOutputStream(tempFile)) {
            byte[] buffer = new byte[1024];
            int bytesRead;
            while ((bytesRead = fileInputStream.read(buffer)) != -1) {
                outputStream.write(buffer, 0, bytesRead);
            }


            //String assetId = documentProcessingService.processDocument(tempFile);
           String assetId = documentProcessingService.generateUniqueAssetId();
            tempFile.delete();

            return Response.ok().entity("{\"assetId\":\"" + assetId + "\"}").build();

        } catch (IOException e) {
            return Response.serverError()
                    .entity("Error processing file: " + e.getMessage())
                    .build();
        }
    }
}
