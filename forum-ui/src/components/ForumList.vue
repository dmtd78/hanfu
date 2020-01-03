<template>
    <a-list class="demo-loadmore-list" :loading="loading" itemLayout="horizontal" :dataSource="data">
        <div
                v-if="showLoadingMore"
                slot="loadMore"
                :style="{ textAlign: 'center', marginTop: '12px', height: '32px', lineHeight: '32px' }"
        >
            <a-spin v-if="loadingMore" />
            <a-button v-else @click="onLoadMore">加载更多</a-button>
        </div>

        <a-list-item slot="renderItem" slot-scope="item">
            <a slot="actions">{{$moment(item.date).format('YYYY-MM-DD')}}</a>
            <a-list-item-meta :description="item.content" >
                <a slot="title" @click="gotoDetail(item.aid)">{{item.title}}</a>
                <a-avatar slot="avatar" src="https://zos.alipayobjects.com/rmsportal/ODTLcjxAfvqbxHnVXCYX.png"
                />
            </a-list-item-meta>
        </a-list-item>
    </a-list>
</template>
<script>
    import reqwest from 'reqwest';
    import axios from 'axios';

    const fakeDataUrl = 'http://106.12.61.131:8081/article/list?currentPage=1&type=1';

    export default {
        name:"ForumList",
        data() {
            return {
                loading: true,
                loadingMore: false,
                showLoadingMore: true,
                data: [],
            };
        },
        mounted() {
            this.getData(res => {
                this.loading = false;
                this.data = res.data.list;
            });
        },
        methods: {
            gotoDetail(aid){
                axios.post('/article/details?aid='+aid,{aid,
                    xhrFields: {
                        withCredentials: true
                    },
                }).then((res) => {
                    // eslint-disable-next-line no-console
                    console.log(res)
                    if (res.data.resultCode == 0) {
                        this.articleData = res.data.data;
                        this.$message.success(
                            "标题："+this.articleData.title+"内容：\n"+this.articleData.content,
                            10,
                        );
                    } else {
                        this.$message.failure(
                            res.data.resultInfo,
                            10,
                        );
                    }
                })
            },
            getData(callback) {
                reqwest({
                    url: fakeDataUrl,
                    type: 'json',
                    method: 'get',
                    contentType: 'application/json',
                    success: res => {
                        callback(res);
                    },
                });
            },
            onLoadMore() {
                this.loadingMore = true;
                this.getData(res => {
                    this.data = this.data.concat(res.list);
                    this.loadingMore = false;
                    this.$nextTick(() => {
                        window.dispatchEvent(new Event('resize'));
                    });
                });
            },
        },
    };
</script>
<style>
    .demo-loadmore-list {
        min-height: 350px;
    }
</style>
