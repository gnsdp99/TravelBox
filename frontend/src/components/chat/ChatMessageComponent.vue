<script setup>
import {ref} from "vue";
import {useUserStore} from "@/stores/user.js";

const props = defineProps({
  "message": Object,
})

const userStore = useUserStore();

const chatDateFormat = (param) => {
  const date = new Date(param);
  return (date.getMonth() + 1) + "/" + date.getDate() + " " + date.toTimeString().split(' ')[0];
}

const isMyMessage = ref(false);

if (props.message.senderId === userStore.userInfo.id) {
  isMyMessage.value = true;
}
</script>

<template>
  <div v-if="isMyMessage" class="message my-message">
    <span>{{message.content}}</span>
    <br><span class="date">({{chatDateFormat(message.sendDate)}})</span>
  </div>
  <div v-else class="message other-message">
    <span>{{message.content}}</span>
    <br><span class="sender">{{message.senderName}} ({{chatDateFormat(message.sendDate)}})</span>
  </div>
</template>

<style scoped>
.sender, .date {
  font-size: 15px;
}

.message {
  max-width: 70%;
  margin: 5px 0;
  padding: 10px;
  border-radius: 10px;
  position: relative;
  word-wrap: break-word;
  font-family: Montserrat, "Noto Serif KR";
}

.other-message {
  align-self: flex-start;
  background-color: #e5e5e5;
  border-bottom-left-radius: 0;
  border: 1px solid #ddd;
}

.my-message {
  align-self: flex-end;
  background-color: #f1e0a8;
  border-bottom-right-radius: 0;
  margin-left: auto;
}
</style>