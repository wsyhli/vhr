<!--template里面只有一个div标签,但是可以在一个div标签里面嵌套多个div标签-->
<template>
<div>
  <div>
    <el-input
        size="small"
        class="addPosInput"
        placeholder="添加职位..."
        prefix-icon="el-icon-circle-plus-outline"
        @keydown.enter.native="addPosition"
        v-model="pos.name">
    </el-input>
    <!--type="primary"表明按钮是蓝色的-->
    <el-button type="primary" size="small" @click="addPosition">添加职位</el-button>
  </div>
  <div class="posManaMain">
    <el-table
        :data="positions"
        border
        @selection-change="handleSelectionChange"
        size="small"
        stripe
        style="width: 70%">
      <el-table-column
          type="selection"
          width="55">
      </el-table-column>
      <el-table-column
          prop="id"
          label="编号"
          width="50">
      </el-table-column>
      <el-table-column
          prop="name"
          label="职位名称"
          width="180">
      </el-table-column>
      <el-table-column
          prop="createDate"
          width="150"
          label="创建时间">
      </el-table-column>
      <el-table-column
          label="是否启用">
        <template slot-scope="scope">
          <el-tag size="small" type="success" v-if="scope.row.enabled">已启用</el-tag>
          <el-tag size="small" type="danger" v-else>未启用</el-tag>
        </template>
      </el-table-column>
      <el-table-column label="操作">
        <template slot-scope="scope">
          <el-button
              size="mini"
              @click="showEditView(scope.$index, scope.row)">编辑</el-button>
          <el-button
              size="mini"
              type="danger"
              @click="handleDelete(scope.$index, scope.row)">删除</el-button>
        </template>
      </el-table-column>
    </el-table>
    <!--:disabled="multipleSelection.length==0"中的multipleSelection.length==0是表达式-->
    <!--disabled="multipleSelection.length==0"中的multipleSelection.length==0是字符串-->
    <!--根据变量multipleSelection来决定”按钮-批量删除“是否禁用-->
    <el-button @click="deleteMany" type="danger" size="small" style="margin-top: 8px" :disabled="multipleSelection.length==0">批量删除</el-button>
  </div>
  <el-dialog
      title="修改职位"
      :visible.sync="dialogVisible"
      width="30%">
    <div>
      <div>
        <el-tag>职位名称</el-tag>
        <el-input class="updatePosInput" size="small" v-model="updatePos.name"></el-input>
      </div>
     <div>
       <el-tag>是否启用</el-tag>
       <el-switch
           v-model="updatePos.enabled"
           active-text="启用"
           inactive-text="禁用">
       </el-switch>
     </div>
    </div>
    <span slot="footer" class="dialog-footer">
    <el-button size="small" @click="dialogVisible = false">取 消</el-button>
    <el-button size="small" type="primary" @click="doUpdate">确 定</el-button>
  </span>
  </el-dialog>
</div>
</template>

<script>
export default {
  name: "PosMana",
  data(){
    return{
      pos:{
        name:''
      },
      multipleSelection:[],
      dialogVisible:false,
      updatePos:{
        name:'',
        enabled:false
      },
      //json数组,表格的每一行对应下面json数组的每一个大括号{}里面的东西
      positions: []
    }
  },
  //组件初始化的时候会自动调用mounted()方法（vue的钩子函数）
  mounted() {
    this.initPositions();
  },
  methods:{
    deleteMany(){
      this.$confirm('此操作将永久删除【'+this.multipleSelection.length+'】条记录, 是否继续?', '提示', {
        confirmButtonText: '确定',
        cancelButtonText: '取消',
        type: 'warning'
      }).then(() => {
        let ids ='?';
        this.multipleSelection.forEach(item=>{
          ids+='ids='+item.id+'&';
        })
        this.deleteRequest("/system/basic/pos/"+ids).then(resp=>{
          if(resp){
            this.initPositions();
          }
        })
      }).catch(() => {
        this.$message({
          type: 'info',
          message: '已取消删除'
        });
      });
    },
    handleSelectionChange(val){
      //变量val保存着目前选择的项,然后把值赋值给multipleSelection数组
      this.multipleSelection=val;
      // console.log(val);
    },
    initPositions(){
      this.getRequest("/system/basic/pos/").then(resp=>{
        if(resp){
          this.positions=resp;
        }
      })
    },
    showEditView(index,data){
      //修改框中修改数据,网页上的原始数据不会实时发生变化
      Object.assign(this.updatePos,data);
      this.dialogVisible=true;
    },
    doUpdate(){
      this.putRequest("/system/basic/pos/",this.updatePos).then(resp=>{
        if(resp){
          this.initPositions();
          this.updatePos.name='';
          this.dialogVisible=false;
        }
      })
    },
    handleDelete(index,data){
      this.$confirm('此操作将永久删除【'+data.name+'】职位，是否继续?', '提示', {
        confirmButtonText: '确定',
        cancelButtonText: '取消',
        type: 'warning'
      }).then(() => {
        this.deleteRequest("/system/basic/pos/"+data.id).then(resp=>{
          if(resp){
            this.initPositions();
          }
        })
      }).catch(() => {
        this.$message({
          type: 'info',
          message: '已取消删除'
        });
      });
    },
    addPosition(){
      if(this.pos.name){
        this.postRequest("/system/basic/pos/",this.pos).then(resp=>{
          if(resp){
            //刷新表格
            this.initPositions();
          //在输入框输入职位，按下回车后，自动清空输入框中的内容
            this.pos.name='';
          }
        })
      }else{
        this.$message.error('职位名称不可以为空');
      }
    }
  }
}
</script>

<style>
.updatePosInput{
  width: 200px;
  margin-left: 20px;
}

.addPosInput{
  width:350px;
  margin-right: 8px
}
.posManaMain{
  margin-top: 10px;
}

</style>