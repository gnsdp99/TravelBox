<script setup>
/**
 * 페이지별 페이지 제목 컴포넌트. 예를들어 여권정보, 숙박정보 같은
 */

defineProps({
  title : String
})
</script>

<template>
  <div class="travel-list">
    <div class="title-div">
      <div class="travel-text">{{ title }}</div>
    </div>
  </div>
</template>

<style scoped>
.travel-list {
  position: relative;
  font-weight: 600;

}
.title-div {
  width: 100%;
  position: relative;
  height: 214px;
  overflow: hidden;
  flex-shrink: 0;
  display: flex;
  flex-direction: column;
  align-items: flex-start;
  justify-content: center;
  padding: 33px 169px;
  box-sizing: border-box;
  text-align: left;
  font-size: 45px;
  color: #000;
  font-family: Montserrat;
}

.travel-text {
  position: relative;
  background-color: #fff;
  width: 100%;
  height: 1500px;
  overflow: hidden;
  display: flex;
  flex-direction: column;
  align-items: flex-start;
  justify-content: flex-start;
  text-align: left;
  font-size: 40px;
  color: #000;
  font-family: Montserrat;
  padding-top: 10px;
}

/* Media Queries for responsiveness */
@media (max-width: 1200px) {
  .title-div {
    padding: 33px 100px;
    font-size: 40px;
  }
  .travel-text {
    font-size: 35px;
  }
}

@media (max-width: 992px) {
  .title-div {
    padding: 33px 50px;
    font-size: 35px;
  }
  .travel-text {
    font-size: 30px;
  }
}

@media (max-width: 768px) {
  .title-div {
    padding: 33px 30px;
    font-size: 30px;
  }
  .travel-text {
    font-size: 25px;
  }
}

@media (max-width: 576px) {
  .title-div {
    padding: 33px 15px;
    font-size: 25px;
  }
  .travel-text {
    font-size: 20px;
  }
}

</style>