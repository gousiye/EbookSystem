<!-- <template>
  <div class="dialog">
    <el-dialog title="上传书籍" :visible.sync="dialogVisible[0]">
      <el-form :model="newBook" :rules="bookRules" :ref="ruleFormName[0]" class="demo-ruleForm">

        <el-form-item label="书名" :label-width="formLabelWidth" prop="name">
          <el-input v-model="newBook.name" autocomplete="off"></el-input>
        </el-form-item>

        <el-form-item label="类别" :label-width="formLabelWidth" prop="classifications">
          <el-select v-model="newBook.classifications" multiple placeholder="请输入类别" filterable default-first-option
            allow-create style="width:100%">
            <el-option v-for="item in this.newBook.classifications" :key="item" :label="item" :value="item" />
          </el-select>
        </el-form-item>

        <el-form-item label="作者" :label-width="formLabelWidth" prop="author">
          <el-input v-model="newBook.author" autocomplete="off"></el-input>
        </el-form-item>

      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button @click="cancel(0)">取 消</el-button>
        <el-button @click="reset(0)">重 置</el-button>
        <el-button type="primary" @click="next(0)">下 一 步</el-button>
      </div>
    </el-dialog>

    <el-dialog title="上传书籍" :visible.sync="dialogVisible[1]">
      <el-form :model="newBook" :rules="bookRules" :ref="ruleFormName[1]" class="demo-ruleForm">


        <el-form-item label="简介" :label-width="formLabelWidth" prop="introduction">
          <el-input v-model="newBook.introduction" autocomplete="off"></el-input>
        </el-form-item>


      </el-form>

      <div slot="footer" class="dialog-footer">
        <el-button @click="back(1)">上 一 步</el-button>
        <el-button @click="reset(1)">重 置</el-button>
        <el-button type="primary" @click="submit(1)">确认</el-button>
      </div>
    </el-dialog>


  </div>
</template>

<script>
export default {
  name: "upload",
  data() {
    return {
      dialogVisible: [false, false],
      ruleFormName: ["form1", "form2"],

      formLabelWidth: '120px',  //对话窗口的长度
      // 新添加的书籍的信息
      newBook: {
        name: null,
        author: null,
        classifications: null,
        suffix: null,
        publisher: null,
        language: null,
        introduction: null
      },
      bookRules: {   //上传书籍对信息的验证
        name: [{ required: true, message: '书名不能为空', trigger: 'blur' }],
        suffix: [{ required: true, message: '文件格式不能为空', trigger: 'blur' }],
        introduction: [{ required: true, message: '简介不能为空', trigger: 'blur' }]
      },
      activate: 0 // 上传进度条的显示
    }
  },
  methods: {
    //显示对话窗口
    show() {
      this.dialogVisible.splice(0, 1, true);
    },
    // 对话窗口的下一步
    next(index) {
      this.$refs[this.ruleFormName[index]].validate((valid) => {
        if (valid) {
          this.dialogVisible.splice(index, 1, false);
          this.dialogVisible.splice(index + 1, 1, true);
        } else {
          console.log('error submit!!');
          return false;
        }
      });
    },
    // 对话窗口的上一步
    back(index) {
      this.reset(index);
      this.dialogVisible.splice(index, 1, false);
      this.dialogVisible.splice(index - 1, 1, true);
    },
    // 对话窗口的提交
    submit(index) {
      this.$refs[this.ruleFormName[index]].validate((valid) => {
        if (valid) {
          for (var i = 0; i <= index; i++) {
            this.reset(i);
          }
          this.dialogVisible.splice(index, 1, false);
        } else {
          console.log('error submit!!');
          return false;
        }
      });
    },
    // 对话窗口的当前页面的重置
    reset(index) {
      this.$refs[this.ruleFormName[index]].resetFields();
    },
    // 对话窗口的取消
    cancel(index) {
      this.reset(index);
      this.dialogVisible.splice(index, 1, false);
    }
  }
}
</script> -->
