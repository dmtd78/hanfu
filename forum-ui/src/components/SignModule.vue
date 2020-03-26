<template>
    <a-card hoverable style="width: 240px" v-show="isLogin">
        <a-card-meta>
            <div :style="{ width: '240px', border: '1px solid #d9d9d9', borderRadius: '4px' }">
            </div>
        </a-card-meta>
        <div :style="{ height: '60px', border: '0px solid #d9d9d9', borderRadius: '4px' }">
            <div v-show="signButton">
                签到有礼
                <a-icon type="form" @click="sign"/>
            </div>
            <div v-show="snippetsButton">
                今日已签到
                <a-icon type="snippets" theme="filled"/>
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
    // eslint-disable-next-line no-irregular-whitespace
    import {mapGetters} from "vuex";

    const userId = JSON.parse(sessionStorage.getItem("userId"));
    export default {
        name: "SignModule",
        data() {
            return {
                myIntegral: '',
                signDay: 1,
                signButton: true,
                snippetsButton: false,
                integral: 0,
                signDays: [],
                currentStep: 1
            }
        },
        computed: {
            ...mapGetters({
                isLogin: 'auth/isLogin',
                user: 'auth/user'
            }),
        },
        mounted() {
            this.getData(res => {
                this.data = res.data;
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
            getSignDayCount() {
                if (userId == null) {
                    return;
                }
                let params = {userId: userId};
                this.$api.getSignDayCount(params).then(res => {
                    if (res.resultCode == 0) {
                        this.signDay = res.data.signDay;
                    }
                })
            },
            sign() {
                let params = {userId: userId};
                this.$api.addIntegralByUserId(params).then(res => {
                    if (res.resultCode == 0) {
                        this.signButton = false;
                        this.snippetsButton = true;
                        this.integral = 1;
                        this.getData(res => {
                            this.data = res.data;
                            this.myIntegral = '我的积分：' + (this.data == null ? 0 : this.data.integral);
                        });
                    }
                });
            },
            getData(callback) {
                if (userId == null) {
                    return;
                }
                let params = {userId: userId}
                this.$api.getIntegralByUserId(params).then(res => {
                    // eslint-disable-next-line no-console
                    console.log("getIntegralByUserId,Yay! " + res.resultCode);
                    if (res.resultCode == 0) {
                        callback(res);
                    }
                })
            },
            hasIntegralByUserId() {
                if (userId == null) {
                    return;
                }
                let params = {userId: userId};
                this.$api.hasIntegralByUserId(params).then(res => {
                    if (res.resultCode == 0) {
                        if (res.data != null) {
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
                let params = {userId: userId}
                this.$api.getIntegralDays(params).then(res => {
                    if (res.resultCode == 0) {
                        let signDaysArr = res.data;
                        let presentIntegral = signDaysArr[signDaysArr.length - 1].integral;
                        let index = 0;
                        for (let i = 0; i < signDaysArr.length; i++) {
                            this.signDays.push({
                                title: signDaysArr[i].integral + '分',
                                value: this.$moment(signDaysArr[i].createTime).format('MM.DD'),
                            });
                            if (this.$moment(signDaysArr[i].createTime).format('MM.DD') != this.$moment(Date.now()).format('MM.DD')) {
                                index++;
                            } else {
                                this.currentStep = index;
                            }
                        }
                        if (this.$moment(signDaysArr[signDaysArr.length - 1].createTime).format('MM.DD')
                            == this.$moment(Date.now()).format('MM.DD')) {
                            this.signDays.push({
                                title: ((presentIntegral + 1) >= 7 ? 7 : presentIntegral + 1) + '分',
                                value: this.$moment(Date.now()).add(1, 'days').format('MM.DD')
                            });
                            this.currentStep = index;
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
                        } else {
                            this.signDays.push({
                                title: '1分',
                                value: this.$moment(Date.now()).format('MM.DD')
                            }, {
                                title: '2分',
                                value: this.$moment(Date.now()).add(1, 'days').format('MM.DD')
                            });
                            this.currentStep = 1;
                        }
                    }
                })
            },
        }
    }
</script>

<style scoped>

</style>