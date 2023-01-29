package pl.lodz.uni.edu.gin.services;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import pl.lodz.uni.edu.gin.dto.AppUserDto;
import pl.lodz.uni.edu.gin.entities.AppUser;
import pl.lodz.uni.edu.gin.mappers.AppUserMapper;
import pl.lodz.uni.edu.gin.repositories.AppUserRepository;
import pl.lodz.uni.edu.gin.requests.AuthenticationRequest;
import pl.lodz.uni.edu.gin.requests.UserRegistrationRequest;
import pl.lodz.uni.edu.gin.responses.AuthenticationResponse;

import java.util.Objects;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class AuthenticationService {
    private final AuthenticationManager authenticationManager;
    private final AppUserRepository appUserRepository;
    private final JwtService jwtService;
    private final AppUserMapper appUserMapper;
    private final PasswordEncoder passwordEncoder;

    public AuthenticationResponse authenticate(AuthenticationRequest request) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getUsername(),
                        request.getPassword()
                )
        );
        AppUser user = appUserRepository.findByUsername(request.getUsername())
                .orElseThrow();
        String jwtToken = jwtService.generateToken(user);
        return new AuthenticationResponse(jwtToken);
    }

    public AppUserDto registerUser(UserRegistrationRequest userRegistrationRequest) {
        if (!Objects.equals(userRegistrationRequest.password(), userRegistrationRequest.password2())) {
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST,
                    "Given passwords do not match"
            );
        }

        Optional<AppUser> appUser = appUserRepository.findByUsername(userRegistrationRequest.username());

        if (appUser.isPresent()) {
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST,
                    "This username is already taken"
            );
        } else {
            return appUserMapper.appUserToDto(appUserRepository.save(
                    AppUser.builder()
                            .username(userRegistrationRequest.username())
                            .password(passwordEncoder.encode(userRegistrationRequest.password()))
                            .email(userRegistrationRequest.email())
                            .role(AppUser.Role.USER)
                            .build()));
        }
    }
}
