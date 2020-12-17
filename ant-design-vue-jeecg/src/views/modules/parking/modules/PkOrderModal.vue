<template>
  <j-modal
    :title="title"
    :width="width"
    :visible="visible"
    :confirmLoading="confirmLoading"
    switchFullscreen
    @ok="handleOk"
    @cancel="handleCancel"
    cancelText="关闭">
    <a-spin :spinning="confirmLoading">
      <a-form :form="form">

        <a-form-item label="车位编号" :labelCol="labelCol" :wrapperCol="wrapperCol">
          <a-input v-decorator="['lotNum']" placeholder="请输入车牌号"></a-input>
        </a-form-item>     
        <a-form-item label="车牌号" :labelCol="labelCol" :wrapperCol="wrapperCol">
          <a-input v-decorator="['carNumber']" placeholder="请输入车牌号"></a-input>
        </a-form-item>
        <a-form-item label="停车时间" :labelCol="labelCol" :wrapperCol="wrapperCol">
           <j-date  v-decorator="['parkTime']" placeholder="请输入停车时间" :showTime="true" dateFormat="YYYY-MM-DD HH:mm:ss"/>
        </a-form-item>
        <a-form-item label="离开时间" :labelCol="labelCol" :wrapperCol="wrapperCol">
          <j-date  v-decorator="['leaveTime']" placeholder="请输入离开时间" :showTime="true" dateFormat="YYYY-MM-DD HH:mm:ss"/>
        </a-form-item>
        <a-form-item label="订单状态" :labelCol="labelCol" :wrapperCol="wrapperCol">
           <j-dict-select-tag placeholder="请选择订单状态" :triggerChange="true" dictCode="order_status" v-decorator="['status']"/>
        </a-form-item>
        
        
       

      </a-form>
    </a-spin>
  </j-modal>
</template>

<script>

  import { httpAction } from '@/api/manage'
  import pick from 'lodash.pick'
  import { validateDuplicateValue } from '@/utils/util'
  import JDictSelectTag from '@/components/dict/JDictSelectTag'
  import {getUserList } from '@/api/api'
  import JDate from '@/components/jeecg/JDate'
  import JSelectUserByDep from '@/components/jeecgbiz/JSelectUserByDep'

  export default {
    name: "PkOrderModal",
    components: { 
      JDate,
      JDictSelectTag,
      JSelectUserByDep
      
   },
    data () {
      return {
        form: this.$form.createForm(this),
        title:"操作",
        width:800,
        visible: false,
        model: {},
        labelCol: {
          xs: { span: 24 },
          sm: { span: 5 },
        },
        wrapperCol: {
          xs: { span: 24 },
          sm: { span: 16 },
        },
        confirmLoading: false,
        validatorRules: {
        },
        url: {
          add: "/parking/pkOrder/add",
          edit: "/parking/pkOrder/edit",
        },
        selectedUser:[],
      }
    },
    created () {
    },
    methods: {
      add () {
        this.edit({});
      },
      edit (record) {
        this.form.resetFields();
        this.model = Object.assign({}, record);
        this.visible = true;

        this.$nextTick(() => {
          this.form.setFieldsValue(pick(this.model,'carNumber','type','carColor','userId','username','motor','remarks'))
        })
      },
      close () {
        this.$emit('close');
        this.visible = false;
      },
      handleOk () {
        const that = this;
        // 触发表单验证
        this.form.validateFields((err, values) => {
          if (!err) {
            that.confirmLoading = true;
            let httpurl = '';
            let method = '';
            if(!this.model.id){
              httpurl+=this.url.add;
              method = 'post';
            }else{
              httpurl+=this.url.edit;
               method = 'put';
            }
            let formData = Object.assign(this.model, values);
            console.log("表单提交数据",formData)
            httpAction(httpurl,formData,method).then((res)=>{
              if(res.success){
                that.$message.success(res.message);
                that.$emit('ok');
              }else{
                that.$message.warning(res.message);
              }
            }).finally(() => {
              that.confirmLoading = false;
              that.close();
            })
          }
         
        })
      },
      handleCancel () {
        this.close()
      },
      popupCallback(row){
        this.form.setFieldsValue(pick(row,'carNumber','type','carColor','userId','username','motor','remarks'))
      },

      
    }
  }
</script>