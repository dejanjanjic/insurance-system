package net.etfbl.sni.insurancesystem.controller;

import net.etfbl.sni.insurancesystem.dtos.RegisterResponseDTO;
import net.etfbl.sni.insurancesystem.enums.Role;
import net.etfbl.sni.insurancesystem.model.ResponseMessage;
import net.etfbl.sni.insurancesystem.model.User;
import net.etfbl.sni.insurancesystem.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/users")
public class UserController {
    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("register")
    public ResponseEntity<RegisterResponseDTO> register(@RequestBody User user) {
        return ResponseEntity.ok(userService.register(user));
    }
}
