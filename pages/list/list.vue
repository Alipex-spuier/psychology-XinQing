<template>
	<view class="content">


		<!-- colors：选中颜色 current：列表选择序列  左边分类数据 dataList：右边列表数据 @cateClick：左边分类点击 @itemClick：右边条目点击 -->
		<LIST colors="#fa436a" :current="currentTwo" :classList="categoryList" :dataList="dataList"
			:hideShow="true" @cateClick="cateClick" @itemClick="itemClick"></LIST>

	</view>

</template>

<script>
	import LIST from "@/utils/list.vue"
	
	export default {
		components: {
			LIST
		},
		created() {
			this.cateClick(this.categoryList[0])
		},
		data() {
			return {
				//分类列表
				categoryList: [{
					id: 1,
					name: '平静',
					emotion:'neutral'

				}, {
					id: 2,
					name: '开心',
					emotion:'happy'

				}, {
					id: 3,
					name: '惊喜',
					emotion:'surprise'

				}, {
					id: 4,
					name: '伤心',
					emotion:'sad'

				}, {
					id: 5,
					name: '难受',
					emotion:'disgust'

				}, {
					id: 6,
					name: '生气',
					emotion:'angry'

				}, {
					id: 7,
					name: '恐惧',
					emotion:'fear'

				}],
				// 选中序列
				currentTwo: 0,

				// 右边列表数据
				dataList: [],

			};
		},


		methods: {
			
			// 分类点击
			cateClick(item) {
				this.dataList.length=0;
				let allMusicTmp=uni.getStorageSync("allMusic");
				let allMusic=allMusicTmp.data.records;
				for (var i =0;i<allMusic.length;i++){
					if(allMusic[i].emotion==item.emotion){
						allMusic[i].img=allMusic[i].albumCover;
						this.dataList.push(allMusic[i]);
					}
				}
			},
			
			// 右边条目点击
			itemClick(e) {
				let clickedMusic = [e];
				uni.navigateTo({
					url: './player/player?result='+ encodeURIComponent(JSON.stringify(clickedMusic))
				})
			}
		}
	}
</script>

<style lang="scss" scoped>
	.content {
		display: flex;
		flex-direction: column;

	}
</style>