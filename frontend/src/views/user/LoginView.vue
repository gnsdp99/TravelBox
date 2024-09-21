<script setup>

import NavBarComponent from "@/components/commons/NavBarComponent.vue";
import {ref} from "vue"
import { storeToRefs } from "pinia"
import { useRouter } from "vue-router"
import {useUserStore} from "@/stores/user.js"

const userStore = useUserStore()
const router = useRouter()

const { isLogin } = storeToRefs(userStore)
const { userLogin, getUserInfo } = userStore

const loginUser = ref({
  id: "",
  password: "",
})

const login = async () => {
  await userLogin(loginUser.value)
  let token = sessionStorage.getItem("accessToken")
  console.log(token)
  if (isLogin.value) {
    getUserInfo(token)
    router.replace("/")
  } else {
    isIdNotExist.value = true;
  }
}
const isIdNotExist = ref(false);
</script>
<template>

  <div class="login-real">
    <div class="background-image">
      <div class="overlay">
        <NavBarComponent/>
        <div class="main-div">
          <div class="left">
            <div class="title-div">
              <b class="travel-box">Travel box</b>
              <div class="journey-on-and">Journey On, and Write Your Story</div>
            </div>
          </div>
          <div class="right">
            <div class="login-div">
              <div class="username-div">
                <div class="user-name">user name</div>
                <input type="text" class="text-field" v-model="loginUser.id">
              </div>
              <div class="username-div">
                <div class="user-name">password</div>
                <input type="password" class="text-field" v-model="loginUser.password" @keyup.enter="login">
              </div>
              <div class="error-message" v-if="isIdNotExist">Account not found</div>
              <div class="login-sign-div">
                <button class="label-text" @click="login">Login</button>
              </div>
            </div>
          </div>
        </div>
      </div>
    </div>
  </div>
</template>
<style scoped>
.error-message {
  position: relative;
  font-size: 15px;
  color: #d82f2f;
  font-weight: bold;
}

.travel-box {
  position: relative;
  text-transform: uppercase;
}

.journey-on-and {
  position: relative;
  font-size: 20px;
  font-weight: 600;
  font-family: Montserrat;
}

.title-div {
  flex: 1;
  overflow: hidden;
  display: flex;
  flex-direction: column;
  align-items: center;
  justify-content: flex-start;
  padding: 10px;
  gap: 10px;
}

.left {
  flex: 1;
  height: 248px;
  overflow: hidden;
  display: flex;
  flex-direction: row;
  align-items: flex-start;
  justify-content: center;
  padding: 10px 0px 10px 96px;
  box-sizing: border-box;
}

.user-name {
  position: relative;
  font-weight: 500;
  font-family: Montserrat;
  font-size: 25px;
  padding: 5px;
}

.text-field {
  align-self: stretch;
  font-family: Montserrat;
  position: relative;
  border-radius: 14px;
  border: 4px solid rgba(255, 255, 255, 0.77);
  box-sizing: border-box;
  height: 56px;
  background: none;
  color: white;
  font-size: 23px;
  padding: 12px;
}

input:focus {
  border-color: #5a9;
  box-shadow: 0 0 8px rgba(90, 153, 90, 0.6);
  outline: none;
}

.username-div {
  width: 368px;
  overflow: hidden;
  display: flex;
  flex-direction: column;
  align-items: flex-start;
  justify-content: center;
  gap: 3px;
}

.login {
  width: 61px;
  position: relative;
  font-weight: 600;
  display: inline-block;
  flex-shrink: 0;
}

.label-text {
  flex: 1;
  font-size: 18px;
  position: relative;
  font-weight: 600;
}

.sign-button {
  position: relative;
  border-radius: 10px;
  background-color: #fff;
  height: 48px;
  display: flex;
  flex-direction: row;
  align-items: center;
  justify-content: center;
  padding: 10px 10px;
  box-sizing: border-box;
  text-align: center;
  color: #112211;
  font-family: Montserrat;
}
/*
.login-sign-div {
  overflow: hidden;
  display: flex;
  flex-direction: row;
  align-items: center;
  justify-content: center;
  padding: 0px 77px;
  box-sizing: border-box;
  gap: 39px;
  font-family: Montserrat;
} */
.login-sign-div {
  overflow: hidden;
  width: 428px;
  display: flex;
  flex-direction: column;
  align-items: center;
  justify-content: center;
  padding: 0px 77px;
  gap: 10px;
  font-family: Montserrat;
  background: none;
}

.login-div {
  width: 600px;
  overflow: hidden;
  display: flex;
  flex-direction: column;
  align-items: center;
  justify-content: center;
  padding: 10px 10px 10px 0px;
  box-sizing: border-box;
  gap: 22px;
}

.right {
  flex : 1;
  width: 968px;
  height: 450px;
  overflow: hidden;
  flex-shrink: 0;
  display: flex;
  flex-direction: column;
  align-items: flex-start;
  justify-content: center;
  padding: 10px;
  box-sizing: border-box;
  text-align: left;
  font-size: 25px;
  font-family: Poppins;
}

.main-div {
  position: relative;
  top: 150px;
  left: 0px;
  width: 100%;
  height: 586px;
  overflow: hidden;
  display: flex;
  flex-direction: row;
  align-items: center;
  justify-content: center;
  padding: 10px;
  box-sizing: border-box;
  gap: 10px;
}

.login-real {
  width: 100%;
  background-color: #fff;
  height: 100vh;
  overflow: visible;
  text-align: center;
  font-size: 80px;
  color: #fff;
  font-family: 'Alegreya Sans SC';
  z-index: 0;
  overflow: hidden;
}

NavBarComponent {
  position: relative; /* 네브바를 화면 상단에 고정 */
  z-index: 1000; /* 다른 모든 요소 위에 올라오도록 z-index를 매우 높게 설정 */
}

.background-image {
  position: relative;
  background-image: url('../../assets/Images/main/background.png');
  height: 926px;
  background-position: center;
  background-repeat: no-repeat;
  background-size: cover;
  border-radius: 24px;
  flex-shrink: 0;
  overflow: visible; /* 오버플로우 숨김으로 모서리 둥글게 처리 */
  z-index: 0; /* 배경 이미지 기본 z-index */
}

.overlay {
  position: relative;
  background-image: linear-gradient(to bottom, rgba(0.3, 0.2, 0.6, 0.7) 50%, rgba(0, 0, 0.2, 0.2) 80%), url('../../assets/Images/main/background.png');
  height: 1000px;
  background-position: center;
  background-repeat: no-repeat;
  background-size: cover;
  border-radius: 24px;
  flex-shrink: 0;
  overflow: visible; /* 오버플로우 숨김으로 모서리 둥글게 처리 */
}

/* Media Queries for responsiveness */
@media (max-width: 1200px) {
  .left {
    padding: 10px 0px 10px 50px;
  }
  .right {
    width: 700px;
    height: 400px;
    font-size: 22px;
  }
  .username-div {
    width: 300px;
  }
  .login-div {
    width: 500px;
  }
}

@media (max-width: 992px) {
  .left {
    padding: 10px 0px 10px 30px;
  }
  .right {
    width: 500px;
    height: 350px;
    font-size: 20px;
  }
  .username-div {
    width: 250px;
  }
  .login-div {
    width: 400px;
  }
  .main-div {
    flex-direction: column;
    height: auto;
  }
}

@media (max-width: 768px) {
  .left {
    padding: 10px;
    justify-content: flex-start;
  }
  .right {
    width: 100%;
    height: auto;
    font-size: 18px;
    padding: 10px 0;
  }
  .username-div {
    width: 100%;
  }
  .login-div {
    width: 100%;
    padding: 10px;
  }
  .login-sign-div {
    flex-direction: column;
    padding: 0;
    gap: 20px;
  }
}

@media (max-width: 576px) {
  .left {
    padding: 10px;
    justify-content: flex-start;
  }
  .right {
    width: 100%;
    height: auto;
    font-size: 16px;
    padding: 10px 0;
  }
  .username-div {
    width: 100%;
  }
  .login-div {
    width: 100%;
    padding: 10px;
  }
  .login-sign-div {
    flex-direction: column;
    padding: 0;
    gap: 20px;
  }
  .sign-button {
    width: 100%;
  }
}
</style>
