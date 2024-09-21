<script setup>

</script>

<template>
  <div class="group-button">
    <div class="button-1">
      <b class="button">name</b>
    </div>
    <div class="button-2">
      <div class="button1">Passport number</div>
    </div>
    <div class="button-2">
      <div class="button1">Expire Date</div>
    </div>
    <div class="button-2">
      <div class="button1">National</div>
    </div>
    <div class="button-3"></div>
  </div>

</template>

<style scoped>

.button {
  position: relative;
  white-space: nowrap; /* 한 줄로 유지 */
  line-height: 20px;
}
.button-1 {
  flex: 1;
  border-radius: 30px 0px 0px 30px;
  background-color: #fafbfc;
  height: 40px;
  display: flex;
  flex-direction: row;
  align-items: center;
  justify-content: center;
  padding: 15px 15px 15px 20px;
  box-sizing: border-box;
  font-size: 25px;
  color: #56b293;
}
.button1 {
  position: relative;
  width: 100%;
  line-height: 20px;
  font-weight: 500;
}
.button-2 {
  flex: 1;
  position: relative;
  background-color: #fff;
  height: 40px;
  display: flex;
  flex-direction: row;
  align-items: center;
  justify-content: center;
  padding: 15px;
  box-sizing: border-box;
}
.button-3{
  width: 120px;
}
.group-button {
  width: 100%;
  position: relative;
  filter: drop-shadow(0px 2px 5px rgba(38, 51, 77, 0.03));
  height: 56px;
  display: flex;
  flex-direction: row;
  align-items: center;
  justify-content: space-between;
  text-align: center;
  font-size: 20px;
  color: #3a404b;
  font-family: Montserrat;
  min-width: 1300px; /* 부모 요소의 최소 너비 설정 */
}


</style>