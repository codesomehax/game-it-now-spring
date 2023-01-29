package pl.lodz.uni.edu.gin.requests;

public record UserRegistrationRequest(
        String username,
        String password,
        String password2,
        String email
) {
}
