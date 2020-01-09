<template>
    <a-card hoverable style="width: 240px">
        <img
                alt="example"
                src="https://gw.alipayobjects.com/zos/rmsportal/JiqGstEfoWAOHiTxclqi.png"
                slot="cover"
        />
        <template class="ant-card-actions" slot="actions">
            <a-icon type="setting"/>
            <a-icon type="edit"/>
            <a-icon type="ellipsis"/>
        </template>
        <a-card-meta v-if="data.username==null" :title="loginMsg">
            <a-avatar slot="avatar" src="https://zos.alipayobjects.com/rmsportal/ODTLcjxAfvqbxHnVXCYX.png"/>
        </a-card-meta>
        <a-card-meta v-else :title="data.username" :description="msg+$moment(data.lastLoginTime).format('YYYY-MM-DD')">
            <a-avatar slot="avatar" src="https://zos.alipayobjects.com/rmsportal/ODTLcjxAfvqbxHnVXCYX.png"/>
        </a-card-meta>
    </a-card>
</template>

<script>
    import axios from 'axios';
    // eslint-disable-next-line no-irregular-whitespace
    const userId = JSON.parse(sessionStorage.getItem("userId"));
    export default {
        name: "InformationCard",
        data() {
            return {
                loading: true,
                loadingMore: false,
                showLoadingMore: true,
                msg: '上次登录时间：',
                loginMsg: '请登录',
                data: [],
            };
        },
        mounted() {
            this.getData(res => {
                this.loading = false;
                this.data = res.data.data;
            });
        },
        methods: {
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