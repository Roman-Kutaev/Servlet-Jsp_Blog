package org.itstep;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Optional;

@MultipartConfig
@WebServlet(urlPatterns = "/admin")
public class AdminServlet extends BaseAbstractServlet {

    private static final Logger logger = LoggerFactory.getLogger(HttpServlet.class);
    public static final String UPLOADS = "resources/foto";

    @Override
    public String getViewName() {
        return "admin.jsp";
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("utf-8");
        resp.setCharacterEncoding("utf-8");

        String title = req.getParameter("title");
        String author = req.getParameter("author");
        String description = req.getParameter("description");
        String text = req.getParameter("text");
        String draft = req.getParameter("draft");
        String delete = req.getParameter("remove");
        System.out.println(title);

        String file = saveImg(req.getPart("file"));

        if (file.equals("img not found") & delete == null){
            HttpSession session = req.getSession();
            session.setAttribute("error", "error img");
            resp.sendRedirect(getServletContext().getContextPath() + req.getRequestURL());

        } else {

            try (Connection connection = DriverManager.getConnection(ConnectionParameters.URL.getValue(),
                    ConnectionParameters.USER.getValue(), ConnectionParameters.PASSWORD.getValue());
                 Statement statement = connection.createStatement()) {
                if (draft != null) {
                    statement.executeUpdate("insert into blog.drafts(Head, Author, Description, Text, FotoName)" +
                            " values ('" + title + "', '" + author + "',  '" + description + "', '" + text + "', '" + file + "') ");
                } else {

                    if (delete != null) {
                        statement.executeUpdate("delete from blog.posts where head = '" + title + "' and author = '" + author + "'");

                    } else {
                        statement.executeUpdate("insert into blog.posts(Head, Author, Description, Text, FotoName)" +
                                " values ('" + title + "', '" + author + "',  '" + description + "', '" + text + "', '" + file + "') ");
                    }
                }

            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
            resp.sendRedirect("/home");
        }
    }

    private String saveImg(Part part) throws IOException {
        if (part == null) return "img not found";
        String comtent = part.getContentType();
        int last = comtent.lastIndexOf("/");
        String img = comtent.substring(0,last);
        if (img.equals("image")){
            String contentDisposition = part.getHeader("Content-Disposition");
            int start = contentDisposition.indexOf("filename=");
            start += "filename=".length();
            int end = contentDisposition.lastIndexOf("\"");
            String filename = contentDisposition.substring(start + 1, end);
            logger.info("filename: {}", filename);
            String uploadsDirUrl = getServletContext().getRealPath(UPLOADS);
            String absolutePathToFile = uploadsDirUrl + "/" + filename;
            logger.info("File path: {}", absolutePathToFile);
            part.write(absolutePathToFile);
            return filename;
        }else return "img not found";
    }
}
