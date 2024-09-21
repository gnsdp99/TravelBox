<script setup>

</script>

<template>
  <div class="card card-style">
    <div class="card-in card-style">
    </div>
    <div class="card-in card-style">
    </div>
    <div class="card-in card-style">
    </div>

  </div>
</template>

<style scoped>
.card {
  display: flex;
  height: 160px;
  padding: 16px;
  align-items: center;
  gap: 16px;
  flex: 1 0 0;
}

.card-in {
  display: flex;
  height: 130px;
  width: 20px;
  padding: 16px;
  align-items: center;
  gap: 16px;
  flex: 1 0 0;
}

.card-style {
  border-radius: 16px;
  background: var(--neutrals, #FFF);

  /* Cards Shadow */
  box-shadow: 0px 7px 18px 0px rgba(17, 34, 17, 0.05);
}
</style>