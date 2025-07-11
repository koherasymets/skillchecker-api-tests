package dto;

import lombok.Data;

@Data
public class UserResponse {
    private int id;
    private int organizationId;
    private String fullName;
    private String role;
    private String email;
    private boolean active;
    private String lastLogin;
}