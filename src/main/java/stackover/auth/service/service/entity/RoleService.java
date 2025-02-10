package stackover.auth.service.service.entity;

import stackover.auth.service.model.Role;
import stackover.auth.service.util.enums.RoleNumEnum;

public interface RoleService extends AbstractService<Role, Long> {
    Role getRoleByName(RoleNumEnum roleName);
}
