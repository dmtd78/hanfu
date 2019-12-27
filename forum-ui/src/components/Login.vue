<template>
    <div>
        <a-button type="primary" @click="showModal">
            登录
        </a-button>
        <create-login-form
                ref="loginForm"
                :visible="visible"
                @cancel="handleCancel"
                @create="handleCreate"
        />
    </div>
</template>

<script>
    const CreateLoginForm = {
        props: ['visible'],
        beforeCreate() {
            this.form = this.$form.createForm(this, { name: 'login form_in_modal' });
        },
        template: `
    <a-modal
      :visible="visible"
      title='登录'
      okText='登录'
      @cancel="() => { $emit('cancel') }"
      @ok="() => { $emit('create') }"
    >
      <a-form layout='vertical' :form="form">
        <a-form-item label='登录账号'>
          <a-input
            v-decorator="[
              'title',
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
      </a-form>
    </a-modal>
  `,
    };

    export default {
        name:"Login",
        components: { CreateLoginForm },
        data() {
            return {
                visible: false,
            };
        },
        methods: {
            showModal() {
                this.visible = true;
            },
            handleCancel() {
                this.visible = false;
            },
            handleCreate() {
                const form = this.$refs.loginForm.form;
                form.validateFields((err, values) => {
                    if (err) {
                        return;
                    }
                    // eslint-disable-next-line no-console
                    console.log('Received values of form: ', values);
                    this.$api.post('http://106.12.61.131:8081/user/login?username='+values.username+'&password='+values.password,values,'success','failure');
                    form.resetFields();
                    this.visible = false;
                });
            },
        },
    };
</script>