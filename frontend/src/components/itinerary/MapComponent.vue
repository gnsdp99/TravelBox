<script setup>
import {KakaoMap, KakaoMapCustomOverlay, KakaoMapMarker, KakaoMapPolyline} from 'vue3-kakao-maps';
import {useMapStore} from "@/stores/map.js";
import {storeToRefs} from "pinia";
import {ref, watch} from "vue";

const mapStore = useMapStore();

const {markerList, visible, isAttraction, curMarker} = storeToRefs(mapStore);

const latLngList = ref([]);

watch(markerList, () => {
  latLngList.value = [];
  for (const marker of markerList.value) {
    latLngList.value.push({
      lat: marker.latitude,
      lng: marker.longitude,
    });
  }
})

const closeOverlay = () => {
  visible.value = false;
};

const controlOverLay = (marker) => {
  visible.value = !visible.value;
  if (marker) {
    curMarker.value = marker;
  }
}
</script>

<template>
  <KakaoMap :lat="curMarker.latitude" :lng="curMarker.longitude"
            @onLoadKakaoMap="mapStore.loadKakaoMap"
  >
    <KakaoMapPolyline :latLngList="latLngList" />
    <KakaoMapMarker v-for="marker in markerList" :key="marker"
                    :image="{imageSrc: '/src/assets/Images/map/marker-selected.png', imageWidth: 36, imageHeight: 36, imageOption: {}}"
                    :lat="marker.latitude"
                    :lng="marker.longitude"
                    @onClickKakaoMapMarker="controlOverLay(marker)"
                    :clickable="true"
    >
    </KakaoMapMarker>

    <KakaoMapMarker v-if="isAttraction" :lat="curMarker.latitude" :lng="curMarker.longitude"
                    :image="{imageSrc: '/src/assets/Images/map/marker-not-selected.png', imageWidth: 36, imageHeight: 36, imageOption: {}}"
                    @onClickKakaoMapMarker="controlOverLay()"
                    :clickable="true"
    >
    </KakaoMapMarker>

    <KakaoMapCustomOverlay :lat="curMarker.latitude"
                           :lng="curMarker.longitude"
                           :yAnchor="1.4"
                           :visible="visible"
                           style="z-index: 2;"
    >
      <div style="padding: 10px; background-color: white; border: 1px solid #ccc; border-radius: 5px">
        <div style="font-weight: bold; margin-bottom: 5px">
          {{  curMarker.title }}
          <span style="float: right; cursor: pointer" @click="closeOverlay" title="닫기">X</span>
        </div>
        <div style="display: flex">
          <div style="margin-right: 10px">
            <img :src="curMarker.image ? curMarker.image : '/src/assets/Images/common/no-image.jpg'" width="73" height="70" />
          </div>
          <div class="item-overlay" style="display: flex; flex-direction: column; align-items: flex-start">
            <div style="overflow: hidden; text-overflow: ellipsis; white-space: nowrap; margin-bottom: 10px; padding-top: 5px">({{ curMarker.address }})</div>
            <div style="text-overflow: clip; white-space: nowrap;">{{ curMarker.overview }}</div>
          </div>
        </div>
      </div>
    </KakaoMapCustomOverlay>
  </KakaoMap>
</template>

<style scoped>
.item-overlay {
  width: 300px;
  overflow-x: scroll;
}

.item-overlay::-webkit-scrollbar {
  height: 10px;
}

.item-overlay::-webkit-scrollbar-thumb {
  background: #7b7b7b; /* 스크롤바 막대 색상 */
  border: 2px solid #ffffff;  /* 스크롤바 막대 테두리 설정  */
  border-radius: 6px 6px 6px 6px;
  min-width: 100px; /* 스크롤바 막대 최소 높이 */
}

.item-overlay::-webkit-scrollbar-thumb:hover {
  background-color: #6d6d6d;
}

.item-overlay::-webkit-scrollbar-track {
  /*스크롤바 뒷 배경 색상*/
  /*background: rgb(236, 236, 236);
  border-radius: 6px 6px 6px 6px;*/
}
</style>