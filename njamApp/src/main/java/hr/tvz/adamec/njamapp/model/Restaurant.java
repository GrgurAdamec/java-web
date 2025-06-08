package hr.tvz.adamec.njamapp.model;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Restaurant {
    private Long id;
    private String name;
    private String address;
    private Long telephoneNumber;
    private String email;
    private Integer workingHours;
    private Boolean currentlyOpen;
    private Integer averageDeliveryTime;
    private Double averageRating;
    private Float maxNumberOfOrders;
    private Integer michelinStar;
    private String description;
}
