<template>
    <a-list itemLayout="vertical" size="large" :pagination="pagination" :dataSource="data">
        <div slot="footer"><b>大美汉服</b> ，致力于发扬中国传统文化。</div>
        <a-list-item slot="renderItem" slot-scope="item" key="item.title">
            <img slot="extra" v-if="item.img!=null" width="200" alt="logo" :src="item.img" v-show="shortShow"/>
            <a-list-item-meta :description="item.author.username">
                <a slot="title" :href="item.href">{{item.title}}</a>
                <a-avatar slot="avatar" src="https://zos.alipayobjects.com/rmsportal/ODTLcjxAfvqbxHnVXCYX.png"/>
            </a-list-item-meta>
            <p v-html="item.content" v-show="longShow"></p>
            <p v-html="item.content.substring(0,400)" v-show="shortShow"></p>
            <div v-if="item.content.length>400">
                <a @click="showMoreContent(1)" v-show="shortShow">阅读全文</a>
                <a @click="showMoreContent(2)" v-show="longShow">收起</a>
            </div>
            <v-addComment :articleId="item.aid"></v-addComment>
        </a-list-item>
    </a-list>
</template>
<script>
    import AddComment from "./AddComment";
    import axios from 'axios';

    export default {
        name: "ForumListImg",
        components: {
            'v-addComment': AddComment,
        },
        data() {
            return {
                allContent: '1',
                moreContent: '',
                shortShow: true,
                longShow: false,
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
                this.loading = false;
                this.data = res.data.data.list;
            });
        },
        methods: {
            showMoreContent(flag) {
                //this.allContent=str;
                //this.moreContent=str;
                if (flag == 1) {
                    this.shortShow = false;
                    this.longShow = true;
                } else {
                    this.shortShow = true;
                    this.longShow = false;
                }

            },
            getData(callback) {
                let values = {
                    currentPage: 1,
                    type: 2,
                };
                axios.get('/article/list', {params: values}, {
                    xhrFields: {
                        withCredentials: true
                    },
                }).then((res) => {
                    if (res.data.resultCode == 0) {
                        callback(res);
                    }
                })
            },
        }
    };
</script>
<style></style>
