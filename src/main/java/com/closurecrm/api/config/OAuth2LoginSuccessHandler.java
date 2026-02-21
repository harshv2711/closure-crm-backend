package com.closurecrm.api.config;

import java.io.IOException;
import java.util.Set;

import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import com.closurecrm.api.auth.entity.User;
import com.closurecrm.api.auth.repository.RoleRepository;
import com.closurecrm.api.auth.repository.UserRepository;
import com.closurecrm.api.auth.service.CustomUserDetails;
import com.closurecrm.api.auth.service.JwtService;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class OAuth2LoginSuccessHandler extends SimpleUrlAuthenticationSuccessHandler {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final JwtService jwtService;

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, 
                                        Authentication authentication) throws IOException, ServletException {
        
        OAuth2User oAuth2User = (OAuth2User) authentication.getPrincipal();
        String email = oAuth2User.getAttribute("email");
        String firstName = oAuth2User.getAttribute("given_name");
        String lastName = oAuth2User.getAttribute("family_name");

        // Find existing user or create a new one
        User user = userRepository.findByEmail(email)
                .orElseGet(() -> {
                    User newUser = User.builder()
                            .email(email)
                            .firstName(firstName)
                            .lastName(lastName)
                            .enabled(true)
                            .roles(Set.of(roleRepository.findByName("ROLE_SALES_REP").orElseThrow()))
                            .build();
                    return userRepository.save(newUser);
                });

        var userDetails = new CustomUserDetails(user);
        String token = jwtService.generateToken(userDetails);

        // Redirect to a frontend-friendly URL with the token
        // In a real app, you might use a cookie or a specific redirect URI
        getRedirectStrategy().sendRedirect(request, response, "http://localhost:8080/api/auth/oauth-success?token=" + token);
    }
}