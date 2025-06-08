package hr.tvz.adamec.njamapp.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CustomerDTO {
    private String name;
    private String surname;
    private String email;
    private LocalDate dateOfBirth;
}
