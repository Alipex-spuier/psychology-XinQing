<template>
    <view class="content">

        <!-- colors：选中颜色 current：列表选择序列  左边分类数据 dataList：右边列表数据 @cateClick：左边分类点击 @itemClick：右边条目点击 -->
        <cc-twoCate colors="#fa436a" :current="currentTwo" :classList="categoryList" :dataList="dataList"
            :hideShow="true" @cateClick="cateClick" @itemClick="itemClick"></cc-twoCate>

    </view>

</template>

<script>
    export default {
        components: {
			
        },
        data() {
            return {

                //分类列表
                categoryList: [{
                    id: 1,
                    name: '开心',

                }, {
                    id: 2,
                    name: '伤心',

                }, {
                    id: 3,
                    name: '恐惧',

                }, {
                    id: 4,
                    name: '惊喜',

                }],
                // 选中序列
                currentTwo: 0,

                // 右边列表数据
                dataList: [{
                        title: '音乐1',
                        goods_id: 201,
                        number: 1,
                        img: 'http://170.106.183.24:8081/photo/1.jpg',
                        status: 1, //商品过期状态  0正常  1已失效
                        stock: 600,

                    },
                    {
                        title: '音乐2',
                        goods_id: 202,
                        number: 75,
                        img: 'http://170.106.183.24:8081/photo/2.jpg',
                        status: 0, //商品过期状态  0正常  1已失效
                        stock: 100,
                    },
                    {
                        title: '音乐3',
                        type: 3,
                        goods_id: 203,
                        number: 1,
                        img: 'http://170.106.183.24:8081/photo/3.jpg',
                        status: 0, //商品过期状态  0正常  1已失效
                        stock: 200,
                    }
                ],

            };
        },

        methods: {

            cateClick(item) {

                uni.showModal({
                    title: "点击分类条目",
                    content: '点击分类条目 = ' + JSON.stringify(item)
                })

            },

            itemClick(e) {

                uni.showModal({
                    title: "点击右边商品条目",
                    content: '点击右边商品条目 = ' + JSON.stringify(e)
                })
            }

        }
    }
</script>

<style lang="scss" scoped>
    .content {
        display: flex;
        flex-direction: column;

    }
</style>