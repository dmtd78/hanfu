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
        name:"ForumListNoImg",
        components: {
            'v-addComment':AddComment,
        },
        data() {
            return {
                data:[],
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
            getData(callback) {
                let values = {
                    currentPage:1,
                    type:1,
                };
                axios.get('/article/list',{params:values},{
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
