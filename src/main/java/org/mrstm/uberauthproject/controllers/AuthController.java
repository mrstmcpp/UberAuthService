package org.mrstm.uberauthproject.controllers;


import org.mrstm.uberauthproject.dto.PassengerResponseDTO;
import org.mrstm.uberauthproject.dto.PassengerSignUpRequestDTO;
import org.mrstm.uberauthproject.services.AuthService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/auth")
public class AuthController {

    private AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping("/signup/passenger")
    public ResponseEntity<PassengerResponseDTO> signupPassenger(@RequestBody PassengerSignUpRequestDTO passengerSignUpRequestDTO) {
        PassengerResponseDTO response = authService.sigunUpPassenger(passengerSignUpRequestDTO);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @PostMapping("/signup/driver")
    public ResponseEntity<?> signupDriver(@RequestParam String driver) {
        return ResponseEntity.ok().build();
    }
}
