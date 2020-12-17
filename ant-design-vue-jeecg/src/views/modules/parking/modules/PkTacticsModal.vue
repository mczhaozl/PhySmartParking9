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

        <a-form-item label="开始时间" :labelCol="labelCol" :wrapperCol="wrapperCol">
          <!-- <a-input v-decorator="['startTime']" placeholder="请输入开始时间"></a-input> -->
          <j-date  v-decorator="['startTime']" placeholder="请输入开始时间" :showTime="true" dateFormat="YYYY-MM-DD HH:mm:ss"/>
        </a-form-item>
        <a-form-item label="结束时间" :labelCol="labelCol" :wrapperCol="wrapperCol">
          <!-- <a-input v-decorator="['endTime']" placeholder="请输入结束时间"></a-input> -->
          <j-date  v-decorator="['endTime']" placeholder="请输入结束时间" :showTime="true" dateFormat="YYYY-MM-DD HH:mm:ss"/>
        </a-form-item>
        <a-form-item label="所属区域" :labelCol="labelCol" :wrapperCol="wrapperCol">
          <a-input v-decorator="['area']" placeholder="请输入所属区域"></a-input>
        </a-form-item>
      <!--  <a-form-item label="缩略图" :labelCol="labelCol" :wrapperCol="wrapperCol">
          <j-image-upload v-decorator="['thumbnail']"></j-image-upload>
        </a-form-item>-->
       <a-form-item label="类型" :labelCol="labelCol" :wrapperCol="wrapperCol">
           <j-dict-select-tag placeholder="请选择停车场类型" :triggerChange="true" dictCode="parking_lot_type" v-decorator="['type']"/>
        </a-form-item>
      <a-form-item label="价格（元/小时）" :labelCol="labelCol" :wrapperCol="wrapperCol">
           <a-input v-decorator="['price']"  placeholder="单位（元/小时）"></a-input>
        </a-form-item>

      </a-form>
    </a-spin>
  </j-modal>
</template>

<script>

  import { httpAction } from '@/api/manage'
  import pick from 'lodash.pick'
  import { validateDuplicateValue } from '@/utils/util'
  import JEditor from '@/components/jeecg/JEditor'
  import JImageUpload from '@/components/jeecg/JImageUpload'
   import JDate from '@/components/jeecg/JDate'

  export default {
    name: "PkTacticsModal",
    components: {
      JEditor,
      JImageUpload,
      JDate
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
          add: "/parking/PkTactics/add",
          edit: "/parking/PkTactics/edit",
        },
        validatorRules: {
          startTime: {
            rules: [{ required: true, message: '请输入开始时间'}]
          },
          endTime: {
            rules: [{ required: true, message: '请输入结束时间'}]
          },
          type: {
            rules: [{ required: true, message: '请选择停车类型'}]
          },
          area: {
            rules: [{ required: true, message: '请输入区域'}]
          },
          price: {
            rules: [{ required: true, message: '请输入价格'}]
          },
        }
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
          this.form.setFieldsValue(pick(this.model,'startTime','endTime','area','type','price'))
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
        this.form.setFieldsValue(pick(row,'startTime','endTime','area','type','price'))
      },

      
    }
  }
</script>