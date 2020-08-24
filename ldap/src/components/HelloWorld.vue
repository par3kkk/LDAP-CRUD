<!--顶部按件-->
<!--输入域名查询-->
<template>
<div>
<el-form :inline="true" class="demo-form-inline">
    <el-form-item>
        <el-input v-model="search" class="search_adName" size="mini" placeholder="输入域名查询">
        </el-input>
    </el-form-item>
    <el-form-item>
        <el-button type="text" @click="onSearch()" class="el-icon-search">查询</el-button>
    </el-form-item>
    <el-form-item>
        <el-button class="el-icon-refresh" type="text" @click="refreshData">刷新</el-button>
    </el-form-item>
    <el-form-item>
        <el-button class="el-icon-circle-plus-outline" type="text" @click="dialogVisible = true">添加</el-button>
    </el-form-item>
</el-form>

<!--表格-->
<el-table :data="tableData" highlight-current-row border style="width : 100%">
<el-table-column label="编号">
    <template slot-scope="scope">
        <span>{{ scope.row.ldapID }}</span>
    </template>
</el-table-column>
<el-table-column label="域名">
    <template slot-scope="scope">
        <el-popover trigger="hover" placement="right">
            <p>域名: {{scope.row.adName}}</p>
            <p>代理用户地址: {{scope.row.bindUserPath}}</p>
            <p>代理用户密码: {{scope.row.bindUserPassword}}</p>
            <div slot="reference" class="name-wrapper">
                <el-button type="text">{{scope.row.adName}}</el-button>
            </div>
        </el-popover>
    </template>
</el-table-column>
<el-table-column label="IP地址">
    <template slot-scope="scope">
        <span>{{ scope.row.ip }}</span>
    </template>
</el-table-column>
<el-table-column label="用户搜索路径">
    <template slot-scope="scope">
        <span>{{ scope.row.searchPath }}</span>
    </template>
</el-table-column>
<el-table-column label="操作" fixed="right" width="200px">
<template slot-scope="scope">
    <el-button size="mini" icon="el-icon-edit" style="primary" @click="handleEdit(scope.$index,scope.row)">编辑</el-button>
    <el-button size="mini" icon="el-icon-delete" style="danger" @click="handleDelete(scope.$index,scope.row)">删除</el-button>
</template>
</el-table-column>
</el-table>


<!--新增表单-->
<el-form :model="ruleForm"  ref="ruleForm" label-width="70px" class="demo-ruleForm" size="medium">
<el-dialog title="编辑" :append-to-body="true" :visible.sync="dialogVisible" width="80%" :before-close="handleClose">
    <el-input type="hidden" v-model="ruleForm.ldapID"/>
    <el-form-item label="域名" prop="adName">
        <el-input v-model="ruleForm.adName"></el-input>
    </el-form-item>
    <el-form-item label="IP地址" prop="ip">
        <el-input v-model="ruleForm.ip"></el-input>
    </el-form-item>
    <el-form-item label="用户搜索路径" prop="searchPath">
    <el-input v-model="ruleForm.searchPath"></el-input>
    </el-form-item>
    <el-form-item label="代理用户路径" prop="bindUserPath">
    <el-input v-model="ruleForm.bindUserPath"></el-input>
    </el-form-item>
    <el-form-item label="代理用户密码" prop="bindUserPassword">
        <el-input v-model="ruleForm.bindUserPassword"></el-input>
    </el-form-item>

    <span slot="footer" class="dialog-footer">
    <el-button @click="cancel()" size="medium">取消</el-button>
    <el-button @click="addLdap()" type="primary" size="medium">确定</el-button>
    </span>
</el-dialog>
</el-form>

<!--编辑表单-->
<el-form :model="ruleForm" ref="ruleForm" label-width="70px" class="demo-ruleForm" size="medium">
<el-dialog title="编辑" :append-to-body="true" :visible.sync="dialogUpdate" width="80%" :before-close="handleClose">
    <el-input type="hidden" v-model="ruleForm.ldapID"/>
    <el-form-item label="域名" prop="adName">
        <el-input v-model="ruleForm.adName"></el-input>
    </el-form-item>
    <el-form-item label="IP地址" prop="ip">
        <el-input v-model="ruleForm.ip"></el-input>
    </el-form-item>
    <el-form-item label="用户搜索路径" prop="searchPath">
    <el-input v-model="ruleForm.searchPath"></el-input>
    </el-form-item>
    <el-form-item label="代理用户路径" prop="bindUserPath">
    <el-input v-model="ruleForm.bindUserPath"></el-input>
    </el-form-item>
    <el-form-item label="代理用户密码" prop="bindUserPassword">
        <el-input v-model="ruleForm.bindUserPassword"></el-input>
    </el-form-item>

    <span slot="footer" class="dialog-footer">
    <el-button @click="cancel()" size="medium">取消</el-button>
    <el-button @click="updateLdap()" type="primary" size="medium">确定</el-button>
    </span>
</el-dialog>
</el-form>

<br>
<!--分页-->
<div class="pages">
    <el-pagination background :disabled="disablePage" :current-page.sync="currentPage" small
    layout="prev, pager, next" :page-size="pageSize" :total="total" @current-change="handleCurrentChange">
    </el-pagination>
</div>
</div>
</template>

<script>
export default {
    data() {
        return {
            ruleForm : {
                ldapID : '',
                adName : '',
                ip : '',
                searchPath : '',
                bindUserPath : '',
                bindUserPassword : ''
            },
            tableData : [],
            search : '',
            dialogVisible : false,  
            dialogUpdate : false,
            pageSize : 5,
            currentPage : 1,
            total : 0,
            disablePage : false
        }
    },
    methods : {
        handleEdit(index, row) {
            this.dialogUpdate = true;
            this.ruleForm = Object.assign({}, row);
        },

        handleDelete(index, row) {
            console.log(index, row);
            this.$confirm('删除操作, 是否继续?', '提示', {
                confirmButtonText : '确定',
                cancelButtonText : '取消',
                type : 'warning'
            }).then(() => {
                let postData = this.qs.stringify({
                    ldapID : row.ldapID,
                });
                this.axios({
                    method : 'post',
                    url : '/delete',
                    data : postData
                }).then(response => {
                    this.getPages();
                    this.currentPage = 1;
                    this.axios.post('/page').then(response => {
                        this.tableData = response.data;
                    }).catch(error => {
                        console.log(error);
                    });
                    this.$message ({
                        type : 'success',
                        message : '删除成功'
                    });
                    console.log(response);
                }).catch(error => {
                    console.log(error);
                });
            
            }).catch(() => {
                this.$message({
                    type : 'info',
                    message : '已取消删除'
                });
            });
        },

        handleClose(done) {
            this.$confirm('确认关闭?')
            .then(_ => {
                done();
            })
            .catch(_ => {});
        },

        handleCurrentChange() {
            console.log('当前页: ${this.currentPage}');
            let postData = this.qs.stringify({
                page : this.currentPage
            });
            this.axios({
                method : 'post',
                url : '/page',
                data : postData
            }).then(response => {
                this.tableData.response.data;
            }).catch(error => {
                console.log(error);
            });
        },

        cancel() {
            this.dialogVisible = false;
            this.dialogUpdate = false;
            this.emptyLdapData();
        },

        //清空
        emptyLdapData() {
            this.ruleForm = {
                adName : '',
                ip : '',
                searchPath : '',
                bindUserPath : '',
                bindUserPassword : ''
            }
        },

        addLdap() {
            let postData = this.qs.stringify({
                adName : this.ruleForm.adName,
                ip : this.ruleForm.ip,
                searchPath : this.ruleForm.searchPath,
                bindUserPath : this.ruleForm.bindUserPath,
                bindUserPassword : this.ruleForm.bindUserPassword
            });
            this.axios({
                method : 'post',
                url: '/insert',
                data : postData
            }).then(response => {
                this.axios.post('/page').then(response => {
                    this.tableData = response.data;
                    this.currentPage = 1;
                    this.$message({
                        type : 'success',
                        message : '已添加'
                    });
                }).catch(error => {
                    console.log(error);
                });
                this.getPages();
                this.dialogVisible = false;
                console.log(response);
            }).catch(error => {
                console.log(error);
            });
        },

        updateLdap() {
            let postData = this.qs.stringify({
            adName : this.ruleForm.adName,
            ip : this.ruleForm.ip,
            searchPath : this.ruleForm.searchPath,
            bindUserPath : this.ruleForm.bindUserPath,
            bindUserPassword : this.ruleForm.bindUserPassword 
            });
            this.axios({
                method : 'post',
                url : '/update',
                data : postData
            }).then(response => {
                this.handleCurrentChange();
                this.cancel();
                this.$message({
                    type: 'success',
                    message : '更新成功'
                });
                console.log(response);
            }).catch(error => {
                this.$message({
                    type : 'success',
                    message : '更新失败'
                });
                console.log(error);
            });
        },

        onSearch() {
            let postData = this.qs.stringify({
                adName : this.search
            });
            this.axios({
                method : 'post',
                url: '/ListByadName',
                data : postData
            }).then(response => {
                this.tableData = response.data;
                this.disablePage = true;
            }).catch(error => {
                console.log(error);
            });
        },

        getPages() {
            this.axios.post('/rows').then(response => {
                this.total = response.data;
            }).catch(error => {
                console.log(error);
            });
        },

        refreshData() {
            location.reload();
        }
    },

    created() {
            /*this.axios.get('static/user.json').then(response =>
            {
                this.tableData = response.data.tableData;
                this.total = response.data.tableData.length;
                // console.log(JSON.parse(JSON.stringify(response.data))['tableData'])
            });*/
            this.axios.post('/page').then(response =>
            {
                this.tableData = response.data;
            }).catch(error =>
            {
                console.log(error);
            });

            this.axios.post('/rows').then(response =>
            {
                this.total = response.data;
            }).catch(error =>
            {
                console.log(error);
            });

        },
}
</script>
<style scoped>
  .search_name{
    width: 200px;
  }
  .pages{
    margin: 0px;
    padding: 0px;
    text-align: right;
  }
</style>
