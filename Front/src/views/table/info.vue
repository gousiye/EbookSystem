<template>
    <div class="InfoDialog">
        
        <el-dialog title="详细信息" :visible.sync="dialogVisible[0]">
            <el-form :ref="ruleFormName[0]" class="demo-ruleForm" close-on-press-escape>

                <el-form-item label="编号" :label-width="formLabelWidth">
                    <el-input v-model="Ebook.id" autocomplete="off" :readonly="readOnly"></el-input>
                </el-form-item>

                <el-form-item label="书名" :label-width="formLabelWidth">
                    <el-input v-model="Ebook.bookName" autocomplete="off" :readonly="readOnly"></el-input>
                </el-form-item>
                
                <el-form-item label="作者名" :label-width="formLabelWidth">
                    <el-input v-model="Ebook.authorName" autocomplete="off" :readonly="readOnly"></el-input>
                </el-form-item>

                <el-form-item label="类别" :label-width="formLabelWidth" >
                    <el-input v-model="Ebook.classifications" autocomplete="off" :readonly="readOnly"></el-input>
                </el-form-item>

                <el-form-item label="语言" :label-width="formLabelWidth">
                    <el-input v-model="Ebook.language" autocomplete="off" :readonly="readOnly"></el-input>
                </el-form-item>

                
            </el-form>

            <div slot="footer" class="dialog-footer">
                <el-button @click="cancel(0)">取 消</el-button>
                <el-button type="primary" @click="next(0)">下 一 页</el-button>
            </div>

        
        </el-dialog>



        <el-dialog title="详细信息" :visible.sync="dialogVisible[1]" close-on-press-escape>
            <el-form :ref="ruleFormName[1]" class="demo-ruleForm">

                <el-form-item label="文件格式" :label-width="formLabelWidth">
                    <el-input v-model="Ebook.suffix" autocomplete="off" :readonly="readOnly"></el-input>
                </el-form-item>
        

                <el-form-item label="文件大小(KB)" :label-width="formLabelWidth">
                    <el-input v-model="Ebook.size" autocomplete="off" :readonly="readOnly"></el-input>
                </el-form-item>
        
                <el-form-item label="出版社" :label-width="formLabelWidth">
                    <el-input v-model="Ebook.publisher" autocomplete="off" :readonly="readOnly"></el-input>
                </el-form-item>

                <el-form-item label="创建时间" :label-width="formLabelWidth">
                    <el-input v-model="Ebook.createTime" autocomplete="off" :readonly="readOnly"></el-input>
                </el-form-item>
        

                <el-form-item label="最后修改时间" :label-width="formLabelWidth">
                    <el-input v-model="Ebook.lastAlterTime" autocomplete="off" :readonly="readOnly"></el-input>
                </el-form-item>
    
                <el-form-item label="下载量" :label-width="formLabelWidth">
                    <el-input v-model="Ebook.download" autocomplete="off" :readonly="readOnly"></el-input>
                </el-form-item>


                <!-- <el-form-item label="简介" :label-width="formLabelWidth" prop="introduction">
                    <el-input v-model="Ebook.introduction" autocomplete="off" type="textarea" :rows=10 readonly:true></el-input>
                </el-form-item> -->


            </el-form>

            <div slot="footer" class="dialog-footer">
                <el-button @click="back(1)">上 一 页</el-button>
                <el-button  @click="cancel(1)">取 消</el-button>
                <el-button type="primary" @click="next(1)">下 一 页</el-button>
            </div>

        </el-dialog>

        <el-dialog title="详细信息" :visible.sync="dialogVisible[2]">
            <el-form :ref="ruleFormName[2]" class="demo-ruleForm" close-on-press-escape>
                <el-form-item label="简介" :label-width="formLabelWidth" prop="introduction">
                    <el-input v-model="Ebook.introduction" autocomplete="off" type="textarea" :rows=10 :readonly="readOnly"></el-input>
                </el-form-item>
            </el-form>

            <div slot="footer" class="dialog-footer">
                <el-button @click="cancel(2)">取 消</el-button>
                <el-button type="primary" @click="back(2)">上 一 页</el-button>
            </div>

    
        </el-dialog>



    </div>
</template>

<script>
import { GetABookInfo } from '@/api/table'
export default {
    name: "info",
    data() {
        return {
            dialogVisible: [false, false, false],
            ruleFormName: ["form1", "form2", "form3"],
            formLabelWidth: '120px',  //对话窗口的长度
            bookId: "",
            readOnly: true,
            Ebook: {}
        }
    },
    methods: {
        //显示对话窗口
        show() {
            GetABookInfo({ id: this.bookId }).then(response => {
                this.Ebook = response.data
                this.Ebook.classifications = this.Ebook.classifications[0]
            })

            this.dialogVisible.splice(0, 1, true);
        },
        // 判断当前是哪一本书
        setId(bookId) {
            this.bookId = bookId;
        },
        // 对话窗口的下一步
        next(index) {
            this.dialogVisible.splice(index, 1, false);
            this.dialogVisible.splice(index + 1, 1, true);

        },
        // 对话窗口的上一步
        back(index) {
            this.dialogVisible.splice(index, 1, false);
            this.dialogVisible.splice(index - 1, 1, true);
        },
        // 对话窗口的取消
        cancel(index) {
            this.dialogVisible.splice(index, 1, false);
        }
    }
}
</script>



