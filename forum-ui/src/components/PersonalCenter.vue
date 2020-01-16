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
                <a slot="actions">{{$moment(article.date).format('YY-MM-DD HH:mm')}}</a>
                <a slot="actions"><a-icon type="delete" theme="filled" @click="deleteArticle(article.aid)" /></a>
                <a-list-item-meta :descreption="article.title">
                    <a slot="title" @click="gotoDetail(article.aid)">{{article.title}}</a>
                </a-list-item-meta>
            </a-list-item>
        </a-list>
        <a-divider orientation="left">我的收藏</a-divider>
        <a-list bordered :dataSource="iCollectData">
            <a-list-item slot="renderItem" slot-scope="iCollctArticle">
                <a slot="actions">{{$moment(iCollctArticle.date).format('YY-MM-DD HH:mm')}}</a>
                <a slot="actions"><a-icon type="delete" theme="filled" @click="deleteCollect(iCollctArticle.aid)"/></a>
                <a-list-item-meta :descreption="iCollctArticle.title">
                    <a slot="title" @click="gotoDetail(iCollctArticle.aid)">{{iCollctArticle.title}}</a>
                </a-list-item-meta>
            </a-list-item>
        </a-list>
    </div>
</template>

<script>
    import axios from 'axios';
    import qs from 'qs';
    // eslint-disable-next-line no-irregular-whitespace
    const userId = JSON.parse(sessionStorage.getItem("userId"));
    export default {
        data() {
            return {
                text: `A dog is a type of domesticated animal.Known for its loyalty and faithfulness,it can be found as a welcome guest in many households across the world.`,
                userData: [],
                articleData: [],
                iCollectData:[]
            };
        },
        name: "PersonalCenter",
        mounted() {
            this.getArticleData(res => {
                this.articleData = res.data.data.list;
            });
            this.getICollectArticleData(res => {
                // eslint-disable-next-line no-console
                console.log('i collect article data list:' + res.data.data);
                this.iCollectData = res.data.data;
            });
            this.getUserData(res => {
                this.userData = res.data;
            });
        },
        methods: {
            deleteArticle(articleId){
                let params = {
                    articleId :articleId
                };
                axios.post('/article/delete',qs.stringify(params),{
                    xhrFields: {
                        withCredentials: true
                    },
                },).then((res) => {
                    if (res.data.resultCode == 0) {
                        this.$notification.open({
                            message: '温馨提醒',
                            description: '已删除',
                            icon: <a-icon type="smile" style="color: #108ee9" />,
                        });
                    }
                })
            },
            deleteCollect(articleId){
                let params = {
                    articleId :articleId,
                    userId :userId
                };
                axios.post('/collect/deleteCollectByArticleId',qs.stringify(params),{
                    xhrFields: {
                        withCredentials: true
                    },
                },).then((res) => {
                    if (res.data.resultCode == 0) {
                        this.$notification.open({
                            message: '温馨提醒',
                            description: '已删除',
                            icon: <a-icon type="smile" style="color: #108ee9" />,
                    });
                    }
                })
            },
            getArticleData(callback) {
                axios.get('/article/list?currentPage=1&userId=' + userId, {
                    xhrFields: {
                        withCredentials: true
                    },
                }).then((res) => {
                    if (res.data.resultCode == 0) {
                        callback(res)
                    } else {
                        this.$message.failure(
                            res.data.resultInfo,
                            10,
                        );
                    }
                })
            },
            getICollectArticleData(callback) {
                axios.get('/article/iCollectArticles?currentPage=1&userId=' + userId, {
                    xhrFields: {
                        withCredentials: true
                    },
                }).then((res) => {
                    if (res.data.resultCode == 0) {
                        callback(res)
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