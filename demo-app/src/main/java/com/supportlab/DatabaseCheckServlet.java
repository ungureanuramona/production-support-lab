package com.supportlab;

import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

@WebServlet("/db-check")
public class DatabaseCheckServlet extends HttpServlet {

    private static final Logger LOGGER =
        Logger.getLogger(DatabaseCheckServlet.class.getName());

    @Override
    protected void doGet(HttpServletRequest request,
                         HttpServletResponse response) throws IOException {

        String url = System.getenv("SUPPORT_DB_URL");
        String user = System.getenv("SUPPORT_DB_USER");
        String password = System.getenv("SUPPORT_DB_PASSWORD");

        if (url == null || user == null || password == null) {
            response.sendError(503, "Database configuration missing");
            return;
        }

        try {
            Class.forName("org.postgresql.Driver");
        } catch (ClassNotFoundException exception) {
            LOGGER.log(Level.SEVERE, "PostgreSQL JDBC driver not found", exception);
            response.sendError(503, "Database driver unavailable");
            return;
        }

        try (Connection connection =
                 DriverManager.getConnection(url, user, password);
             Statement statement = connection.createStatement();
             ResultSet result = statement.executeQuery("SELECT 1")) {

            result.next();
            response.setContentType("text/plain");
            response.getWriter().println(
                "Database connection OK: " + result.getInt(1)
            );

        } catch (SQLException exception) {
            LOGGER.log(Level.SEVERE, "Database connection failed", exception);
            response.sendError(503, "Database unavailable");
        }
    }
}
