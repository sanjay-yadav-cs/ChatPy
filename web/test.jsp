<!DOCTYPE html>
<html>
<head>
    <title>Chat Room</title>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css">
</head>
<body>
    <nav class="navbar navbar-expand-lg navbar-light bg-light">
        <a class="navbar-brand" href="#">Chat Room Name</a>
        <button class="btn btn-outline-danger my-2 my-sm-0" type="button">Logout</button>
    </nav>
    <div class="container">
        <div class="row">
            <div class="col-12">
                <div id="messageArea" class="overflow-auto" style="height: 400px;">
                    <!-- Messages will be dynamically inserted here -->
                </div>
            </div>
        </div>
    </div>
    <div class="container">
        <div class="row">
            <div class="col-10">
                <input id="messageInput" type="text" class="form-control" placeholder="Type a message...">
            </div>
            <div class="col-2">
                <button id="sendButton" class="btn btn-primary btn-block">Send</button>
            </div>
        </div>
    </div>
    <script>
        var ws = new WebSocket('ws://localhost:8080/ChatPy/chat');
        
        ws.onmessage = function(event) {
            var messageArea = document.getElementById('messageArea');
            var message = document.createElement('div');
            message.textContent = event.data;
            messageArea.appendChild(message);
            messageArea.scrollTop = messageArea.scrollHeight;
        };

        document.getElementById('sendButton').onclick = function() {
            var input = document.getElementById('messageInput');
            ws.send(input.value);
            input.value = '';
        };
    </script>
</body>
</html>
