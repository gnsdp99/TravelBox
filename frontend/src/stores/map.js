import { defineStore } from 'pinia'
import {ref} from "vue";

export const useMapStore = defineStore('map', () => {

    // const lat = ref(,); // 관광지 리스트에서 클릭한 관광지의 위도
    // const lng = ref(); // 관광지 리스트에서 클릭한 관광지의 경도

    const curMarker = ref({
        title: "",
        latitude: "37.50133467520475",
        longitude: "127.03981956361396",
        address: "",
        image: "",
    });

    const markerList = ref([]); // 추가한 관광지 마커 리스트

    const map = ref(null); // 지도 객체
    
    const isInit = ref(true); // 첫 화면에 대한 여부

    const visible = ref(false); // 오버레이 렌더링 여부

    const isAttraction = ref(false); // 관광지 리스트에서 클릭한 것인지에 대한 여부

    const loadKakaoMap = (mapRef) => { // 지도 로딩 시 첫 화면
        map.value = mapRef;
        map.value.setLevel(10);
    };
    const moveMap = (item, itemType) => {
        // 관광지 클릭 시 해당 위치로 이동
        // lat.value = item.latitude;
        // lng.value = item.longitude;
        curMarker.value = item;
        // curMarker.value.latitude = item.latitude;
        // curMarker.value.longitude = item.longitude;
        isInit.value = false;
        visible.value = false;
        if (itemType === "attraction") {
            isAttraction.value = true;
        } else {
            isAttraction.value = false;
        }
    };

    return {
        isInit,
        moveMap,
        // lat,
        // lng,
        curMarker,
        loadKakaoMap,
        markerList,
        visible,
        isAttraction
    };
})