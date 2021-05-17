package ru.ncedu.auth.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

import java.util.Set;
import javax.persistence.Column;
import javax.validation.constraints.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SignupRequest {

    @NotBlank
    @Pattern(regexp = "[A-aZ-z-]+")
    @Size(min = 3, max = 20)
    private String username;

    @NotBlank
    @Size(max = 50)
    @Email
    private String email;

    private Set<String> role;

    @NotBlank
    @Size(min = 6, max = 40)
    private String password;

    @Column(name = "verification_code", length = 64)
    @Length(max = 64, message = "length 64")
    private String verificationCode;

    private boolean verifyEnabled;

    public boolean getVerifyEnabled(){
        return this.verifyEnabled;
    }
}
