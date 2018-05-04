// /**
//  * Created by wanghuidong on 2018/3/22.
//  */
function Cascade(data) {
    this.data = data;
}

// var zzChildren = [
//     {
//         name: "中原区",
//         code: "410102",
//         children: []
//     },
//     {
//         name: "二七区",
//         code: "410103",
//         children: []
//     }
// ];
//
// var bjChildren = [
//     {
//         name: "东城区",
//         code: "110101",
//         children: []
//     },
//     {
//         name: "西城区",
//         code: "110102",
//         children: []
//     }
// ];
// var haChildren = [
//     {
//         name: "郑州市",
//         code: "410100",
//         children: zzChildren
//     },
//     {
//         name: "开封市",
//         code: "410200",
//         children: []
//     }
// ];
//
//
// var demo = [
//     {
//         name: "北京市",
//         code: "110000",
//         children: bjChildren
//     },
//     {
//         name: "河南省",
//         code: "410000",
//         children: haChildren
//     }
// ];
// Cascade.prototype.cascade = function () {
//     var obj = this;
//     var firstLevel = createCheckList(obj.data);
//     return firstLevel;
// };
// Cascade.prototype.onSelected=function (data,obj) {
//     var parentCode=$(this).val();
//     var _data=getChildrenByCode(parentCode,data);
//     if(_data.length>0){
//         var tmp=createCheckList(_data);
//         obj.css("margin-right","5px");
//         obj.after(tmp);
//     }
// };
//
// function createCheckList(data) {
//     var select = $("<select></select>");
//     $.each(data, function (index, val) {
//         var option = $("<option value='" + val.code + "'>" + val.name + "</option>");
//         select.append(option);
//     });
//     select.bind("change",function () {
//         var next=select.next();
//         if(next){
//             var _next=next.next();
//             if(_next){
//                 var __next=_next.next();
//                 if(__next)
//                     __next.remove();
//                 _next.remove();
//             }
//             next.remove();
//         }
//         select.onSelected(data,select);
//     });
//     return select;
// }
//
// function getChildrenByCode(code,data){
//     var _data=[];
//     $.each(data,function (index, val) {
//         if(val.code==code){
//             _data=val.children;
//             return false;
//         }
//     });
//     return _data;
//
// }

(function($){
    $.fn.extend({
        loadSelect:function(opt){
            var defaultOpt={
            };
            var opts = $.extend(defaultOpt,opt);
            var $this=$(this);
            //alert(opts.url+"\n"+opts.pcode);
            $.getJSON(opts.url,{pcode:opts.pcode},function(json){
                var html="<select  onchange=\"javascript:$(this).selectSelect({url:'"+opts.url+"',pcode:this.value})\"><option value=\"0\">请选择</option>";
                $.each(json,function(i,n){
                    html+= '<option value="'+n.code+'">'+n.name+"</option>";
                });
                html += "</select>\n";
                $this.append(html);
            });
        },
        selectSelect:function(opt){
            var defaultOpt={
            };
            var opts = $.extend(defaultOpt,opt);
            var $this=$(this);
            $this.nextAll().remove();
            //alert(opts.url+"\n"+opts.pcode);
            $.getJSON(opts.url,{pcode:opts.pcode},function(json){
                var html="<select  onchange=\"javascript:$(this).selectSelect({url:'"+opts.url+"',pcode:this.value})\"><option value=\"0\">请选择</option>";
                $.each(json,function(i,n){
                    html+= '<option value="'+n.code+'">'+n.name+"</option>";
                });
                html += "</select>\n";
                $this.css("margin-right","5px");
                if (json.length>0){
                    $this.after(html);
                }

            });
        }
    });

})(jQuery);
