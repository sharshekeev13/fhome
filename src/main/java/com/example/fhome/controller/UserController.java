package com.example.fhome.controller;

import com.example.fhome.domain.dto.request.UserLoginDto;
import com.example.fhome.domain.dto.request.UserRegistrationDto;
import com.example.fhome.domain.entity.User;
import com.example.fhome.domain.enums.Status;
import com.example.fhome.service.impl.UserServiceImpl;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@Tag(name = "USERS", description = "Конечные точки для работы с пользователями")
@RestController
@RequiredArgsConstructor
@RequestMapping("api/user")
public class UserController {
    private final UserServiceImpl userService;



    @Operation(summary = "Регистрация пользователя")
    @PostMapping("/registration")
    public ResponseEntity<User> userRegistration(@RequestBody UserRegistrationDto userRegistrationDto){
        return ResponseEntity.ok(userService.registration(userRegistrationDto));
    }

    @Operation(summary = "Подтверждение почты пользователя")
    @PostMapping("/verify/{userId}/{code}")
    public ResponseEntity<User> userVerify(@PathVariable Long userId,@PathVariable Integer code){
        return ResponseEntity.ok(userService.verifyEmail(userId,code));
    }

    @Operation(summary = "Вход в аккаунт пользователя")
    @PostMapping("/login")
    public ResponseEntity<User> userLogin(@RequestBody UserLoginDto userLoginDto){
        return ResponseEntity.ok(userService.login(userLoginDto));
    }

    @Operation(summary = "Пользователь по его идентификатору")
    @GetMapping("/{id}")
    public ResponseEntity<User> getUserById(@PathVariable Long id){
        return ResponseEntity.ok(userService.getUserById(id));
    }

    @Operation(summary = "Все пользователи")
    @GetMapping("/all")
    public ResponseEntity<List<User>> getUsers(
            @RequestParam(defaultValue = "0") Integer pageNo,
            @RequestParam(defaultValue = "id") String sortBy){
        return ResponseEntity.ok(userService.findAll(pageNo,sortBy));
    }

    @Operation(summary = "Обновление информации о пользователе")
    @PostMapping("/{id}")
    public ResponseEntity<User> updateUserInfo(@PathVariable Long id, @RequestParam Integer status){
        return ResponseEntity.ok(userService.updateUserInfo(id,status));
    }


}
