package com.api.apiestacionamento.dto;

import org.hibernate.validator.constraints.Length;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.Data;

@Data
public class ParkingDto {
    
    @NotBlank
    @NotNull
    @Length(max = 25)
    private String name;
    
    @NotBlank
    @NotNull
    @Length(max = 25)
    @Pattern(regexp = "aero porto|porto|shoping|condominio")
    private String type;
}
