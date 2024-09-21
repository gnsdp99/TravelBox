<script setup>


import {storeToRefs} from "pinia";
import {useUserStore} from "@/stores/user.js";

const userStore = useUserStore()
const { userInfo  } = storeToRefs(userStore)

</script>

<template >
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
        <input class="text-field" :value="userInfo.name" disabled/>
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
        <input class="text-field" :value="userInfo.phoneNumber" disabled/>
      </div>
      <div class="right-menu-div">
        <div class="updatebutton">
          <div class="patient-button">
            <div class="state-layer">
              <RouterLink :to="{ name: 'mypageModify' }" >Modify </RouterLink>
              <br/>
              <br/>
            </div>
          </div>
        </div>
      </div>
    </div>
  </div>
  <router-view></router-view>
</template>

<style scoped>
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
