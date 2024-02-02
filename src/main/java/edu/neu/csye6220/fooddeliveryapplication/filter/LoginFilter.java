package edu.neu.csye6220.fooddeliveryapplication.filter;

import edu.neu.csye6220.fooddeliveryapplication.dao.model.User;
import org.springframework.stereotype.Component;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Enumeration;

@Component
public class LoginFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {
        HttpServletRequest req=(HttpServletRequest)request;
        HttpServletResponse res=(HttpServletResponse)response;
        HttpSession session = req.getSession();

        if((session.getAttribute("user")==null||!(session.getAttribute("user") instanceof User))&&!req.getRequestURI().contains("/project/user"))
            res.sendRedirect("/project/user/login.htm");
        else
            chain.doFilter(request,response);
    }



    @Override
    public void destroy() {
    }

}