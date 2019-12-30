<template>
        <a-form layout='vertical' :form="form" @submit="handleSubmit" ref="loginForm">
            <a-form-item label='登录账号'>
                <a-input
                        v-decorator="[
              'username',
              {
                rules: [{ required: true, message: '请输入登录账号！' }],
              }
            ]"
                />
            </a-form-item>
            <a-form-item label="密码">
                <a-input v-decorator="['password',
          {
            rules: [{required: true,message: '请输入密码！'}],
          },
        ]"
                         type="password"
                />
            </a-form-item>
            <a-form-item>
                <a-button type="primary" html-type="submit">
                    登录
                </a-button>
            </a-form-item>
        </a-form>
</template>

<script>
    import axios from 'axios';
    export default {
        name:"LoginForm",
        data() {
            return {
                visible: false,
            };
        },
        beforeCreate() {
            this.form = this.$form.createForm(this, { name: 'login form' });
        },
        methods: {
            handleSubmit() {
                const form = this.$refs.loginForm.form;
                form.validateFields((err, values) => {
                    // eslint-disable-next-line no-console
                    console.log('Received err of form: ', err);
                    if (err) {
                        return;
                    }
                    // eslint-disable-next-line no-console
                    console.log('Received values of form: ', values);
                    axios.post('/user/login?username='+values.username+'&password='+values.password,{values,
                        xhrFields: {
                            withCredentials: true
                        },
                    }).then((res) => {
                        // eslint-disable-next-line no-console
                        console.log(res)
                        if (res.data.resultCode == 0) {
                            this.userData = res.data.data;
                            sessionStorage.setItem("userId",this.userData.uid);
                            sessionStorage.setItem("userToken",this.userData.token);
                            this.$message.success(
                                res.data.resultInfo,
                                10,
                            );
                        } else {
                            this.$message.failure(
                                res.data.resultInfo,
                                10,
                            );
                        }
                    })
                });
            },
        },
    };
</script>