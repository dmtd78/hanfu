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
                <br>
            </div>
            <div v-show="snippetsButton">
                今日已签到
                <a-icon type="snippets" theme="filled"/>
                <br>
            </div>
            <div v-if="integral==0">
                您还未签到，签到可以领积分！
            </div>
            <div v-else>
                已连续签到
                <br>
                {{signDay}}天
            </div>
        </div>
        <a-card-meta title="" :description="myIntegral">
        </a-card-meta>
        <div>
            <br>
            <a-steps direction="vertical" size="small" progressDot :current="signDay-1">
                <a-step title="1分" description="1天" />
                <a-step title="2分" description="2天" />
                <a-step title="3分" description="3天" />
                <a-step title="……" description="……" />
            </a-steps>
        </div>
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
                integral:0
            }
        },
        mounted() {
            this.getData(res => {
                this.data = res.data.data;
                this.myIntegral = '我的积分：' + (this.data == null ? 0 : this.data.integral);
                if(this.data == null){
                    this.integral = 0;
                }
            });
            //连续签到天数
            this.getIntegralDays();
            //看今天是否已签到
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
                        this.signButton = false;
                        this.snippetsButton = true;
                        this.integral = 1;
                        this.getData(res => {
                            this.data = res.data.data;
                            this.myIntegral = '我的积分：' + (this.data == null ? 0 : this.data.integral);
                        });
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
                            this.integral = 1;
                        } else {
                            this.signButton = true;
                            this.snippetsButton = false;
                            this.integral = 1;
                        }

                    }
                })
            },
            getIntegralDays() {
                if (userId == null) {
                    return;
                }
                axios.get('/integral/getRecentlyIntegralByUserId', {params: {userId: userId}}, {
                    xhrFields: {
                        withCredentials: true
                    },
                }).then((res) => {
                    if (res.data.resultCode == 0) {
                        this.signDay = res.data.data.integral;
                    }
                })
            },
        }
    }
</script>

<style scoped>

</style>