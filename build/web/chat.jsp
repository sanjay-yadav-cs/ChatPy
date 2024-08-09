<%@page import="java.time.LocalDateTime"%>
<%@page import="DependencyInjector.DIResolver"%>
<%@page import="java.time.format.DateTimeFormatter"%>
<%@page import="java.util.List,javax.servlet.http.HttpSession, Repository.*, Models.*, Helper.*"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%
//    if (session == null || session.getAttribute("username") == null) {
//        response.sendRedirect("Login.jsp");
//        return;
//    }
    int userId = 1; //session.getAttribute("username");
    String roomId = request.getParameter("roomid");

    UserService userService = DIResolver.resolve(UserService.class);
    MessageService messageService = DIResolver.resolve(MessageService.class);
    ChatRoomService chatRoomService = DIResolver.resolve(ChatRoomService.class);

    List<ChatRoom> chatRooms = chatRoomService.findAllChatRooms();

    ChatRoom currentRomm = null;
    int RoomId;
    try {
        RoomId = Integer.parseInt(roomId);
    } catch (NumberFormatException e) {
        RoomId = -1;
    }


%>
<jsp:include page="header.jsp"/>

<div class="container-fluid m-0 p-0 text-center content">
    <div class="row">
        <div class="col-md-4 pr-0 d-none d-md-block">
            <div class="card">
                <div class="card-body">
                    <div class="mb-3">
                        <input type="text" class="form-control" id="exampleFormControlInput1" placeholder="name@example.com">
                            <svg id="status" fill="yellow" height="20px" width="20px" version="1.1" id="Capa_1" xmlns="http://www.w3.org/2000/svg" xmlns:xlink="http://www.w3.org/1999/xlink" 
                                 viewBox="0 0 365.892 365.892" xml:space="preserve">
                                <g>
                                    <circle cx="182.945" cy="286.681" r="41.494"/>
                                    <path d="M182.946,176.029c-35.658,0-69.337,17.345-90.09,46.398c-5.921,8.288-4.001,19.806,4.286,25.726
                                          c3.249,2.321,6.994,3.438,10.704,3.438c5.754,0,11.423-2.686,15.021-7.724c13.846-19.383,36.305-30.954,60.078-30.954
                                          c23.775,0,46.233,11.571,60.077,30.953c5.919,8.286,17.437,10.209,25.726,4.288c8.288-5.92,10.208-17.438,4.288-25.726
                                          C252.285,193.373,218.606,176.029,182.946,176.029z"/>
                                    <path d="M182.946,106.873c-50.938,0-99.694,21.749-133.77,59.67c-6.807,7.576-6.185,19.236,1.392,26.044
                                          c3.523,3.166,7.929,4.725,12.32,4.725c5.051-0.001,10.082-2.063,13.723-6.116c27.091-30.148,65.849-47.439,106.336-47.439
                                          s79.246,17.291,106.338,47.438c6.808,7.576,18.468,8.198,26.043,1.391c7.576-6.808,8.198-18.468,1.391-26.043
                                          C282.641,128.621,233.883,106.873,182.946,106.873z"/>
                                    <path d="M360.611,112.293c-47.209-48.092-110.305-74.577-177.665-74.577c-67.357,0-130.453,26.485-177.664,74.579
                                          c-7.135,7.269-7.027,18.944,0.241,26.079c3.59,3.524,8.255,5.282,12.918,5.281c4.776,0,9.551-1.845,13.161-5.522
                                          c40.22-40.971,93.968-63.534,151.344-63.534c57.379,0,111.127,22.563,151.343,63.532c7.136,7.269,18.812,7.376,26.08,0.242
                                          C367.637,131.238,367.745,119.562,360.611,112.293z"/>
                                </g>
                            </svg>
                    </div>
                </div>
            </div>
            <div class="group-list">

                <%  for (ChatRoom chatRoom : chatRooms) {

                        if (chatRoom.getId() == RoomId) {
                            currentRomm = chatRoom;
                        }
                %>

                <jsp:include page="chatcard.jsp" >
                    <jsp:param name="id" value="<%= chatRoom.getId()%>" />
                    <jsp:param name="name" value="<%= chatRoom.getRoomName()%>" />
                    <jsp:param name="lastmsg" value="<%= chatRoom.getLastMessage()%>" />
                    <jsp:param name="lastmsgtime" value="<%= DateTimeHelper.formatDateTime(chatRoom.getLastMeesageDateTime())%>" />
                </jsp:include>

                <%
                    }
                %>


            </div>
        </div>
        <div class="col pl-0">
            <%
                if (currentRomm != null) {
            %>
            <div class="chat-container">
                <!-- Chat Header -->
                <div class="chat-header d-flex justify-content-between align-items-center">
                    <div>
                        <img src="https://via.placeholder.com/40" class="rounded-circle" alt="Profile Picture">
                            <span class="ms-2"><%= currentRomm.getRoomName()%></span>
                    </div>
                    <button class="btn btn-light">Logout</button>
                </div>

                <!-- Chat Body -->
                <div id="chatBody" class="chat-body">

                    <%
                        for (Message chat : messageService.findMessagesByRoomId(currentRomm.getId())) {
                            boolean isSentByCurrentUser = chat.getSenderid() == userId;
                            String messageClass = isSentByCurrentUser ? "sent" : "received";
                            String messageTime = chat.getDatetime().format(DateTimeFormatter.ofPattern("hh:mm a")); // Format time as needed
%>
                    <div class="message <%= messageClass%>">
                        <p><%= chat.getMessage()%></p>
                        <small><%= messageTime%></small>
                    </div>
                    <%
                        }
                    %>
                </div>
                <!-- Chat Input -->
                <div class="chat-input d-flex align-items-center">
                    <input onkeydown="handleEnterKey(event, this);" id="messageInput" class="form-control" type="text" placeholder="Type a message...">
                        <button id="sendButton" class="btn ms-2">Send</button>
                </div>
            </div>
            <% }%>
        </div>
    </div>
</div> 
</div>

<script>
    // Retry settings
    const MAX_RETRIES = 5;
    let retryCount = 0;
    let ws;
    let wsStatus = WebSocket.CLOSED;
    var username = '<%= session.getAttribute("username")%>';
    //var ws = new WebSocket('ws://localhost:8080/ChatPy/chat');

    function connectWebSocket() {
        updateStatus('Connecting...', 'connecting');
        ws = new WebSocket('ws://192.168.0.105:8080/ChatPy/chat');

        ws.onopen = () => {
            console.log('Connected to the server');
            //retryCount = 0; // Reset retry count on successful connection

    <%if (RoomId > 0) {%>
            const registerRoom = {
                "EventName": "RegisterIntrestedRoom",
                "data": {
                    "id": <%= RoomId%>
                }};
            ws.send(JSON.stringify(registerRoom));
    <%}%>

        };

        ws.onmessage = (event) => {
            var chatMessage = JSON.parse(event.data);
            //appendChatMessage(chatMessage.data);
            handleSocketEvent(chatMessage);
        };

        ws.onclose = () => {
             updateStatus('Disconnected', 'disconnected');
            console.log('Disconnected from the server');
            if (retryCount < MAX_RETRIES) {
                retryCount++;
                console.log(`Retrying connection (\${retryCount}/\${MAX_RETRIES})...`);
                setTimeout(connectWebSocket, 3000); // Retry after 2 seconds
            } else {
                console.log('Max retries reached. Refreshing the page...');
                // Refresh the page after max retries
                window.location.reload();
            }
        };

        ws.onerror = (error) => {
            console.log('WebSocket Error:', error);
            ws.close(); // Close the connection on error to trigger retry
        };

        checkConnectionState();
    }

    let sendbtn = document.getElementById('sendButton');

    if (sendbtn != null) {
        sendbtn.onclick = sendmessage;
    }

    function sendmessage() {
        var recipient = "username";
        var input = document.getElementById('messageInput');
        const chatMessageJson = {
            "EventName": "Message",
            "data": {
                "id": 1,
                "roomId": <%= RoomId%>,
                "senderId": <%= userId%>,
                "reciverId": 202,
                "datetime": new Date(),
                "message": input.value
            }};
        ws.send(JSON.stringify(chatMessageJson));
        input.value = '';
    }

    function logout() {
        ws.close();
        window.location.href = "logout.jsp";
    }

    function appendChatMessage(chat) {
        const chatBody = document.getElementById('chatBody');
        const isSentByCurrentUser = chat.senderId === <%= userId%>; // Replace with logic to determine current user
        const messageClass = isSentByCurrentUser ? 'sent' : 'received';
        const messageTime = new Date(chat.datetime).toLocaleTimeString([], {hour: '2-digit', minute: '2-digit'});

        const messageDiv = document.createElement('div');
        messageDiv.className = 'message ' + messageClass;
        messageDiv.innerHTML = '<p>' + chat.message + '</p><small>' + messageTime + '</small>';

        chatBody.appendChild(messageDiv);
        chatBody.scrollTop = chatBody.scrollHeight; // Scroll to the bottom
    }

    function handleEnterKey(event) {
        if (event.key === 'Enter') {
            // Call your method here
            sendmessage();
        }
    }

    function handleSocketEvent(message) {
        const event = message;
        switch (event.EventName) {
            case "Message":
                appendChatMessage(event.data);
                break;
            case "Connected":
                retryCount = 0;
                updateStatus('Connected', 'connected');
                break;
            default:
                console.log("Unknown event type:", event.eventName);
        }
    }

    function isWebSocketOpen() {
        return ws && ws.readyState === WebSocket.OPEN;
    }

    function checkConnectionState() {
        const checkInterval = setInterval(() => {
            if (ws.readyState === WebSocket.CONNECTING) {
                updateStatus('Connecting...', 'connecting');
            } else if (ws.readyState === WebSocket.OPEN) {
                updateStatus('Connected', 'connected');
                clearInterval(checkInterval); // Stop checking once connected
            } else if (ws.readyState === WebSocket.CLOSING || ws.readyState === WebSocket.CLOSED) {
                updateStatus('Disconnected', 'disconnected');
                clearInterval(checkInterval); // Stop checking if connection is closed
            }
        }, 1000); // Check every 1 second
    }

    function updateStatus(message, statusClass) {
        const statusElement = document.getElementById('status');
        let color = 'yellow';
        if(statusClass=='disconnected'){
            color = 'red';
        }else if(statusClass=='connected'){
            color = 'green';
        }
        statusElement.setAttribute('fill', color);
    }

    connectWebSocket();

</script>
<jsp:include page="footer.jsp"/>

