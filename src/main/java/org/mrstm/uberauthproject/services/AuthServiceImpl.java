package org.mrstm.uberauthproject.services;

import org.mrstm.uberauthproject.dto.PassengerResponseDTO;
import org.mrstm.uberauthproject.dto.PassengerSignUpRequestDTO;
import org.mrstm.uberauthproject.models.Passenger;
import org.mrstm.uberauthproject.repositories.PassengerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;


@Service
public class AuthServiceImpl implements AuthService {

    private final PassengerRepository passengerRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    /*
    Note -> we cannot directly use dependency injection  here.
    bcoz spring doesn't know how to create object of it.
    So, handling it in configurations.
    After handling it in configurations package. it started working here
    as now spring knows from where we have to initiate it.
     */

    public AuthServiceImpl(PassengerRepository passengerRepository , BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
        this.passengerRepository = passengerRepository;
    }

    @Override
    public PassengerResponseDTO sigunUpPassenger(PassengerSignUpRequestDTO passengerSignUpRequestDTO) {
        Passenger passenger = Passenger.builder()
                .email(passengerSignUpRequestDTO.getEmail())
                .password(bCryptPasswordEncoder.encode(passengerSignUpRequestDTO.getPassword())) // to be encrypted
                .passanger_name(passengerSignUpRequestDTO.getName())
                .phoneNumber(passengerSignUpRequestDTO.getPhoneNumber())
                .build();
        Passenger p = passengerRepository.save(passenger);
        return PassengerResponseDTO.fromPassenger(p);

    }
}
