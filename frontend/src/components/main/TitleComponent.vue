<script setup>
import router from "@/router/index.js";

const props = defineProps({
  title : String,
  name : String,
})

const seeMore = () => {
  if(props.name === 'travellist'){
    router.push({ name: 'travellist' });
  }else{
    //
  }
}
</script>

<template>

  <div class="menu-title">
    <div class="your-travel-list">{{ props.title }}</div>
    <div class="button">
      <button class="see-more-your-travel-wrapper" @click="seeMore">
        <div class="see-more-your">See more your travel</div>
      </button>
    </div>
  </div>



</template>

<style scoped>
.your-travel-list {
  align-self: stretch;
  width: 640px;
  position: relative;
  font-weight: 600;
  display: inline-block;
  flex-shrink: 0;
}
.see-more-your {
  position: relative;
  font-size: 15px;
  font-weight: 500;
  font-family: Montserrat;
  color: #112211;
  text-align: left;
}

.see-more-your-travel-wrapper {
  width: 225px;
  border-radius: 4px;
  border: 1px solid #8dd3bb;
  box-sizing: border-box;
  height: 40px;
  display: flex;
  flex-direction: row;
  align-items: center;
  justify-content: center;
}
.button {
  width: 100%;
  position: relative;
  height: 63px;
  display: flex;
  flex-direction: column;
  align-items: flex-end;
  justify-content: center;
  text-align: left;
  font-size: 15px;
  color: #112211;
  font-family: Montserrat;
}
.menu-title {
  width: 100%;
  position: relative;
  height: 100px;
  display: flex;
  flex-direction: row;
  align-items: center;
  justify-content: space-between;
  padding: 20px 20px;
  box-sizing: border-box;
  text-align: left;
  font-size: 45px;
  color: #000;
  font-family: Montserrat;
}


</style>