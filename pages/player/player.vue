<template>
<view class="container">
	<view class="card" :style="{ filter: `blur(${blurValue}px)` }">
	  <img class="bg_img" mode="widthFix" :src="cover" alt="">
	  <view class="filter_blur"></view>
	  <view class="content">
	    <view class="cover_box">
	      <view class="player">
	        <image :src="isPlayer ? '../../static/pause.png' : '../../static/Player_Play.png'"
	               :style="isPlayer ? 'width: 60rpx; height: 60rpx;' : 'width: 100rpx; height: 100rpx;'"
	               @click="togglePlay"></image>
	      </view>
	      <img class="cover" mode="widthFix" :src="cover" alt="">
	    </view>
	    <view class="comment">{{ comment }}</view>
	    <view class="author">——{{ user }}</view>
	    <view class="author">《{{ songs }}》{{ sings }}</view>
	    <view class="change" @click="change">
	      <image src='../../static/change.png' style='width: 50rpx;height: 50rpx;display:inline-block;' />
	    </view>
	  </view>
	</view>
	<text class="loading-text" :style="{ opacity: opacityValue }">{{ load }}%</text>
	<text class="logo-text">清 · 听 —— 听你想听</text>
</view>
</template>

<script>
export default {
  data() {
    return {
      isPlayer: true,
      cover: "",
      comment: "",
      user: "",
      songs: "",
      sings: "",
	  sortedMusic:[],
	  singNum:0,
	  load: 0,
	  interval: null
    };
  },
  onLoad(options){
	  this.sortedMusic=JSON.parse(decodeURIComponent(options.sortedMusic));
  },
  onUnload() {
  	this.innerAudioContext.stop();
  },
  onHide() {
  	this.innerAudioContext.stop();
  },
  onBackPress(){
	  this.innerAudioContext.stop();
  },
  computed: {
    blurValue() {
      return this.scale(this.load, 0, 100, 30, 0);
    },
    opacityValue() {
      return this.scale(this.load, 0, 100, 1, 0);
    }
  },
  methods: {
    togglePlay() {
      this.isPlayer = !this.isPlayer;
      this.isPlayer ? this.innerAudioContext.play() : this.innerAudioContext.stop();
    },
    change() {
		if (this.singNum < this.sortedMusic.length) {
		uni.showLoading({ title: '加载中' });
		this.cover = this.sortedMusic[this.singNum].albumCover;
		this.comment = this.sortedMusic[this.singNum].hotComment;
		this.user = this.sortedMusic[this.singNum].commentAuthor;
		this.songs = this.sortedMusic[this.singNum].title;
		this.sings = this.sortedMusic[this.singNum].author;
		this.innerAudioContext.src = this.sortedMusic[this.singNum].url;
		this.innerAudioContext.autoplay = true;
		this.isPlayer = true;
		uni.hideLoading();
		this.singNum++
		}else{
			uni.showToast({
				title:"没有更多歌曲了",
				icon:"fail"
			})
		}
    },
	scale(num, in_min, in_max, out_min, out_max) {
	  return ((num - in_min) * (out_max - out_min)) / (in_max - in_min) + out_min;
	},
	blurring() {
	  this.load++;
	
	  if (this.load > 99) {
	    clearInterval(this.interval);
	  }
	}
  },
  mounted() {
    this.interval = setInterval(this.blurring, 35);
  },
  beforeDestroy() {
    clearInterval(this.interval);
  },
  created() {
    this.innerAudioContext = uni.createInnerAudioContext();
    this.change();
  }
}
</script>

<style>

	.card {
		width: 600rpx;
		min-height: 900rpx;
		margin: 30rpx auto;
		border-radius: 30rpx;
		overflow: hidden;
		position: relative;
		display: flex;
		flex-direction: column;
		justify-content: flex-end;
	}

	.filter_blur {
		width: 100%;
		height: 100%;
		position: absolute;
		top: 0;
		left: 0;
		background: rgba(180, 181, 183, 0.2);
		backdrop-filter: blur(60rpx);
		overflow: hidden;
		border-radius: 30rpx;
	}

	.bg_img {
		width: 100%;
		flex-shrink: 0;
		position: absolute;
		bottom: 0;
	}

	.content {
		z-index: 99;
		overflow: hidden;
		border-radius: 30rpx;
		position: relative;
	}

	.cover_box {
		position: relative;
	}

	.player {
		position: absolute;
		top: 50%;
		left: 50%;
		width: 150rpx;
		height: 150rpx;
		margin-left: -75rpx;
		margin-top: -75rpx;
		background: rgba(176, 176, 176, 0.2);
		backdrop-filter: blur(10rpx);
		border-radius: 50%;
		display: flex;
		justify-content: center;
		align-items: center;
	}

	.cover {
		width: 100%;
	}

	.comment {
		padding: 30rpx;
		font-size: 35rpx;
		color: #FFF
	}

	.author {
		text-align: right;
		padding: 0 30rpx 30rpx;
		font-size: 30rpx;
		color: #FFF
	}

	.change {
		width: 50rpx;
		height: 50rpx;
		position: absolute;
		left: 20rpx;
		bottom: 20rpx;
	}

	.change img {
		width: 100%;
		height: 100%;
	}	
	.loading-text{
		font-size:251rpx;
		display: flex;
		align-items: center;
		justify-content: center;
		color: #F7BA0B
	}
	.logo-text{
		font-size:50rpx;
		display: flex;
		align-items: center;
		justify-content: center;
		color: #999
	}
</style>