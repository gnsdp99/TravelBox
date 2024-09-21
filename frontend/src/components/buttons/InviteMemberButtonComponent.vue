<script setup>
import {instanceInvitation} from "@/api/api.js";
import {usePlanStore} from "@/stores/plan.js";
import {ref} from "vue";
import {useUserStore} from "@/stores/user.js";
import InvitationUserModal from "@/components/invitation/InvitationUserModal.vue";

const planStore = usePlanStore();
const userStore = useUserStore();

// const usersNotInvited = ref([]);
const searchId = ref("");
const searchedUser = ref(null);
const isModalOpen = ref(false);
const noMessage = ref("");

const showModal = async () => {
  // const {data} = await instanceInvitation.get(`/user/${planStore.planInfo.planId}`)
  // usersNotInvited.value = data;
  isModalOpen.value = true;
}

const closeModal = () => {
  isModalOpen.value = false;
}

const sendInvitation = async () => {
  const result = confirm("정말 요청을 보내시겠습니까?");
  if (result) {
    const response = await instanceInvitation.post(``, {
      planId: planStore.planInfo.planId,
      senderId: userStore.userInfo.id,
      receiverId: searchedUser.value.id
    });
    if (response.data) {
      alert("요청에 성공하였습니다.");
    } else {
      alert("요청에 실패하였습니다.");
    }
    await clearSearch();
    searchedUser.value = null;
  } else {
    alert("요청을 취소하였습니다.");
  }
}

// const removeInvited = async (user) => {
//   await usersNotInvited.value.splice(
//       usersNotInvited.value.findIndex(item => item.id === user.id), 1
//   );
// }

const searchUser = async () => {
  const response = await instanceInvitation.get(`/user`, {
    params: {
      userId: searchId.value,
      planId: planStore.planInfo.planId,
    }
  });
  if (response?.data) {
    searchedUser.value = response.data;
    noMessage.value = "";
  } else {
    searchedUser.value = null;
    noMessage.value = "User not found";
  }
}

const clearSearch = () => {
  searchId.value = "";
}
</script>

<template>
  <button class="button" @click="showModal">
    <div class="style-layer">
      <img class="paper-plane-icon" alt="" src="@/assets/Images/main/airplane-paper.png">
      <button class="button1">Invite New Member</button>
    </div>
  </button>
  <InvitationUserModal class="modal" :isVisible="isModalOpen" @close="closeModal">
    <h2>Invite New Member</h2>
    <div class="search">
      <input class="search-input" type="text" placeholder="Enter friend's id" v-model="searchId" @keyup.enter="searchUser">
      <button class="clear-btn" v-show="searchId" @click="clearSearch">X</button>
      <button class="search-btn" @click="searchUser">
        <img src="@/assets/Images/common/search-icon.png" />
      </button>
    </div>
    <div class="modal-content">
      <div class="user-list" v-if="searchedUser">
        <span class="user" >{{searchedUser.name}} ({{searchedUser.id}})</span>
        <button class="invite-btn" type="button" @click="sendInvitation">초대</button>
      </div>
      <div class="user-list" v-if="noMessage">
        <span class="user">{{noMessage}}</span>
      </div>
    </div>
  </InvitationUserModal>
</template>

<style scoped>
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
  display: flex;
  flex-direction: column;
  align-items: flex-end;
  justify-content: flex-end;
  text-align: left;
  font-size: 15px;
  padding-right : 100px;
  color: #112211;
  font-family: Montserrat;
}

.search {
  display: flex;
  width: 380px;
  height: 45px;
  border: 2px solid #f3f3f3;
  border-radius: 8px;
  box-shadow: 0 5px 10px rgba(0, 0, 0, 0.10), 0 5px 5px rgba(0, 0, 0, 0.1);
  margin-bottom: 20px;
}

.invite-btn, .search-input {
  font-family: Montserrat, "Noto Serif KR";
}

.search .search-input {
  width: 100%;
  border: 0;
  padding: 0 15px;
  outline: none;
  font-size: 15px;
}

.search .search-btn {
  margin-right: 10px;
  margin-top: 3px;
  border: 0;
  background: none;
  cursor: pointer;
}

.search .clear-btn {
  margin-right: 10px;
  border: 0;
  background: none;
  cursor: pointer;
}

.search-btn img {
  width: 20px;
  height: 20px;
}

.modal-content {
  height: 90%;
  border-bottom: 1px solid #ccc;
  margin-bottom: 10px;
}

.user-list {
  padding: 10px;
  border-bottom: 1px solid #eee;
  display: flex;
  justify-content: space-around;
  align-items: center;
}

.invite-btn {
  padding: 10px;
  font-weight: 400;
  text-align: center;
  transition: all 0.2s;
  color: #ffffff;
  background: #000000;
  border-radius: 10px;
}

.invite-btn:hover {
  background: #d5d5d5;
  color: black;
}
</style>