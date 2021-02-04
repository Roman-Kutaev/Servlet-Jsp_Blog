package org.itstep.filter;

import org.itstep.ConnectionParameters;
import org.itstep.User;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.*;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

public class SecurityFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {

       HttpSession session = ((HttpServletRequest) servletRequest).getSession();

       if (session.getAttribute("user") != null){

           filterChain.doFilter(servletRequest, servletResponse);

       } else {
           HttpServletResponse httpResponse = (HttpServletResponse) servletResponse;
           httpResponse.sendRedirect("/login");
       }

    }

    @Override
    public void destroy() {
    }
}
