package com.jag.controller;

import com.jag.dao.UserDao;
import com.jag.model.User;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.SQLException;

public class AuthController extends HttpServlet {

    UserDao userDao;

    public AuthController() {
        userDao = new UserDao();
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String username = req.getParameter("username");
        String password = req.getParameter("password");
        User loggedInUser;
        try {
            loggedInUser = userDao.loginUser(username, password);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        if (loggedInUser != null) {
            HttpSession session = req.getSession();
            session.setAttribute("id", loggedInUser.getId());
            RequestDispatcher dispatcher = req.getRequestDispatcher("todo");
            dispatcher.forward(req, resp);
        } else {
            req.setAttribute("error", true);
            RequestDispatcher dispatcher = req.getRequestDispatcher("index.jsp");
            dispatcher.forward(req, resp);
        }
    }
}
