<template>
    <a-list itemLayout="vertical" size="large" :pagination="pagination" :dataSource="data">
        <div slot="footer"><b>大美汉服</b> ，致力于发扬中国传统文化。</div>
        <a-list-item slot="renderItem" slot-scope="item" key="item.title">
            <template slot="actions" v-for="{type, text} in actions">
        <span :key="type" @click="show(type)">
          <a-icon :type="type" style="margin-right: 8px" />
          {{text}}
        </span>
            </template>
            <img slot="extra" width="272" alt="logo" src="../assets/logo-hanfu.png"
            />
            <a-list-item-meta :description="item.author.username">
                <a slot="title" :href="item.href">{{item.title}}</a>
                <a-avatar slot="avatar" src="https://zos.alipayobjects.com/rmsportal/ODTLcjxAfvqbxHnVXCYX.png" />
            </a-list-item-meta>
            <p v-html="item.content" v-show="longShow"></p>
            <p v-html="item.content.substring(0,400)" v-show="shortShow"></p>
            <div v-if="item.content.length>400">
                <a @click="showMoreContent(1)" v-show="shortShow">阅读全文</a>
                <a @click="showMoreContent(2)" v-show="longShow">收起</a>
            </div>
        </a-list-item>
    </a-list>
</template>
<script>
    import reqwest from 'reqwest';
    import axios from 'axios';
    const fakeDataUrl = 'http://106.12.61.131:8081/article/list?currentPage=1&type=2';

    export default {
        name:"ForumListImg",
        data() {
            return {
                allContent:'1',
                moreContent:'',
                shortShow:true,
                longShow:false,
                data:[],
                pagination: {
                    onChange: page => {
                        // eslint-disable-next-line no-console
                        console.log(page);
                    },
                    pageSize: 3,
                },
                actions: [
                    { type: 'star-o', text: '156' },
                    { type: 'like-o', text: '156' },
                    { type: 'message', text: '2' },
                ],
            };
        },
        mounted() {
            this.getData(res => {
                this.loading = false;
                this.data = res.data.list;
            });
        },
        methods: {
            show(str){
                // eslint-disable-next-line no-console
                console.log(str);
                if(str=='star-o'){
                    axios.post('/').then((res) => {
                        if (res.data.resultCode == 0) {
                            this.$message.success(
                                res.data.resultInfo,
                                10,
                            );
                            this.$router.push('/home');
                        } else {
                            this.$message.failure(
                                res.data.resultInfo,
                                10,
                            );
                        }
                    })
                }else if(str=='like-o'){
                    axios.post('/')
                }
            },
            showMoreContent(flag){
                //this.allContent=str;
                //this.moreContent=str;
                if(flag==1){
                    this.shortShow=false;
                    this.longShow = true;
                }else{
                    this.shortShow=true;
                    this.longShow = false;
                }

            },
            getData(callback) {
                reqwest({
                    url: fakeDataUrl,
                    type: 'json',
                    method: 'get',
                    contentType: 'application/json',
                    success: res => {
                        callback(res);
                    },
                });
            },
        }
    };
</script>
<style></style>
