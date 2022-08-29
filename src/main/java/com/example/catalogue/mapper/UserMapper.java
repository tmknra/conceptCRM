package com.example.catalogue.mapper;

import com.example.catalogue.dto.UserInDto;
import com.example.catalogue.model.UsersEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public abstract class UserMapper {

    @Mapping(target = "id", ignore = true)
    public abstract UsersEntity userInDtoToUserEntity(UserInDto userInDto);

}
