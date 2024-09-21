<script setup>

import {onMounted, onUnmounted, ref, watch} from "vue";
import {instanceChat} from "@/api/api.js";
import ChatMessageComponent from "@/components/chat/ChatMessageComponent.vue";
import {chatConnect, chatDisconnect, chatMessage, chatPublish} from "@/components/chat/chatClient.js";

const props = defineProps({
  "planId": String,
  "sender": Object
})
const content = ref("");
const messageList = ref([]);
const messagesRef = ref(null);

onMounted(async () => {
  await chatConnect(props.planId);
  const {data} = await instanceChat.get(`${props.planId}`);
  if (data) {
    messageList.value = data
    await console.log()
    await scrollDown();
  }
})

const scrollDown = async () => {
  await messagesRef.value.scrollTo({
    top: messagesRef.value.scrollHeight,
    behavior: 'smooth'
  });
}

onUnmounted(() => {
  chatDisconnect();
})

const sendMessage = () => {
  if (!content.value.trim()) {
    return;
  }
  chatPublish(props.planId, props.sender.id, props.sender.name, content.value);
  content.value = "";

}

watch(chatMessage, async (data) => {
  await messageList.value.push(data);
  console.log(messagesRef.value.scrollHeight);
  await scrollDown();
})
</script>

<template>
  <div id="chat-app">
    <div class="chat-container">
      <div id="messages" ref="messagesRef">
        <ChatMessageComponent v-for="message in messageList" :key="message.chatId" :message="message"/>
      </div>
      <div id="input">
        <input type="text" v-model="content" placeholder="Enter Message" @keyup.enter="sendMessage">
        <button type="button" @click="sendMessage" class="button"
                :disabled="content.trim() === ''"
        >
          Send
        </button>
      </div>
    </div>
  </div>
</template>

<style scoped>
#messages::-webkit-scrollbar {
  width: 8px;
}

#messages::-webkit-scrollbar-thumb {
  background: #888; /* Scrollbar color */
  border-radius: 10px;
}

#messages::-webkit-scrollbar-thumb:hover {
  background: #555;
}

.chat-container {
  width: 100%;
  max-width: 500px;
  height: 600px;
  margin: 20px auto;
  border: 1px solid #ccc;
  border-radius: 10px;
  box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1);
  display: flex;
  flex-direction: column;
  background-color: #fff;
}

#messages {
  flex: 1;
  padding: 20px;
  overflow-y: auto;
  border-bottom: 1px solid #ccc;
}

#input {
  display: flex;
  padding: 10px;
  background-color: #f9f9f9;
  border-top: 1px solid #ccc;
}

input {
  flex: 1;
  padding: 10px;
  margin-right: 10px;
  border: 1px solid #93c39f;
  border-radius: 10px;
  font-size: 16px;
  font : inherit;
}

button {
  padding: 10px 20px;
  font-weight: 600;
  text-align: center;
  color: #fff;
  background-color: #93c39f;
  border: none;
  border-radius: 10px;
  cursor: pointer;
  transition: background-color 0.3s;
}

button:disabled {
  background-color: #ccc;
  cursor: not-allowed;
}

button:hover:not(:disabled) {
  background-color: #0056b3;
}

/* Media Queries for responsiveness */
@media (max-width: 768px) {
  .chat-container {
    height: 500px;
  }

  input {
    font-size: 14px;
  }

  button {
    padding: 8px 16px;
    font-size: 14px;
  }
}

@media (max-width: 576px) {
  .chat-container {
    height: 400px;
  }

  input {
    font-size: 12px;
  }

  button {
    padding: 6px 12px;
    font-size: 12px;
  }
}
</style>
