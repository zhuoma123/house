<%@ page language="java" contentType="text/html; utf-8" pageEncoding="utf-8" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" <%--"http://www.w3c.org/TR/1999/REC-html401-19991224/loose.dtd"--%>>
<!-- saved from url=(0044)http://localhost:8080/HouseRent/page/add.jsp -->
<HTML xmlns="http://www.w3.org/1999/xhtml"><HEAD><TITLE>青鸟租房 -发布房屋信息</TITLE>
<META content="text/html; charset=utf-8" http-equiv=Content-Type><LINK 
rel=stylesheet type=text/css href="../css/style.css">
<META name=GENERATOR content="MSHTML 8.00.7601.17514">
  <script language="JavaScript" type="text/javascript" src="../scripts/jquery-1.8.3.js"></script>
  <script>
    $(function () {
      //发送异步请求，加载房屋类型
      $.post("getTypes",null,function (data) {
        for (var i = 0; i <data.length ; i++) {
          //创建option
          var opt=$("<option value="+data[i].id+">"+data[i].name+"</option>")
          //将option添加到下拉列表
          $("#typeId").append(opt);
        }
      },"json")

      //发送异步请求，加载区域信息
      $.post("getDistricts",null,function (data) {
        for (var i = 0; i <data.length ; i++) {
          //创建option
          var opt=$("<option value="+data[i].id+">"+data[i].name+"</option>")
          //把option添加到下拉列表
          $("#district_id").append(opt);
        }
      },"json")

      //二级联动：给区域下拉列表添加改变事件
      $("#district_id").change(function () {
        //获取当前区域的id
        var di=$(this).val();//this表示当前对象
        $("#street_id").empty()//清空街道数据
        //发送异步请求，加载街道信息
        $.post("getStreetByDistrict",{"did":di},function (data) {
          for (var i = 0; i <data.length ; i++) {
            //创建option
            var opt=$("<option value="+data[i].id+">"+data[i].name+"</option>")
            //把option添加到下拉列表
            $("#street_id").append(opt);
          }
        },"json")
      })
    })

  </script>
</HEAD>
<BODY>
<DIV id=header class=wrap>
<DIV id=logo><IMG src="../images/logo.gif"></DIV></DIV>
<DIV id=regLogin class=wrap>
<DIV class=dialog>
<DL class=clearfix>
  <DT>新房屋信息发布</DT>
  <DD class=past>填写房屋信息</DD></DL>
<DIV class=box>
<FORM id=add_action method=post name=add.action action=addHouse enctype="multipart/form-data">
<DIV class=infos>
<TABLE class=field>
  <TBODY>
  <TR>
    <TD class=field>标　　题：</TD>
    <TD><INPUT id=add_action_title class=text type=text name=title> </TD></TR>
  <TR>
    <TD class=field>户　　型：</TD>
    <TD><SELECT class=text name=typeId id="typeId">
      <option value="">==请选择==</option>
        </SELECT></TD></TR>
  <TR>
    <TD class=field>面　　积：</TD>
    <TD><INPUT id=add_action_floorage class=text type=text name=floorage></TD></TR>
  <TR>
    <TD class=field>价　　格：</TD>
    <TD><INPUT id=add_action_price class=text type=text name=price> </TD></TR>
  <TR>
    <TD class=field>房产证日期：</TD>
    <TD><INPUT class=text type=date name=pubdate></TD></TR>
  <TR>
    <TD class=field>位　　置：</TD>
    <TD>区：<SELECT class=text name=districtId id="district_id">
      <option value="">==请选择==</option>
    </SELECT>
      街：<SELECT class=text name=streetId id="street_id">
        <option value="">==请选择==</option>
    </SELECT> </TD></TR><!--
						<tr>
							<td class="field">坐  标：</td>
							<td><input type="text" class="text" name="point" />
							</td>
						</tr>
						--><!--  <tr>
							<td class="field">Y 坐  标：</td>
							<td><input type="text" class="text" name="point.y" /></td>
						</tr>-->
  <TR>
    <TD class=field>联系方式：</TD>
    <TD><INPUT id=add_action_contact class=text type=text name=contact> </TD></TR>
  <TR>
    <TD class=field>详细信息：</TD>
    <TD><TEXTAREA name=description></TEXTAREA></TD>
  </TR>
  <TR>
    <TD class=field>图片：</TD>
    <TD><input type="file" name="pfile" id="pfile"></TD></TR>
  </TBODY>
</TABLE>
<DIV class=buttons><INPUT  value=立即发布 type=submit>
</DIV></DIV></FORM></DIV></DIV></DIV>
<DIV id=footer class=wrap>
    <%--<form action="addHouse" method="post" enctype="multipart/form-data">
        <input type="text" name="title">
        <input type="submit" value="提交">
    </form>--%>
<DL>
  <DT>青鸟租房 © 2018 北大青鸟 京ICP证1000001号</DT>
  <DD>关于我们 · 联系方式 · 意见反馈 · 帮助中心</DD></DL></DIV></BODY>

</HTML>
