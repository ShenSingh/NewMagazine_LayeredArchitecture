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

public class InmateRecordDTO implements Serializable {
    private String inmateId;
    private String sectionId;
    private Date entryDate;
    private Date releaseDate;
    private String crime;
    private String caseStatus;

}
