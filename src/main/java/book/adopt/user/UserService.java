package book.adopt.user;

import com.sun.istack.NotNull;
import lombok.AllArgsConstructor;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
@AllArgsConstructor
public class UserService implements UserDetailsService{

    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepository.getUserByUsername(username).orElseThrow();
    }

    public User getUserByUsername(String username){
        return userRepository.getUserByUsername(username).orElseThrow();
    }

    /**
     * creating default new user with roles USER
     * @param username
     * @param password
     * @throws Exception when user already exist
     */
    public void createUser(String username, String password) throws Exception{
        User user = new User();

        if (userRepository.getUserByUsername(username).isPresent())
            throw new Exception("username already exist");

        if(password.isEmpty() || username.isEmpty())
            throw new Exception("illegal password characters");

        user.setUsername(username);
        user.setPassword(new BCryptPasswordEncoder().encode(password));
        user.setRole(Role.USER.name());
        userRepository.save(user);
    }

    public void deleteUser(String name) {
        userRepository.deleteByUsername(name);
    }

    /**
     * updating user data
     * if you want to not changing one of data just insert param exactly as has been before
     * @param name user to update data
     * @param userData data to updated
     * @throws UsernameNotFoundException if user don't exist
     * @throws BadCredentialsException when user with this username already exist
     */
    public void updateUser(String name, UserData userData) {
        User user = getUserByUsername(name);

        var testUser = userRepository.getUserByUsername(userData.getUsername());
        if(testUser.isPresent()){
            if(!testUser.get().equals(user)){
                throw new BadCredentialsException("username already exist");
            }
        }
        user.setUsername(userData.getUsername());
        user.setPassword(new BCryptPasswordEncoder().encode(userData.getPassword()));
        userRepository.updateUser(user.getId(), user.getUsername(), user.getPassword());
    }
}
