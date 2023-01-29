package pl.lodz.uni.edu.gin.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.lodz.uni.edu.gin.dto.AppUserDto;
import pl.lodz.uni.edu.gin.requests.AuthenticationRequest;
import pl.lodz.uni.edu.gin.requests.UserRegistrationRequest;
import pl.lodz.uni.edu.gin.responses.AuthenticationResponse;
import pl.lodz.uni.edu.gin.services.AuthenticationService;

import java.net.URI;

@RestController
@RequiredArgsConstructor
@RequestMapping
public class AuthenticationController {
    private final AuthenticationService authenticationService;

    @GetMapping("/authenticate")
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
}
