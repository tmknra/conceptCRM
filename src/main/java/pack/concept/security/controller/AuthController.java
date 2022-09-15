package pack.concept.security.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import pack.concept.security.dto.TokenOutDto;
import pack.concept.security.dto.UserInDto;
import pack.concept.security.jwt.AuthTokenFilter;
import pack.concept.security.mapper.UserMapper;
import pack.concept.security.model.UsersEntity;
import pack.concept.security.exception.InvalidTokenRequestException;
import pack.concept.security.payload.request.JwtCheckRequest;
import pack.concept.security.payload.request.LoginRequest;
import pack.concept.security.payload.response.MessageResponse;
import pack.concept.security.repository.UsersRepository;
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
import pack.concept.security.services.UserDetailsImpl;

import javax.management.MalformedObjectNameException;
import javax.management.ObjectName;
import javax.validation.Valid;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

import static java.lang.Boolean.*;
import static org.springframework.http.HttpStatus.UNAUTHORIZED;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/auth")
@Slf4j
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
    public ResponseEntity<?> authenticateUser(@Valid @RequestBody LoginRequest request) {
        Optional<UsersEntity> byUsername = usersRepository.findByUsername(request.getUsername());
        if (byUsername.isEmpty() || !encoder.matches(request.getPassword(), byUsername.get().getPassword())) {
            log.error("Username {} does not exist!", request.getUsername());
            return ResponseEntity.status(UNAUTHORIZED).body(
                    new MessageResponse("Error", "Incorrect login/password for user: " + request.getUsername())
            );
        }
        Authentication authentication =
                authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword()));
        SecurityContextHolder.getContext().setAuthentication(authentication);

        return ResponseEntity.ok().body(new TokenOutDto(jwtUtils.generateJwtToken(authentication)));
    }

    @PostMapping("/logout")
    public ResponseEntity<?> logout(@RequestHeader(HttpHeaders.AUTHORIZATION) HttpHeaders headers) {
        String token = String.join("", Objects.requireNonNull(headers.get("authorization")));
        String userNameFromJwtToken = jwtUtils.getUserNameFromJwtToken(token);
        Optional<UsersEntity> byUsername = usersRepository.findByUsername(userNameFromJwtToken);
        if (byUsername.isEmpty()) {
            log.error("User {} does not exist!", userNameFromJwtToken);
            return ResponseEntity.badRequest().body(new MessageResponse("User " + userNameFromJwtToken + " does not exist!"));
        }
        UsersEntity usersEntity = byUsername.get();
        OnUserLogoutSuccessEvent logoutSuccessEvent = new OnUserLogoutSuccessEvent(usersEntity.getUsername(), token);
        applicationEventPublisher.publishEvent(logoutSuccessEvent);
        return ResponseEntity.ok(TRUE);
    }

    @PostMapping("/check")
    public ResponseEntity<?> checkToken(@RequestHeader(HttpHeaders.AUTHORIZATION) HttpHeaders headers) {
        String token = String.join("", Objects.requireNonNull(headers.get("authorization")));

        if (!jwtUtils.validateJwtToken(token)) {
            return ResponseEntity.badRequest().body(new MessageResponse("Error: Invalid JWT signature"));
        }
        return ResponseEntity.ok().body(TRUE);
    }

    @PostMapping("/signup")
    public ResponseEntity<?> registerUser(@Valid @RequestBody UserInDto userInDto) {
        if (usersRepository.existsByUsername(userInDto.getUsername())) {
            log.error("Username {} is already taken!", userInDto.getUsername());
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
