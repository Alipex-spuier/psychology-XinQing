<template>
	<view>
		<view class="results--box">
			<view class="results--item" v-for="(item, index) in lists" :key="index" @click="$emit('onResults', item)">
				<!-- <view class="item--cover" :style="{backgroundImage: 'url(' + item.albumCover + ')'}" /> -->
				<view class="item--cover" :style="{backgroundImage: 'url(' + item.albumCover + ')'}" />
				<view class="item--content">
					<view class="author">{{item.exName || 'unknownexport'}}</view>
					<view class="title">———{{item.exEmail|| 'unknowntitle'}}</view>
					<!-- <view class="author"> —— {{item.exName || 'unknownexport'}}</view> -->
				</view>
			</view>
		</view>
	</view>
</template>
<script>
	export default{
		props:{
			lists: {
				type	: Array,
				required: true,
				default : () => {
					return [ ]
				}
			}
		},
		mounted() {
		}
	}
</script>
<style lang="scss" scoped>
	$padding: 30rpx;
	$margin: 10rpx;
	$radius: 20rpx;
	
	.ellipsis-1 {
	    max-width: 100%;
	    overflow: hidden;
	    white-space: nowrap;
	    text-overflow: ellipsis;
	}
	
	.ellipsis-2{
		max-width: 100%;
		display: -webkit-box;
		overflow: hidden;
		text-overflow: ellipsis;
		-webkit-box-orient: vertical;
		-webkit-line-clamp: 2;
	}
	.results--box{
		display: flex;
		flex-wrap: wrap;
		padding: $padding/3;
		.results--item{
			background: white;
			width: calc(50% - #{$margin*2});
			background: white;
			border-radius: $radius;
			overflow: hidden;
			margin: $margin;
			.item--cover{
				width: 100%;
				padding-top: 100%;
				background-position: center;
				background-size: cover;
			}
			.item--content{
				padding: $padding - 10;
				& > .title{
					padding-left: 0;
					font-size: 30rpx;
					font-weight: bold;
					line-height: 60rpx;
					height: 80rpx;
					color: #333;
					@extend .ellipsis-2;
				}
				& > .author{
					padding-top: $padding/2;
					line-height: 40rpx;
					color: #999;
					font-size: 28rpx;
					padding-left: 40rpx;
					@extend .ellipsis-1;
				}
			}
		}
	}
</style>
