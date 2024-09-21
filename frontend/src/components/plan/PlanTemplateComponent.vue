<script setup>
import router from "@/router/index.js";

const props = defineProps({
  templateName: {
    type: String,
    required: true
  },
});


</script>


<template>
  <button class="travel-card" >
    <div class="passport-info">{{templateName}}</div>
  </button>

</template>

<style scoped>
.passport-info {
  position: relative;
  font-weight: 500;
}
.travel-card {
  width: 100%;
  position: relative;
  box-shadow: 0px 4px 4px rgba(0, 0, 0, 0.25);
  border-radius: 40px;
  background-color: #fff;
  height: 61px;
  display: flex;
  flex-direction: column;
  align-items: flex-start;
  justify-content: center;
  padding: 13.8px 43px 13.8px 25.9px;
  box-sizing: border-box;
  mix-blend-mode: normal;
  text-align: left;
  font-size: 20px;
  color: #363636;
  font-family: "Montserrat", "Noto Serif KR";
}
button:hover{
  border-color: #5a9;
  box-shadow: 0 0 8px rgba(90, 153, 90, 0.6);
  outline: none;
}

</style>