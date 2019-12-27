<template>
    <a-form :form="form" @submit="handleSubmit">
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
                message: 'Please confirm your password!',
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
        <a-form-item v-bind="formItemLayout">
      <span slot="label">
        昵称&nbsp;
        <a-tooltip title="其他人怎么称呼你?">
          <a-icon type="question-circle-o" />
        </a-tooltip>
      </span>
            <a-input
                    v-decorator="[
          'nickname',
          {
            rules: [{ required: true, message: 'Please input your nickname!', whitespace: true }],
          },
        ]"
            />
        </a-form-item>
        <a-form-item v-bind="formItemLayout" label="常住城市">
            <a-cascader
                    v-decorator="[
          'residence',
          {
            initialValue: ['安徽', '合肥', '蜀山区'],
            rules: [
              { type: 'array', required: false, message: 'Please select your habitual residence!' },
            ],
          },
        ]"
                    :options="residences"
            />
        </a-form-item>
        <a-form-item v-bind="formItemLayout" label="手机号">
            <a-input
                    v-decorator="[
          'phone',
          {
            rules: [{ required: false, message: 'Please input your phone number!' }],
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
        <a-form-item v-bind="formItemLayout" label="E-mail">
            <a-input
                    v-decorator="[
          'email',
          {
            rules: [
              {
                type: 'email',
                message: 'The input is not valid E-mail!',
              },
              {
                required: true,
                message: 'Please input your E-mail!',
              },
            ],
          },
        ]"
            />
        </a-form-item>
        <a-form-item v-bind="formItemLayout" label="Website">
            <a-auto-complete
                    v-decorator="['website', { rules: [{ required: false, message: 'Please input website!' }] }]"
                    placeholder="website"
                    @change="handleWebsiteChange"
            >
                <template slot="dataSource">
                    <a-select-option v-for="website in autoCompleteResult" :key="website">
                        {{ website }}
                    </a-select-option>
                </template>
                <a-input />
            </a-auto-complete>
        </a-form-item>
        <a-form-item
                v-bind="formItemLayout"
                label="验证码"
                extra="We must make sure that your are a human."
        >
            <a-row :gutter="8">
                <a-col :span="12">
                    <a-input
                            v-decorator="[
              'captcha',
              { rules: [{ required: true, message: 'Please input the captcha you got!' }] },
            ]"
                    />
                </a-col>
                <a-col :span="12">
                    <a-button>Get captcha</a-button>
                </a-col>
            </a-row>
        </a-form-item>
        <a-form-item v-bind="tailFormItemLayout">
            <a-checkbox v-decorator="['agreement', { valuePropName: 'checked' }]">
                I have read the
                <a href="">
                    agreement
                </a>
            </a-checkbox>
        </a-form-item>
        <a-form-item v-bind="tailFormItemLayout">
            <a-button type="primary" html-type="submit">
                Register
            </a-button>
        </a-form-item>
    </a-form>
</template>

<script>
    const residences = [
        {
            value: 'Anhui',
            label: '安徽',
            children: [
                {
                    value: 'Hefei',
                    label: '合肥',
                    children: [
                        {
                            value: 'Shushan',
                            label: '蜀山区',
                        },
                        {
                            value: 'Gaoxin',
                            label: '高新区',
                        },
                    ],
                },
            ],
        },
        {
            value: 'Jiangsu',
            label: '江苏',
            children: [
                {
                    value: 'Nanjing',
                    label: '南京',
                    children: [
                        {
                            value: 'zhonghuamen',
                            label: '中华门',
                        },
                    ],
                },
            ],
        },
    ];

    export default {
        name:"RegisterSubmit",
        data() {
            return {
                confirmDirty: false,
                residences,
                autoCompleteResult: [],
                formItemLayout: {
                    labelCol: {
                        xs: { span: 24 },
                        sm: { span: 8 },
                    },
                    wrapperCol: {
                        xs: { span: 24 },
                        sm: { span: 16 },
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
            this.form = this.$form.createForm(this, { name: 'register' });
        },
        methods: {
            handleSubmit(e) {
                e.preventDefault();
                this.form.validateFieldsAndScroll((err, values) => {
                    if (!err) {
                        // eslint-disable-next-line no-console
                        console.log('Received values of form: ', values);
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
                    callback('Two passwords that you enter is inconsistent!');
                } else {
                    callback();
                }
            },
            validateToNextPassword(rule, value, callback) {
                const form = this.form;
                if (value && this.confirmDirty) {
                    form.validateFields(['confirm'], { force: true });
                }
                callback();
            },
            handleWebsiteChange(value) {
                let autoCompleteResult;
                if (!value) {
                    autoCompleteResult = [];
                } else {
                    autoCompleteResult = ['.com', '.org', '.net'].map(domain => `${value}${domain}`);
                }
                this.autoCompleteResult = autoCompleteResult;
            },
        },
    };
</script>