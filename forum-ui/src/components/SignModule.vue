<template>
    <a-card hoverable style="width: 240px" v-show="isShow">
        <a-card-meta>
            <div :style="{ width: '240px', border: '1px solid #d9d9d9', borderRadius: '4px' }">
            </div>
        </a-card-meta>
        <div :style="{ height: '100px', border: '0px solid #d9d9d9', borderRadius: '4px' }">
            <div v-show="signButton">
            签到有礼
            <a-icon type="form" @click="sign"/>
            </div>
            <div v-show="snippetsButton">
            今日已签到
            <a-icon type="snippets" theme="filled"/>
            </div>
            <br><br>
            已连续签到
            <br>
            {{signDay}}天
        </div>
        <a-card-meta title="" :description="myIntegral">
        </a-card-meta>
    </a-card>
</template>

<script>
    import axios from 'axios';
    import qs from 'qs';
    // eslint-disable-next-line no-irregular-whitespace
    const userId = JSON.parse(sessionStorage.getItem("userId"));
    export default {
        name: "SignModule",
        data() {
            return {
                myIntegral: '',
                isShow: false,
                signDay: 1,
                signButton: true,
                snippetsButton: false,
            }
        },
        mounted() {
            this.getData(res => {
                this.loading = false;
                this.data = res.data.data;
                this.myIntegral = '我的积分：' + this.data.integral;
            });
            this.getIntegralData();
        },
        methods: {
            sign() {
                let params = {userId: userId};
                axios.post('/integral/addIntegralByUserId', qs.stringify(params), {
                    xhrFields: {
                        withCredentials: true
                    },
                }).then((res) => {
                    if (res.data.resultCode == 0) {
                        this.isShow = true;
                    }
                })
            },
            getData(callback) {
                if (userId == null) {
                    return;
                }
                axios.get('/integral/getIntegralByUserId', {params: {userId: userId}}, {
                    xhrFields: {
                        withCredentials: true
                    },
                }).then((res) => {
                    if (res.data.resultCode == 0) {
                        this.isShow = true;
                        callback(res);
                    }
                })
            },
            getIntegralData() {
                if (userId == null) {
                    return;
                }
                axios.get('/integral/hasIntegralByUserId', {params: {userId: userId}}, {
                    xhrFields: {
                        withCredentials: true
                    },
                }).then((res) => {
                    if (res.data.resultCode == 0) {
                        if (res.data.data != null) {
                            this.signButton = false;
                            this.snippetsButton = true;
                        } else {
                            this.signButton = true;
                            this.snippetsButton = false;
                        }

                    }
                })
            },
        }
    }
</script>

<style scoped>

</style>