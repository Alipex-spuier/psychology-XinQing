<template>
	<view class="niceui-flex-column width-100p bg">
		<image class="login-bg-img" src="/static/login/login-bg.png" mode="widthFix"></image>
		<view class="logo">
			<view class="logo-border niceui-flex-column">
				<image class="logo-img" src="/static/logo.png" mode="widthFix"></image>
				<text class="logo-name">{{title}}</text>
				<text class="logo-slogan">{{slogan}}</text>
			</view>
		</view>
		<view class="login-btns">
			<view class="btn-view login" @click="login"><text class="btn-text">登录</text></view>
			<view class="btn-view register" @click="register"><text class="btn-text">注册</text></view>
		</view>
	</view>
</template>

<script>
	export default {
		data() {
			return {
				title:"清 · 听",
				slogan:"让心情遇见音乐的美好",
				backButtonPress:0,
			}
		},
		onBackPress(options) {
		　　this.backButtonPress++;
		　　if (this.backButtonPress > 1) { 
		　　　　plus.runtime.quit();
		　　} else {
		　　　　plus.nativeUI.toast('再按一次退出应用');
		　　} 
		　　setTimeout(function() {
		　　　　this.backButtonPress = 0;
		　　}, 1000);
		　　return true;
		},
		methods: {
			login(){
				uni.navigateTo({
					url:'/pages/login/login'
				})
			},
			register() {
				uni.navigateTo({
					url:'/pages/login/register'
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
		src: url('https://at.alicdn.com/t/c/font_4382910_1bcgyhz3rqw.ttf?t=1703216704048') format('truetype');
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
		flex-direction: row!important;
	}
	.niceui-center{
		align-items: center;
	}
	.niceui-between{
		justify-content: space-between;
	}
	.width-100p{
		width:100%;
	}
</style>
<style lang="scss" scoped>
	$head-color:#cd3b33;
	$white-color:#fff;
	$radius:20rpx;
	$border-color:#efefef;
	$color-1: #007aff;
	$color-2: #4cd964;
	$color-3: #f0ad4e;
	$color-4: #dd524d;
	$list-item-height:100rpx;
	$list-margin:20rpx;
	$leftColor:#fdd447;
	$rightColor:#fa886a;
	.bg{
		background-image: linear-gradient(to bottom,$leftColor,$rightColor);
		height: 100vh;
	}	

	.login-bg-img{
		width: 100%;
		margin-top: 100rpx;
	}
	.logo{
		position: absolute;
		z-index: 9;
		width: 100%;
		height: 100%;
		flex-direction: row;
		justify-content: center;
		align-items: center;
	}
	.logo-border{
		align-items: center;
	}
	.logo-img{
		width: 200rpx;
		border-radius: 30rpx;
	}
	.logo-name{
		margin: 30rpx 0 30rpx;
		font-size: 38rpx;
		color: #fff;
		font-weight: bold;
	}
	.logo-slogan{
		font-size: 34rpx;
		color: #fff;
		letter-spacing: 9rpx;
	}
	.login-btns{
		position: absolute;
		z-index: 9;
		bottom:120rpx;
		height: 100rpx;
		width: 100%;
		padding: 0 30rpx;
		box-sizing: border-box;
		flex-direction: row;
		justify-content: space-between;
		align-items: center;
	}
	.btn-view{
		width:310rpx;
		margin: 0rpx auto;
		border-radius: 40rpx;
		height: 80rpx;
		flex-direction: row;
		justify-content: center;
		align-items: center;
	}
	.login{
		background-color: #fff;
	}
	.register{
		background-color: #ffe8e3;
	}
	.btn-text{
		color: $leftColor;
		font-size: 32rpx;
	}
</style>