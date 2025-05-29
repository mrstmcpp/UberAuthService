package org.mrstm.uberauthproject.services;

import org.mrstm.uberauthproject.helpers.AuthPassengerDetails;
import org.mrstm.uberauthproject.models.Passenger;
import org.mrstm.uberauthproject.repositories.PassengerRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.Optional;

public class UserDetailsServiceImpl implements UserDetailsService {
    //class is responsible for loading the user in the form of UserDetails object for auth.

    private final PassengerRepository passengerRepository;
    public UserDetailsServiceImpl(PassengerRepository passengerRepository) {
        this.passengerRepository = passengerRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Optional<Passenger> passenger = passengerRepository.findByEmail(email);
        if(passenger.isPresent()){
            return new AuthPassengerDetails(passenger.get());
        }else{
            throw new UsernameNotFoundException("No passenger found with email: " + email);
        }

    }
}
