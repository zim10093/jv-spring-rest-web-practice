package mate.academy.spring.controller;

import java.util.NoSuchElementException;
import mate.academy.spring.mapper.impl.response.UserResponseMapper;
import mate.academy.spring.model.dto.response.UserResponseDto;
import mate.academy.spring.service.UserService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/users")
public class UserController {
    private final UserService userService;
    private final UserResponseMapper mapper;

    public UserController(UserService userService, UserResponseMapper mapper) {
        this.userService = userService;
        this.mapper = mapper;
    }

    @GetMapping("/by-email")
    public UserResponseDto getUser(@RequestParam String email) {
        return mapper.toDto(userService.findByEmail(email).orElseThrow(() ->
                new NoSuchElementException("Can`t find user by email: " + email)));
    }
}