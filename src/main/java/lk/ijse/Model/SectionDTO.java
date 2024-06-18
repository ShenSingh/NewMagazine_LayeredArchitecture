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

public class SectionDTO implements Serializable {
    private String sectionId;
    private String sectionName;
    private String location;
    private Integer capacity;
    private String securityLevel;
    private String status;
}
