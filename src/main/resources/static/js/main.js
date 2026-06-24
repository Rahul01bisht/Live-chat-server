let stompClient = null;
let user=null;

const socket = new SockJS('/ws-endpoints');
stompClient = Stomp.over(socket);

stompClient.connect({}, function(frame) {
  console.log("Connected Successfully");
  stompClient.subscribe("/topic/messages", (message)=>{
    
    const li = document.createElement('li');
    const data = JSON.parse(message.body);
    li.innerHTML= data.content + " by( "+data.sender+" at "+data.time+" )";
    
    const messageList = document.getElementById('messageList');
    messageList.appendChild(li);
  });
});

function sendMessage(){
  const msg = document.getElementById("messageInput").value;

  stompClient.send("/app/chat", {}, JSON.stringify({
    'sender': user,
    'content': msg,
    'type': "CHAT",
    'time': new Date().toLocaleString()
  }));
}

function connect(){
  user = document.getElementById('user').value;
  document.getElementById('us').style.display='none';
  document.getElementById('block').style.display='block';
  document.getElementById('ch').innerHTML="Chat-Room "+user;
}
