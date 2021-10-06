package com.codecool.shop.api;


import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Arrays;

@WebServlet(name = "CheckoutChangeServlet", urlPatterns = {"/checkout-change"})
public class CheckoutChangeServlet extends javax.servlet.http.HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String email = request.getParameter("email");
        String firstName = request.getParameter("first-name");
        String lastName = request.getParameter("last-name");
        String address = request.getParameter("address");
        if (email != null && !email.equals("")) {
            System.out.println(email);
        }
        if (firstName != null && !firstName.equals("")) {
            System.out.println(firstName);
        }
        if (lastName != null && !lastName.equals("")) {
            System.out.println(lastName);
        }
        if (address != null && !address.equals("")) {
            System.out.println(address);
        }

    }
}
