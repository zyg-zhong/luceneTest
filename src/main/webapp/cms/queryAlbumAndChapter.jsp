<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
  	<script type="text/javascript">
  		$(function(){
  			$("#albumAndChapter").treegrid({    
			    url:"${pageContext.request.contextPath}/album/queryAllAlbums",    
			    idField:"id",    
			    treeField:"title",  
			    toolbar: "#toolbarbtu",
			    columns:[[    
			        {field:"title",title:"名称",width:300},    
			        {field:"src",title:"下载路径",width:300,align:"center"},    
			        {field:"size",title:"章节大小",width:300},    
			    ]]    
			}); 
			//初始化添加专辑对话框
			 $("#appendNewAlbumdialog").dialog({
			 	title:"添加专辑",
			 	width:400,
			 	height:420,
			 	modal:true,
			 	cache:false,
			 	closed:true,
			 	resizable:true,//可变大小
			 	minimizable:true,//最小化
			 	maximizable:true,//最大化
			 	draggable:true
			 });
			 //初始化上传音频对话框
			 $("#appendNewChapterdialog").dialog({
			 	title:"上传音频",
			 	width:400,
			 	height:420,
			 	modal:true,
			 	cache:false,
			 	closed:true,
			 	resizable:true,
			 	minimizable:true,//最小化
			 	maximizable:true,//最大化
			 });
			 
			 
			 //文本框初始化------//初始添加专辑
			 $("#addAlbumTitleInput").textbox({
			 	required:true,
			 });
			 
			 $("#addAlbumcountInput").textbox({
			 	required:true,
			 });
			 
			 $("#addAlbumscoreInput").textbox({
			 	required:true,
			 });
			 
			 $("#addAlbumauthorInput").textbox({
			 	required:true,
			 });
			 
			 $("#addAlbumtellerInput").textbox({
			 	required:true,
			 });
			 
			 $("#addAlbumbriefInput").textbox({
			 	required:true,
			 });
			 
			 $("#addAlbumfileInput").filebox({
			 	required:true,
			 	buttonText: '选择文件',
			 });
			 
			 $("#addAlbumdateInput").datebox({
			 	required:true,
			 });
			 
			 //上传音频
			 $("#addChapterclbumIdInput").textbox({
			 	required:true,
			 });
			 
			 $("#addChapterTitleInput").textbox({
			 	required:true,
			 });
			 //大小
			 $("#addChapterSizeInput").textbox({
			 	required:true,
			 });
			 //文件
			 $("#addChapterfileInput").filebox({
			 	required:true,
			 	multiple:true,//多个音频
			 	buttonText: "音频文件",
			 	separator:","
			 });
  		});
  		//自定义添加专辑按钮
  		function appendNewAlbum(){
  			$("#appendNewAlbumdialog").dialog("open");
  		};
  		//初始化添加保存按钮
  		$("#appendNewAlbumFormSavebtu").linkbutton({
  			iconCls:'icon-save',
  			onClick:function(){
  				//表单提交
  				$("#appendNewAlbumForm").form("submit",{
  					url:"${pageContext.request.contextPath}/album/appendNewAlbum",
  					onSubmit:function(){
  					},
  					success:function(){
  						//关闭对话框
  						$("#appendNewAlbumdialog").dialog("close");
  						//刷新
  						$("#albumAndChapter").treegrid("reload");
						//打开提示
						$.messager.show({
							title:"系统提示",
							msg:"添加成功！"
						});
  					}
  				});
  			}
  			
  		});
  		 //自定义上传音频
  		function appendNewChapter(){
  			ob=$("#albumAndChapter").treegrid("getSelected");
  			if(ob==null||ob.count==null){
  				alert("请选择专辑");
  			}else{
  				$("#appendNewChapterdialog").dialog("open");
  				//自动填充
  				var obj=$("#albumAndChapter").treegrid("getSelected");
  				$("#appendNewChapterForm").form("load",{
  					clbumId:obj.id,
  				});
  			}
  		};
  		//初始化上传按钮
  		$("#appendNewChapterFormSavebtu").linkbutton({
  			iconCls:"icon-save",
  			onClick:function(){
  				//上传表单提交
  				$("#appendNewChapterForm").form("submit",{
  					url:"${pageContext.request.contextPath}/album/appendNewChapter",
  					onSubmit:function(){
  					},
  					success:function(){
  						//关闭对话框
  						$("#appendNewChapterdialog").dialog("close");
  						//刷新
  						$("#albumAndChapter").treegrid("reload");
						//打开提示
						$.messager.show({
							title:"系统提示",
							msg:"上传音频成功！"
						});
  					}
  				});
  			}
  		});
  		//专辑详情
  		function showAlbum(){
  			var obj=$("#albumAndChapter").treegrid("getSelected");
  			if(obj==null||obj.count==null){
  				alert("请选择专辑");
  			}else{
  				
  				//专辑详情对话框(先加载，再打开)
				 $("#showAlbumdialog").dialog({
				 	title:"专辑详情",
				 	width:600,
				 	height:620,
				 	modal:true,
				 	cache:false,
				 	resizable:true,
				 	minimizable:true,//最小化
				 	maximizable:true,//最大化
				 	//对话框里的属性，
				 	content:"<center><img src='${pageContext.request.contextPath}/img/album/"+obj.coverPath+"'><br/><br/><br/>"
				 			+"专辑名称：<font size='3'>"+obj.title+"</font><br/>"
				 			+"作者：<font size='3'>"+obj.author+"</font><br/>"
				 			+"播音：<font size='3'>"+obj.teller+"</font><br/>"
				 			+"音频数量：<font size='3'>"+obj.count+"<br/>"
				 			+"评分：<font size='3'>"+obj.score+"</font><br/><br/>"
				 			+"发布时间："+obj.publish_date+"</font><br/></center>"
				 			
				 			
				 });
				 //打开
				 $("#showAlbumdialog").dialog("open");
  			}
  		};
  		//下载音频
  		function downloadChapter(){
  			var obj=$("#albumAndChapter").treegrid("getSelected");
  			if(obj==null||obj.size==null){
  				alert("请选择要下载的音频");
  			}else{
  				location.href="${pageContext.request.contextPath }/album/download?fileName="+obj.src;
  			}
  			
  		};
  		
  	</script>
    	
    <!--  树形表格-->
   <table id="albumAndChapter" style="width:1000px;height:1000px"></table>  
   
   <div id="toolbarbtu">
		<a id="a" onclick="appendNewAlbum()" class="easyui-linkbutton" data-options="iconCls:'icon-add',plain:true">添加专辑</a>
		<a id="b" onclick="appendNewChapter()" class="easyui-linkbutton" data-options="iconCls:'icon-add',plain:true">添加音频</a>
		<a id="d" onclick="showAlbum()" class="easyui-linkbutton" data-options="iconCls:'icon-man',plain:true">专辑详情</a>
		<a id="e" onclick="downloadChapter()" class="easyui-linkbutton" data-options="iconCls:'icon-filter',plain:true">下载</a>
	</div>
    
    <!-- 对话框设置 -->
   <!-- 添加专辑 -->
   <div id="appendNewAlbumdialog">
   		<center>
   		<br/>
   		<font color="red" size="5">添加新专辑</font><br/><br/>
   		<form id="appendNewAlbumForm" enctype="multipart/form-data" method="POST" >
   			专辑名称：<input id="addAlbumTitleInput"  name="title"  style="width:200px" /><br/><br/>
   			章节数量：<input id="addAlbumcountInput" name="count" style="width:200px" /><br/><br/>
   			评分：<input id="addAlbumscoreInput" name="score" style="width:200px"/><br/><br/>
   			作者：<input id="addAlbumauthorInput" name="author" style="width:200px"/><br/><br/>
   			播音员：<input id="addAlbumtellerInput" name="teller" style="width:200px" /><br/><br/>
   			专辑简介：<input id="addAlbumbriefInput"  name="brief" style="width:200px" /><br/><br/>
   			专辑插图：<input id="addAlbumfileInput" name="file2" style="width:200px"/><br/><br/>
   			发布时间：<input id="addAlbumdateInput" name="publish_date" style="width:200px"/><br/><br/>
   			<a id="appendNewAlbumFormSavebtu" >添加</a>
   		</form>
   		</center>
   </div>
   <!-- 上传音频对话框 -->
   <div id="appendNewChapterdialog">
   		<center>
   		<br/>
   		<font color="red" size="5">上传音频</font><br/>
   		<form id="appendNewChapterForm" enctype="multipart/form-data" method="POST" >
   			专辑id:<input id="addChapterclbumIdInput" name="clbumId" style="width:200px"/><br/><br/>
   			音频名称：<input id="addChapterTitleInput"  name="title"  style="width:200px" /><br/><br/>
   			音频大小：<input id="addChapterSizeInput" name="size" style="width:200px" /><br/><br/>
   			选择音频：<input id="addChapterfileInput" name="file3" style="width:200px"/><br/><br/>
   			<a id="appendNewChapterFormSavebtu" >上传</a>
   		</form>
   		</center>
   </div>
   <!-- 专辑详情 -->
    <div id="showAlbumdialog">
   		<!-- 专辑详情数据表格 -->
		<table id="showAlbum"></table>
   </div>