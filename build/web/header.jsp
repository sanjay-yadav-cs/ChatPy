<!doctype html>
<html lang="en">
  <head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>Bootstrap demo</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH" crossorigin="anonymous">
  </head>
  <body>
  <style>
    body {
        background-color: #f8f9fa;
    }
    .group-list {
        max-width: 600px;
        margin: auto;
        background: #ffffff;
        /*border-radius: 8px;*/
        box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
        max-height: 90vh;
        overflow-y:auto;
        margin-bottom: 35px;
    }
    .group-item {
        cursor: pointer;
        display: flex;
        text-align: left;
        align-items: flex-start;
        padding: 15px;
        border-bottom: 1px solid #e9ecef;
        white-space: nowrap;
        overflow: hidden;
        text-overflow: ellipsis;
    }

    .group-item:hover{
        background-color: #E9EAEC;
    }

    .group-item:last-child {
        border-bottom: none;
    }
    .group-avatar {
        width: 50px;
        height: 50px;
        border-radius: 50%;
        overflow: hidden;
        margin-right: 15px;
    }
    .group-avatar img {
        width: 100%;
        height: 100%;
        object-fit: cover;
    }
    .group-info {
        flex: 1;
    }
    .group-name {
        font-size: 1.1rem;
        font-weight: bold;
    }
    .group-last-message {
        color: #6c757d;
    }

    .chat-container {
        height: 100vh;
        display: flex;
        flex-direction: column;
    }
    .chat-header {
        background-color: #075E54;
        color: white;
        padding: 1rem;
    }
    .chat-body {
        flex: 1;
        overflow-y: scroll;
        padding: 1rem;
        background-color: #ECE5DD;
    }
    .message {
        max-width: 60%;
        margin-bottom: 1rem;
    }
    .message.sent {
        align-self: flex-end;
        background-color: #DCF8C6;
        border-radius: 15px 15px 0 15px;
        padding: 0.5rem 1rem;
        text-align: right;
        margin-left: auto;
    }
    .message.received {
        align-self: flex-start;
        background-color: white;
        border-radius: 15px 15px 15px 0;
        padding: 0.5rem 1rem;
        text-align: left;
    }
    .chat-input {
        border-top: 1px solid #ddd;
        padding: 0.5rem;
        background-color: #fff;
    }
    .chat-input input {
        /*border: none;*/
        border-radius: 20px;
        padding: 0.5rem 1rem;
        width: calc(100% - 60px);
    }
    .chat-input button {
        border: none;
        background-color: #075E54;
        color: white;
        border-radius: 20px;
        padding: 0.5rem 1rem;
    }

    .pr-0{
        padding-right: 0 !important;
    }

    .pl-0{
        padding-left: 0 !important;
    }

    html, body {
        overflow: hidden;
        margin: 0;
        padding: 0;
        height: 100%;
        width: 100%;
        display: flex;
        align-items: center;
        justify-content: center;
        background-color: #f0f0f0;
    }
    .content {
        background-color: #fff;
        padding: 20px;
        border-radius: 8px;
        box-shadow: 0 4px 8px rgba(0, 0, 0, 0.2);
        position: relative;
        top:20px;
    }

    .card {
        text-align: left;
        border-radius: 0 !important;
        border: 0 !important;
    }
    
    a{
        text-decoration: none;
        color: unset;
    }

</style>    