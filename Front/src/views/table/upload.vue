<template>
     <div class = "UpDialog">
          <el-dialog title="上传书籍" :visible.sync = "dialogVisible[0]" :v-if="dialogVisible[0]">
            <el-form :model="Ebook" :rules = "bookRules" :ref="ruleFormName[0]" class="demo-ruleForm" close-on-press-escape>

              <!-- 书名 -->
              <el-form-item label="书名" :label-width="formLabelWidth" prop = "bookName">
                <el-input v-model="Ebook.bookName" autocomplete="off" placeholder="请输入书名"></el-input>
              </el-form-item>


              <!-- 通过多选框实现多个类别的同时输入 -->
              <el-form-item label="类别" :label-width="formLabelWidth" prop = "classifications">
                <el-select v-model="Ebook.classifications" multiple placeholder="请输入类别" 
                  filterable default-first-option allow-create  style="width:100%">
                    <el-option
                      v-for = "item in this.Ebook.classifications"
                      :key = "item"
                      :label = "item"
                      :value = "item"
                    />
                </el-select>
              </el-form-item>

              <!-- 作者 -->
              <el-form-item label="作者" :label-width="formLabelWidth" prop = "authorName">
                <el-input v-model="Ebook.authorName" autocomplete="off" placeholder="请输入第一作者"></el-input>
              </el-form-item>

              <!-- 语言 -->
              <el-form-item label="语言" :label-width="formLabelWidth" prop = "language">
                <el-input v-model="Ebook.language" autocomplete="off" placeholder="请输入主要语言"></el-input>
              </el-form-item>

              <!-- 语言 -->
              <el-form-item label="出版社" :label-width="formLabelWidth" prop = "publisher">
                <el-input v-model="Ebook.publisher" autocomplete="off" placeholder="请输入出版社"></el-input>
              </el-form-item>

            </el-form>
            <div slot="footer" class="dialog-footer">
              <el-button @click="cancel(0)">取 消</el-button>
              <el-button @click="reset(0)">重 置</el-button>
              <el-button type="primary" @click = "next(0)">下 一 页</el-button>
            </div>
          </el-dialog>

          <el-dialog title="上传书籍" :visible.sync="dialogVisible[1]" close-on-press-escape>
               <el-form :model="Ebook" :rules = "bookRules" :ref="ruleFormName[1]" class="demo-ruleForm" >

        
                   <el-form-item label="简介" :label-width="formLabelWidth" prop = "introduction" >
                     <el-input v-model="Ebook.introduction" autocomplete="off" 
                     type="textarea" :rows = 10></el-input>
                   </el-form-item>


               </el-form>

               <div slot="footer" class="dialog-footer">
                 <el-button @click="cancel(1)">取 消</el-button>
                 <el-button @click="back(1)">上 一 页</el-button>
                 <el-button @click="reset(1)">重 置</el-button>
                 <el-button type="primary" @click = "next(1)">下 一 步</el-button>
               </div>
          </el-dialog>


          <el-dialog title="上传书籍" :visible.sync="dialogVisible[2]" close-on-press-escape>
            
              <el-form :model="Ebook" :rules = "bookRules" :ref="ruleFormName[2]" class="demo-ruleForm" >
                  <el-form-item label="书籍文件" :label-width="formLabelWidth" prop = "file" >
                    <el-upload class = "upload-demo" 
                    action
                    :http-request="uploadFile"
                    :limit = "fileLimit"
                    :file-list="fileList" 
                    :before-upload="BeforeUpload"
                    :on-exceed="handleExceed"
                    :on-remove="handleRemove"
                    drag>
                      <i class="el-icon-upload"></i>
                      <div class="el-upload__text">将文件拖到此处，或<em>点击上传</em></div>
                      <div class="el-upload__tip" slot="tip">只能上传文本类型的文件</div>
                      <!-- <el-button type = "primary">上传文件 <i class = "el-icon-document-add"></i></el-button> -->
                  </el-upload>
              </el-form-item>


              </el-form>

              <div slot="footer" class="dialog-footer">
                <el-button @click="back(2)">上 一 步</el-button>
                <el-button @click="reset(2)">重 置</el-button>
                <el-button type="primary" @click = "submit(2)">提 交</el-button>
              </div>
        </el-dialog>

    
         </div>
</template>

<script>
import { Add, Upload } from '@/api/table'
export default {
    name: "upload",
    data() {
      return {
        dialogVisible: [false, false, false],
        ruleFormName: ["form1", "form2", "form3"],

          formLabelWidth: '120px',  //对话窗口的长度
          // 新添加的书籍的信息
        Ebook: {
            id: null,
            bookName: null,
            authorName: null,
            classifications: null,
            suffix: null,
            language: null,
            download: 0,
            introduction: null,
            publisher: null,
            file:null
        },
        bookRules: {   //上传书籍对信息的验证
            bookName: [{ required: true, message: '书名不能为空', trigger: 'blur' }],
            authorName: [{ required: true, message: '作者不能为空', trigger: 'blur' }],
            suffix: [{ required: true, message: '文件格式不能为空', trigger: 'blur' }],
            introduction: [{ required: true, message: '简介不能为空', trigger: 'blur' }],
            file: [{ required: true, message: '文件不能为空', trigger: 'blur' }]
        },
        uploadPath: "http://localhost/table/upload",
        newBook: null,
        fileLimit: 1,
        fileList: [],   //上传的文件列表
        fileType:["pdf", "dox", "docx", "txt", "md", "xml", "rtf", "caj"] //支持的文件类型
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
          this.dialogVisible.splice(index, 1, false);
          this.dialogVisible.splice(index - 1, 1, true);
      },
      // 对话窗口的提交，同时将新书传递给后台保存
      submit(index) {                                        
          this.$refs[this.ruleFormName[index]].validate((valid) => {
            if (valid) {
              let fileName = this.fileList[0].name; 
              this.Ebook.suffix = fileName.substring(fileName.lastIndexOf(".") + 1);
              this.Ebook.suffix = this.Ebook.suffix.toLowerCase();
              Add(this.Ebook).then(response => {
                if (response && response.data != null) {
                  this.Ebook.id = response.data.id;
                  // 传递文件要转换类型
                  let params = new FormData()
                  params.append("file", this.Ebook.file);
                  params.append("id", this.Ebook.id);
                  params.append("name", this.Ebook.bookName);
                  // 刷新页面，类别和后缀名
                  Upload(params).then(res => {
                    this.$emit('refresh');
                    this.$message.success("上传成功");
                  })


                }
                else if (response && response.data == null) {
                  this.$message.warning("书籍已存在，只有作者可以更改");
                }
                else {
                  this.$message.error("上传失败");
                }
                for (var i = 0; i <= index; i++) {
                  this.reset(i);
                }       
              });
              this.dialogVisible.splice(index, 1, false);
            }
            else {
                console.log('error submit!!');
                return false;
            }
          });
      },
      //文件上传的函数
      uploadFile(item) {
        this.Ebook.file = item.file;
        this.fileList.push(item.file);

      },
      //上传前检查文件
      BeforeUpload(file) {
        //获取上传文件的后缀名
        let suffix = file.name.substring(file.name.lastIndexOf(".") + 1).toLowerCase();
        if (this.fileType.includes(suffix)) {
          return true;
        }
        else {
          this.$message.error("上传文件格式不正确");
          return false;
        }       
      },
      //超出文件个数
      handleExceed() {
        this.$message.error("一次只能上传一个文件");        
      },
      // 移除上传文件的操作
      handleRemove() { 
        this.fileList.pop();
      },
      // 对话窗口的当前页面的重置
      reset(index) {
        this.$refs[this.ruleFormName[index]].resetFields();
        if (index == 2) this.fileList = []
      },
      // 对话窗口的取消
      cancel(index) {
        for (var i = 0; i <= index; i++) {
          this.reset(i);
        }  
        this.dialogVisible.splice(index, 1, false);
      }
  }
}
</script>
