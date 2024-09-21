<script setup>
const props = defineProps({
  "plan": Object,
})

const emit = defineEmits(["click"]);

const onClick = () => {
  emit("click", props.plan);
}
</script>

<template>
  <button class="travel-card" @click="onClick">
    <div class="travel-card-inner">
      <div class="travel-card-img">
        <div class="travel-card-img-inner">
          <img class="frame-child" alt="" src="@/assets/Images/plan/planIcon.png">
        </div>
      </div>
      <div class="trave-card-text">
        <div class="travel-card-title">{{plan.planName}}</div>
        <div class="travel-card-date">{{ plan.startDate }} ~ {{ plan.endDate }}</div>
      </div>
    </div>
  </button>
</template>

<style scoped>
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
  padding: 13.8px 25.9px;
  box-sizing: border-box;
  text-align: left;
  font-size: 19.01px;
  color: #112211;
  font-family: Montserrat;
}


</style>
