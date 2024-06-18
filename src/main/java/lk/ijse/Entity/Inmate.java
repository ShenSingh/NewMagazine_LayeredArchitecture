package lk.ijse.Entity;

import java.time.LocalDate;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Data
@ToString

public class Inmate {
    private String inmateId;
    private String inmateFirstName;
    private String inmateLastName;
    private LocalDate inmateDOB;
    private String inmateNIC;
    private String gender;
    private String inmateAddress;
    private String status;
    private byte[] inmateImage;
}
