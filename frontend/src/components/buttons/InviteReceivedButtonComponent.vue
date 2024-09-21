<script setup>
import {ref} from "vue";
import {instanceInvitation} from "@/api/api.js";
import {useUserStore} from "@/stores/user.js";
import InvitationUserModal from "@/components/invitation/InvitationUserModal.vue";

const invitationList = ref([]);
const isModalOpen = ref(false);

const userStore = useUserStore();

const emit = defineEmits(["accept"]);

const showModal = async () => {
  const {data} = await instanceInvitation.get(`/${userStore.userInfo.id}`);
  invitationList.value = data;
  await console.log("invitation: ", invitationList.value);
  isModalOpen.value = true;
}

const closeModal = () => {
  isModalOpen.value = false;
}

const acceptInvitation = async (invitation) => {
  const result = confirm("정말 요청을 수락하시겠습니까?");
  if (result) {
    const response = await instanceInvitation.post(`/accept`, {
      planId: invitation.planId,
      senderId: invitation.senderId,
      receiverId: invitation.receiverId,
    });
    if (response.data) {
      alert("요청을 수락하였습니다.");
      deleteInvitation(invitation);
      emit("accept", invitation);
    } else {
      alert("요청 수락에 실패하였습니다..");
    }
  } else {
    alert("요청 수락을 취소하였습니다.")
  }
}

const refuseInvitation = async (invitation) => {
  const result = confirm("정말 요청을 거절하시겠습니까?");
  if (result) {
    const response = await instanceInvitation.delete(``, {
      params: {
        planId: invitation.planId,
        senderId: invitation.senderId,
        receiverId: invitation.receiverId,
      },
    });
    if (response.data) {
      alert("요청을 거절하였습니다.");
      deleteInvitation(invitation);
    } else {
      alert("요청 거절에 실패하였습니다.");
    }
  } else {
    alert("요청 거절을 취소하였습니다.")
  }
}

const deleteInvitation = async (invitation) => {
  await invitationList.value.splice(
      invitationList.value.findIndex(item => item.planId === invitation.planId && item.receiverId === invitation.receiverId)
      , 1);
}
</script>

<template>
  <button  @click="showModal">
    <div class="style-layer">
      <img class="paper-plane-icon" alt="" src="../../assets/Images/main/airplane-paper.png">
      <button class="button1">Received Invitation</button>
    </div>
  </button>
  <InvitationUserModal class="modal" :isVisible="isModalOpen" @close="closeModal">
    <h2>Received Invitation List</h2>
    <div class="modal-content" v-for="invitation in invitationList" :key="invitation.invitationId">
      <div class="invitation-list">
        <div class="invitation">
          <span class="plan-name">{{invitation.planName}}</span>
          <span class="sender-id">&nbsp (by {{invitation.senderId}})</span>
        </div>
        <button class="btn accept-btn" type="button" @click="acceptInvitation(invitation)">수락</button>
        <button class="btn refuse-btn" type="button" @click="refuseInvitation(invitation)">거절</button>
      </div>
    </div>
  </InvitationUserModal>
</template>

<style scoped>
.btn {
  font-family: Montserrat, "Noto Serif KR";
}

.paper-plane-icon {
  width: 16px;
  position: relative;
  height: 16px;
  overflow: hidden;
  flex-shrink: 0;
}
.button1 {
  position: relative;
  font-weight: 500;
}
.style-layer {
  border-radius: 4px;
  background-color: #8dd3bb;
  height: 40px;
  display: flex;
  flex-direction: row;
  align-items: center;
  justify-content: center;
  padding: 8px 16px;
  box-sizing: border-box;
  gap: 4px;
}
.button {
  position: relative;
  width: 100%;
  display: flex;
  flex-direction: column;
  align-items: flex-end;
  justify-content: flex-end;
  text-align: left;
  font-size: 15px;
  color: #112211;
  font-family: Montserrat;
}

.modal-content {
  height: 90%;
  border-bottom: 1px solid #ccc;
  margin-bottom: 10px;
}

.invitation-list {
  padding: 10px;
  border-bottom: 1px solid #eee;
  display: flex;
  justify-content: space-around;
  align-items: center;
}

.invitation {
  width: 60%;
}

.accept-btn {
  padding: 10px;
  font-weight: 400;
  text-align: center;
  transition: all 0.2s;
  color: #ffffff;
  background: #000000;
  border-radius: 10px;
}

.accept-btn:hover, .refuse-btn:hover {
  background: #d5d5d5;
  color: black;
}

.refuse-btn {
  padding: 10px;
  font-weight: 400;
  text-align: center;
  transition: all 0.2s;
  color: #ffffff;
  background: #727272;
  border-radius: 10px;
}
</style>