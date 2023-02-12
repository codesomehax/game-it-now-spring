package pl.lodz.uni.edu.gin.services;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import pl.lodz.uni.edu.gin.dto.AppUserDto;
import pl.lodz.uni.edu.gin.dto.GameDto;
import pl.lodz.uni.edu.gin.entities.AppUser;
import pl.lodz.uni.edu.gin.mappers.AppUserMapper;
import pl.lodz.uni.edu.gin.mappers.GameMapper;
import pl.lodz.uni.edu.gin.repositories.AppUserRepository;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class AppUserService implements UserDetailsService {
    private final AppUserRepository appUserRepository;
    private final AppUserMapper appUserMapper;
    private final GameMapper gameMapper;

    public List<AppUserDto> getAllUsers() {
        return appUserRepository
                .findAll()
                .stream()
                .map(appUserMapper::appUserToDto)
                .toList();
    }

    public Optional<AppUserDto> getUserById(int id) {
        return appUserRepository
                .findById(id)
                .map(appUserMapper::appUserToDto);
    }

    public Optional<AppUserDto> getUserByName(String name) {
        return appUserRepository
                .findByUsername(name)
                .map(appUserMapper::appUserToDto);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return appUserRepository
                .findByUsername(username)
                .orElse(null);
    }

    public List<GameDto> getLibraryById(int id) {
        return appUserRepository
                .findById(id)
                .map(AppUser::getGames)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND,
                        "A user with given id does not exist"
                )).stream()
                .map(gameMapper::gameToDto)
                .toList();
    }
}
