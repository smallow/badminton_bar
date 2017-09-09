<div class="modal-header">
    <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">×</span></button>
    <h4 class="modal-title">新建群组</h4>
</div>
<div class="modal-body" style="">
    <form class="form-horizontal" action="/badminton/admin/group/save" id="add_group_form" method="post">
        <div class="form-group">
            <label for="site_num" class="col-sm-4 control-label">申请人名称</label>
            <div class="col-sm-8">
                <input type="text" class="form-control" value="" name="groupManagerName" id="group_manager_name" >
            </div>
        </div>
        <div class="form-group">
            <label for="site_num" class="col-sm-4 control-label">申请人身份证号</label>
            <div class="col-sm-8">
                <input type="text" class="form-control" value="" name="groupManagerIdNumber" id="group_manager_id_number" >
            </div>
        </div>
        <div class="form-group">
            <label for="site_num" class="col-sm-4 control-label">申请人手机号</label>
            <div class="col-sm-8">
                <input type="text" class="form-control" value="" name="groupManagerPhone" id="group_manager_phone" >
            </div>
        </div>
        <div class="form-group">
            <label for="site_num" class="col-sm-4 control-label">群名称</label>
            <div class="col-sm-8">
                <input type="text" class="form-control" value="" name="groupName" id="group_name" >
            </div>
        </div>
        <div class="form-group">
            <label for="site_num" class="col-sm-4 control-label">加群是否要审核</label>
            <div class="col-sm-8">
                <div class="radio">
                    <label><input type="radio"  value="0" name="groupCheck" id="group_check_y"/>是</label>
                    <label><input type="radio"  value="1" name="groupCheck" id="group_check_n"/>否</label>
                </div>

            </div>
        </div>
        <#if qr_code=="">
        <#else>
            <div class="form-group">
                <label for="site_num" class="col-sm-4 control-label">申请人扫码确认</label>
                <div class="col-sm-8">
                    <img src="/badminton/admin/getQrCodePic/group_reg_${qr_code}" width="100" height="100" border="0" />
                    <span id="scanResultMsg" style="color: #00CD00;"></span>
                </div>
            </div>
        </#if>
        <input type="hidden" name="groupId" id="group_id" value=""/>
        <input type="hidden" name="openid" id="openid" value=""/>
    </form>
</div>
<div class="modal-footer">
    <button type="button" class="btn btn-default poll-left" data-dismiss="modal">取消</button>
    <button type="button" id="saveBtn" class="btn btn-info" onclick="save()">保存</button>
</div>
<script>
    var webSocket=null;
    var scanFlag=null;
    $(function () {
        var group="${(group)!''}";
        if(group!="" && group!=null){
            display(group);
        }
        var qr_code="${qr_code!''}";
        if(qr_code!=""){
            createWebSocket();
            sendMsg("group_reg_"+qr_code);
            setTimeout(function () {
                clearInterval(scanFlag);
                //getQrCode();
            },300*1000);
        }
    });

    function display(group) {
        $("#group_id").val(group.groupId);
        $("#group_manager_name").val(group.groupManagerName);
        $("#group_manager_phone").val(group.groupManagerPhone);
        $("#group_manager_id_number").val(group.groupManagerIdNumber);
    }
    function save() {
        $("#add_group_form").submit();
    }

    function createWebSocket() {
        if ('WebSocket' in window) {
            webSocket = new WebSocket('ws://smallow.top/badminton/webSocket2');
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
            var openid=array[1];
            if (sign == "success") {
                webSocket.close();
                clearInterval(scanFlag);
                $("#openid").val(openid);
                $("#scanResultMsg").html("确认成功");
            }
        };
        webSocket.onerror = function () {
            alert('websocket通信发生错误！');
        };

        window.onbeforeunload = function () {
            webSocket.close();
            clearInterval(scanFlag);
        };
    }

    function sendMsg(msg) {
        scanFlag=setInterval(function () {
            console.log("sendMsg");
            if(webSocket.readyState==1){
                webSocket.send(msg);
            }
        },2000);
    }
</script>