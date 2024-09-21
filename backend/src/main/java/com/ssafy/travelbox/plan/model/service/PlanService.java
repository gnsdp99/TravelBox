package com.ssafy.travelbox.plan.model.service;

import com.ssafy.travelbox.plan.model.dto.PassportDto;
import com.ssafy.travelbox.plan.model.dto.PlanDto;
import com.ssafy.travelbox.plan.model.dto.PlanMemberDto;

import java.util.List;
import java.util.Map;

public interface PlanService {
    List<Integer> listOfPlanNums (String userId) throws Exception;
    PlanDto getPlan (int planId) throws Exception;
//    int gerPassportId(int planId) throws Exception;
    List<PassportDto> getPassportByPlanId(int planId) throws Exception;
    int getAccomodationId (int planId) throws Exception;
    int getItineraryId (int planId) throws Exception;

    int createPlan(PlanDto plan) throws Exception;
    int modifyPlan(PlanDto plan) throws Exception;
    int removePlan(Map<String, String> param) throws Exception;
    int addPlanMember(PlanMemberDto planMember) throws Exception;
    int removePlanMember(Map<String, String> param) throws Exception;
    List<PlanDto> findPlansByMemberId(String memberId) throws Exception;
    List<PlanMemberDto> findMembersByPlanId(int planId) throws Exception;

    // passport
    PassportDto getPassportInfo(int passportId) throws Exception;
    int modifyPassportInfo(PassportDto passportDto) throws Exception;
    int addPassport(int planId) throws Exception;
    int deletePassport(int passportId) throws Exception;
}
