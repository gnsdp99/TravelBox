<script setup>

import {ref} from "vue";
import {instanceAttraction} from "@/api/api.js";
import ListItem from "@/components/itinerary/ListItem.vue";

defineProps({
  "plan": Object,
})

const attractionList = ref([]);
const title = ref("");

const showList = async () => {
  if (title.value) {
    const response = await instanceAttraction.get(`${title.value}`);
    if (response?.data) {
      attractionList.value = await response.data;
    } else {
      await console.log("관광지 조회 에러: ", response);
    }
    title.value = "";
  }
};

const clearSearch = () => {
  title.value = "";
}
</script>

<template>
  <div class="attraction-list">
    <div class="search">
      <input class="search-input" type="text" placeholder="Enter a Attraction..." v-model="title" @keyup.enter="showList(title)">
      <button class="clear-btn" v-show="title" @click="clearSearch">X</button>
      <button class="search-btn" @click="showList(title)">
        <img src="@/assets/Images/common/search-icon.png" />
      </button>
    </div>
    <div class="list">
      <ListItem v-for="attraction in attractionList" :key="attraction.contentId"
                :item="attraction" item-type="attraction" :plan="plan"
      />
    </div>
  </div>
</template>

<style scoped>
.search-input {
  font-family: Montserrat, "Noto Serif KR";
}

.list {
  width: 430px;
  height: 800px;
  overflow-y: scroll;
}

.list::-webkit-scrollbar {
  width: 10px;
}

.list::-webkit-scrollbar-thumb {
  background: #7b7b7b; /* 스크롤바 막대 색상 */
  border: 2px solid #ffffff;  /* 스크롤바 막대 테두리 설정  */
  border-radius: 6px 6px 6px 6px;
  min-height: 50px; /* 스크롤바 막대 최소 높이 */
}

.list::-webkit-scrollbar-thumb:hover {
  background-color: #6d6d6d;
}

.list::-webkit-scrollbar-track {
  /*스크롤바 뒷 배경 색상*/
  /*background: rgb(236, 236, 236);
  border-radius: 6px 6px 6px 6px;*/
}

.search {
  display: flex;
  width: 400px;
  height: 45px;
  border: 2px solid #f3f3f3;
  border-radius: 8px;
  box-shadow: 0 5px 10px rgba(0, 0, 0, 0.10), 0 5px 5px rgba(0, 0, 0, 0.1);
  margin-bottom: 20px;
}
.search .search-input {
  width: 100%;
  border: 0;
  padding: 0 15px;
  outline: none;
}

.search .search-btn {
  margin-right: 10px;
  margin-top: 3px;
  border: 0;
  background: none;
  cursor: pointer;
}

.search .clear-btn {
  margin-right: 10px;
  border: 0;
  background: none;
  cursor: pointer;
}

.search-btn img {
  width: 20px;
  height: 20px;
}
</style>