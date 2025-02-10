package stackover.auth.service.converters;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import stackover.auth.service.dto.request.SignupRequestDto;
import stackover.auth.service.model.Account;
import stackover.auth.service.model.Role;
import stackover.auth.service.util.enums.RoleNumEnum;

@Mapper(componentModel = "spring")
public abstract class SignupDtoConverter {

    @Mapping(source = "roleName", target = "role", qualifiedByName = "roleNameToRole")
    public abstract Account signupDtoToAccount(SignupRequestDto signupRequestDto);

    @Named("roleNameToRole")
    public Role roleNameToRole(String roleName) {
        Role role = new Role();
        role.setName(RoleNumEnum.valueOf(roleName));
        return role;
    }
}
