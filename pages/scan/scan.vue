<template>
	<view class="page">
		<view style="height: 80rpx;"></view>
		<navigator class="buttons" url="./camera"><button type="primary">打开证件照采集相机</button></navigator>
		
		<canvas id="canvas-clipper" canvas-id="canvas-clipper" type="2d" :style="{width: canvasSiz.width+'px',height: canvasSiz.height+'px',position: 'absolute',left:'-500000px',top: '-500000px'}" />
	</view>
</template>

<script>
var _this;
export default {
    data() {
        return {
            windowWidth: '',
            windowHeight: '',
            imagesrc: null,
            canvasSiz: {
                width: 188,
                height: 273
            }
        };
    },
    onLoad() {
        _this = this;
        this.init();
    },
    methods: {
        // 设置图片
        setImage(e) {
            console.log(e);
            _this.zjzClipper(e.path);
        },

        // 证件照裁切
        zjzClipper(path) {
            uni.getImageInfo({
                src: path,
                success: function(image) {
                    console.log(image);
                    _this.canvasSiz.width = 188;
                    _this.canvasSiz.height = 273;

                    let ctx = uni.createCanvasContext('canvas-clipper', _this);

                    ctx.drawImage(
                        path,
                        parseInt(0.15 * image.width),
                        parseInt(0.17 * image.height),
                        parseInt(0.69 * image.width),
                        parseInt(0.65 * image.height),
                        0,
                        0,
                        188,
                        273
                    );

                    ctx.draw(false, () => {
                        uni.canvasToTempFilePath({
                            destWidth: 188,
                            destHeight: 273,
                            canvasId: 'canvas-clipper',
                            fileType: 'jpg',
                            success: function(res) {
                                _this.uploadImage(res.tempFilePath);
                            }
                        }, _this);
                    });
                }
            });
        },

        // 上传图片到服务器
        uploadImage(filePath) {
            const uploadTask = uni.uploadFile({
                url: 'http://170.106.183.24:8084/classifyImage',
                filePath: filePath,
                name: 'image',  
                formData: {
                    'user': 'test'  
                },
                success: (uploadRes) => {
                    console.log('Server response:', uploadRes.data);  
                    try {
                        const response = JSON.parse(uploadRes.data);
                        uni.showModal({
                            title: 'Upload Success',
                            content: `Emotion: ${response.emotion}, Gender: ${response.gender}`,
                            showCancel: false
                        });
                    } catch (e) {
                        console.error('Error parsing JSON:', e);
                        uni.showModal({
                            title: 'Parsing Error',
                            content: 'Failed to parse server response as JSON. Response was: ' + uploadRes.data,
                            showCancel: false
                        });
                    }
                },
                fail: (err) => {
                    console.error('Upload failed:', err);
                    uni.showModal({
                        title: 'Upload Failed',
                        content: `Error: ${err.errMsg}`,
                        showCancel: false
                    });
                }
            });
        },

        // 初始化
        init() {
            uni.getSystemInfo({
                success: function(res) {
                    _this.windowWidth = res.windowWidth;
                    _this.windowHeight = res.windowHeight;
                }
            });
        }
    }
};
</script>

<style lang="scss">
.page {
	width: 750rpx; 
	justify-content: center;
	align-items: center;
	flex-direction:column;
	display: flex;
	.buttons {
		width: 600rpx;
	}
}


</style>
