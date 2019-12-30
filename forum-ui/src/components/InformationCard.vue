<template>
    <a-card hoverable style="width: 240px">
        <img
                alt="example"
                src="https://gw.alipayobjects.com/zos/rmsportal/JiqGstEfoWAOHiTxclqi.png"
                slot="cover"
        />
        <template class="ant-card-actions" slot="actions">
            <a-icon type="setting" />
            <a-icon type="edit" />
            <a-icon type="ellipsis" />
        </template>
        <a-card-meta :title="data.username" :description="msg+$moment(data.lastLoginTime).format('YYYY-MM-DD')">
            <a-avatar
                    slot="avatar"
                    src="https://zos.alipayobjects.com/rmsportal/ODTLcjxAfvqbxHnVXCYX.png"
            />
        </a-card-meta>
    </a-card>
</template>

<script>
    import reqwest from 'reqwest';


    // eslint-disable-next-line no-irregular-whitespace
    const userId=JSON.parse(sessionStorage.getItem("userId"));
    const fakeDataUrl = 'http://localhost:8081/user/info?uid='+userId;
    export default {
        name: "InformationCard",
        data() {
            return {
                loading: true,
                loadingMore: false,
                showLoadingMore: true,
                msg:'上次登录时间：',
                data: [],
            };
        },
        mounted() {
            this.getData(res => {
                this.loading = false;
                this.data = res.data;
            });
        },
        methods: {
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
    }
</script>

<style scoped>

</style>