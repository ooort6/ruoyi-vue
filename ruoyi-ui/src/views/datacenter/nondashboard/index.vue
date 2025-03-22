<template>
  <div class="app-container">
    <el-row :gutter="20">
      <el-col :span="6" v-for="(item, index) in nonCoreData" :key="index">
        <el-card class="box-card" shadow="hover">
          <div slot="header" class="clearfix">
            <span>{{ item.title }}</span>
          </div>
          <div class="item-value">
            <count-to
              :start-val="0"
              :end-val="item.value"
              :duration="2000"
              separator=","
            />
          </div>
          <div class="item-desc">{{ item.desc }}</div>
        </el-card>
      </el-col>
    </el-row>

    <el-row :gutter="20" style="margin-top: 20px">
      <el-col :span="12">
        <el-card class="box-card" shadow="hover">
          <div slot="header" class="clearfix">
            <span>区域分布</span>
          </div>
          <div class="chart-wrapper">
            <pie-chart ref="regionDistributionChart" />
          </div>
        </el-card>
      </el-col>
      <el-col :span="12">
        <el-card class="box-card" shadow="hover">
          <div slot="header" class="clearfix">
            <span>二级单位业务量趋势</span>
          </div>
          <div class="chart-wrapper">
            <line-chart
              ref="departmentTrendChart"
              :chart-data="{ expectedData: [], actualData: [] }"
            />
          </div>
        </el-card>
      </el-col>
    </el-row>

    <el-row style="margin-top: 20px">
      <el-col :span="24">
        <el-card class="box-card" shadow="hover">
          <div slot="header" class="clearfix">
            <span>二级单位数据</span>
          </div>
          <el-table :data="departmentData" style="width: 100%">
            <el-table-column prop="name" label="单位名称" width="180">
            </el-table-column>
            <el-table-column prop="count" label="处理业务数" width="120">
            </el-table-column>
            <el-table-column prop="amount" label="金额" width="150">
              <template slot-scope="scope">
                {{ scope.row.amount.toFixed(2) }} 元
              </template>
            </el-table-column>
            <el-table-column prop="ratio" label="完成比例">
              <template slot-scope="scope">
                <el-progress :percentage="scope.row.ratio"></el-progress>
              </template>
            </el-table-column>
            <el-table-column prop="status" label="状态">
              <template slot-scope="scope">
                <el-tag
                  :type="scope.row.status === '正常' ? 'success' : 'danger'"
                  >{{ scope.row.status }}</el-tag
                >
              </template>
            </el-table-column>
          </el-table>
        </el-card>
      </el-col>
    </el-row>
  </div>
</template>

<script>
import CountTo from "vue-count-to";
import LineChart from "@/views/dashboard/LineChart";
import PieChart from "@/views/dashboard/PieChart";
// 使用实际API
import {
  getNonCoreData,
  getDepartmentTrendData,
  getRegionDistributionData,
  getDepartmentData,
} from "@/api/datacenter/nondashboard";

export default {
  name: "NonCoreDataDashboard",
  components: {
    CountTo,
    LineChart,
    PieChart,
  },
  data() {
    return {
      nonCoreData: [
        { title: "二级单位数", value: 336, desc: "系统二级单位总数" },
        { title: "业务总金额", value: 34711.74, desc: "业务累计金额(元)" },
        { title: "待审核业务", value: 18, desc: "待处理业务数量" },
        { title: "订单总量", value: 596, desc: "系统订单总数" },
      ],
      departmentData: [
        {
          name: "广州分部",
          count: 325,
          amount: 23456.78,
          ratio: 87,
          status: "正常",
        },
        {
          name: "深圳分部",
          count: 208,
          amount: 18765.43,
          ratio: 92,
          status: "正常",
        },
        {
          name: "佛山分部",
          count: 156,
          amount: 12345.67,
          ratio: 76,
          status: "正常",
        },
        {
          name: "东莞分部",
          count: 142,
          amount: 9876.54,
          ratio: 68,
          status: "正常",
        },
        {
          name: "珠海分部",
          count: 98,
          amount: 7654.32,
          ratio: 45,
          status: "异常",
        },
      ],
    };
  },
  created() {
    // 调用实际API获取数据
    this.getNonCoreOverviewData();
    this.getDepartmentTableData();
  },
  mounted() {
    // 在nextTick中调用以确保DOM已完全渲染
    this.$nextTick(() => {
      this.initRegionDistributionChart();
      this.initDepartmentTrendChart();
    });
  },
  methods: {
    // 获取非核心数据概览
    getNonCoreOverviewData() {
      getNonCoreData()
        .then((response) => {
          const { data } = response;
          this.nonCoreData[0].value = data.totalDepartments;
          this.nonCoreData[1].value = data.totalAmount;
          this.nonCoreData[2].value = data.pendingTasks;
          this.nonCoreData[3].value = data.totalOrders;
        })
        .catch((error) => {
          console.error("获取非核心数据概览失败:", error);
        });
    },

    // 获取二级单位数据
    getDepartmentTableData() {
      getDepartmentData()
        .then((response) => {
          this.departmentData = response.data;
        })
        .catch((error) => {
          console.error("获取二级单位数据失败:", error);
        });
    },

    // 初始化区域分布图表
    initRegionDistributionChart() {
      getRegionDistributionData()
        .then((response) => {
          const data = response.data;

          // 确保组件已挂载且refs可用
          this.$nextTick(() => {
            if (this.$refs.regionDistributionChart) {
              this.$refs.regionDistributionChart.setOptions({
                title: {
                  text: "区域分布",
                },
                tooltip: {
                  trigger: "item",
                  formatter: "{a} <br/>{b} : {c} ({d}%)",
                },
                series: [
                  {
                    name: "区域分布",
                    type: "pie",
                    radius: [15, 95],
                    center: ["50%", "38%"],
                    data: data,
                    animationEasing: "cubicInOut",
                    animationDuration: 2600,
                  },
                ],
              });
            }
          });
        })
        .catch((error) => {
          console.error("获取区域分布数据失败:", error);
        });
    },

    // 初始化二级单位业务趋势图表
    initDepartmentTrendChart() {
      getDepartmentTrendData()
        .then((response) => {
          const { data } = response;

          // 确保组件已挂载且refs可用
          this.$nextTick(() => {
            if (this.$refs.departmentTrendChart) {
              this.$refs.departmentTrendChart.setOptions({
                xAxis: {
                  data: data.months,
                },
                series: [
                  {
                    name: "业务量",
                    data: data.values,
                  },
                ],
              });
            }
          });
        })
        .catch((error) => {
          console.error("获取二级单位业务量趋势数据失败:", error);
        });
    },
  },
};
</script>

<style lang="scss" scoped>
.app-container {
  .box-card {
    margin-bottom: 10px;
  }
  .item-value {
    font-size: 30px;
    font-weight: bold;
    color: #333;
    margin: 15px 0;
  }
  .item-desc {
    font-size: 14px;
    color: #999;
  }
  .chart-wrapper {
    width: 100%;
    height: 300px;
  }
}
</style>
