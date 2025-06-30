(function() {

    const app = document.getElementById('app');
    let ws = null;
    let roomId = '';
    let nickName = '';

})(); 

function renderRoomForm() {
    app.innetHTML = '';
    const form = document.createElement('form');
    form.className = 'room-form';
    form.innerHTML = `
            <input type="text" id="roomId" placeholder="채팅방 ID" required />
            <input type="text" id="nickName" placeholder="닉네임" required />
            <button type="submit">입장</button>
        `;
    app.appendChild(form);
    
    form.onsubmit = function(e) {
        e.preventDefault();
        roomId = form.querySelector('#roomId').value.trim();
        nickName = form.querySelector('#nickName').value.trim();

        if (!roomId || !nickName) 
            return;

        renderChatSection();
        connectWebSocket();
    };
}

function renderChatSection() {
    app.innerHTML = '';
    const chatSection = document.createElement('div');
    chatSection.id = 'chatSection';

    chatSection.innerHTML = `
    <div class="chat-log" id="chatLog"></div>
    <form class="input-row" id="messageForm">
        <input type="text" id="messageInput" placeholder="메시지 입력" autocomplete="off" required />
        <button type="submit">전송</button>
        <button type="button" id="leaveBtn" style="background:#d32f2f;">나가기</button>
    </form>
`;
    app.appendChild(chatSection);

    const chatLog = chatSection.querySelector('#chatLog');
    const messageForm = chatSection.querySelector('#messageForm');
    const messageInput = chatSection.querySelector('#messageInput');
    const leaveBtn = chatSection.querySelector('#leaveBtn');  
    
    messageForm.onsubmit = function(e) {
        e.preventDefault();
        sendMessage(messageInput, chatLog);
    };

    leaveBtn.onClick = function() {
        leaveChatRoom();
    };
    messageInput.focus();
}

function appendMessage(msgObj) {
    const div = document.createElement('div');
    div.className = 'chat-message';
    div.innerHTML = `<span class="sender">${msgObj.sender || ''}</span>${msgObj.message}`;
    chatLog.appendChild(div);
    chatLog.scrollTop = chatLog.scrollHeight;
}
