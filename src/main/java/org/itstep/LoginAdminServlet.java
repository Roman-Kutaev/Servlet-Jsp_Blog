package org.itstep;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.*;

@WebServlet(urlPatterns = "/login")
public class LoginAdminServlet extends BaseAbstractServlet {

    @Override
    public String getViewName() {
        return "login.jsp";
    }


    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String login = req.getParameter("login");
        String password = req.getParameter("password");
        User user = new User(login, password);

        try (Connection connection = DriverManager.getConnection(ConnectionParameters.URL.getValue(),
                ConnectionParameters.USER.getValue(), ConnectionParameters.PASSWORD.getValue());
             Statement statement = connection.createStatement()) {

            ResultSet rs = statement.executeQuery("select admins.login, admins.password from admins");

            while (rs.next()) {
                User admin = new User(rs.getString("login"), rs.getString("password"));

                if (admin.equals(user)) {

                    HttpSession session = req.getSession();
                    session.setAttribute("user", user);

                    resp.sendRedirect("/admin");
                    return;
                }
            }

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        resp.sendRedirect("/login");

    }
}
