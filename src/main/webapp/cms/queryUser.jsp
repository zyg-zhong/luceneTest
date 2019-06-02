<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>

	<script type="text/javascript">
		$(function(){
			//数据表格
			$("#UserDatagrid").datagrid({
					title : "用户列表",
					toolbar:"#SomeUserdownloadbtu,#downloadUserList",
					width : "100%",
					height : "100%",
					checkbox:true,
					checkOnSelect:true,
					ctrlSelect:true,
					remoteSort : false,
					singleSelect : true,
					nowrap : true,
					fitColumns : true,
					url : "${pageContext.request.contextPath}/user/queryAllUser",
					columns : [ [ {
						field : "userId",//自断名称
						title : "ID",//列名
						width : 30,//宽
						align : "center",//对齐方式
						//sortable : true//排序
					}, {
						field : "name",
						title : "用户名",
						align : "center",//对齐方式
						width : 50,
						
					}, {
						field : "password",
						title : "密码",
						width : 80,
						align : "center",//对齐方式
					}, {
						field : "phone",
						title : "电话",
						width : 80,
						align : "center",
					}, {
						field : "gender",
						title : "性别",
						width : 30,
						align : "center",//对齐方式
					}, {
						field : "headPath",
						title : "头像",
						width : 70,
						align : "center",//对齐方式
					}, {
						field : "province",
						title : "省份",
						width : 50,
						align : "center",//对齐方式
					}, {
						field : "city",
						title : "城市",
						width : 50,
						align : "center",//对齐方式
					}, {
						field : "sign",
						title : "简介",
						width : 130,
						align : "center",//对齐方式
					}, {
						field : "dharma",
						title : "状态",
						width : 50,
						align : "center",
						formatter: function(value,row,index){
								if (row.status==1){
									return "正常";
								} else {
									return "<font color='red'>停号</font>";
								}
							}
						
					}, {
						field : "registerDate",
						title : "注册时间",
						width : 80,
						align : "center",//对齐方式
					}, {
						field : "salt",
						title : "加密信息",
						width : 80,
						align : "center",//对齐方式
					}] ],
			});
		});
		//--导出excel文档
		function AllUserdownload(){
			//提示
			$.messager.confirm("温馨提示","确定导出所有用户？", function(){
				location.href="${pageContext.request.contextPath}/user/downloadUser";
			});
		};
		//--自定义导出文档--选择导出
		function SomeUserdownload(){
			//获得数据表的id
			var row=$("#UserDatagrid").datagrid("getSelected");
			if(row==null){
				alert("请选择要导出的项目！");
			}else{
				//alert("id:"+row.userId);
				//提示
				$.messager.confirm("温馨提示","确定导出所选项目吗？", function(){
					location.href="${pageContext.request.contextPath}/user/downloadSomeUser?id="+row.userId;
				});
			}
		};
		//--导入文档
	</script>
	
	<!-- 工具栏 -->
	<div id="tb" style="height:auto">
		<a id="SomeUserdownloadbtu"  href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:'icon-man',plain:true" onclick="SomeUserdownload()">自定义导出</a>&nbsp;   &nbsp;&nbsp;
		<a id="downloadUserList" href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:'icon-more',plain:true" onclick="AllUserdownload()">全部导出</a>
	</div>
	
	<!-- 数据表格 -->
	<table id="UserDatagrid"></table>
	
