function closeWindow() {
    window.opener.location.reload();//刷新父窗口
    window.opener = null;
    window.open('', '_self', '');
    window.close();
}

/**
 * 待选区域全部选中
 */
function removeAllItem(){
    var sltSrc=document.getElementById('selectRole');
    var sltTarget=document.getElementById('hadSelectRole');

    for(var i=0;i<sltSrc.options.length;i++){
        var oOption = document.createElement("OPTION");
        oOption.text = sltSrc.options[i].text;
        oOption.value = sltSrc.options[i].value;
        sltTarget.add(oOption);
    }
    sltSrc.options.length=0;
}

/**
 * 双击添加权限类型
 */
function removeItem(){
    var sltSrc=document.getElementById('selectRole');
    var sltTarget=document.getElementById('hadSelectRole');
    for(var i=0;i<sltSrc.options.length;i++) {
        var tempOption=sltSrc.options[i];
        if(tempOption.selected){
            sltSrc.removeChild(tempOption);
            sltTarget.appendChild(tempOption);
        }
    }
}

/**
 * 已选区域全部移除
 */
function addAllItem(){
    var sltSrc=document.getElementById('selectRole');
    var sltTarget=document.getElementById('hadSelectRole');
    for(var i=0;i<sltTarget.options.length;i++){
        var oOption = document.createElement("OPTION");
        oOption.text = sltTarget.options[i].text;
        oOption.value = sltTarget.options[i].value;
        sltSrc.add(oOption);
    }
    sltTarget.options.length=0;
}

function addItem(){
    var sltSrc=document.getElementById('selectRole');
    var sltTarget=document.getElementById('hadSelectRole');
    for(var i=0;i<sltTarget.options.length;i++) {
        var tempOption=sltTarget.options[i];
        if(tempOption.selected){
            sltTarget.removeChild(tempOption);
            sltSrc.appendChild(tempOption);
        }
    }
}
function showSelectOptions(){
    var sltTarget=document.getElementById('sltTarget');
    var myhtml="";
    for(var i=0;i<sltTarget.options.length;i++) {
        myhtml +="Select Item" + i + ":  text= " + sltTarget.options[i].text + ", value=" + sltTarget.options[i].value + "<br/>";
    }
    document.getElementById("showInfo").innerHTML=myhtml;
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
 * 新增角色
 */
function saveNewRole() {

    var roleId = $("#roleId").val();
    var sltTarget = document.getElementById('hadSelectRole');
    var authorityIds = new Array(sltTarget.options.length);
    for(var i=0;i<sltTarget.options.length;i++) {
        authorityIds[i] = sltTarget.options[i].value;
    }

    var params = {
        authorityIds : authorityIds,
        roleId : roleId
    };
    var url= WEB_ROOT + "/admin/manageRole/saveAuthorityRole";
    $.ajax({
        url:url,
        cache:false,
        async:true,
        type:"POST",
        dataType:'json',
        data : params,
        success:function(data){
            if (data.success == true) {
                modelInfo("提示信息","授权成功！",function () {
                    window.location.href=window.location.href;
                });
            } else {
                modelInfo("提示信息","授权失败！" + data.msg);
            }
        },
        error : function (data) {
            alert(data);
        }
    });
}


