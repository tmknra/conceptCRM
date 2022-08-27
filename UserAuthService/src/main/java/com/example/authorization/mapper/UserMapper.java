package com.example.authorization.mapper;

import com.example.authorization.dto.RolesInDto;
import com.example.authorization.dto.UserInDto;
import com.example.authorization.model.RolesEntity;
import com.example.authorization.model.UsersEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.beans.factory.annotation.Autowired;

@Mapper(componentModel = "spring")
public abstract class UserMapper {
    @Autowired
    private RolesMapper rolesMapper;

    @Mapping(target = "id", ignore = true)
    public abstract UsersEntity userInDtoToUserEntity(UserInDto userInDto);

    RolesEntity rolesInDtoToRolesEntity(RolesInDto roles){
        return rolesMapper.rolesInDtoToRolesEntity(roles);
    }

}
