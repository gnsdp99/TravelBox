<script setup>

import TitleComponent from "@/components/main/TitleComponent.vue";
import PopularLocationItemComponent from "@/components/main/popular/PopularLocationItemComponent.vue";

const title = "Popular Destinations"
</script>

<template>
  <div class="main-div">
    <div class="div-2">
      <TitleComponent :title="title"/>
      <div class="item-list">
        <PopularLocationItemComponent/>
        <PopularLocationItemComponent/>
      </div>
    </div>

  </div>
</template>

<style scoped>
.main-div {
  display: flex;
  height: 736px;
  flex-direction: column;
  justify-content: center;
  align-items: center;
  gap: 40px;
  flex-shrink: 0;

}

.div-2 {
  display: flex;
  width: 100%; /* 부모 컨테이너의 너비를 100%로 설정 */
  flex-wrap: wrap; /* 필요에 따라 여러 줄로 나열 */
  gap: 16px; /* 카드 사이의 간격 */
  padding-top: 100px;
  justify-content: center; /* 카드를 가운데 정렬 */
  align-items: flex-start; /* 카드를 상단 정렬 */
}
.item-list {
  align-items: center;
  display: flex;
  width: 60%; /* 부모 컨테이너의 너비를 100%로 설정 */
  height: 500px;
  flex-direction: row; /* 자식 요소들을 가로로 나열 */
  flex-wrap: wrap; /* 필요에 따라 여러 줄로 나열 */
  gap: 16px; /* 카드 사이의 간격 */
  padding-top: 100px;

}


</style>