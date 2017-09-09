<div class="modal-header">
    <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">×</span></button>
    <h4 class="modal-title">新建群组</h4>
</div>
<div class="modal-body" style="">
    <form class="form-horizontal" action="/badminton/admin/group/save" id="add_group_form" method="post">
        <div class="form-group">
            <label for="site_num" class="col-sm-4 control-label">申请人名称</label>
            <div class="col-sm-8">
                <input type="text" class="form-control" value="" name="group_manager_name" id="group_manager_name" >
            </div>
        </div>
        <div class="form-group">
            <label for="site_num" class="col-sm-4 control-label">申请人身份证号</label>
            <div class="col-sm-8">
                <input type="text" class="form-control" value="" name="group_manager_id_number" id="group_manager_id_number" >
            </div>
        </div>
        <div class="form-group">
            <label for="site_num" class="col-sm-4 control-label">申请人手机号</label>
            <div class="col-sm-8">
                <input type="text" class="form-control" value="" name="group_manager_phone" id="group_manager_phone" >
            </div>
        </div>
        <div class="form-group">
            <label for="site_num" class="col-sm-4 control-label">群名称</label>
            <div class="col-sm-8">
                <input type="text" class="form-control" value="" name="group_name" id="group_name" >
            </div>
        </div>
        <div class="form-group">
            <label for="site_num" class="col-sm-4 control-label">加群是否要审核</label>
            <div class="col-sm-8">
                <div class="radio">
                    <label><input type="radio"  value="0" name="group_check" id="group_check_y"/>是</label>
                    <label><input type="radio"  value="1" name="group_check" id="group_check_n"/>否</label>
                </div>

            </div>
        </div>
    </form>
</div>
<div class="modal-footer">
    <button type="button" class="btn btn-default poll-left" data-dismiss="modal">取消</button>
    <button type="button" id="saveBtn" class="btn btn-info">保存</button>
</div>