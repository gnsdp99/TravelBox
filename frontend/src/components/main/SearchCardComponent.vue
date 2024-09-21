<script setup>

import {computed, ref} from "vue";
import {usePlanStore} from "@/stores/plan.js";
import {useRouter} from "vue-router";
import {useUserStore} from "@/stores/user.js";

const router = useRouter();

const planStore = usePlanStore();

const emit = defineEmits(["created"]);

const userStore = useUserStore();

const newPlan = ref({
  planName: "",
  startDate: "",
  endDate: "",
});

const isEmptyPlanName = ref(false);
const isEmptyStartDate = ref(false);
const isEmptyEndDate = ref(false);

const createPlan = async () => {
  isEmptyPlanName.value = !newPlan.value.planName;
  isEmptyStartDate.value = !newPlan.value.startDate;
  isEmptyEndDate.value = !newPlan.value.endDate;

  if (!isEmptyPlanName.value && !isEmptyStartDate.value && !isEmptyEndDate.value) {
    if (userStore.userInfo === null) {
      alert("로그인이 필요합니다.");
      router.push({name: "login"});
      return;
    }
    const createdPlan = await planStore.createPlan(newPlan.value);
    newPlan.value.planName = "";
    newPlan.value.startDate = "";
    newPlan.value.endDate = "";
    await emit("created", createdPlan);
  }
}
</script>

<template>
  <div class="main-title-div">
    <div class="title-section">
      <b class="travel-box">Travel box</b>
      <div class="main-title-sub">Journey On, and Write Your Story</div>
    </div>
    <div class="main-title-div-inner">
      <div class="search-title-div-parent">
        <div class="search-title-div">
          <img class="airplane-icon" alt="" src="../../assets/Images/main/airplane-black.png">
          <div class="where-would-you">Where would you like to travel?</div>
        </div>
        <div class="search-card-div">
          <div class="input-div">
            <div class="input-name">Plan Name</div>
            <input type="text" class="text-field" v-model="newPlan.planName">
            <div class="error-message" v-if="isEmptyPlanName">Enter a plan name</div>
          </div>
          <div class="input-div">
            <div class="input-name">Start Date</div>
            <input type="date" class="date-field" v-model="newPlan.startDate" :min="planStore.minDate()" :max="newPlan.endDate">
            <div class="error-message" v-if="isEmptyStartDate">Enter a start date</div>
          </div>
          <div class="input-div">
            <div class="input-name">End Date</div>
            <input type="date" class="date-field" v-model="newPlan.endDate" :min="newPlan.startDate ? planStore.maxDate(newPlan.startDate) : planStore.minDate()">
            <div class="error-message" v-if="isEmptyEndDate">Enter a end date</div>
          </div>
        </div>
        <div class="create-new-travel-plan-button">
          <div class="button">
          </div>
          <div class="button1">
            <div class="style-layer">
              <img class="paper-plane-icon" alt="" src="../../assets/Images/main/airplane-paper.png">
              <button class="button2" @click="createPlan">Create New Travel Plan</button>
            </div>
          </div>
        </div>
      </div>
    </div>
  </div>


</template>

<style scoped>
.travel-box {
  width: 1276px;
  position: relative;
  text-transform: uppercase;
  display: inline-block;
}
.main-title-sub {
  align-self: stretch;
  position: relative;
  font-size: 20px;
  font-weight: 600;
  font-family: Montserrat;
}
.title-section {
  align-self: stretch;
  height: 191.5px;
  display: flex;
  flex-direction: column;
  align-items: center;
  justify-content: flex-start;
}
.airplane-icon {
  width: 45px;
  position: relative;
  height: 42px;
  overflow: hidden;
  flex-shrink: 0;
}
.where-would-you {
  position: relative;
  font-weight: 600;
}
.search-title-div {
  align-self: stretch;
  height: 42px;
  display: flex;
  flex-direction: row;
  align-items: center;
  justify-content: flex-start;
  gap: 10px;
}

.error-message {
  position: relative;
  font-size: 14px;
  color: #ee1d52;
  margin-top: 5px;
}

.input-name {
  font-size: 15px;
  margin-bottom: 10px;
}

.date-field {
  font-family: "Noto Serif KR", serif;
}

.text-field {
  font-family: Montserrat;
}

.text-field, .date-field {
  width: 300px;
  align-self: stretch;
  position: relative;
  border-radius: 14px;
  border: 2px solid gray;
  box-sizing: border-box;
  height: 56px;
  background: none;
  color: black;
  font-size: 20px;
  padding: 12px;
}

input:focus {
  border-color: #5a9;
  box-shadow: 0 0 8px rgba(90, 153, 90, 0.6);
  outline: none;
}

.input-div {
  position: relative;
  font-weight: 500;
  font-family: Montserrat;
  font-size: 20px;
  padding: 5px;
}

.search-card-div {
  align-self: stretch;
  height: 50px;
  display: flex;
  flex-direction: row;
  align-items: center;
  justify-content: space-between;
}
.button {
  width: 70px;
  height: 48px;
}
.paper-plane-icon {
  width: 16px;
  position: relative;
  height: 16px;
  overflow: hidden;
  flex-shrink: 0;
}
.button2 {
  position: relative;
  font-weight: 500;
}
.style-layer {
  border-radius: 4px;
  background-color: #8dd3bb;
  height: 48px;
  display: flex;
  flex-direction: row;
  align-items: center;
  justify-content: center;
  padding: 8px 16px;
  box-sizing: border-box;
  gap: 4px;
}
.button1 {
  display: flex;
  flex-direction: column;
  align-items: flex-start;
  justify-content: flex-start;
}
.create-new-travel-plan-button {
  align-self: stretch;
  display: flex;
  flex-direction: row;
  align-items: center;
  justify-content: flex-end;
  gap: 24px;
  font-size: 16px;
}
.search-title-div-parent {
  width: 1116px;
  border-radius: 30px;
  background-color: #fff;
  height: 300px;
  display: flex;
  flex-direction: column;
  align-items: center;
  justify-content: center;
  padding: 0px 51px;
  box-sizing: border-box;
  gap: 50px;
  border: 1px solid #e0e0e0; /* 연한색 테두리 추가 */
}
.main-title-div-inner {
  align-self: stretch;
  height: 288px;
  overflow: hidden;
  flex-shrink: 0;
  display: flex;
  flex-direction: column;
  align-items: center;
  justify-content: center;
  padding: 0px 74px;
  box-sizing: border-box;
  text-align: left;
  font-size: 25px;
  color: #112211;
  font-family: Montserrat;
  margin-top: 340px;
  margin-bottom: 105px;
}
.main-title-div {
  position: relative;
  width: 100%;
  height: 511px;
  display: flex;
  flex-direction: column;
  align-items: center;
  justify-content: space-between;
  text-align: center;
  font-size: 75px;
  color: #fff;
  font-family: 'Alegreya Sans SC';
  margin-top: 236px;
}


/* Media Queries for responsiveness */
@media (max-width: 1200px) {
  .travel-box {
    max-width: 1000px;
  }
  .search-title-div-parent {
    max-width: 900px;
  }
  .text-field {
    max-width: 250px;
  }
}

@media (max-width: 992px) {
  .travel-box {
    max-width: 800px;
  }
  .search-title-div-parent {
    max-width: 700px;
  }
  .text-field {
    max-width: 200px;
  }
  .main-title-div-inner {
    padding: 0px 50px;
  }
  .main-title-div {
    font-size: 60px;
  }
}

@media (max-width: 768px) {
  .travel-box {
    max-width: 600px;
  }
  .search-title-div-parent {
    max-width: 500px;
  }
  .text-field {
    max-width: 150px;
  }
  .main-title-div-inner {
    padding: 0px 30px;
  }
  .main-title-div {
    font-size: 50px;
  }
}

@media (max-width: 576px) {
  .travel-box {
    max-width: 100%;
  }
  .search-title-div-parent {
    max-width: 100%;
  }
  .text-field {
    max-width: 100%;
  }
  .main-title-div-inner {
    padding: 0px 15px;
  }
  .main-title-div {
    font-size: 40px;
  }
}
</style>