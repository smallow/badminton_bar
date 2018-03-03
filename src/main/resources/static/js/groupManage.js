var obj = {
    url: "/badminton/admin/group/list",
    columns: [
        {field: "groupName", title: "群组名称", width: "", height: "", align: "left"},
        {field: "province", title: "省份", width: "", height: "", align: "left"},
        {field: "city", title: "城市", width: "", height: "", align: "left"},
        {field: "area", title: "地区", width: "", height: "", align: "left"},
        {field: "groupScale", title: "规模(人)", width: "", height: "", align: "left"},

        {field: "groupManagerName", title: "群管理员名称", width: "", height: "", align: "left"},
        {field: "groupManagerIdNumber", title: "管理员证件号码", width: "", height: "", align: "left"},
        {field: "groupManagerPhone", title: "管理员手机号", width: "", height: "", align: "left"},
        {field: "groupStatus", title: "状态", width: "", height: "", align: "left"},
        {field: "createTime", title: "创建时间", width: "", height: "", align: "left"},
        {
            field: "operation",
            title: "操作",
            width: "",
            height: "",
            align: "left",
            formatter: function (value, row, index) {
                return "<a href='javascript:void(0);' onclick=\"modify('" + row.groupId + "')\">修改</a>&nbsp;|&nbsp;"
                    + "<a href='javascript:void(0);' onclick=\"del('" + row.groupId + "')\">删除</a>";
            }
        }
    ],
    pageable:true
};