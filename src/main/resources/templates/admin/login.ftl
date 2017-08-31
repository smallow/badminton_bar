<html>
<#include "../common/header.ftl">
<script src="https://cdn.bootcss.com/jquery/1.12.4/jquery.min.js"></script>
<script src="https://cdn.bootcss.com/bootstrap/3.3.5/js/bootstrap.min.js"></script>
<script src="/badminton/js/common.js"></script>
<body>
<style>
    .successLoginMsg{
        color: #00CD00;
    }
    #loginMsg{
        margin-top: 5px;
        font-size: 18px;
    }

</style>


<div class="container">
    <div class="row clearfix">
        <div class="col-md-12 column badminton-div-center" style="text-align: center;">
            <img   id="qr_code" alt="140x140" src="" class="img-thumbnail" />
            <p id="loginMsg">小主不要心急羽球君马上就来...</p>
        </div>
    </div>
    <div class="loading">
        <span></span>
        <span></span>
        <span></span>
        <span></span>
        <span></span>
        <span></span>
        <span></span>
    </div>

</div>

<script>
    var webSocket=null;
    var loginFlag=null;
    var i=0;
    $(function () {
        createWebSocket();
        getQrCode();
    });


    function sendMsg(msg) {
        loginFlag=setInterval(function () {
            console.log("sendMsg");
            if(webSocket.readyState==1){
                webSocket.send(msg);
            }
        },2000);
    }
    function getQrCode() {
        if(i<10){
            $.post("/badminton/admin/getQrCode",{},function (data) {
                if(data.qr_code && data.qr_code!=""){
                    $("#qr_code").attr("src","/badminton/admin/getQrCodePic/"+data.qr_code+"");
                    setTimeout(function () {
                        $("#loginMsg").html("请使用群管理员绑定微信号扫描登录");
                        $(".loading").hide();
                    },1500);
                    sendMsg(data.qr_code);
                    setTimeout(function () {
                        clearInterval(loginFlag);
                        getQrCode();
                    },300*1000);
                }
            },'json');
            i++;
        }

    }

    function createWebSocket() {
        if ('WebSocket' in window) {
            webSocket = new WebSocket('ws://smallow.top/badminton/webSocket');
        } else {
            alert('该浏览器不支持websocket!');
        }
        webSocket.onopen = function (event) {
            console.log('建立连接');
        };
        webSocket.onclose = function (event) {
            console.log('连接关闭');
        };
        webSocket.onmessage = function (event) {
            console.log('收到消息:' + event.data);
            var array = event.data.split("_");
            var sign=array[0];
            var scene_str=array[1];
            if (sign == "success") {
                $("#loginMsg").html("扫描成功正在登录!");
                $("#loginMsg").addClass("successLoginMsg");
                setTimeout(function () {
                    goLogin(scene_str);
                },1500);
                webSocket.close();
                clearInterval(loginFlag);
            }
        };
        webSocket.onerror = function () {
            alert('websocket通信发生错误！');
        };

        window.onbeforeunload = function () {
            webSocket.close();
            clearInterval(loginFlag);
        };
    }



    function goLogin(scene_str) {
        httpFormPost("/badminton/admin/adminMain", {scene_str: scene_str});
    }
</script>



</body>

</html>