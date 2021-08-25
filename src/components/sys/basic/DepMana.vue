<template>
<div style="width:500px">
  <el-input
      placeholder="请输入部门名称进行搜索..."
      prefix-icon="el-icon-search"
      v-model="filterText">
  </el-input>

  <el-tree
      :data="deps"
      :props="defaultProps"
      :expand-on-click-node="false"
      :filter-node-method="filterNode"
      ref="tree">
    <span class="custom-tree-node" style="display:flex;justify-content: space-between;width:100%" slot-scope="{ node, data }">
        <span>{{ node.label }}</span>
        <span>
          <el-button
              type="primary"
              size="mini"
              class="depBtn"
              @click="() => showAddDepView(data)">
            添加部门
          </el-button>
          <el-button
              type="danger"
              size="mini"
              class="depBtn"
              @click="() => deleteDep(data)">
            删除部门
          </el-button>
        </span>
      </span>
  </el-tree>
</div>
</template>

<script>
export default {
  name: "DepMana",
  data(){
    return{
      filterText:'',
      deps:[],
      defaultProps: {
        children: 'children',
        label: 'name'
      }
    }
  },
  //watch会监控filterText,当filterText的值发生变化的时候,会触发watch里面的filterText方法执行,进而会触发this.$refs.tree.filter(val);执行
  //val是filterText的当前值
  watch: {
    filterText(val) {
      //this.$refs.tree:获取到上面的<el-tree>树形控件,然后调用其中的filter方法,方法名为filterNode
      this.$refs.tree.filter(val);
    }
  },
  mounted() {
    this.initDeps();
  },
  methods: {
    deleteDep(data){
      console.log(data);
    },
    showAddDepView(data){
      console.log(data);
    },
    initDeps(){
      this.getRequest("/system/basic/department/").then(resp=>{
        if(resp){
          this.deps=resp;
        }
      })
    },
    //data为json对象
    filterNode(value, data) {
      if (!value) return true;
      return data.name.indexOf(value) !== -1;
    }
  },
}
</script>

<style>
.depBtn{
  padding:2px;
}

</style>