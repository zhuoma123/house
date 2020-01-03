$(function(){
    //1.使用datagrid组件绑定数据
    $('#dg').datagrid({
        url:'getDistrictData',
        pagination:true,  //开启分页
        pageSize:3,  //初始化页大小
        pageList:[3,5,8,10,20],  //页大小选项
        toolbar:'#ToolBar',//指定工具栏
        columns:[[
            {checkbox:true,width:100,align:'center'},
            {field:'id',title:'编号',width:100,align:'center'},
            {field:'name',title:'区域名称',width:100,align:'center'},
            {field:'bj',title:'编辑',width:100,align:'center',
                formatter: function(value,row,index){
                    return "<a href='javascript:goEdit("+row.id+")'>修改</a> <a href='javascript:goDel("+row.id+")'>删除</a>"
                }
            }
        ]]
    });
});
//打开添加窗口
function goAdd() {
    $("#addDialog").dialog("open").dialog("setTitle","添加区域");
}
//关闭窗口
function CloseDialog(dialogId) {//通过id关闭窗口，就不用添加和修改都要写关闭窗口的方法
    $("#"+dialogId).dialog("close")
}
//保存添加的数据   异步请求
function SaveDialog() {
    //将表单序列化参数数据
    var parm=$("#addDialogForm").serialize();
    $.post("addDistrict",parm,function (data) {
        if(data.i>0){
            $("#addDialog").dialog("close")
            $("#dg").datagrid("reload")        //刷新datagrid
            $.messager.alert('恭喜您','添加成功','info')
        }else{
            $.messager.alert('提示信息','添加失败','info')
        }
    }),"json"
}
//打开修改的窗口
function goUpdate() {
    //获取datagrid的选中行
    var objsel=$("#dg").datagrid("getSelections")//getSelections返回所有被选择的行，当没有记录被选中时，返回一个空数组
    //验证是否选中一行
    if(objsel.length==1){
        $("#updateDialog").dialog("open").dialog("setTitle","编辑区域")
        //回显表单数据
        //情形一：如果表格中的数据支撑修改窗口的数据展示，就无需查询数据库
        //$("#updateDialogForm").form("load",objsel[0])
        //情形二，需要查询数据库
        //使用post方式发异步请求
        var id=objsel[0].id;
        $.post("getDistrict",{"id":id},function (data) {
            //data对象额属性名和表单对象的属性名相同，即可回显
            $("#updateDialogForm").form("load",data);
        },"json")
    }else{
        $.messager.alert("提示信息","请选择一行需要修改的信息","info")
    }
}
//编辑列中的修改
function goEdit(id) {
    $("#updateDialog").dialog("open").dialog("setTitle","编辑区域");
    //发送异步请求回显数据
    $.post("getDistrict",{"id":id},function (data) {
        //data对象额属性名和表单对象的属性名相同，即可回显
        $("#updateDialogForm").form("load",data);
    },"json")
}
//实现修改数据
function updateSaveDialog() {
    //使用esayui提交表单
    $("#updateDialogForm").form('submit',{
        url:"modifyDistrict",
        success:function (data) {
            var obj=$.parseJSON(data);//将json字符串转换成json对象
            if(obj.i>0){
                $("#updateDialog").dialog("close")//成功关闭窗口
                $("#dg").datagrid("reload")        //刷新datagrid
                $.messager.alert('恭喜您','修改成功','info')
            }else{
                $.messager.alert('提示信息','修改失败','info')
            }
        }
    })
}
//删除
function goDel(id) {
    $.messager.confirm('温馨提示','是否确认删除？',function (r) {
        if(r){//r=true,表示点击ok，否则点击取消
            //使用jquery的post发送异步请求
            $.post("delDistrict", {"id": id}, function (data) {
                if (data.i > 0) {
                    $("#dg").datagrid("reload");
                } else {
                    $.messager.alert('提示信息', '删除失败', 'info')
                }
            }, "json");
        }
    });
}
//批量删除
function goDelete() {
    //获取datagrid的选中行
    var objsel = $("#dg").datagrid("getSelections");
    //判断有无选中项
    if (objsel.length > 0) {
        $.messager.confirm("温馨提示：", "是否确认删除？", function (r) {
            if (r) {
                //获取选中项的值id，拼接成：值1，值2，值3
                var str="";
                for (var i = 0; i <objsel.length ; i++) {
                    str=str+objsel[i]+",";
                }
                str=str.substring(0,str.length-1);
                $.post("delManyDistrict", {"ids": str},function (data) {
                    if(data.i>0){
                        $("#dg").datagrid("reload");
                    }else {
                        $.messager.alert('提示信息', '批量删除失败', 'info')
                    }
                },"json")
            }
        })
    }else {
        $.messager.alert("提示信息","请最少选择一行需要删除的信息","info")
    }
}