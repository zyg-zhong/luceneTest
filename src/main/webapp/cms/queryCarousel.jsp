<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>

	<script type="text/javascript">
		$(function(){
			//数据表格
			$("#CarouselDatagrid").datagrid({
					title : "轮播图列表",
					toolbar:"#appendNewCarousel,#removeCarousel,#getChangesCarousel",
					width : "100%",
					height : "100%",
					checkbox:true,
					remoteSort : false,
					singleSelect : true,
					nowrap : false,
					fitColumns : true,
					url : "${pageContext.request.contextPath}/carousel/queryAllCarousel",
					columns : [ [ {
						field : "carouselId",//自断名称
						title : "轮播图ID",//列名
						width : 150,//宽
						align : "center",//对齐方式
						sortable : true//排序
					}, {
						field : "carouselName",
						title : "图片名称",
						align : "center",//对齐方式
						width : 160,
						
					}, {
						field : "imgPath",
						title : "图片路径",
						width : 180,
						align : "center",//对齐方式
					}, {
						field : "description",
						title : "详细描述",
						width : 250,
						align : "center",
					}, {
						field : "uploadDate",
						title : "上传时间",
						width : 150,
						align : "center",//对齐方式
						sortable : true
					}, {
						field : "status",
						title : "是否播放",
						width : 90,
						align : "center",
						formatter: function(value,row,index){
								if (row.status==1){
									return "正在展示";
								} else {
									return "<font color='red'>下架</font>";
								}
							}
						
					} ] ],
					view : detailview,
					detailFormatter : function(rowIndex, rowData) {
						return "<table><tr>"
								+ "<td rowspan=2 style='border:1'><img src='${pageContext.request.contextPath}/img/carousel/" + rowData.imgPath + "' style='height:50px,height='50px''></td>"
								+ "<td style='border:0'>"
								+ "<p>详细介绍: " + rowData.description
								+ "</p>" + "<p>是否展示: "
								+ rowData.status + "</p>" + "</td>"
								+ "</tr></table>";
					},
			});
			
			//初始化添加对话框
			$("#appendNewCarouselDialog").dialog({
				title:"添加新轮播图",
				width:400,
				height:420,
				modal:true,
				closed:true,
				cache:false,
				//href:"${pageContext.request.contextPath}/appendNewCarousel.jsp"//----改到一个页面
			});
			
			//初始化添加表单文本框：
    			$("#nameIntut").textbox({
    				required:true,
    			});
    			//初始化详细文本框：
    			$("#descriptionIntut").textbox({
    				required:true,
    			});
    			//初始化详细文本框：
    			$("#fileIntut").filebox({
    				required:true,
    			}); 
    			//初始化保存按钮
    			$("#addnewCarouselFormSavabtu").linkbutton({
    				iconCls:"icon-save",
    				onClick:function(){//单击事件
    					//提示
    					$.messager.confirm("系统提示",
	    					"您确认要保存吗？",
	    					function(){
	    						//获得表单，提交
	    						$("#addnewCarouselForm").form("submit",{
		    						url : "${pageContext.request.contextPath}/carousel/appendOneCarousel",
			    					onSubmit : function(){
			    						//可做表单验证
		    						},
		    						success:function(){
										//添加完成后关闭对话框
										$("#appendNewCarouselDialog").dialog("close");
										// 自动刷新表格数据
										$("#CarouselDatagrid").datagrid("load");
										//$.messager.progress("close");	// 显示进度条
										//打开提示
										$.messager.show({
											title:"系统提示",
											msg:"添加成功！"
										});
									},
	    					});
    					});
    					
    				}
    			});
    			//初始化重置按钮
    			$("#insertFormresetbtu").linkbutton({
    				iconCls:"icon-no",
    				onClick:function(){
    					$("#addnewCarouselForm").form("reset");
    				}
    			});
		});
		
		//自定义添加，
			function appendcarousel(){
				$("#appendNewCarouselDialog").dialog("open");
			}
			//删除
			function removeit(){
				//获得行数据
				var row = $("#CarouselDatagrid").datagrid("getSelected");
				if(row==null){
					alert("请选择一个选项");
				}else{
					$.ajax({type:"GET",url:"${pageContext.request.contextPath}/carousel/removeOneCarousel?carouselId="+row.carouselId,dataType:"text",
					success:function(result){
					   //刷新
						$("#CarouselDatagrid").datagrid("load");
						//打开提示
						$.messager.show({
							title:"系统提示",
							msg:"删除成功！"
						});
					}
				});
				}
				
			}
			//修改
			function getChanges(){
				var rows = $("#CarouselDatagrid").datagrid("getSelected");
				if(rows==null){
					alert("请选中一个轮播图");
				}else{
					num=-1*rows.status;
					//发送ajax请求
					$.ajax({type:"GET",url:"${pageContext.request.contextPath}/carousel/updateOneCarousel?id="+rows.carouselId+"&sta="+num,dataType:"text",
						success:function(res){ // res是jsObject类型
							//刷新
							$("#CarouselDatagrid").datagrid("load");
						}
					});
					//提示
					alert("状态已更改！");
				}
			} 
		
	</script>
	
	<!-- 工具栏 -->
	<div id="tb" style="height:auto">
		<a id="appendNewCarousel"  href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:'icon-add',plain:true" onclick="appendcarousel()">添加</a>
		<a id="removeCarousel" href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:'icon-remove',plain:true" onclick="removeit()">删除</a>
		<a id="getChangesCarousel" href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:'icon-edit',plain:true" onclick="getChanges()">修改</a>
	</div>
	
	<!-- 数据表格 -->
	<table id="CarouselDatagrid"></table>
	 
	<!-- 添加对话框 -->
	<div id="appendNewCarouselDialog">
		<center>
    	<br/>
    	<font color="red" size="4">请填写信息：</font>
    	<br/><br/>
    	<form id="addnewCarouselForm" enctype="multipart/form-data"  method="post">
	  		图片名称：<input id="nameIntut" name="carouselName" style="width:200px"/><br/><br/>
	  		图片路径：<input id="fileIntut" name="caroufile" style="width:200px"/><br/><br/>
	  		详细介绍：<input id="descriptionIntut" name="description" style="width:200px"/><br/><br/>
	  		播放状态：<input type="radio" name="status" value="1"/>展示&nbsp;&nbsp;<input type="radio" name="status" value="-1"/>不展示<br/><br/>
    		<!-- 保存  重置 -->
    		<a id="addnewCarouselFormSavabtu">添加</a>&nbsp;&nbsp;<a id="insertFormresetbtu">重置</a>
    	</form>
  		</center>
	 </div>
	
