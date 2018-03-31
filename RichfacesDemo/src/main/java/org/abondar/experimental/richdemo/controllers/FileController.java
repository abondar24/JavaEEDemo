package org.abondar.experimental.richdemo.controllers;

import org.richfaces.event.FileUploadEvent;
import org.richfaces.model.UploadedFile;

import javax.enterprise.context.SessionScoped;
import javax.inject.Named;
import java.io.IOException;
import java.io.OutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Named("fileController")
@SessionScoped
public class FileController implements Serializable {

    private List<UploadedFile> files = new ArrayList<>();

    public void fileListener(FileUploadEvent event) throws Exception{
        UploadedFile file = event.getUploadedFile();
        files.add(file);
    }


    public void paint(OutputStream stream, Object object) throws IOException {
        stream.write(getFiles().get((Integer) object).getData());
    }

    public Date getTimestamp(){
        return new Date();
    }



    public List<UploadedFile> getFiles() {
        return files;
    }

    public void setFiles(List<UploadedFile> files) {
        this.files = files;
    }


}
