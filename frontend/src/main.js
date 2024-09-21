import { createApp } from "vue";
import { createPinia } from "pinia";

import App from "./App.vue";
import router from "./router";
import './assets/css/global.css'; // 전역 스타일 파일 임포트
import {useKakao} from "vue3-kakao-maps/@utils";


const { VITE_KAKAO_MAP_APPKEY } = import.meta.env;
useKakao(VITE_KAKAO_MAP_APPKEY);

const app = createApp(App);

app.use(createPinia());
app.use(router);

app.mount("#app");