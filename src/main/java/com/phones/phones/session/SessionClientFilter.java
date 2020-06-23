package com.phones.phones.session;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Service
public class SessionClientFilter extends OncePerRequestFilter {

    @Autowired
    private SessionManager sessionManager;

    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain filterChain)
            throws ServletException, IOException {

        String sessionToken = request.getHeader("Authorization");

        System.out.printf(" -- ClientFilter => SessionToken: " + sessionToken + "\n\n");

        Session session = sessionManager.getSession(sessionToken);

        System.out.print(session.getLoggedUser().getId());

        if (null != session && session.getLoggedUser().hasRoleClient()) {
            System.out.printf("ENTRE SESSION CLIENT");
            filterChain.doFilter(request, response);
        } else {
            System.out.printf("NO TIENE ROL CLIENTE");
            response.setStatus(HttpStatus.FORBIDDEN.value());
        }
    }

}