package net.therap.controller;

import net.therap.domain.Food;
import net.therap.domain.User;
import net.therap.service.FoodService;
import net.therap.service.FoodServiceImpl;
import net.therap.service.LoginService;
import net.therap.service.LoginServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;


public class UserController extends HttpServlet {
    private static final Logger log = LoggerFactory.getLogger(UserController.class);

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession(false);

        if (session == null || session.getAttribute("USER") == null) {
            log.debug("user is not logged in");
            RequestDispatcher rd = request.getRequestDispatcher("/login");
            rd.forward(request, response);
        } else {

            User user = (User) session.getAttribute("USER");

            FoodService foodService = new FoodServiceImpl();
            List<String> foodNames = foodService.getFoodNames();
            request.setAttribute("FoodNames", foodNames);

            if (user.getType().equals("U")) {
                RequestDispatcher rd = request.getRequestDispatcher("/Vote.jsp");
                rd.forward(request, response);

            } else if (user.getType().equals("A")) {
                List<Integer> votes = foodService.getVoteCount();
                request.setAttribute("Votes", votes);

                RequestDispatcher rd = request.getRequestDispatcher("/Result.jsp");
                rd.forward(request, response);
            }


        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
