package lk.ijse.Entity;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Data
@ToString

public class Section {
    private String sectionId;
    private String sectionName;
    private String location;
    private Integer capacity;
    private String securityLevel;
    private String status;
}
