<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>用户统计</title>
<%-- <script type="text/javascript" src="${pageContext.request.contextPath}/js/echarts.js"></script> --%>
<script type="text/javascript">
	$(function(){
		var dNan=new Array();
		var dNv=new Array();
		//发ajax请求得到数据
		$.ajax({type:"POST",url:"${pageContext.request.contextPath}/user/queryCount",dateType:"json",
			success:function(res){
				var first=res[0];
				for(var i=0;i<first.length;i++){//第一层
					dNan[i]=first[i].count;
					
					//dNv[i]=first[i].count;
				}
				//alert("--------"+dNan);
				var first=res[1];
				for(var i=0;i<first.length;i++){//第一层
					dNv[i]=first[i].count;
				}
				//
				// 基于准备好的dom，初始化echarts实例
		        var myChart = echarts.init(document.getElementById("showUsers"));
				option = {
					title:{
						text:"2018年年度用户注册统计",
					},
		            tooltip: {},
		            legend: {
		                data:["男","女"]
		            },
				    xAxis: {
				        type: 'category',
				        data: ["一月", "二月", "三月", "四月", "五月", "六月", "七月","八月", "九月", "十月", "十一月", "十二月"]
				    },
				    yAxis: {
				        type: 'value'
				    },
				    series: [{
				    	name:"男",
				        data: dNan,
				        type: 'bar'
				    },{
				    	name:"女",
				        data: dNv,
				        type: 'bar'
				    }],
				};
				// 使用刚指定的配置项和数据显示图表。
		        myChart.setOption(option);
			}
		});
		
	});
</script>


	<div id="showUsers" style="width: 750px;height:460px;"></div>
