package hr.tvz.adamec.njamapp.model;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RestaurantCommand {
    private Long id;
    @NotBlank(message = "Restaurant must have a name")
    private String name;
    @NotBlank
    private String address;
    @NotNull
    private Long telephoneNumber;
    @NotBlank
    private String email;
    @PositiveOrZero
    private Integer workingHours;
    @NotNull
    private Boolean currentlyOpen;
    @PositiveOrZero
    private Integer averageDeliveryTime;
    @PositiveOrZero
    private Double averageRating;
    @PositiveOrZero
    private Float maxNumberOfOrders;
    @PositiveOrZero
    private Integer michelinStar;
    @NotNull
    private String description;
}
