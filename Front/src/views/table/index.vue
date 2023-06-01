<template>
  <div class = "offset" scroll = "auto">

   <!-- 设置搜索的栏目 -->
    <div class = "Gap" style= "height:40px">
      <el-form :inline="true" :model="formInline" class="demo-form-inline" style= "white-space: nowrap">
        <el-form-item label="书籍名" >
          <el-input v-model="formInline.bookName" placeholder="书籍名"></el-input>
        </el-form-item>
        <el-form-item label="类别">
          <el-select v-model="formInline.classification" multiple placeholder="类别" filterable>
            <!-- 需要添加一个空的表示全选 -->
            <el-option label="" value= ""></el-option>
            <!-- 根据数据库中已有的标签进行渲染 -->
            <el-option
                v-for = "item in this.classifications"
                :key = "item.id"
                :label = "item.name"
                :value = "item.name"
            />
          </el-select>
        </el-form-item>

        <!-- 后缀名比较少，只用选一个就可以 -->
        <el-form-item label="后缀名">
          <el-select v-model="formInline.suffix" placeholder="后缀名">
            <!-- 与类别处理类似 -->
            <el-option label="" value=""></el-option>
            <el-option
                  v-for = "item in this.suffixes"
                  :key = "item"
                  :label = "item"
                  :value = "item"
            />
          </el-select>
        </el-form-item>

        <el-form-item>
          <el-button type="primary" class = "ml"  @click="reset">重置 <i class = "el-icon-refresh"></i></el-button>
          <el-button type="primary" style=" margin-left:5px"  @click="search">查询 <i class = "el-icon-search"></i></el-button>
        </el-form-item>
      </el-form>
    </div>
    <!-------------------------->

    <!-- 新增，删除 -->
    <div class = "Gap">
      <el-button type = "primary"  @click="ShowUploadDialog">
        上传书籍 <i class = "el-icon-circle-plus-outline"></i>
      </el-button>

      <el-button type = "warning" class = "ml" @click="MultiDelete">
        批量删除 <i class = "el-icon-remove-outline"></i>
      </el-button>  
    </div>

    <!-- 表格 -->
    <div class = "table" style = "width: 1300px;" >
      <el-table
        :data="tableData"
        style="width: 100%" 
        @selection-change ="handleSelectionChange">

          <el-table-column
          type="selection"
            width="55">
         </el-table-column>

        <el-table-column
          prop="bookName"
          label="书籍名"
          width="180">
        </el-table-column>

        <el-table-column
            prop="authorName"
            label="作者"
            width="100">
        </el-table-column>

        <el-table-column
          prop="suffix"
          label="文件格式"
          width="80">
        </el-table-column>

        <el-table-column
          prop="classifications"
          label="类别"
          width="200">
        </el-table-column>

        <el-table-column
          prop="download"
          label="下载量"
          width="80">
        </el-table-column>

        <el-table-column
            prop="size"
            label="文件大小(KB)"
            width="120">
        </el-table-column>

        <el-table-column label = "操作" align = "center" width="400">
            <template slot-scope = "scope">
              <el-button type = "info" @click = "ShowInfoDialog(scope.row)">详细信息</el-button>
              <el-button type = "success"  @click = "Download(scope.row)">下载</el-button>
              <el-button type = "danger" @click = "Delete(scope.row)">删除</el-button>
            </template>
        </el-table-column>

      </el-table>
    </div>
    <!-------------------------->

    <!-- 页面 -->
    <div class="pageComponent" style = "padding: 10px 0">
      <el-pagination
        @size-change="handleSizeChange"
        @current-change="handleCurrentChange"
        :current-page=this.pageNum
        :page-sizes="[5, 10, 15, 20]"
        :page-size=this.pageSize
        layout="total, sizes, prev, pager, next, jumper"
        :total=this.tableTotal>
      </el-pagination>
    </div>
    <!-------------------------->

    <!-- 上传书籍的谈话窗口 -->
      <upload ref = "upload" @refresh = "refresh"></upload>
    <!-------------------------->
  
    <!-- 详细信息的谈话窗口 -->
      <info ref = "info"></info>

  </div>
</template>


<script>
import { Query, GetClass, GetSuffix, DeleteABook, GetABookURL, UpdateDownload, MultiDelete } from '@/api/table'
import upload from './upload.vue';
import info from './info.vue';
export default {
  components: {
    upload,
    info
  },
  data() {
    return {
      // 表单中输入的搜索中的各个栏目
      formInline: {
        bookName: '',
        classification: null,
        suffix: ''
      },
      
      tableTotal: 0,   //总的书目条数
      tableData: [],   //书的具体信息
      pageNum: 1,    //当前页码
      pageSize: 10,
      classifications: [],   //所有的类别
      suffixes: [],   //所有的后缀名 
      dialogControl: {
        uploadControl: false,
        infoControl: false
      },
      multipleSelection: [] //用于多选
    }
  },
  methods: {
    search() {
      this.pageNum = 1;
      this.load();
    },
    reset() { 
      this.formInline.bookName = "";
      this.formInline.classification = null;
      this.formInline.suffix = "";
    },
    //初始化类别和后缀名
    init() {
      GetClass().then(response => {
        this.classifications = response.classes;
        //console.log(this.classifications)
      });
      GetSuffix().then(response => {
        this.suffixes = response.suffix;
      })
    },
    //查询电子书
    load() {
      // console.log(this.formInline.classification)
      // qs会跳过为空的参数，但不会跳过null的参数
      if (this.formInline.classification != null && this.formInline.classification.length == 0)
        this.formInline.classification = null;
        
      Query({
        pageNum: this.pageNum,
        pageSize: this.pageSize,
        bookName: this.formInline.bookName,
        classification: this.formInline.classification,
        suffix: this.formInline.suffix
      }).then(response => {
        this.tableTotal = response.total
        this.tableData = response.data;
      })   
    },
    handleSizeChange(pageSize) {
      this.pageSize = pageSize;
      this.load();
    },
    handleCurrentChange(pageNum) {
      this.pageNum = pageNum;
      this.load();
    },
    ShowUploadDialog() {     //显示上传窗口
      this.$refs.upload.show();
    },
    ShowInfoDialog(bookPartInfo) {    //显示详细信息窗口
      this.$refs.info.setId(bookPartInfo.id)
      this.$refs.info.show();
    },
    Download(book) {
      GetABookURL({ id: book.id }).then(response => {
        if (response != null && response.data != null) {
          console.log("开始请求下载")
          let link = document.createElement('a');
          link.style.display = 'none';
          link.href = response.data;
          document.body.appendChild(link);
          link.click();
          console.log("请求完成");
          book.download += 1;
          UpdateDownload({id :book.id}).then(res => { })
          }
        })
    },
    // 新增书籍后刷新书籍，类别，后缀名
    refresh() {
      this.init();
      this.load();
    },
    // 删除
    Delete(book) {
      this.$confirm('此操作将永久删除该文件, 是否继续?', '提示', {
        confirmButtonText: '确定',
        cancelButtonText: '取消',
        type: 'warning'
      }).then(() => {
        DeleteABook({ id: book.id }).then(response => {
          this.refresh();
        });
        this.$message({
          type: 'success',
          message: '删除成功!'
        });
       }).catch(() => {
       });
    },
    handleSelectionChange(val) {
      this.multipleSelection = val;
    },
    MultiDelete() {
      this.$confirm('此操作将永久删除这些文件, 是否继续?', '提示', {
        confirmButtonText: '确定',
        cancelButtonText: '取消',
        type: 'warning'
      }).then(() => {
        let bookIds = this.multipleSelection.map(iter => iter.id)
        console.log(bookIds);
        MultiDelete(bookIds).then(() => { this.refresh(); });
      })
    }
  },
  created() {
    this.init();
    this.load();
  },
}
</script>


<style scoped>

.offset{
  margin-top:20px;
  margin-left: 10px;
    overflow-x: auto;
}
.ml{
  margin-left: 50px;
  height: 100%;
}

.Gap{
  padding: 0px 0;
  margin: 10px 0 ;
  border: 0px 0;
}

.Hidden{
    word-break:keep-all;/* 不换行 */
    white-space:nowrap;/* 不换行 */
}


</style>