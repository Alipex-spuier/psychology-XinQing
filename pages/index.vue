<template>
	<view class="mainVieW">
		<!-- 主站 -->
		<HOMES ref='Home' v-if='active==0'></HOMES> 
		<LIST ref='list' v-if='active==1'></LIST>
		<SCAN ref='SCAN' v-if='active==2'></SCAN>
		<FAVORITE ref='fav' v-if='active==3'></FAVORITE>
		<USERCENTER ref='userCent' v-if='active==4'></USERCENTER>
		
		<tab-bar class="fixs" ref='commentTabbat' :actives='active'></tab-bar>
	</view>
</template>
<script>
	
import HOMES from './home/home.vue'
import LIST from './list/list.vue'
import SCAN from './scan/scan.vue'
import FAVORITE from './favorite/favorite.vue'
import USERCENTER from './userCent/userCent.vue'

/**
 * @name 次功能适用于APP  H5  小程序
 * 小程序不需要 import 导入 tabbar
 * APP 端如需使用自定义tabbar  需要单独 import 导包形式 进行components 注入组件 
 * 该组件样式BUG问题已全部修复可以直接使用
 * */ 
 
import tabBar from '../components/tabbar.vue'

export default{
	name:'mains',
	data(){
		return{
			active: Number || 0,
		}
	},
	components:{
		HOMES,
		LIST,
		SCAN,
		FAVORITE,
		USERCENTER,
		tabBar
	},
	onLoad() {
		let temp = uni.getStorageSync('setStatusIndexFunc') || 0
		uni.setStorageSync('setStatusIndexFunc', temp)
		this.active = temp
		this.$nextTick(()=>{
			this.$refs.commentTabbat.getSetting(temp)
			this._initData();
		})
	},
	provide(){
		return {
			_mainFuncInit: this._mainFuncInit
		}
	},
	methods:{
		/**
		 * @name 公共点击函数
		 * */
		_mainFuncInit(){
			this.active = uni.getStorageSync('setStatusIndexFunc') || 0
			console.log(this.active)
			if(this.tempActive == this.active && this.active != 4){
				return
			}
			this.$nextTick(()=>{
				switch(this.active){
					case 0: 
						uni.setStorageSync('setStatusIndexFunc', 0)
						break;
					case 1: 
						uni.setStorageSync('setStatusIndexFunc', 1)
						break;
					case 2: 
						uni.setStorageSync('setStatusIndexFunc', 2)
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
		/**
		 * @name 首页数据初始化 
		 * */
		_initData(){
			this._mainFuncInit();
		},
	}
}
</script>
<style scoped>
.fixs{
	position: fixed;
	z-index:100;
}
</style>
