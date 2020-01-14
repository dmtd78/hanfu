<template>
    <div>
        <a-divider orientation="left">基本信息</a-divider>
        <a-card style="width: 300px">
            <p>{{userData.username}}</p>
            <p>{{userData.nickname}}</p>
        </a-card>
        <a-divider orientation="left">我的帖子</a-divider>
        <a-list bordered :dataSource="articleData">
            <a-list-item slot="renderItem" slot-scope="article">
                <a slot="actions">{{$moment(article.date).format('YYYY-MM-DD')}}</a>
                <a-list-item-meta :descreption="article.title">
                    <a slot="title" @click="gotoDetail(article.aid)">{{article.title}}</a>
                </a-list-item-meta>
            </a-list-item>
        </a-list>
        <a-divider orientation="left">我的收藏</a-divider>
        <a-list bordered :dataSource="articleData">
            <a-list-item slot="renderItem" slot-scope="article">
                <a slot="actions">{{$moment(article.date).format('YYYY-MM-DD')}}</a>
                <a-list-item-meta :descreption="article.title">
                    <a slot="title" @click="gotoDetail(article.aid)">{{article.title}}</a>
                </a-list-item-meta>
            </a-list-item>
        </a-list>
    </div>
</template>

<script>
    import axios from 'axios';
    // eslint-disable-next-line no-irregular-whitespace
    const userId = JSON.parse(sessionStorage.getItem("userId"));
    export default {
        data() {
            return {
                text: `A dog is a type of domesticated animal.Known for its loyalty and faithfulness,it can be found as a welcome guest in many households across the world.`,
                userData: [],
                articleData: [],
                collectData:[]
            };
        },
        name: "PersonalCenter",
        mounted() {
            this.getArticleData(res => {
                // eslint-disable-next-line no-console
                console.log('article data list:' + res.data.list);
                this.articleData = res.data.data.list;
            });
            this.getUserData(res => {
                this.userData = res.data;
            });
        },
        methods: {
            getArticleData() {
                axios.get('/article/list?currentPage=1&userId=' + userId, {
                    xhrFields: {
                        withCredentials: true
                    },
                }).then((res) => {
                    if (res.data.resultCode == 0) {
                        this.articleData = res.data.data.list;
                    } else {
                        this.$message.failure(
                            res.data.resultInfo,
                            10,
                        );
                    }
                })
            },
            getUserData() {
                axios.get('/user/info?uid=' + userId, {
                    xhrFields: {
                        withCredentials: true
                    },
                }).then((res) => {
                    // eslint-disable-next-line no-console
                    console.log('user data list:' + res.data);
                    if (res.data.resultCode == 0) {
                        this.userData = res.data.data;
                    } else {
                        this.$message.failure(
                            res.data.resultInfo,
                            10,
                        );
                    }
                })
            },
        }
    }
</script>

<style scoped>

</style>