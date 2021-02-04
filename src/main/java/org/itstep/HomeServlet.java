package org.itstep;

import org.itstep.dto.Post;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

@WebServlet(urlPatterns = "/")
public class HomeServlet extends BaseAbstractServlet {
    List<Post> posts = new CopyOnWriteArrayList<>();

    @Override
    public String getViewName() {
        return "home.jsp";
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("utf-8");
        resp.setCharacterEncoding("utf-8");

        try (Connection connection = DriverManager.getConnection(ConnectionParameters.URL.getValue(),
                ConnectionParameters.USER.getValue(), ConnectionParameters.PASSWORD.getValue());
             Statement statement = connection.createStatement()) {

                ResultSet rs = statement.executeQuery("select p.head, p.description, p.author, p.mydate, p.text, p.fotoName from posts as p");

                while (rs.next()) {

                    Post post = new Post(rs.getString("head"), rs.getString("description"),
                            rs.getString("text"), rs.getString("mydate"),
                            rs.getString("author"), rs.getString("fotoName"));

                    posts.add(post);

                }



        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        if (posts.size() != 0) {
            Collections.sort(posts, new Comparator<Post>() {
                @Override
                public int compare(final Post object1, final Post object2) {
                    return object2.getDate().compareTo(object1.getDate());
                }
            } );
            req.setAttribute("blogs", posts);
        }
        super.doGet(req, resp);

        posts.clear();
    }
}
