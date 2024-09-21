import { localAxios } from "@/util/http-commons";

// localAxios 인스턴스를 생성합니다.
const local = localAxios();

/**
 * 사용자 로그인 확인 함수
 *
 * @param {Object} param - 로그인 요청에 필요한 파라미터 (사용자 이름, 비밀번호 등).
 * @param {Function} success - 요청이 성공했을 때 호출되는 콜백 함수.
 * @param {Function} fail - 요청이 실패했을 때 호출되는 콜백 함수.
 */
async function userConfirm(param, success, fail) {
  await local.post(`/travelbox/user/login`, param).then(success).catch(fail);
}

/**
 * 사용자 정보 조회 함수
 *
 * @param {String} userid - 조회할 사용자의 ID.
 * @param {Function} success - 요청이 성공했을 때 호출되는 콜백 함수.
 * @param {Function} fail - 요청이 실패했을 때 호출되는 콜백 함수.
 */
async function findById(userId, success, fail) {
  local.defaults.headers["Authorization"] = sessionStorage.getItem("accessToken");
  await local.get(`/travelbox/user/info/${userId}`).then(success).catch(fail);
}

/**
 * 토큰 재발급 함수
 *
 * @param {Object} user - 사용자 정보 객체.
 * @param {Function} success - 요청이 성공했을 때 호출되는 콜백 함수.
 * @param {Function} fail - 요청이 실패했을 때 호출되는 콜백 함수.
 */
async function tokenRegeneration(user, success, fail) {
  local.defaults.headers["refreshToken"] = sessionStorage.getItem("refreshToken"); // axios header에 refresh-token 셋팅
  await local.post(`/travelbox/user/refresh`, user).then(success).catch(fail);
}

/**
 * 사용자 로그아웃 함수
 *
 * @param {String} userid - 로그아웃할 사용자의 ID.
 * @param {Function} success - 요청이 성공했을 때 호출되는 콜백 함수.
 * @param {Function} fail - 요청이 실패했을 때 호출되는 콜백 함수.
 */
async function logout(userid, success, fail) {
  await local.get(`/travelbox/user/logout/${userid}`).then(success).catch(fail);
}

/**
 * 사용자 회원가입 함수
 *
 * @param {Object} param - 회원가입 요청에 필요한 파라미터 (사용자 이름, 비밀번호, 이메일 등).
 * @param {Function} success - 요청이 성공했을 때 호출되는 콜백 함수.
 * @param {Function} fail - 요청이 실패했을 때 호출되는 콜백 함수.
 */
async function signup(param, success, fail) {
  await local.post(`/travelbox/user/signup`, param).then(success).catch(fail);
}

async function modify(param, success, fail) {
  await local.post(`/travelbox/user/modify`, param).then(success).catch(fail);
}

/**
 * 사용자 회원탈퇴 함수
 *
 * @param {Object} userId - 회원탈퇴에 필요한 회원 아이디
 * @param {Function} success - 요청이 성공했을 때 호출되는 콜백 함수.
 * @param {Function} fail - 요청이 실패했을 때 호출되는 콜백 함수.
 */
async function deleteUser(userId, success, fail) {
  await local.delete(`/travelbox/user/${userId}`).then(success).catch(fail);
}

export { userConfirm, findById, tokenRegeneration, logout,signup, modify, deleteUser };
