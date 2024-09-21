package com.ssafy.travelbox.plan.controller;

import com.ssafy.travelbox.plan.model.dto.PassportDto;
import com.ssafy.travelbox.plan.model.service.PlanService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/passport")
@Tag(name = "여행 일정과 관련된 컨트롤러", description = "여행 일정 확인, 새 여행 생성 등 여행 일정과 관련된 내용을 처리하는 클래스")
@Slf4j
public class PassportController {
    private final PlanService planService;

    public PassportController(PlanService planService) {
        this.planService = planService;
    }

    @Operation(summary = "여권정보가져오기", description = "여권정보 가져오기")
    @GetMapping("/{passportId}")
    public ResponseEntity<Map<String, Object>> getPassportInfo(
            @PathVariable("passportId") @Parameter(description = "해당 여행의 여권 템플릿 ID", required = true) int passportId) {

        Map<String, Object> resultMap = new HashMap<>();
        HttpStatus status = HttpStatus.OK;
        try {
            log.debug("passportId: {}", passportId);
            PassportDto info = planService.getPassportInfo(passportId);
            if (passportId == -1) {
                resultMap.put("message", "No passport information found for the given passport Id");
            } else {
                log.debug("info: {}", info);
                resultMap.put("info", info);
            }

        } catch (Exception e) {
            log.debug("목록 가져오기 에러 발생 : {}", e);
            resultMap.put("message", e.getMessage());
            status = HttpStatus.INTERNAL_SERVER_ERROR;
        }
        return new ResponseEntity<>(resultMap, status);
    }
    @Operation(summary = "여권정보 수정하기", description = "여권정보 수정")
    @PutMapping("")
    public ResponseEntity<Map<String, Object>> updatePassportInfo(
            @RequestBody @Parameter(description = "여권정보 수정 내용 저장", required = true) PassportDto passportDto) {

        Map<String, Object> resultMap = new HashMap<>();
        HttpStatus status = HttpStatus.ACCEPTED;
        try {
            log.debug("passportDto: {}", passportDto);

            planService.modifyPassportInfo(passportDto);
            PassportDto newPassportInfo = planService.getPassportInfo(passportDto.getPassportId());

            resultMap.put("passportInfo", newPassportInfo);
            status = HttpStatus.OK;
        } catch (Exception e) {
            log.debug("목록 가져오기 에러 발생 : {}", e);
            resultMap.put("message", e.getMessage());
            status = HttpStatus.INTERNAL_SERVER_ERROR;
        }
        return new ResponseEntity<>(resultMap, status);
    }

    @Operation(summary = "플랜 아이디로 여권 정보 리스트 조회", description = "여권정보가 담겨있는 정보의 아이디를 가져오는 url")
    @GetMapping("list/{planId}")
    public ResponseEntity<Map<String, Object>> getListPassportInfo(
            @PathVariable("planId") @Parameter(description = "해당 여행의 여권 템플릿 ID", required = true) int planId) {

        Map<String, Object> resultMap = new HashMap<>();
        HttpStatus status = HttpStatus.OK;
        try {
            List<PassportDto> list = planService.getPassportByPlanId(planId);
            if (list == null || list.isEmpty()) {
                resultMap.put("message", "No passport information found for the given plan ID");
            } else {
                resultMap.put("list", list);
            }

        } catch (Exception e) {
            log.debug("목록 가져오기 에러 발생 : {}", e);
            resultMap.put("message", e.getMessage());
            status = HttpStatus.INTERNAL_SERVER_ERROR;
        }
        return new ResponseEntity<>(resultMap, status);
    }

    @PostMapping("/{planId}")
    public ResponseEntity<Map<String, Object>> insertPassportInfo(
            @PathVariable("planId") @Parameter(description = "여권정보 수정 내용 저장", required = true) int planId) {
        Map<String, Object> resultMap = new HashMap<>();
        HttpStatus status = HttpStatus.ACCEPTED;
        try {
            log.debug("planId addPassport------------: {}", planId);
            planService.addPassport(planId);
            status = HttpStatus.OK;
        } catch (Exception e) {
            log.debug("목록 가져오기 에러 발생 : {}", e);
            resultMap.put("message", e.getMessage());
            status = HttpStatus.INTERNAL_SERVER_ERROR;
        }
        return new ResponseEntity<>(resultMap, status);
    }


    @DeleteMapping("/{passportId}")
    public ResponseEntity<Map<String, Object>> deletePassportInfo(
            @PathVariable("passportId") @Parameter(description = "여권정보 삭제", required = true) int passportId) {
        Map<String, Object> resultMap = new HashMap<>();
        HttpStatus status = HttpStatus.ACCEPTED;
        try {
            log.debug("passportId : ", passportId);
            planService.deletePassport(passportId);
            status = HttpStatus.OK;
        } catch (Exception e) {
            log.debug("목록 가져오기 에러 발생 : {}", e);
            resultMap.put("message", e.getMessage());
            status = HttpStatus.INTERNAL_SERVER_ERROR;
        }
        return new ResponseEntity<>(resultMap, status);
    }
}
