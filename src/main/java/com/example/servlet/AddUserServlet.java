package com.example.servlet;

import java.io.IOException;
import java.util.Optional;

import com.example.*;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

@WebServlet("")
public class AddUserServlet extends HttpServlet {
  @Override
  protected void doGet(HttpServletRequest req, HttpServletResponse resp)
      throws ServletException, IOException, NullPointerException {
    if (req.getParameter("firstName") != null) {
      req.setAttribute("firstName", req.getParameter("firstName"));
      req.setAttribute("lastName", req.getParameter("lastName"));
    }
    req.getRequestDispatcher("/add").forward(req, resp);
  }

  @Override
  protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    Optional<String> firstName = Optional.ofNullable(req.getParameter("firstName"));
    Optional<String> lastName = Optional.ofNullable(req.getParameter("lastName"));
    if (firstName.get() != null && lastName.get() != null && !firstName.isPresent() && !lastName.isPresent()) {
      User user = new User(firstName.get(), lastName.get());
      Warehouse.getInstance().addUser(user);
      req.setAttribute("user", user);
    } else {
      req.setAttribute("errorMessage", "Both first name and last name are required");
    }
    req.getRequestDispatcher("/add.jsp").forward(req, resp);
  }
}
