<div class="modal-header">
    <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">×</span></button>
    <h4 class="modal-title">新建群组</h4>
</div>
<div class="modal-body" style="">
    <form class="form-horizontal" action="/badminton/admin/group/save" id="add_group_form" method="post">
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
            <div class="col-sm-8">
                    <div class="row clearfix">
                        <div class="col-md-6 column">
                            <select id="provinceCode" name="provinceCode" onchange="provinceChange()">
                                <option>省份</option>
                                <#if group??>
                                    <#list province as item>
                                        <#if group.provinceCode==item.code>
                                            <option value="${item.code}" selected>${item.name}</option>
                                        <#else>
                                            <option value="${item.code}">${item.name}</option>
                                        </#if>
                                    </#list>
                                <#else>
                                    <#list province as item>
                                            <option value="${item.code}">${item.name}</option>
                                    </#list>
                                </#if>
                            </select>
                        </div>
                        <div class="col-md-6 column">
                            <select id="cityCode" name="cityCode">
                                <option>城市</option>
                            </select>
                        </div>
                    </div>
                    <div class="row clearfix" style="margin-top: 10px;">
                        <div class="col-md-6 column">
                            <select id="areaCode" name="areaCode">
                                <option>区域</option>
                            </select>
                        </div>
                        <div class="col-md-6 column">
                            <select id="groupArenaCode" name="groupArenaCode">
                                <option>球馆</option>
                            </select>
                        </div>
                    </div>
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
                    <img src="/badminton/admin/getQrCodePic/groupReg_${qr_code}" width="100" height="100" border="0" />
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
</div>
<div class="modal-footer">
    <button type="button" class="btn btn-default poll-left" data-dismiss="modal">取消</button>
    <button type="button" id="saveBtn" class="btn btn-info" onclick="save()">保存</button>
</div>
<script>
    var webSocket=null;
    var scanFlag=null;
    var qr_code='';
    var city=[];
    var area=[];
    var arena=[];
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
         city=${city!''};
         area=${area!''};
         arena=${arena!''};
    });

    function provinceChange() {
        $("#cityCode").html("");
        var provinceCode=$("#provinceCode").val();
        var tmp=city;
        var tmp2=$("#groupCityCode").val();
        if(provinceCode=="110000" || provinceCode=="120000" || provinceCode=="310000" ||provinceCode=="500000"){
            tmp=area;
            tmp2=$("#groupAreaCode").val();
        }

        $.each(tmp,function(index,val){
            if(val.parent==provinceCode){
                var option=$("<option value='"+val.code+"'>"+val.name+"</option>");
                if(tmp2==val.code){
                    option=$("<option value='"+val.code+"' selected>"+val.name+"</option>");
                }
                $("#cityCode").append(option);
            }
        })
    }

    function display(group) {
        $("#group_id").val(group.groupId);
        $("#group_manager_name").val(group.groupManagerName);
        $("#group_manager_phone").val(group.groupManagerPhone);
        $("#group_manager_id_number").val(group.groupManagerIdNumber);

        $("#group_name").val(group.groupName);
        $.each($("input[name=groupCheck]"),function (index, obj) {
           if(group.groupCheck==$(this).val()){
               $(this).attr("checked","checked");
           }
        });
    }
    function save() {
        $("#add_group_form").submit();
    }

    function createWebSocket(callback) {
        if ('WebSocket' in window) {
            webSocket = new WebSocket('ws://smallow.top/badminton/webSocket2/groupReg/'+qr_code);
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