<template>
	<view class="container" :style="{height:this.resultsArr.length>4?'100%':'100vh'}">
		<results :lists="resultsArr" @onResults="handleResultClick" />
		<view class="none" :style="{opacity:this.resultsArr.length>0?0:1,display:this.resultsArr.length>0?'none':'flex'}">
			<text>== 不好意思，这里好像什么都没有哦 ==</text>
		</view>
	</view>
</template>

<script>
	import results from "@/components/showResult.vue"
	export default {
		data() {
			return {
				resultsArr: []
			}
		},
		onLoad(options) {//反序列化
			this.resultsArr = JSON.parse(decodeURIComponent(options.result));//解码
			uni.setNavigationBarTitle({
				title: options.result.pageTitle
			})
		},
		components: {
			results,
		},
		methods: {
			handleResultClick(item) {
				let clickedMusic = [item];
				uni.navigateTo({
					url: '../player/player?result=' + encodeURIComponent(JSON.stringify(clickedMusic))
				})
			}
		}
	}
</script>

<style>
	.container {
		background-color: #323332;
	}

	.none {
		padding-top: 50%;
		justify-content: center;
		align-items: center;
	}

	.none text {
		font-size: 30rpx;
		font-style: italic;
		color: antiquewhite;
		font-weight: 100;
	}
</style>