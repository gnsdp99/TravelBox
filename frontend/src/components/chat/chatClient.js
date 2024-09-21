import {Client} from "@stomp/stompjs";
import {ref} from "vue";

const chatClient = new Client({
    brokerURL: "ws://localhost:8080/travelbox/",
    onDisconnect: (frame) => {
        console.log(frame)
    },
    onStompError: (frame) => {
        console.log(frame)
    }
});

const chatMessage = ref(null);

const chatConnect = (planId) => {
    chatClient.onConnect = () => {
        chatClient.subscribe(`/topic/chat/${planId}`, (frame) => {
            chatMessage.value = JSON.parse(frame.body);
        });
    },
    chatClient.activate();
}

const chatPublish = (planId, senderId, senderName, content) => {
    chatClient.publish({
        destination: `/app/chat/${planId}`,
        body: JSON.stringify({
            planId: planId,
            senderId: senderId,
            senderName: senderName,
            content: content,
        }),
    });
}

const chatDisconnect = () => {
    chatClient.deactivate();
}

export {
    chatPublish,
    chatConnect,
    chatDisconnect,
    chatMessage,
};
