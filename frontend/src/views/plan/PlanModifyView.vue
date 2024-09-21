<script setup>
import {useRoute } from "vue-router";
import PlanModifyFormComponent from "@/components/plan/PlanModifyFormComponent.vue";
import {onMounted} from "vue";
import {usePlanStore} from "@/stores/plan.js";
import NavBarComponentBasicAfterLogin from "@/components/commons/NavBarComponentBasicAfterLogin.vue";

const route = useRoute();
const planStore = usePlanStore();

onMounted(() => {
  planStore.getPlanInfo(route.params.planId);
});
</script>

<template>
  <NavBarComponentBasicAfterLogin/>
  <PlanModifyFormComponent />
</template>

<style scoped>

</style>