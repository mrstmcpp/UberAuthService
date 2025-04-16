package org.mrstm.uberauthproject.services;

import org.mrstm.uberauthproject.dto.PassengerResponseDTO;
import org.mrstm.uberauthproject.dto.PassengerSignUpRequestDTO;
import org.springframework.stereotype.Component;

@Component
public interface AuthService {
    public default PassengerResponseDTO sigunUpPassenger(PassengerSignUpRequestDTO passengerSignUpRequestDTO) {
        return null;
    }
}
