package com.example.vabatahtlikud.user;

import io.swagger.v3.oas.annotations.Operation;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

@RestController
@RequestMapping("/user")
public class UserController {

    @Resource
    private UserService userService;

    @PatchMapping("/update")
    @Operation(summary = "Kliendi andmete muutmine")
    public void updateUserData(@RequestBody UserInfoInfo request, @RequestParam Integer userId) {
        userService.updateUserData(request, userId);
    }

    @DeleteMapping("/delete")
    @Operation(summary = "Kasutaja konto kustutamine ehk staatuse muutmine")
    public void deleteUser(@RequestParam Integer userId) {
        userService.deleteUser(userId);
    }

    @GetMapping("/user")
    @Operation(summary = "Kuva kasutaja info")
    public UserInfoInfo findUserInfo(@RequestParam Integer userId) {
        return userService.findUserInfo(userId);
    }

}
