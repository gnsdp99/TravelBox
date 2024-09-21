<script setup>

import NavBarComponentBasicAfterLogin from "@/components/commons/NavBarComponentBasicAfterLogin.vue";
import TitleComponent from "@/components/commons/TitleComponent.vue";
import TravelCardComponent from "@/components/plan/TravelCardComponent.vue";

import {storeToRefs} from "pinia";
import {useUserStore} from "@/stores/user.js";
import {ref, onMounted, computed} from "vue"
import NewPlanButtonComponent from "@/components/buttons/NewPlanButtonComponent.vue";
import router from "@/router/index.js";
import {instancePlan} from "@/api/api.js";
import InviteListButtonComponent from "@/components/buttons/InviteReceivedButtonComponent.vue";

onMounted(() => {
  travelListsFetch();
});

const userStore = useUserStore();
const { userInfo, isLogin,getUserInfo } = storeToRefs(userStore);
const travelLists = ref([]);

const getPlans = async () => {
  const {data} = await instancePlan.get(`/list/${userStore.userInfo.id}`);
  travelLists.value = data.list;
}

const getPlan = async (planId) => {
  const {data} = await instancePlan.get(`/info/${planId}`);
  return data.planInfo;
}

const travelListsFetch = async () => {
  if (isLogin.value) {
    if (!userInfo.value) {
      const token = await sessionStorage.getItem("accessToken");
      if (token) {
        await getUserInfo(token);
      } else {
        console.error("No access token found");
        return;
      }
    }
    await getPlans();

  }
};

const groupedTravelLists = computed(() => {
  const groups = [];
  for (let i = 0; i < travelLists.value.length; i += 3) {
    const group = travelLists.value.slice(i, i + 3);
    while (group.length < 3) {
      group.push({}); // 빈 객체를 추가하여 빈 컴포넌트가 렌더링되도록 함
    }
    groups.push(group);
  }
  return groups;
});

const enterPlan = (planId) => {
  router.push({ name: 'templatelist', params: { planId } });
};

const deletePlan = (planId) => {
  travelLists.value.splice(
      travelLists.value.findIndex(item => item.planId === planId), 1
  );
}

const addPlan = async (invitation) => {
  const plan = await getPlan(invitation.planId);
  await travelLists.value.push(plan);
}

</script>

<template>
  <NavBarComponentBasicAfterLogin/>

  <div class="title-button-div">
    <div class="title-div">
      <TitleComponent title="Your travel list"/>
    </div>
    <div class="button-div">
      <RouterLink :to="{name: 'planCreate'}">
        <NewPlanButtonComponent/>
      </RouterLink>
      <InviteListButtonComponent @accept="addPlan"/>
    </div>

  </div>

  <div class="list-div">
    <template v-for="(group, index) in groupedTravelLists" :key="index">
      <div class="travel-card-parent">
        <TravelCardComponent v-for="(plan, idx) in group" :key="idx" :plan="plan" @click="enterPlan" @delete-plan="deletePlan"/>
      </div>
    </template>
  </div>
</template>

<style scoped>
.list-div {
  position: relative;
  width: 100%;
  display: flex;
  flex-direction: column;
  align-items: center;
  justify-content: center;
  text-align: left;
  font-size: 19.01px;
  color: #112211;
  font-family: Montserrat;
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
  margin-bottom: 20px;
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
  flex-direction: row;
  align-items: flex-end;
  justify-content: center;
  font-size: 16px;
  gap : 10px;
  color: #112211;
}
.travel-card-parent {
  width: 500px;
  height: 138px;
  display: flex;
  flex-direction: row;
  align-items: center;
  justify-content: center;
  gap: 8.6px;
  padding: 15px;
}


@media (max-width: 992px) {
  .title-button-div {
    padding: 0px 60px;
    font-size: 35px;
  }
  .travel-card-parent {
    width: 800px;
    height: auto;
    flex-direction: column;
  }
}

@media (max-width: 768px) {
  .title-button-div {
    padding: 0px 40px;
    font-size: 30px;
  }
  .travel-card-parent {
    width: 100%;
    height: auto;
    flex-direction: column;
  }
}

@media (max-width: 576px) {
  .title-button-div {
    padding: 0px 20px;
    font-size: 25px;
  }
  .travel-card-parent {
    width: 100%;
    height: auto;
    flex-direction: column;
  }
  .button-div {
    font-size: 14px;
  }
}
</style>