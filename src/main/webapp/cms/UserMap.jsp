<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
	<script type="text/javascript">
	
	$(function(){
		//发送ajax请求
		$.ajax({type:"POST",url:"${pageContext.request.contextPath }/user/queryCountByProvince",dataType:"json",
			success:function(res){
			// 基于准备好的dom，初始化echarts实例
	         var myChart = echarts.init(document.getElementById("mainwwwww"));
				option = {
				    title : {
				        text: "用户分布图",
				        subtext: "(持明法洲)",
				        left: "center"
				    },
				    tooltip : {
				        trigger: "item"
				    },
				    legend: {
				        orient: "vertical",
				        left: "left",
				        data: ["男生","女生"]
				    },
				    visualMap: {
				        min: 0,
				        max: 20,
				        left: "left",
				        top: "bottom",
				        text:["高","低"],           // 文本，默认为数值文本
				        calculable : true
				    },
				    toolbox: {
				        show: true,
				        orient : "vertical",
				        left: 'right',
				        top: 'center',
				        feature : {
				            mark : {show: true},
				            dataView : {show: true, readOnly: false},
				            restore : {show: true},
				            saveAsImage : {show: true}
				        }
				    },
				    series : [
				        {
				            name: "男生",
				            type: 'map',
				            mapType: 'china',
				            label: {
				                normal: {
				                    show: false
				                },
				                emphasis: {
				                    show: true
				                }
				            },
				            data:res.countN
				                
				        },
				        {
				            name: "女生",
				            type: 'map',
				            mapType: 'china',
				            label: {
				                normal: {
				                    show: false
				                },
				                emphasis: {
				                    show: true
				                }
				            },
				            data:res.countV
				        }
				    ]
				}; 	
		        // 使用刚指定的配置项和数据显示图表。
				myChart.setOption(option);  
				}
			});
			
		});
	</script>
	<div id="mainwwwww" style="width: 800px;height:600px;"></div>
