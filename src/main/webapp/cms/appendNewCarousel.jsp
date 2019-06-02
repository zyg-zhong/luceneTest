<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
    	<script type="text/javascript">
    		$(function(){
    			//初始化文本框：
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
		    							return true;
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
    	</script>
    	<center>
    	<br/>
    	<font color="red" size="4">请填写新用户信息：</font>
    	<br/><br/>
    	<form id="addnewCarouselForm" enctype="multipart/form-data"  method="post">
	  		图片名称：<input id="nameIntut" name="carouselName"/><br/><br/>
	  		图片路径：<input id="fileIntut" name="caroufile" /><br/><br/>
	  		详细介绍：<input id="descriptionIntut" name="description"/><br/><br/>
	  		播放状态：<input type="radio" name="status" value="y"/>展示&nbsp;&nbsp;<input type="radio" name="status" value="n"/>不展示<br/><br/>
    		<!-- 保存  重置 -->
    		<a id="addnewCarouselFormSavabtu">添加</a>&nbsp;&nbsp;<a id="insertFormresetbtu">重置</a>
    		
    	</form>
  		</center>
