package com.ssafy.travelbox.plan.model.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;
import java.sql.Timestamp;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Schema(title = "PlanMemberDto : 여행 정보", description = "어떤 여행이 어떤 유저가 가지고 있는지 나타낸다.")
public class PlanMemberDto {
    private int planId;
    private String memberId;
    private String memberName;
    private boolean isManager;
    private Timestamp joinDate;
}

