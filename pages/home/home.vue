<template>
	<view class="">
		<!--banner-->
		<view class="banner-bar">
			<view class="search-input-wrapper">
				<view class="banner-header">
					<view class="header-title">清·听</view>
					<view class="header-sub-title"></view>
				</view>
				<text class="search-input-icon">{{'\u{e619}'}}</text>
				<input class="uni-input search-input" placeholder="搜索" />
			</view>
		</view>
		<swiper class="swiper" :circular="circular" :indicator-dots="indicatorDots" :autoplay="autoplay" :interval="interval"
			:duration="duration" :indicator-color="indicatorColor" :indicator-active-color="activeColor[current]"
			:previous-margin="leftRightMargin" :next-margin="leftRightMargin"
			@change="changeItem">
			<swiper-item v-for="(url,x) in banner" :key="x">
				<view class="banner-bar">
					<view class="banner-img-wrapper">
						<image :src="url" mode="widthFix" class="banner-img"></image>
					</view>
				</view>
			</swiper-item>
		</swiper>
		<!--nav-->
		<view class="nav-bar" :class="navLevel+'-bg'">
			<view class="notice-bar">
				<view class="notice-bar-wrapper">
					<view class="notice-bar-left">
						<text class="notice-bar-text"> 听 你 想 听 </text>
					</view>
				</view>
			</view>
			<view class="nav-bar-wrapper">
				<view class="nav-bar-item" v-for="(item,index) in nav" :key="index">
					<text class="nav-bar-item-icon" :class="item.class">{{item.icon}}</text>
					<text class="nav-bar-item-text">{{item.text}}</text>
				</view>
			</view>
			<view class="nav-image-wrapper">
				<view class="nav-image-item-view" v-for="(item,index) in ruk" :key="index">
					<image :src="item.image" class="nav-image-item" mode="widthFix"></image>
					<view class="nav-image-content-wrapper">
						<view class="nav-image-content">
							<text class="nav-image-text">{{item.text}}</text>
							<text class="nav-image-text-sm">随便听听</text>
						</view>
						
					</view>
				</view>
			</view>
		</view>
		
		<view class="line-bar">
			<view class="line-bar-wrapper">
				<view class="bar-title">
					<view class="bar-title-left">歌单遨游指南</view>
					<text class="bar-title-right">全部 ></text>
				</view>
				<view class="line-bar-item-wrapper">
					<view class="line-bar-item" v-for="(item,index) in line" :key="index">
						<image :src="item.image" class="line-bar-item-image" mode="aspectFill"></image>
						<view>
							<text class="text-title">{{item.textTitle}}</text>
							<text class="text-sub-title">{{item.textSubTitle}}</text>
						</view>
					</view>
				</view>
			</view>			
		</view>

		<view class="qu-bar">
			<view class="qu-bar-wrapper">
				<view class="bar-title">
					<view class="bar-title-left">为你推荐</view>
					<text class="bar-title-right">全部 ></text>
				</view>
				<view class="qu-bar-item" v-for="(item,index) in qu" :key="index">
					<image :src="item.image" class="qu-bar-item-image" mode="aspectFill"></image>
					<view>
						<text class="text-title">{{item.textTitle}}</text>
						<text class="text-sub-title">{{item.textSubTitle}}</text>
					</view>
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
				circular:false,
				indicatorDots: true,
				autoplay: false,
				interval: 2000,
				duration: 500,
				indicatorColor:'#e9e9e9',
				indicatorActiveColor:'red',
				leftRightMargin:'0rpx',
				current:0,
				navLevel:'nav1',
				activeColor:['#ffe3cb','#ffe3cb','#ffe3cb','#ffe3cb','#1d1b1b'],  //#a7b0e6; #e6bd79; #d49a67;
				banner:[
					"https://bpic.51yuansu.com/backgd/cover/00/56/62/5cff637cd5ad6.jpg?x-oss-process=image/resize,h_300,m_lfit/sharpen,100",
					"https://img.pconline.com.cn/images/upload/upc/tx/photoblog/1309/10/c2/25481656_25481656_1378769655073.jpg",
					"https://x0.ifengimg.com/res/2020/A2D9583258A2636BD93565B536832E77E60164CD_size214_w1360_h857.jpeg",
					"https://up.deskbizhi.com/pic/0c/e3/90/0ce390fba8519afa327c3c82c41097af.jpg"
				],
				nav:[
					{
						icon:"\u{e63b}",
						text:'搜一搜',
						class:"nav1-color"
					},
					{
						icon:"\u{e601}",
						text:'推荐歌单',
						class:"nav2-color"
					},
					{
						icon:"\u{e600}",
						text:'心情指南',
						class:"nav3-color"
					},
					{
						icon:"\u{e763}",
						text:'历史纪录',
						class:"nav4-color"
					},
				],
				ruk:[
					{
						image:"http://170.106.183.24:8081/photo/134.jpg",
						text:"Skyline Mirage"
					},
					{
						image:"http://170.106.183.24:8081/photo/78.jpg",
						text:"Told Me"
					}
				],
				line:[
					{
						image:"../static/happy.jpg",
						textTitle:'开心',
						textSubTitle:'情绪的高地｜欢乐的源泉',
						price:15
						
					},
					{
						image:"../static/surprise.jpg",
						textTitle:'惊喜',
						textSubTitle:'意外的收获｜愉悦的惊奇',
						price:15
						
					},
					{
						image:"../static/neutral.jpg",
						textTitle:'平静',
						textSubTitle:'内心的宁静｜生活的沉淀',
						price:15
						
					}
				],
				qu:[
					{
						image:"https://img0.baidu.com/it/u=1893845159,3805671064&fm=253&fmt=auto&app=138&f=JPEG?w=677&h=408",
						textTitle:'刘公岛景区',
						textSubTitle:'历史的印迹｜心灵的旅行',
						price:15
						
					},
					{
						image:"https://wscdn.ql1d.com/20190702v2KG7VS5XyoLdINYjZar.jpeg",
						textTitle:'退役驱逐舰景区',
						textSubTitle:'军事主题｜研学教育',
						price:15
						
					},
					{
						image:"https://img2.baidu.com/it/u=3977521327,1380190115&fm=253&fmt=auto&app=138&f=JPEG?w=690&h=450",
						textTitle:'刘公岛景区',
						textSubTitle:'帆船赛事｜游艇观光',
						price:15
						
					}
				]
			}
		},
		methods: {
			changeItem(e){
				this.current = e.detail.current
				this.navLevel = "nav"+(e.detail.current+1)
			}
		}
	}	
</script>

<style>
	@font-face {
		
		font-family: texticons;
		font-weight: normal;
		font-style: normal;
		src: url('https://at.alicdn.com/t/c/font_4082971_rjo1s6t6mpj.ttf?t=1710063944182') format('truetype');
		src: url('https://at.alicdn.com/t/c/font_4528601_ny9blfp6n89.woff2?t=1714297680581') format('woff2'),
		       url('https://at.alicdn.com/t/c/font_4528601_ny9blfp6n89.woff?t=1714297680581') format('woff'),
		       url('https://at.alicdn.com/t/c/font_4528601_ny9blfp6n89.ttf?t=1714297680581') format('truetype');
	}

	page,
	view{
		border:0;
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
	$margin:35rpx;
	$radius:30rpx;
	$bg:#194f8a;

	.swiper{
		width: 100vw;
		padding: 0rpx 0 0;
		height: 466rpx;
		box-sizing: border-box;
	}
	.banner-img{
		width: 100%;
	}
	.banner-header{
		margin: 10rpx $margin 10rpx;
		flex-direction: row;
		color:#fff;
	}
	.header-title{
		font-size: 33rpx;
	}
	.header-sub-title{
		font-size: 22rpx;
		padding-top: 10rpx;
		padding-left: 20rpx;
	}
	.search-input-wrapper{
		position: absolute;
		z-index: 10;
		top:90rpx;
		width: 100%;
	}
	.search-input-icon{
		position: absolute;
		z-index: 10;
		left:60rpx;
		top:106rpx;
		font-size:30rpx;
		font-weight: normal;
		font-family: texticons;
		color:#666;
		text-align: center;
	}
	.search-input{
		background: rgba(255,255,255,.7);
		color:#fff;
		border-radius: 50rpx;
		width: calc(100% - 70rpx);
		height: 70rpx;
		margin: 20rpx auto;
		padding-left: 60rpx;
		box-sizing: border-box;
	}
	.uni-input-placeholder{
		color:#666;
		font-size: 28rpx;
	}
	.nav1-bg{
		background-image: linear-gradient(to bottom,#6a4860,$bg);
	}
	.nav2-bg{
		background-image: linear-gradient(to bottom,#1f4377,$bg);
	}
	.nav3-bg{
		background-image: linear-gradient(to bottom,#282433,$bg);
	}
	.nav4-bg{
		background-image: linear-gradient(to bottom,#0e0e18,$bg);
	}
	
	.nav-bar,.qu-bar,.line-bar{
		background-color: $bg;
	}
	.notice-bar{
		margin-top: 20rpx;
	}
	.notice-bar-wrapper{
		color:#fff;
		font-size: 28rpx;
		background: rgba(230,230,230,0.15);
		border-radius: 30rpx;
		width: calc(100% - 70rpx);
		height: 65rpx;
		margin: 0rpx auto 10rpx;
		flex-direction: row;
		justify-content: space-between;
		align-items: center;
		padding: 5rpx 30rpx;
		box-sizing:border-box;
	}
	.notice-bar-left{
		flex-direction: row;
		align-items: center;
	}
	.notice-bar-icon{
		font-size:30rpx;
		font-weight: normal;
		font-family: texticons;
		color:#fff;
		text-align: center;
	}
	.notice-bar-text{
		padding-left: 15rpx;
	}

	.nav-bar-wrapper{
		flex-direction: row;
		align-items: center;
		background-color: #fff;
		box-sizing: border-box;
		margin: 10rpx $margin 10rpx;
		border-radius: $radius;
		height: 170rpx;
	}
	.nav-bar-item{
		flex:1;
	}
	.nav-bar-item-icon{
		font-size:55rpx;
		font-weight: bold;
		font-family: texticons;
		color:green;
		text-align: center;
	}
	.nav1-color{
		color:#03c500;
		font-size:70rpx;
	}
	.nav2-color{
		color:#46c0ff;
		font-size:70rpx;
	}
	.nav3-color{
		color:#ff9005;
	}
	.nav4-color{
		color:#ffc143;
	}
	.nav-bar-item-text{
		text-align: center;
		font-size: 26rpx;
		color: #666;
		margin-top: 5rpx;
	}
	.nav-image-wrapper{
		flex-direction: row;
		margin: 10rpx $margin 10rpx;
	}
	.nav-image-item-view{
		flex:1;
		margin: 0rpx 0;
		border-radius: $radius;
		width: 46%;
	}
	.nav-image-item-view + .nav-image-item-view{
		margin-left: 4%;
	}
	.nav-image-item-view{
		position: relative;
	}
	.nav-image-item{
		width: 100%;
		margin: 0rpx 0;
		border-radius: $radius;
	}
	.nav-image-content-wrapper{
		position: absolute;
		z-index: 10;
		width: 100%;
		height: 100%;
		flex-direction: row;
		justify-content: center;
		align-items: center;
	}
	.nav-image-content{
		color:#fff;
		height: 100rpx;
		text-align: center;
		padding-top: 15rpx;
	}
	.nav-image-text{
		font-size:30rpx;
		font-weight: bold;
	}
	.nav-image-text-sm{
		font-size:26rpx;
	}
	
	.qu-bar-wrapper{
		margin: 10rpx $margin 10rpx;
	}
	.qu-bar-item{
		border-radius: $radius;
		background-color: #fff;
		margin-bottom: 35rpx;
	}
	.qu-bar-item-image{
		width: 100%;
		height: 240rpx;
		border-top-left-radius: $radius;
		border-top-right-radius: $radius;
	}
	.bar-title{
		flex-direction: row;
		justify-content: space-between;
		align-items: center;
		margin-top: 50rpx;
		margin-bottom: 25rpx;
	}
	.bar-title-left{
		font-size: 35rpx;
		font-weight: bold;
		color:#fff;
	}
	.bar-title-right{
		font-size: 26rpx;
		font-weight: normal;
		color:#fff;
	}
	.text-title{
		font-size: 32rpx;
		font-weight: bold;
		padding-left:30rpx;
		padding-top:10rpx ;
		box-sizing: border-box;
		
	}
	.text-sub-title{
		font-size: 28rpx;
		font-weight: normal;
		padding-left:30rpx;
		padding-bottom:10rpx ;
		box-sizing: border-box;
	}
	.line-bar-wrapper{
		margin: 10rpx $margin 10rpx;
	}
	.line-bar-item-wrapper{
		flex-direction: row;
		flex-wrap: nowrap;
		justify-content: flex-start;
		overflow-x: scroll;
	}
	.line-bar-item{
		width: 500rpx;
		border-radius: $radius;
		background-color: #fff;
	}
	.line-bar-item + .line-bar-item{
		margin-left: 30rpx;
	}
	.line-bar-item-image{
		width: 100%;
		height: 260rpx;
		border-top-left-radius: $radius;
		border-top-right-radius: $radius;
	}
	.hide{
		width: 100vw;
		height: 5vh;
	}
</style>