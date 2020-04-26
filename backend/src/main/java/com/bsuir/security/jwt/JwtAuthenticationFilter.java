//package com.bsuir.security.jwt;
//
//import com.auth0.jwt.JWT;
//import com.auth0.jwt.algorithms.Algorithm;
//import com.bsuir.model.User;
//import com.fasterxml.jackson.core.JsonParseException;
//import com.fasterxml.jackson.databind.JsonMappingException;
//import com.fasterxml.jackson.databind.ObjectMapper;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.authentication.AuthenticationManager;
//import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
//import org.springframework.security.core.Authentication;
//import org.springframework.security.core.AuthenticationException;
//import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
//
//import javax.servlet.FilterChain;
//import javax.servlet.ServletException;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//import java.io.IOException;
//import java.util.ArrayList;
//import java.util.Date;
//
//import static com.auth0.jwt.algorithms.Algorithm.HMAC512;
//
//public class JwtAuthenticationFilter extends UsernamePasswordAuthenticationFilter {
//
//    @Autowired
//    private AuthenticationManager authenticationManager;
//
//    public JwtAuthenticationFilter(AuthenticationManager authenticationManager){
//        this.authenticationManager = authenticationManager;
//    }
//
//    @Override
//    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {
//        try{
//            User creds = new ObjectMapper()
//                    .readValue(request.getInputStream(),User.class);
//            System.out.println("auth " + creds.getLogin() + "  " + creds.getPassword());
//
//            return authenticationManager.authenticate(
//                    new UsernamePasswordAuthenticationToken(
//                            creds.getLogin(),
//                            creds.getPassword(),
//                            new ArrayList<>()
//                    )
//            );
//        } catch (JsonParseException e) {
//            e.printStackTrace();
//        } catch (JsonMappingException e) {
//            e.printStackTrace();
//        } catch (IOException e) {
//            throw new RuntimeException(e);
//        }
//        return  null;
//    }
//
//    @Override
//    protected void successfulAuthentication(HttpServletRequest request,
//                                            HttpServletResponse response,
//                                            FilterChain chain,
//                                            Authentication authResult) throws IOException, ServletException {
//        System.out.println("create token");
//        String token = JWT.create()
//                .withSubject(((org.springframework.security.core.userdetails.User)authResult.getPrincipal()).getUsername())
//                .withExpiresAt(new Date(System.currentTimeMillis() + JwtSecurityConstants.EXPIRATION_TIME))
//                .sign(HMAC512(JwtSecurityConstants.SECRET.getBytes()));
//        response.addHeader(JwtSecurityConstants.HEADER_STRING, JwtSecurityConstants.TOKEN_PREFIX + token);
//    }
//}
