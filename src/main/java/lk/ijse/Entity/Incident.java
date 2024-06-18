package lk.ijse.Entity;

import java.time.LocalDate;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Data
@ToString

public class Incident {
    private String incidentId;
    private String sectionId;
    private String incidentType;
    private LocalDate incidentDate;
    private String incidentTime;
    private String description;
}
