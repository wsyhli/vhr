<template>
<div>
  <div>
    <el-input size="small" v-model="jl.name" style="width:300px" prefix-icon="el-icon-plus" placeholder="添加职称..."></el-input>
    <el-select v-model="jl.titleLevel" placeholder="职称等级" size="small" style="margin-left: 5px;margin-right: 5px">
      <el-option
          v-for="item in titleLevels"
          :key="item"
          :label="item"
          :value="item">
      </el-option>
    </el-select>
    <el-button icon="el-icon-plus" type="primary" size="small">添加</el-button>
  </div>
  <div style="margin-top: 10px">
    <el-table
        :data="jls"
        border
        size="small"
        style="width: 80%">
      <el-table-column
          prop="id"
          label="编号"
          width="55">
      </el-table-column>
      <el-table-column
          prop="职称名称"
          label="姓名"
          width="150">
      </el-table-column>
      <el-table-column
          prop="titleLevel"
          label="职称级别">
      </el-table-column>
      <el-table-column
          prop="createDate"
          label="创建时间">
      </el-table-column>
      <el-table-column
          label="操作">
        <template slot-scope="scope">
          <el-button size="small">编辑</el-button>
          <el-button size="small" type="danger">删除</el-button>
        </template>
      </el-table-column>
    </el-table>
  </div>
</div>
</template>

<script>
export default {
  name: "DepMana",
  data(){
    return{
      //变量jl管理这个对象
      jl:{
        name:'',
        titleLevel:''
      },
      jls:[],
      titleLevels:[
          '正高级',
          '副高级',
          '中级',
          '初级',
          '员级',
      ]
    }
  }
}
</script>

<style scoped>

</style>