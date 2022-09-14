package com.example.vabatahtlikud.admin;

import com.example.vabatahtlikud.domain.event.event.EventInfo;
import com.example.vabatahtlikud.domain.event.event.PastEventInfo;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

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


    @GetMapping("/event/past")
    @Operation(summary = "Kuva kõik möödunud üritused")
    public List<PastEventInfo> findAllPastEvents() {
        return adminService.findAllPastEvents();
    }
}
