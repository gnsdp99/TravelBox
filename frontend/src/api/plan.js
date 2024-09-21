import { localAxios } from "@/util/http-commons";

// localAxios 인스턴스를 생성합니다.
const local = localAxios();

async function list(userid, success, fail) {
    await local.get(`/travelbox/plan/list/${userid}`).then(success).catch(fail);
}
async function passport(planId, success, fail) {
    await local.get(`/travelbox/plan/passport/${planId}`).then(success).catch(fail);
}
async function accomodation(planId, success, fail) {
    await local.get(`/travelbox/plan/accomodation/${planId}`).then(success).catch(fail);
}
async function itinerary(planId, success, fail) {
    await local.get(`/travelbox/plan/itinerary/${planId}`).then(success).catch(fail);
}
async function planInfoById(planId, success, fail) {
    await local.get(`/travelbox/plan/info/${planId}`).then(success).catch(fail);
}
async function passportInfoById(passportId, success, fail) {
    await local.get(`/travelbox/passport/${passportId}`).then(success).catch(fail);
}
async function savePassportInfo(param, success, fail) {
    await local.put(`/travelbox/passport`,param).then(success).catch(fail);
}
async function addPassportInfo(planId, success, fail) {
    await local.post(`/travelbox/passport/${planId}`).then(success).catch(fail);
}

export {list,passport, accomodation,itinerary,planInfoById,passportInfoById,savePassportInfo,addPassportInfo};
