package com.example.vabatahtlikud.user;

import io.swagger.v3.oas.annotations.Operation;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

@RestController
public class UserController {

    @Resource
    private UserService userService;

    @PatchMapping("/user/update")
    @Operation(summary = "Kliendi andmete muutmine")
    public void updateUserData(@RequestBody UserRequest request, @RequestParam Integer userId) {
        userService.updateUserData(request, userId);
    }

    @DeleteMapping("/user/delete")
    @Operation(summary = "Kasutaja konto kustutamine ehk staatuse muutmine")
    public void deleteUser(@RequestParam Integer userId) {
        userService.deleteUser(userId);
    }
}
