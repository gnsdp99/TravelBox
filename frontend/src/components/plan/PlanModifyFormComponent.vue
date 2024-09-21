<script setup>
import {useRouter} from "vue-router";
import {ref} from "vue";
import {useUserStore} from "@/stores/user.js";
import {usePlanStore} from "@/stores/plan.js";
import {storeToRefs} from "pinia";


const userStore = useUserStore();
const planStore = usePlanStore();

const {userInfo} = storeToRefs(userStore);
const {planInfo} = storeToRefs(planStore);

const router = useRouter();

const isEmptyPlanName = ref(false);
const isEmptyStartDate = ref(false);
const isEmptyEndDate = ref(false);

const modifyPlan = async () => {
  if (userInfo.value === null) {
    alert("로그인이 필요합니다.");
    router.push({name: "login"});
    return;
  }

  isEmptyPlanName.value = !planInfo.value.planName;
  isEmptyStartDate.value = !planInfo.value.startDate;
  isEmptyEndDate.value = !planInfo.value.endDate;

  if (!isEmptyPlanName.value && !isEmptyStartDate.value && !isEmptyEndDate.value) {
    const data = await planStore.modifyPlan(planInfo.value);
    await console.log(data);
    await router.push({name: "travellist"});
  }
}

const goToList = () => {
  router.push({name: "travellist"});
}

</script>

<template>
  <div class="sign-main-div">
    <div class="sign-up-div">
      <div class="sign-up">Modify Plan</div>
    </div>
    <div class="sign-form-div">
      <div class="username">
        <div class="label-wrapper">
          <div class="label">Travel Titel</div>
        </div>
        <input class="text-field" v-model="planInfo.planName">
        <div class="error-message" v-if="isEmptyPlanName">Enter a plan name</div>
      </div>
      <div class="username">
        <div class="label-wrapper">
          <div class="label">Start Date</div>
        </div>
        <input type="date" class="date-field" v-model="planInfo.startDate" :min="planStore.minDate()" :max="planInfo.endDate">
        <div class="error-message" v-if="isEmptyStartDate">Enter a start date</div>
      </div>
      <div class="username">
        <div class="label-wrapper">
          <div class="label">End Date</div>
        </div>
        <input type="date" class="date-field" v-model="planInfo.endDate" :min="planInfo.startDate ? planStore.maxDate(planInfo.startDate) : planStore.minDate()">
        <div class="error-message" v-if="isEmptyEndDate">Enter a end date</div>
      </div>
      <div class="right-menu-div">
        <div class="signup-button">
          <div class="patient-button">
            <div class="state-layer">
              <button class="sign-up" @click="modifyPlan">Modify</button>
              <br/>
              <br/>
            </div>
          </div>
        </div>
      </div>
      <button class="sign-up" @click="goToList">Go to List</button>
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

.date-field {
  padding: 10px;
  padding-top: 5px;
  font-family: "Noto Serif KR", serif;
  font-size: 15px;
}

.text-field, .date-field {
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
.signup-button {
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