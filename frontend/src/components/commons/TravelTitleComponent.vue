<script setup>
/**
 * 여행의 제목과 날짜 컴포넌트.
 */

defineProps({
  title : String,
  startDate : String,
  endDate : String
})
</script>

<template>
  <div class="frame-parent">
    <div class="tokyo-parent">
      <div class="tokyo">{{title}}</div>
      <div class="date">{{ startDate }} - {{ endDate }}</div>
    </div>
  </div>

</template>

<style scoped>
.tokyo {
  position: relative;
  font-weight: 600;
  display: flex;
  align-items: center;
  justify-content: center;
  height: 46px;
  flex-shrink: 0;
}
.date {
  position: relative;
  font-weight: 600;
  display: flex;
  align-items: center;
  justify-content: center;
  height: 46px;
  color: #606060;
  flex-shrink: 0;
}

.tokyo-parent {
  display: flex;
  flex-direction: column;
  align-items: flex-start;
  justify-content: space-between;
}
.frame-parent {
  width: 100%;
  position: relative;
  display: flex;
  flex-direction: row;
  align-items: center;
  justify-content: flex-start;
  padding-left: 250px;
  padding-top: 20px;
  padding-bottom: 50px;
  box-sizing: border-box;
  text-align: center;
  font-size: 25px;
  color: #000;
  font-family: Montserrat;
}

/* Media Queries for responsiveness */
@media (max-width: 1200px) {
  .frame-parent {
    padding-left: 150px;
    font-size: 22px;
  }
}

@media (max-width: 992px) {
  .frame-parent {
    padding-left: 100px;
    font-size: 20px;
  }
}

@media (max-width: 768px) {
  .frame-parent {
    flex-direction: column;
    align-items: flex-start;
    padding: 20px;
    font-size: 18px;
  }
  .tokyo, .date {
    justify-content: flex-start;
    width: 100%;
  }
}

@media (max-width: 576px) {
  .frame-parent {
    padding: 10px;
    font-size: 16px;
  }
  .tokyo, .date {
    justify-content: flex-start;
    width: 100%;
  }
}
</style>
