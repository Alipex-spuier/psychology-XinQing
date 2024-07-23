<template>
	<view>

		<!-- 文字标注 -->
		<view style="display: flex;margin: 10px 14px;">
			<view style="width: 50%;font-size: 13px;text-align: left;color: #000000;">{{'' + barTitle}}</view>
			<view style="width: 50%;font-size: 13px;text-align: right;color: #7E7E7E;">{{'' + barTime}}</view>
		</view>

		<!-- 柱形图 -->
		<view class="barBgV" :style="{'background': color}">
			<view class="barV" :style="{'width': 100*barValue/maxValue + '%',  'background': selColor}"></view>
			<view style="margin-top: 22px; margin-left: -7px;color: #555555;font-size: 13px;">{{barValue}}</view>
		</view>

		<!-- 下标 -->
		<view style="display: flex;margin:2px 10px;">
			<view style="width: 50%;font-size: 13px;text-align: left;color: #777777;">{{minValue}}</view>
			<view style="width: 50%;font-size: 13px;text-align: right;color: #777777;">{{maxValue}}</view>
		</view>

	</view>
</template>

<script>
	export default {
		props: {

			barTitle: {
				type: String,
				default: ''
			},
			barTime: {
				type: String,
				default: ''
			},
			barValue: {
				type: [String, Number],
				default: ''
			},
			color: {
				type: String,
				default: '#C0DDFA'
			},
			selColor: {
				type: String,
				default: '#1890FF'
			},
			minValue: {
				type: [String, Number],
				default: '0'
			},
			maxValue: {
				type: [String, Number],
				default: '100'
			}



		},
		data() {
			return {

			}
		},

		computed: {

		},
		methods: {



		}
	}
</script>


<style scoped>
	.barBgV {
		display: flex;
		width: calc((100vw- 26px));
		margin: 6px 13px;
		height: 16px;
	}

	.barV {

		height: 16px;
	}
</style>