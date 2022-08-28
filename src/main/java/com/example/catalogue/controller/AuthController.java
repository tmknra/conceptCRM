package com.example.catalogue.controller;

import com.example.catalogue.dto.UserInDto;
import com.example.catalogue.mapper.UserMapper;
import com.example.catalogue.model.UsersEntity;
import com.example.catalogue.payload.request.LoginRequest;
import com.example.catalogue.payload.response.JwtResponse;
import com.example.catalogue.payload.response.MessageResponse;
import com.example.catalogue.repository.UsersRepository;
import com.example.catalogue.security.jwt.JwtUtils;
import com.example.catalogue.security.services.UserDetailsImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/auth")
public class AuthController {
    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    UsersRepository usersRepository;

    // @Autowired
    // RolesRepository rolesRepository;

    @Autowired
    PasswordEncoder encoder;

    @Autowired
    JwtUtils jwtUtils;

    @Autowired
    private UserMapper userMapper;

    @PostMapping("/login")
    public String authenticateUser(@Valid @RequestBody LoginRequest request) {
        Authentication authentication =
                authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword()));
        // authentication.setAuthenticated(true);

        SecurityContextHolder.getContext().setAuthentication(authentication);
        if (!authentication.isAuthenticated())
            throw new RuntimeException();
        String jwt = jwtUtils.generateJwtToken(authentication);
        System.out.println(jwt);
        UserDetailsImpl userDetails = new UserDetailsImpl(authentication.getPrincipal().toString());

        return jwt;
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
        // Create new user's account
        // UsersEntity user = new UsersEntity(userInDto.getUserName(),
        //         encoder.encode(userInDto.getPassword()));

        // List<RolesEntity> roles = userInDto.getRoles();
        // Set<RolesEntity> roles = new HashSet<>();

        // if (roles == null) {
        //     throw new RuntimeException("Error: Role is not found.");
        //     // RolesEntity userRole = rolesRepository.findByUsername("user")
        //     //         .orElseThrow(() ->
        //     // roles.add(userRole);
        // } else {
        //     roles.forEach(role -> {
        //         switch (role.getName()) {
        //             case "admin":
        //                 RolesEntity adminRole = rolesRepository.findByName("admin")
        //                         .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
        //                 roles.add(adminRole);
        //
        //                 break;
        //             // case "mod":
        //             //     RolesEntity modRole = roleRepository.findByName(ERole.ROLE_MODERATOR)
        //             //             .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
        //             //     roles.add(modRole);
        //             //
        //             //     break;
        //             default:
        //                 RolesEntity userRole = rolesRepository.findByName("user")
        //                         .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
        //                 roles.add(userRole);
        //         }
        //     });
        // }
        // List<RolesEntity> roles = new ArrayList<>();
        // RolesEntity rolesEntity = new RolesEntity();
        // rolesEntity.setName("admin");
        // roles.add(rolesEntity);
        // user.setRoles(roles);
        usersRepository.save(user);
        // System.out.println("HERE 2");

        return ResponseEntity.ok(new MessageResponse("User registered successfully!"));
    }
}
