package book.adopt.user;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

@RestController
@RequestMapping("/user")
@AllArgsConstructor
public class UserController {

    private final UserService userService;


    @GetMapping
    public User user(Principal principal){
        return userService.getUserByUsername(principal.getName());
    }

    /**
     * creating new User
     * @param userData username ond password
     * @throws Exception if user with that username already exist
     */
    @PutMapping
    public void register(@RequestBody UserData userData) throws Exception{
        userService.createUser(userData.getUsername(), userData.getPassword());
    }

    /**
     * deleting currently logged account
     * @param principal user credentials
     */
    @DeleteMapping
    public void deleteAccount(Principal principal){
        userService.deleteUser(principal.getName());
    }

    /**
     * updating currently logged account
     * @param principal user credentials
     * @param userData data to change
     */
    @PatchMapping
    public void updateAccount(Principal principal, @RequestBody UserData userData){
        userService.updateUser(principal.getName(), userData);
    }



}
