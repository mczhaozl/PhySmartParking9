<template>
    <div>
        <van-nav-bar
                title="车辆列表"
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
                <van-cell  v-for="(item,index) in carList" :key="index"  >
                    <van-col   span="24">【{{item.type_dictText}}】{{item.carNumber}}</van-col>
                    <template #right-icon>
                        <van-icon name="close" @click="delCar(item.id)" class="close-icon" />
                    </template>
                </van-cell>
            </van-list>
        </div>
        <div class="am-margin-top-xl" style="margin: 16px;"> 
          <van-button round block  icon="plus" type="primary" size="large" to="/me/mycar">新增车辆</van-button>
        </div>
    </div>
    
</template>

<script>
    import Vue from 'vue';
    import {Grid, GridItem} from 'vant';
    import {Col, Row} from 'vant';
    import {Image as VanImage} from 'vant';
    import {Cell, CellGroup} from 'vant';
    import {getAction, deleteAction} from '@/api/manage';
    import { Toast } from 'vant';
    import { Dialog } from 'vant';

    Vue.use(Toast);

    export default {
        name: "carList",
        data() {
            return {
               carList:[],
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
                // Toast.loading({
                //     message: '加载中...',
                //     forbidClick: true
                // });
                
                this.getCarList();
                
            },
            onClickLeft(){
                 this.$router.push("/me")
            },

            delCar(id){
                console.log(id);
                Dialog.confirm({
                    title: '车辆删除',
                    message: '确认删除该车辆吗？',
                    })
                    .then(() => {
                        deleteAction(window._CONFIG['domianURL'] + '/parking/pkCar/delete', {
                            'id': id,
                            }
                        ).then(data => {
                            this.getCarList();
                            Toast(data.message);
                        })
                    })
                    .catch(() => {
                    });

            },

            getCarList(){
                let that = this
                getAction(window._CONFIG['domianURL'] + '/parking/pkCar/list', {
                        // 'userId': '',
                        'username': "admin",
                        
                        'pageNo': 1,
                        'pageSize': that.pageTSize
                    }
                ).then(data => {
                    // 加载状态结束
                    that.tloading = false;
                    if (data.result.records != null && data.result.records.length > 0) {
                         that.carList=[];
                        for (let i in data.result.records) {
                            that.carList.push(data.result.records[i])
                        }
                        // that.pageNo++
                    } else {
                        that.tfinished = true
                        if (that.pageNo == 1) {
                            that.isTEmpty = true
                        }
                    }
                })
            }

        }
    };
</script>

<style lang="scss">
      .van-nav-bar__title{
        color: #3282F9 !important;
    }
    .close-icon {
    font-size: 16px;
    line-height: inherit;
  }
</style>