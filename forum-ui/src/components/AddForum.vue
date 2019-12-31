<template>
    <div>
        <a-button type="primary" @click="showModal">
            发帖
        </a-button>
        <a-modal
                :visible="visible"
                title='创建一个新帖'
                okText='创建'
                @ok="handleCreate"
                @cancel="handleCancel"
        >
            <a-form layout='vertical' :form="form" ref="createArticleForm">
                <a-form-item label='标题'>
                    <a-input v-decorator="[ 'title', { rules: [{ required: true, message: '请输入帖子标题！' }], } ]"
                    />
                </a-form-item>
                <a-form-item label='内容'>
                    <a-input type='textarea' v-decorator="['description', { rules: [{ required: true, message: '请输入帖子内容！' }], }]"
                    />
                </a-form-item>
            </a-form>
        </a-modal>
    </div>
</template>

<script>
    import axios from 'axios';
    // eslint-disable-next-line no-irregular-whitespace
    const userId = JSON.parse(sessionStorage.getItem("userId"));
    export default {
        name: "AddForum",
        data() {
            return {
                visible: false,
            };
        },
        beforeCreate() {
            this.form = this.$form.createForm(this, { name: 'create article form' });
        },
        methods: {
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
                    // eslint-disable-next-line no-console
                    console.log('Received values of form: ', values);
                    axios.post('/article/add', 'title='+values.title+'&description='+values.description+'&userId='+userId).then((res) => {
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