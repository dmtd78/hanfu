<template>
    <div>
        <a-descriptions title="基本信息">
            <a-descriptions-item label="姓名">{{user.username}}</a-descriptions-item>
            <a-descriptions-item label="电话">1810000000</a-descriptions-item>
            <a-descriptions-item label="户籍">Hangzhou, Zhejiang</a-descriptions-item>
            <a-descriptions-item label="备注">empty</a-descriptions-item>
            <a-descriptions-item label="地址">
                No. 18, Wantang Road, Xihu District, Hangzhou, Zhejiang, China
            </a-descriptions-item>
        </a-descriptions>
        <a-divider orientation="left">头像</a-divider>
        <a-upload
                name="avatar"
                listType="picture-card"
                class="avatar-uploader"
                :showUploadList="false"
                action="https://www.mocky.io/v2/5cc8019d300000980a055e76"
                :beforeUpload="beforeUpload"
                @change="handleChange"
        >
            <img v-if="imageUrl" :src="imageUrl" class="aa" alt="avatar" />
            <div v-else>
                <a-icon :type="loading ? 'loading' : 'plus'" />
                <div class="ant-upload-text">上传</div>
            </div>
        </a-upload>
        <a-divider orientation="left">我的帖子</a-divider>
        <a-list bordered :dataSource="articleData">
            <a-list-item slot="renderItem" slot-scope="article">
                <a slot="actions">{{$moment(article.date).format('YY-MM-DD HH:mm')}}</a>
                <a slot="actions" >
                    <a-popconfirm title="确定要删除吗?" @confirm="() => onDelete(article.aid)">
                       删除
                    </a-popconfirm>
                </a>
                <a-list-item-meta :descreption="article.title">
                    <a slot="title" @click="gotoDetail(article.aid)">{{article.title}}</a>
                </a-list-item-meta>
            </a-list-item>
        </a-list>
        <a-divider orientation="left">我的收藏</a-divider>
        <a-list bordered :dataSource="iCollectData">
            <a-list-item slot="renderItem" slot-scope="iCollctArticle">
                <a slot="actions">{{$moment(iCollctArticle.date).format('YY-MM-DD HH:mm')}}</a>
                <a slot="actions" >
                    <a-popconfirm title="确定要删除吗?" @confirm="() => deleteCollect(iCollctArticle.aid)">
                        删除
                    </a-popconfirm>
                </a>
                <a-list-item-meta :descreption="iCollctArticle.title">
                    <a slot="title" @click="gotoDetail(iCollctArticle.aid)">{{iCollctArticle.title}}</a>
                </a-list-item-meta>
            </a-list-item>
        </a-list>
    </div>
</template>

<script>
    import {mapGetters} from "vuex";
    // eslint-disable-next-line no-irregular-whitespace
    const userId = JSON.parse(sessionStorage.getItem("userId"));
    let uploadUrl = 'http://106.12.61.131:8081/file/upload';
    function getBase64(img,callback) {
        const reader = new FileReader();
        reader.addEventListener('load', () => callback(reader.result));
        reader.readAsDataURL(img);
        this.$api.uploadImg(uploadUrl);
    }
    export default {
        data() {
            return {
                articleData: [],
                iCollectData:[],
                loading: false,
                imageUrl: '',
            };
        },
        computed: {
            ...mapGetters({
                isLogin: 'auth/isLogin',
                user: 'auth/user'
            }),
        },
        name: "PersonalCenter",
        mounted() {
            this.getArticleData(res => {
                // eslint-disable-next-line no-console
                console.log('getArticleData:' + res.data);
                this.articleData = res.data.list;
            });
            this.getICollectArticleData(res => {
                // eslint-disable-next-line no-console
                console.log('i collect article data list:' + res.data);
                this.iCollectData = res.data;
            });
        },
        methods: {
            handleChange(info) {
                if (info.file.status === 'uploading') {
                    this.loading = true;
                    return;
                }
                if (info.file.status === 'done') {
                    // Get this url from response in real world.
                    getBase64(info.file.originFileObj, imageUrl => {
                        this.imageUrl = imageUrl;
                        this.loading = false;
                    });
                }
            },
            beforeUpload(file) {
                const isJpgOrPng = file.type === 'image/jpeg' || file.type === 'image/png';
                if (!isJpgOrPng) {
                    this.$message.error('只能上传 JPG 格式的图片!');
                }
                const isLt2M = file.size / 1024 / 1024 < 2;
                if (!isLt2M) {
                    this.$message.error('必须小于2MB!');
                }
                return isJpgOrPng && isLt2M;
            },
            onDelete(articleId) {
                let params = {
                    articleId :articleId
                };
                this.$api.deleteArticle(params).then((res) => {
                    if (res.resultCode == 0) {
                        this.$notification.open({
                            message: '温馨提醒',
                            description: '已删除',
                            icon: <a-icon type="smile" style="color: #108ee9" />,
                    })}
                })
                const dataSource = [...this.articleData];
                this.articleData = dataSource.filter(item => item.aid !== articleId);
            },
            deleteCollect(articleId){
                let params = {
                    articleId :articleId,
                    userId :userId
                };
                this.$api.deleteCollect(params).then((res) => {
                    if (res.resultCode == 0) {
                        this.$notification.open({
                            message: '温馨提醒',
                            description: '已删除',
                            icon: <a-icon type="smile" style="color: #108ee9" />,
                    })}
                })
                const dataSource = [...this.iCollectData];
                this.iCollectData = dataSource.filter(item => item.aid !== articleId);
            },
            getArticleData(callback) {
                let values = {
                    currentPage: 1,
                    userId: userId,
                };
                this.$api.getArticleList(values).then((res) => {
                    if (res.resultCode == 0) {
                        callback(res)
                    }
                })
            },
            getICollectArticleData(callback) {
                let values = {
                    currentPage: 1,
                    userId: userId,
                };
                this.$api.getICollectArticleData(values).then((res) => {
                    if (res.resultCode == 0) {
                        callback(res)
                    }
                })
            },
        }
    }
</script>

<style scoped>
    .avatar-uploader > .ant-upload {
        width: 128px;
        height: 128px;
    }
    .ant-upload-select-picture-card i {
        font-size: 32px;
        color: #999;
    }

    .ant-upload-select-picture-card .ant-upload-text {
        margin-top: 8px;
        color: #666;
    }
    .aa{
        width: 200px;
    }
</style>