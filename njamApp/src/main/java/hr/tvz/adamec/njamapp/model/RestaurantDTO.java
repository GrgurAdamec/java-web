package hr.tvz.adamec.njamapp.model;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RestaurantDTO {
    private Long id;
    private String name;
    private String address;
    private Boolean currentlyOpen;
    private Float workload;

}
