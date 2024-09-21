import { createRouter, createWebHistory } from "vue-router";
import { useUserStore } from '@/stores/user.js';
import { jwtDecode } from "jwt-decode"

const router = createRouter({
    history: createWebHistory(import.meta.env.BASE_URL),
    routes: [
        {
            path: '/',
            name: 'main',
            component: () => import("@/views/MainView.vue"),
        },
        {
            path: '/login',
            name: 'login',
            component: () => import("@/views/user/LoginView.vue"),
        },
        {
            path: '/signup',
            name: 'signup',
            component: () => import("@/views/user/SignUpView.vue"),
        },
        {
            path: '/mypage',
            name: 'mypage',
            component: () => import("@/views/user/MyPageView.vue"),
            meta: { requiresAuth: true }
        },
        {
            path: '/mypageModify',
            name: 'mypageModify',
            component: () => import("@/views/user/MyPageModifyView.vue"),
            meta: { requiresAuth: true }
        },
        {
            path: '/travellist',
            name: 'travellist',
            component: () => import("@/views/plan/TravelListView.vue"),
            meta: { requiresAuth: true }
        },
        {
            path: '/templatelist:planId',
            name: 'templatelist',
            component: () => import("@/views/plan/TemplateListView.vue"),
            meta: { requiresAuth: true }
        },
        // {
        //     path: "/passport:planId/:passportId",
        //     name: "passport",
        //     component: () => import("@/views/plan/PassportView.vue"),
        //     meta: { requiresAuth: true }
        // },
        {
            path: "/passport:planId",
            name: "passport",
            component: () => import("@/views/plan/PassportView.vue"),
            meta: { requiresAuth: true }
        },
        {
            path: "/accomodation:planId",
            name: "accomodation",
            component: () => import("@/views/plan/AccomodationView.vue"),
            meta: { requiresAuth: true }
        },
        {
            path: "/itinerary:planId",
            name: "itinerary",
            component: () => import("@/views/plan/ItineraryView.vue"),
            meta: { requiresAuth: true }
        },
        {
            path: "/plan-create",
            name: "planCreate",
            component: () => import("@/views/plan/PlanCreateView.vue"),
            meta: { requiresAuth: true }
        },
        {
            path: "/plan-modify/:planId",
            name: "planModify",
            component: () => import("@/views/plan/PlanModifyView.vue"),
            meta: { requiresAuth: true }
        },
        {
            path: "/chatbot",
            name: "chatbot",
            component: () => import("@/views/chatbot/ChatBotView.vue"),
        }
    ],
});
router.beforeEach(async (to, from, next) => {
    const userStore = useUserStore();
    const token = sessionStorage.getItem('accessToken');

    if (userStore.isLogin) {
        next();
    } else {
        if (token) {
            await userStore.getUserInfo(token);
        }
        if (to.meta.requiresAuth && !userStore.isLogin) {
            next({ name: 'login' });
        } else {
            next();
        }
    }
});

export default router;
