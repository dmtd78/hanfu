<template slot="actions">
    <div>
        <span v-for="{type, text} in actions" :key="type" @click="show(type)">
               <a-icon :type="type" style="margin-right: 8px"/>
              {{text}}
      </span>
        <div v-show="commentIsShow">
            <a-list v-if="comments.length" :pagination="pagination"
                    :dataSource="comments"
                    :header="`${comments.length} ${comments.length > 1 ? '回复' : '回复'}`"
                    itemLayout="horizontal"
            >
                <a-list-item slot="renderItem" slot-scope="item">
                    <a-comment
                            :author="item.userName"
                            :avatar="item.avatar"
                            :content="item.content"
                            :datetime="$moment(item.createTime).format('MM-DD HH:mm')"
                    >
                    </a-comment>
                </a-list-item>
            </a-list>
            <a-comment>
                <a-avatar
                        slot="avatar"
                        src="https://zos.alipayobjects.com/rmsportal/ODTLcjxAfvqbxHnVXCYX.png"
                        alt="Han Solo"
                />
                <div slot="content">
                    <a-form-item>
                        <a-textarea :rows="4" @change="handleChange" :value="value"></a-textarea>
                    </a-form-item>
                    <a-form-item>
                        <a-button htmlType="submit" :loading="submitting" @click="handleSubmit" type="primary">
                            发布
                        </a-button>
                    </a-form-item>
                </div>
            </a-comment>
        </div>
    </div>
</template>
<script>
    import axios from "axios";
    import moment from 'moment';
    // eslint-disable-next-line no-irregular-whitespace
    const userId = JSON.parse(sessionStorage.getItem("userId"));
    import qs from 'qs';

    export default {
        name: "AddComment",
        data() {
            return {
                actions: [],
                comments: [],
                submitting: false,
                value: '',
                moment,
                commentIsShow: false,
                pagination: {
                    onChange: page => {
                        // eslint-disable-next-line no-console
                        console.log(page);
                    },
                    pageSize: 3,
                },
            }
        },
        mounted() {
            this.getData(res => {
                res.then(data => {
                    // eslint-disable-next-line no-console
                    console.log("get article actions Yay! " + data.data);
                    this.actions = data.data;
                })
            });
        },
        methods: {
            getData(callback) {
                // axios.get('/article/getArticleActions', {params: {articleId: this.articleId}}, {
                //     xhrFields: {
                //         withCredentials: true
                //     },
                // },).then((res) => {
                //     if (res.data.resultCode == 0) {
                //         callback(res);
                //     }
                // })
                let params = {
                    articleId: this.articleId
                }
                callback(this.$api.getArticleActions(params))
            },
            show(str) {
                // eslint-disable-next-line no-console
                console.log(str);
                if (str == 'star-o') {
                    if (!userId) {
                        this.$message.info('请先登录！',5);
                        setTimeout(()=>{this.$router.push('/register')},1000);
                    }
                    let params = {
                        articleId:this.articleId,
                        userId:userId
                    };
                    this.$api.addCollectByUserId(params).then(res => {
                        if (res.resultCode == 0) {
                            this.$notification.open({
                                message: '温馨提醒',
                                description: '已收藏',
                                icon: <a-icon type="smile" style="color: #108ee9" />,
                            });
                        }
                    })
                    // axios.post('/collect/addCollectByUserId',qs.stringify(params),{
                    //     xhrFields: {
                    //         withCredentials: true
                    //     },
                    // },).then((res) => {
                    //     if (res.data.resultCode == 0) {
                    //         this.$notification.open({
                    //             message: '温馨提醒',
                    //             description: '已收藏',
                    //             icon: <a-icon type="smile" style="color: #108ee9" />,
                    //         });
                    //     }
                    // })
                } else if (str == 'like-o') {
                    this.$notification.open({
                        message: '温馨提醒',
                        description: '程序员忙不过来，该功能暂未上线，敬请期待！（你可以试试评论）',
                        icon: <a-icon type="smile" style="color: #108ee9" />,
                    });
                } else if (str == 'message') {
                    if (this.commentIsShow == false) {
                        axios.get('/comment/list',{params:{articleId:this.articleId}},{
                            xhrFields: {
                                withCredentials: true
                            },
                        },).then((res) => {
                            if (res.data.resultCode == 0) {
                                this.comments = res.data.data;
                            }
                        })
                        this.commentIsShow = true;
                    } else {
                        this.commentIsShow = false;
                    }
                }
            },
            handleSubmit() {
                if (!this.value) {
                    return;
                }
                if (!userId) {
                    this.$message.info('请先登录！',5);
                    setTimeout(()=>{this.$router.push('/register')},1000);
                }

                this.submitting = true;
                let values = {
                    articleId: this.articleId,
                    userId: userId,
                    content: this.value
                };
                axios.post('/comment/add', qs.stringify(values), {
                    xhrFields: {
                        withCredentials: true
                    },
                },).then((res) => {
                    this.submitting = false;
                    if (res.data.resultCode == 0) {
                        this.comments = [
                            {
                                userName: 'Han Solo',
                                avatar: 'https://zos.alipayobjects.com/rmsportal/ODTLcjxAfvqbxHnVXCYX.png',
                                content: this.value,
                                createTime: moment().fromNow(),
                            },
                            ...this.comments,
                        ];
                        this.value = '';
                    } else {
                        this.$message.failure(
                            res.data.resultInfo,
                            10,
                        );
                    }
                })
            },
            handleChange(e) {
                this.value = e.target.value;
            },
        },
        props: ['articleId'],

    }
</script>

<style scoped>

</style>