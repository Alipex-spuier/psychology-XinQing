# cc-horBarChart


#### 使用方法

```使用方法
<!--barTitle:文字标题 barTime:更新时间 barValue:得分值 color:背景颜色 selColor:得分选中颜色 maxValue:最大值  -->
<cc-horBarCharts barTitle="管理综合得分: 6.50" barTime="更新于2023-08-21" barValue="6.5" color="#C0DDFA" selColor="#1890FF" maxValue="10"></cc-horBarCharts>		
```

#### HTML代码实现部分
```html
<template>
	<view class="content">
		
		<!-- ESG得分构成 -->
		<view style="background: white; margin-top: 10px;flex-direction:column;">
			<view style="margin-left: 10px;font-size: 15px;color: black;padding: 10px 0px;">岗位得分构成</view>
			
			<!--barTitle:文字标题 barTime:更新时间 barValue:得分值 color:背景颜色 selColor:得分选中颜色 maxValue:最大值  -->
			<cc-horBarCharts barTitle="管理综合得分: 6.50" barTime="更新于2023-08-21" barValue="6.5" color="#C0DDFA" selColor="#1890FF" maxValue="10"></cc-horBarCharts>
			<!--barTitle:文字标题 barTime:更新时间 barValue:得分值 color:背景颜色 selColor:得分选中颜色 maxValue:最大值  -->
			<cc-horBarCharts barTitle="技术实践得分:3.6" barTime="更新于2023-08-21" barValue="3.6" color="#FFE7C5" selColor="#FFB23E" maxValue="6"></cc-horBarCharts>
			<!--barTitle:文字标题 barTime:更新时间 barValue:得分值 color:背景颜色 selColor:得分选中颜色 maxValue:最大值  -->
			<cc-horBarCharts barTitle="工具流程得分: 2.81" barTime="更新于2023-08-21" barValue="2.81" color="#DCD0FE" selColor="#8B64FC" maxValue="4"></cc-horBarCharts>
			
						
		</view>
	</view>
</template>

<script>
	export default {
		data() {
			return {
				title: 'Hello'
			}
		},
		onLoad() {

		},
		methods: {

		}
	}
</script>

<style>
	.content {
		display: flex;
		flex-direction: column;
		
	}

</style>
```