package stackover.auth.service.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import stackover.auth.service.model.RefreshToken;

import java.util.Optional;

public interface RefreshTokenRepository extends JpaRepository<RefreshToken, Long> {
    Optional<RefreshToken> findByToken(String token);
    void deleteByAccountId(Long accountId);
}
