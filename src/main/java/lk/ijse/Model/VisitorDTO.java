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

public class VisitorDTO implements Serializable {
    private String visitorID;
    private String visitorFirstName;
    private String visitorLastName;
    private Date visitorDOB;
    private String visitorNIC;
    private Integer visitorNumber;
    private String visitorAddress;
    private String visitorType;
    private String gender;
}
