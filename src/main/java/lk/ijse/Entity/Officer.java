package lk.ijse.Entity;

import java.sql.Date;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Data
@ToString

public class Officer {
    private String officerId;
    private String officerFirstName;
    private String officerLastName;
    private Date officerDOB;
    private String officerNIC;
    private String gender;
    private String officerAddress;
    private String officerEmail;
    private String officerNumber;
    private String position;
    private String sectionId;
    private double salary;
}
