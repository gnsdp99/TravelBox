package com.ssafy.travelbox.plan.model.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Schema(title = "PassportDto : 여권 정보", description = "여권 정보를 나타냅니다. ")

public class PassportDto {
    private int passportId;
    private int planId;
    private String country;
    private String name;
    private String passportNumber;
    private String expiryDate;
}
