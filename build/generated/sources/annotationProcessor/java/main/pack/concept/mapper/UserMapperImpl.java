package pack.concept.mapper;

import pack.concept.security.dto.UserInDto;
import pack.concept.security.mapper.UserMapper;
import pack.concept.security.model.UsersEntity;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2022-08-31T17:01:27+0500",
    comments = "version: 1.5.2.Final, compiler: IncrementalProcessingEnvironment from gradle-language-java-7.5.jar, environment: Java 17.0.4 (Amazon.com Inc.)"
)
@Component
public class UserMapperImpl extends UserMapper {

    @Override
    public UsersEntity userInDtoToUserEntity(UserInDto userInDto) {
        if ( userInDto == null ) {
            return null;
        }

        UsersEntity usersEntity = new UsersEntity();

        usersEntity.setUsername( userInDto.getUsername() );
        usersEntity.setPassword( userInDto.getPassword() );

        return usersEntity;
    }
}
