package stackover.auth.service.converters;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import stackover.auth.service.dto.profile.ProfilePostDto;
import stackover.auth.service.dto.request.SignupRequestDto;

@Mapper(componentModel = "spring")
public abstract class SignupToProfileConverter {

    @Mapping(target = "accountId", source = "accountId")
    public abstract ProfilePostDto toProfilePostDto(SignupRequestDto signupRequestDto, Long accountId);

}


