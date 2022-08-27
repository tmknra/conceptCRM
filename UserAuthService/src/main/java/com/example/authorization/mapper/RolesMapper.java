package com.example.authorization.mapper;

import com.example.authorization.dto.RolesInDto;
import com.example.authorization.model.RolesEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public abstract class RolesMapper {

    @Mapping(target = "id", ignore = true)
    public abstract RolesEntity rolesInDtoToRolesEntity(RolesInDto roles);

}
