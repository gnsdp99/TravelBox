package com.ssafy.travelbox.invitation.model.service;

import com.ssafy.travelbox.invitation.model.dto.Invitation;
import com.ssafy.travelbox.user.model.dto.UserDto;

import java.util.List;
import java.util.Map;

public interface InvitationService {

    int createInvitation(Invitation invitation);

    int removeInvitation(Map<String, String> param);

    List<Invitation> findReceivedInvitations(String receiverId);

    List<UserDto> findUsersNotInvited(int planId);

    int addPlanMember(Invitation invitation);

    UserDto findUserNotInvited(Map<String, String> param);
}
