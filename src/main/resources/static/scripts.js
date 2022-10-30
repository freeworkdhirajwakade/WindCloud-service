var stompClient = null;
var notificationCount = 0;

$(document).ready(function() {
    console.log("Index page is ready");
    connect();

    $("#send").click(function() {
		sendMessage();
    });

    $("#send-private").click(function() {
        sendPrivateMessage();
    });

    $("#notifications").click(function() {
        resetNotificationCount();
    });
});

function connect() {
	
	var wsUri =getBaseUrl() + "/our-websocket";
	console.log("URL="+wsUri);
    var socket = new SockJS(wsUri);
    stompClient = Stomp.over(socket);
    stompClient.connect({}, function (frame) 
    {
        console.log('Connected: ' + frame);
        updateNotificationDisplay();
        stompClient.subscribe('/topic/messages', function (message) {
			showMessage(JSON.parse(message.body).content,JSON.parse(message.body).username);
        });

        stompClient.subscribe('/user/topic/private-messages', function (message) {
            showMessage(JSON.parse(message.body).content,JSON.parse(message.body).username);
        });

        stompClient.subscribe('/topic/global-notifications', function (message) {
            notificationCount = notificationCount + 1;
            updateNotificationDisplay();
        });

        stompClient.subscribe('/user/topic/private-notifications', function (message) {
            notificationCount = notificationCount + 1;
            updateNotificationDisplay();
        });
    });
}

function showMessage(message,name) {
	$("#message-chat-div").addClass("open_div");
	$("#message-chat-div").removeClass("close_div");
	
	var nameLocalStore=localStorage.getItem("username");
	if(nameLocalStore==name)
	{
		 $("#messages-body").append("<tr><td style='width:30%'>You</td><td style='width:70%'>" + message + "</td></tr>");
	}
	else
	{
		$("#messages-body").append("<tr><td style='width:30%'>"+name+"</td><td style='width:70%'>" + message + "</td></tr>");
	}
	
   
}

function sendMessage() {
	
    var name=localStorage.getItem("username");
    
    $("#your-name").html(name);
    stompClient.send("/ws/message", {}, JSON.stringify({'content': $("#message").val(),'username':name}));
    $("#message").val("");
}

function sendPrivateMessage() {
    console.log("sending private message");
    stompClient.send("/ws/private-message", {}, JSON.stringify({'messageContent': $("#private-message").val()}));
}

function updateNotificationDisplay() {
    if (notificationCount == 0) {
        $('#notifications').hide();
    } else {
        $('#notifications').show();
        $('#notifications').text(notificationCount);
    }
}

  function getBaseUrl()
     {
		 var host = window.location.origin;		
		var contextpath = window.location.pathname.split( '/' )[1];
		var baseUrl=host+"/"+contextpath;
		return baseUrl;
	 }



function resetNotificationCount() {
    notificationCount = 0;
    updateNotificationDisplay();
}