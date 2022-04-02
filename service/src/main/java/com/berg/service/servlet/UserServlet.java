package com.berg.service.servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import com.berg.service.service.UserService;

import java.io.IOException;

@WebServlet("/users")
public class UserServlet extends HttpServlet {

     private final UserService userService = new UserService();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        var user = userService.getUser(1L);
    }
}
