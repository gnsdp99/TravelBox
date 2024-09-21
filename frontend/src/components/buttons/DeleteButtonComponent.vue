<script setup>


defineProps({
  title : String,
})


</script>

<template>
  <div class="button" @click="deletePassport">
    <div class="style-layer">
      <button class="button1">{{ title }}</button>
    </div>
  </div>
</template>

<style scoped>
.paper-plane-icon {
  width: 16px;
  position: relative;
  height: 16px;
  overflow: hidden;
  flex-shrink: 0;
}
.button1 {
  position: relative;
  font-weight: 500;
}
.style-layer {
  border-radius: 4px;
  background-color: #8dd3bb;
  height: 40px;
  display: flex;
  flex-direction: row;
  align-items: center;
  justify-content: center;
  padding: 8px 16px;
  box-sizing: border-box;
  gap: 4px;
}
.button {
  position : relative;
  display: flex;
  flex-direction: column;
  align-items: flex-start;
  justify-content: flex-start;
  text-align: left;
  font-size: 15px;
  color: #112211;
  font-family: Montserrat;
}


</style>