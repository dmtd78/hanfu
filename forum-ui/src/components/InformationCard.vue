<template>
    <div>
        <a-card hoverable style="width: 240px">
            <img
                    alt="example"
                    src="../assets/hanfu-hf.jpg"
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
            <a-card-meta v-if="!isLogin" :title="loginMsg" @click="toLogin">
                <a-avatar slot="avatar" src="https://zos.alipayobjects.com/rmsportal/ODTLcjxAfvqbxHnVXCYX.png"/>
            </a-card-meta>
            <a-card-meta v-else :title="user.username"
                         :description="msg+$moment(data.lastLoginTime).format('YYYY-MM-DD')">
                <a-avatar slot="avatar" src="https://zos.alipayobjects.com/rmsportal/ODTLcjxAfvqbxHnVXCYX.png"/>
            </a-card-meta>
        </a-card>
        <SignModule></SignModule>
    </div>
</template>

<script>
    import {mapActions, mapGetters} from 'vuex'
    import SignModule from "./SignModule";
    // eslint-disable-next-line no-irregular-whitespace
    const userId = JSON.parse(sessionStorage.getItem("userId"));
    export default {
        name: "InformationCard",
        components: {SignModule},
        data() {
            return {
                msg: '上次登录时间：',
                loginMsg: '请登录',
                data: {},
            };
        },
        computed: {
            ...mapGetters({
                isLogin: 'auth/isLogin',
                user: 'auth/user'
            }),
        },
        mounted() {},
        methods: {
            ...mapActions({
                checkLogin: 'auth/checkLogin'
            }),
            toLogin() {
                this.$router.push('/register')
            },
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
                let values = {
                    uid: userId,
                };
                callback(this.$api.getUserInfo(values))
            },
        }
    }
</script>

<style scoped>

</style>