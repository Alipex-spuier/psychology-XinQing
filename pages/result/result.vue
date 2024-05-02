<template>
	<view class="container" :style="{height:this.resultsArr.length>4?'100%':'100vh'}">
		<results :lists="resultsArr" @onResults="handleResultClick" />
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
		onLoad(options) {
			this.resultsArr = JSON.parse(decodeURIComponent(options.result));
			console.log(options.result)
			uni.setNavigationBarTitle({
				title:options.result.pageTitle
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
</style>