package lk.ijse.Entity;

import java.sql.Date;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Data
@ToString

public class Visitor {
    private String visitorID;
    private String visitorFirstName;
    private String visitorLastName;
    private Date visitorDOB;
    private String visitorNIC;
    private String gender;
    private int visitorNumber;
    private String visitorAddress;
    private String visitorType;
    private byte[] visitorImage;
}
