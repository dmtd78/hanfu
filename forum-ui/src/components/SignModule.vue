<template>
    <a-card hoverable style="width: 240px" v-show="isShow">
        <a-card-meta>
            <div :style="{ width: '240px', border: '1px solid #d9d9d9', borderRadius: '4px' }">
            </div>
        </a-card-meta>
        <div :style="{ height: '60px', border: '0px solid #d9d9d9', borderRadius: '4px' }">
            <div v-show="signButton">
                签到有礼 <a-icon type="form" @click="sign"/>
            </div>
            <div v-show="snippetsButton">
                今日已签到  <a-icon type="snippets" theme="filled"/>
            </div>
            <div v-if="integral==0">
                您还未签到，签到可以领积分！
            </div>
            <div v-else>
                已连续签到 {{signDay}}天
            </div>
        </div>
        <a-card-meta title="" :description="myIntegral"></a-card-meta>
        <div>
            <a-steps :current="currentStep" direction="vertical" progressDot size="small">
                <a-step v-for="(item,index) in signDays" :key='index' :title="item.title" :description="item.value"/>
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
                integral: 0,
                signDays: [],
                currentStep: 1
            }
        },
        mounted() {
            this.getData(res => {
                this.data = res.data.data;
                this.myIntegral = '我的积分：' + (this.data == null ? 0 : this.data.integral);
                if (this.data == null) {
                    this.integral = 0;
                } else {
                    //看今天是否已签到
                    this.hasIntegralByUserId();
                }
            });
            //积分情况
            this.getIntegralDays();
            //连续签到天数signDay
            this.getSignDayCount();
        },
        methods: {
            getSignDayCount(){
                if (userId == null) {
                    return;
                }
                axios.get('/integral/getSignDayCount', {params: {userId: userId}}, {
                    xhrFields: {
                        withCredentials: true
                    },
                }).then((res) => {
                    if (res.data.resultCode == 0) {
                        // eslint-disable-next-line no-console
                        console.log(res.data.data.signDay)
                        this.signDay=res.data.data.signDay;
                    }
                })
            },
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
            hasIntegralByUserId() {
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
                axios.get('/integral/getIntegralDays', {params: {userId: userId}}, {
                    xhrFields: {
                        withCredentials: true
                    },
                }).then((res) => {
                    if (res.data.resultCode == 0) {
                        let signDaysArr = res.data.data;
                        let presentIntegral = signDaysArr[signDaysArr.length - 1].integral;
                        let index = 0;
                        for (let i = 0; i < signDaysArr.length; i++) {
                            this.signDays.push({
                                title: signDaysArr[i].integral + '分',
                                value: this.$moment(signDaysArr[i].createTime).format('MM.DD'),
                            });
                            if (this.$moment(signDaysArr[i].createTime).format('MM.DD') != this.$moment(Date.now()).format('MM.DD')) {
                                index++;
                                // eslint-disable-next-line no-console
                                console.log(index)
                            } else {
                                this.currentStep = index;
                                // eslint-disable-next-line no-console
                                console.log('currentStep:' + this.currentStep)
                            }
                        }
                        if (this.$moment(signDaysArr[signDaysArr.length - 1].createTime).format('MM.DD')
                            == this.$moment(Date.now()).format('MM.DD')) {
                            this.signDays.push({
                                title: ((presentIntegral + 1) >= 7 ? 7 : presentIntegral + 1) + '分',
                                value: this.$moment(Date.now()).add(1, 'days').format('MM.DD')
                            });
                            this.currentStep = index;
                            // eslint-disable-next-line no-console
                            console.log('currentStep:' + this.currentStep)
                        } else if (this.$moment(signDaysArr[signDaysArr.length - 1].createTime).format('MM.DD')
                            == this.$moment(Date.now()).add(-1, 'days').format('MM.DD')) {
                            this.signDays.push({
                                title: ((presentIntegral + 1) >= 7 ? 7 : presentIntegral + 1) + '分',
                                value: this.$moment(Date.now()).format('MM.DD')
                            }, {
                                title: ((presentIntegral + 2) >= 7 ? 7 : presentIntegral + 2) + '分',
                                value: this.$moment(Date.now()).add(1, 'days').format('MM.DD')
                            });
                            this.currentStep = index - 1;
                            // eslint-disable-next-line no-console
                            console.log('currentStep:' + this.currentStep)
                        } else {
                            this.signDays.push({
                                title: '1分',
                                value: this.$moment(Date.now()).format('MM.DD')
                            }, {
                                title: '2分',
                                value: this.$moment(Date.now()).add(1, 'days').format('MM.DD')
                            });
                            this.currentStep = 1;
                            // eslint-disable-next-line no-console
                            console.log('currentStep:' + this.currentStep)
                        }
                    }
                })
            },
        }
    }
</script>

<style scoped>

</style>