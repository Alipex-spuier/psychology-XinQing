<template>
	<view>
		<view class="header">
			<view class="header_body">
				<view>36</view>
				<view>我的账户</view>
			</view>
		</view>

		<view class="record">预约记录</view>

		<view class="main_body">
			<view class="item" v-for="item,index in 20">
				<view>
					<text>提现成功</text>
					<text>2024/02/09 07:58</text>
				</view>
				<view>
					+30
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
	export default {
		name: "pointsMallDetails",
		created() {
			this.loginType = uni.getStorageSync("loginType");
		},
		data() {
			return {
				loginType:null,
			}
		},

		methods: {
			// 提现跳转
			GetLaunch() {
				uni.navigateTo({
					url: '/pages/index'
				})
				
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