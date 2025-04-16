package org.mrstm.uberauthproject.dto;


import lombok.*;

import java.util.Date;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PassengerResponseDTO {
    private int id;
    private String name;
    private String phoneNumber;
    private String email;
    private String password;
    private Date createdAt;
}
