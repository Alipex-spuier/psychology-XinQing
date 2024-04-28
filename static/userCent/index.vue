<template>
	<view class="page">
		<view class="user">
			<image class="avatar" src="/static/center5/avatar.png"></image>
			<view class="name">
				<text>易师傅</text>
				<text>工作党</text>
			</view>
			<image class="right" src="/static/center5/right.png"></image>
		</view>
		
		<view class="card">
			<view class="important">
				<view class="item">
					<image :src="'/static/center5/wallet.png'"></image>
					<text>我的钱包</text>
				</view>
				<view class="item">
					<image :src="'/static/center5/collect.png'"></image>
					<text>我的收藏</text>
				</view>
				<view class="item">
					<image :src="'/static/center5/order.png'"></image>
					<text>商品订单</text>
				</view>
			</view>
		</view>
		
		<view class="card">
			<view class="menu">
				<view class="item" v-for="v in menus1" :key="v.id">
					<image :src="v.icon"></image>
					<text>{{v.name}}</text>
					<image class="arrow" v-if="v.arrow" src="/static/center1/right.png"></image>
				</view>
			</view>
		</view>
		
		<view class="card">
			<view class="menu">
				<view class="item" v-for="v in menus2" :key="v.id">
					<image :src="v.icon"></image>
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
				menus1: [
					{id: 1, name: '我的书籍', icon: '/static/center5/book.png', arrow: true},
					{id: 2, name: '我的账户', icon: '/static/center5/self.png', arrow: true},
					{id: 3, name: '图书捐赠', icon: '/static/center5/heart.png', arrow: true}
				],
				menus2: [
					{id: 4, name: '联系客服', icon: '/static/center5/kefu.png', arrow: true},
					{id: 5, name: '分享应用', icon: '/static/center5/share.png', arrow: false},
					{id: 6, name: '退出登录', icon: '/static/center5/out.png', arrow: false},
				]
			}
		}
	}
</script>

<style lang="scss">
	.page {
		background: linear-gradient(180deg, #00946c, #fff);
		min-height: 100vh;
		
		.user{
			display: flex;
			align-items: center;
			padding: 25rpx;
			
			&:active{
				background: #006246;
			}
			
			.avatar{
				width: 90rpx;
				height: 90rpx;
				border-radius: 50%;
			}
			.right{
				width: 30rpx;
				height: 30rpx;
			}
			
			.name{
				height: 80rpx;
				flex-grow: 1;
				display: flex;
				flex-direction: column;
				padding-left: 20rpx;
				color: #fff;
				justify-content: space-between;
				
				text{
					&:nth-child(1){
						font-size: 16px;
						font-weight: bold;
					}
					
					&:nth-child(2){
						font-size: 12px;
						color: #f0f0f0;
					}
				}
			}
		}
		
		.card{
			background: #fff;
			box-sizing: border-box;
			width: 700rpx;
			margin: auto;
			padding: 15rpx;
			margin-top: 20rpx;
			border-radius: 12rpx;
			
			.important{
				display: flex;
				justify-content: center;
				
				.item{
					width: 225rpx;
					padding: 15rpx 0;
					display: flex;
					flex-direction: column;
					align-items: center;
					border-radius: 12rpx;
					
					&:active{
						background: #f0f0f0;
					}	
					
					image{
						width: 80rpx;
						height: 80rpx;
						margin-bottom: 10rpx;
					}
					
					text{
						font-size: 12px;
					}
				}
			}
			
			.menu{
				
				.item{
					display: flex;
					height: 100rpx;
					align-items: center;
					padding: 0 20rpx;
					box-sizing: border-box;
					width: 700rpx;
					
					&:active{
						background: #f0f0f0;
					}
					
					image{
						height: 45rpx;
						width: 45rpx;
					}
					
					text{
						font-size: 14px;
						color: #444;
						padding-left: 15rpx;
						box-sizing: border-box;
						width: calc(100% - 100rpx);
					}
					
					.arrow{
						width: 26rpx;
						height: 26rpx;
					}
				}
			}
		}
		
	}
</style>