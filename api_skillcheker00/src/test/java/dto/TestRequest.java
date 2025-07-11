package dto;

import lombok.Data;
import com.fasterxml.jackson.annotation.JsonInclude;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class TestRequest {
    private String name;
    private String description;
    private Integer timeLimit;
    private Integer passingScore;
}