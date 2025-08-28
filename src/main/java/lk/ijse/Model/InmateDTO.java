package lk.ijse.Model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.io.Serializable;
import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Data
@ToString

public class InmateDTO implements Serializable {
    private String inmateId;
    private String inmateFirstName;
    private String inmateLastName;
    private LocalDate inmateDOB;
    private String inmateNIC;
    private String gender;
    private String inmateAddress;
    private String status;
}
