<template>
    <view class="page-main">
        <liu-clean-time ref="liuCleanTime" @submit="submit"></liu-clean-time>
    </view>
</template>

<script>
    export default {
        data() {
            return {
                testNumber: 1,
                dataObj: {
                    pkId: '1', //问卷ID
                    title: '抑郁指数测试', //问卷标题
                    desc: '关于抑郁症的问卷测试，得分越高，抑郁倾向性越强', //问卷描述
                    number: null, //问卷总题目数量
                    questions: []
                }
            }
        },
        onLoad(options) { //反序列化
            this.testNumber = JSON.parse(decodeURIComponent(options.testNumber)); //解码
            this.LoadQuestion();
        },
        methods: {
            //问卷初始化
            init() {
                this.$refs.liuCleanTime.initObj(this.dataObj);
            },
            //提交回调
            submit(e) {
                console.log('提交回调信息：' + JSON.stringify(e));
            },
            //获取表格数据
            getTabelData() {
                this.tableData = this.allData.slice(
                    (this.page - 1) * this.size,
                    this.page * this.size
                );
                this.total = this.allData.length;
            },
            currentChange(val) {
                console.log("翻页当前为第几页", val);
                this.page = val;
                this.getTabelData();
            },
            sizeChange(val) {
                console.log("改变每页多少条，当前一页多少条数据", val);
                this.size = val;
                this.page = 1;
                this.getTabelData();
            },
            LoadQuestion() {
                const _this = this;
                uni.request({
                    url: this.$baseURL + '/api/v1/test/indexPageById',
                    method: 'POST',
                    data: {
                        pageNum: 1,
                        pageSize: 5,
                        param: {
                            belongingId: this.testNumber
                        }
                    },
                    success: (response) => {
                        const res = response.data;
                        const questions = [];
                        res.data.forEach((item) => {
                            let tmp = {
                                questionId: item.testId,
                                questionType: 'SINGLE',
                                title: item.testContent,
                                score: item.testScore,
                                children: []
                            };
                            tmp.children.push({
                                id: 'A',
                                state: 0,
                                content: item.testChoA,
                                proportion: item.testProportionA
                            });
                            tmp.children.push({
                                id: 'B',
                                state: 0,
                                content: item.testChoB,
                                proportion: item.testProportionB
                            });
                            if (item.testChoC !== 'N') {
                                tmp.children.push({
                                    id: 'C',
                                    state: 0,
                                    content: item.testChoC,
                                    proportion: item.testProportionC
                                });
                            }
                            if (item.testChoD !== 'N') {
                                tmp.children.push({
                                    id: 'D',
                                    state: 0,
                                    content: item.testChoD,
                                    proportion: item.testProportionD
                                });
                            }
                            questions.push(tmp);
                        });
                        _this.dataObj.questions = questions;
						_this.dataObj.number=_this.dataObj.questions.length
						_this.init();
                    },
                    fail: (response) => {
                        console.log("fail");
                    }
                });
            }
        }
    }
</script>