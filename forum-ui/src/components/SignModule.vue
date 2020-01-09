<template>
    <a-card hoverable style="width: 240px">
        <a-card-meta>
            <div :style="{ width: '240px', border: '1px solid #d9d9d9', borderRadius: '4px' }">

            </div>
        </a-card-meta>

        <a-card-meta title="" :description="data.integral">

        </a-card-meta>

    </a-card>
</template>

<script>
    import axios from 'axios';
    // eslint-disable-next-line no-irregular-whitespace
    const userId = JSON.parse(sessionStorage.getItem("userId"));
    export default {
        name: "SignModule",
        mounted() {
            this.getData(res => {
                this.loading = false;
                this.data = res.data.data;
                // eslint-disable-next-line no-console
                console.log('get data :'+this.data);
            });
        },
        methods: {
            getData(callback) {
                if (userId == null) {
                    return;
                }
                axios.get('/integral/getIntegralByUserId', {params: {uid: userId}}, {
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