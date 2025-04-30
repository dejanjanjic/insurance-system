package net.etfbl.sni.insurancesystem.auth;

import net.etfbl.sni.insurancesystem.dtos.LoginRequestDTO;
import net.etfbl.sni.insurancesystem.dtos.LoginResponseDTO;
import net.etfbl.sni.insurancesystem.dtos.RegisterResponseDTO;
import net.etfbl.sni.insurancesystem.dtos.VerificationCodeDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/auth")
public class AuthController {
    private final AuthService authService;

    @Autowired
    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping("/login")
    public ResponseEntity<RegisterResponseDTO> login(@RequestBody LoginRequestDTO request) {
        return ResponseEntity.ok(authService.login(request));
    }

    @PostMapping("/verify")
    public ResponseEntity<LoginResponseDTO> verify(@RequestBody VerificationCodeDTO verificationCodeDTO){
        return ResponseEntity.ok(authService.verifyCode(verificationCodeDTO));
    }
}
