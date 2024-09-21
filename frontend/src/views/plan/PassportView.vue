<script setup>
import NavBarComponentBasicAfterLogin from "@/components/commons/NavBarComponentBasicAfterLogin.vue";
import TitleComponent from "@/components/commons/TitleComponent.vue";
import AddbuttonComponent from "@/components/buttons/AddbuttonComponent.vue";
import TravelTitleComponent from "@/components/commons/TravelTitleComponent.vue";
import { usePlanStore } from "@/stores/plan.js";
import { storeToRefs } from "pinia";
import { useRoute } from "vue-router";
import { onMounted } from "vue";
import PassportTitleComponent from "@/components/plan/PassportTitleComponent.vue";
import PassportTemplateComponent from "@/components/plan/PassportTemplateComponent.vue";

defineProps({
	passport: Object,
});

const route = useRoute();
const planId = route.params.planId;
const planStore = usePlanStore();
const { planInfo } = storeToRefs(planStore);

const passportList = async () => {
	console.log("passportView!!!!! ");
	await planStore.getPlanInfo(planId);
	await planStore.getPassportList(planId);
};

const AddPersonInfo = async () => {
	await planStore.addPassport(planId);
	await passportList();
};
const reload = async () => {
	await passportList();
};
onMounted(() => {
	passportList();
});
</script>

<template>
	<NavBarComponentBasicAfterLogin />

	<div class="title-button-div">
		<div class="title-div">
			<TitleComponent title="Passport Infomation" />
		</div>
		<div class="button-div">
			<AddbuttonComponent
				title="Add"
				@click="AddPersonInfo"
			/>
		</div>
	</div>
	<TravelTitleComponent
		:title="planInfo?.planName"
		:startDate="planInfo?.startDate"
		:endDate="planInfo?.endDate"
	/>
	<div class="body-div">
		<div>
			<div class="passport-list">
				<PassportTitleComponent />
			</div>
			<div class="passport-list">
				<PassportTemplateComponent
					v-for="passport in planStore.passportList"
					:key="passport.passportId"
					:passport="passport"
					@delete-success="reload"
				/>
			</div>
		</div>
	</div>
</template>

<style scoped>
.body-div {
	position: relative;
	height: 280px;
	display: flex;
	flex-direction: column;
	align-items: center;
	justify-content: space-between;
	text-align: center;
	font-size: 20px;
	color: #3a404b;
	font-family: Montserrat;
}

.passport-list {
	width: 100%;
	position: relative;
	display: flex;
	flex-direction: column;
	align-items: center;
	justify-content: flex-start;
	text-align: center;
	gap: 16px;
	font-size: 20px;
	color: #4d5e80;
	font-family: Montserrat;
}

.frame-parent {
	width: 100%;
	position: relative;
	display: flex;
	flex-direction: row;
	align-items: center;
	justify-content: flex-start;
	padding-left: 250px;
	padding-right: 250px;
	box-sizing: border-box;
	text-align: center;
	font-size: 25px;
	color: #000;
	font-family: Montserrat;
}

.title-div {
	align-self: stretch;
	flex: 1;
	position: relative;
}

.button-div {
	align-self: stretch;
	flex: 1;
	display: flex;
	flex-direction: column;
	align-items: flex-end;
	justify-content: center;
	font-size: 16px;
	color: #112211;
}

.title-button-div {
	width: 100%;
	position: relative;
	height: 150px;
	display: flex;
	flex-direction: row;
	align-items: center;
	justify-content: center;
	padding: 0px 120px;
	box-sizing: border-box;
	gap: 49px;
	text-align: left;
	font-size: 45px;
	color: #000;
	font-family: Montserrat;
}
</style>
