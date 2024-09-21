package com.ssafy.travelbox.invitation.model.dao;

import com.ssafy.travelbox.invitation.model.dto.Invitation;
import com.ssafy.travelbox.user.model.dto.UserDto;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

@Mapper
public interface InvitationDao {

    // 초대 요청 시 두 개의 행 추가
    int insertInvitationBoth(Invitation invitation);

    // 초대 거절 시 두 개의 행 삭제
    int deleteInvitationBoth(Map<String, String> param);

    // 받은 초대 리스트 조회
    List<Invitation> selectReceivedInvitations(String receiverId);

    // 초대 수락 시 플랜 멤버에 추가
    int insertPlanMember(Invitation invitation);

    // 초대할 수 있는 멤버 리스트 조회
    List<UserDto> selectUsersNotInvited(int planId);

    UserDto selectUserNotInvited(Map<String, String> param);
}
