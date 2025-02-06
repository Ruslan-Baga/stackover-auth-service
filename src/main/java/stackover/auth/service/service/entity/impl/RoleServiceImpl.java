package stackover.auth.service.service.entity.impl;

import org.springframework.stereotype.Service;
import stackover.auth.service.model.Role;
import stackover.auth.service.repository.RoleRepository;
import stackover.auth.service.service.entity.RoleService;
import stackover.auth.service.util.enums.RoleNumEnum;

@Service
public class RoleServiceImpl extends AbstractServiceImpl<Role, Long> implements RoleService {
    private final RoleRepository roleRepository;

    public RoleServiceImpl(RoleRepository roleRepository) {
        super(roleRepository);
        this.roleRepository = roleRepository;
    }

    @Override
    public Role getRoleByName(RoleNumEnum roleName) {
        return roleRepository.getRoleByName(roleName);
    }
}
