<script setup>

import TitleComponent from "@/components/main/TitleComponent.vue";
const title = "News";
</script>

<template>
  <div class="main-div">
    <div class="div-2">
      <TitleComponent :title="title"/>
    </div>
  </div>
</template>

<style scoped>
.main-div{
  display: flex;
  height: 736px;
  flex-direction: column;
  justify-content: center;
  align-items: center;
  gap: 40px;
  flex-shrink: 0;
  padding-top: 50px;
}

.div-2{
  display: flex;
  width: 1493px;
  flex-direction: column;
  gap: 16px;
  flex: 1 0 0;
  padding-top: 100px;
  padding-left: 0px;
}
</style>