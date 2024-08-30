package application.backend.models.DTO;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class LoyaltyCardDTO {

    private Long id;
    private Long points;
    private Boolean discount;

}
