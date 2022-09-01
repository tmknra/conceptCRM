package pack.concept.security.controller;

import pack.concept.user_service.dto.UserInDto;
import pack.concept.user_service.mapper.UserMapper;
import pack.concept.user_service.model.UsersEntity;
import pack.concept.security.exception.InvalidTokenRequestException;
import pack.concept.security.payload.request.JwtCheckRequest;
import pack.concept.security.payload.request.LoginRequest;
import pack.concept.security.payload.response.MessageResponse;
import pack.concept.user_service.repository.UsersRepository;
import pack.concept.security.jwt.JwtUtils;
import pack.concept.security.payload.request.LogOutRequest;
import pack.concept.security.payload.event.OnUserLogoutSuccessEvent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Optional;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/auth")
public class AuthController {
    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    UsersRepository usersRepository;

    @Autowired
    PasswordEncoder encoder;

    @Autowired
    JwtUtils jwtUtils;

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private ApplicationEventPublisher applicationEventPublisher;

    @PostMapping("/login")
    public String authenticateUser(@Valid @RequestBody LoginRequest request) {
        Authentication authentication =
                authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword()));
        SecurityContextHolder.getContext().setAuthentication(authentication);
        // TODO: return error. prefer json {"error":"error_text"}
        if (!authentication.isAuthenticated()){
            throw new RuntimeException();
        }
        // TODO: return status code
        // TODO: return type JSON
        return jwtUtils.generateJwtToken(authentication);
    }

    @PostMapping("/logout")
    // TODO: JWT takes from headers. Learn how to validate token from each request
    public Boolean logout(@Valid @RequestBody LogOutRequest logOutRequest) {
        Optional<UsersEntity> byName = usersRepository.findByUsername(logOutRequest.getUsername());
        if (byName.isEmpty()) {
            throw new RuntimeException("User does not exist!");
        }
        UsersEntity usersEntity = byName.get();
        OnUserLogoutSuccessEvent logoutSuccessEvent = new OnUserLogoutSuccessEvent(usersEntity.getUsername(), logOutRequest.getToken(), logOutRequest);
        applicationEventPublisher.publishEvent(logoutSuccessEvent);

        return true;
    }

    @PostMapping("/check")
    // TODO: JWT takes from headers
    public Boolean checkToken(@Valid @RequestBody JwtCheckRequest jwtCheckRequest) throws InvalidTokenRequestException {
        if (!jwtUtils.validateJwtToken(jwtCheckRequest.getToken()))
            throw new InvalidTokenRequestException("JWT", jwtCheckRequest.getToken(), "Invalid token!");
        return true;
    }

    @PostMapping("/signup")
    public ResponseEntity<?> registerUser(@Valid @RequestBody UserInDto userInDto) {
        if (usersRepository.existsByUsername(userInDto.getUsername())) {
            return ResponseEntity
                    .badRequest()
                    .body(new MessageResponse("Error: Username is already taken!"));
        }
        UsersEntity user = userMapper.userInDtoToUserEntity(userInDto);
        user.setPassword(encoder.encode(user.getPassword()));
        usersRepository.save(user);
        return ResponseEntity.ok(new MessageResponse("User registered successfully!"));
    }
}
