package hr.tvz.adamec.njamapp.model;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.PastOrPresent;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CustomerCommand {
    @DecimalMin("5")
    private Long id;
    @NotEmpty (message = "Name can't be empty!")
    private String name;
    @NotEmpty (message = "Surname can+t be empty!")
    private String surname;
    @NotEmpty (message = "Email can't be empty!")
    private String email;
    @Past
    private LocalDate dateOfBirth;
}
