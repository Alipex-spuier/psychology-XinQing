<template>
	<view class="container">
		<!-- 默认用法 直接传入md文本即可-->
	<zero-markdown-view :markdown="content"></zero-markdown-view>
	</view>
</template>
<script>
	
	export default {
		data() {
			return {
				content: ""
			}
		},
		created() {
		},
		onLoad(options) {//反序列化
			// let markdownContent = JSON.parse(decodeURI(options.article))[0].Content;//解码
			let markdownContent = decodeURIComponent(options.article);//解码
			this.content=this.replaceSpacesOutsideTags(markdownContent)
		},
		computed: {
			// 流式输出时代码块处理 , 这时候请使用 msgContent 传入组件中
			msgContent() {
				if (!this.content) {
					return 
				}
				let htmlString = ''
				// 判断markdown中代码块标识符的数量是否为偶数
				if (this.content.split("```").length % 2) {
					let content = this.content
					if (content[content.length - 1] != '\n') {
						content += '\n'
					}
					htmlString = content
				} else {
					htmlString = this.content
				}
				return htmlString
			}
		},
		methods: {
			replaceSpacesOutsideTags(str) {
			    // 正则表达式用于匹配所有HTML标签
			    const tagRegExp = /(<[^>]+>)/g;
			    // 将字符串拆分成标签和文本部分
			    const parts = str.split(tagRegExp);
			    
			    // 遍历每一部分，如果不是标签就替换空格为&nbsp;
			    for (let i = 0; i < parts.length; i++) {
			        if (!tagRegExp.test(parts[i])) {
			            parts[i] = parts[i].replace(/ /g, '&nbsp;');
			        }
			    }
			    
			    // 重新组合所有部分
			    return parts.join('');
			}
		},
	}
</script>
	<style lang="scss" scoped>
	</style>