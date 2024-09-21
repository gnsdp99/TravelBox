package com.ssafy.travelbox.invitation.model.service;

import com.ssafy.travelbox.invitation.model.dao.InvitationDao;
import com.ssafy.travelbox.invitation.model.dto.Invitation;
import com.ssafy.travelbox.user.model.dto.UserDto;
import com.ssafy.travelbox.user.model.mapper.UserMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class InvitationServiceImpl implements InvitationService {

    private final InvitationDao invitationDao;

    @Override
    public int createInvitation(Invitation invitation) {
        return invitationDao.insertInvitationBoth(invitation);
    }

    @Override
    public int removeInvitation(Map<String, String> param) {
        return invitationDao.deleteInvitationBoth(param);
    }

    @Override
    public List<Invitation> findReceivedInvitations(String receiverId) {
        return invitationDao.selectReceivedInvitations(receiverId);
    }

    @Override
    public List<UserDto> findUsersNotInvited(int planId) {
        return invitationDao.selectUsersNotInvited(planId);
    }

    @Override
    public int addPlanMember(Invitation invitation) {

        Map<String, String> param = Map.of(
                "planId", invitation.getPlanId() + "",
                "senderId", invitation.getSenderId() + "",
                "receiverId", invitation.getReceiverId() + ""
        );

        int result = invitationDao.deleteInvitationBoth(param);
        if (result == 2) {
            return invitationDao.insertPlanMember(invitation);
        } else {
            return 0;
        }
    }

    @Override
    public UserDto findUserNotInvited(Map<String, String> param) {
        return invitationDao.selectUserNotInvited(param);
    }
}
