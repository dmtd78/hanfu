<template>
    <md-card>
        <md-card-media>
            <div class="quill-editor-example">
                <!-- quill-editor -->
                <quill-editor class="editor-example bubble"
                              ref="myTextEditor"
                              :content="content"
                              :options="editorOption"
                              @change="onEditorChange($event)">
                </quill-editor>
                <div class="quill-code" v-show="false">
                    <code class="hljs xml" name="description" v-html="contentCode"></code>
                </div>
            </div>
        </md-card-media>
    </md-card>
</template>

<script>
    import hljs from 'highlight.js'
    export default {
        name:"QuillEditorForArticle",
        data() {
            return {
                name: '02-example',
                content: ``,
                editorOption: {
                    theme: 'bubble',
                    placeholder: "请输入您想要表达的内容！",
                    modules: {
                        toolbar: [
                            ['bold', 'italic', 'underline', 'strike'],
                            [{ 'list': 'ordered'}, { 'list': 'bullet' }],
                            [{ 'header': [1, 2, 3, 4, 5, 6, false] }],
                            [{ 'color': [] }, { 'background': [] }],
                            [{ 'font': [] }],
                            [{ 'align': [] }],
                            ['link', 'image'],
                            ['clean']
                        ]
                    }
                }
            }
        },
        methods: {
            // eslint-disable-next-line no-unused-vars
            onEditorChange({ editor, html, text }) {
                this.content = html;
                this.$emit('description',this.content)
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
            console.log('this is my editor, example 2', this.editor)
        }
    }
</script>

<style lang="scss" scoped>
    .quill-editor,
    .quill-code {
        height: 20rem;
    }
    .quill-editor {
        border: 1px solid #ccc;
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