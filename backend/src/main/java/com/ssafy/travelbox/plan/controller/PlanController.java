package com.ssafy.travelbox.plan.controller;

import com.ssafy.travelbox.plan.model.dto.PassportDto;
import com.ssafy.travelbox.plan.model.dto.PlanDto;
import com.ssafy.travelbox.plan.model.dto.PlanMemberDto;
import com.ssafy.travelbox.plan.model.service.PlanService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/plan")
@Tag(name = "여행 일정과 관련된 컨트롤러", description = "여행 일정 확인, 새 여행 생성 등 여행 일정과 관련된 내용을 처리하는 클래스")
@Slf4j
public class PlanController {
    private final PlanService planService;

    public PlanController(PlanService planService) {
        this.planService = planService;
    }

    @Operation(summary = "여행 프로젝트 불러오기", description = "회원이 생성한 여행 리스트 불러오기")
    @GetMapping("/list/{userId}")
    public ResponseEntity<Map<String, Object>> list(
            @PathVariable("userId") @Parameter(description = "회원이 생성한 여행 리스트 목록", required = true) String userId) {

        Map<String, Object> resultMap = new HashMap<>();
        HttpStatus status = HttpStatus.ACCEPTED;
        try {
            log.debug("userId: {}", userId);
            List<Integer> planNums = planService.listOfPlanNums(userId);
            List<PlanDto> lists = new ArrayList<>();

            for (Integer planNum : planNums) {
                lists.add(planService.getPlan(planNum));
            }
            resultMap.put("list", lists);
            log.debug(lists.toString());
            status = HttpStatus.OK;

        } catch (Exception e) {
            log.debug("목록 가져오기 에러 발생 : {}", e);
            resultMap.put("message", e.getMessage());
            status = HttpStatus.INTERNAL_SERVER_ERROR;
        }
        return new ResponseEntity<>(resultMap, status);
    }

    @Operation(summary = "숙소정보계획 아이디 가져오기", description = "숙박정보가 담겨있는 정보의 아이디를 가져오는 url")
    @GetMapping("/accomodation/{planId}")
    public ResponseEntity<Map<String, Object>> accomodation(
            @PathVariable("planId") @Parameter(description = "해당 여행의 여권 템플릿 ID", required = true) int planId) {


        Map<String, Object> resultMap = new HashMap<>();
        HttpStatus status = HttpStatus.OK;
        try {
            log.debug("planId: {}", planId);
            int accomodationId = planService.getAccomodationId(planId);
            if (accomodationId == -1) {
                resultMap.put("message", "No accommodation found for the given plan ID");
            } else {
                resultMap.put("id", accomodationId);
            }

        } catch (Exception e) {
            log.debug("목록 가져오기 에러 발생 : {}", e);
            resultMap.put("message", e.getMessage());
            status = HttpStatus.INTERNAL_SERVER_ERROR;
        }
        return new ResponseEntity<>(resultMap, status);
    }

    @Operation(summary = "여행 계획 아이디 가져오기", description = "여행 일정 정보가 담겨있는 정보의 아이디를 가져오는 URL")
    @GetMapping("/itinerary/{planId}")
    public ResponseEntity<Map<String, Object>> itinerary(
            @PathVariable("planId") @Parameter(description = "해당 여행의 일정 템플릿 ID", required = true) int planId) {

        Map<String, Object> resultMap = new HashMap<>();
        HttpStatus status = HttpStatus.OK;
        try {
            log.debug("planId: {}", planId);
            int itineraryId = planService.getItineraryId(planId);
            if (itineraryId == -1) {
                resultMap.put("message", "No itinerary found for the given plan ID");
            } else {
                resultMap.put("id", itineraryId);
            }

        } catch (Exception e) {
            log.debug("목록 가져오기 에러 발생 : {}", e);
            resultMap.put("message", e.getMessage());
            status = HttpStatus.INTERNAL_SERVER_ERROR;
        }
        return new ResponseEntity<>(resultMap, status);
    }

    @Operation(summary = "여행 계획 가져오기", description = "여행 정보 가져오기")
    @GetMapping("/info/{planId}")
    public ResponseEntity<Map<String, Object>> info(
            @PathVariable("planId") @Parameter(description = "여행 정보를 가져오기 위해 필요한 Plan Id", required = true) int planId) {

        Map<String, Object> resultMap = new HashMap<>();
        HttpStatus status = HttpStatus.OK;
        try {
            log.debug("planId: {}", planId);
            PlanDto plan = planService.getPlan(planId);
            if (plan == null) {
                resultMap.put("message", "No info found for the given plan ID");
            } else {
                resultMap.put("planInfo", plan);
            }

        } catch (Exception e) {
            log.debug("목록 가져오기 에러 발생 : {}", e);
            resultMap.put("message", e.getMessage());
            status = HttpStatus.INTERNAL_SERVER_ERROR;
        }
        return new ResponseEntity<>(resultMap, status);
    }

    @Operation(summary = "플랜의 멤버 리스트 불러오기")
    @GetMapping("/member/{planId}")
    public ResponseEntity<?> getMembersByPlanId(@PathVariable("planId") int planId) {

        try {
            List<PlanMemberDto> planMembers = planService.findMembersByPlanId(planId);
            return ResponseEntity.ok(planMembers);
        } catch (Exception e) {
            return ResponseEntity.internalServerError().build();
        }
    }

    @Operation(summary = "새 플랜 생성하기")
    @PostMapping("")
    public ResponseEntity<?> createPlan(@RequestBody PlanDto plan) {

        try {
            planService.createPlan(plan);
            PlanDto created = planService.getPlan(plan.getPlanId());
            return ResponseEntity.ok(created);
        } catch (Exception e) {
            return ResponseEntity.internalServerError().build();
        }
    }

    @Operation(summary = "플랜 수정하기")
    @PutMapping("")
    public ResponseEntity<?> modifyPlan(@RequestBody PlanDto plan) {

        try {
            planService.modifyPlan(plan);
            PlanDto modified = planService.getPlan(plan.getPlanId());
            return ResponseEntity.ok(modified);
        } catch (Exception e) {
            return ResponseEntity.internalServerError().build();
        }
    }

    @Operation(summary = "플랜 삭제하기")
    @DeleteMapping("")
    public ResponseEntity<?> removePlan(@RequestParam Map<String, String> params) {

        try {
            int result = planService.removePlan(params);
            return ResponseEntity.ok(result);
        } catch (Exception e) {
            log.debug("플랜 삭제 에러 발생 : {}", e.getMessage());
            return ResponseEntity.internalServerError().build();
        }
    }

    @Operation(summary = "플랜의 멤버 삭제하기")
    @DeleteMapping("/member")
    public ResponseEntity<?> removeMember(@RequestParam Map<String, String> params) {

        try {
            planService.removePlanMember(params);
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            log.debug("플랜 멤버 삭제 에러 발생 : {}", e.getMessage());
            return ResponseEntity.internalServerError().build();
        }
    }
}
