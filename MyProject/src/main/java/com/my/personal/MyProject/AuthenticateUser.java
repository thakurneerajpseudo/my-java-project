package com.my.personal.MyProject;
import java.io.IOException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.web.filter.OncePerRequestFilter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;


public class AuthenticateUser extends OncePerRequestFilter {

    @Autowired
    private JwtUtil jwtUtil;
    
    @Autowired
    private TokenBlacklist tokenBlacklist;

    @Autowired
    private UserDetailsService userDetailsService;

//    @Override
//    protected void doFilterInternal(HttpServletRequest request,
//                                    HttpServletResponse response,
//                                    FilterChain filterChain)
//                                    throws ServletException, IOException {
//
//        final String authHeader = request.getHeader("Authorization");
//
//        String email = null;
//        String jwtToken = null;
//
//        if (authHeader != null && authHeader.startsWith("Bearer ")) {
//            jwtToken = authHeader.substring(7);
//
//            try {
//                email = jwtUtil.extractEmail(jwtToken); // Custom method to extract email
//            } catch (Exception e) {
//                System.out.println("Token parsing failed");
//            }
//        }
//
//        // Skip authentication for login/register URLs
//        if (email != null && SecurityContextHolder.getContext().getAuthentication() == null) {
//            UserDetails userDetails = userDetailsService.loadUserByUsername(email);
//
//            if (jwtUtil.validateToken(jwtToken, userDetails)) {
//                UsernamePasswordAuthenticationToken authToken =
//                        new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
//
//                authToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
//                SecurityContextHolder.getContext().setAuthentication(authToken);
//            }
//        }
//
//        // Very important line to continue the chain
//        filterChain.doFilter(request, response);
//    }
    
  
       
       
        @Override
        protected void doFilterInternal(HttpServletRequest request,
                                        HttpServletResponse response,
                                        FilterChain filterChain)
                                        throws ServletException, IOException {

            final String authHeader = request.getHeader("Authorization");

            // 1. If there’s a Bearer token, extract it
            if (authHeader != null && authHeader.startsWith("Bearer ")) {
                String jwtToken = authHeader.substring(7);

                // 2. Reject immediately if it’s blacklisted
                if (tokenBlacklist.isRevoked(jwtToken)) {
                    response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
                    response.setContentType("application/json");
                    response.getWriter().write("{\"message\":\"Token has been revoked\"}");
                    return;  // do NOT continue the chain
                }

                // 3. Parse email from token
                String email = null;
                try {
                    email = jwtUtil.extractEmail(jwtToken);
                } catch (Exception e) {
                    // invalid token, but let validation logic handle it below
                }

                // 4. If valid email and not yet authenticated, validate and set auth
                if (email != null && SecurityContextHolder.getContext().getAuthentication() == null) {
                    UserDetails userDetails = userDetailsService.loadUserByUsername(email);
                    if (jwtUtil.validateToken(jwtToken, userDetails)) {
                        UsernamePasswordAuthenticationToken authToken =
                            new UsernamePasswordAuthenticationToken(
                                userDetails, null, userDetails.getAuthorities()
                            );
                        authToken.setDetails(new WebAuthenticationDetailsSource()
                                .buildDetails(request));
                        SecurityContextHolder.getContext().setAuthentication(authToken);
                    }
                }
            }

            // 5. Continue the chain if we haven’t already returned
            filterChain.doFilter(request, response);
        }

   
    @Override
    protected boolean shouldNotFilter(HttpServletRequest request) throws ServletException {
        // Skip filtering for authentication endpoints
        String path = request.getRequestURI();
        return path.startsWith("/api/auth");
    }
}

	
	 
	
	

