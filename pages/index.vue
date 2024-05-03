<template>
	<view class="mainVieW">
		<!-- 主站 -->
		<HOMES ref='Home' v-if='active==0' @custom-event2="redirectToInput"></HOMES>
		<LIST ref='list' v-if='active==1'></LIST>
		<GUIDE ref='fav' v-if='active==3'></GUIDE>
		<USERCENTER ref='userCent' v-if='active==4'></USERCENTER>

		<tab-bar class="fixs" ref='commentTabbat' :actives='active'></tab-bar>
	</view>
</template>
<script>
	import HOMES from './home/home.vue'
	import LIST from './list/list.vue'
	import GUIDE from './guide/guide.vue'
	import USERCENTER from './userCent/userCent.vue'
	import tabBar from '../components/tabbar.vue'
	var _this;
	export default {
		name: 'mains',
		data() {
			return {
				active: Number || 0,
				windowWidth: '',
				windowHeight: '',
				imagesrc: null,
				backButtonPress:0,
				canvasSiz: {
					width: 188,
					height: 273
				}
			}
		},
		components: {
			HOMES,
			LIST,
			GUIDE,
			USERCENTER,
			tabBar
		},
		created() {
			this.getAllMusic();
		},
		onLoad() {
			_this = this;
			this.init();
			let temp = uni.getStorageSync('setStatusIndexFunc') || 0
			uni.setStorageSync('setStatusIndexFunc', temp)
			this.active = temp
			this.$nextTick(() => {
				this.$refs.commentTabbat.getSetting(temp)
				this._initData();
			})
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
		provide() {
			return {
				_mainFuncInit: this._mainFuncInit
			}
		},
		methods: {
			_mainFuncInit() {
				this.active = uni.getStorageSync('setStatusIndexFunc') || 0
				if (this.tempActive == this.active && this.active != 4) {
					return
				}
				this.$nextTick(() => {
					switch (this.active) {
						case 0:
							uni.setStorageSync('setStatusIndexFunc', 0)
							break;
						case 1:
							uni.setStorageSync('setStatusIndexFunc', 1)
							break;
						case 3:
							uni.setStorageSync('setStatusIndexFunc', 3)
							break;
						case 4:
							uni.setStorageSync('setStatusIndexFunc', 4)
							break;
					}
				})
				this.tempActive = this.active
			},
			_initData() {
				this._mainFuncInit();
			},
			// 设置图片
			setImage(e) {
				_this.zjzClipper(e.path);
			},

			// 证件照裁切
			zjzClipper(path) {
				uni.getImageInfo({
					src: path,
					success: function(image) {
						_this.canvasSiz.width = 188;
						_this.canvasSiz.height = 273;

						let ctx = uni.createCanvasContext('canvas-clipper', _this);

						ctx.drawImage(
							path,
							parseInt(0.15 * image.width),
							parseInt(0.17 * image.height),
							parseInt(0.69 * image.width),
							parseInt(0.65 * image.height),
							0,
							0,
							188,
							273
						);

						ctx.draw(false, () => {
							uni.canvasToTempFilePath({
								destWidth: 188,
								destHeight: 273,
								canvasId: 'canvas-clipper',
								fileType: 'jpg',
								success: function(res) {
									_this.uploadImage(res.tempFilePath);
								}
							}, _this);
						});
					}
				});
			},

			// 上传图片到服务器
			uploadImage(filePath) {
				const uploadTask = uni.uploadFile({
					url: this.$baseFaceURL+'/classifyImage',
					filePath: filePath,
					name: 'image',
					formData: {
						'user': 'test'
					},
					success: (uploadRes) => {
						try {
							const response = JSON.parse(uploadRes.data);
							let gender = "male";
							if (response.gender == "man") {
								gender = "male"
							} else if (response.gender == "woman") {
								gender = "female"
							}
							this.sortMusic(response.emotion, gender);
						} catch (e) {
							console.error('Error parsing JSON:', e);
							uni.showModal({
								title: 'Parsing Error',
								content: 'Failed to parse server response as JSON. Response was: ' +
									uploadRes.data,
								showCancel: false
							});
						}
					},
					fail: (err) => {
						console.error('Upload failed:', err);
						uni.showModal({
							title: 'Upload Failed',
							content: `Error: ${err.errMsg}`,
							showCancel: false
						});
					}
				});
			},

			// 初始化
			init() {
				uni.getSystemInfo({
					success: function(res) {
						_this.windowWidth = res.windowWidth;
						_this.windowHeight = res.windowHeight;
					}
				});
			},
			sortMusic(emotion, gender) {
				let allMusicTmp = uni.getStorageSync("allMusic");
				let allMusic = allMusicTmp.data.records;
				let sortedMusic = [];
				let emotionPart = 0.3;
				let genderPart = 0.7;
				let time = this.getTime();
				if (time === "bad" && emotion !== "surprise" && emotion !== "happy") {
					emotionPart = 0.7;
					genderPart = 0.3;
				}
				let caculatedScore = null;
				//排序
				for (var i = 0; i < allMusic.length; i++) {
					if (allMusic[i].emotion == emotion && allMusic[i].gender == gender) {
						allMusic[i].caculatedScore = (allMusic[i].emotionRecommendScore * emotionPart + allMusic[i]
							.genderRecommendScore * genderPart) / (genderPart + emotionPart);
						sortedMusic.push(allMusic[i]);
					}
				}
				sortedMusic.sort(function(a, b) {
					if (a.caculatedScore === b.caculatedScore) { //降序
						return b.recommendId - a.recommendId
					} else {
						return b.caculatedScore - a.caculatedScore
					}
				})
				const sortedMusicSent = JSON.stringify(sortedMusic)
				uni.navigateTo({
					url: './player/player?result=' + encodeURIComponent(sortedMusicSent)
				})
			},
			getTime() {
				const now = new Date();
				const hours = now.getHours();

				if (hours >= 0 && hours < 7) {
					return "bad"
				}
			},
			getAllMusic() {
				uni.request({
					url: this.$baseURL+'/music/getAllMusic',
					method: 'GET',
					success: (response) => {
						const res = response.data;
						uni.setStorageSync("allMusic", res);
					}
				});
			},
			redirectToInput(data){
				if(data.class=="nav2-color"){
					let tmp=uni.setStorageSync('setStatusIndexFunc', 1)
					this.active = tmp
					this.tempActive=tmp
					this.$nextTick(() => {
						this.$refs.commentTabbat.getSetting(tmp)
						this._initData();
					})
				}else if(data.class=="nav5-color"){
					let tmp=uni.setStorageSync('setStatusIndexFunc', 3)
					this.active = tmp
					this.tempActive=tmp
					this.$nextTick(() => {
						this.$refs.commentTabbat.getSetting(tmp)
						this._initData();
					})
				}
			}
		}
	}
</script>
<style scoped>
	.fixs {
		border: 0;
		margin: 0;
		padding: 0;
		display: flex;
		flex-direction: column;
		position: fixed;
		z-index: 1000;
	}
</style>