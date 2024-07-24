<template>
	<view class="container" :style="{height:this.resultsArr.length>4?'100%':'100vh'}">

	<view class="content">
	
	    <!-- ESG得分构成 -->
	    <view style="background: white; margin-top: 10px;flex-direction:column;">
	        <view style="margin-left: 10px;font-size: 20px;color: black;padding: 10px 0px;">您的历史得分记录</view>
				<view  v-for="(item,resultsArr) in resultsArr" :key="index" @tap="redirectToInput(item)">
					<!--barTitle:文字标题 barTime:更新时间 barValue:得分值 color:背景颜色 selColor:得分选中颜色 maxValue:最大值  -->
					<cc-horBarCharts :barTitle="'您的第'+(resultsArr+1)+'次分析:'+item.belongingId+'指数 '+item.resResult" :barTime="'答题时间:'+item.resTime" :barValue="item.resResult" color="#C0DDFA" selColor="#1890FF" maxValue="100"></cc-horBarCharts>     	
				</view>
	
	    </view>
	</view>
		<view class="none" :style="{opacity:this.resultsArr.length>0?0:1,display:this.resultsArr.length>0?'none':'flex'}">
			<text>== 不好意思，这里好像什么都没有哦 ==</text>
		</view>
	</view>

</template>

<script>
	
	export default {
		props: {
		
			color: {
				type: String,
				default: '#C0DDFA'
			},
			selColor: {
				type: String,
				default: '#1890FF'
			},
			minValue: {
				type: [String, Number],
				default: '0'
			},
			maxValue: {
				type: [String, Number],
				default: '100'
			}
		},
		data() {
			return {
				username:"username",
				userId:0,
				userAuthorization:null,
				score:'',
				resultsArr: [],
				index:[],
				pageNum: 1,
				pageSize: 20,
				
			}
		},
		created() {
			this.init()
			},
		onLoad(options) {
			
		},
		
		methods: {
			handleResultClick(item) {
				let clickedMusic = [item];
				uni.navigateTo({
					url: '../player/player?result=' + encodeURIComponent(JSON.stringify(clickedMusic))
				})
			},
			init()
			{	
				let res=uni.getStorageSync("res")
				this.userId=res.data.data.userId
				this.userAuthorization=res.header.authorization
				this.getResult()
			},
			getResult(){
				console.log("11")
				uni.request({
					url:this.$baseURL+'/api/v1/testResult/indexPageByUser',
					method:'POST',
					header:{Authorization:this.userAuthorization},
					data:{
						pageSize:this.pageSize, 
						pageNum:this.pageNum, 
						param:{ 
							userId:this.userId
						}
					},
					success: (response) => {
						const res = response.data;
						if (Array.isArray(res.data)) {
						    // 使用 sort() 方法进行排序
						    res.data.sort((a, b) => b.resId - a.resId);
						
						    // 打印排序后的数据
						    //console.log(res);
						} else {
						    console.error('Response data is not an array');
						}
						
						this.resultsArr=res.data;
						for (let i = 0; i < this.resultsArr.length; i++) {
									if( this.resultsArr[i].belongingId==1)
										this.resultsArr[i].belongingId = '自卑'; 
									else if( this.resultsArr[i].belongingId==2)
										this.resultsArr[i].belongingId = '抑郁'; 
									else if( this.resultsArr[i].belongingId==3)
										this.resultsArr[i].belongingId = '伤心'; 
									if( this.resultsArr[i].belongingId==4)
										this.resultsArr[i].belongingId = '疯狂'; 
						        }
						console.log(res);
						//console.log(this.resultsArr.length);
					}
				})
				
			}
			
		}
	}
</script>

<style>
	.container {
		background-color: #323332;
	}

	.none {
		padding-top: 50%;
		justify-content: center;
		align-items: center;
	}

	.none text {
		font-size: 30rpx;
		font-style: italic;
		color: antiquewhite;
		font-weight: 100;
	}
</style>