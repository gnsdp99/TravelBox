import axios from "axios";

const { VITE_VUE_API_URL } = import.meta.env;

const instanceAttraction = axios.create({
    baseURL: VITE_VUE_API_URL + "/travelbox/attraction",
    headers: {
        "Content-Type": "application/json;charset=utf-8",
    },
})

const instanceBasic = axios.create({
    baseURL: VITE_VUE_API_URL + "/travelbox",
    headers: {
        "Content-Type": "application/json;charset=utf-8",
    },
})

const instanceItinerary = axios.create({
    baseURL: VITE_VUE_API_URL + "/travelbox/itinerary",
    headers: {
        "Content-Type": "application/json;charset=utf-8",
    },
})

const instanceChat = axios.create({
    baseURL: VITE_VUE_API_URL + "/travelbox/chat",
    headers: {
        "Content-Type": "application/json;charset=utf-8",
    },
})

const instanceInvitation = axios.create({
    baseURL: VITE_VUE_API_URL + "/travelbox/invitation",
    headers: {
        "Content-Type": "application/json;charset=utf-8",
    },
})

const instancePlan = axios.create({
    baseURL: VITE_VUE_API_URL + "/travelbox/plan",
    headers: {
        "Content-Type": "application/json;charset=utf-8",
    },
})

const instancePassport = axios.create({
    baseURL: VITE_VUE_API_URL + "/travelbox/passport",
    headers: {
        "Content-Type": "application/json;charset=utf-8",
    },
})

export {
    instanceAttraction,
    instanceItinerary,
    instanceChat,
    instanceInvitation,
    instancePlan,
    instancePassport,
    instanceBasic
};