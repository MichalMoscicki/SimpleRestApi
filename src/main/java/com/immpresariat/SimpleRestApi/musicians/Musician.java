package com.immpresariat.SimpleRestApi.musicians;


import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Musician {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotBlank
    private String firstName;
    @NotBlank
    private String lastName;
    @Email
    private String email;
    private String phoneNumber;
    @NotBlank
    private String instrument;


    public void update(Musician musicianUpdated) {
        this.firstName = musicianUpdated.getFirstName();
        this.lastName = musicianUpdated.getLastName();
        this.email = musicianUpdated.getEmail();
        this.phoneNumber = musicianUpdated.getPhoneNumber();
        this.instrument = musicianUpdated.getInstrument();
    }
}
