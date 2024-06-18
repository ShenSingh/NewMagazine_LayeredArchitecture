package lk.ijse.Model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalTime;

@AllArgsConstructor
@NoArgsConstructor
@Data
@ToString

public class ProgramDTO implements Serializable {
    private String programId;
    private String programName;
    private String sectionId;
    private LocalDate programDate;
    private LocalTime programTime;
    private String description;
}
