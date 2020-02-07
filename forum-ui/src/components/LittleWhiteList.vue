<template>
    <a-list itemLayout="vertical" size="large" :pagination="pagination" :dataSource="data">
        <div slot="footer"><b>大美汉服</b> ，致力于发扬中国传统文化。</div>
        <a-list-item slot="renderItem" slot-scope="item" key="item.title">
            <a-list-item-meta :description="item.author.username">
                <a slot="title" :href="item.href">{{item.title}}</a>
                <a-avatar slot="avatar" src="https://zos.alipayobjects.com/rmsportal/ODTLcjxAfvqbxHnVXCYX.png" />
            </a-list-item-meta>
            <p v-html="item.content"></p>
            <v-addComment :articleId="item.aid"></v-addComment>
        </a-list-item>
    </a-list>
</template>
<script>
    import axios from 'axios';
    import AddComment from "./AddComment";

    export default {
        name:"LittleWhiteList",
        components: {
            'v-addComment':AddComment,
        },
        data() {
            return {
                data: [],
                pagination: {
                    onChange: page => {
                        // eslint-disable-next-line no-console
                        console.log(page);
                    },
                    pageSize: 3,
                },
            };
        },
        mounted() {
            this.getData(res => {
                res.then(data => {
                    // eslint-disable-next-line no-console
                    console.log("Yay! " + data.data.list);
                    this.data = data.data.list;
                })
            });
        },
        methods: {
            gotoDetail(aid){
                axios.post('/article/details?aid='+aid,{aid,
                    xhrFields: {
                        withCredentials: true
                    },
                }).then((res) => {
                    // eslint-disable-next-line no-console
                    console.log(res)
                    if (res.data.resultCode == 0) {
                        this.articleData = res.data.data;
                        this.$message.success(
                            "标题："+this.articleData.title+"内容：\n"+this.articleData.content,
                            10,
                        );
                    } else {
                        this.$message.failure(
                            res.data.resultInfo,
                            10,
                        );
                    }
                })
            },
            getData(callback) {
                let values = {
                    currentPage: 1,
                    type: 3,
                };
                // axios.get('/article/list', {params: values}, {
                //     xhrFields: {
                //         withCredentials: true
                //     },
                // }).then((res) => {
                //     if (res.data.resultCode == 0) {
                //         callback(res);
                //     }
                // })
                callback(this.$api.getArticleList(values));
            },
            onLoadMore() {
                this.loadingMore = true;
                this.getData(res => {
                    this.data = this.data.concat(res.list);
                    this.loadingMore = false;
                    this.$nextTick(() => {
                        window.dispatchEvent(new Event('resize'));
                    });
                });
            },
        },
    };
</script>
<style>
    .demo-loadmore-list {
        min-height: 350px;
    }
</style>
