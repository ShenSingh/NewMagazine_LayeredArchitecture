package lk.ijse.Entity;

import java.time.LocalDate;
import java.time.LocalTime;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Data
@ToString

public class Program {
    private String programId;
    private String programName;
    private String sectionId;
    private LocalDate programDate;
    private LocalTime programTime;
    private String description;
}
