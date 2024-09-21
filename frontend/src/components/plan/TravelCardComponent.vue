<script setup>

 import {useRouter} from "vue-router";
 import {usePlanStore} from "@/stores/plan.js";
 const props = defineProps({
   plan: Object
 });

 const router = useRouter();
 const planStore = usePlanStore();

 const emit = defineEmits(['click', "deletePlan"])
const handleClick = async (planId) => {
  await emit('click', planId);
}

const modifyPlan = () => {
  router.push({name: "planModify", params: {planId: props.plan.planId}});
}

const deletePlan = async () => {
  if (confirm("정말 삭제하시겠습니까?")) {
    const result = await planStore.deletePlan(props.plan.planId);
    if (result) {
      emit("deletePlan", props.plan.planId);
    }
  }
}
</script>

<template>
  <button class="travel-card"  @click="handleClick(plan.planId)">
    <div class="travel-card-inner">
      <div class="travel-card-img">
        <div class="travel-card-img-inner">
          <img class="frame-child" alt="" src="@/assets/Images/plan/planIcon.png">
        </div>
      </div>
      <div class="trave-card-text">
        <div class="travel-card-title">{{ plan.planName }}</div>
        <div class="travel-card-date">{{ plan.startDate }} - {{ plan.endDate }}</div>
      </div>
      <div class="btns">
        <button class="modify-btn button btnFade btnBlueGreen" @click.stop="modifyPlan">modify</button>
        <button class="delete-btn button btnFade btnBlueGreen" @click.stop="deletePlan">delete</button>
      </div>
    </div>
  </button>
</template>

<style scoped>
.btns {

}

.modify-btn, .delete-btn {
  display: block;
  position: relative;
  float: left;
  width: 80px;
  font-size: 16px;
  padding: 0;
  margin: 10px 20px 10px 0;
  font-weight: 500;
  text-align: center;
  line-height: 40px;
  color: #6a6a6a;
  border-radius: 5px;
  transition: all 0.2s ;
}

.frame-child {
  align-self: stretch;
  flex: 1;
  position: relative;
  border-radius: 6.91px;
  max-width: 100%;
  overflow: hidden;
  max-height: 100%;
  object-fit: cover;
}
.travel-card-img-inner {
  width: 77.8px;
  height: 77.8px;
  display: flex;
  flex-direction: column;
  align-items: center;
  justify-content: flex-start;
}
.travel-card-img {
  width: 77.8px;
  height: 77.8px;
  display: flex;
  flex-direction: column;
  align-items: flex-start;
  justify-content: center;
}
.travel-card-title {
  width: 196px;
  flex: 1;
  position: relative;
  font-weight: 600;
  display: inline-block;
  opacity: 0.7;
}
.travel-card-date {
  position: relative;
  font-size: 15.55px;
  font-weight: 500;
}
.trave-card-text {
  flex: 1;
  height: 50.1px;
  display: flex;
  flex-direction: column;
  align-items: flex-start;
  justify-content: center;
  gap: 6.9px;
}
.travel-card-inner {
  flex: 1;
  display: flex;
  flex-direction: row;
  align-items: center;
  justify-content: center;
  gap: 31.1px;
}
.travel-card {
  width: 100%;
  position: relative;
  box-shadow: 0px 3.455737829208374px 13.82px rgba(17, 34, 17, 0.05);
  border-radius: 13.82px;
  background-color: #fff;
  height: 138.2px;
  display: flex;
  flex-direction: row;
  align-items: center;
  justify-content: center;
  margin: 20px;
  box-sizing: border-box;
  text-align: left;
  font-size: 19.01px;
  color: #112211;
  font-family: Montserrat;
}
button:hover{
  border-color: #5a9;
  box-shadow: 0 0 8px rgba(90, 153, 90, 0.6);
  outline: none;
}

</style>