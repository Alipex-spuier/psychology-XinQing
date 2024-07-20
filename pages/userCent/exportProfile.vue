<template>
	<view class="container">
		<view class="ui-all">
			<view class="avatar"  @click="chooseImage">
				<view class="imgAvatar">
					<view class="iavatar" :style="'background: url('+avatar+') no-repeat center/cover #eeeeee;'"></view>
				</view>
				<text>修改头像</text>
			</view>
			<view class="ui-list">
				<text>用户ID</text>
				<text>{{exId}}</text>
			</view>
			<view class="ui-list">
				<text>用户名</text>
				<input type="text" :placeholder="value" :value="exname" @input="bindExname"
					placeholder-class="place" />
			</view>
			<view class="ui-list">
				<text>简介</text>
				<input type="text" :placeholder="value" :value="exbio" @input="bindExbio" placeholder-class="place" />
			</view>
			<view class="ui-list">
				<text>方向</text>
				<input type="text" :placeholder="value" :value="exdire" @input="bindExdire" placeholder-class="place" />
			</view>
			<view class="ui-list">
				<text>职称</text>
				<input type="text" :placeholder="value" :value="exqualification" @input="bindExqualification"
					placeholder-class="place" />
			</view>
			<view class="ui-list">
				<text>邮箱</text>
				<input type="text" :placeholder="value" :value="email" @input="bindEmail" placeholder-class="place" />
			</view>
			<view class="ui-list">
				<text>创建时间</text>
				<text>{{createdTime}}</text>
			</view>
			<!-- <view class="ui-list">
				<text>上次登录</text>
				<text>{{lastLogin}}</text>
			</view> -->
			<button class="save" @tap="saveChange">保 存 修 改</button>
		</view>

	</view>
</template>

<script>
	import editBtns from '@/uni_modules/lsj-edit/components/lsj-edit/edit-btns/edit-btns.vue'
	export default {
		created() {
			let expert = uni.getStorageSync("res")
			if(expert.header.authorization){
				uni.setStorageSync("authorization",expert.header.authorization)
			}
			this.authorization = uni.getStorageSync("authorization");
			this.exId = expert.data.data.exId;
			this.createdTime = expert.data.data.createdTime;
			if(expert.data.data.avatar!==undefined){
				this.avatar = expert.data.data.avatar;
			}
			this.email = expert.data.data.exEmail;
			this.exname = expert.data.data.exName;
			
			this.exbio = expert.data.data.exBio;		
			this.exdire = expert.data.data.exDire;
			this.exqualification = expert.data.data.exQualification;
		},
		data() {
			return {
				value: '请填写',
				avatar: '/static/userCent/avatar.png',
				exId: null,
				exname: null,
				edit: null,
				email: null,
				exbio: null,
				exdire: null,
				exqualification: null,
				//lastLogin: null,
				createdTime: null,
				authorization: null
			}
		},
		methods: {
			editReady(edit) {
				// 将富文本对象存放到当前页面，便于后续直接操作
				this.edit = edit;
			},
			bindExname(e) {
				this.exname = e.detail.value;
			},
			bindExbio(e) {
				this.exbio = e.detail.value;
			},
			bindExdire(e) {
				this.exdire = e.detail.value;
			},
			bindExqualification(e) {
				this.exqualification = e.detail.value;
			},
			bindEmail(e) {
				this.email = e.detail.value;
			},
			saveChange() {
				if (!this.exname.trim()) {
					return uni.showToast({
						title: '用户名不能为空',
						icon: 'none'
					});
				}
				
				if (!this.email.trim()) {
					return uni.showToast({
						title: '邮箱不能为空',
						icon: 'none'
					});
				}
				if (this.email && !/\S+@\S+\.\S+/.test(this.email)) {
					return uni.showToast({
						title: '邮箱格式不正确',
						icon: 'none'
					});
				}
				const _this = this;
				uni.request({
					url: this.$baseURL + '/api/v1/expert/update',
					method: 'PUT',
					header: {
						Authorization: _this.authorization
					},
					data: {
						exId: _this.exId,
						exBio: _this.exbio,
						exDire:_this.exdire,
						exQualification:_this.exqualification,
						avatar: _this.avatar,
						country: _this.country,
						exEmail: _this.email,
						exname: _this.exname,
						work: _this.work,
					},
					success: res => {
						if (res.data.code === 200) {
							this.getNewData();
							uni.showToast({
								title: '修改成功',
								success: function() {
									setTimeout(function() {
										uni.navigateTo({
											url: '/pages/index'
										})
									}, 2000)
								}
							});
						} else {
							uni.showToast({
								title: '修改失败：' + res.data.msg,
								icon: 'none'
							});
						}
					},
					fail: () => {
						uni.showToast({
							title: '网络请求失败',
							icon: 'none'
						});
					}
				});
			},
			getNewData() {
				const _this = this;
				uni.request({
					//todo
					url: this.$baseURL + '/api/v1/expert/index/' + _this.exId,
					method: 'POST',
					header: {
						Authorization: _this.authorization
					},
					success: res => {
						if (res.data.code == 200) {
							uni.removeStorageSync("res")
							res.header.authorization = _this.authorization
							uni.setStorageSync("res", res)
						}
					}
				})
			},
			chooseImage() {
			    uni.chooseImage({
			        success: (res) => {
			            this.chooseAvatar(res.tempFilePaths[0]);
			        }
			    });
			},
			async chooseAvatar(img) {
			    let _this = this;
			    // 将所有未上传的本地图片上传到服务器并替换到编辑器
			    const replaceImage = async (img) => {
			        // 已上传的无需再上传
			        if (img.indexOf('http') === 0) {
			            return img;
			        }
			        // 上传并替换图片
			        try {
			            const data = await new Promise((resolve, reject) => {
			                uni.uploadFile({
			                    url: _this.$baseURL + '/api/v1/file/upload',
			                    filePath: img, // 本地图片
			                    name: 'file',
			                    header: {
			                        Authorization: _this.authorization
			                    },
			                    success: (res) => {
			                        if (JSON.parse(res.data).code !== 200) {
			                            uni.showToast({
			                                title: '头像上传失败：' + JSON.parse(res.data).msg,
			                                icon: 'none'
			                            });
			                            return reject(new Error(JSON.parse(res.data).msg));
			                        }
			                        const url = _this.$basePhotoURL + "/file/" + JSON.parse(res.data).data + ".jpg";
			                        uni.removeStorageSync("exAvatar");
			                        uni.setStorageSync("exAvatar", url);
			                        resolve(url);
			                    },
			                    fail: (err) => {
			                        reject(err);
			                    }
			                });
			            });
			            return data;
			        } catch (error) {
			            return img;
			        }
			    };
			
			    // 替换图片
			    try {
			        const newAvatar = await replaceImage(img);
			        this.avatar = newAvatar;
			        this.saveImg(newAvatar);
			    } catch (error) {
			        console.error('Image upload failed:', error);
			    }
			},
			saveImg(img) {
			    uni.request({
			        url: this.$baseURL + '/api/v1/expert/update',
			        method: "PUT",
			        data: {
			            exId: this.exId,
			            avatar: img
			        },
			        header: {
			            Authorization: this.authorization
			        },
			        success: (res) => {
			            uni.showToast({
			                title: '头像保存成功',
			                icon: "success"
			            });
						console.log(res)
						uni.removeStorageSync("res")
						uni.setStorageSync("res",res)
			            setTimeout(function() {
			                uni.navigateTo({
			                    url: '/pages/index'
			                });
			            }, 2000);
			        }
			    });
			}
		}
	}
</script>

<style lang="less">
	.container {
		display: block;
	}

	.ui-all {
		padding: 20rpx 40rpx;

		.avatar {
			width: 100%;
			text-align: left;
			padding: 20rpx 0;
			border-bottom: solid 1px #f2f2f2;
			position: relative;
			
			&:active{
				opacity: 0.7;
			}

			.imgAvatar {
				width: 140rpx;
				height: 140rpx;
				border-radius: 50%;
				display: inline-block;
				vertical-align: middle;
				overflow: hidden;

				.iavatar {
					width: 100%;
					height: 100%;
					display: block;
					z-index: 999;
				}
			}

			text {
				display: inline-block;
				vertical-align: middle;
				color: #8e8e93;
				font-size: 28rpx;
				margin-left: 40rpx;
			}

			&:after {
				content: ' ';
				width: 20rpx;
				height: 20rpx;
				border-top: solid 1px #030303;
				border-right: solid 1px #030303;
				transform: rotate(45deg);
				-ms-transform: rotate(45deg);
				/* IE 9 */
				-moz-transform: rotate(45deg);
				/* Firefox */
				-webkit-transform: rotate(45deg);
				/* Safari 和 Chrome */
				-o-transform: rotate(45deg);
				position: absolute;
				top: 85rpx;
				right: 0;
			}
		}

		.ui-list {
			width: 100%;
			text-align: left;
			padding: 20rpx 0;
			border-bottom: solid 1px #f2f2f2;
			position: relative;

			text {
				color: #4a4a4a;
				font-size: 28rpx;
				display: inline-block;
				vertical-align: middle;
				min-width: 150rpx;
			}

			input {
				color: #030303;
				font-size: 30rpx;
				display: inline-block;
				vertical-align: middle;
			}

			button {
				color: #030303;
				font-size: 30rpx;
				display: inline-block;
				vertical-align: middle;
				background: none;
				margin: 0;
				padding: 0;

				&::after {
					display: none;
				}
			}

			picker {
				width: 90%;
				color: #030303;
				font-size: 30rpx;
				display: inline-block;
				vertical-align: middle;
				position: absolute;
				top: 30rpx;
				left: 150rpx;
			}

			textarea {
				color: #030303;
				font-size: 30rpx;
				vertical-align: middle;
				height: 150rpx;
				width: 100%;
				margin-top: 50rpx;
			}

			.place {
				color: #999999;
				font-size: 28rpx;
			}
		}

		.right:after {
			content: ' ';
			width: 20rpx;
			height: 20rpx;
			border-top: solid 1px #030303;
			border-right: solid 1px #030303;
			transform: rotate(45deg);
			-ms-transform: rotate(45deg);
			/* IE 9 */
			-moz-transform: rotate(45deg);
			/* Firefox */
			-webkit-transform: rotate(45deg);
			/* Safari 和 Chrome */
			-o-transform: rotate(45deg);
			position: absolute;
			top: 40rpx;
			right: 0;
		}

		.save {
			background: #030303;
			border: none;
			color: #ffffff;
			margin-top: 40rpx;
			font-size: 28rpx;
		}
	}
</style>