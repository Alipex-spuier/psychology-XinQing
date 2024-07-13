<style scoped>
.tabbarMain{
	height: 188upx;
	position: fixed;
	z-index: 50;
	width: 750upx;
	flex: 1;
	bottom: 0;
}
.tabbarMainIphone{
	height: 256upx;
	padding-bottom: 64upx;
}
.tabBtnMina{
	width: 750upx;
	height: 88upx;
	position: absolute;
	z-index:2;
	bottom:0;
	flex-direction: row;
	/* #ifndef */
	padding-bottom: constant(safe-area-inset-top);
	padding-bottom: env(safe-area-inset-top);
	/* #endif */
	display: flex;
}
.tabBtnMinaIphone{
	height: 156upx;
	padding-bottom: 64upx;
}
.imgse{
	width: 48upx;
	height: 48upx;
}
.txtBtn{
	font-size: 24upx;
	color: #666666;
}
.txtBtnSel{
	font-size: 24upx;
	color: #333333;
}
.itmMain{
	/* width: 250upx; */
	width: 150upx;
	justify-content: center;
	align-items: center;
	display: flex;
	flex-direction: column;
}
.imgseTs{
	width: 150upx;
	height: 140upx;
	margin-bottom: 90upx;
	position: relative;
	z-index:1;
	bottom: 0upx;
}

.btnfixMin{
	width: 750upx;
	height: 178upx;
	position: absolute;
	z-index:1;
	bottom:0;
}
.btnfixMinIphone{
	bottom:64upx;
}
.fixTpm{
	width: 150upx;
	height: 140upx;
	flex-direction: row;
	justify-content: center;
	align-items: center;
	position: relative;
	z-index:0;
}
.imgseTsAnds{
	width: 150upx;
	height: 150upx;
	position: fixed;
	z-index:1;
	bottom: 10upx;
	left: 50%;
	border-radius: 150upx;
	transform: translateX(-50%);
}
</style>


<!-- 
    此版本需要注意
		1、如果此项目有地图模块功能的话   整个底部建议使用<cover-view><cover-image>进行标签改版      或者判断如果当前点击等于地图模块功能时候，却换成<cover-view><cover-image>进行原生渲染
 -->
 
<template>
	
	<view class="tabbarMain" :class="[flagTypeInfo? 'tabbarMainIphone':'']">
		<image src="/static/tabbar/nav_btn.png" :fade-show='false' class="btnfixMin"  :class="[flagTypeInfo? 'btnfixMinIphone':'']" mode=""></image>
		<view class="tabBtnMina"  :class="[flagTypeInfo? 'tabBtnMinaIphone':'']" mode="">
			<view class="itmMain" @click.stop="switchTabFunc(0, '/pages/home')">
				<image class="imgse" :fade-show='false' v-if="active!=0" src="/static/tabbar/home.png"></image>
				<image class="imgse" :fade-show='false' v-if="active===0" src="/static/tabbar/home_active.png"></image>
				<text class="txtBtn" :class="{'txtBtnSel' :active==0}">首页</text>
			</view>
			<view class="itmMain" @click.stop="switchTabFunc(1, '/pages/news')">
				<image class="imgse" :fade-show='false' v-if="active!=1" src="/static/tabbar/type.png"></image>
				<image class="imgse" :fade-show='false' v-if="active===1" src="/static/tabbar/type_active.png"></image>
				<text class="txtBtn" :class="{'txtBtnSel' :active==1}">资讯广场</text>
			</view>
			<view class="itmMain">
				<!-- <navigator url="../pages/appointment/selectExport"> -->
				<view class="fixTpm" @click="toselect">
					<view class="imgseTsAnds" ref='leftWidth' :style="'left:'+leftWidth+'px'"></view>
				</view>
				</navigator>
			</view>
			<view class="itmMain" @click.stop="switchTabFunc(3, '/pages/guide')">
				<image class="imgse" :fade-show='false' v-if="active!=3" src="/static/tabbar/guide.png"></image>
				<image class="imgse" :fade-show='false' v-if="active===3" src="/static/tabbar/guide_active.png"></image>
				<text class="txtBtn" :class="{'txtBtnSel' :active==3}">实时咨询</text>
			</view>
			<view class="itmMain" @click.stop="switchTabFunc(4, '/pages/userCent')">
				<image class="imgse" :fade-show='false' v-if="active!=4" src="/static/tabbar/my.png"></image>
				<image class="imgse" :fade-show='false' v-if="active===4" src="/static/tabbar/my_active.png"></image>
				<text class="txtBtn" :class="{'txtBtnSel' :active==4}">个人中心</text>
			</view>
		</view>
		<canvas id="canvas-clipper" canvas-id="canvas-clipper" type="2d" :style="{width: canvasSiz.width+'px',height: canvasSiz.height+'px',position: 'absolute',left:'-500000px',top: '-500000px'}" />
	</view>
</template>

<script>
var _this;
export default{
	data(){
		return{
			active: Number, 
			platforms: '', 
			leftWidth: 0,
			flagTypeInfo: false,
			windowWidth: '',
			windowHeight: '',
			imagesrc: null,
			authorization:null,
			canvasSiz: {
			    width: 188,
			    height: 273
			},
			selectres:[],
			
		}
	},
	onLoad() {
	    _this = this;
	    this.init();
	},
	mounted() {
		this.flagTypeInfo = this.$is_bang;
		let tempSystem = uni.getSystemInfoSync()
		this.platforms = tempSystem.platform
		
		this.$nextTick(()=>{
			// #ifdef APP-NVUE || APP-PLUS || APP-PLUS-NVUE ||APP-VUE
			if(this.platforms == 'android'){
				let temp = this.$refs.leftWidth
				this.leftWidth = tempSystem.screenWidth / 2
				
			}
			// #endif
			this.active = this.actives
		})
	},
	props:{
		actives:{
			type: Number,
			default: 0
		}
	},
	inject:['_mainFuncInit'], // 父级传递过来的函数
	methods:{
		/**
		 * @name 初始化当前点击索引
		 * 解决初始化闪烁图标问题
		 * */ 
		getSetting(numbers){
			this.active = numbers || 0
		},
		/**
		 * @name tab点击事件
		 * @param {number} index 当前点击的索引
		 * @param {string} Urls 当前点击带进来的链接
		 * */ 
		switchTabFunc(index, Urls) {
				uni.setStorageSync('setStatusIndexFunc', index)
				this._mainFuncInit() // 回调父级函数
				this.active = index				
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
		            console.log('Server response:', uploadRes.data);  
		            try {
		                const response = JSON.parse(uploadRes.data);
		            } catch (e) {
		                console.error('Error parsing JSON:', e);
		            }
		        },
		        fail: (err) => {
		            console.error('Upload failed:', err);
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
		toselect(){
			let _this= this;
			const res=uni.getStorageSync("res")
			_this.authorization=res.header.authorization;
			uni.request({
				url: this.$baseURL + '/api/v1/expert/index',
				method: 'GET',
				header: {
					Authorization: _this.authorization
				},
				success: (response) => {
					const res = response.data;
					this.selectres=res;
					uni.navigateTo({
						url: '../pages/appointment/selectExport?result=' + encodeURIComponent(JSON.stringify(this.selectres))
					})
					// console.log(res)
				}
			});
		}
	}
}
</script>
