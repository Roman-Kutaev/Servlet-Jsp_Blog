package org.itstep;

import jdk.internal.jimage.ImageStrings;
import org.itstep.dto.Post;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.URLEncoder;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

@WebServlet(urlPatterns = "/post")
public class PostServlet extends BaseAbstractServlet {
    List<Post> posts = new CopyOnWriteArrayList<>();

    @Override
    public String getViewName() {
        return "post.jsp";
    }


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("utf-8");
        resp.setCharacterEncoding("utf-8");

        String numPost = req.getParameter("num");
        int num = Integer.parseInt(numPost);

        try (Connection connection = DriverManager.getConnection(ConnectionParameters.URL.getValue(),
                ConnectionParameters.USER.getValue(), ConnectionParameters.PASSWORD.getValue());
             Statement statement = connection.createStatement()) {

            ResultSet rs = statement.executeQuery("select p.head, p.description, p.author, p.mydate, p.text, p.FotoName from posts as p");


            while (rs.next()) {

                Post post = new Post(rs.getString("head"), rs.getString("description"),
                        rs.getString("text"), rs.getString("mydate"),
                        rs.getString("author"), rs.getString("fotoName"));

                posts.add(0,post);

            }

            req.setAttribute("post", posts.get(num));


        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        super.doGet(req, resp);
    }
}

