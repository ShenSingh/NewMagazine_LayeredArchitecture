package lk.ijse.Entity;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Data
@ToString

public class User {
    private String uId;
    private String uName;
    private String uEmail;
    private String uPassword;
    private String addressLine1;
    private String addressLine2;
    private String phone;
    private String gender;
    private String dob;
    private byte[] imageData;
}
