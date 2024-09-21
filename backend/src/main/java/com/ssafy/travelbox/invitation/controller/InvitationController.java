package com.ssafy.travelbox.invitation.controller;

import com.ssafy.travelbox.invitation.model.dto.Invitation;
import com.ssafy.travelbox.invitation.model.service.InvitationService;
import com.ssafy.travelbox.user.model.dto.UserDto;
import com.ssafy.travelbox.user.model.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequiredArgsConstructor
@RequestMapping("/invitation")
@Slf4j
public class InvitationController {

    private final InvitationService invitationService;

    @GetMapping("/{receiverId}")
    public ResponseEntity<?> getReceivedInvitations(@PathVariable("receiverId") String receiverId) {

        List<Invitation> list = invitationService.findReceivedInvitations(receiverId);
        if (list != null && !list.isEmpty()) {
            return ResponseEntity.ok(list);
        } else {
            return ResponseEntity.noContent().build();
        }
    }

    @GetMapping("/user/{planId}")
    public ResponseEntity<?> getUsersNotInvited(@PathVariable("planId") int planId) {

        List<UserDto> list = invitationService.findUsersNotInvited(planId);
        if (list != null && !list.isEmpty()) {
            return ResponseEntity.ok(list);
        } else {
            return ResponseEntity.noContent().build();
        }
    }

    @PostMapping("")
    public ResponseEntity<?> sendInvitation(@RequestBody Invitation invitation) {

        int result = invitationService.createInvitation(invitation);

        if (result == 2) {
            return ResponseEntity.ok(result);
        } else {
            return ResponseEntity.internalServerError().build();
        }
    }

    @DeleteMapping("")
    public ResponseEntity<?> refuseInvitation(@RequestParam Map<String, String> param) {

        int result = invitationService.removeInvitation(param);

        if (result == 2) {
            return ResponseEntity.ok(result);
        } else {
            return ResponseEntity.internalServerError().build();
        }
    }

    @PostMapping("/accept")
    public ResponseEntity<?> acceptInvitation(@RequestBody Invitation invitation) {

        int result = invitationService.addPlanMember(invitation);

        if (result == 1) {
            return ResponseEntity.ok(result);
        } else {
            return ResponseEntity.internalServerError().build();
        }
    }

    @GetMapping("/user")
    public ResponseEntity<?> getUserNotInvited(@RequestParam Map<String, String> param) {

        try {
            UserDto userDto = invitationService.findUserNotInvited(param);
            if (userDto != null) {
                return ResponseEntity.ok(userDto);
            } else {
                return ResponseEntity.noContent().build();
            }
        } catch (Exception e) {
            log.debug("회원 정보 조회 에러: {}", e.getMessage());
            return ResponseEntity.internalServerError().build();
        }
    }
}
