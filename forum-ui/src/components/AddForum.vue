<template>
    <div>
        <a-button type="primary" @click="showModal">
            发帖
        </a-button>
        <collection-create-form
                ref="collectionForm"
                :visible="visible"
                @cancel="handleCancel"
                @create="handleCreate"
        />
    </div>
</template>

<script>
    const CollectionCreateForm = {
        props: ['visible'],
        beforeCreate() {
            this.form = this.$form.createForm(this, {name: 'form_in_modal'});
        },
        template: `
    <a-modal
      :visible="visible"
      title='创建一个新帖'
      okText='创建'
      @cancel="() => { $emit('cancel') }"
      @ok="() => { $emit('create') }"
    >
      <a-form layout='vertical' :form="form">
        <a-form-item label='标题'>
          <a-input
            v-decorator="[
              'title',
              {
                rules: [{ required: true, message: '请输入帖子标题！' }],
              }
            ]"
          />
        </a-form-item>
        <a-form-item label='内容'>
          <a-input
            type='textarea'
            v-decorator="['description']"
          />
        </a-form-item>
        <a-form-item class='collection-create-form_last-form-item'>
          <a-radio-group
            v-decorator="[
              'modifier',
              {
                initialValue: 'private',
              }
            ]"
          >
              <a-radio value='public'>公开</a-radio>
              <a-radio value='private'>私有</a-radio>
            </a-radio-group>
        </a-form-item>
      </a-form>
    </a-modal>
  `,
    };
    import axios from 'axios';
    // eslint-disable-next-line no-irregular-whitespace
    const userId = JSON.parse(sessionStorage.getItem("userId"));
    export default {
        name: "AddForum",
        components: {CollectionCreateForm},
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
                const form = this.$refs.collectionForm.form;
                form.validateFields((err, values) => {
                    if (err) {
                        return;
                    }
                    // eslint-disable-next-line no-console
                    console.log('Received values of form: ', values);
                    let myName = JSON.parse(sessionStorage.getItem("userId"));
                    // eslint-disable-next-line no-console
                    console.log(myName);
                    axios.post('/article/add?title=' + values.title + '&description=' + values.description + '&userId=' + userId, {
                        values,
                        xhrFields: {
                            withCredentials: true
                        },
                    }).then((res) => {
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
                    form.resetFields();
                    this.visible = false;
                });
            },
        },
    };
</script>