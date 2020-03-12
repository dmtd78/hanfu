<template>
    <!-- quill-editor -->
    <quill-editor class="snow"
                  ref="myTextEditor"
                  :content="content"
                  :options="editorOption"
                  @change="onEditorChange($event)">
    </quill-editor>

</template>

<script>
    import hljs from 'highlight.js'
    import 'quill/dist/quill.core.css'
    import 'quill/dist/quill.snow.css'
    import 'quill/dist/quill.bubble.css'
    import {quillEditor,Quill} from 'vue-quill-editor'
    import {container,ImageExtend, QuillWatch} from 'quill-image-extend-module'
    import ImageResize from 'quill-image-resize-module'

    Quill.register('modules/imageResize',ImageResize)

    Quill.register('modules/ImageExtend', ImageExtend)
    let updateUrl = 'http://106.12.61.131:8081/file/upload';

    export default {
        name: "QuillEditorForArticle",
        components: {quillEditor},
        data() {
            return {
                name: 'add forum',
                content: ``,
                editorOption: {
                    theme: 'snow',
                    placeholder: "请输入您想要表达的内容！",
                    modules: {
                        imageResize: {
                            displayStyles: {
                                backgroundColor: "black",
                                border: "none",
                                color: "white"
                            },
                            modules: ["Resize", "DisplaySize", "Toolbar"]
                        },
                        ImageExtend: {
                            loading: true,
                            name: 'file',
                            action: updateUrl,
                            // response 为一个函数用来获取服务器返回的具体图片地址
                            // 例如服务器返回{code: 200; data:{ url: 'baidu.com'}}
                            // 则 return res.data.url
                            response: (res) => {
                                // eslint-disable-next-line no-console
                                console.log(res.data)
                                return res.data.path
                            },
                        },
                        toolbar: {
                            container: container,
                            handlers: {
                                'image': function () {
                                    QuillWatch.emit(this.quill.id)
                                }
                            }
                        }
                        //     [
                        //     ['bold', 'italic', 'underline', 'strike'],
                        //     [{'list': 'ordered'}, {'list': 'bullet'}],
                        //     [{'header': [1, 2, 3, 4, 5, 6, false]}],
                        //     [{'color': []}, {'background': []}],
                        //     [{'font': []}],
                        //     [{'align': []}],
                        //     ['link', 'image'],
                        //     ['clean']
                        // ]
                    }
                }
            }
        },
        methods: {
            // eslint-disable-next-line no-unused-vars
            onEditorChange({editor, html, text}) {
                this.content = html;
                this.$emit('description', this.content)
            }
        },
        computed: {
            contentCode() {
                return hljs.highlightAuto(this.content).value
            },
            editor() {
                return this.$refs.myTextEditor.quill
            }
        },
        mounted() {
            // eslint-disable-next-line no-console
            console.log('this is my editor', this.editor)
        }
    }
</script>

<style lang="scss" scoped>
    .quill-editor,
    .quill-code {

    }

    .quill-editor {
        border-bottom: none;
    }

    .quill-code {
        border: none;
        height: auto;

        > code {
            width: 100%;
            margin: 0;
            padding: 1rem;
            border: 1px solid #ccc;
            border-radius: 0;
            height: 10rem;
            overflow-y: auto;
        }
    }
</style>