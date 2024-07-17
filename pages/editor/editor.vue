<template>
	<view>
		<lsj-edit 
		ref="lsjEdit" 
		placeholder="输入正文"
		:maxCount="10000"
		@onReady="editReady">
		    <template v-slot:btns="data">
		        <edit-btns 
		        :edit="data.edit" 
		        @click="onTabClick" 
		        @submit="save"></edit-btns>
		    </template>
		</lsj-edit>
	</view>
</template>

<script>
import editBtns from '@/uni_modules/lsj-edit/components/lsj-edit/edit-btns/edit-btns.vue';
export default {
    components:{
        // 富文本基本操作按键，可自行选择是否使用示例的按键
        editBtns
    },
	created() {
		const res = uni.getStorageSync("res")
		this.authorization = res.header.authorization;
		this.userId=res.data.data.userId;
	},
    data() {
        return {
            edit: null,
			authorization: null,
			userId:null
        }
    },
    methods: {
        // 编辑器初始化完毕，返回edit对象
        editReady(edit) {
            // 将富文本对象存放到当前页面，便于后续直接操作
            this.edit = edit;
        },
        // 演示----按钮组件点击事件
        onTabClick({name,index}) {
            switch (index){
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
        async save() {
            // 获取插入的图片列表
            let imgs = await this.edit.getImages()
            // 判断是否允许提交
            if (!this.edit.textCount && !imgs.length) {
                uni.showToast({
                    title: '请录入内容'
                });
            }
            // 将所有未上传的本地图片上传到服务器并替换到编辑器
            this.edit.replaceImage(async(img)=>{
                // 已上传的无需再上传
                // 这里没有放到edit内部去过滤，因为我觉得需不需要上传得你自己决定
                // 比如插入的base64图片是否需要上传
                // img.indexOf('http') = 0说明这个图片已经是网络地址，无需替换就直接跳过
                if(img.indexOf('http') === 0) {return img;}

                // 上传并替换图片
                //todo
                    let {data}=await uni.uploadFile({
                        url: 'http://8.217.178.86:8080/api/v1/file/upload',
                        filePath: img, //本地图片
                        name: 'file',
						header:{
							Authorization: this.authorization
						},
                        formData: {
                            'user': this.userId
                        },
						success: (res) => {
							imageURL=this.$basePhotoURL+"/"+JSON.parse(res.data).data+".jpg"
						}
                    });
					
					return imageURL;
                    
                

                // 因为演示的时候没有服务器，所以直接换个网络图方便后续演示，
                // 实际项目应使用上方注释内容
                // return 'http://8.217.178.86:8081/1721205217054.jpg'

            }).then(res=>{
				// uni.request({
				// 	url:this.$baseURL+"/api/v1/article/save",
				// 	data:{
						
				// 	}
				// })
    //             uni.navigateTo({
    //                 url: '/pages/result/articleResult?article='+encodeURIComponent(res.html)
    //             });
	console.log(res)
            }); 

        },
        // 插入话题示例
        addLink() {
            this.edit.addLink({
                prefix: '#',
                suffix: '#',
                name: '有问题欢迎加入LSJ插件交流群',
                data: {
                    name: 'QQ交流群',
                    qqGroupChatID: '667530868',
                }
            })
        },
        // @某人示例
        addLink2() {
            this.edit.addLink({
                prefix: '@',
                name: '马冬梅',
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
