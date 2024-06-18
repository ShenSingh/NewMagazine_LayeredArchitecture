package lk.ijse.Model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.io.Serializable;

@AllArgsConstructor
@NoArgsConstructor
@Data
@ToString

public class ExpencesDTO implements Serializable {
    private String expencesID;
    private String sectionId;
    private String month;
    private String type;
    private double cost;
}
