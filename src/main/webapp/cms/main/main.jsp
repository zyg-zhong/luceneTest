<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8">
    <title>持名法州主页</title>
    <link rel="stylesheet" type="text/css" href="../themes/default/easyui.css">
    <link rel="stylesheet" type="text/css" href="../themes/icon.css">
    <script type="text/javascript" src="../js/jquery.min.js"></script>
    <script type="text/javascript" src="../js/jquery.easyui.min.js"></script>
    <script type="text/javascript" src="../js/easyui-lang-zh_CN.js"></script>
    <script type="text/javascript" src="../js/datagrid-detailview.js"></script>
    <script type="text/javascript" src="../js/echarts.js"></script>
    <script type="text/javascript" src="../js/china.js"></script>
    <script type="text/javascript">
        $(function () {
            //发送ajax请求--》获得查所有菜单的数据-----》创建分类---》tree
            $.ajax({
                type: "POST", url: "http://localhost:9999/lucene/menu/queryAllMenus", dataType: "json",
                success: function (result) {
                    //console.info(result[0]);
                    for (var i = 0; i < result.length; i++) {
                        var as = new Array();
                        var ms = result[i].chrildren;
                        for (var j = 0; j < ms.length; j++) {
                            as.push({
                                "text": ms[j].menuName,
                                "iconCls": ms[j].iconCls,
                                "myPath": "${pageContext.request.contextPath}" + ms[j].href
                            });
                            //console.info("1111"+ms[j].href);
                        }
                        //创建分类
                        $("#aa").accordion("add", {
                            title: result[i].menuName,
                            content: "<ul name=" + result[i].menuName + "></ul>",
                            selected: false,
                            iconCls: result[i].iconCls,
                        });

                        //创建二级分类菜单
                        $("ul[name=" + result[i].menuName + "]").tree({
                            "data": as,
                            onClick: function (node) {
                                //判断tabs是否存在
                                if ($("#mytabs").tabs("exists", node.text)) {
                                    //存在 选中
                                    $("#mytabs").tabs("select", node.text);
                                } else {
                                    //不存在 创建
                                    $("#mytabs").tabs("add", {
                                        title: node.text,
                                        closable: true,
                                        href: node.myPath
                                    });
                                }
                            }
                        });
                    }

                }
            });//ajax结束
            //初始化修改密码对话框
            $("#updatePassworddialog").dialog({
                title: "修改密码",
                width: 360,
                height: 350,
                modal: true,
                cache: false,
                closed: true,
                resizable: true,//可变大小
                minimizable: true,//最小化
                maximizable: true,//最大化
                draggable: true
            });
            //文本框初始化------//初始添加专辑
            $("#oldPassInput").textbox({
                required: true,
                type: "password",
            });

            $("#newPassInputA").textbox({
                required: true,
                type: "password",
            });

            $("#newPassInputB").textbox({
                required: true,
                type: "password",
            });
            /* //确认密码验证
            $("#newPassInputB").blur(function(){
                var passwordB=$(this).val();
                var passwordA=$("#newPassInputA").val();
                if(passwordA==passwordB){
                    $("#Bpass").html("<b><font color='green'>密码验证成功！</font></b>");

                }else{
                    $("#Bpass").html("<b><font color='red'>两次密码输入不一致！</font></b>");
                }
            }); */
            //初始化修改表单提交按钮
            $("#updatePasswordFormBtu").linkbutton({
                iconCls: 'icon-save',
                onClick: function () {
                    //表单提交
                    $("#updatePasswordForm").form("submit", {
                        url: "${pageContext.request.contextPath}/admin/updatePssword",
                        onSubmit: function () {
                        },
                        success: function (data) {
                            //关闭对话框
                            $("#updatePassworddialog").dialog("close");
                            var obj = JSON.parse(data);

                            alert(obj.words);
                        }
                    });
                }

            });
        });

        //退出系统--------------自定义
        function exit() {
            location.href = "${pageContext.request.contextPath }/admin/exit";
        }

        function updateAdminPassword() {
            //打开对话框
            $("#updatePassworddialog").dialog("open");
        }
    </script>

</head>
<body class="easyui-layout">
<div data-options="region:'north',split:true" style="height:60px;background-color:  #5C160C">
    <div style="font-size: 24px;color: #FAF7F7;font-family: 楷体;font-weight: 900;width: 500px;float:left;padding-left: 20px;padding-top: 10px">
        持名法州后台管理系统
    </div>
    <div style="font-size: 16px;color: #FAF7F7;font-family: 楷体;width: 300px;float:right;padding-top:15px">
        欢迎您:${sessionScope.admin.username} &nbsp;<a id="updateAdminPassword" onclick="updateAdminPassword()"
                                                    class="easyui-linkbutton"
                                                    data-options="iconCls:'icon-edit'">修改密码</a>&nbsp;&nbsp;<a id="exit"
                                                                                                              onclick="exit()"
                                                                                                              class="easyui-linkbutton"
                                                                                                              data-options="iconCls:'icon-no'">退出系统</a>
    </div>
</div>
<div data-options="region:'south',split:true" style="height: 40px;background: #5C160C">
    <div style="text-align: center;font-size:15px; color: #FAF7F7;font-family: 楷体">&copy;百知教育 wwwjn@zparkhr.com.cn</div>
</div>

<div data-options="region:'west',title:'导航菜单',split:true" style="width:220px;">
    <div id="aa" class="easyui-accordion" data-options="fit:true">
    </div>
</div>
<div data-options="region:'center',split:true,href:'${pageContext.request.contextPath}/cms/tabs.jsp'">center</div>

<!-- 修改对话框 -->
<div id=updatePassworddialog>
    <center>
        <br/>
        <span style="font-size:56px; color: #ff525e">修改密码</span><br/><br/>
        <form id="updatePasswordForm" method="post">
            原密码：<input id="oldPassInput" name="oldPass" maxlength="20" style="width:200px"/><span
                id="Opass"></span><br/><br/>
            新密码：<input id="newPassInputA" name="newpassA" maxlength="20" style="width:200px"/><span
                id="Apass"></span><br/><br/>
            再次输入<input id="newPassInputB" name="newpassB" maxlength="20" style="width:200px"/><span
                id="Bpass"></span><br/><br/>
            <a id="updatePasswordFormBtu">提交</a>
        </form>
    </center>
</div>
</body>
</html>