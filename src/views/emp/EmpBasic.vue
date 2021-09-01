<template>
<div>
  <div style="display: flex;justify-content: space-between">
    <div>
      <el-input placeholder="请输入员工姓名进行搜索，可按回车搜索..." prefix-icon="el-icon-search"
                clearable
                @clear="initEmps"
                style="width:300px;margin-right: 10px" v-model="keyword" @keydown.enter.native="initEmps"></el-input>
      <el-button icon="el-icon-search" type="primary" @click="initEmps">搜索</el-button>
      <el-button type="primary">
        <i class="fa fa-angle-double-down"></i>
        高级搜索
      </el-button>
    </div>
    <div>
      <el-button type="success">
        <i class="fa fa-level-up"></i>
        导入数据
      </el-button>
      <el-button type="success">
        <i class="fa fa-level-down"></i>
        导出数据
      </el-button>
      <el-button type="primary" icon="el-icon-plus" @click="showAddEmpView">
        添加用户
      </el-button>
    </div>
  </div>
  <div style="margin-top: 10px">
    <el-table
        :data="emps"
        stripe
        boeder
        v-loading="loading"
        element-loading-text="正在加载...."
        element-loading-spinner="el-icon-loading"
        element-loading-background="rgba(0, 0, 0, 0.8)"
        style="width: 100%">
      <el-table-column
          type="selection"
          width="55">
      </el-table-column>
      <el-table-column
          prop="name"
          fixed
          align="left"
          label="姓名"
          width="90">
      </el-table-column>
      <el-table-column
          prop="workID"
          label="工号"
          align="left"
          width="85">
      </el-table-column>
      <el-table-column
          prop="birthday"
          width="95"
          align="left"
          label="出生日期">
      </el-table-column>
      <el-table-column
          prop="idCard"
          width="150"
          align="left"
          label="身份证号码">
      </el-table-column>
      <el-table-column
          prop="wedlock"
          width="70"
          label="婚姻状态">
      </el-table-column>
      <el-table-column
          prop="nation.name"
          width="50"
          label="民族">
      </el-table-column>
      <el-table-column
          prop="nativePlace"
          width="80"
          label="籍贯">
      </el-table-column>
      <el-table-column
          prop="politicsstatus.name"
          label="政治面貌">
      </el-table-column>
      <el-table-column
          prop="email"
          width="180"
          align="left"
          label="电子邮件">
      </el-table-column>
      <el-table-column
          prop="phone"
          width="100"
          align="left"
          label="电话号码">
      </el-table-column>
      <el-table-column
          prop="address"
          width="220"
          align="left"
          label="联系地址">
      </el-table-column>
      <el-table-column
          prop="department.name"
          width="100"
          align="left"
          label="所属部门">
      </el-table-column>
      <el-table-column
          prop="position.name"
          width="100"
          label="职位">
      </el-table-column>
      <el-table-column
          prop="jobLevel.name"
          width="100"
          label="职称">
      </el-table-column>
      <el-table-column
          prop="engageForm"
          width="100"
          align="left"
          label="聘用形式">
      </el-table-column>
      <el-table-column
          prop="beginDate"
          width="95"
          align="left"
          label="入职日期">
      </el-table-column>
      <el-table-column
          prop="conversionTime"
          width="95"
          align="left"
          label="转正日期">
      </el-table-column>
      <el-table-column
          prop="beginContract"
          width="95"
          align="left"
          label="合同起始日期">
      </el-table-column>
      <el-table-column
          prop="endContract"
          width="95"
          align="left"
          label="合同截至日期">
      </el-table-column>
      <el-table-column
          prop="contractTerm"
          width="100"
          align="left"
          label="合同期限">
        <template slot-scope="scope">
          <el-tag>{{scope.row.contractTerm}}</el-tag>年
        </template>
      </el-table-column>
      <el-table-column
          prop="tiptopDegree"
          label="最高学历">
      </el-table-column>
      <el-table-column
          fixed="right"
          width="200"
          label="操作">
        <template slot-scope="scope">
          <el-button style="padding: 3px" size="mini" >编辑</el-button>
          <el-button style="padding: 3px" size="mini" type="primary">查看高级资料</el-button>
          <el-button style="padding: 3px" size="mini" type="danger">删除</el-button>
        </template>
      </el-table-column>
    </el-table>
    <div style="display: flex;justify-content: flex-end">
      <el-pagination
          background
          @current-change="currentChange"
          @size-change="sizeChange"
          layout="sizes, prev, pager, next, jumper, ->, total, slot"
          :total="total">
      </el-pagination>
    </div>
  </div>
  <el-dialog
      title="添加员工"
      :visible.sync="dialogVisible"
      width="80%">
    <div>
      <el-form :model="emp" :rules="rules" ref="empForm">
        <!--第一行-->
      <el-row>
        <!--第一列-->
        <el-col :span="6">
          <el-form-item label="姓名:" prop="name">
            <el-input size="mini" style="width:150px" prefix-icon="el-icon-edit" v-model="emp.name" placeholder="请输入员工姓名"></el-input>
          </el-form-item>
        </el-col>
        <!--第二列-->
        <el-col :span="5">
          <el-form-item label="性别:" prop="gender">
           <el-radio-group v-model="emp.gender">
             <el-radio label="男">男</el-radio>
             <el-radio label="女">女</el-radio>
           </el-radio-group>
          </el-form-item>
        </el-col>
        <!--第三列-->
        <el-col :span="6">
          <el-form-item label="出生日期:" prop="birthday">
            <el-date-picker
                v-model="emp.birthday"
                size="mini"
                type="date"
                value-format="yyyy-MM-dd"
                style="width:150px;"
                placeholder="出生日期">
            </el-date-picker>
          </el-form-item>
        </el-col>
        <!--第四列-->
        <el-col :span="7">
          <el-form-item label="政治面貌:" prop="politicId">
            <el-select v-model="emp.politicId" placeholder="政治面貌" size="mini" style="width:200px;">
              <el-option
                  v-for="item in politicsstatus"
                  :key="item.id"
                  :label="item.name"
                  :value="item.id">
              </el-option>
            </el-select>
          </el-form-item>
        </el-col>
      </el-row>
        <!--第二行-->
        <el-row>
          <!--第一列-->
          <el-col :span="6">
            <el-form-item label="民族：" prop="nationId">
              <el-select v-model="emp.nationId" placeholder="民族" size="mini" style="width:150px;">
                <el-option
                    v-for="item in nations"
                    :key="item.id"
                    :label="item.name"
                    :value="item.id">
                </el-option>
              </el-select>
            </el-form-item>
          </el-col>
        <!--第二列-->
          <el-col :span="5">
            <el-form-item label="籍贯:" prop="nativePlace">
              <el-input size="mini" style="width:120px" prefix-icon="el-icon-edit" v-model="emp.nativePlace" placeholder="请输入籍贯"></el-input>
            </el-form-item>
          </el-col>
        <!--第三列-->
          <el-col :span="6">
            <el-form-item label="电子邮箱:" prop="email">
              <el-input size="mini" style="width:150px" prefix-icon="el-icon-edit" v-model="emp.email" placeholder="请输入电子邮箱"></el-input>
            </el-form-item>
          </el-col>
        <!--第四列-->
          <el-col :span="7">
            <el-form-item label="联系地址:" prop="address">
              <el-input size="mini" style="width:200px" prefix-icon="el-icon-edit" v-model="emp.address" placeholder="请输入联系地址"></el-input>
            </el-form-item>
          </el-col>
        </el-row>
      <!--第三行-->
        <el-row>
        <!--第一列-->
          <el-col :span="6">
            <el-form-item label="职位：" prop="posId">
              <el-select v-model="emp.posId" placeholder="职位" size="mini" style="width:150px;">
                <el-option
                    v-for="item in positions"
                    :key="item.id"
                    :label="item.name"
                    :value="item.id">
                </el-option>
              </el-select>
            </el-form-item>
          </el-col>
        <!--第二列-->
          <el-col :span="5">
            <el-form-item label="职称：" prop="jobLevelId">
              <el-select v-model="emp.jobLevelId" placeholder="职称" size="mini" style="width:120px;">
                <el-option
                    v-for="item in joblevels"
                    :key="item.id"
                    :label="item.name"
                    :value="item.id">
                </el-option>
              </el-select>
            </el-form-item>
          </el-col>
        <!--第三列-->
          <el-col :span="6">
            <el-form-item label="所属部门:" prop="departmentId">
              <!--<el-input size="mini" style="width:150px" prefix-icon="el-icon-edit" v-model="emp.departmentId" placeholder="所属部门"></el-input>-->
              <el-popover
                  placement="right"
                  title="请选择部门"
                  width="200"
                  trigger="manual"
                  v-model="popVisible">
                <el-tree default-expand-all :data="allDeps" :props="defaultProps" @node-click="handleNodeClick"></el-tree>
                <div slot="reference" style="width:150px;display:inline-flex;font-size: 13px;border:1px solid #dedede;height:26px;border-radius: 5px;cursor: pointer;align-items: center;padding-left: 8px;box-sizing: border-box" @click="showDepView">
                  {{inputDepName}}
                </div>
              </el-popover>
            </el-form-item>
          </el-col>
        <!--第四列-->
          <el-col :span="7">
            <el-form-item label="电话号码:" prop="phone">
              <el-input size="mini" style="width:200px" prefix-icon="el-icon-phone" v-model="emp.phone" placeholder="电话号码"></el-input>
            </el-form-item>
          </el-col>
        </el-row>
      <!--第四行-->
        <el-row>
        <!--第一列-->
          <el-col :span="6">
            <el-form-item label="工号:" prop="workID">
              <el-input size="mini" style="width:150px" prefix-icon="el-icon-edit" v-model="emp.workID" placeholder="工号" disabled></el-input>
            </el-form-item>
          </el-col>
        <!--第二列-->
          <el-col :span="5">
            <el-form-item label="学历：" prop="tiptopDegree">
              <el-select v-model="emp.tiptopDegree" placeholder="学历" size="mini" style="width:120px;">
                <el-option
                    v-for="item in tiptopDegrees"
                    :key="item"
                    :label="item"
                    :value="item">
                </el-option>
              </el-select>
            </el-form-item>
          </el-col>
        <!--第三列-->
          <el-col :span="6">
            <el-form-item label="毕业院校:" prop="school">
              <el-input size="mini" style="width:150px" prefix-icon="el-icon-edit" v-model="emp.school" placeholder="毕业院校"></el-input>
            </el-form-item>
          </el-col>
        <!--第四列-->
          <el-col :span="7">
            <el-form-item label="专业名称:" prop="specialty">
              <el-input size="mini" style="width:200px" prefix-icon="el-icon-edit" v-model="emp.specialty" placeholder="专业名称"></el-input>
            </el-form-item>
          </el-col>
        </el-row>
      <!--第五行-->
        <el-row>
        <!--第一列-->
          <el-col :span="6">
            <el-form-item label="入职日期:" prop="beginDate">
              <el-date-picker
                  v-model="emp.beginDate"
                  size="mini"
                  type="date"
                  value-format="yyyy-MM-dd"
                  style="width:130px;"
                  placeholder="入职日期">
              </el-date-picker>
            </el-form-item>
          </el-col>
        <!--第二列-->
          <el-col :span="5">
            <el-form-item label="转正日期:" prop="conversionTime">
              <el-date-picker
                  v-model="emp.conversionTime"
                  size="mini"
                  type="date"
                  value-format="yyyy-MM-dd"
                  style="width:130px;"
                  placeholder="转正日期">
              </el-date-picker>
            </el-form-item>
          </el-col>
        <!--第三列-->
          <el-col :span="6">
            <el-form-item label="合同起始日期:" prop="beginContract">
              <el-date-picker
                  v-model="emp.beginContract"
                  size="mini"
                  type="date"
                  value-format="yyyy-MM-dd"
                  style="width:130px;"
                  placeholder="合同起始日期">
              </el-date-picker>
            </el-form-item>
          </el-col>
        <!--第四列-->
          <el-col :span="7">
            <el-form-item label="合同截止日期:" prop="endContract">
              <el-date-picker
                  v-model="emp.endContract"
                  size="mini"
                  type="date"
                  value-format="yyyy-MM-dd"
                  style="width:130px;"
                  placeholder="合同截止日期">
              </el-date-picker>
            </el-form-item>
          </el-col>
        </el-row>
      <!--第六行-->
        <el-row>
          <!--第一列-->
          <el-col :span="8">
            <el-form-item label="身份证号码:" prop="idCard">
              <el-input size="mini" style="width:150px" prefix-icon="el-icon-edit" v-model="emp.idCard" placeholder="请输入身份证号码"></el-input>
            </el-form-item>
          </el-col>
          <!--第二列-->
          <el-col :span="8">
            <el-form-item label="聘用形式:" prop="engageForm">
              <el-radio-group v-model="emp.engageForm">
                <el-radio label="劳动合同">劳动合同</el-radio>
                <el-radio label="劳务合同">劳务合同</el-radio>
              </el-radio-group>
            </el-form-item>
          </el-col>
          <!--第三列-->
          <el-col :span="8">
            <el-form-item label="婚姻状况:" prop="wedlock">
              <el-radio-group v-model="emp.wedlock">
                <el-radio label="已婚">已婚</el-radio>
                <el-radio label="未婚">未婚</el-radio>
                <el-radio label="离异">离异</el-radio>
              </el-radio-group>
            </el-form-item>
          </el-col>
        </el-row>
      </el-form>
    </div>
    <span slot="footer" class="dialog-footer">
    <el-button @click="dialogVisible = false">取 消</el-button>
    <el-button type="primary" @click="doAddEmp">确 定</el-button>
  </span>
  </el-dialog>
</div>
</template>

<script>
export default {
  name: "EmpBasic",
  data(){
    return{
      //数据校验，如果在前端页面没有输入数据，将不能提交数据
      rules:{
        name:[{required:true,message:'请输入用户名',trigger:'blur'}],
        gender:[{required:true,message:'请输入性别',trigger:'blur'}],
        birthday:[{required:true,message:'请输入出生日期',trigger:'blur'}],
        idCard:[{required:true,message:'请输入身份证号码',trigger:'blur'}],
        wedlock:[{required:true,message:'请输入婚姻状况',trigger:'blur'}],
        nationId:[{required:true,message:'请输入民族',trigger:'blur'}],
        nativePlace:[{required:true,message:'请输入籍贯',trigger:'blur'}],
        politicId:[{required:true,message:'请输入政治面貌',trigger:'blur'}],
        email:[{required:true,message:'请输入邮箱',trigger:'blur'},{
          type:'email',
          message:'邮箱格式不正确',
          trigger:'blur'
        }],
        phone:[{required:true,message:'请输入电话',trigger:'blur'}],
        address:[{required:true,message:'请输入地址',trigger:'blur'}],
        departmentId:[{required:true,message:'请输入部门名称',trigger:'blur'}],
        jobLevelId:[{required:true,message:'请输入职称',trigger:'blur'}],
        posId:[{required:true,message:'请输入职位',trigger:'blur'}],
        engageForm:[{required:true,message:'请输入聘用形式',trigger:'blur'}],
        tiptopDegree:[{required:true,message:'请输入学历',trigger:'blur'}],
        specialty:[{required:true,message:'请输入专业',trigger:'blur'}],
        school:[{required:true,message:'请输入毕业院校',trigger:'blur'}],
        beginDate:[{required:true,message:'请输入入职日期',trigger:'blur'}],
        workState:[{required:true,message:'请输入工作状态',trigger:'blur'}],
        workID:[{required:true,message:'请输入工号',trigger:'blur'}],
        contractTerm:[{required:true,message:'请输入合同期限',trigger:'blur'}],
        conversionTime:[{required:true,message:'请输入转正日期',trigger:'blur'}],
        notWorkDate:[{required:true,message:'请输入notWorkDate',trigger:'blur'}],
        beginContract:[{required:true,message:'请输入合同起始日期',trigger:'blur'}],
        endContract:[{required:true,message:'请输入合同截止日期',trigger:'blur'}]
      },
      allDeps:[],
      popVisible:false,
      tiptopDegrees:['本科','大专','硕士','博士','高中','初中','小学','其他'],
      positions:[],
      nations:[],
      joblevels:[],
      politicsstatus:[],
      options: [{
        value: '选项1',
        label: '黄金糕'
      }, {
        value: '选项2',
        label: '双皮奶'
      }, {
        value: '选项3',
        label: '蚵仔煎'
      }, {
        value: '选项4',
        label: '龙须面'
      }, {
        value: '选项5',
        label: '北京烤鸭'
      }],
      emps:[],
      loading:false,
      total:0,
      page:1,
      size:10,
      dialogVisible:false,
      keyword:'',
      emp:{
        name:"小李",
        gender:"女",
        birthday:"2021-10-26",
        idCard:"1455224555224552",
        wedlock:"已婚",
        nationId:1,
        nativePlace:"陕西",
        politicId:13,
        email:"1327855@qq.com",
        phone:"18214765924",
        address:"四川成都",
        departmentId:null,
        jobLevelId:9,
        posId:29,
        engageForm:"劳务合同",
        tiptopDegree:"本科",
        specialty:"信息管理与信息系统",
        school:"清华大学",
        beginDate:"2021-10-28",
        workState:"在职",
        workID:"00000057",
        contractTerm:2,
        conversionTime:"2021-6-28",
        notWorkDate:null,
        beginContract:"2015-6-28",
        endContract:"2018-6-28",
        workAge:null
      },
      defaultProps: {
        children: 'children',
        label: 'name'
      },
      inputDepName:''
    }

  },
  mounted() {
    this.initEmps();
    this.initData();
  },
  methods: {
    doAddEmp() {
      this.$refs['empForm'].validate(valid => {
        if (valid) {
          this.postRequest("/emp/basic/", this.emp).then(resp => {
            if (resp) {
              this.dialogVisible = false;
              this.initEmps();
            }
          })
        }
      })
    },
    handleNodeClick(data) {
      this.inputDepName = data.name;
      this.emp.departmentId = data.id;
      this.popVisible = !this.popVisible;
    },
    showDepView() {
      this.popVisible = !this.popVisible;
    },
    getMaxWordID() {
      this.getRequest("/emp/basic/maxWorkID").then(resp => {
        if (resp) {
          //写成this.emp.workID=resp;会出现错误
          this.emp.workID = resp.obj;
        }
      })
    },
    initPositions() {
      this.getRequest('/emp/basic/positions').then(resp => {
        if (resp) {
          this.positions = resp;
        }
      })
    },
    initData() {
      if (!window.sessionStorage.getItem("nations")) {
        this.getRequest('/emp/basic/nations').then(resp => {
          if (resp) {
            this.nations = resp;
            //保存数据在前端
            window.sessionStorage.setItem("nations", JSON.stringify(resp));
          }
        })
      } else {
        this.nations = JSON.parse(window.sessionStorage.getItem("nations"));
      }
      if (!window.sessionStorage.getItem("joblevels")) {
        this.getRequest('/emp/basic/joblevels').then(resp => {
          if (resp) {
            this.joblevels = resp;
            //保存数据在前端
            window.sessionStorage.setItem("joblevels", JSON.stringify(resp));
          }
        })
      } else {
        this.joblevels = JSON.parse(window.sessionStorage.getItem("joblevels"));
      }
      if (!window.sessionStorage.getItem("politicsstatus")) {
        this.getRequest('/emp/basic/politicsstatus').then(resp => {
          if (resp) {
            this.politicsstatus = resp;
            //保存数据在前端
            window.sessionStorage.setItem("politicsstatus", JSON.stringify(resp));
          }
        })
      } else {
        this.politicsstatus = JSON.parse(window.sessionStorage.getItem("politicsstatus"));
      }
      if (!window.sessionStorage.getItem("deps")) {
        this.getRequest('/emp/basic/deps').then(resp => {
          if (resp) {
            this.allDeps = resp;
            //保存数据在前端
            window.sessionStorage.setItem("deps", JSON.stringify(resp));
          }
        })
      } else {
        this.allDeps = JSON.parse(window.sessionStorage.getItem("deps"));
      }
    },
    showAddEmpView() {
      this.getMaxWordID();
      this.initPositions();
      this.dialogVisible = true;
    },
    sizeChange(currentSize) {
      this.size = currentSize;
      this.initEmps();
    },
    currentChange(currentPage) {
      this.page = currentPage;
      this.initEmps();
    },
    initEmps() {
      this.loading = true;
      this.getRequest("/emp/basic/?page=" + this.page + "&size=" + this.size + "&keyword=" + this.keyword).then(resp => {
        this.loading = false;
        if (resp) {
          this.emps = resp.data;
          this.total = resp.total;
        }
      })
    }
  }
}
</script>

<style scoped>

</style>

