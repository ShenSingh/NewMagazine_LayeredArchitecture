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

public class IncidentDTO implements Serializable {
    private String incidentId;
    private String sectionId;
    private String incidentType;
    private LocalDate incidentDate;
    private String incidentTime;
    private String description;
}
