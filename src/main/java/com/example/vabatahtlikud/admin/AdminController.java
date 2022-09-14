package com.example.vabatahtlikud.admin;

import io.swagger.v3.oas.annotations.Operation;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@RequestMapping("/admin")
public class AdminController {

    @Resource
    private AdminService adminService;

    @PatchMapping("/event/valid")
    @Operation(summary = "Kinnita üritus ehk muuda staatust")
    public void validateEvent(Integer eventId) {
        adminService.validateEvent(eventId);
    }
}
