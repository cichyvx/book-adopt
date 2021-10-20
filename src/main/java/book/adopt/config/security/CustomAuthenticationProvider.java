package book.adopt.config.security;

import book.adopt.user.User;
import book.adopt.user.UserService;
import org.springframework.security.authentication.AuthenticationCredentialsNotFoundException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class CustomAuthenticationProvider extends DaoAuthenticationProvider {

    private final UserService userService;

    public CustomAuthenticationProvider(UserService userService, BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.userService = userService;
        this.setPasswordEncoder(bCryptPasswordEncoder);
        this.setUserDetailsService(this.userService);
    }

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        String username = authentication.getName();
        String password = authentication.getCredentials().toString();

        User user = userService.getUserByUsername(username);

        if(!getPasswordEncoder().matches(password, user.getPassword()))
            throw new AuthenticationCredentialsNotFoundException("bad password or username");

        return new UsernamePasswordAuthenticationToken(username, password, user.getAuthorities());
    }
}
