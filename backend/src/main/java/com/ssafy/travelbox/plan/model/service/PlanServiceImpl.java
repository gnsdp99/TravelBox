package com.ssafy.travelbox.plan.model.service;

import com.ssafy.travelbox.plan.model.dto.PassportDto;
import com.ssafy.travelbox.plan.model.dto.PlanDto;
import com.ssafy.travelbox.plan.model.dto.PlanMemberDto;
import com.ssafy.travelbox.plan.model.mapper.PlanMapper;
import com.ssafy.travelbox.user.model.dto.UserDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Map;

@Service
public class PlanServiceImpl implements PlanService{
    private PlanMapper planMapper;

    public PlanServiceImpl(PlanMapper planMapper) {
        this.planMapper = planMapper;
    }

    @Override
    public List<Integer> listOfPlanNums(String userId) throws Exception {
        return planMapper.getPlanById(userId);
    }

    @Override
    public PlanDto getPlan(int planId) throws Exception {
        return planMapper.getPlan(planId);
    }

    @Override
    public List<PassportDto> getPassportByPlanId(int planId) throws Exception {
        return planMapper.getPassportByPlanId(planId);
    }

//    @Override
//    public int gerPassportId(int planId) throws Exception {
//        Integer passportId = planMapper.getPassportId(planId);
//        return passportId != null ? passportId : -1;
//    }



    @Override
    public int getAccomodationId(int planId) throws Exception {
        Integer accomodationId = planMapper.getAccomodationId(planId);
        return accomodationId != null ? accomodationId : -1;
    }

    @Override
    public int getItineraryId(int planId) throws Exception {
        Integer itineraryId = planMapper.getItineraryId(planId);
        return itineraryId != null ? itineraryId : -1;
    }

    @Override
    public PassportDto getPassportInfo(int passportId) throws Exception {
        return planMapper.getPassportInfo(passportId);
    }

    @Override
    public int modifyPassportInfo(PassportDto passportDto) throws Exception {
        return planMapper.updatePassportInfo(passportDto);
    }

    @Override
    public int addPassport(int planId) throws Exception {
        return planMapper.insertPassport(planId);
    }

    @Override
    public int deletePassport(int passportId) throws Exception {
        return planMapper.deletePassport(passportId);
    }

    @Override
    public int createPlan(PlanDto plan) throws Exception {
        // 플랜 생성 후 방장 plan_member 테이블에 추가
        int result = planMapper.insertPlan(plan);
        if (result == 1) {
            PlanMemberDto planMember =
                    PlanMemberDto.
                            builder().
                            planId(plan.getPlanId()).
                            memberId(plan.getManagerId()).
                            isManager(true).build();
            return planMapper.insertPlanMember(planMember);
        } else {
            return 0;
        }
    }

    @Override
    public int modifyPlan(PlanDto plan) throws Exception {
        return planMapper.updatePlan(plan);
    }

    @Override
    public int removePlan(Map<String, String> param) throws Exception {
        // 방장만 삭제 가능
        PlanMemberDto planMember = planMapper.selectPlanMember(param);
        if (planMember != null && planMember.isManager()) {
            return planMapper.deletePlan(planMember.getPlanId());
        } else {
            return 0;
        }
    }

    @Override
    public int addPlanMember(PlanMemberDto planMember) throws Exception {
        return planMapper.insertPlanMember(planMember);
    }

    @Override
    public int removePlanMember(Map<String, String> param) throws Exception {
        return planMapper.deletePlanMember(param);
    }

    @Override
    public List<PlanDto> findPlansByMemberId(String memberId) throws Exception {
        return planMapper.selectPlansByMemberId(memberId);
    }

    @Override
    public List<PlanMemberDto> findMembersByPlanId(int planId) throws Exception {
        return planMapper.selectMembersByPlanId(planId);
    }
}
