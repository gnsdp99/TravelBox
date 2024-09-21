<script setup>
import {useItineraryStore} from "@/stores/itinerary.js";
import {useMapStore} from "@/stores/map.js";

const props = defineProps({
  "item": Object,
  "itemType": String,
  "plan": Object,
})

const itineraryStore = useItineraryStore();
const mapStore = useMapStore();

const addItinerary = async (attraction) => {
  await itineraryStore.addItinerary(attraction);
};

const deleteItinerary = async (itinerary) => {
  await itineraryStore.deleteItinerary(itinerary);
}
</script>

<template>
  <div class="list-item"
       @click="mapStore.moveMap(item, itemType)"
  >
    <img :src="item.image" v-if="item.image">
    <img src="@/assets/Images/common/no-image.jpg" v-else>
    <div class="info">
      <div class="title">
        <span class="item-title">{{item.title}}</span><br>
        <span class="content-type" v-if="itemType === 'attraction'">{{itineraryStore.mapAttractionType(props.item)}}</span>
      </div>
      <div class="address">
        {{item.address}}
      </div>
    </div>
    <button v-if="itemType === 'attraction'" class="add-btn" @click="addItinerary(item)">
      <img src="@/assets/Images/common/plus-icon.png">
    </button>
    <button v-else-if="itemType === 'itinerary'" class="delete-btn" @click="deleteItinerary(item)">
      <img src="@/assets/Images/common/minus-icon.png">
    </button>
  </div>
</template>

<style scoped>
.list-item {
  display: flex;
  justify-content: space-between;
  align-items: center;
  padding: 10px;
  margin-bottom: 20px;
  width: 380px;
  height: 90px;
  border: 2px solid #f3f3f3;
  border-radius: 8px;
  box-shadow: 0 5px 10px rgba(0, 0, 0, 0.10), 0 5px 5px rgba(0, 0, 0, 0.1);
}

.list-item img {
  width: 20%;
  height: 100%;
  object-fit: cover;
  border-radius: 5px;
  margin-right: 10px;
}

.list-item .info {
  flex-grow: 1;
  width: 100%;
  margin: auto 15px
}

.title {
  margin: 10px 0;
}

.item-title {
  font-weight: bold;
  font-size: 15px;
}

.list-item .info .address  {
  margin: 0;
  font-size: 13px;
  color: #484848;
}

.list-item .add-btn, .delete-btn {
  width: 60px;
  height: 40px;
  background: none;
  border: 0;
  cursor: pointer;
  margin: auto 15px
}

.add-btn img, .delete-btn img {
  width: 20px;
  height: 20px;
  background: none;
}

.content-type {
  font-size: 13px;
  color: gray;

}

.info {
  overflow: hidden;
  text-overflow: ellipsis;
  white-space: nowrap;
}
</style>