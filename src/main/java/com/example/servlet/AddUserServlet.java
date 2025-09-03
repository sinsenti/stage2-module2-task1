package com.example.servlet;

import java.io.IOException;

import com.example.*;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

@WebServlet("/add")
public class AddUserServlet extends HttpServlet {

  @Override
  protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    req.getRequestDispatcher("/add").forward(req, resp);
  }

  @Override
  protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    String firstName = req.getParameter("firstName");
    String lastName = req.getParameter("lastName");
    if (firstName != null && lastName != null) {
      User user = new User(firstName, lastName);
      Warehouse.getInstance().addUser(user);
      req.setAttribute("user", user);
    }

    req.getRequestDispatcher("/add").forward(req, resp);

  }
}
