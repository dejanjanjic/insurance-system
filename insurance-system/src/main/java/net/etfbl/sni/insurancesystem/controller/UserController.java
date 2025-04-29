package net.etfbl.sni.insurancesystem.controller;

import net.etfbl.sni.insurancesystem.enums.Role;
import net.etfbl.sni.insurancesystem.model.ResponseMessage;
import net.etfbl.sni.insurancesystem.model.User;
import net.etfbl.sni.insurancesystem.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
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
    public ResponseEntity register(User user) {
        if (!user.getRole().equals(Role.ROLE_CLIENT)) {
            return ResponseEntity.status(HttpStatusCode.valueOf(403)).body(new ResponseMessage("Users role is not valid."));
        }
        User temp = userService.register(user);
        if (temp == null) {
            return ResponseEntity.status(HttpStatusCode.valueOf(409)).body(new ResponseMessage("Username or email already exists."));
        } else {
            return ResponseEntity.ok(temp);
        }
    }
}
