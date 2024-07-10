<template>
    <view class="page-main">
        <liu-clean-time ref="liuCleanTime" @submit="submit"></liu-clean-time>
    </view>
</template>

<script>
    export default {
        data() {
            return {
                dataObj: {
                    pkId: '1', //问卷ID
                    title: '抑郁指数测试', //问卷标题
                    desc: '关于抑郁症的问卷测试，得分越高，抑郁倾向性越强', //问卷描述
                    number: 3, //问卷总题目数量
                    questions: [{
                        questionId: '11',
                        questionType: 'SINGLE',
                        title: '这是一个单选题？',
                        children: [{
                            id: '111',
                            state: 0, //是否选中(0未选中；1:已选中)
                            serial: 'A', //选项序号
                            content: '单选第一个选项' //选项内容
                        }, {
                            id: '222',
                            state: 0, //是否选中(0未选中；1:已选中)
                            serial: 'B', //选项序号
                            content: '单选第二个选项' //选项内容
                        }, {
                            id: '333',
                            state: 0, //是否选中(0未选中；1:已选中)
                            serial: 'C', //选项序号
                            content: '单选第三个选项' //选项内容
                        }]
                    }, {
                        questionId: '22',
                        questionType: 'MULTY',
                        title: '这是一个多选题？',
                        children: [{
                            id: '111',
                            state: 0, //是否选中(0未选中；1:已选中)
                            serial: 'A', //选项序号
                            content: '多选第一个选项' //选项内容
                        }, {
                            id: '222',
                            state: 0, //是否选中(0未选中；1:已选中)
                            serial: 'B', //选项序号
                            content: '多选第二个选项' //选项内容
                        }, {
                            id: '333',
                            state: 0, //是否选中(0未选中；1:已选中)
                            serial: 'C', //选项序号
                            content: '多选第三个选项' //选项内容
                        }]
                    }, {
                        questionId: '33',
                        questionType: 'QUESTION',
                        title: '这个是简答题？',
                        children: []
                    }]
                },
            }
        },
        mounted() {
            this.$nextTick(res => {
                this.init()
            })
        },
        methods: {
            //问卷初始化
            init() {
                this.$refs.liuCleanTime.initObj(this.dataObj)
            },
            //提交回调
            submit(e) {
                console.log('提交回调信息：' + JSON.stringify(e))
            },
			//获取表格数据
			getTabelData(){
				     //allData为全部数据，tableData是目前表格绑定的数据
				    this.tableData = this.allData.slice(
				    (this.page - 1) * this.size,
				    this.page * this.size
				    );
				    this.total=this.allData.length

			},
			currentChange(val){
				console.log("翻页当前为第几页",val);
				this.page=val;
				this.getTabelData();
			},
			sizeChange(val){
				console.log("改变每页多少条，当前一页多少条数据",val);
				this.size=val;
				this.page=1;
				this.getTabelData();
			},
        }
    }
</script>