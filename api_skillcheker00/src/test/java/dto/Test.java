package dto;

import lombok.Data;

@Data
public class Test {
    private int id;
    private int organizationId;
    private String name;
    private String description;
    private int createdBy;
    private Integer timeLimit; // nullable
    private boolean isActive;
    private int passingScore;
}