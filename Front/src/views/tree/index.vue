<!-- <template>
  <div class="app-container">
    <el-input v-model="filterText" placeholder="Filter keyword" style="margin-bottom:30px;" />

    <el-tree
      ref="tree2"
      :data="data2"
      :props="defaultProps"
      :filter-node-method="filterNode"
      class="filter-tree"
      default-expand-all
    />

  </div>
</template>

<script>
export default {

  data() {
    return {
      filterText: '',
      data2: [{
        id: 1,
        label: 'Level one 1',
        children: [{
          id: 4,
          label: 'Level two 1-1',
          children: [{
            id: 9,
            label: 'Level three 1-1-1'
          }, {
            id: 10,
            label: 'Level three 1-1-2'
          }]
        }]
      }, {
        id: 2,
        label: 'Level one 2',
        children: [{
          id: 5,
          label: 'Level two 2-1'
        }, {
          id: 6,
          label: 'Level two 2-2'
        }]
      }, {
        id: 3,
        label: 'Level one 3',
        children: [{
          id: 7,
          label: 'Level two 3-1'
        }, {
          id: 8,
          label: 'Level two 3-2'
        }]
      }],
      defaultProps: {
        children: 'children',
        label: 'label'
      }
    }
  },
  watch: {
    filterText(val) {
      this.$refs.tree2.filter(val)
    }
  },

  methods: {
    filterNode(value, data) {
      if (!value) return true
      return data.label.indexOf(value) !== -1
    }
  }
}
</script>
 -->



 
 <!-- <template>
  <div class = "dialog">
   <el-button type="primary" @click="dialogVisible.splice(0, 1, true);">打开嵌套表单的 Dialog</el-button>
    <el-dialog title="收货地址" :visible.sync = "dialogVisible[0]">
      <el-form :model="form" :rules = "rules" :ref="ruleFormName[0]" class="demo-ruleForm">

          <el-form-item label="活动编号" :label-width="formLabelWidth" prop = "id">
            <el-input v-model="form.id" autocomplete="off" multiple placeholder="请输入"></el-input>
          </el-form-item>


        <el-form-item label="活动名称" :label-width="formLabelWidth" prop = "name">
          <el-input v-model="form.name" autocomplete="off"></el-input>
        </el-form-item>

        <el-form-item label="活动区域" :label-width="formLabelWidth" prop = "region">
          <el-select v-model="form.region" multiple placeholder="请选择活动区域" filterable allow-create default-first-option>
              <el-option
                v-for = "item in this.form.region"
                :key = "item"
                :label = "item"
                :value = "item"
              />
          </el-select>
        </el-form-item>

      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button @click="cancel(0)">取 消</el-button>
        <el-button @click="reset(0)">重 置</el-button>
        <el-button type="primary" @click = "next(0)">下 一 步</el-button>
      </div>

      <el-steps :active="active" finish-status="success">
          <el-step title="步骤 1"></el-step>
          <el-step title="步骤 2"></el-step>
          <el-step title="步骤 3"></el-step>
      </el-steps>

    </el-dialog>

    <el-dialog title="活动描述" :visible.sync="dialogVisible[1]">
         <el-form :model="form" :rules = "rules" :ref="ruleFormName[1]" class="demo-ruleForm">

        
             <el-form-item label="活动描述" :label-width="formLabelWidth" prop = "describe">
               <el-input v-model="form.describe" autocomplete="off"></el-input>
             </el-form-item>


         </el-form>



         <div slot="footer" class="dialog-footer">
           <el-button @click="back(1)">上 一 步</el-button>
           <el-button @click="reset(1)">重 置</el-button>
           <el-button type="primary" @click = "submit(1)">确认</el-button>
         </div>

         <el-steps :active="active" finish-status="success">
             <el-step title="步骤 1"></el-step>
             <el-step title="步骤 2"></el-step>
             <el-step title="步骤 3"></el-step>
         </el-steps>

       </el-dialog> 


       
   </div>
 </template>


<script>
import { FLOWPREDICATE_TYPES } from '@babel/types';



export default {
  data() {
    return {
      active: 0,
      dialogVisible: [false, false],
      ruleFormName: ["form1", "form2"],
      formLabelWidth: '120px',
      form: {
        id: '',
        name: '',
        region: '',
        describe: '',
        date2: '',
        delivery: false,
        type: [],
        resource: '',
        desc: ''
      },
      rules: {
        id: [{ required: true, message: '请输入', trigger: 'blur' }],
        name: [{ required: true, message: '请输入', trigger: 'blur'}],
        region: [{ required: false, message: '请输入', trigger: 'blur' }],
        describe: [{ required: true, message: '请输入', trigger: 'blur' }]
      }
    }
  },
  methods: {
    next(index) {
      this.$refs[this.ruleFormName[index]].validate((valid) => {
        if (valid) {
          alert('next!');
          this.dialogVisible.splice(index, 1, false);
          this.dialogVisible.splice(index + 1, 1, true);
          this.active += 1;
        } else {
          console.log('error submit!!');
          return false;
        }
      });
    }, 
    back(index) {
      this.reset(index);
      this.dialogVisible.splice(index, 1, false);
      this.dialogVisible.splice(index - 1, 1, true);
      this.active -= 1;
    },
    submit(index) {
        this.$refs[this.ruleFormName[index]].validate((valid) => {
        if (valid) {
          alert('submit');
          for (var i = 0; i <= index; i++){
            this.reset(i);
          }
          this.dialogVisible.splice(index, 1, false);
        } else {
          console.log('error submit!!');
          return false;
        }
      });
    },

    reset(index) {
      this.$refs[this.ruleFormName[index]].resetFields();
    },
    cancel(index) {
      this.reset(index);
      this.dialogVisible.splice(index, 1, false);
    }
  }
}
</script> -->



<!-- 测试文件上传功能 -->
<template>
  <div class = "file_upload">
    <el-upload
      class="upload-demo"
      action="http://localhost/upload"
      :on-preview="handlePreview"
      :on-remove="handleRemove"
      :before-remove="beforeRemove"
      multiple
      :limit="3"
      :on-exceed="handleExceed"
      :file-list="fileList">
      <el-button size="small" type="primary">点击上传</el-button>
      <div slot="tip" class="el-upload__tip">只能上传jpg/png文件，且不超过500kb</div>
    </el-upload>
  </div>
</template>

<script>
export default {
  data() {
    return {
      fileList: [{ name: 'food.jpeg', url: 'https://fuss10.elemecdn.com/3/63/4e7f3a15429bfda99bce42a18cdd1jpeg.jpeg?imageMogr2/thumbnail/360x360/format/webp/quality/100' }, { name: 'food2.jpeg', url: 'https://fuss10.elemecdn.com/3/63/4e7f3a15429bfda99bce42a18cdd1jpeg.jpeg?imageMogr2/thumbnail/360x360/format/webp/quality/100' }]
    };
  },
  methods: {
    handleRemove(file, fileList) {
      console.log(file, fileList);
    },
    handlePreview(file) {
      console.log(file);
    },
    handleExceed(files, fileList) {
      this.$message.warning(`当前限制选择 3 个文件，本次选择了 ${files.length} 个文件，共选择了 ${files.length + fileList.length} 个文件`);
    },
    beforeRemove(file, fileList) {
      return this.$confirm(`确定移除 ${file.name}？`);
    }
  }
}
</script>