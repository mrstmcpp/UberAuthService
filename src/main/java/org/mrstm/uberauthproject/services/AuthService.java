package org.mrstm.uberauthproject.services;

import org.mrstm.uberauthproject.dto.PassengerResponseDTO;
import org.mrstm.uberauthproject.dto.PassengerSignUpRequestDTO;
import org.springframework.stereotype.Service;

@Service
public interface AuthService {
    public PassengerResponseDTO sigunUpPassenger(PassengerSignUpRequestDTO passengerSignUpRequestDTO);
}
