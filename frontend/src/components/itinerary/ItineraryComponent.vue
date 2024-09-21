<script setup>
import ItineraryListComponent from "@/components/itinerary/ItineraryListComponent.vue";
import AttractionListComponent from "@/components/itinerary/AttractionListComponent.vue";
import MapComponent from "@/components/itinerary/MapComponent.vue";
import {onMounted, onUnmounted, watch} from "vue";
import {useItineraryStore} from "@/stores/itinerary.js";
import {itineraryConnect, itineraryDisconnect, itineraryMessage} from "@/components/itinerary/itineraryClient.js";
import {usePlanStore} from "@/stores/plan.js";
import {useRoute} from "vue-router";
import TravelTitleComponent from "@/components/commons/TravelTitleComponent.vue";
import {storeToRefs} from "pinia";
import TitleComponent from "@/components/commons/TitleComponent.vue";
import NavBarComponentBasicAfterLogin from "@/components/commons/NavBarComponentBasicAfterLogin.vue";

const itineraryStore = useItineraryStore();
const planStore = usePlanStore();
const route = useRoute();

const {planInfo} = storeToRefs(planStore);

onMounted(async () => {
  await planStore.getPlanInfo(route.params.planId);
  await itineraryStore.initItinerary();
  await itineraryConnect(planInfo.value.planId);
})

onUnmounted(() => {
  itineraryDisconnect();
})

watch(itineraryMessage, async (data) => {
  // 순서 변경된 리스트 재배열
  await itineraryStore.setItineraryList(data.date, data.list);
  if (data.prevDate && data.date !== data.prevDate) {
    await itineraryStore.setItineraryList(data.prevDate, data.prevList);
  }
  await itineraryStore.setMarkerList();
})
</script>

<template>
  <NavBarComponentBasicAfterLogin/>

  <div class="component-container">
    <div class="title-button-div">
      <div class="title-div">
        <TitleComponent title="Itinerary Information"/>
      </div>
    </div>
    <TravelTitleComponent :title="planInfo?.planName" :startDate="planInfo?.startDate" :endDate="planInfo?.endDate"/>
    <div class="container">
      <div class="attraction">
        <AttractionListComponent :plan="planInfo"/>
      </div>
      <div class="itinerary">
        <div class="frame-parent">
          <h3 class="count">Number of Itinerary: {{itineraryStore.count}}</h3>
          <div class="itinerary-list-parent">
            <div class="itinerary-list">
              <ItineraryListComponent :plan="planInfo" v-for="(date, index) in Object.keys(itineraryStore.itineraryList)" :key="date" :date="date" :index="index + 1" class="itinerary-list"/>
            </div>
          </div>
        </div>
      </div>
      <div class="map">
        <MapComponent width="80vh" height="100vh"/>
      </div>
    </div>
  </div>
</template>

<style scoped>
.component-container {
  font-family: Montserrat, "Noto Serif KR";
}
.container {
  width: 100%;
  position: relative;
  display: flex;
  flex-direction: row;
  justify-content: center;
  gap: 30px;
  box-sizing: border-box;
  font-size: 15px;
  color: #3a404b;
}

.attraction {
  margin: 0 10px;
  border: 2px solid #f3f3f3;
  border-radius: 8px;
  box-shadow: 0 5px 10px rgba(0, 0, 0, 0.10), 0 5px 5px rgba(0, 0, 0, 0.1);
  padding: 15px;
}

.itinerary {
  border: 2px solid #f3f3f3;
  border-radius: 8px;
  box-shadow: 0 5px 10px rgba(0, 0, 0, 0.10), 0 5px 5px rgba(0, 0, 0, 0.1);
  padding: 15px;
}

.map {
  border: 2px solid #f3f3f3;
  border-radius: 8px;
  box-shadow: 0 5px 10px rgba(0, 0, 0, 0.10), 0 5px 5px rgba(0, 0, 0, 0.1);
  padding: 10px;
}

.itinerary-list-parent {
  width: 430px;
  height: 800px;
  overflow-y: scroll;
}

.itinerary-list-parent::-webkit-scrollbar {
  width: 10px;
}

.itinerary-list-parent::-webkit-scrollbar-thumb {
  background: #7b7b7b; /* 스크롤바 막대 색상 */
  border: 2px solid #ffffff;  /* 스크롤바 막대 테두리 설정  */
  border-radius: 6px 6px 6px 6px;
  min-height: 50px; /* 스크롤바 막대 최소 높이 */
}

.itinerary-list-parent::-webkit-scrollbar-thumb:hover {
  background-color: #6d6d6d;
}

.itinerary-list::-webkit-scrollbar-track {
  background: rgb(236, 236, 236);  /*스크롤바 뒷 배경 색상*/
  border-radius: 6px 6px 6px 6px;
}

.count {
  margin-bottom: 40px;
}

.title-div {
  align-self: stretch;
  flex: 1;
  position: relative;
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
}

</style>
