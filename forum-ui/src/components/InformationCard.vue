<template>
    <div>
        <a-card hoverable style="width: 240px">
            <img
                    alt="example"
                    src="https://gw.alipayobjects.com/zos/rmsportal/JiqGstEfoWAOHiTxclqi.png"
                    slot="cover"
            />
            <template class="ant-card-actions" slot="actions">
                <a-tooltip placement="topLeft" title="设置">
                    <a-icon type="setting" @click="toPersonalCenter"/>
                </a-tooltip>
                <a-tooltip placement="topLeft" title="修改">
                    <a-icon type="edit"/>
                </a-tooltip>
                <a-tooltip placement="topLeft" title="个人中心">
                    <a-icon type="ellipsis"/>
                </a-tooltip>
            </template>
            <a-card-meta v-if="data.username==null" :title="loginMsg">
                <a-avatar slot="avatar" src="https://zos.alipayobjects.com/rmsportal/ODTLcjxAfvqbxHnVXCYX.png"/>
            </a-card-meta>
            <a-card-meta v-else :title="data.username"
                         :description="msg+$moment(data.lastLoginTime).format('YYYY-MM-DD')">
                <a-avatar slot="avatar" src="https://zos.alipayobjects.com/rmsportal/ODTLcjxAfvqbxHnVXCYX.png"/>
            </a-card-meta>
        </a-card>
        <SignModule></SignModule>
    </div>
</template>

<script>
    import axios from 'axios';
    import SignModule from "./SignModule";
    // eslint-disable-next-line no-irregular-whitespace
    const userId = JSON.parse(sessionStorage.getItem("userId"));
    export default {
        name: "InformationCard",
        components: {SignModule},
        data() {
            return {
                loading: true,
                loadingMore: false,
                showLoadingMore: true,
                msg: '上次登录时间：',
                loginMsg: '请登录',
                data: {},
            };
        },
        mounted() {
            this.getData(res => {
                this.loading = false;
                this.data = res.data.data;
                // eslint-disable-next-line no-console
                console.log('get data :' + this.data);
            });
        },
        methods: {
            toPersonalCenter() {
                if (!userId) {
                    this.$message.info('请先登录！', 5);
                    setTimeout(() => {
                        this.$router.push('/register')
                    }, 1000);
                } else {
                    this.$router.push('/personalInfo');
                }
            },
            getData(callback) {
                if (userId == null) {
                    return;
                }
                axios.get('/user/info', {params: {uid: userId}}, {
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
    }
</script>

<style scoped>

</style>