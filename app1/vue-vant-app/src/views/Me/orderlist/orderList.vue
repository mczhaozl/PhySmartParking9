<template>
    <div>
        <van-nav-bar
                title="订单列表"
                left-text=""
                :fixed="fixed"
                left-arrow
                plus
                @click-left="onClickLeft"
        />

        <!-- <van-icon name="plus" />
        <van-icon name="arrow-left" /> -->
        <div class="van-card">
            <van-list  @load="ontLoad">
                <van-cell v-for="(item,index) in orderList" :key="index">
                    <van-col span="24">【{{item.orderId}}】{{item.carNumber}}</van-col>
                </van-cell>
            </van-list>
        </div>
        
    </div>
    
</template>

<script>
    import Vue from 'vue';
    import {Grid, GridItem} from 'vant';
    import {Col, Row} from 'vant';
    import {Image as VanImage} from 'vant';
    import {Cell, CellGroup} from 'vant';
    import {getAction, getFileAccessHttpUrl} from '@/api/manage';
    import { Toast } from 'vant';

    Vue.use(Toast);

    export default {
        name: "orderList",
        data() {
            return {
               orderList:[],
               pageNo: 1,
               pageSize: 5,
                
            };
           
        },
        computed: {},
        components: {
           
        },
        created() {
          
        },
        mounted() {

        },
        methods: {
            ontLoad() {
                Toast.loading({
                    message: '加载中...',
                    forbidClick: true
                });
                let that = this
                getAction(window._CONFIG['domianURL'] + '/parking/pkOrder/list', {
                        'userId': '',
                        'username': localStorage.realname,
                        
                        'pageNo': that.pageTNo,
                        'pageSize': that.pageTSize
                    }
                ).then(data => {
                    // 加载状态结束
                    that.tloading = false;
                    if (data.result.records != null && data.result.records.length > 0) {
                        for (let i in data.result.records) {
                            that.orderList.push(data.result.records[i])
                        }
                        that.pageTNo++
                    } else {
                        that.tfinished = true
                        if (that.pageTNo == 1) {
                            that.isTEmpty = true
                        }
                    }
                    Toast.clear();
                })
            },
            onClickLeft(){
                 this.$router.push("/me")
            }
        }
    };
</script>

<style lang="scss">
      .van-nav-bar__title{
        color: #3282F9 !important;
    }
</style>