package hr.tvz.adamec.njamapp.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Review {
    private Long id;
    private Long restaurantId;
    private String title;
    private String description;
    private Long grade;
    private LocalDate date;
}
