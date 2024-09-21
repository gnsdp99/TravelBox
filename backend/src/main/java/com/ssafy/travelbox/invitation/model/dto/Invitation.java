package com.ssafy.travelbox.invitation.model.dto;

import lombok.Data;

@Data
public class Invitation {

    private int planId;
    private String planName;
    private String senderId;
    private String receiverId;
    private boolean isAccepted;
}
