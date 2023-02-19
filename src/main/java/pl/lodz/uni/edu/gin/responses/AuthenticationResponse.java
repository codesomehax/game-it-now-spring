package pl.lodz.uni.edu.gin.responses;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import pl.lodz.uni.edu.gin.dto.AppUserDto;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AuthenticationResponse {
    private String token;
    private AppUserDto appUser;
}
