<template>
	<view class="">
		<!--banner-->
		<view class="banner-bar">
			<view class="search-input-wrapper">
				<view class="banner-header">
					<view class="header-title">心 · 晴</view>
					<view class="header-sub-title">心晴心理咨询，让心灵与晴天同行</view>
				</view>
				<svg t="1721113443883" class="icon search-input-icon" viewBox="0 0 1024 1024" version="1.1" xmlns="http://www.w3.org/2000/svg" p-id="1543" width="21" height="21"><path d="M953.474215 908.234504l-152.576516-163.241391c61.92508-74.48211 95.81186-167.36973 95.81186-265.073744 0-229.294809-186.63531-415.930119-416.102133-415.930119-229.294809 0-415.930119 186.63531-415.930119 415.930119s186.63531 415.930119 415.930119 415.930119c60.032925 0 118.00168-12.55703 172.186125-37.327062 16.169326-7.396607 23.221905-26.318159 15.825298-42.315471-7.396607-16.169326-26.318159-23.221905-42.315471-15.825298-45.927768 20.813707-94.951789 31.478582-145.695952 31.478582-194.031917 0-351.94087-157.908953-351.94087-351.94087 0-194.031917 157.908953-351.94087 351.94087-351.94087 194.031917 0 351.94087 157.908953 351.94087 351.94087 0 91.339493-34.918864 177.86259-98.048043 243.743995-12.213002 12.729044-11.868974 33.026709 0.860071 45.239711 1.032085 0.860071 2.236183 1.204099 3.268268 2.064169 0.860071 1.204099 1.376113 2.752226 2.408198 3.956325l165.477574 177.00252c6.192508 6.70855 14.793214 10.148833 23.393919 10.148833 7.912649 0 15.653284-2.92424 21.845792-8.600706C964.827146 941.433227 965.515202 921.135562 953.474215 908.234504z" fill="#2c2c2c" p-id="1544"></path></svg>
				<input class="uni-input search-input" placeholder="搜索你关心的心理方向 ~" @input="onInput"
					@confirm="getSearchMusic()" :focus="Focus" />
			</view>
		</view>
		<swiper class="swiper" :circular="circular" :indicator-dots="indicatorDots" :autoplay="autoplay"
			:interval="interval" :duration="duration" :indicator-color="indicatorColor"
			:indicator-active-color="activeColor[current]" :previous-margin="leftRightMargin"
			:next-margin="leftRightMargin" @change="changeItem">
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
						<text class="notice-bar-text">当下的舒缓，是为了发现更美好的生活</text>
					</view>
				</view>
			</view>
			
			<view class="nav-bar-wrapper">
				<view class="nav-bar-item" v-for="(item,index) in nav" :key="index" @tap="redirectToInput(item)">
					<text class="nav-bar-item-icon" :class="item.class">{{item.icon}}</text>
					<text class="nav-bar-item-text">{{item.text}}</text>
				</view>
			</view>
			
			<view class="nav-image-wrapper" >
				<!-- <view class="nav-image-item-view" v-for="(item,index) in ruk" :key="index" @click="itemClick(item)"> -->
				<view class="nav-image-item-view" v-for="(item,index) in ruk" :key="index">
					<!-- <image :src="item.albumCover" class="nav-image-item" mode="widthFix"></image> -->
					<view class="nav-image-content-wrapper">
						<view class="nav-image-content">
							<text class="nav-image-text">{{city}}</text>
							<text class="nav-image-text-sm">{{temperature}}</text>
						</view>
					</view>
				</view>
			</view>
			
		</view>

		<view class="qu-bar">
			<view class="qu-bar-wrapper">
				<view class="bar-title">
					<view class="bar-title-left">为你推荐</view>
				</view>
				<view class="qu-bar-item" v-for="(item,index) in qu" :key="index" @click="itemClick(item)">
					<image :src="item.albumCover" class="qu-bar-item-image" mode="aspectFill"></image>
					<view>
						<text class="text-title">{{item.title}}</text>
						<text class="text-sub-title">{{item.hotComment}}</text>
					</view>
				</view>
			</view>
			<view class="hide">

			</view>
		</view>
	</view>
</template>

<script>
	export default {
		name:'HOMES',
		created() {
			this.getAllMusic();
			const res = uni.getStorageSync("res");
			this.userId = res.data.data.userId;
			this.authorization = res.header.authorization;
			//this.getMusic(this.ruk);
			this.getArtcles();
			//this.getMusic(this.qu);
			this.getLocation();
		},
		mounted() {
			//this.getAllMusic();
			const res = uni.getStorageSync("res");
			this.userId = res.data.data.userId;
			this.authorization = res.header.authorization;
			this.getMusic(this.ruk);
			//this.getMusic(this.qu);
			this.getArtcles();
			
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
				indexAuthor:null,
				indicatorColor: '#e9e9e9',
				indicatorActiveColor: 'red',
				leftRightMargin: '0rpx',
				current: 0,
				authorization: null,
				navLevel: 'nav1',
				adcode:"",
				city:" ",
				temperature:null,
				usermessage:"您有新的消息",
				activeColor: ['#ffe3cb', '#ffe3cb', '#ffe3cb', '#ffe3cb', '#1d1b1b'],
				banner: [
					"/static/banner/banner1.jpg",
					"/static/banner/banner2.jpg",
					"/static/banner/banner3.jpeg",
					"/static/banner/banner4.jpg"
				],
				nav: [{
						icon: "\u{e601}",
						text: '解压工具',
						class: "nav1-color"
					},
					{
						icon: "\u{e625}",
						text: '咨询日志',
						class: "nav2-color"
					},
					{
						icon: "\u{e63c}",
						text: '心理测试',
						class: "nav3-color"
					},
					{
						icon: "\u{e667}",
						text: '指数分析',
						class: "nav4-color"
					},
				],
				ruk: [{
						temperature:this.temperature
					}//,
					// {
					// 	albumCover: this.$basePhotoURL+"/photo/78.jpg",
					// 	title: "Told Me",
					// 	city:this.weather,
					// 	temperature:this.temperature
					// }
				],
				line: [{
						image: "/static/emotion/happy.jpg",
						textTitle: '开心',
						textSubTitle: '情绪的高地｜欢乐的源泉',
					},
					{
						image: "/static/emotion/surprise.jpg",
						textTitle: '惊喜',
						textSubTitle: '意外的收获｜愉悦的惊奇',
					},
					{
						image: "/static/emotion/neutral.jpg",
						textTitle: '平静',
						textSubTitle: '内心的宁静｜生活的沉淀',
					}
				],
				qu: [{
					albumCover:"res[i].artPic;",
					title:"res[i].artTitle;",
					hotComment:"res[i].artAuthor;"
				},{
					albumCover:"res[i].artPic;",
					title:"res[i].artTitle;",
					hotComment:"res[i].artAuthor;"
				},{
					albumCover:"res[i].artPic;",
					title:"res[i].artTitle;",
					hotComment:"res[i].artAuthor;"
				}]
			}
		},

		methods: {
			getAllMusic() {
				uni.request({
					url: this.$baseURL + '/music/getAllMusic',
					method: 'GET',
					success: (response) => {
						const res = response.data;
						uni.setStorageSync("allMusic", res);
					}
				});
			},
			changeItem(e) {
				this.current = e.detail.current
				this.navLevel = "nav" + (e.detail.current + 1)
			},
			getMusic(arr) {

				let allMusicTmp = uni.getStorageSync("allMusic");
				let allMusic = allMusicTmp.data.records;
				let randomSet = new Set();

				while (randomSet.size < arr.length) {
					let num = Math.floor(Math.random() * 140);
					randomSet.add(num);
				}
				arr.length = 0;
				let randomNum = [...randomSet];
				let tmpMusic = [];
				for (let i = 0; i < randomNum.length; i++) {
					tmpMusic[i] = allMusic[randomNum[i]]
					arr.push(tmpMusic[i]);
				}
			},
			itemClick(e) {
				let clickedMusic = [e];
				uni.navigateTo({
					url: './player/player?result=' + encodeURIComponent(JSON.stringify(clickedMusic))
				})
			},
			redirectToInput(item) {
				if (item.class == "nav1-color") {//解压工具
				uni.navigateTo({
					url: './scan/camera' 
				})
					this.Focus = true;
				} else if (item.class == "nav2-color") {//咨询日志
					uni.navigateTo({
						url: './Test/Skip' 
					})
				} else if (item.class == "nav3-color") {//心理测评
				uni.navigateTo({
					url: './Test/Skip' 
				})
				} else if (item.class == "nav4-color") {//指数分析
					uni.request({
						url: this.$baseURL+'/history/'+this.userId,
						method: "GET",
						header: {
							Authorization: this.authorization
						},
						success: res => {
							const response = res.data.data.records;
							const allMusicTmp=uni.getStorageSync("allMusic").data.records;
							let history=[]
							for(let j=0;j<response.length;j++){
								for(let i =0;i<allMusicTmp.length;i++){
									if(allMusicTmp[i].recommendId===response[j].musicId){
										history[j]=allMusicTmp[i];
										break
									}
								}
							}
							uni.navigateTo({
								url: './result/result?result=' + encodeURIComponent(JSON.stringify(history))
							})
						}
					})
				}else if(item.class == "nav5-color"){
					this.$emit('custom-event2',item);
				}
			},
			onInput(event) {
				this.searchQuery = event.target.value;
			},
			getSearchMusic() {
				const encodedQuery = encodeURIComponent(this.searchQuery);
				const url = this.$baseURL+'/music/search?pattern='+encodedQuery;
				uni.request({
					url: url,
					method: 'GET',
					success: (response) => {
						const res = response.data;
						uni.navigateTo({
							url: './result/result?result=' + encodeURIComponent(JSON.stringify(res.data
								.records))
						})
					}
				});
			},
			getLocation(){
				const url = "https://restapi.amap.com/v3/ip?key=bb9a64689b4a7dd9e7c96f7513c80a60";
				uni.request({
					url: url,
					method: 'GET',
					success: (response) => {
						const res = response.data;
						this.adcode=res.adcode;
						//console.log(res)
						this.getWeather();
					}
				});
			},
			getWeather(){
				const weatheradcode = this.adcode;
				const url = `https://restapi.amap.com/v3/weather/weatherInfo?city=${weatheradcode}&key=bb9a64689b4a7dd9e7c96f7513c80a60`;
				uni.request({
					url: url,
					method: 'GET',
					success: (response) => {
						const res = response.data;
						this.temperature=res["lives"][0]["temperature"]+"°C";
						//console.log(this.temperature);
						this.city=res["lives"][0]["city"];
					}
				});
			},
			getArtcles() {
				  //return new Promise((resolve, reject) => {
				    uni.request({
					url: this.$baseURL + '/api/v1/article/index',
					method: 'GET',
					success: (response) => {
						const res = response.data;
						//console.log(uni.getStorageSync("res"))
						for(let i=0;i<3;i++){
							this.qu[i].albumCover=res.data[i].artPic;
							this.qu[i].title=res.data[i].artTitle;
							this.qu[i].hotComment=res.data[i].artAuthor;
							uni.request({
								url:this.$baseURL+'/api/v1/expert/index/'+this.qu[i].hotComment,
								header:{Authorization:uni.getStorageSync("res").header.authorization},
								method:'POST',
								success:(response)=>{
									const res=response.data;
									this.qu[i].hotComment=res.data.exName;
								}
							})
						}		
						//setTimeout(1000)
					}
				});
				// this.getArtcleAuthors(1);
				//});
			},
			messageWindow(){
				uni.request({
					url: this.$baseURL + '/api/v1/userMessage/indexPage/user/'+this.userId,
					method: 'POST',
					data:{
						  "pageNum": 1,
						  "pageSize": 1
					},
					success: (response) => {
						responseData.sort((a, b) => a.mesTime - b.mesTime);
						const res = response.data;
						this.usermessage=res[0].mesContent;
							uni.showModal({
							title: '用户消息通知',
							content:this.usermessage,
							//content: '您的预约已成功',
							success: function(res) {
							if (res.confirm) {
								console.log('祝您预约顺利(●·◡·●)')
							} else {
								console.log('预约失败┭┮﹏┭┮')
									}
								}
							})
						}
					})
				
			

			}
			
		}
	}
</script>

<style>
	
	/* 在线链接服务仅供平台体验和调试使用，平台不承诺服务的稳定性，企业客户需下载字体包自行发布使用并做好备份。 */
	@font-face {
	  font-family: texticons;
	  font-weight: normal;
	  font-style: normal;
	  src: url('https://at.alicdn.com/t/c/font_4614114_1rc8ancrsef.ttf?t=1720595388022') format('truetype');
	}
	/* @font-face {

		font-family: texticons;
		font-weight: normal;
		font-style: normal;
		src: url('https://at.alicdn.com/t/c/font_4082971_rjo1s6t6mpj.ttf?t=1710063944182') format('truetype');
	}
	
	@font-face {

		font-family: texticons;
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
	$margin: 35rpx;
	$radius: 30rpx;
	$bg: #194f8a;

	.swiper {
		width: 100vw;
		padding: 0rpx 0 0;
		height: 466rpx;
		box-sizing: border-box;
	}

	.banner-img {
		width: 100%;
	}

	.banner-header {
		margin: 10rpx $margin 10rpx;
		flex-direction: row;
		color: #fff;
	}

	.header-title {
		font-size: 50rpx;
		padding-top: 0rpx;
	}

	.header-sub-title {
		font-size: 22rpx;
		padding-top: 40rpx;
		padding-left: 20rpx;
	}

	.search-input-wrapper {
		position: absolute;
		z-index: 10;
		top: 90rpx;
		width: 100%;
	}

	.search-input-icon {
		position: absolute;
		z-index: 10;
		left: 65rpx;
		padding-top: 134rpx;
		font-size: 40rpx;
		font-weight: normal;
		font-family: texticons;
		color: #666;
		text-align: center;
	}

	.search-input {
		background: rgba(255, 255, 255, .7);
		color: #fff;
		border-radius: 50rpx;
		width: calc(100% - 70rpx);
		height: 100rpx;
		margin: 20rpx auto;
		padding-left: 90rpx;
		box-sizing: border-box;
	}

	.uni-input-placeholder {
		color: #666;
		font-size: 28rpx;
	}

	.nav1-bg {
		background-image: linear-gradient(to bottom, #6a4860, $bg);
	}

	.nav2-bg {
		background-image: linear-gradient(to bottom, #1f4377, $bg);
	}

	.nav3-bg {
		background-image: linear-gradient(to bottom, #282433, $bg);
	}

	.nav4-bg {
		background-image: linear-gradient(to bottom, #0e0e18, $bg);
	}

	.nav-bar,
	.qu-bar,
	.line-bar {
		background-color: $bg;
	}

	.notice-bar {
		margin-top: 20rpx;
	}

	.notice-bar-wrapper {
		color: #fff;
		font-size: 28rpx;
		background: rgba(230, 230, 230, 0.15);
		border-radius: 30rpx;
		width: calc(100% - 70rpx);
		height: 65rpx;
		margin: 0rpx auto 10rpx;
		flex-direction: row;
		justify-content: center;
		align-items: center;
		padding: 20rpx 30rpx;
		box-sizing: border-box;
	}

	.notice-bar-left {
		flex-direction: row;
		align-items: center;
	}

	.notice-bar-icon {
		font-size: 30rpx;
		font-weight: normal;
		font-family: texticons;
		color: #fff;
		text-align: center;
	}

	.notice-bar-text {
		padding-left: 15rpx;
	}

	.nav-bar-wrapper {
		flex-direction: row;
		align-items: center;
		background-color: #fff;
		box-sizing: border-box;
		margin: 10rpx $margin 10rpx;
		border-radius: $radius;
		height: 170rpx;
	}

	.nav-bar-item {
		flex: 1;
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

	.nav-bar-item-text {
		text-align: center;
		font-size: 26rpx;
		color: #666;
		margin-top: 5rpx;
	}

	.nav-image-wrapper {
		//height:50%;
		flex-direction: row;
		margin: 10rpx $margin 10rpx;
	}

	.nav-image-item-view {
		flex: 1;
		margin: 0rpx 0;
		border-radius: $radius;
		width: 46%;
	}

	.nav-image-item-view+.nav-image-item-view {
		margin-left: 4%;
	}

	.nav-image-item-view {
		position: relative;
	}

	.nav-image-item {
		height: 70rpx;
		width: 100%;
		margin: 0rpx 0;
		padding: 100rpx;
		border-radius: $radius;
	}

	.nav-image-content-wrapper {
		position: absolute;
		z-index: 10;
		width: 100%;
		height: 50%;
		flex-direction: row;
		justify-content: center;
		align-items: center;
	}

	.nav-image-content {
		color: #fff;
		height: 100rpx;
		text-align: center;
		padding-top: 60rpx;
	}

	.nav-image-text {
		font-size: 50rpx;
		font-weight: bold;
	}

	.nav-image-text-sm {
		font-size: 70rpx;
	}

	.qu-bar-wrapper {
		margin: 10rpx $margin 10rpx;
		padding-top: 100rpx;
	}

	.qu-bar-item {
		border-radius: $radius;
		background-color: #fff;
		margin-bottom: 35rpx;
	}

	.qu-bar-item-image {
		width: 100%;
		height: 240rpx;
		border-top-left-radius: $radius;
		border-top-right-radius: $radius;
	}

	.bar-title {
		flex-direction: row;
		justify-content: space-between;
		align-items: center;
		margin-top: 50rpx;
		margin-bottom: 25rpx;
	}

	.bar-title-left {
		font-size: 35rpx;
		font-weight: bold;
		color: #fff;
	}

	.bar-title-right {
		font-size: 26rpx;
		font-weight: normal;
		color: #fff;
	}

	.text-title {
		font-size: 32rpx;
		font-weight: bold;
		padding-left: 30rpx;
		padding-top: 10rpx;
		box-sizing: border-box;

	}

	.text-sub-title {
		font-size: 28rpx;
		font-weight: normal;
		padding-left: 30rpx;
		padding-bottom: 10rpx;
		box-sizing: border-box;
	}

	.line-bar-wrapper {
		margin: 10rpx $margin 10rpx;
	}

	.line-bar-item-wrapper {
		flex-direction: row;
		flex-wrap: nowrap;
		justify-content: flex-start;
		overflow-x: scroll;
	}

	.line-bar-item {
		width: 500rpx;
		border-radius: $radius;
		background-color: #fff;
	}

	.line-bar-item+.line-bar-item {
		margin-left: 30rpx;
	}

	.line-bar-item-image {
		width: 100%;
		height: 260rpx;
		border-top-left-radius: $radius;
		border-top-right-radius: $radius;
	}

	.hide {
		width: 100vw;
		height: 8.5vh;
	}
</style>