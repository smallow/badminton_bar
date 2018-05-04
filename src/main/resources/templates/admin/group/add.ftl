<div class="modal-header">
    <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">×</span></button>
    <h4 class="modal-title">新建群组</h4>
</div>
<div class="modal-body" style="">
    <form class="form-horizontal" action="${request.contextPath}/admin/group/save" id="add_group_form" method="post">
        <div class="form-group">
            <label for="site_num" class="col-sm-4 control-label">申请人名称</label>
            <div class="col-sm-8">
                <input type="text" class="form-control" value="${(group.groupManagerName)!""}" name="groupManagerName" id="group_manager_name" >
            </div>
        </div>
        <div class="form-group">
            <label for="site_num" class="col-sm-4 control-label">申请人身份证号</label>
            <div class="col-sm-8">
                <input type="text" class="form-control" value="${(group.groupManagerIdNumber)!""}" name="groupManagerIdNumber" id="group_manager_id_number" >
            </div>
        </div>
        <div class="form-group">
            <label for="site_num" class="col-sm-4 control-label">申请人手机号</label>
            <div class="col-sm-8">
                <input type="text" class="form-control" value="${(group.groupManagerPhone)!""}" name="groupManagerPhone" id="group_manager_phone" >
            </div>
        </div>
        <div class="form-group">
            <label for="site_num" class="col-sm-4 control-label">群组名称</label>
            <div class="col-sm-8">
                <input type="text" class="form-control" value="${(group.groupName)!""}" name="groupName" id="group_name" >
            </div>
        </div>
        <div class="form-group">
            <label for="site_num" class="col-sm-4 control-label">群组规模(人)</label>
            <div class="col-sm-8">
                <input type="text" class="form-control" value="${(group.groupScale)!""}" name="groupScale" id="group_scale" >
            </div>
        </div>
        <div class="form-group">
            <label for="site_num" class="col-sm-4 control-label">活动场馆</label>
            <div class="col-sm-8" id="cascade_area">

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
        <#if qr_code??>
            <div class="form-group">
                <label for="site_num" class="col-sm-4 control-label">申请人扫码确认</label>
                <div class="col-sm-8">
                    <img src="${request.contextPath}/admin/getQrCodePic/groupReg_${qr_code}" width="100" height="100" border="0" />
                    <span id="scanResultMsg" style="color: #00CD00;"></span>
                </div>
            </div>
        <#else>

        </#if>
        <input type="hidden" name="groupId" id="group_id" value=""/>
        <input type="hidden" name="openid" id="openid" value=""/>

        <input type="hidden" name="province" id="province" value=""/>
        <input type="hidden" name="city" id="city" value=""/>
        <input type="hidden" name="area" id="area" value=""/>
        <input type="hidden" name="groupArena" id="groupArena" value=""/>

    </form>
    <input type="hidden" id="groupCityCode" value="${(group.cityCode)!''}"/>
    <input type="hidden" id="groupAreaCode" value="${(group.areaCode)!''}"/>
    <input type="hidden" id="groupArenaCode" value="${(group.groupArenaCode)!''}"/>
</div>
<div class="modal-footer">
    <button type="button" class="btn btn-default poll-left" data-dismiss="modal">取消</button>
    <button type="button" id="saveBtn" class="btn btn-info" onclick="save()">保存</button>
</div>
<script>
    var webSocket=null;
    var scanFlag=null;
    var qr_code='';
   // var list=[];
    $(function () {
        if(qr_code!=""){
            createWebSocket(function () {
                sendMsg("groupReg_"+qr_code);
                setTimeout(function () {
                    clearInterval(scanFlag);
                    //getQrCode();
                },300*1000);
            });
        }
        //list=${list!''};
//        var cascade=new Cascade(list);
//        var provinceCheckList=cascade.cascade();
//        $("#cascade_area").append(provinceCheckList);
        $("#cascade_area").loadSelect({url:"${request.contextPath}/common/findAreaByPCode",pcode:"999"});
    });






    function save() {
        $("#add_group_form").submit();
    }

    function createWebSocket(callback) {
        if ('WebSocket' in window) {
            webSocket = new WebSocket('ws://smallow.top${request.contextPath}/webSocket2/groupReg/'+qr_code);
        } else {
            alert('该浏览器不支持websocket!');
        }
        webSocket.onopen = function (event) {
            console.log('建立连接');
            callback();
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