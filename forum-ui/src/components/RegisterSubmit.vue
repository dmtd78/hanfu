<template>
    <a-tabs defaultActiveKey="1" @change="callback">
        <a-tab-pane tab="注册" key="1">
            <a-form :form="form" @submit="handleSubmit">
                <a-form-item v-bind="formItemLayout">
                  <span slot="label">
                    登录账号&nbsp;
                    <a-tooltip title="如果输入了手机号，也可以用手机号登录！">
                      <a-icon type="question-circle-o"/>
                    </a-tooltip>
                  </span>
                    <a-input
                            v-decorator="[
                                  'username',
                                  {
                                    rules: [{ required: true, message: '请输入你的登录账号！', whitespace: true }],
                                  },
                                ]"
                    />
                </a-form-item>
                <a-form-item v-bind="formItemLayout" label="密码">
                    <a-input v-decorator="['password',
                                      {
                                        rules: [
                                          {
                                            required: true,
                                            message: '请输入密码！',
                                          },
                                          {
                                            validator: validateToNextPassword,
                                          },
                                        ],
                                      },
                                    ]"
                             type="password"
                    />
                </a-form-item>
                <a-form-item v-bind="formItemLayout" label="确认密码">
                    <a-input v-decorator="[
                                              'confirm',
                                              {
                                                rules: [
                                                  {
                                                    required: true,
                                                    message: '请确认密码！',
                                                  },
                                                  {
                                                    validator: compareToFirstPassword,
                                                  },
                                                ],
                                              },
                                            ]"
                             type="password"
                             @blur="handleConfirmBlur"
                    />
                </a-form-item>
                <a-form-item v-bind="formItemLayout" label="手机号">
                    <a-input
                            v-decorator="[
                                              'phone',
                                              {
                                                rules: [{ required: false, message: '请输入你的手机号！' }],
                                              },
                                            ]"
                            style="width: 100%"
                    >
                        <a-select
                                slot="addonBefore"
                                v-decorator="['prefix', { initialValue: '86' }]"
                                style="width: 70px"
                        >
                            <a-select-option value="86">
                                +86
                            </a-select-option>
                            <a-select-option value="87">
                                +87
                            </a-select-option>
                        </a-select>
                    </a-input>
                </a-form-item>
                <a-form-item v-bind="tailFormItemLayout">
                    <a-button type="primary" html-type="submit">
                        注册
                    </a-button>
                </a-form-item>
            </a-form>

        </a-tab-pane>
        <a-tab-pane tab="登录" key="2" forceRender>
            <LoginForm></LoginForm>
        </a-tab-pane>
    </a-tabs>
</template>

<script>
    import LoginForm from "./LoginForm";

    export default {
        name: "RegisterSubmit",
        components: {LoginForm},
        data() {
            return {
                closable: true,
                confirmDirty: false,
                autoCompleteResult: [],
                formItemLayout: {
                    labelCol: {
                        xs: {span: 24},
                        sm: {span: 8},
                    },
                    wrapperCol: {
                        xs: {span: 24},
                        sm: {span: 16},
                    },
                },
                tailFormItemLayout: {
                    wrapperCol: {
                        xs: {
                            span: 24,
                            offset: 0,
                        },
                        sm: {
                            span: 16,
                            offset: 8,
                        },
                    },
                },
            };
        },
        beforeCreate() {
            this.form = this.$form.createForm(this, {name: 'register'});
        },
        methods: {
            handleSubmit(e) {
                e.preventDefault();
                this.form.validateFieldsAndScroll((err, values) => {
                    if (!err) {
                        // eslint-disable-next-line no-console
                        console.log('Received values of form: ', values);
                        this.$api.post('http://106.12.61.131:8081/user/regist?username=' + values.username + '&password=' + values.password, values);
                    }
                });
            },
            handleConfirmBlur(e) {
                const value = e.target.value;
                this.confirmDirty = this.confirmDirty || !!value;
            },
            compareToFirstPassword(rule, value, callback) {
                const form = this.form;
                if (value && value !== form.getFieldValue('password')) {
                    callback('两个密码要一致！');
                } else {
                    callback();
                }
            },
            validateToNextPassword(rule, value, callback) {
                const form = this.form;
                if (value && this.confirmDirty) {
                    form.validateFields(['confirm'], {force: true});
                }
                callback();
            },
            callback(key) {
                // eslint-disable-next-line no-console
                console.log(key);
            },
        },
    };
</script>