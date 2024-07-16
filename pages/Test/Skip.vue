<template>
	<!-- <view class="">
		
		<view class="nav-bar" :class="navLevel+'-bg'">	
			<view class="nav-bar-wrapper">
				<view class="nav-bar-item" v-for="(item,index) in nav" :key="index" @tap="redirectToInput(item)">
					<text class="nav-bar-item-icon" :class="item.class">{{item.icon}}</text>
					<text class="nav-bar-item-text">{{item.text}}</text>
				</view>
			</view>
		</view>
	</view> -->
	
	<view class="page">
		<view class="hide">
		</view>
		<view class="card">
			<view class="menu">
				<view class="item" v-for="v in menus1" :key="v.id" @click="redirectToInput(v)">
				
					<!-- <view class="icon-name">
						<view class="icon">{{v.icon}}</view>
						<text class="name">{{v.name}}</text>
					</view> -->
					<!-- <view class="icon">{{v.icon}}</view> -->
					<text>{{v.name}}</text>
					<image class="arrow" v-if="v.arrow" src="/static/center1/right.png"></image>
				</view>
			</view>
			
			
		</view>
	</view>
</template>

<script>
	export default {
		name:'SKIPS',
		created() {
			// this.userId = res.data.data.userId;
			// this.authorization = res.header.authorization;
		},
		mounted() {
			// this.userId = res.data.data.userId;
			// this.authorization = res.header.authorization;
		},
		data() {
			return {
				circular: false,
				indicatorDots: true,
				autoplay: false,
				interval: 2000,
				duration: 500,
				Focus: false,
				userId: null,
				searchQuery: '',
				searchResults: [],
				indicatorColor: '#e9e9e9',
				indicatorActiveColor: 'red',
				leftRightMargin: '0rpx',
				testNumber :1,
				authorization: null,
				navLevel: 'nav1',
				
				menus1: [{
						id: 1,
						icon: "\u{e7b6}" ,
						name: '我感到自卑',
						class: "nav1-color",
						arrow: true
					},
					{
						id: 2,
						icon: "\u{e709} " ,
						name: '我有抑郁症',
						class: "nav2-color",
						arrow: true
					},
					{
						id: 3,
						icon: " \u{e642}" ,
						name: '我最近很伤心',
						class: "nav3-color",
						arrow: true
					},{
						id: 4,
						icon:"\u{e628} " ,
						name: '我陷入疯狂',
						class: "nav4-color",
						arrow: true
					}]
			
			}
		},

		methods: {
			redirectToInput(item) {
				this.testNumber=item.id;
				
				uni.navigateTo({
					url: './Test?testNumber=' + encodeURIComponent(JSON.stringify(this.testNumber))
				})
				// uni.navigateTo({
				// 	url:'./Test'
				// })
				
				/*uni.navigateTo({
							url: './result/result?result=' + encodeURIComponent(JSON.stringify(res.data
								.records))
						})*/
			},	
		}
	}
</script>

<style>
	
/* 在线链接服务仅供平台体验和调试使用，平台不承诺服务的稳定性，企业客户需下载字体包自行发布使用并做好备份。 */
@font-face {
	font-weight: normal;
	font-style: normal;
	font-family: texticons;
  src: url('https://at.alicdn.com/t/c/font_4618012_42efo7gjd5x.ttf?t=1720689238767') format('truetype');
}
	/* @font-face {

		font-family: texticons;
		font-weight: normal;
		font-style: normal;
		src: url('https://at.alicdn.com/t/c/font_4082971_rjo1s6t6mpj.ttf?t=1710063944182') format('truetype');
	}
	
	@font-face {

		font-weight: normal;
		font-style: normal;
		src: url('https://at.alicdn.com/t/c/font_4528601_ny9blfp6n89.ttf?t=1714297680581') format('truetype');
	} */

	page,
	view {
		border: 0;
		margin: 0;
		padding: 0;
		display: flex;
		flex-direction: column;
	}

	view,
	image,
	text {
		box-sizing: border-box;
		flex-shrink: 0;
	}

	.niceui-flex-column {
		flex-direction: column;
	}
</style>
<style lang="scss">
	.page {
		background: linear-gradient(180deg, #fdd447, #fff);
		min-height: 100vh;
	
		.card {
			background: #fff;
			box-sizing: border-box;
			width: 700rpx;
			margin: auto;
			padding: 15rpx 0rpx;
			margin-top: 0rpx;
			border-radius: 12rpx;
	
			.menu {
	
				.item {
					display: flex;
					height: 200rpx;
					align-items: center;
					padding: 0 20rpx;
					// padding-right: 10rpx;
					box-sizing: border-box;
					width: 700rpx;
					position: relative;
	
					&:active {
						background: #f0f0f0;
					}
	
					image {
						height: 45rpx;
						width: 45rpx;
					}
	
					text {
						position: absolute;
						font-size: 20px;
						color: #ff5500;
						padding-top: 10%;
						padding-left: 15rpx;
						float: left;
						width: calc(100% - 100rpx);
					}
					
					.icon{
						// position: absolute;
						// float: right;
						// padding-right: 5px;
						justify-content: center;
						font-family: texticons;
						flex-direction: column;
						padding-top:37px;
						// padding-right: 0rpx;
						color: #03c500;
						width: 50rpx;
						height: 125rpx;
					}
					
					// .arrow {
					// 	width: 500rpx;
					// 	height: 500rpx;
					// }
				}
				
			}
		}
	
		.hide {
			width: 100vw;
			height: 5vh;
		}
	}
	.nav-bar-item-icon {
		font-size: 55rpx;
		font-weight: bold;
		font-family: texticons;
		color: green;
		text-align: center;
	}
	
	
.nav1-color {
		color: #03c500;
		font-size: 70rpx;
	}

	.nav2-color {
		color: #46c0ff;
		font-size: 70rpx;
	}

	.nav3-color {
		color: #f49fde;
		font-size: 70rpx;
	}

	.nav4-color {
		color: #ff00ff;
		font-size: 70rpx;
	}

</style>
