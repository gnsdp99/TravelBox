import { ref } from "vue"
import { useRouter } from "vue-router"
import { defineStore } from "pinia"
import { jwtDecode } from "jwt-decode"

import { userConfirm, findById, tokenRegeneration, logout } from "@/api/user"
import { httpStatusCode } from "@/util/http-status"

export const useUserStore = defineStore("memberStore", () => {
    const router = useRouter();

    const isLogin = ref(false);
    const isLoginError = ref(false);
    const userInfo = ref(null);
    const isValidToken = ref(false);
    const isDeleted = ref(false);

    const userLogin = async (loginUser) => {
        await userConfirm(
            loginUser,
            async (response) => {
                if (response.status === httpStatusCode.CREATE) {
                    console.log("Login success!");

                    let { data } = response;
                    let accessToken = data["access-token"];
                    let refreshToken = data["refresh-token"];

                    isLogin.value = true;
                    isLoginError.value = false;
                    isValidToken.value = true;

                    // store tokens to session
                    sessionStorage.setItem("accessToken", accessToken);
                    sessionStorage.setItem("refreshToken", refreshToken);

                    // get user info with access token
                    await getUserInfo(accessToken);

                    // 로그 출력
                    if (userInfo.value) {
                        console.log("User ID after login: " + userInfo.value.id);
                    } else {
                        console.error("User info is null after login.");
                    }
                }
            },
            (error) => {
                console.log("Login fail, try again.");
                isLogin.value = false;
                isLoginError.value = true;
                isValidToken.value = false;
                console.error(error);
            }
        );
    };

    const getUserInfo = async (token) => {
        let decodeToken = jwtDecode(token);
        console.log("Decoded Token: ", decodeToken);

        await findById(
            decodeToken.userId,
            (response) => {
                console.log("FindById Response: ", response);
                if (response.status === httpStatusCode.OK) {
                    userInfo.value = response.data.userInfo; // response.data.userInfo가 아닌 response.data로 설정
                    isLogin.value = true;
                    console.log("User Info retrieved: ", userInfo.value);
                } else {
                    console.log("유저 정보 없음!!!!");
                }
            },
            async (error) => {
                console.error(
                    "토큰 만료되어 사용 불가능: ",
                    error.response.status,
                    error.response.statusText
                );
                isValidToken.value = false;

                await tokenRegenerate();
            }
        );
    };

    const tokenRegenerate = async () => {
        await tokenRegeneration(
            JSON.stringify(userInfo.value),
            (response) => {
                if (response.status === httpStatusCode.CREATE) {
                    let accessToken = response.data["access-token"];
                    sessionStorage.setItem("accessToken", accessToken);
                    isValidToken.value = true;
                }
            },
            async (error) => {
                if (error.response.status === httpStatusCode.UNAUTHORIZED) {
                    await logout(
                        userInfo.value.id,
                        (response) => {
                            if (response.status === httpStatusCode.OK) {
                                console.log("리프레시 토큰 제거 성공");
                            } else {
                                console.log("리프레시 토큰 제거 실패");
                            }
                            alert("RefreshToken 기간 만료! 다시 로그인해 주세요.");
                            isLogin.value = false;
                            userInfo.value = null;
                            isValidToken.value = false;
                            router.push({ name: "user-login" });
                        },
                        (error) => {
                            console.error(error);
                            isLogin.value = false;
                            userInfo.value = null;
                        }
                    );
                }
            }
        );
    };

    const userLogout = async () => {
        if (userInfo.value && userInfo.value.id) {
            console.log("로그아웃 아이디: " + userInfo.value.id);
            await logout(
                userInfo.value.id,
                (response) => {
                    if (response.status === httpStatusCode.OK) {
                        isLogin.value = false;
                        userInfo.value = null;
                        isValidToken.value = false;

                        sessionStorage.removeItem("accessToken");
                        sessionStorage.removeItem("refreshToken");
                    } else {
                        console.error("유저 정보 없음!");
                    }
                },
                (error) => {
                    console.log(error);
                }
            );
        } else {
            console.error("로그아웃 실패: 유저 정보가 없습니다.");
        }
    };

    const verifyToken = (token) => {
        try {
            const decodedToken = jwtDecode(token);
            const currentTime = Date.now() / 1000;

            if (decodedToken.exp < currentTime) {
                console.log('토큰이 만료되었습니다.');
                return false;
            }
            return true;
        } catch (error) {
            console.error('토큰 디코딩 오류:', error);
            return false;
        }
    };

    return {
        isLogin,
        isLoginError,
        userInfo,
        isValidToken,
        userLogin,
        getUserInfo,
        tokenRegenerate,
        userLogout,
        verifyToken,
        isDeleted
    };
});