<script setup>
/**
 * 메인 페이지에 있는 네브바. 하얀색 글씨 폰트. 로그인 후 변경된 네브바
 */

import {useUserStore} from "@/stores/user.js";

const userStore = useUserStore()
const {userLogout, userInfo } = userStore

const logout = () => {
  userLogout();
};

</script>

<template>
  <div class="nav-bar">
    <div class="left-menu-div">
      <div class="news">
        <img class="airplane-icon" alt="" src="../../assets/Images/main/airplane-2.svg">
        <RouterLink :to="{name:'chatbot'}" class="logout">Chat Bot</RouterLink>
      </div>
      <div class="mypagemenu">
        <img class="airplane-icon" alt="" src="../../assets/Images/main/airplane-2.svg">
        <RouterLink :to="{name:'travellist'}" class="logout">Travel List</RouterLink>
      </div>
    </div>
    <div class="homelogo">
      <RouterLink :to="{ name: 'main' }" class="travel-box">Travel box</RouterLink>
    </div>
    <div class="right-menu-div">
      <div class="div">
        <RouterLink :to="{name: 'main'}" class="logout" @click.prevent="logout">Logout</RouterLink>
      </div>
      <div class="state-layer">
        <RouterLink :to="{ name: 'mypage' }" class="logout">{{ userInfo.name }}</RouterLink>
      </div>
    </div>
  </div>
  <RouterView/>
</template>

<style scoped>
.airplane-icon {
  width: 24px;
  height: 24px;
  flex-shrink: 0;
}
.news, .mypagemenu {
  display: flex;
  flex-direction: row;
  align-items: center;
  gap: 4px;
}
.my-page {
  font-weight: 600;
}
.left-menu-div, .right-menu-div {
  display: flex;
  flex-direction: row;
  align-items: center;
  gap: 28px;
  font-size: 18px;
}
.travel-box {
  text-transform: uppercase;
  text-decoration: none;
  color: #FFFFFF;
}
.homelogo {
  font-size: 30px;
  font-family: 'Alegreya Sans SC';
  text-align: center;
}
.logout {
  font-weight: 600;
  text-decoration: none;
  color: #FFFFFF;
}
.nav-bar {
  width: 100%;
  display: flex;
  flex-direction: row;
  align-items: center;
  justify-content: space-between;
  padding: 30px 96px;
  box-sizing: border-box;
  font-size: 15px;
  color: #fff;
  font-family: Montserrat;
}
/* Media Queries for responsiveness */
@media (max-width: 1200px) {
  .nav-bar {
    padding: 13px 48px;
  }
  .left-menu-div, .right-menu-div {
    gap: 16px;
  }
  .homelogo {
    font-size: 24px;
  }
}

@media (max-width: 992px) {
  .nav-bar {
    padding: 13px 24px;
  }
  .left-menu-div, .right-menu-div {
    gap: 12px;
  }
  .homelogo {
    font-size: 22px;
  }
}

@media (max-width: 768px) {
  .nav-bar {
    flex-direction: column;
    align-items: flex-start;
    padding: 13px 16px;
  }
  .left-menu-div, .right-menu-div {
    justify-content: space-between;
    width: 100%;
    padding: 8px 0;
  }
  .homelogo {
    padding: 8px 0;
    font-size: 20px;
  }
}

@media (max-width: 576px) {
  .nav-bar {
    padding: 13px 8px;
  }
  .left-menu-div, .right-menu-div {
    flex-direction: column;
    align-items: flex-start;
    width: 100%;
  }
  .homelogo {
    font-size: 18px;
  }
  .airplane-icon {
    width: 20px;
    height: 20px;
  }
}

</style>
