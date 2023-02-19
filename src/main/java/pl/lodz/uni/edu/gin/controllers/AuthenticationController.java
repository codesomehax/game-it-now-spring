package pl.lodz.uni.edu.gin.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;
import pl.lodz.uni.edu.gin.dto.AppUserDto;
import pl.lodz.uni.edu.gin.entities.AppUser;
import pl.lodz.uni.edu.gin.requests.AuthenticationRequest;
import pl.lodz.uni.edu.gin.requests.UserRegistrationRequest;
import pl.lodz.uni.edu.gin.responses.AuthenticationResponse;
import pl.lodz.uni.edu.gin.services.AuthenticationService;

import java.net.URI;
import java.util.Optional;

@RestController
@RequiredArgsConstructor
@RequestMapping
public class AuthenticationController {
    private final AuthenticationService authenticationService;

    @PostMapping("/authenticate")
    public ResponseEntity<AuthenticationResponse> authenticate(@RequestBody AuthenticationRequest authenticationRequest) {
        return ResponseEntity.ok(authenticationService.authenticate(authenticationRequest));
    }

    @PostMapping("/register")
    public ResponseEntity<AppUserDto> registerNewUser(@RequestBody UserRegistrationRequest userRegistrationRequest) {
        AppUserDto appUserDto = authenticationService.registerUser(userRegistrationRequest);
        return ResponseEntity
                .created(URI.create("/users/" + appUserDto.id()))
                .body(appUserDto);
    }

    public static void checkUserCorrespondsToId(int id) {
        if (Optional
                .ofNullable((AppUser) SecurityContextHolder
                        .getContext()
                        .getAuthentication()
                        .getPrincipal())
                .orElseThrow(() -> {
                    throw new ResponseStatusException(HttpStatus.UNAUTHORIZED);
                }).getId() != id) {
            throw new ResponseStatusException(HttpStatus.FORBIDDEN);
        }
    }
}
