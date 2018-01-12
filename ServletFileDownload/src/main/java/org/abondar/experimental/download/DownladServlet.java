package org.abondar.experimental.download;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

@WebServlet(urlPatterns ="/filedownload/*")
public class DownladServlet extends HttpServlet{


    @Override
    public void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException{

        String fileName = req.getRequestURI().split("/")[3];
        downloadFile(resp,fileName);
    }


    private void downloadFile(HttpServletResponse response,String filename)throws IOException{
        byte [] file = Files.readAllBytes(new File("/home/abondar/"+filename).toPath());

        response.setHeader("Expires", "0");
        response.setHeader("Cache-Control",
                "must-revalidate, post-check=0, pre-check=0");
        response.setHeader("Pragma", "public");
        response.setContentType("application/txt");
        response.setHeader("Content-disposition",
                "attachment; filename="+filename);
        response.setContentLength(file.length);

        try(ServletOutputStream sos = response.getOutputStream();) {
            sos.write(file);
            sos.flush();
            sos.close();
        }
    }
}
