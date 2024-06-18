package lk.ijse.Entity;

import java.sql.Date;
import java.sql.Time;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Data
@ToString

public class VisitorRecord {
    private String visitorRecordId;
    private String visitorId;
    private String inmateId;
    private Date visitDate;
    private Time visitTime;
}
