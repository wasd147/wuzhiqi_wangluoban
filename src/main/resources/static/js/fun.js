var socket;

function getSocket() {
    var socketUrl = "http://localhost:8080/hellosocket/" + user;
    console.log(socketUrl)
    socketUrl = socketUrl.replace("https", "ws").replace("http", "ws");
    socket = new WebSocket(socketUrl);//得到一个连接完毕的socket
    //关闭事件
    socket.onclose = function () {
        console.log("websocket已关闭");
    };
    //发生了错误事件
    socket.onerror = function () {
        console.log("websocket发生了错误");
    }
    socket.onopen = function (e) {
        console.log("websocket已打开");

    }
    //获得消息事件
    socket.onmessage = function (msg) {
        console.log("服务器发来消息", msg.data);
        if (msg.data == "whiteIsIn") {
            whiteIsInfun();
            signalmine()
        } else {
            var location = msg.data;
            var x = location.split(",")[0];
            var y = location.split(",")[1];
            luozi(x, y);
            enable = true;
            signalmine();
        }
        //发现消息进入    开始处理前端触发逻辑
    }


}

function sendMsg() {

}
