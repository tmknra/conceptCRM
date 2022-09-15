package pack.concept.security.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import pack.concept.security.dto.UserInDto;
import pack.concept.security.model.UsersEntity;

@Mapper(componentModel = "spring")
public abstract class UserMapper {

    @Mapping(target = "id", ignore = true)
    public abstract UsersEntity userInDtoToUserEntity(UserInDto userInDto);

}
