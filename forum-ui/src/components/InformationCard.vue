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
        <a-card-meta :title="data.username" description="This is the description">
            <a-avatar
                    slot="avatar"
                    src="https://zos.alipayobjects.com/rmsportal/ODTLcjxAfvqbxHnVXCYX.png"
            />
        </a-card-meta>
    </a-card>
</template>

<script>
    import reqwest from 'reqwest';

    const fakeDataUrl = 'http://106.12.61.131:8081/user/info?uid=1';
    export default {
        name: "InformationCard",
        data() {
            return {
                loading: true,
                loadingMore: false,
                showLoadingMore: true,
                data: [],
            };
        },
        mounted() {
            this.getData(res => {
                this.loading = false;
                this.data = res;
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