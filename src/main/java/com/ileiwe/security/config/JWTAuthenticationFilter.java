package com.ileiwe.security.config;

import com.auth0.jwt.JWT;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.ileiwe.data.model.LearningParty;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Date;

import static com.auth0.jwt.algorithms.Algorithm.HMAC512;
import static com.ileiwe.security.config.SecurityConstants.*;
import static org.springframework.security.config.Elements.JWT;

@Slf4j
public class JWTAuthenticationFilter extends UsernamePasswordAuthenticationFilter {

    private AuthenticationManager authManager;

    @Override
    public void setAuthenticationManager(AuthenticationManager authenticationManager) {
        super.setAuthenticationManager(authenticationManager);
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response)
            throws AuthenticationException {
        try{
            LearningParty user = new ObjectMapper()
                    .readValue(request.getInputStream(), LearningParty.class);

            UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(
                    user.getEmail(), user.getPassword());

            return authManager.authenticate(authToken);

        }catch(IOException e){
            log.info("Exception occurred -> {}", e.getMessage());
            throw new RuntimeException(e);
        }

    }

    @Override
    protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain, Authentication authResult) throws IOException, ServletException {
       String token = com.auth0.jwt.JWT.create()
               .withSubject(((User) authResult.getPrincipal()).getUsername())
                       .withExpiresAt(new Date(System.currentTimeMillis()+EXPIRATION_TIME))
                       .sign(HMAC512(SECRET.getBytes()));

       response.addHeader(HEADER_STRING, TOKEN_PREFIX+token);
    }
}
