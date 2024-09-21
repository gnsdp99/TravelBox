<script setup>
/**
 * 메인 페이지 이외에 네브바. 검정색 글씨 폰트
 */

</script>

<template>
  <div class="nav-bar">
    <div class="left-menu-div">
      <div class="news">
        <img class="airplane-icon" alt="" src="../../assets/Images/main/airplane-black.png">
        <RouterLink :to="{name:'chatbot'}" class="login">Chat Bot</RouterLink>
      </div>
      <div class="mypagemenu">
        <img class="airplane-icon" alt="" src="../../assets/Images/main/airplane-black.png">
        <RouterLink :to="{name:'travellist'}" class="my-page">Travel List</RouterLink>
      </div>
    </div>
    <div class="homelogo">
      <RouterLink :to="{ name: 'main' }" class="travel-box">Travel box</RouterLink>
    </div>
    <div class="right-menu-div">
      <div class="div">
        <RouterLink :to="{ name: 'login' }" class="login">Login</RouterLink>
      </div>
      <div class="div1">
        <div class="patient-button">
          <div class="state-layer">
            <RouterLink :to="{ name: 'signup' }" class="login">Sign up</RouterLink>
          </div>
        </div>
      </div>
    </div>
  </div>
  <RouterView/>
</template>

<style scoped>
.airplane-icon {
  width: 24px;
  position: relative;
  height: 24px;
  overflow: hidden;
  flex-shrink: 0;
}
.news {
  width: 87px;
  height: 30px;
  display: flex;
  flex-direction: row;
  align-items: center;
  justify-content: flex-start;
  gap: 4px;
}
.my-page {
  flex: 1;
  position: relative;
  font-weight: 600;
  text-decoration: none !important; /* 밑줄 제거 */
  color : #121 !important;
}
.mypagemenu {
  width: 116px;
  height: 30px;
  display: flex;
  flex-direction: row;
  align-items: center;
  justify-content: flex-start;
  gap: 4px;
  text-decoration: none; /* 밑줄 제거 */
  color : #121;
}
.left-menu-div {
  height: 61px;
  overflow: hidden;
  display: flex;
  flex-direction: row;
  align-items: center;
  justify-content: center;
  padding: 11px 30px;
  box-sizing: border-box;
  gap: 28px;
  font-size: 18px;
  white-space: nowrap; /* Prevent text from wrapping */
}
.travel-box {
  flex: 1;
  position: relative;
  text-transform: uppercase;
  display: inline-block;
  height: 36px;
  text-decoration: none; /* 밑줄 제거 */
  color : #121;
}
.homelogo {
  flex: 1;
  height: 30px;
  display: flex;
  flex-direction: row;
  align-items: center;
  justify-content: center;
  text-align: center;
  font-size: 30px;
  font-family: 'Alegreya Sans SC';
}
.login {
  position: relative;
  font-weight: 600;
  text-decoration: none; /* 밑줄 제거 */
  color : #121;
  white-space: nowrap;
}
.div {
  display: flex;
  flex-direction: row;
  align-items: center;
  justify-content: flex-start;
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
  border-radius: 8px;
  background-color: #FFFFFF;
  height: 48px;
  overflow: hidden;
  display: flex;
  flex-direction: column;
  align-items: center;
  justify-content: center;
}
.div1 {
  display: flex;
  flex-direction: row;
  align-items: center;
  justify-content: flex-start;
  text-align: center;
  color: #112211;
}
.right-menu-div {
  overflow: hidden;
  display: flex;
  flex-direction: row;
  align-items: center;
  justify-content: center;
  padding: 11px 30px;
  gap: 28px;
  font-size: 18px;
}
.nav-bar {
  width: 100%;
  position: relative;
  overflow: hidden;
  display: flex;
  flex-direction: row;
  align-items: center;
  justify-content: space-between;
  padding: 13px 110px;
  box-sizing: border-box;
  text-align: left;
  font-size: 15px;
  color: #121;
  font-family: Montserrat;
}

/* Media Queries for responsiveness */
@media (max-width: 1200px) {
  .nav-bar {
    padding: 13px 60px;
  }
  .left-menu-div, .right-menu-div {
    gap: 16px;
  }
  .homelogo {
    font-size: 26px;
  }
}

@media (max-width: 992px) {
  .nav-bar {
    padding: 13px 30px;
  }
  .left-menu-div, .right-menu-div {
    gap: 12px;
  }
  .homelogo {
    font-size: 24px;
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