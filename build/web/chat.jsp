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
        <div class="col-4 pr-0">
            <div class="card">
                <div class="card-body">
                    <div class="mb-3">
                        <input type="text" class="form-control" id="exampleFormControlInput1" placeholder="name@example.com">
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
                            String messageTime = chat.getDatetime().format(DateTimeFormatter.ofPattern("h:mm a")); // Format time as needed
                    %>
                    <div class="message <%= messageClass%>">
                        <p><%= chat.getMessage() %></p>
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
    var username = '<%= session.getAttribute("username")%>';
    var ws = new WebSocket('ws://localhost:8080/ChatPy/chat');

    ws.onmessage = function (event) {
        var chatMessage = JSON.parse(event.data);
        appendChatMessage(chatMessage);
    };

    document.getElementById('sendButton').onclick = sendmessage;
            
    function sendmessage () {
        var recipient = "username";
        var input = document.getElementById('messageInput');
        const chatMessageJson = {
            "id": 1,
            "roomId": <%= RoomId %>,
            "senderId": <%= userId %>,
            "reciverId": 202,
            "datetime": "2024-08-09T12:34:56",
            "message": input.value
        };

        ws.send(JSON.stringify(chatMessageJson));
        input.value = '';
    };

    function logout() {
        ws.close();
        window.location.href = "logout.jsp";
    }

    function appendChatMessage(chat) {
        const chatBody = document.getElementById('chatBody');
        const isSentByCurrentUser = chat.senderId === <%= userId %>; // Replace with logic to determine current user
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

</script>
<jsp:include page="footer.jsp"/>

