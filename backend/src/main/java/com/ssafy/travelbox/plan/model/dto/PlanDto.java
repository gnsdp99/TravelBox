package com.ssafy.travelbox.plan.model.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;
import java.sql.Timestamp;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Schema(title = "PlanDto : 여행 정보", description = "하나의 여행에 대한 정보를 나타낸다.")
public class PlanDto {
    private int planId;
    private String planName;
    private Date startDate;
    private Date endDate;
    private Timestamp registerDate;
    private String managerId; // 방장 아이디
}

