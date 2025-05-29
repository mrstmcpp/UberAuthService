package org.mrstm.uberauthproject.controllers;


import org.mrstm.uberauthproject.dto.AuthRequestDto;
import org.mrstm.uberauthproject.dto.PassengerResponseDTO;
import org.mrstm.uberauthproject.dto.PassengerSignUpRequestDTO;
import org.mrstm.uberauthproject.services.AuthService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/auth")
public class AuthController {

    private final AuthenticationManager authenticationManager;
    private AuthService authService;

    public AuthController(AuthService authService, AuthenticationManager authenticationManager) {
        this.authService = authService;
        this.authenticationManager = authenticationManager;
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

    @PostMapping("/signin/passenger")
    public ResponseEntity<?> signIn(@RequestBody AuthRequestDto authRequestDto) {
        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(authRequestDto.getEmail(), authRequestDto.getPassword()));
        if(authentication.isAuthenticated()) {
            return new ResponseEntity<>("Successfully logged in", HttpStatus.OK);
        }
        return new ResponseEntity<>("Authorization unsuccessful.", HttpStatus.EXPECTATION_FAILED);
    }
}
