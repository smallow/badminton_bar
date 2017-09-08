$(function(){
    var $navHeaderLink = $("#publicTpl .panel-group .panel-body .list-group>a");
    var $navDiv = $("#publicTpl .panel-default");

    $navHeaderLink.on('click',function(){
        var $linkListParents = $(this).parents(".panel-collapse");
        if($linkListParents.hasClass("in")){
            var $index = $linkListParents.parents(".panel-default").index();
            localStorage.setItem("commonLeftNavIndex",$index)
        }

    })
    var $navIndex = parseInt(localStorage.getItem('commonLeftNavIndex'));
    $navDiv.eq($navIndex).find(".panel-collapse").addClass("in").parents(".panel-default").siblings().find(".panel-collapse").removeClass("in");


})