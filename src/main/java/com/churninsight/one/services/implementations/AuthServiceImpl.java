package com.churninsight.one.services.implementations;

import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import com.churninsight.one.models.dto.request.AuthResponse;
import com.churninsight.one.services.AuthService;

@Service
public class AuthServiceImpl implements AuthService {
    
       @Override
        public AuthResponse loginUsuario(LoginRequest loginRequest) {
                String userEmail = loginRequest.email();
                String password = loginRequest.password();

                Authentication authentication = this.authenticate(userEmail, password);
                SecurityContextHolder.getContext().setAuthentication(authentication);

                String accessToken = this.jwtUtils.createToken(authentication);

                AuthResponse authReponse = new AuthResponse(userEmail, "User loged successfuly", accessToken, true);
                return authReponse;
        }

        public Authentication authenticate(String userEmail, String password) {
                UserDetails userDetails = this.loadUserByUsername(userEmail);
                System.out.println(userDetails);

                if (userDetails == null) {
                        throw new BadCredentialsException("Invalid email or password");
                }

                if (!this.passwordEncoder.matches(password, userDetails.getPassword())) {
                        throw new BadCredentialsException("Invalid password");
                }

                return new UsernamePasswordAuthenticationToken(userEmail, userDetails.getPassword(),
                                userDetails.getAuthorities());
        }
}
