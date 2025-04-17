package org.mrstm.uberauthproject.dto;


import lombok.*;
import org.mrstm.uberauthproject.models.Passenger;

import java.util.Date;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PassengerResponseDTO {
    private Long id;
    private String name;
    private String phoneNumber;
    private String email;
    private String password;
    private Date createdAt;

    public static PassengerResponseDTO fromPassenger(Passenger passenger) {
        PassengerResponseDTO dto = PassengerResponseDTO.builder()
                .id(passenger.getId())
                .createdAt(passenger.getCreatedAt())
                .email(passenger.getEmail())
                .password(passenger.getPassword())
                .phoneNumber(passenger.getPhoneNumber())
                .name(passenger.getPassanger_name())
                .build();
        return dto;
    }
}
