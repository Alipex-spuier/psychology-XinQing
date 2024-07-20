<template>
    <view class="page-main">
        <liu-clean-time ref="liuCleanTime" @submit="submit"></liu-clean-time>
    </view>
</template>

<script>
    export default {
        data() {
            return {
				testNumber:1,
                dataObj: {
                    pkId: '1', //问卷ID
                    title: '抑郁指数测试', //问卷标题
                    desc: '关于抑郁症的问卷测试，得分越高，抑郁倾向性越强', //问卷描述
                    number:10, //问卷总题目数量
                    questions: [{
                        questionId: 11,
                        questionType: 'SINGLE',
                        title: '这是一个单选题？',
						score:10,
						answer:'A',
                        children: [{
                            id: 'A',
                            state: 0, //是否选中(0未选中；1:已选中)
                            //serial: 'A', //选项序号
                            content: '单选第一个选项' ,//选项内容
							proportion:0
                        }, {
                            id: 'B',
                            state: 0, //是否选中(0未选中；1:已选中)
                            //serial: 'B', //选项序号
                            content: '单选第二个选项', //选项内容
							proportion:1
                        }, {
                            id: 'C',
                            state: 0, //是否选中(0未选中；1:已选中)
                            //serial: 'C', //选项序号
                            content: '单选第三个选项' ,//选项内容
							proportion:2
                        },{
                            id: 'D',
                            state: 0, //是否选中(0未选中；1:已选中)
                            //serial: 'C', //选项序号
                            content: '单选第四个选项' ,//选项内容
							proportion:3
                        }]
                    },
					]
                },
            }
        },
		onLoad(options) {//反序列化
			this.testNumber= JSON.parse(decodeURIComponent(options.testNumber));//解码
			// uni.setNavigationBarTitle({
			// 	title: options.result.pageTitle
			// })
			this.LoadQuestion();
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
			LoadQuestion(){
				uni.request({
					url: this.$baseURL + '/api/v1/test/indexPageById',
					method: 'POST',
					data:{
						pageNum: 1,
						pageSize: 20,
						param: {  
						    belongingId:this.testNumber
						  }
					},

					success: (response) => {
						//if(!res.data){ console.log("No data received or data format is incorrect");}
						const res = response.data;
						for(let i=0;i<res.data.length;i++){
							 this.dataObj.questions[i].questionId=res.data[i].testId;
							 this.dataObj.questions[i].questionType='SINGLE';
							 this.dataObj.questions[i].title=res.data[i].testContent;
							 this.dataObj.questions[i].score=res.data[i].testScore;
							 this.dataObj.questions[i].children[0].content=res.data[i].testChoA;
							 this.dataObj.questions[i].children[0].proportion=res.data[i].testChoA;
							 this.dataObj.questions[i].children[1].content=res.data[i].testChoB;
							 this.dataObj.questions[i].children[0].proportion=res.data[i].testChoA;
							 if(res.data[i].testChoD!=='N'){this.dataObj.questions[i].children[3].content=res.data[i].testChoD;}
							 if(res.data[i].testChoC!=='N'){this.dataObj.questions[i].children[2].content=res.data[i].testChoC;}
							 this.dataObj.questions[i].score=res.data[i].testScore;
							 //this.questions[i].answer=res.data[i].testAnswer;
						}
					},
					fail : (response) => {
						console.log("fail")
					}
				});
				
			}
			
			
        }
    }
</script>