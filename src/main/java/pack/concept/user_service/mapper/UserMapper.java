package pack.concept.user_service.mapper;

import pack.concept.user_service.dto.UserInDto;
import pack.concept.user_service.model.UsersEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public abstract class UserMapper {

    @Mapping(target = "id", ignore = true)
    public abstract UsersEntity userInDtoToUserEntity(UserInDto userInDto);

}
