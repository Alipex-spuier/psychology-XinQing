<template>
	<view class="niceui-flex-column width-100p">
		<view class="bg">
			<image class="login-bg-img" src="/static/login/login-top.png" mode="widthFix"></image>
			<view class="logo">
				<view class="logo-border niceui-flex-column">
					<image class="logo-img" src="/static/logo.png" mode="widthFix"></image>
					<text class="logo-name">{{title}}</text>
				</view>
			</view>
		</view>
		<view class="login-form">
			<view class="form-item">
				<input class="input account" v-model="account" type="text" placeholder="请输入注册账号"
					placeholder-style="color:#c9c9c9;" />
			</view>
			<view class="form-item">
				<input class="input account" v-model="email" type="text" placeholder="请输入注册邮箱"
					placeholder-style="color:#c9c9c9;" />
			</view>
			<view class="form-item">
				<input class="input password" v-model="password" password type="text" placeholder="请输入注册密码"
					placeholder-style="color:#c9c9c9;" />
			</view>
			<view class="form-item">
				<input class="input password" v-model="confirmPassword" password type="text" placeholder="请再次输入注册密码"
					placeholder-style="color:#c9c9c9;" />
			</view>
			<text class="forget-pass" @click="jumpToLogin">已有账号？去登录</text>
			<view class="login-btns">
				<view class="btn-view login" @click="register"><text class="btn-text">注册</text></view>
			</view>

		</view>
	</view>
</template>

<script>
	export default {
		data() {
			return {
				title: "清 · 听",
				slogan: "让心情遇见音乐的美好",
				account: '',
				password: '',
				confirmPassword: '',
				email: ''
			}
		},
		methods: {
			checkEmail(email) {
				const emailReg=/^\w+([-+.]\w+)*@\w+([-.]\w+)*\.\w+([-.]\w+)*$/;
				if (emailReg.test(email)) {
					return true;
				} else {
					return false;
				}
			},
			register() {
				if(this.account==""){
					uni.showToast({
						title: '注册账号不能为空',
						icon: 'none'
					});
					return;					
				}
				if (this.email == "") {
					uni.showToast({
						title: '请输入邮箱地址',
						icon: 'none'
					});
					return;
				}
				if (!this.checkEmail(this.email)) {
					uni.showToast({
						title: '邮箱格式不正确',
						icon: 'none'
					});
					return;
				}
				if(this.password==""){
					uni.showToast({
						title: '请输入密码',
						icon: 'none'
					});
					return;					
				}
				if(this.confirmPassword==""){
					uni.showToast({
						title: '请再次输入密码',
						icon: 'none'
					});
					return;					
				}

				if (this.password !== this.confirmPassword) {
					uni.showToast({
						title: '两次密码输入不一致',
						icon: 'none'
					});
					return;
				}
				uni.request({
					url: this.$baseURL + '/account/register',
					method: 'POST',
					data: {
						username: this.account,
						password: this.password,
						email: this.email,
						status: 1
					},
					success: res => {
						if (res.data.code === 200) {
							uni.showToast({
								title: '注册成功',
								success: function() {
									setTimeout(function() {
										uni.navigateTo({
											url: '/pages/login/login'
										})
									}, 2000)
								}
							});

						} else {
							uni.showToast({
								title: '注册失败：' + res.data.msg,
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
			},
			jumpToLogin(){
				uni.navigateTo({
					url:"/pages/login/login"
				})
			}
		}
	}
</script>

<style>
	@font-face {
		font-family: texticons;
		font-weight: normal;
		font-style: normal;
		src: url('https://at.alicdn.com/t/c/font_4384164_5f2pie5s8hs.ttf?t=1703254408937') format('truetype');
	}

	page,
	view {
		display: flex;
	}

	page {
		background-color: #fff;
	}

	.niceui-flex-column {
		flex-direction: column;
	}

	.niceui-flex-row {
		flex-direction: row !important;
	}

	.niceui-center {
		align-items: center;
	}

	.niceui-between {
		justify-content: space-between;
	}

	.width-100p {
		width: 100%;
	}
</style>
<style lang="scss" scoped>
	$head-color: #027AFF;
	$white-color: #fff;
	$radius: 20rpx;
	$border-color: #efefef;
	$color-1: #007aff;
	$color-2: #4cd964;
	$color-3: #f0ad4e;
	$color-4: #dd524d;
	$list-item-height: 100rpx;
	$list-margin: 20rpx;
	$leftColor: #027AFF;
	$rightColor: #fa886a;

	.bg {
		background-image: linear-gradient(to bottom, $leftColor, $rightColor);
		height: 500rpx;
		width: 1400rpx;
		position: absolute;
		top: 0;
		left: 50%;
		margin-left: -700rpx;
		border-bottom-left-radius: 100%;
		border-bottom-right-radius: 100%;
		flex-direction: column;
		align-items: center;
	}

	.login-bg-img {
		width: 100vw;
		margin-top: 22rpx;
	}

	.logo {
		position: absolute;
		z-index: 9;
		width: 100%;
		height: 450rpx;
		flex-direction: row;
		justify-content: center;
		align-items: flex-end;
	}

	.logo-border {
		align-items: center;
	}

	.logo-img {
		width: 160rpx;
		border-radius: 30rpx;
		margin-top: 130rpx;
	}

	.logo-name {
		margin: 30rpx 0 0rpx;
		font-size: 38rpx;
		color: #fff;
		font-weight: bold;
	}

	.logo-slogan {
		font-size: 34rpx;
		color: #fff;
		letter-spacing: 9rpx;
	}


	.login-form {
		margin-top: 500rpx;
		padding: 120rpx 0 50rpx;
		flex-direction: column;
		align-items: center;
	}

	.form-item {
		margin: 45rpx 0 25rpx;
	}

	.input {
		height: 65rpx;
		line-height: 65rpx;
		width: 550rpx;
		border-bottom: solid 2px #f2f2f2;
		position: relative;
		padding-left: 60rpx;
		box-sizing: border-box;
		font-size: 36rpx;
		color: #999;
	}

	.input_p {
		font-size: 32rpx;
		color: #c9c9c9;
	}

	.account::before {
		content: '\e61d';
		font-family: texticons;
		position: absolute;
		z-index: 9;
		left: 0rpx;
		bottom: 5rpx;
		font-size: 60rpx;
		color: #0169d8;
	}

	.password::before {
		content: '\e623';
		font-family: texticons;
		position: absolute;
		z-index: 9;
		left: 0rpx;
		bottom: 1rpx;
		font-size: 60rpx;
		color: #0169d8;
	}

	.forget-pass {
		color: #c9c9c9;
		font-size: 30rpx;
		display: block;
		width: 550rpx;
		text-align: right;
	}

	.login-btns {
		height: 100rpx;
		width: 100%;
		box-sizing: border-box;
		flex-direction: row;
		justify-content: center;
		align-items: center;
	}

	.btn-view {
		width: 500rpx;
		margin: 200rpx auto 0;
		border-radius: 40rpx;
		height: 90rpx;
		flex-direction: row;
		justify-content: center;
		align-items: center;
	}

	.login {
		background-image: linear-gradient(to right, $leftColor, $rightColor);
	}

	.btn-text {
		color: #fff;
		font-size: 36rpx;
	}
</style>