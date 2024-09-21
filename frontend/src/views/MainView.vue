<script setup>

import NavBarComponent from "@/components/commons/NavBarComponent.vue";
import SearchCardComponent from "@/components/main/SearchCardComponent.vue";
import TitleComponent from "@/components/main/TitleComponent.vue";
import PopularLocationItemComponent from "@/components/main/popular/PopularLocationItemComponent.vue";
import NavBarComponentAfterLogin from "@/components/commons/NavBarComponentAfterLogin.vue";
import {useUserStore} from "@/stores/user.js";
import {storeToRefs} from "pinia";
import {computed, onMounted, ref} from "vue";
import {instancePlan} from "@/api/api.js";
import router from "@/router/index.js";

const userStore = useUserStore()
const { isLogin } = storeToRefs(userStore)

const planList = ref([]);

onMounted(() => {
  loadPlanList();
  console.log("main", isLogin.value);
})

const loadPlanList = async () => {
  if (userStore.userInfo && userStore.userInfo.id) {
    const {data} = await instancePlan.get(`list/${userStore.userInfo.id}`);
    planList.value = data.list;
  }
}

const groupedPlanList = computed(() => {
  const groups = [];
  for (let i = 0; i < planList.value.length; i += 3) {
    const group = planList.value.slice(i, i + 3);
    while (group.length < 3) {
      group.push({}); // 빈 객체를 추가하여 빈 컴포넌트가 렌더링되도록 함
    }
    groups.push(group);
  }
  console.log("group: ", groups[0]);
  return groups;
});

const enterPlan = (plan) => {
  if (plan && plan.planId) {
    router.push({ name: 'templatelist', params: { planId: plan.planId } });
  }
};

const addCreatedPlan = async (newPlan) => {
  await console.log("add: ", newPlan);
  await planList.value.push(newPlan);
  await console.log(planList.value);
}
</script>

<template>
  <div class="empty">
    <div class="one">
      <div class="background-image">
        <div class="overlay">
          <template v-if="isLogin">
            <NavBarComponentAfterLogin/>
          </template>
          <template v-else>
            <NavBarComponent/>
          </template>
          <SearchCardComponent @created="addCreatedPlan"/>
        </div>
      </div>
    </div>
    <div class="two">
      <TitleComponent title="Your travel list"  name="travellist"/>
      <div class="list-div">
        <template v-for="(group, index) in groupedPlanList" :key="index">
          <div class="travel-card-parent">
            <PopularLocationItemComponent v-for="(plan, idx) in group" :key="idx" :plan="plan" @click="enterPlan"/>
          </div>
        </template>
      </div>
    </div>
  </div>
  <RouterView/>
</template>


<style scoped>

.list-div {
  position: relative;
  width: 100%;
  display: flex;
  flex-direction: column;
  align-items: center;
  justify-content: center;
  text-align: left;
  font-size: 19.01px;
  color: #112211;
  font-family: Montserrat;
}

.background-image {
  position: relative;
  background-image: url('../assets/Images/main/background.png');
  height: 926px;
  background-position: center;
  background-repeat: no-repeat;
  background-size: cover;
  border-radius: 24px;
  flex-shrink: 0;
  overflow: visible; /* 오버플로우 숨김으로 모서리 둥글게 처리 */
  z-index: 0; /* 배경 이미지 기본 z-index */

}

.overlay {
  position: relative;
  background-image: linear-gradient(to bottom, rgba(0.3, 0.2, 0.6, 0.7) 50%, rgba(0, 0, 0.2, 0.2) 80%), url('../assets/Images/main/background.png');
  height: 1000px;
  background-position: center;
  background-repeat: no-repeat;
  background-size: cover;
  border-radius: 24px;
  flex-shrink: 0;
  overflow: visible; /* 오버플로우 숨김으로 모서리 둥글게 처리 */

}

NavBarComponent {
  position: relative; /* relative나 absolute 중 선택 */
  z-index: 2; /* 오버레이보다 높게 설정하여 컴포넌트가 보이도록 함 */
}

.empty {
  height: 3500px
}

.one {
  width: 100%;
  position: relative;
  height: 1100px;
  text-align: center;
  font-size: 75px;
  color: #fff;
  font-family: 'Alegreya Sans SC';
}

.two {
  position: relative;
  display: flex;
  flex-direction: column;
  align-items: center;
  justify-content: center;
  gap: 63px;
  text-align: left;
  font-size: 45px;
  color: #000;
  padding-left : 200px;
  padding-right : 200px;
  font-family: Montserrat;
}

.travel-card-parent {
  width: 100%;
  height: 138px;
  display: flex;
  flex-direction: row;
  align-items: center;
  justify-content: center;
  gap: 8.6px;
  padding: 15px;
}


</style>
