package ru.innopolis.stc9.controller.filter;

import ru.innopolis.stc9.controller.constants.Roles;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * Filter for users. Access is granted only registered users
 */
@WebFilter(urlPatterns = {"/group", "/groupStructure", "/lessons", "/person", "/role", "/speciality", "/subject", "teacherSubject", "/user"})
public class AuthentificationFilter implements Filter {
    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpSession httpSession = ((HttpServletRequest) request).getSession();
        boolean access = false;
        Integer roleId = (Integer) httpSession.getAttribute("role");
        Object login = httpSession.getAttribute("login");
        if (roleId != null) {
            switch (roleId) {
                case Roles.ADMIN:
                case Roles.FACULTY:
                case Roles.TEACHER:
                case Roles.STUDENT:
                    access = true;
                    break;
                default:
                    break;
            }
        }
        if (login != null && access) {
            chain.doFilter(request, response);
        } else {
            HttpServletResponse httpServletResponse = (HttpServletResponse) response;
            HttpServletRequest httpServletRequest = (HttpServletRequest) request;
            httpServletResponse.sendRedirect(httpServletRequest.getContextPath() + "/login?errorMsg=noAccess");
        }
    }

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void destroy() {

    }
}
