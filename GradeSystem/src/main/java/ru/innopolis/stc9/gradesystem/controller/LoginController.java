package ru.innopolis.stc9.gradesystem.controller;

import org.apache.log4j.Logger;
import ru.innopolis.stc9.gradesystem.service.UserService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class LoginController extends HttpServlet {
    private static Logger logger = Logger.getLogger(MarksServlet.class);
    private UserService userService = new UserService();

    @Override
    protected void doGet(HttpServletRequest req
            , HttpServletResponse resp)
            throws ServletException, IOException {

        String action = req.getParameter("action");

        if ("logout".equals("logout")) {
            req.getSession().invalidate();
        }

        logger.info("Received request for login page");
        req.setCharacterEncoding("UTF-8");
        resp.setCharacterEncoding("UTF-8");
        req.getRequestDispatcher("/login.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req
            , HttpServletResponse resp)
            throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        resp.setCharacterEncoding("UTF-8");
        String login = req.getParameter("userName");
        String password = req.getParameter("userPassword");

        if (userService.checkAuth(login, password)) {
            Integer role = userService.getRole(login);
            req.getSession().setAttribute("login", login);
            req.getSession().setAttribute("role", role);

            if (role == 1 || role == 2) {
                resp.sendRedirect(req.getContextPath() + "/admin/dashboard");
            } else if (role == 3) {
                resp.sendRedirect(req.getContextPath() + "/user/dashboard");
            }
            /*
            else {
                resp.sendRedirect(req.getContextPath() + "/inner/dashboard");
            }
            */
        } else {
            resp.sendRedirect(req.getContextPath() + "/login?errorMsg=authErr");
        }
    }
}
