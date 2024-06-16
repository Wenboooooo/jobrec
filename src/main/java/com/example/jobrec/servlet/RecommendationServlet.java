package com.example.jobrec.servlet;

import com.example.jobrec.Entity.Item;
import com.example.jobrec.Entity.ResultResponse;
import com.example.jobrec.recommendation.Recommendation;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.util.List;

@WebServlet(name = "RecommendationServlet", urlPatterns = {"/recommendation"})
public class RecommendationServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        ObjectMapper mapper = new ObjectMapper();
        HttpSession session = request.getSession(false);
        if (session == null) {
            response.setStatus(403);
            mapper.writeValue(response.getWriter(), new ResultResponse("Session Invalid"));
            return;
        }
        //get userID
        String userId = request.getParameter("user_id");
        //get location
        String locaiton = request.getParameter("locaiton");
        Recommendation recommendation = new Recommendation();
        List<Item> items = recommendation.recommendItems(userId, locaiton);
        response.setContentType("application/json");
        mapper.writeValue(response.getWriter(), items);
    }

}
