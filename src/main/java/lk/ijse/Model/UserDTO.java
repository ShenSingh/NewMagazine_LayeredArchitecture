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

public class UserDTO implements Serializable {
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
