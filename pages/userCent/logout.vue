<template>
	<view class="page">
		<view class="hide">
		</view>
		<view class="card">
			<view class="menu">
				<view class="item" v-for="v in menus1" :key="v.id" @click="chooseConfig(v)">
					<text>{{v.name}}</text>
					<image class="arrow" v-if="v.arrow" src="/static/center1/right.png"></image>
				</view>
			</view>
		</view>
	</view>
</template>


<script>
	export default {
		data() {
			return {
				menus1: [{
						id: 1,
						name: '资料设置',
						arrow: true
					},
					{
						id: 2,
						name: '账号注销',
						arrow: true
					},
					{
						id: 3,
						name: '账号登出',
						arrow: true
					}
				],
				Authorization: null
			}
		},
		created() {
			const res = uni.getStorageSync("res")
			this.Authorization = res.header.Authorization;
		},
		methods: {
			chooseConfig(item) {
				if (item.id == 3) {
					console.log(this.Authoration)
					uni.request({
						url: 'http://170.106.183.24:8080/account/logout',
						method: 'GET',
						header: {
							Authorization: this.Authorization
						},
						success: res => {
							if (res.data.code === 200) {
								uni.showToast({
									title: '退出成功',
									success: function() {
										uni.clearStorage();
										setTimeout(function() {
											uni.navigateTo({
												url: '/pages/login/index'
											})
										}, 2000)
									}
								});
							} else {
								uni.showToast({
									title: '退出失败：' + res.data.msg,
									icon: 'none'
								});
							}
						},
						fail: () => {
							uni.showToast({
								title: '网络请求失败',
								icon: 'none'
							});
						}
					});
				}
			}
		}
	}
</script>

<style lang="scss">
	.page {
		background: linear-gradient(180deg, #fdd447, #fff);
		min-height: 100vh;

		.card {
			background: #fff;
			box-sizing: border-box;
			width: 700rpx;
			margin: auto;
			padding: 15rpx;
			margin-top: 0rpx;
			border-radius: 12rpx;

			.menu {

				.item {
					display: flex;
					height: 100rpx;
					align-items: center;
					padding: 0 20rpx;
					box-sizing: border-box;
					width: 700rpx;

					&:active {
						background: #f0f0f0;
					}

					image {
						height: 45rpx;
						width: 45rpx;
					}

					text {
						font-size: 14px;
						color: #444;
						padding-left: 15rpx;
						box-sizing: border-box;
						width: calc(100% - 100rpx);
					}

					.arrow {
						width: 26rpx;
						height: 26rpx;
					}
				}
			}
		}

		.hide {
			width: 100vw;
			height: 5vh;
		}
	}
</style>