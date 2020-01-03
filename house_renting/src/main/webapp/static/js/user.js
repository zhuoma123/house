$(function(){
    //1.使用datagrid组件绑定数据
    $('#dg').datagrid({
        url:'getUserData',
        pagination:true,  //开启分页
        pageSize:5,  //初始化页大小
        pageList:[5,8,10,20],  //页大小选项
        toolbar:'#ToolBar',//指定工具栏
        columns:[[
            {checkbox:true,width:100,align:'center'},
            {field:'id',title:'编号',width:100,align:'center'},
            {field:'name',title:'用户名称',width:100,align:'center'},
            {field:'password',title:'密码',width:100,align:'center'},
            {field:'telephone',title:'电话',width:100,align:'center'},
            {field:'isadmin',title:'是否系统用户',width:100,align:'center'},
            {field:'bj',title:'编辑',width:100,align:'center',
                formatter: function(value,row,index){
                    return "<a href='javascript:goEdit("+row.id+")'>修改</a> <a href='javascript:goDel("+row.id+")'>删除</a>"
                }
            }
        ]]
    });
});
//实现datagrid绑定查询事件
function searchUsers(){
    var name=$("#name").val();
    var tel=$("#tel").val();
    $("#dg").datagrid('load',{
        name:name,
        telePhone:tel
    })
}

