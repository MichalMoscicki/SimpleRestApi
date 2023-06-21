package com.immpresariat.SimpleRestApi.musicians;


import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.validation.annotation.Validated;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Musician {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotBlank(message = "First name must not be blank")
    private String firstName;
    @NotBlank(message = "Last name must not be blank")
    private String lastName;
    @Email(message = "Invalid email")
    private String email;

   // @Pattern(regexp = "/^[\\+]?[(]?[0-9]{3}[)]?[-\\s\\.]?[0-9]{3}[-\\s\\.]?[0-9]{4,6}$/mi")
    private String phoneNumber;
    @NotBlank(message = "Instrument must not be blank")
    private String instrument;


    public void update(Musician musicianUpdated) {
        this.firstName = musicianUpdated.getFirstName();
        this.lastName = musicianUpdated.getLastName();
        this.email = musicianUpdated.getEmail();
        this.phoneNumber = musicianUpdated.getPhoneNumber();
        this.instrument = musicianUpdated.getInstrument();
    }
}
