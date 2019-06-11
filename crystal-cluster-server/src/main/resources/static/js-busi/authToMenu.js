var Menu={
     authToMenu:function(authorityId) {
    $("#roleMenu").empty();
    $("#roleMenu").append("<div class=\"overlay\">\n" +
        "          <i class=\"fa fa-refresh fa-spin\"></i>\n" +
        "        </div>");
    var params = {
        authorityId : authorityId
    };
    var url= WEB_ROOT + "/admin/manageAuthority/authToMenu";
    $.ajax({
        url:url,
        async : true,
        cache:false,
        type : "GET",
        dataType : "html",
        data : params,
        success:function(data){
            $("#roleMenu").empty();
            $("#roleMenu").html(data);
        },
        error:function (XMLHttpRequest, textStatus, errorThrown) {
            tab.alertTitle(textStatus,null);
            
        }
    });
}
}