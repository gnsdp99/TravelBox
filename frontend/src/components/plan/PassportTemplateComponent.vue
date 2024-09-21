<script setup>

import {ref, watch} from "vue";
import {savePassportInfo} from "@/api/plan.js";
import debounce from "lodash/debounce";
import DeleteButtonComponent from "@/components/buttons/DeleteButtonComponent.vue";
import {instancePassport} from "@/api/api.js";

const props = defineProps({
  "passport": Object
});

const localPassport = ref(props.passport);
const emit = defineEmits(['delete-success']);

const debouncedSavePassportData = debounce(async (passportData) => {
  await savePassportInfo(
      passportData,
      (response) => {
        localPassport.value = response.data.passportInfo;
      },
      async (error) => {
        console.error(error.response.status, error.response.statusText);
      }
  );
}, 300); // 300ms 후에 호출

watch(
    localPassport,
    (newVal) => {
      debouncedSavePassportData(newVal);
    },
    { deep: true }
);

const deletePassport = async () => {
  if (props.passport && props.passport.passportId) {
    console.log(props.passport.passportId, "passportId");
    await instancePassport.delete(`/${props.passport.passportId}`);
    emit('delete-success');
  } else {
    console.error("Passport ID is missing.");
  }

};
</script>

<template>
  <div class="passport-row">
    <div class="base">
      <input class="effective-advertisin" v-model="localPassport.name">
    </div>
    <div class="base">
      <input class="effective-advertisin" v-model="localPassport.passportNumber">
    </div>
    <div class="base">
      <input class="effective-advertisin" v-model="localPassport.expiryDate">
    </div>
    <div class="base">
      <input class="effective-advertisin" v-model="localPassport.country">
    </div>
    <DeleteButtonComponent class="delete-button" title="delete" @click="deletePassport"/>
  </div>
</template>

<style scoped>
.passport-row {
  display: flex;
  align-items: center;
  justify-content: space-between;
  gap: 16px;
  padding: 10px 0;

}

.base {
  flex: 1;
  position: relative;
  height: 64px;
  display: flex;
  align-items: center;
  justify-content: center;
  padding: 0 10px;
  box-shadow: 0px 2px 5px rgba(38, 51, 77, 0.03);
  border-radius: 10px;
  background-color: #fff;
  border: 2px solid #f5f6f7;
  box-sizing: border-box;
}

.effective-advertisin {
  width: 100%;
  border: none;
  background: none;
  color: inherit;
  font: inherit;
  margin: 0;
  outline: none;
  text-align: center;
  font-family: "Montserrat", "Noto Serif KR";
}

.delete-button {
  margin-left: 16px;
}
</style>
