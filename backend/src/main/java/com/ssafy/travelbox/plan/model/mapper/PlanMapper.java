package com.ssafy.travelbox.plan.model.mapper;

import com.ssafy.travelbox.plan.model.dto.PassportDto;
import com.ssafy.travelbox.plan.model.dto.PlanDto;
import com.ssafy.travelbox.plan.model.dto.PlanMemberDto;
import org.apache.ibatis.annotations.Mapper;


import java.util.List;
import java.util.Map;

@Mapper
public interface PlanMapper {
    PlanDto getPlan(int planId);
    List<Integer> getPlanById(String userId);
//    int getPassportId(int planId);
    List<PassportDto> getPassportByPlanId(int planId);
    int getAccomodationId(int planId);
    int getItineraryId(int planId);


    int insertPlan(PlanDto plan);
    int updatePlan(PlanDto plan);
    int deletePlan(int planId);

    int insertPlanMember(PlanMemberDto planMember);
    int deletePlanMember(Map<String, String> param); // { planId, memberId }

    PlanMemberDto selectPlanMember(Map<String, String> param);
    List<PlanDto> selectPlansByMemberId(String memberId);
    List<PlanMemberDto> selectMembersByPlanId(int planId);

    // passport 관련
    PassportDto getPassportInfo(int passportId);
    int updatePassportInfo(PassportDto passport);
    int insertPassport(int planId);
    int deletePassport(int passportId);
}