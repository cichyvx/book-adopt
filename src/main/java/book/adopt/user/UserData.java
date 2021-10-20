package book.adopt.user;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
public class UserData implements Serializable {

    private String username;
    private String password;

}
