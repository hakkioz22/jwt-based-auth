package com.tpe.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class RegisterRequest {
    @NotBlank(message = "Please provide firstName")
    private String firstName;

    @NotBlank(message = "Please provide lastName")
    private String lastName;

    @NotBlank(message = "Please provide userName")
    @Size(min = 5,max = 10,message = "Please provide a User Name min=5,max=10 chars long")
    private String userName;

    @NotBlank(message = "Please provide userName")
    private String password;
}
