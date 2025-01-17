package com.ametov.postgres_test.user.controller;

import com.ametov.postgres_test.user.dto.request.UserRequest;
import com.ametov.postgres_test.user.dto.response.UserResponse;
import com.ametov.postgres_test.user.entity.UserEntity;
import com.ametov.postgres_test.user.exception.BadRequestException;
import com.ametov.postgres_test.user.exception.UserNotFoundException;
import com.ametov.postgres_test.user.repository.UserRepository;
import com.ametov.postgres_test.user.routes.UserRoutes;
import com.ametov.postgres_test.user.service.UserService;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
//@AllArgsConstructor
@RequiredArgsConstructor
public class UserApiController {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Value("${init.email}")
    private String initUser;
    @Value("${init.password}")
    private String initPassword;

    @GetMapping("/")
    public UserEntity root() {
        UserEntity user = UserEntity.builder()
                .firstName("test")
                .lastName("test")
                .build();

        user = userRepository.save(user);
        return user;
    }

    @PostMapping(UserRoutes.INIT)
    public String init() {
        for (int i = 0; i < 10000; i++) {
            userRepository.save(UserService.createUser());
        }
        return HttpStatus.OK.name();
    }

    @PostMapping(UserRoutes.CREATE)
    public UserResponse create(@RequestBody UserRequest request) throws BadRequestException {
        request.validate();

        UserEntity user = UserEntity.builder()
                .firstName(request.getFirstName())
                .lastName(request.getLastName())
                .email(request.getEmail())
                .password(passwordEncoder.encode(request.getPassword()))
                .build();

        user = userRepository.save(user);
        return UserResponse.of(user);
    }

    @GetMapping(UserRoutes.BY_ID)
    public UserResponse getById(@PathVariable Long id) throws UserNotFoundException {
        return UserResponse.of(userRepository.findById(id).orElseThrow(UserNotFoundException::new));
    }

    @GetMapping(UserRoutes.SEARCH)
    public List<UserResponse> getAll(
            @RequestParam(defaultValue = "0") Integer page,
            @RequestParam(defaultValue = "10") Integer size,
            @RequestParam(defaultValue = "") String query
    ) {
        Pageable pageable = PageRequest.of(page, size);
        ExampleMatcher exampleMatcher = ExampleMatcher.matchingAny()
                .withMatcher("firstName", ExampleMatcher.GenericPropertyMatchers.contains().ignoreCase())
                .withMatcher("lastName", ExampleMatcher.GenericPropertyMatchers.contains().ignoreCase());

        Example<UserEntity> example = Example.of(
                UserEntity.builder().firstName(query).lastName(query).build(), exampleMatcher
        );

        return userRepository.findAll(example, pageable).stream().map(UserResponse::of).collect(Collectors.toList());
    }

    @PutMapping(UserRoutes.BY_ID)
    public UserResponse edit(@PathVariable Long id, @RequestBody UserRequest request) throws UserNotFoundException {
        UserEntity user = userRepository.findById(id).orElseThrow(UserNotFoundException::new);
        if (request.getFirstName() != null) user.setFirstName(request.getFirstName());
        if (request.getLastName() != null) user.setLastName(request.getLastName());

        user = userRepository.save(user);

        return UserResponse.of(user);
    }

    @DeleteMapping(UserRoutes.BY_ID)
    public String delete(@PathVariable Long id) throws UserNotFoundException {
        userRepository.deleteById(id);
        return HttpStatus.OK.name();
    }

    @GetMapping(UserRoutes.NOT_SECURED_INIT)
    public UserResponse not_secured_init(){
        Optional<UserEntity> checkUser = userRepository.findByEmail(initUser);
        UserEntity user;

        if(checkUser.isEmpty()){
            user = UserEntity.builder()
                    .firstName("Default")
                    .lastName("Default")
                    .email(initUser)
                    .password(passwordEncoder.encode(initPassword))
                    .build();

            userRepository.save(user);
        } else {
            user = checkUser.get();
        }
        return UserResponse.of(user);
    }
}