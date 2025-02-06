package stackover.auth.service.service.entity.impl;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import stackover.auth.service.model.RefreshToken;
import stackover.auth.service.repository.AccountRepository;
import stackover.auth.service.repository.RefreshTokenRepository;
import stackover.auth.service.service.entity.RefreshTokenService;

import java.time.Instant;
import java.util.Optional;
import java.util.UUID;

@Service
public class RefreshTokenServiceImpl extends AbstractServiceImpl<RefreshToken, Long> implements RefreshTokenService {
    private final RefreshTokenRepository refreshTokenRepository;
    private final AccountRepository accountRepository;

    @Value("${jwt.expiration:86400000}")
    private long refreshTokenTime;

    public RefreshTokenServiceImpl(RefreshTokenRepository refreshTokenRepository, AccountRepository accountRepository) {
        super(refreshTokenRepository);
        this.refreshTokenRepository = refreshTokenRepository;
        this.accountRepository = accountRepository;
    }

    public RefreshToken createRefreshToken(Long accountId) {
        RefreshToken refreshToken = new RefreshToken();
        refreshToken.setAccount(accountRepository.findById(accountId)
                .orElseThrow(() -> new RuntimeException("Аккаунт не найден")));
        refreshToken.setToken(UUID.randomUUID().toString());
        refreshToken.setExpiryDate(Instant.now().plusMillis(refreshTokenTime));
        return super.save(refreshToken);
    }
    @Transactional
    public Optional<RefreshToken> findByToken(String token) {
        return refreshTokenRepository.findByToken(token);
    }
    public boolean isTokenExpired(RefreshToken token) {
        return token.getExpiryDate().isBefore(Instant.now());
    }
    @Transactional
    public void deleteByAccountId(Long accountId) {
        refreshTokenRepository.deleteByAccountId(accountId);
    }
}
