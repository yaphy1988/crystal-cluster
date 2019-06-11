$(document).ready(function() {
    //渲染表格
    $table = $('#userTables');
    processTable($table);
} );

function createNewUser() {
    var url = WEB_ROOT + "/admin/manageUser/newUser";
    window.open(url, "_blank");
}

function  queryUserData(pageno) {
    // debugger
    // 设置参数 pageSize 查询全部再在界面分页
    var userName = $("#queryUserName").val();
    // 属名
    var fullName = $("#queryFullName").val();
    data = {
        pageNo : pageno,
        pageSize : 10000,
        userName :userName,
        fullName:fullName
    };
    $.ajax({
        url : WEB_ROOT + "/admin/manageUser/queryusers",
        data : data,
        async : true,
        type : "GET",
        dataType : "html",
        success : function(data) {
            $("#showusers").html(data);//界面加载完
            //渲染表格
            $table = $('#userTables');
            processTable($table);

        }
    });
}

function processTable($element) {
    $element.DataTable({
        paging      : true,
        lengthChange: false,
        searching   : false,
        ordering    : true,
        info        : true,
        autoWidth   : false,
        language: {
            "sProcessing": "处理中...",
            "sLengthMenu": "显示 _MENU_ 项结果",
            "sZeroRecords": "没有匹配结果",
            "sInfo": "显示第 _START_ 至 _END_ 项结果，共 _TOTAL_ 项",
            "sInfoEmpty": "显示第 0 至 0 项结果，共 0 项",
            "sInfoFiltered": "(由 _MAX_ 项结果过滤)",
            "sInfoPostFix": "",
            "sSearch": "搜索:",
            "sUrl": "",
            "sEmptyTable": "表中数据为空",
            "sLoadingRecords": "载入中...",
            "sInfoThousands": ",",
            "oPaginate": {
                "sFirst": "首页",
                "sPrevious": "上页",
                "sNext": "下页",
                "sLast": "末页"
            },
            "oAria": {
                "sSortAscending": ": 升序",
                "sSortDescending": ": 降序"
            }
        }
    });
};

function closeWindow() {
    window.opener = null;
    window.open('', '_self', '');
    window.close();
}
//保存新用户
function saveNewUser() {
    var username = $("#userName").val();
    var nickname = $("#nickName").val();
    var fullname = $("#fullName").val();
    var mobile = $("#inputPhone").val();
    var email = $("#inputEmail").val();
    var password = $("#inputPassword").val();
    var selectData = $("#inputRoles").select2("data");
    var roles = new Array(selectData.length);
    for (var i=0; i < selectData.length; i++) {
        roles[i] = selectData[i].id;
    }

    var params = {
        username : username,
        nickname : nickname,
        fullname : fullname,
        mobile : mobile,
        email : email,
        password : password,
        roles : roles
    };

    var url= WEB_ROOT + "/admin/manageUser/createUser";
    $.ajax({
        url:url,
        cache:false,
        async:true,
        type:"POST",
        dataType:'json',
        data : params,
        success:function(data){
            if (data.success == true) {
                $("#modal-success").modal("show");
                closeWindow();
            } else {
                tab.alertTitle(data.msg,"")
              //  $("#modal-success").modal("show");
            }
        }
    });

}

//检查用户名
function checkUsername($ele) {
    var username = $ele.value;
    var params = {
        username : username
    };

    var url= WEB_ROOT + "/admin/manageUser/checkname";
    $.ajax({
        url:url,
        cache:false,
        async:true,
        type:"POST",
        data : params,
        success:function(data){
            if (data.success == true) {
                $("#usernameCheckState").removeClass();
                $("#usernameCheckState").addClass("fa fa-check text-success");
                $("#divUserName").removeClass("has-error");
            } else {
                $("#usernameCheckState").removeClass();
                $("#usernameCheckState").addClass("fa fa-times text-red");
                $("#divUserName").addClass("has-error");
            }
        }
    });

}

function showEdit(userId) {
    $("#edit-user-submit").attr("onclick", "userManager.saveEditUser();");
    $('#modal-title').text('修改用户信息');
    var url = WEB_ROOT + "/admin/manageUser/showedit";
    var params = {
        userId : userId
    };
    $.ajax({
        url: url,
        cache: false,
        async: true,
        type: "POST",
        dataType : "html",
        data: params,
        success:function (data) {
            $('#edit-user-form').html(data);
            $('#edit-user').modal("show");
        }
    });
}

function saveEditUser() {
    var params = {
        username : username
    };

    var url= WEB_ROOT + "/admin/manageUser/edituser";
    $.ajax({
        url:url,
        cache:false,
        async:true,
        type:"POST",
        data : params,
        success:function(data){
            if (data.success == true) {

            } else {

            }
        }
    });
}

function showForbidden(userId) {
    $('#edit-user').modal("show");
}

