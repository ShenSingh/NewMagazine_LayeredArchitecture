package lk.ijse.Model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.io.Serializable;
import java.sql.Date;

@AllArgsConstructor
@NoArgsConstructor
@Data
@ToString

public class OfficerDTO implements Serializable {
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
