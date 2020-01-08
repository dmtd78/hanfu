<template slot="actions">
    <div>
        <span v-for="{type, text} in actions" :key="type" @click="show(type)">
               <a-icon :type="type" style="margin-right: 8px"/>
              {{text}}
      </span>
        <a-list :visible="visible"
                v-if="comments.length"
                :dataSource="comments"
                :header="`${comments.length} ${comments.length > 1 ? 'replies' : 'reply'}`"
                itemLayout="horizontal"
        >
            <a-list-item slot="renderItem" slot-scope="item">
                <a-comment
                        :author="item.author"
                        :avatar="item.avatar"
                        :content="item.content"
                        :datetime="item.datetime"
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
                        Add Comment
                    </a-button>
                </a-form-item>
            </div>
        </a-comment>
    </div>
</template>
<script>
    import axios from "axios";
    import moment from 'moment';
    // import qs from 'qs';
    export default {
        name: "AddComment",
        data() {
            return {
                actions: [],
                comments: [],
                submitting: false,
                value: '',
                moment,
                visible:false,
            }
        },
        mounted() {
            this.getData(res => {
                this.actions = res.data.data;
            });
        },
        methods: {
            getData(callback) {
                axios.get('/article/getArticleActions',{params:{articleId:this.articleId}}, {
                    xhrFields: {
                        withCredentials: true
                    },
                },).then((res) => {
                    if (res.data.resultCode == 0) {
                        callback(res);
                    }
                })
            },
            show(str) {
                // eslint-disable-next-line no-console
                console.log(str);
                if (str == 'star-o') {
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
                } else if (str == 'like-o') {
                    axios.post('/')
                } else if (str == 'message') {
                    this.visible = true;
                }
            },
        },
        props:['articleId'],
        handleSubmit() {
            if (!this.value) {
                return;
            }

            this.submitting = true;

            setTimeout(() => {
                this.submitting = false;
                this.comments = [
                    {
                        author: 'Han Solo',
                        avatar: 'https://zos.alipayobjects.com/rmsportal/ODTLcjxAfvqbxHnVXCYX.png',
                        content: this.value,
                        datetime: moment().fromNow(),
                    },
                    ...this.comments,
                ];
                this.value = '';
            }, 1000);
        },
        handleChange(e) {
            this.value = e.target.value;
        },

    }
</script>

<style scoped>

</style>