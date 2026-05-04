package cl.duoc.LibraryFlow.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Users {

    private Integer id;
    private String fullName;
    private String email;
    private String phone;
    private Date createdAt;
}
