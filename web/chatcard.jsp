<%@page import="java.util.List,javax.servlet.http.HttpSession, Repository.*, Models.*"%>
<a href="/ChatPy/chat.jsp?roomid=${param.id}">
<div class="group-item">
    <div class="group-avatar">
        <img src="https://via.placeholder.com/50" alt="Group Avatar">
    </div>
    <div class="group-info">
        <div class="group-name">${param.name}</div>
        <div class="group-last-message">${param.lastmsg}</div>
    </div>
    <div class="text-muted small">${param.lastmsgtime}</div>
</div>
</a>
