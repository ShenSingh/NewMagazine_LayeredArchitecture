package lk.ijse.Model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.io.Serializable;

@AllArgsConstructor
@NoArgsConstructor
@Data
@ToString

public class IncidentRelatedInmateDTO implements Serializable {
    private String incidentID;
    private String inmateID;
}
