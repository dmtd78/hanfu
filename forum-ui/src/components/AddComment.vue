<template slot="actions">
    <div>
        <span v-for="{type, text} in actions" :key="type" @click="show(type)">
               <a-icon :type="type" style="margin-right: 8px"/>
              {{text}}
      </span>
    </div>
</template>
<script>
    import axios from "axios";
    // import qs from 'qs';
    export default {
        name: "AddComment",
        data() {
            return {
                actions: [],
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
                    axios.post('/')
                }
            },
        },
        props:['articleId']

    }
</script>

<style scoped>

</style>