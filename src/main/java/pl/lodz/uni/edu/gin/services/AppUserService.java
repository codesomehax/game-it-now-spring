package pl.lodz.uni.edu.gin.services;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import pl.lodz.uni.edu.gin.dto.AppUserDto;
import pl.lodz.uni.edu.gin.mappers.AppUserMapper;
import pl.lodz.uni.edu.gin.repositories.AppUserRepository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class AppUserService {
    private final AppUserRepository appUserRepository;
    private final AppUserMapper appUserMapper;

    public List<AppUserDto> getAllUsers() {
        return appUserRepository.findAll().stream()
                .map(appUserMapper::appUserToDto)
                .collect(Collectors.toList());
    }

    public Optional<AppUserDto> getUserById(int id) {
        return appUserRepository.findById(id)
                .map(appUserMapper::appUserToDto);
    }

    public Optional<AppUserDto> getUserByName(String name) {
        return appUserRepository.findByUsername(name)
                .map(appUserMapper::appUserToDto);
    }
}
