<script setup>
import { instanceBasic } from "@/api/api.js";
import {ref, watch} from "vue";
import NavBarComponentBasicAfterLogin from "@/components/commons/NavBarComponentBasicAfterLogin.vue";
import {useUserStore} from "@/stores/user.js";
import {storeToRefs} from "pinia";
import NavBarComponentBasic from "@/components/commons/NavBarComponentBasic.vue";

const userStore = useUserStore();
const {userInfo} = storeToRefs(userStore);

const userInput = ref("");
const messages = ref([]);
const messagesRef = ref(null);

const sendMessage = async () => {
  const message = userInput.value;
  userInput.value = '';
  await messages.value.push({id: Date.now(), sender: 'You', text: message});
  await scrollDown();
  try {
    const response = await instanceBasic.post('/chatbot', {message});

    await messages.value.push({id: Date.now(), sender: 'Bot', text: response.data.chat});
  } catch (error) {
    await messages.value.push({id: Date.now(), sender: 'Bot', text: 'Sorry, there was an error.'});
  }
  await scrollDown();
}

const scrollDown = async () => {
  await messagesRef.value.scrollTo({
    top: messagesRef.value.scrollHeight,
    behavior: 'smooth'
  });
}
</script>

<template>
  <NavBarComponentBasicAfterLogin v-if="userInfo"/>
  <NavBarComponentBasic v-else/>
  <div id="app">
    <h1>Tourist Guide Bot</h1>
    <div id="chatbox">
      <div id="messages" ref="messagesRef">
        <div v-for="message in messages" :key="message.id" :class="message.sender">
          <p><strong>{{ message.sender }}:</strong> {{ message.text }}</p>
        </div>
      </div>
      <div class="input-div">
      <input class="input" type="text" v-model="userInput" @keyup.enter="sendMessage" placeholder="  Ask me something..."/>
      <button class="send-btn" @click="sendMessage">Send</button>
      </div>
    </div>
  </div>
</template>


<style scoped>
.input-div{
  padding : 10px;
  align-content: center;
  justify-content: center;
}
button{
  height: 50px;
  padding-left: 10px;
}
input{
  font-family: Montserrat;
  width: 85%;
  height: 50px;
  border-radius: 20px;
}
.input {
  padding-left: 10px;
  font-family: Montserrat, "Noto Serif KR";
  font-size: 15px;
  font-weight: bold;
}
.send-btn {
  font-family: Montserrat, "Noto Serif KR";
}
body {
  background-color: #f0f0f0;
  font-family: 'Arial', sans-serif;
}

#app {
  display: flex;
  flex-direction: column;
  align-items: center;
  padding: 20px;
  font-family: Montserrat;
}

h1 {
  color: #333;
  margin-bottom: 20px;
}

#chatbox {
  width: 100%;
  max-width: 600px;
  height: 600px;
  border: 1px solid #ddd;
  border-radius: 10px;
  background-color: #ffffff;
  box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1);
  padding: 20px;
  display: flex;
  flex-direction: column;
}

#messages {
  flex: 1;
  border: 1px solid #eee;
  border-radius: 10px;
  padding: 30px;
  height: 300px;
  overflow-y: scroll;
  background-color: #fafafa;
  font-family: Montserrat, "Noto Serif KR";
}

#messages .You {
  text-align: right;
  background-color: #d1e7dd;
  color: #0f5132;
  padding: 10px;
  border-radius: 10px;
  margin: 5px 0;
  box-shadow: 0 2px 4px rgba(0, 0, 0, 0.1);
  animation: slideIn 0.3s ease;
}

#messages .Bot {
  text-align: left;
  background-color: #cfe2ff;
  color: #084298;
  padding: 10px;
  border-radius: 10px;
  margin: 5px 0;
  box-shadow: 0 2px 4px rgba(0, 0, 0, 0.1);
  animation: slideIn 0.3s ease;
}

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

@keyframes slideIn {
  from {
    transform: translateY(10px);
    opacity: 0;
  }
  to {
    transform: translateY(0);
    opacity: 1;
  }
}

#inputArea {
  display: flex;
  align-items: center;
  margin-top: 10px;
  padding: 10px;
  background-color: #ffffff;
  border-radius: 10px;
  box-shadow: 0 2px 4px rgba(0, 0, 0, 0.1);
}

#inputArea input {
  flex: 1;
  padding: 12px 20px;
  border: 1px solid #ccc;
  border-radius: 20px;
  margin-right: 10px;
  box-shadow: 0 2px 4px rgba(0, 0, 0, 0.1);
  transition: all 0.2s ease;
  font-size: 16px;
  font-family: Montserrat;
}
#inputArea input:focus {
  border-color: #007bff;
  box-shadow: 0 0 5px rgba(0, 123, 255, 0.5);
  outline: none;
  font-family: Montserrat;
}

#inputArea button {
  padding: 12px 20px;
  border: none;
  border-radius: 20px;
  background-color: #007bff;
  color: #fff;
  cursor: pointer;
  box-shadow: 0 2px 4px rgba(0, 0, 0, 0.1);
  transition: background-color 0.2s ease;
  font-size: 16px;
}

#inputArea button:hover {
  background-color: #0056b3;
}
</style>