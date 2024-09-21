<script setup>
import {ref, watch} from "vue";
import { signup } from "@/api/user";
import router from "@/router/index.js";

const userInfo = ref({
  id: "",
  password: "",
  name :"",
  email : "",
  phoneNumber : ""
})

const passwordCheck = ref(false);
const passwordAgain = ref("");

const idCheck = ref(true);
const emailCheck = ref(false);


// Watcher to check the length of the username
watch(
    () => userInfo.value.id,
    (newVal) => {
      if (newVal.length < 4 || newVal.length > 12) {
        idCheck.value = true;
      } else {
        idCheck.value = false;
      }
    }
);


// Watcher to check if password and passwordAgain match
watch(
    [() => userInfo.value.password, () => passwordAgain.value],
    ([newPassword, newPasswordAgain]) => {
      if (newPassword !== newPasswordAgain) {
        passwordCheck.value = true;
      } else {
        passwordCheck.value = false;
      }
    }
);
// Watcher to check if the email format is valid
watch(
    () => userInfo.value.email,
    (newVal) => {
      const emailPattern = /^[a-zA-Z0-9._-]+@[a-zA-Z0-9.-]+\.[a-zA-Z]{2,6}$/;
      if (!emailPattern.test(newVal)) {
        emailCheck.value = true;
      } else {
        emailCheck.value = false;
      }
    }
);
const signUp = async () => {
  console.log((userInfo.value))
  await signup(
      userInfo.value,
      (response) => {
        if (response.status === 201) { // 상태 코드는 서버에 따라 다를 수 있습니다.
          console.log("Signup success!");
          router.replace({ name: "login" }); // 회원가입 후 로그인 페이지로 이동
        }
      },
      (error) => {
        console.error("Signup failed.", error);
      }
  );
}

</script>

<template>

  <div class="sign-main-div">
    <div class="sign-up-div">
      <div class="sign-up">Sign Up</div>
    </div>
    <div class="sign-form-div">
      <div class="username">
        <div class="label-wrapper">
          <div class="label">username</div>
        </div>
        <input class="text-field" v-model="userInfo.id">
        <div class="error-message" v-if="idCheck">ID must be between 4 and 12 characters.</div>
      </div>
      <div class="username">
        <div class="label-wrapper">
          <div class="label">password</div>
        </div>
        <input type="password" class="text-field" v-model="userInfo.password">
        <div class="label-wrapper">
          <div class="label">password again</div>
        </div>
        <input type="password" class="text-field" v-model="passwordAgain">
        <div class="error-message" v-if="passwordCheck">Password doesn't match</div>
      </div>
      <div class="username">
        <div class="label-wrapper">
          <div class="label">name</div>
        </div>
        <input class="text-field" v-model="userInfo.name">
      </div>
      <div class="username">
        <div class="label-wrapper">
          <div class="label">email</div>
        </div>
        <input type="email" class="text-field" v-model="userInfo.email">
        <div class="error-message" v-if="emailCheck">Invalid email format</div>
      </div>
      <div class="username">
        <div class="label-wrapper">
          <div class="label">phone number</div>
        </div>
        <input type="tel" class="text-field" v-model="userInfo.phoneNumber" placeholder="000-0000-0000">
      </div>
      <div class="right-menu-div">
        <div class="signupbutton">
          <div class="patient-button">
            <div class="state-layer">
              <button class="sign-up" @click="signUp">Sign up</button>
              <br/>
              <br/>
            </div>
          </div>
        </div>
      </div>
    </div>
  </div>
</template>

<style scoped>
.sign-up {
  position: relative;
  font-weight: 600;
}
.sign-up-div {
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
}
.signupbutton {
  box-shadow: 0px 4px 4px rgba(0, 0, 0, 0.25);
  border-radius: 9px;
  background-color: #fff;
  display: flex;
  flex-direction: row;
  align-items: center;
  justify-content: flex-start;
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
.sign-form-div {
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
.sign-main-div {
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


</style>