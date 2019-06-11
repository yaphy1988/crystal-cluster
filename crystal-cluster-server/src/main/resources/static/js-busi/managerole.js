function closeWindow() {
    window.opener.location.reload();//刷新父窗口
    window.opener = null;
    window.open('', '_self', '');
    window.close();
}

/**
 * 模态框显示提示信息
 * @param title
 * @param content
 * @param method
 */
function modelInfo(title,content,method){
    $("#modal-success-title").text(title);
    $("#modal-success-content").text(content);
    $("#modal-success").modal("show");
    if(typeof method){
        $('#modal-success').on('hidden.bs.modal', function (){
            method();
        });
    }
}

/**
 * 清空模态框的数据
 */
function clearModel() {
    document.getElementById("new-role-form").reset();
}

function createNewRole() {
    clearModel();
    $("#new-role").modal("show");
}

function roleDoWith(){
    var roleId = $("#roleId").val();
    if(roleId != null && roleId != undefined && roleId != ""){
        updateRole();
    } else {
        saveNewRole();
    }
}

/**
 * 新增角色
 */
function saveNewRole() {
    var roleName = $("#roleName").val();
    var roleType = $("#roleType").val();
    var rankNumber = $("#rankNumber").val();

    var params = {
        roleName : roleName,
        roleType : roleType,
        rankNumber : rankNumber
    };
    var url= WEB_ROOT + "/admin/manageRole/createRole";
    $.ajax({
        url:url,
        cache:false,
        async:true,
        type:"POST",
        dataType:'json',
        data : params,
        success:function(data){
            if (data.success == true) {
                $("#new-role").modal("hide");
                modelInfo("提示信息","角色创建成功！",function () {
                    window.location.href=window.location.href;
                });
            } else {
                modelInfo("提示信息","角色创建失败！" + data.msg);
            }
        },
        error : function (data) {
            alert(data);
        }
    });
}

/**
 * 编辑用户的信息
 * @param roleId
 */
function editRole(roleId,roleName,roleType,rankNumber) {
    clearModel();
    $("#roleId").val(roleId);
    $("#roleName").val(roleName);
    $("#roleType").val(roleType);
    $("#rankNumber").val(rankNumber);
    $("#new-role").modal("show");
}

//保存新用户
function updateRole() {
    var roleId = $("#roleId").val();
    var roleName = $("#roleName").val();
    var roleType = $("#roleType").val();
    var rankNumber = $("#rankNumber").val();

    var params = {
        userRoleId : roleId,
        roleName : roleName,
        roleType : roleType,
        rankNumber : rankNumber
    };

    var url= WEB_ROOT + "/admin/manageRole/updateRole";
    $.ajax({
        url:url,
        cache:false,
        async:true,
        type:"POST",
        dataType:'json',
        data : params,
        success:function(data){
            if (data.success == true) {
                $("#new-role").modal("hide");
                modelInfo("提示信息","角色修改成功！",function () {
                    window.location.href=window.location.href;
                });
            } else {
                modelInfo("提示信息","角色修改失败！" + data.msg);
            }
        },
        error : function (data) {
            alert(data);
        }
    });
}

/**
 * 删除模态框
 * @param roleId
 * @param roleName
 */
function deleteRoleModel(roleId,roleName) {
    $("#deleteModelTitle").text("警告");
    $("#deleteModelBody").text("是否确定删除 " + roleName + " 该角色相关信息！");
    $("#deleteRoleId").val(roleId);
    $("#deleteModel").modal("show");
}
/**
 * 是否删除该角色信息
 * @param roleId
 */
function deleteRole() {
    var roleId = $("#devareRoleId").val();

    var params = {
        roleId : roleId
    };

    var url= WEB_ROOT + "/admin/manageRole/deleteRole";
    $.ajax({
        url:url,
        cache:false,
        async:true,
        type:"POST",
        dataType:'json',
        data : params,
        success:function(data){
            if (data.success == true) {
                $("#deleteModel").modal("hide");
                modelInfo("提示信息","角色删除成功！",function () {
                    window.location.href=window.location.href;
                });
            } else {
                modelInfo("提示信息","角色删除失败！" + data.msg);
            }
        },
        error : function (data) {
            alert(data);
        }
    });
}

/**
 * 跳转角色授权界面
 * @param roleId
 */
function authorityRole(roleId) {
    var url = WEB_ROOT + "/admin/manageRole/authorityRole?roleId="+roleId;
    window.open(url, "_blank");
}

