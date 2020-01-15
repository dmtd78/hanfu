<template>
    <div>
        <a-button type="primary" @click="showModal">
            发帖
        </a-button>
        <a-modal
                width="80%"
                :visible="visible"
                title='创建一个新帖'
                okText='创建'
                cancelText='取消'
                @ok="handleCreate"
                @cancel="handleCancel"
        >
            <a-form layout='vertical' :form="form">
                <a-form-item>
                    <a-radio-group name="type" :defaultValue="1" v-decorator="['type']">
                        <a-radio-button :value="1">闲聊</a-radio-button>
                        <a-radio-button :value="2">图文赏析</a-radio-button>
                        <a-radio-button :value="3">小白</a-radio-button>
                    </a-radio-group>
                </a-form-item>
                <a-form-item label='标题'>
                    <a-input v-decorator="[ 'title', { rules: [{ required: true, message: '请输入帖子标题！' }], } ]"
                    />
                </a-form-item>
                <a-form-item label='内容'>
                    <a-input v-decorator="['description']" v-model="description" v-show="false"></a-input>
                    <a-tooltip title="注：目前，如果帖子内容包含图片的话，请将图片大小控制在20kb以内，以后升级服务器会无此限制，非常感谢您的谅解！
                    ②为了营造一个轻松的汉服交流空间，帖子内容请慎重考虑，不要涉及一些有影响的不健康的还有国家禁止的内容，非常感谢！">
                        <a-icon type="question-circle-o"/>
                    </a-tooltip>
                    <span style="color: gray">注：①目前，如果帖子内容包含图片的话，请将图片大小控制在20kb以内，以后升级服务器会无此限制，非常感谢您的谅解！<br>
                    ②为了营造一个轻松的汉服交流空间，帖子内容请慎重考虑，不要涉及一些有影响的不健康的还有国家禁止的内容，非常感谢！
                    </span>
                </a-form-item>
                <QuillEditorForArticle v-on:description="showMsgFromChild"></QuillEditorForArticle>
            </a-form>
        </a-modal>
    </div>
</template>

<script>
    import axios from 'axios';
    import qs from 'qs';
    import QuillEditorForArticle from "./QuillEditorForArticle";
    // eslint-disable-next-line no-irregular-whitespace
    const userId = JSON.parse(sessionStorage.getItem("userId"));
    export default {
        name: "AddForum",
        components: {QuillEditorForArticle},
        data() {
            return {
                visible: false,
                userIdShow: false,
                description: ''
            };
        },
        beforeCreate() {
            this.form = this.$form.createForm(this, {name: 'create article form'});
            this.form.userId = userId;
        },
        methods: {
            showMsgFromChild: function (data) {
                this.form.descreption = data;
            },
            showModal() {
                this.visible = true;
            },
            handleCancel() {
                this.visible = false;
            },
            handleCreate() {
                this.form.validateFields((err, values) => {
                    if (err) {
                        return;
                    }
                    let obj = values;
                    obj.userId = userId;
                    obj.description = this.form.descreption;
                    // eslint-disable-next-line no-console
                    console.log('Received values of form: ', obj);
                    axios.post('/article/add', qs.stringify(obj), {
                        xhrFields: {
                            withCredentials: true
                        },
                    },).then((res) => {
                        // eslint-disable-next-line no-console
                        console.log(res)
                        if (res.data.resultCode == 0) {
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
                    this.form.resetFields();
                    this.visible = false;
                });
            },
        },
    };
</script>