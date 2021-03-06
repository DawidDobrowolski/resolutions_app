package pl.resolutions.filter;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebFilter(filterName = "AuthenticationFilter",urlPatterns = {"/resolution/*","/activity/*","/ranking/*","/report/*", "/login/edit/*","/login/changePassword/*", "/user/*", "/resolutionType/*"})
public class AuthenticationFilter implements Filter {
    public void destroy() {
    }

    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws ServletException, IOException {

        if(!(req instanceof HttpServletRequest)){
            chain.doFilter(req,resp);
            return;
        }

        HttpServletRequest request = (HttpServletRequest) req;
        HttpSession session = request.getSession();
        boolean loggedIn = session.getAttribute("email") != null;

        if(loggedIn){
            chain.doFilter(req,resp);
            return;
        }


        HttpServletResponse response = (HttpServletResponse) resp;
        response.sendError(HttpServletResponse.SC_UNAUTHORIZED,"You are not authorized to see this page");
        //response.sendRedirect("/");

    }

    public void init(FilterConfig config) throws ServletException {



    }

}
