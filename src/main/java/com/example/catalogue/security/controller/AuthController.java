package com.example.catalogue.security.controller;

import com.example.catalogue.dto.UserInDto;
import com.example.catalogue.mapper.UserMapper;
import com.example.catalogue.model.UsersEntity;
import com.example.catalogue.security.exception.InvalidTokenRequestException;
import com.example.catalogue.security.payload.request.JwtCheckRequest;
import com.example.catalogue.security.payload.request.LoginRequest;
import com.example.catalogue.security.payload.response.MessageResponse;
import com.example.catalogue.repository.UsersRepository;
import com.example.catalogue.security.jwt.JwtUtils;
import com.example.catalogue.security.payload.request.LogOutRequest;
import com.example.catalogue.security.payload.event.OnUserLogoutSuccessEvent;
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
        if (!authentication.isAuthenticated())
            throw new RuntimeException();

        return jwtUtils.generateJwtToken(authentication);
    }

    @PostMapping("/logout")
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
