package lk.ijse.Model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.io.Serializable;
import java.sql.Date;
import java.sql.Time;

@AllArgsConstructor
@NoArgsConstructor
@Data
@ToString

public class VisitorRecordDTO implements Serializable {
    private String visitorRecordId;
    private String visitorId;
    private String inmateId;
    private Date visitDate;
    private Time visitTime;
}
