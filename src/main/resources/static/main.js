let stompClient
const SOCKET_URI = "wss://localhost:8080/websocket";

$(() => {
    initSocket();

    $("#inputContainer").on("submit", e => {
        e.preventDefault();

        let input = $("#textInput");
        let message = input.val();
        input.val("");

        sendMessage(message);
    })
});

function sendJoinedMessage() {
    let name = prompt("Enter your name", "vacuousVersifer");
    stompClient.publish({
        destination: "/app/join",
        body: JSON.stringify({
            "name": name
        })
    })
}

function sendMessage(message) {
    console.log("Sending Message: " + message);
    stompClient.publish({
        destination: "/app/message",
        body: JSON.stringify({
            "content": message
        })
    })
}

function onMessage(event) {
    let message = JSON.parse(event.body).content;
    addMessage(message);
}

function onJoin(event) {
    let user = JSON.parse(event.body).content;
    addMessage(`${user} has started trolling you, now!`);
}

function onLeave(event) {

}
function addMessage(message) {

    let messageConsole = $("#messageContainer");
    messageConsole.append($("<li>" + message + "</li>"));
    messageConsole.scrollTop(messageConsole[0].scrollHeight);
}

function initSocket() {
    stompClient = new StompJs.Client({ brokerURL: SOCKET_URI });

    stompClient.activate();

    stompClient.onConnect = (frame) => {
        console.log(frame);

        stompClient.subscribe("/event/message", event => { onMessage(event) });
        stompClient.subscribe("/event/join", event => { onJoin(event) });
        stompClient.subscribe("/event/leave", event => { onLeave(event) });

        sendJoinedMessage();
    }

    stompClient.onWebSocketError = (error) => { console.error(error); alert("Websocket Error!"); }
    stompClient.onStompError = (error) => { console.error(error); alert("Stomp Error!"); }
    function disconnect() { stompClient.deactivate(); console.log("Websocket Disconnected"); }
}