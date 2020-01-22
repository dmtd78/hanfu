<template>
    <a-dropdown>
        <span style="user-select: none">{{lrmsg}}</span>
        <a-menu slot="overlay">
            <a-menu-item key="1" @click="gotoPersonalInfo">个人中心</a-menu-item>
            <a-menu-item key="2" @click="loginOut">退出</a-menu-item>
        </a-menu>
    </a-dropdown>
</template>


<script>
    import axios from 'axios';
    // eslint-disable-next-line no-irregular-whitespace
    const userId = JSON.parse(sessionStorage.getItem("userId"));
    export default {
        name: "LoginOut",
        data() {
            return {
                lrmsg: '注册/登录'
            }
        },
        props: {
            current: {
                type: String,
                default: '/home'
            }
        },
        computed: {
            selectedKeys() {
                return [this.current]
            }
        },
        mounted() {
            this.getData(res => {
                this.data = res.data;
            });
        },
        methods: {
            changeNavigate({key}) {
                this.$router.push(key).catch(() => {
                })
            },
            getData() {
                if (userId == null) {
                    return;
                }
                axios.get('/user/info', {params: {uid: userId}}, {
                    xhrFields: {
                        withCredentials: true
                    },
                }).then((res) => {
                    if (res.data.resultCode == 0) {
                        this.lrmsg = res.data.data.username;
                    } else {
                        this.$message.failure(
                            res.data.resultInfo,
                            10,
                        );
                    }
                })
            },
            gotoPersonalInfo() {
                this.$router.push('/personalInfo');
            },
            loginOut() {
                axios.get('/user/exit?uid=' + userId, {
                    xhrFields: {
                        withCredentials: true
                    },
                }).then((res) => {
                    if (res.data.resultCode == 0) {
                        sessionStorage.removeItem("userId");
                        sessionStorage.removeItem("userToken");
                        this.$router.push('/home');
                    } else {
                        this.$message.failure(
                            res.data.resultInfo,
                            10,
                        );
                    }
                })
            }
        }
    }
</script>

<style scoped>

</style>