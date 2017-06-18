package com.itacademy;

import com.itacademy.service.UserService;

import javax.servlet.ServletException;
import javax.servlet.annotation.*;
import javax.servlet.http.*;
import java.io.IOException;

@WebServlet("/")
public class UserNameServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setAttribute("username", new UserService().getUserById());
        getServletContext()
                .getRequestDispatcher("/WEB-INF/jsp/username.jsp").forward(req, resp);
    }
}
