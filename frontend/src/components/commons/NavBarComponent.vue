
<script setup>
/**
 * 메인 페이지에 있는 네브바. 하얀색 글씨 폰트
 */

</script>

<template>
  <div class="nav-bar">
    <div class="left-menu-div">
      <div class="news">
        <img class="airplane-icon" alt="" src="../../assets/Images/main/airplane-2.svg">
        <RouterLink :to="{name:'chatbot'}" class="login">Chat Bot</RouterLink>
      </div>
      <div class="mypagemenu">
        <img class="airplane-icon" alt="" src="../../assets/Images/main/airplane-2.svg">
        <RouterLink :to="{name:'travellist'}" class="login">Travel List</RouterLink>
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
            <RouterLink :to="{ name: 'signup' }" class="signup">Sign up</RouterLink>
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
  height: 24px;
  overflow: hidden;
  flex-shrink: 0;
}
.news {
  display: flex;
  align-items: center;
  gap: 4px;
}
.mypagemenu {
  display: flex;
  align-items: center;
  gap: 4px;
}
.left-menu-div {
  display: flex;
  align-items: center;
  gap: 28px;
  white-space: nowrap; /* Prevent text from wrapping */
}
.travel-box {
  text-transform: uppercase;
  display: inline-block;
  text-decoration: none; /* Remove underline */
  color: #FFFFFF;
  white-space: nowrap; /* Prevent text from wrapping */
}
.homelogo {
  display: flex;
  align-items: center;
  justify-content: center;
  text-align: center;
  font-size: 30px;
  font-family: 'Alegreya Sans SC';
  white-space: nowrap; /* Prevent text from wrapping */
}
.login {
  font-weight: 600;
  text-decoration: none; /* Remove underline */
  color : #FFFFFF;
}

.signup {
  font-weight: 600;
  text-decoration: none; /* Remove underline */
  color : #121;
}
.div {
  display: flex;
  align-items: center;
}
.state-layer {
  flex: 1;
  display: flex;
  align-items: center;
  justify-content: center;
  padding: 10px 24px;
}
.patient-button {
  border-radius: 8px;
  background-color: #fff;
  height: 48px;
  display: flex;
  align-items: center;
  justify-content: center;
}
.div1 {
  display: flex;
  align-items: center;
  text-align: center;
  color: #112211;
}
.right-menu-div {
  display: flex;
  align-items: center;
  gap: 28px;
  font-size: 18px;
}
.nav-bar {
  width: 100%;
  display: flex;
  align-items: center;
  justify-content: space-between;
  padding: 30px 96px;
  box-sizing: border-box;
  text-align: left;
  font-size: 20px;
  color: #fff;
  font-family: Montserrat;
}

/* 반응형 디자인 */
@media (max-width: 1200px) {
  .nav-bar {
    padding: 13px 40px;
  }

  .left-menu-div, .right-menu-div {
    gap: 15px;
  }

  .homelogo {
    font-size: 25px;
  }

  .news, .mypagemenu {
    gap: 2px;
  }

  .login {
    font-size: 18px;
  }

  .signup {
    font-size: 16px;
  }
}
/* 반응형 디자인 */
@media (max-width: 1200px) {
  .nav-bar {
    padding: 13px 40px;
  }

  .left-menu-div, .right-menu-div {
    gap: 15px;
  }

  .homelogo {
    font-size: 25px;
  }

  .news, .mypagemenu {
    gap: 2px;
  }

  .login {
    font-size: 18px;
  }

  .signup {
    font-size: 16px;
  }
}

@media (max-width: 768px) {
  .nav-bar {
    flex-direction: column;
    align-items: flex-start;
    padding: 10px 20px;
  }

  .left-menu-div, .right-menu-div {
    flex-direction: column;
    align-items: flex-start;
    gap: 10px;
  }

  .homelogo {
    font-size: 22px;
    margin-bottom: 10px;
  }

  .news, .mypagemenu, .div, .div1 {
    width: 100%;
    justify-content: flex-start;
  }
}

@media (max-width: 480px) {
  .nav-bar {
    padding: 10px;
  }

  .homelogo {
    font-size: 18px;
  }

  .left-menu-div, .right-menu-div {
    gap: 5px;
  }

  .news, .mypagemenu, .div, .div1 {
    font-size: 16px;
  }
}
</style>
