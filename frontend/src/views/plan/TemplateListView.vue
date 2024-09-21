<script setup>
/**
 * 여행의 템플릿들이 보이는 뷰
 */
import TitleComponent from "@/components/commons/TitleComponent.vue";
import NavBarComponentBasicAfterLogin from "@/components/commons/NavBarComponentBasicAfterLogin.vue";
import PlanTemplateComponent from "@/components/plan/PlanTemplateComponent.vue";
import {onMounted, ref} from "vue";
import {passport, accomodation, itinerary} from "@/api/plan";
import {useRoute} from "vue-router";
import {httpStatusCode} from "@/util/http-status.js";
import TravelTitleComponent from "@/components/commons/TravelTitleComponent.vue";
import {usePlanStore } from "@/stores/plan.js";
import {storeToRefs} from "pinia";
import ChatComponent from "@/components/chat/ChatComponent.vue";
import {useUserStore} from "@/stores/user.js";
import InviteMemberButtonComponent from "@/components/buttons/InviteMemberButtonComponent.vue";
import router from "@/router/index.js";

const route = useRoute();
const planId = route.params.planId;

const planStore = usePlanStore();
const userStore = useUserStore();
const { planInfo } = storeToRefs(planStore);
const {userInfo} = storeToRefs(userStore);

const passportId= ref(null);
const accomodationId = ref(null);
const itineraryId = ref(null);

const planList = async() => {
  console.log(planId);

  await planStore.getPlanInfo(planId);

  await passport(
      planId,
      async (response) =>{
        if (response.status === httpStatusCode.OK) {
          if(response.data.id != null){
            console.log("get passportInfo success!");
            passportId.value = response.data.id;
            console.log(passportId.value)
          }
        }
      },
      (error) => {
        console.log("passport can't get!");
        console.error(error);
      }
  )
  // accomodation 불러오기
  await accomodation(
      planId,
      async (response) =>{
        if (response.status === httpStatusCode.OK) {
          if(response.data.id != null){
            console.log("get accomodationId success!");
            accomodationId.value = response.data.id;
            console.log(accomodationId.value)
          }
        }
      },
      (error) => {
        console.log("accomodationId can't get!");
        console.error(error);
      }
  )
  // itinerary 불러오기
  await itinerary(
      planId,
      async (response) =>{
        if (response.status === httpStatusCode.OK) {
          if(response.data.id != null){
            console.log("get itineraryId success!");
            itineraryId.value = response.data.id;
            console.log(itineraryId.value)
          }
        }
      },
      (error) => {
        console.log("itineraryId can't get!");
        console.error(error);
      }
  )
}

onMounted(async () => {
  await planStore.getPlanInfo(planId);
})

const passportEnter = () => {
  router.push({ name: 'passport', params: { planId } });
};

const accomodationEnter = () => {
  router.push({ name: 'accomodation', params: { planId } });
};

const itineraryEnter = () => {
  router.push({ name: 'itinerary', params: { planId } });
};
</script>

<template>
  <NavBarComponentBasicAfterLogin/>

  <div class="title-button-div">
    <div class="title-div">
      <TitleComponent title="Information Box"/>
    </div>
    <div class="button-div">
      <InviteMemberButtonComponent />
    </div>
  </div>
  <TravelTitleComponent :title="planInfo?.planName" :startDate="planInfo?.startDate" :endDate="planInfo?.endDate"/>
  <div class="list-div">
    <div class="template-div">
      <div class="travel-card">
        <PlanTemplateComponent template-name="Passport Info (여권정보)" @click="passportEnter"/>
        <PlanTemplateComponent template-name="Accomodation (숙박정보)"  @click="accomodationEnter"/>
        <PlanTemplateComponent template-name="ItineraryId (관광정보)"  @click="itineraryEnter"/>
      </div>
    </div>
    <div class="chat-div">
      <ChatComponent :planId="planId" :sender="userInfo"/>
    </div>
  </div>
  <RouterView/>
</template>

<style scoped>
.template-div {
  align-self: stretch;
  width: 850px;
  display: flex;
  flex-direction: row;
  align-items: start;
  padding-top: 20px;
  padding-right: 50px;
  justify-content: flex-start;
  gap: 20px; /* 카드 간격 추가 */
}
.list-div {
  position: relative;
  width: 100%;
  display: flex;
  flex-direction: row;
  align-items: center;
  justify-content: center;
  text-align: left;
  font-size: 19.01px;
  color: #112211;
  font-family: Montserrat;
}
.chat-div {
  width: 500px;
  position: relative;
}
.title-div {
  align-self: stretch;
  flex: 1;
  position: relative;
}
.button-div {
  align-self: stretch;
  flex: 1;
  display: flex;
  flex-direction: column;
  align-items: flex-end;
  justify-content: center;
  font-size: 16px;
  color: #112211;
}
.title-button-div {
  width: 100%;
  position: relative;
  height: 150px;
  display: flex;
  flex-direction: row;
  align-items: center;
  justify-content: center;
  padding: 0px 120px;
  box-sizing: border-box;
  gap: 49px;
  text-align: left;
  font-size: 45px;
  color: #000;
  font-family: Montserrat;
}
.travel-card {
  width: 100%;
  margin-bottom: 20px; /* 카드 간격 추가 */
  padding: 10px;
  background: #fff;
  border-radius: 10px;
}

/* Media Queries for responsiveness */
@media (max-width: 1200px) {
  .template-div {
    width: 700px;
    padding-right: 30px;
  }
  .chat-div {
    width: 400px;
  }
  .title-button-div {
    padding: 0px 80px;
    font-size: 40px;
  }
}

@media (max-width: 992px) {
  .template-div {
    width: 500px;
    padding-right: 20px;
    flex-direction: column;
  }
  .chat-div {
    width: 100%;
    margin-top: 20px;
  }
  .title-button-div {
    padding: 0px 60px;
    font-size: 35px;
  }
}

@media (max-width: 768px) {
  .template-div {
    width: 100%;
    padding: 0 10px;
    flex-direction: column;
    align-items: center;
  }
  .chat-div {
    width: 100%;
    margin-top: 20px;
  }
  .title-button-div {
    padding: 0px 40px;
    font-size: 30px;
  }
  .button-div {
    align-items: center;
  }
}

@media (max-width: 576px) {
  .template-div {
    width: 100%;
    padding: 0 5px;
    flex-direction: column;
    align-items: center;
  }
  .chat-div {
    width: 100%;
    margin-top: 20px;
  }
  .title-button-div {
    padding: 0px 20px;
    font-size: 25px;
  }
  .button-div {
    font-size: 14px;
    align-items: center;
  }
}
</style>
