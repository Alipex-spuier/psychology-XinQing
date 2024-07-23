<template>
	<view>
		<view class="header">
			<view class="header_body">
				<view>共{{resultsArr.length}}条记录</view>
				<view>我的预约</view>
			</view>
		</view>

		<view class="record">预约记录</view>

		<view class="main_body">
			<view  class="item" v-for="(item,resultsArr) in resultsArr" :key="index" @tap="redirectToInput(item)">
			<!-- <view class="item" v-for="item,index in 20"> -->
				<view>
					<text>第{{(resultsArr+1) || 'unknownerror'}}条记录</text>
					<text>{{item.aptTime.allDate || 'unknownerror'}} {{item.aptTime.hour || 'unknownerror'}}</text>
				</view>
				<view>
					预约专家:{{item.expertId || 'unknownerror'}}
				</view>
			</view>
		</view>

		<view class="hide_footer">
			<text>没有更多数据了哟 ~</text>
		</view>


		<view class="btn_bottom">
			<button @tap="GetLaunch" class="cu-btn bnt1">返回首页</button>
		</view>

		<!-- 占位标签 -->
		<view style="height: 200rpx;"></view>
	</view>
</template>

<script>
	import {
		initData,
		initTime,
		timeStamp,
		currentTime,
		dateToTimestamp
	} from '@/utils/date.js'
	export default {
		name: "pointsMallDetails",
		created() {
			this.loginType = uni.getStorageSync("loginType");
			this.userId = uni.getStorageSync("res").data.data.userId;
			this.getResult();
		},
		data() {
			return {
				userId:null,
				loginType:null,
				pageSize:20,
				pageNum:1,
				resultsArr: [],
				tmpName:" "
			}
		},

		methods: {
			// 提现跳转
			GetLaunch() {
				uni.navigateTo({
					url: '/pages/index'
				})
				
			},
			getResult(){
				//console.log("11")
				uni.request({
					url:this.$baseURL+'/api/v1/appointment/indexPageByUserId',
					method:'POST',
					header:{Authorization:uni.getStorageSync("res").header.authorization},
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
						    res.data.sort((a, b) => b.aptTime - a.aptTime);
						    // 打印排序后的数据
							this.getExpertName(res)
						//this.resultsArr=res.data;
						} else {
						    console.error('Response data is not an array');
						}
						//console.log(resultsArr);
					}
				})
			},
			getExpertName(res){
				let _this=this
				for(let i=0;i<res.data.length;i++){
				uni.request({
					url:this.$baseURL+'/api/v1/expert/index/'+res.data[i].expertId,
					method:'POST',
					header:{Authorization:uni.getStorageSync("res").header.authorization},
					
					success: (response) => {
						
							res.data[i].aptTime=timeStamp(res.data[i].aptTime,true);
							res.data[i].expertId=response.data.data.exName
						
						_this.resultsArr=res.data
					}
				})
				}
			}
		},
	}
</script>

<style lang="scss" scoped>
	.header {
		padding: 20rpx;

		.header_body {
			height: 255rpx;
			background-color: #f0f2fb;
			border-radius: 16rpx;
			text-align: center;
			overflow: hidden;

			&>view:nth-child(1) {
				font-size: 60rpx;
				color: #0044ff;
				margin-top: 66rpx;
				margin-bottom: 10rpx;
			}

			&>view:nth-child(2) {
				color: #0044ff;
			}
		}
	}

	.main_body {
		border-top-left-radius: 24rpx;
		border-top-right-radius: 24rpx;
		padding: 20rpx 38rpx;
		background: #fff;

		.item {
			display: flex;
			justify-content: space-between;
			align-items: center;
			margin-bottom: 30rpx;

			&>view:nth-child(1) {
				display: flex;
				flex-direction: column;

				&>text:nth-child(1) {
					margin-bottom: 20rpx;
					font-size: 30rpx;
				}

				&>text:nth-child(2) {
					font-size: 26rpx;
					color: #666;
				}
			}

			&>view:nth-child(2) {
				font-size: 36rpx;
				color: #FF3E3E;
			}
		}
	}

	.main_body>view:last-child {
		margin-bottom: 0 !important;
	}

	.record {
		padding: 24rpx 44rpx 30rpx 44rpx;
		font-size: 32rpx;
	}





	.hide_footer {
		color: #999;
		width: 100%;
		display: flex;
		justify-content: center;
		align-items: center;
		padding: 28rpx;

		.text {
			color: #999999;
			font-size: 24rpx;
		}
	}

	.btn_bottom {
		position: fixed;
		bottom: 0;
		width: 94.5%;
		height: 170rpx;
		padding: 12rpx 20rpx;
		background-color: #fafbfb;
		margin-top: 100rpx;

		.bnt1 {
			width: 100%;
			color: #fff;
			background: #4d5eff;
			height: 88rpx;
			line-height: 88rpx;
		}
	}
</style>