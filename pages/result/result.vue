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
		},
		components: {
			results,
		},
		methods: {
			handleResultClick(item) {
				let clickedMusic = [item];
				console.log(clickedMusic)
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