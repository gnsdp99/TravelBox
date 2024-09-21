<script setup>
import {useItineraryStore} from "@/stores/itinerary.js";
import ListItem from "@/components/itinerary/ListItem.vue";

const props = defineProps({
  "plan": Object,
  "date": String,
  "index": Number,
})

const itineraryStore = useItineraryStore();

const dateObj = new Date(props.date);

const findIndex = (list, item) => {
  return list.findIndex(ele => item.planId === ele.planId && item.contentId === ele.contentId);
}

const startDrag = (e, item) => {
  e.dataTransfer.dropEffect = "move";
  e.dataTransfer.effectAllowed = "move";
  e.dataTransfer.setData("selected", JSON.stringify(item));
}

const drop = (e, item, date) => {
  const selected = JSON.parse(e.dataTransfer.getData("selected"));
  const selectedIdx = findIndex(itineraryStore.itineraryList[selected.date], selected)
  const targetIdx = findIndex(itineraryStore.itineraryList[date], item);

  // 같은 이동은 무시
  if (date === selected.date && selectedIdx === targetIdx) {
    return;
  }

  // 기존 배열에서 삭제
  itineraryStore.itineraryList[selected.date].splice(selectedIdx, 1);
  const prevDate = selected.date;

  // 수정 후 새로운 배열에 추가
  selected.date = date;
  itineraryStore.itineraryList[date].splice(targetIdx, 0, selected);
  itineraryStore.reOrderItinerary(date, prevDate);
}

const dropOnEmpty = (e, date) => {
  // 빈 곳이 아니면 무시 (중복 연산 방지)
  if (itineraryStore.itineraryList[date].length > 0) {
    return;
  }

  const selected = JSON.parse(e.dataTransfer.getData("selected"));
  const selectedIdx = findIndex(itineraryStore.itineraryList[selected.date], selected)

  // 기존 배열에서 삭제
  itineraryStore.itineraryList[selected.date].splice(selectedIdx, 1);
  const prevDate = selected.date;

  // 수정 후 새로운 배열에 추가
  selected.date = date;
  itineraryStore.itineraryList[date].push(selected);
  itineraryStore.reOrderItinerary(date, prevDate);
}
</script>

<template>
  <div class="list"
       @dragenter.prevent
       @dragover.prevent
       @drop.prevent="dropOnEmpty($event, date)"
  >
    <div class="date">Day {{index}} ({{dateObj.getMonth() + 1}}/{{dateObj.getDate()}} {{itineraryStore.mapDayOfWeek(dateObj.getDay())}})</div>
    <ListItem v-for="itinerary in itineraryStore.itineraryList[date]" :key="itinerary.planId + itinerary.contentId"
              :item="itinerary" item-type="itinerary" :plan="plan"
              draggable="true"
              @dragstart="startDrag($event, itinerary)"
              @drop.prevent="drop($event, itinerary, date)"
    />
  </div>
</template>
<style scoped>
.list {
  margin-bottom: 50px;
}

.date {
  border-bottom: 2px solid #3a404b;
  font-size: 18px;
  font-weight: bold;
  margin-bottom: 20px;
  width: 400px;
}

/* Media Queries for responsiveness */
@media (max-width: 1200px) {
  .date {
    width: 350px;
    font-size: 16px;
  }
}

@media (max-width: 992px) {
  .date {
    width: 300px;
    font-size: 14px;
  }
}

@media (max-width: 768px) {
  .date {
    width: 100%;
    font-size: 12px;
    margin-bottom: 15px;
  }
  .list {
    margin-bottom: 30px;
  }
}

@media (max-width: 576px) {
  .date {
    width: 100%;
    font-size: 10px;
    margin-bottom: 10px;
  }
  .list {
    margin-bottom: 20px;
  }
}
</style>
