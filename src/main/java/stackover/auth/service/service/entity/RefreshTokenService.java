package stackover.auth.service.service.entity;

import stackover.auth.service.model.RefreshToken;

import java.util.Optional;

public interface RefreshTokenService extends AbstractService<RefreshToken, Long>{
    RefreshToken createRefreshToken(Long accountId);
    boolean isTokenExpired(RefreshToken token);
    void deleteByAccountId(Long accountId);
    Optional<RefreshToken> findByToken(String token);
}
