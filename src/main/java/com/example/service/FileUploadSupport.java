package com.example.service;

import org.jboss.resteasy.annotations.providers.multipart.PartType;
import jakarta.ws.rs.FormParam;
import java.io.InputStream;

public class FileUploadSupport {

    @FormParam("file")
    @PartType("application/octet-stream")
    private InputStream fileInputStream;

    @FormParam("fileName")
    @PartType("text/plain")
    private String fileName;

    public InputStream getFileInputStream() {
        return fileInputStream;
    }

    public void setFileInputStream(InputStream fileInputStream) {
        this.fileInputStream = fileInputStream;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }
}
