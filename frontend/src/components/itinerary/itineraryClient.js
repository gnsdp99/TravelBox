import {Client} from "@stomp/stompjs";
import {ref} from "vue";

const itineraryClient = new Client({
    brokerURL: "ws://localhost:8080/travelbox/",
    onDisconnect: (frame) => {
        console.log(frame)
    },
    onStompError: (frame) => {
        console.log(frame)
    }
});

const itineraryMessage = ref(null);

const itineraryConnect = (planId) => {
    itineraryClient.onConnect = () => {
        itineraryClient.subscribe(`/topic/itinerary/${planId}`, (frame) => {
            itineraryMessage.value = JSON.parse(frame.body);
        });
    },
    itineraryClient.activate();
}

const itineraryPublish = (data) => {
    itineraryClient.publish({
        destination: `/app/itinerary/${data.planId}`,
        body: JSON.stringify({
            date: data.date,
            list: data.list,
            prevDate: data.prevDate,
            prevList: data.prevList,
        }),
    });
}

const itineraryDisconnect = () => {
    itineraryClient.deactivate();
}

export {
    itineraryPublish,
    itineraryConnect,
    itineraryDisconnect,
    itineraryMessage,
};
