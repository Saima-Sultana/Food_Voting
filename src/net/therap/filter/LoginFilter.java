package net.therap.filter;


import net.therap.domain.User;
import net.therap.service.LoginService;
import net.therap.service.LoginServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.*;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * @author misbah
 */
public class LoginFilter implements Filter {
    private static final Logger log = LoggerFactory.getLogger(LoginFilter.class);

    public void init(FilterConfig filterConfig) throws ServletException {
    }

    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {

        HttpServletRequest httpRequest = (HttpServletRequest) request;
        HttpServletResponse httpResponse = (HttpServletResponse) response;

        String userName = request.getParameter("Name");
        String password = request.getParameter("Password");


        HttpSession session = ((HttpServletRequest) request).getSession(false);

        if (session == null || session.getAttribute("USER") == null) {
            log.debug("user is not logged in");
            RequestDispatcher rd = request.getRequestDispatcher("/login");
            rd.forward(request, response);
        } else {
            LoginService loginServiceImpl = new LoginServiceImpl();

            User user = loginServiceImpl.checkUser(userName, password);
            if (user != null) {
                session.setAttribute("USER", user);
                chain.doFilter(request, response);
            } else {
                log.debug("Username or password is invalid");
                RequestDispatcher rd = request.getRequestDispatcher("/login");
                rd.forward(request, response);
            }
        }
    }

    public void destroy() {
    }
}
