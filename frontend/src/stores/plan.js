import { ref } from "vue"
import { defineStore } from "pinia"
import {passportInfoById, planInfoById} from "@/api/plan.js"
import { httpStatusCode } from "@/util/http-status"
import {instancePassport, instancePlan} from "@/api/api.js";
import {useUserStore} from "@/stores/user.js";
import {useRouter} from "vue-router";

export const usePlanStore = defineStore("planStore", () => {
    const passportInfo = ref(null);
    const planInfo = ref({
        endDate: "",
        managerId: "",
        planId: "",
        planName: "",
        registerDate: "",
        startDate: "",
    });
    const passportList = ref([]);

    const router = useRouter();

    const userStore = useUserStore();

    const getPlanInfo = async (planId) => {
        console.log("getPlanInfo called with planId:", planId);
        await planInfoById(
            planId,
            async (response) => {
                if (response.status === httpStatusCode.OK) {
                    if (response.data.planInfo != null) {
                        console.log("get planInfo success!");
                        planInfo.value = response.data.planInfo;
                        console.log("planInfo : ", planInfo.value);
                    } else {
                        console.log("planInfo is null");
                    }
                } else {
                    console.log("Failed to get planInfo, status code:", response.status);
                }
            },
            (error) => {
                console.log("planInfo can't get!");
                console.error(error);
            }
        );
    };

    const getPassportInfo = async (passportId) => {
        console.log("PassportInfo called with passprotId:", passportId);
        await passportInfoById(
            passportId,
            async (response) => {
                if (response.status === httpStatusCode.OK) {
                    if (response.data.info != null) {
                        console.log("get passportInfo success!");
                        passportInfo.value = response.data.info;
                        console.log("passportInfo : ", passportInfo.value);
                    } else {
                        console.log("passportInfo is null");
                    }
                } else {
                    console.log("Failed to get passportInfo, status code:", response.status);
                }
            },
            (error) => {
                console.log("passportInfo can't get!");
                console.error(error);
            }
        );
    };

    const getPassportList = async (planId) => {
        await console.log("getPassportList called!");
        const {data} = await instancePassport.get(`list/${planId}`);
        passportList.value = data.list;
    }

    const addPassport = async (planId) => {
        await console.log("addPassport called!", planId);
        await instancePassport.post(`/${planId}`);
    }


    const createPlan = async (plan) => {
        const {data} = await instancePlan.post(``, {
            planName: plan.planName,
            startDate: plan.startDate,
            endDate: plan.endDate,
            managerId: userStore.userInfo.id,
        });
        return await data;
    }

    const modifyPlan = async (plan) => {
        const {data} = await instancePlan.put(``, {
            planId: plan.planId,
            planName: plan.planName,
            startDate: plan.startDate,
            endDate: plan.endDate
        });
        return await data;
    }

    const deletePlan = async (planId) => {
        const response = await instancePlan.delete(``,{
            params: {
                memberId: userStore.userInfo.id,
                planId,
            }
        });
        if (response.data === 1) {
            await alert("플랜이 삭제되었습니다.");
            return true;
        } else {
            await alert("플랜은 방장만 삭제할 수 있습니다.");
            return false;
        }
    }

    // 입력 날짜 제한 (현재 날짜부터 선택 가능)
    const minDate = () => {
        const today = new Date();
        // 사용자 PC 시간에 관계 없이 한국 시간 가져오기
        const utc = today.getTime() + (today.getTimezoneOffset() * 60 * 1000);
        const KR_TIME_DIFF = 9 * 60 * 60 * 1000;
        const kr_today = new Date(utc + KR_TIME_DIFF);
        const year = kr_today.getFullYear();
        const month = ('0' + (kr_today.getMonth() +  1 )).slice(-2);
        const day = ('0' + kr_today.getDate()).slice(-2);
        return `${year}-${month}-${day}`;
    }
    // 입력 날짜 제한 (start date는 end date보다 앞서야 함)
    const maxDate = (startDate) => {
        const today = new Date(startDate);
        const year = today.getFullYear();
        const month = ('0' + (today.getMonth() +  1 )).slice(-2);
        const day = ('0' + today.getDate()).slice(-2);
        return `${year}-${month}-${day}`;
    };

    return {
        planInfo,
        getPlanInfo,
        createPlan,
        passportInfo,
        getPassportInfo,
        modifyPlan,
        deletePlan,
        getPassportList,
        passportList,
        addPassport,
        minDate,
        maxDate
    };
});