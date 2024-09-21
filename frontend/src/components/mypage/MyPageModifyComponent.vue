<script setup>


import {storeToRefs} from "pinia";
import {useUserStore} from "@/stores/user.js";
import {modify, deleteUser} from "@/api/user.js";
import router from "@/router/index.js";

const userStore = useUserStore()
const { userInfo, isLogin } = storeToRefs(userStore)

const modifyUserInfo = async () => {
  await modify(
      userInfo.value,
      (response) => {
        if (response.status === 201) { // 상태 코드는 서버에 따라 다를 수 있습니다.
          console.log("update success!");
          router.replace({ name: "mypage" }); // 회원 수정 후 로그인 페이지로 이동
        }
      },
      (error) => {
        console.error("update failed.", error);
      }
  )
}

const deleteUserInfo = async () => {
  const result = confirm("정말 계정을 탈퇴하시겠습니까?");
  if (result) {
    await deleteUser(
        userInfo.value.id,
        (response) => {
          if (response.status === 200) { // 상태 코드는 서버에 따라 다를 수 있습니다.
            console.log("delete success!");
            alert("계정 삭제가 완료되었습니다.");
            isLogin.value = false;
            sessionStorage.removeItem("accessToken");
            sessionStorage.removeItem("refreshToken");
            router.push({name: "main"}); // 회원탈퇴 후 로그인 페이지로 이동
          }
        },
        (error) => {
          alert("계정 삭제에 실패했습니다.");
          console.error("delete failed.", error);
        }
    );
  }
}
</script>

<template>
  <div class="mypage-main-div">
    <div class="mypage-div">
      <div class="mypage-title">My Page</div>
    </div>
    <div class="mypage-form-div">
      <div class="username">
        <div class="label-wrapper">
          <div class="label">username</div>
        </div>
        <input class="text-field" :value="userInfo.id" disabled/>
      </div>
      <div class="username">
        <div class="label-wrapper">
          <div class="label">name</div>
        </div>
        <input class="text-field" v-model="userInfo.name"/>
      </div>
      <div class="username">
        <div class="label-wrapper">
          <div class="label">email</div>
        </div>
        <input class="text-field" :value="userInfo.email" disabled/>
      </div>
      <div class="username">
        <div class="label-wrapper">
          <div class="label">phone number</div>
        </div>
        <input class="text-field" v-model="userInfo.phoneNumber">
      </div>
      <div class="right-menu-div">
        <div class="updatebutton">
          <div class="patient-button">
            <div class="state-layer">
              <button @click="modifyUserInfo"> Update </button>
              <br/>
              <br/>
            </div>
          </div>
        </div>
        <button @click="deleteUserInfo" id="delete-btn"> 회원탈퇴 </button>
      </div>
    </div>
  </div>
  <router-view></router-view>
</template>

<style scoped>
#delete-btn {
  font-family: Montserrat, "Noto Serif KR";
  position: absolute;
  right: 740px;
  color: #760000;
  font-weight: bold;
}

.mypage-title {
  position: relative;
  font-weight: 600;
}
.mypage-div {
  align-self: stretch;
  overflow: hidden;
  display: flex;
  flex-direction: column;
  align-items: center;
  justify-content: center;
  padding: 83px 511px;
}
.label {
  position: absolute;
  top: 0px;
  left: 0px;
}
.label-wrapper {
  align-self: stretch;
  position: relative;
  height: 27px;
}

.text-field {
  align-self: stretch;
  position: relative;
  border-radius: 12px;
  border: 1px solid rgba(102, 102, 102, 0.35);
  box-sizing: border-box;
  height: 56px;
  overflow: hidden;
  flex-shrink: 0;
}
.error-message {
  position: relative;
  font-size: 14px;
  color: #ee1d52;
}
.username {
  width: 428px;
  display: flex;
  flex-direction: column;
  align-items: flex-start;
  justify-content: flex-start;
  gap: 4px;
  font-family: Montserrat;
  background: none;
}
.state-layer {
  align-self: stretch;
  flex: 1;
  display: flex;
  flex-direction: row;
  align-items: center;
  justify-content: center;
  padding: 10px 24px;
  text-decoration: none; /* 밑줄 제거 */
}
.patient-button {
  box-shadow: 0px 4px 4px rgba(0, 0, 0, 0.25);
  border-radius: 8px;
  background-color: #fff;
  height: 48px;
  overflow: hidden;
  display: flex;
  flex-direction: column;
  align-items: center;
  justify-content: center;
  text-decoration: none; /* 밑줄 제거 */
}
.updatebutton {
  box-shadow: 0px 4px 4px rgba(0, 0, 0, 0.25);
  border-radius: 9px;
  background-color: #fff;
  display: flex;
  flex-direction: row;
  align-items: center;
  justify-content: flex-start;
  text-decoration: none; /* 밑줄 제거 */
}
.right-menu-div {
  overflow: hidden;
  display: flex;
  flex-direction: row;
  align-items: center;
  justify-content: center;
  padding: 11px 30px;
  text-align: center;
  font-size: 14px;
  color: #000;
  font-family: Montserrat;
}
.mypage-form-div {
  align-self: stretch;
  overflow: hidden;
  display: flex;
  flex-direction: column;
  align-items: center;
  justify-content: center;
  padding: 0px 100px;
  gap: 18px;
  font-size: 16px;
  color: #666;
  font-family: Poppins;
}
.mypage-main-div {
  width: 100%;
  position: relative;
  height: 1100px;
  display: flex;
  flex-direction: column;
  align-items: center;
  justify-content: center;
  text-align: left;
  font-size: 50px;
  color: #000;
  font-family: Montserrat;
}

input:focus {
  border-color: #5a9;
  box-shadow: 0 0 8px rgba(90, 153, 90, 0.6);
  outline: none;
}
.text-field {
  font-size: 15px;
  padding: 12px;

  font-family: Montserrat;
}
a{
  text-decoration: none; /* 밑줄 제거 */
  color : #1c1b1f;
}
</style>
