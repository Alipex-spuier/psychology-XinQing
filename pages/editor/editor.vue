<template>
	<view>
		<lsj-edit ref="lsjEdit" placeholder="输入正文" :maxCount="10000" @onReady="editReady">
			<template v-slot:btns="data">
				<edit-btns :edit="data.edit" @click="onTabClick" @submit="save"></edit-btns>
			</template>
		</lsj-edit>
	</view>
</template>

<script>
	import editBtns from '@/uni_modules/lsj-edit/components/lsj-edit/edit-btns/edit-btns.vue';
	export default {
		components: {
			// 富文本基本操作按键，可自行选择是否使用示例的按键
			editBtns
		},
		created() {
			const res = uni.getStorageSync("res")
			this.authorization = res.header.authorization;
			this.userId = res.data.data.exId;
		},
		data() {
			return {
				edit: null,
				authorization: null,
				userId: null
			}
		},
		methods: {
			// 编辑器初始化完毕，返回edit对象
			editReady(edit) {
				// 将富文本对象存放到当前页面，便于后续直接操作
				this.edit = edit;
			},
			// 演示----按钮组件点击事件
			onTabClick({
				name,
				index
			}) {
				switch (index) {
					// 插入#话题#
					case 0:
						this.addLink();
						break;
						// @某人
					case 1:
						this.addLink2();
						break;
					default:
						break;
				}
			},
			// 演示发布
			// 演示发布
			async save() {
				let _this = this
				// 获取插入的图片列表
				let imgs = await this.edit.getImages();
				// 判断是否允许提交
				if (!this.edit.textCount && !imgs.length) {
					uni.showToast({
						title: '请录入内容'
					});
					return;
				}

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
									Authorization: this.authorization
								},
								formData: {
									'user': this.userId
								},
								success: (res) => {
									resolve(this.$basePhotoURL + "/" + JSON.parse(res.data).data + ".jpg");
									if (JSON.parse(res.data).code === 200) {
										
									} else {
										uni.showToast({
											title: '发布失败：' + JSON.parse(res.data).msg,
											icon: 'none'
										});
									}
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
					const res = await this.edit.replaceImage(replaceImage);
					// uni.navigateTo({
					//     url: '/pages/result/articleResult?article=' + encodeURIComponent(res.html)
					// });
					this.saveArticle(res.html)
				} catch (error) {
					console.error('Image replacement failed:', error);
				}
			},
			// 插入话题示例
			addLink() {
				this.edit.addLink({
					prefix: '#',
					suffix: '#',
					name: '标签',
					data: {
						name: '标签名',
						qqGroupChatID: '标签数字',
					}
				})
			},
			saveArticle(content) {
				uni.request({
					url: this.$baseURL + '/api/v1/article/save',
					method: "post",
					data: {
						artAuthor: this.userId,
						artTitle: "文章标题信息",
						artContent: content,
						artPic: "123123",
					},
					success: (res) => {
						setTimeout(function() {
							uni.showToast({
								title: '发布成功',
								icon:"success"
							});
						}, 2000)
						uni.navigateTo({
							url: '/pages/index'
						})
					}
				})
			},
			// @某人示例
			addLink2() {
				this.edit.addLink({
					prefix: '@',
					name: '链接',
					data: {
						userId: 10,
					}
				})
			}
		}
	}
</script>

<style>

</style>