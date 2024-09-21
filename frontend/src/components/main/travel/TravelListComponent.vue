<script setup>

import TravelListItemComponent from "@/components/main/travel/TravelListItemComponent.vue";
import axios from "axios";
import TitleComponent from "@/components/main/TitleComponent.vue";

const travelList = axios.get('/travel/list'); // get으로 리스트 정보 가져오기
const check = () => {
  console.log("check button")
}
const title = "Your travel list";

</script>

<template>
  <div class="main-div">
    <div class="div-2">
      <TitleComponent :title="title"/>

      <div class="list-div">
<!--        <TravelListItemComponent-->
<!--            v-for="(item, index) in travelList.slice(0, 9)"-->
<!--            :key="index"-->
<!--            :destination="item.destination"-->
<!--            :date="item.date"-->
<!--            :image="item.image"-->
<!--        />-->
        <TravelListItemComponent/>
        <TravelListItemComponent/>
        <TravelListItemComponent/>
      </div>
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