import {defineStore, storeToRefs} from "pinia";
import {computed, ref} from "vue";
import {instanceItinerary} from "@/api/api.js";
import {useMapStore} from "@/stores/map.js";
import {itineraryPublish} from "@/components/itinerary/itineraryClient.js";
import {usePlanStore} from "@/stores/plan.js";

export const useItineraryStore = defineStore("itinerary", () => {

    const mapStore = useMapStore();

    const {markerList, visible} = storeToRefs(mapStore);

    const planStore = usePlanStore();

    const itineraryList = ref({});

    const count = computed(() => {
        let count = 0;
        for (const key of Object.keys(itineraryList.value)) {
            count += itineraryList.value[key].length;
        }
        return count;
    });

    const {planInfo} = storeToRefs(planStore);

    const lastDate = computed(() => {
        if (count.value > 0) {
            for (const key of [...Object.keys(itineraryList.value)].reverse()) {
                if (itineraryList.value[key].length > 0) {
                    return key;
                }
            }
        }
        return dateFormat(new Date(planInfo.value.startDate));
    });

    const initItinerary = async () => {
        itineraryList.value = {};
        const {data} = await instanceItinerary.get(`/${planInfo.value.planId}`);

        const startDate = await new Date(planInfo.value.startDate);
        const endDate = await new Date(planInfo.value.endDate);
        const period =  await (endDate.getTime() - startDate.getTime()) / (1000 * 60 * 60 * 24) + 1;
        itineraryList.value[dateFormat(startDate)] = [];
        for (let i = 0; i < period - 1; i++) {
            await startDate.setDate(startDate.getDate() + 1);
            itineraryList.value[dateFormat(startDate)] = [];
        }

        if (data) {
            for (const d of data) {
                await itineraryList.value[d.date].push(d);
            }
            await setMarkerList();
        }
    }

    const setItineraryList = (date, list) => {
        itineraryList.value[date] = list;
    }

    const addItinerary = async (attraction) => {
        // lastDate에 추가
        const itinerary = {
            planId: planInfo.value.planId,
            contentId: attraction.contentId,
            date: lastDate.value,
            indexNumber: count.value > 0 ? itineraryList.value[lastDate.value].length + 1 : 1,
            image: attraction.image,
            title: attraction.title,
            address: attraction.address,
            latitude: attraction.latitude,
            longitude: attraction.longitude,
        }
        const {data} = await instanceItinerary.post(``, JSON.stringify(itinerary));

        if (data) {
            await itineraryList.value[lastDate.value].push(data);
            await setMarkerList();
            await itineraryPublish({
                planId: planInfo.value.planId,
                date: itinerary.date,
                list: itineraryList.value[itinerary.date],
                prevDate: null,
                prevList: null,
            });
        }
    }

    const deleteItinerary = async (itinerary) => {
        const response = await instanceItinerary.delete(``, {params: {planId: itinerary.planId, contentId: itinerary.contentId}})
        if (response?.data) {
            const data = response.data;
            const idx = await itineraryList.value[itinerary.date].findIndex(item => item.planId === data.planId && item.contentId === data.contentId);
            await itineraryList.value[itinerary.date].splice(idx, 1);
            await setMarkerList();
            await itineraryPublish({
                planId: planInfo.value.planId,
                date: itinerary.date,
                list: itineraryList.value[itinerary.date],
                prevDate: null,
                prevList: null,
            });
        } else {
            await console.log("Delete error: ", response);
        }
    }

    const reOrderItinerary = async (date, prevDate) => {
        await itineraryList.value[date].forEach((item, index) => {
            item.indexNumber = index + 1;
        })
        const response = await instanceItinerary.put(``, JSON.stringify(itineraryList.value[date]));
        if (response?.data) {
            await setMarkerList();
            await itineraryPublish({
                planId: planInfo.value.planId,
                date: date,
                list: itineraryList.value[date],
                prevDate: prevDate,
                prevList: itineraryList.value[prevDate],
            });
        } else {
            console.log("ReOrder 실패: ", response);
        }
    }

    const setMarkerList = () => {
        visible.value = false;
        markerList.value = [];
        for (const key of Object.keys(itineraryList.value)) {
            for (const itinerary of itineraryList.value[key]) {
                markerList.value.push(itinerary);
            }
        }
    }

    const mapDayOfWeek = (day) => {
        if (day == 0) {
            return "Sun";
        } else if (day == 1) {
            return "Mon";
        } else if (day == 2) {
            return "Tue";
        } else if (day == 3) {
            return "Wed";
        } else if (day == 4) {
            return "Thr";
        } else if (day == 5) {
            return "Fri";
        } else if (day == 6) {
            return "Sat";
        }
    }

    const mapAttractionType = (attraction) => {
        if (attraction.contentTypeId == 12) {
            return "관광지";
        } else if (attraction.contentTypeId == 14) {
            return "문화시설";
        } else if (attraction.contentTypeId == 15) {
            return "축제공연행사";
        } else if (attraction.contentTypeId == 25) {
            return "여행코스";
        } else if (attraction.contentTypeId == 28) {
            return "레포츠";
        } else if (attraction.contentTypeId == 32) {
            return "숙박";
        } else if (attraction.contentTypeId == 38) {
            return "쇼핑";
        } else if (attraction.contentTypeId == 39) {
            return "음식점";
        }
    }

    const dateFormatDetail = (date) => {
        return date.getFullYear()
            + '.' + ((date.getMonth() + 1) < 9 ? "0" + (date.getMonth() + 1) : (date.getMonth() + 1))
            + '.' + (date.getDate() < 9 ? "0" + date.getDate() : date.getDate())
            + ' (' + mapDayOfWeek([date.getDay()]) + ')';
    }

    const dateFormat = (date) => {
        return date.getFullYear()
            + '-' + ((date.getMonth() + 1) < 9 ? "0" + (date.getMonth() + 1) : (date.getMonth() + 1))
            + '-' + (date.getDate() < 9 ? "0" + date.getDate() : date.getDate());
    }

    return {
        itineraryList,
        initItinerary,
        addItinerary,
        deleteItinerary,
        count,
        dateFormatDetail,
        dateFormat,
        lastDate,
        reOrderItinerary,
        setItineraryList,
        mapAttractionType,
        mapDayOfWeek,
        setMarkerList
    };
})