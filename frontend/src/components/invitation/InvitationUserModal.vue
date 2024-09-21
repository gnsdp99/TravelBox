<script setup>
defineProps({
  "isVisible": Boolean
})

const emit = defineEmits(["close"]);

const closeModal = () => {
  emit("close");
}
</script>

<template>
  <div v-if="isVisible" class="modal-overlay" @click="closeModal">
    <div class="modal-content" @click.stop>
      <button class="close-button" @click="closeModal">X</button>
      <slot></slot>
    </div>
  </div>
</template>

<style scoped>

.close-button:hover,
.close-button:focus {
  color: black;
  text-decoration: none;
  cursor: pointer;
}

.modal-overlay {
  position: fixed;
  top: 0;
  left: 0;
  width: 100%;
  height: 100%;
  background: rgba(0, 0, 0, 0.6);
  display: flex;
  justify-content: center;
  align-items: center;
  z-index: 1000;
}
.modal-content {
  background: white;
  padding: 30px;
  border-radius: 15px;
  box-shadow: 0 4px 8px rgba(0, 0, 0, 0.2);
  position: relative;
  min-width: 300px;
  max-width: 500px;
  text-align: center;
  animation: fadeIn 0.3s ease-out;
}
.close-button {
  position: absolute;
  top: 15px;
  right: 15px;
  border: none;
  background: transparent;
  font-size: 1.5em;
  cursor: pointer;
  color: #888;
  transition: color 0.2s;
}
.close-button:hover {
  color: #000;
}
@keyframes fadeIn {
  from {
    opacity: 0;
    transform: scale(0.9);
  }
  to {
    opacity: 1;
    transform: scale(1);
  }
}
</style>