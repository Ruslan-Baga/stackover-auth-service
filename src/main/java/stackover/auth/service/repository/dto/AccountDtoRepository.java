package stackover.auth.service.repository.dto;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import stackover.auth.service.model.Account;
import stackover.auth.service.dto.account.AccountResponseDto;
import stackover.auth.service.util.enums.RoleNumEnum;

@Repository
public interface AccountDtoRepository extends JpaRepository<Account, Long> {
    @Query("""
            SELECT new stackover.auth.service.dto.account.AccountResponseDto(
            a.id,
            a.email,
            a.role.name,
            a.enabled
            )
            FROM Account a
            WHERE a.id = :id
            """)

    AccountResponseDto getAccountById(@Param("id") Long id);
    @Query("""
            SELECT COUNT(a) > 0
            FROM Account a
            where a.id = :id
            AND a.role.name = :role
            """)
    boolean existsByIdAndRole(@Param("id") Long id, @Param("role") RoleNumEnum role);
}
