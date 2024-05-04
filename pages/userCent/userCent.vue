<template>
	<view class="page">
		<view class="user" @click="jumpToProfile">
			<image class="avatar" :src="avatar"></image>
			<view class="name">
				<text>{{username}}</text>
				<text>{{email}}</text>
			</view>
			<image class="right" src="/static/userCent/right.png"></image>
		</view>

		<view class="card">
			<view class="important">
				<view class="item" @click="showUnfinish">
					<image :src="'/static/userCent/wallet.png'"></image>
					<text>积分商城</text>
				</view>
				<view class="item" @click="toFav">
					<image :src="'/static/userCent/collect.png'"></image>
					<text>我的收藏</text>
				</view>
				<view class="item" @click="toHis">
					<image :src="'/static/userCent/order.png'"></image>
					<text>历史记录</text>
				</view>
			</view>
		</view>

		<view class="card">
			<view class="menu">
				<view class="item" v-for="v in menus1" :key="v.id" @click="config(v)">
					<image :src="v.icon"></image>
					<text>{{v.name}}</text>
					<image class="arrow" v-if="v.arrow" src="/static/center1/right.png"></image>
				</view>
			</view>
		</view>

		<view class="card">
			<view class="menu">
				<view class="item" v-for="v in menus2" :key="v.id" @click="config(v)">
					<image :src="v.icon"></image>
					<text>{{v.name}}</text>
					<image class="arrow" v-if="v.arrow" src="/static/center1/right.png"></image>
				</view>
			</view>
		</view>
		<view class="hide">

		</view>
	</view>
</template>


<script>
	export default {
		data() {
			return {
				menus1: [{
						id: 1,
						name: '个人资料',
						icon: '/static/userCent/idcard.png',
						arrow: true
					},
					{
						id: 2,
						name: '我的咨询',
						icon: '/static/userCent/comment.png',
						arrow: true
					},
					{
						id: 3,
						name: '我的消息',
						icon: '/static/userCent/bell.png',
						arrow: true
					}
				],
				menus2: [{
						id: 4,
						name: 'BUG反馈',
						icon: '/static/userCent/bug.png',
						arrow: true
					},
					{
						id: 5,
						name: '联系客服',
						icon: '/static/userCent/whatsapp.png',
						arrow: true
					},
					{
						id: 6,
						name: '账户设置',
						icon: '/static/userCent/setting.png',
						arrow: true
					},
				],
				username: "username",
				email: "email",
				userId: null,
				authorization: null,
				avatar:"/static/userCent/avatar.png"
			}
		},
		created() {
			const res = uni.getStorageSync("res")
			this.username = res.data.data.username;
			this.email = res.data.data.email;
			this.userId = res.data.data.userId;
			this.authorization = res.header.authorization;
			this.avatar=res.data.data.avatar;
		},
		methods: {
			config(item) {
				if (item.id == 6) {
					uni.navigateTo({
						url: '/pages/userCent/logout'
					})
				} else if (item.id == 2 || item.id == 3 || item.id == 4 || item.id == 5) {
					this.showUnfinish();
				} else if (item.id == 1) {
					this.jumpToProfile()
				}
			},
			jumpToProfile() {
				uni.navigateTo({
					url: "/pages/userCent/userProfile"
				})
			},
			showUnfinish() {
				uni.showToast({
					title: '正在开发中~',
					icon: 'error'
				});
			},
			toFav() {
				uni.request({
					url: this.$baseURL + '/favorite/' + this.userId,
					method: 'GET',
					header: {
						Authorization: this.authorization
					},
					success: res => {
						if (res.data.code == 200) {
							let fav = res.data.data.records;
							const allMusicTmp = uni.getStorageSync("allMusic").data.records;
							let favorite = []
							for (let j = 0; j < fav.length; j++) {
								for (let i = 0; i < allMusicTmp.length; i++) {
									if (allMusicTmp[i].recommendId === fav[j].musicId) {
										favorite[j] = allMusicTmp[i];
										break
									}
								}
							}
							uni.navigateTo({
								url: './result/result?result=' + encodeURIComponent(JSON.stringify(
									favorite))
							})
						}
					}
				})
			},
			toHis() {
				uni.request({
					url: this.$baseURL + '/history/' + this.userId,
					method: "GET",
					header: {
						Authorization: this.authorization
					},
					success: res => {
						const response = res.data.data.records;
						const allMusicTmp = uni.getStorageSync("allMusic").data.records;
						let history = []
						for (let j = 0; j < response.length; j++) {
							for (let i = 0; i < allMusicTmp.length; i++) {
								if (allMusicTmp[i].recommendId === response[j].musicId) {
									history[j] = allMusicTmp[i];
									break
								}
							}
						}
						uni.navigateTo({
							url: './result/result?result=' + encodeURIComponent(JSON.stringify(
								history))
						})
					}
				})
			}
		}
	}
</script>

<style lang="scss">
	.page {
		background: linear-gradient(180deg, #027AFF, #fff);
		min-height: 100vh;

		.user {
			display: flex;
			align-items: center;
			padding: 35rpx;

			&:active {
				opacity: 0.7;
			}

			.avatar {
				width: 100rpx;
				height: 100rpx;
				border-radius: 50%;
				left: 10rpx;
			}

			.right {
				width: 30rpx;
				height: 30rpx;
			}

			.name {
				height: 80rpx;
				flex-grow: 1;
				display: flex;
				flex-direction: column;
				padding-left: 50rpx;
				color: #fff;
				justify-content: space-between;

				text {
					&:nth-child(1) {
						font-size: 16px;
						font-weight: bold;
					}

					&:nth-child(2) {
						font-size: 12px;
						color: #f0f0f0;
					}
				}
			}
		}

		.card {
			background: #fff;
			box-sizing: border-box;
			width: 700rpx;
			margin: auto;
			padding: 15rpx;
			margin-top: 20rpx;
			border-radius: 12rpx;

			.important {
				display: flex;
				justify-content: center;

				.item {
					width: 225rpx;
					padding: 15rpx 0;
					display: flex;
					flex-direction: column;
					align-items: center;
					border-radius: 12rpx;

					&:active {
						background: #f0f0f0;
					}

					image {
						width: 80rpx;
						height: 80rpx;
						margin-bottom: 10rpx;
					}

					text {
						font-size: 12px;
					}
				}
			}

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
			height: 15vh;
		}
	}
</style>