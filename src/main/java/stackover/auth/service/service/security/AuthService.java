package stackover.auth.service.service.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import stackover.auth.service.dto.request.LoginRequestDto;
import stackover.auth.service.dto.response.AuthResponseDto;
import stackover.auth.service.dto.token.RefreshTokenResponseDto;
import stackover.auth.service.model.RefreshToken;
import stackover.auth.service.security.CustomUserDetails;
import stackover.auth.service.service.entity.RefreshTokenService;
import stackover.auth.service.util.JwtTokenUtil;

@Service
public class AuthService {
    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private CustomUserDetailsService customUserDetailsService;

    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    @Autowired
    private RefreshTokenService refreshTokenService;

    public AuthResponseDto login(LoginRequestDto loginRequestDto) {
        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(loginRequestDto.email(), loginRequestDto.password()));
        } catch (BadCredentialsException e) {
            throw new BadCredentialsException("Неверный логин или пароль");
        }
        UserDetails userDetails = customUserDetailsService.loadUserByUsername(loginRequestDto.email());
        String token = jwtTokenUtil.generateToken(userDetails);
        Long accountId = ((CustomUserDetails) userDetails).getAccountId();
        String email = userDetails.getUsername();
        Long expiresIn = 3600L;
        RefreshToken refreshToken = refreshTokenService.createRefreshToken(accountId);
        return new AuthResponseDto(accountId, email, token, expiresIn);
    }

    public RefreshTokenResponseDto refreshToken(String refreshToken) {
        RefreshToken token = refreshTokenService.findByToken(refreshToken)
                .orElseThrow(() -> new RuntimeException("Токен не найден"));

        if (refreshTokenService.isTokenExpired(token)) {
            refreshTokenService.deleteByAccountId(token.getAccount().getId());
            throw new RuntimeException("Токен устарел, нужно выполнить повторный вход");
        }
        UserDetails userDetails = customUserDetailsService.loadUserByUsername(token.getAccount().getEmail());
        String newAccessToken = jwtTokenUtil.generateToken(userDetails);
        return new RefreshTokenResponseDto(newAccessToken, token.getToken(), false);
    }
}
