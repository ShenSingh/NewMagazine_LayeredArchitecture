package lk.ijse.Entity;

import java.sql.Date;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Data
@ToString

public class InmateRecord {
    private String inmateId;
    private String sectionId;
    private Date entryDate;
    private Date releaseDate;
    private String crime;
    private String caseStatus;

}
