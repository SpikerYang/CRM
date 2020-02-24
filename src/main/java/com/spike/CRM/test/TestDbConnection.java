package com.spike.CRM.test;

import javax.servlet.ServletException;
import javax.servlet.annotation.*;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import java.io.PrintWriter;
import java.sql.*;

@WebServlet("/TestDbConnection")
public class TestDbConnection extends HttpServlet {
    private static final long serialVersionUID = 1L;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // setup connection variables
        String user = "root";
        String pass = "Yqr199733dlj*";

        String jdbcUrl = "jdbc:mysql://localhost:3306/web_customer_tracker?useSSL=false&amp&serverTimezone=UTC";
        String driver = "com.mysql.cj.jdbc.Driver";

        // get connection to database
        try {
            PrintWriter out = resp.getWriter();
            out.println("Connecting to database" + jdbcUrl);

            Class.forName(driver);
            Connection myConn = DriverManager.getConnection(jdbcUrl, user, pass);

            out.println("Connection succedd!");
            myConn.close();
        } catch (Exception e) {
            e.printStackTrace();
            throw new ServletException(e);
        }
    }
}
