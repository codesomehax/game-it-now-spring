package pl.lodz.uni.edu.gin.controllers;

import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.lodz.uni.edu.gin.dto.AppUserDto;
import pl.lodz.uni.edu.gin.dto.GameDto;
import pl.lodz.uni.edu.gin.services.AppUserService;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/users")
public class AppUserController {
    private final AppUserService appUserService;

    @GetMapping
    public List<AppUserDto> getAllUsers() {
        return appUserService.getAllUsers();
    }

    @GetMapping("/{id}")
    public ResponseEntity<AppUserDto> getUserById(@PathVariable int id) {
        return ResponseEntity.of(appUserService.getUserById(id));
    }

    @GetMapping(params = "name")
    public ResponseEntity<AppUserDto> getUserByName(@RequestParam String name) {
        return ResponseEntity.of(appUserService.getUserByName(name));
    }

    @GetMapping("/{id}/library")
    public List<GameDto> getLibraryById(@PathVariable int id) {
        return appUserService.getLibraryById(id);
    }
}
