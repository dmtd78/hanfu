<template>
<div>
    <a-breadcrumb :style="{ margin: '16px 0' }">
        <a-breadcrumb-item>首页</a-breadcrumb-item>
        <a-breadcrumb-item>登录</a-breadcrumb-item>
    </a-breadcrumb>
    <div>
        <a-tabs defaultActiveKey="login">
            <a-tab-pane tab="登录" key="login">
                <a-form layout='vertical' :form="loginForm" @submit="login">
                    <a-form-item label='登录账号'>
                        <a-input v-decorator="['username',{rules: [{ required: true, message: '请输入登录账号！' }]}]"/>
                    </a-form-item>
                    <a-form-item label="密码">
                        <a-input v-decorator="['password',{rules: [{required: true,message: '请输入密码！'}]}]" type="password"
                        />
                    </a-form-item>
                    <a-form-item>
                        <a-button type="primary" html-type="submit">
                            登录
                        </a-button>
                    </a-form-item>
                </a-form>
            </a-tab-pane>
            <a-tab-pane tab="注册" key="register">
                <a-form layout='vertical' :form="registerForm" @submit="register">
                    <a-form-item>
                            <span slot="label">
                                登录账号&nbsp;
                                <a-tooltip title="如果输入了手机号，也可以用手机号登录！">
                                <a-icon type="question-circle-o"/>
                                </a-tooltip>
                            </span>
                        <a-input v-decorator="['username',{rules: [{ required: true, message: '请输入你的登录账号！', whitespace: true }],},]"
                        />
                    </a-form-item>
                    <a-form-item label="密码">
                        <a-input v-decorator="['password', {rules: [{required: true,message: '请输入密码！'}]}]" type="password"
                        />
                    </a-form-item>
                    <a-form-item label="确认密码">
                        <a-input v-decorator="['confirm',{rules: [{required: true,message: '请确认密码！'}]}]" type="password"/>
                    </a-form-item>
                    <a-form-item label="手机号">
                        <a-input
                                v-decorator="['phone',{rules: [{ required: false, message: '请输入你的手机号！' }]}]" style="width: 100%">
                            <a-select slot="addonBefore" v-decorator="['prefix', { initialValue: '86' }]" style="width: 70px">
                                <a-select-option value="86">
                                    +86
                                </a-select-option>
                                <a-select-option value="87">
                                    +87
                                </a-select-option>
                            </a-select>
                        </a-input>
                    </a-form-item>
                    <a-form-item>
                        <a-button type="primary" html-type="submit">
                            注册
                        </a-button>
                    </a-form-item>
                </a-form>
            </a-tab-pane>
        </a-tabs>
    </div>
</div>
</template>
<script>
    export default {
        name: "Login",
        data() {
            return {
                loginForm: this.$form.createForm(this, { name: 'login' }),
                registerForm: this.$form.createForm(this, { name: 'registerForm' }),
            }
        },
        methods: {
            login(e) {
                e.preventDefault()
                this.loginForm.validateFields((err, values) => {
                    if (!err) {
                        this.$api.login({
                            username: values.username,
                            password: values.password,
                        }).then((res) => {
                            this.$message.success(res.resultInfo);
                            sessionStorage.setItem("userId",res.data.uid);
                            this.$store.commit('setUser', res.data)
                            this.$router.push('/home').catch(err => {err})
                        }).catch((res) => {
                            this.$message.error(res.resultInfo)
                        })
                    }
                })
            },
            register(e) {
                e.preventDefault()
                this.registerForm.validateFields((err, values) => {
                    if (!err) {
                        this.$api.register({
                            username: values.username,
                            password: values.password,
                            phone: values.phone
                        }).then((res) => {
                            this.$message.success(res.resultInfo)
                            this.$router.push('/home');
                        }).catch((res) => {
                            this.$message.error(res.resultInfo)
                        })
                    }
                })
            }
        },
    }
</script>
<style>
</style>
