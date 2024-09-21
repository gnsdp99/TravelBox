<script setup>
import {useItineraryStore} from "@/stores/itinerary.js";

const props = defineProps({
  "plan": Object,
})

const itineraryStore = useItineraryStore();

const startDate = itineraryStore.dateFormatDetail(new Date(props.plan.startDate));
const endDate = itineraryStore.dateFormatDetail(new Date(props.plan.endDate));

</script>

<template>
  <div class="header">
    <div class="title">{{plan.planName}}</div>
    <div class="period">{{ startDate }}{{}} ~ {{ endDate }}</div>
  </div>
</template>

<style scoped>
.header {
  padding: 10px;
}

.header .title {
  font-size: 35px;
  font-weight: bold;
  margin: 0;
  margin-bottom: 15px;
}

.header .period {
  margin: 5px 0;
  color: #777;
  font-size: 16px;
}
</style>