package lk.ijse.Entity;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Data
@ToString

public class Expences {
    private String expencesID;
    private String sectionId;
    private String month;
    private String type;
    private double cost;
}
