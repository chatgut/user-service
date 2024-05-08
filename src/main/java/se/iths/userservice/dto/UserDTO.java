package se.iths.userservice.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class userDTO {
    private long id;
    private String username;
    private String firstName;
    private String lastName;
    private String imageUrl;
}
