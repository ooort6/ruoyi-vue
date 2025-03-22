<template>
  <div class="app-container">
    <el-row :gutter="20">
      <el-col :span="6" v-for="(item, index) in coreData" :key="index">
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
            <span>用户增长趋势</span>
          </div>
          <div class="chart-wrapper">
            <line-chart ref="userGrowthChart" />
          </div>
        </el-card>
      </el-col>
      <el-col :span="12">
        <el-card class="box-card" shadow="hover">
          <div slot="header" class="clearfix">
            <span>访问来源分布</span>
          </div>
          <div class="chart-wrapper">
            <pie-chart ref="visitSourceChart" />
          </div>
        </el-card>
      </el-col>
    </el-row>

    <el-row style="margin-top: 20px">
      <el-col :span="24">
        <el-card class="box-card" shadow="hover">
          <div slot="header" class="clearfix">
            <span>最近活动</span>
          </div>
          <el-table :data="activities" style="width: 100%">
            <el-table-column prop="timestamp" label="时间" width="180">
              <template slot-scope="scope">
                <i class="el-icon-time"></i>
                <span style="margin-left: 10px">{{ scope.row.timestamp }}</span>
              </template>
            </el-table-column>
            <el-table-column prop="username" label="用户" width="180">
            </el-table-column>
            <el-table-column prop="operation" label="操作"> </el-table-column>
            <el-table-column prop="ip" label="IP地址"> </el-table-column>
            <el-table-column prop="status" label="状态">
              <template slot-scope="scope">
                <el-tag
                  :type="scope.row.status === '成功' ? 'success' : 'danger'"
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
import {
  getCoreDataOverview,
  getUserGrowthData,
  getVisitSourceData,
  getRecentActivities,
} from "@/api/datacenter/dashboard";

export default {
  name: "DataCenterDashboard",
  components: {
    CountTo,
    LineChart,
    PieChart,
  },
  data() {
    return {
      coreData: [
        { title: "总用户数", value: 0, desc: "系统累计用户数量" },
        { title: "今日访问", value: 0, desc: "今日访问系统次数" },
        { title: "消息数量", value: 0, desc: "未处理消息数量" },
        { title: "任务数量", value: 0, desc: "待处理任务数量" },
      ],
      activities: [],
    };
  },
  created() {
    this.getOverviewData();
    this.getActivitiesData();
  },
  mounted() {
    this.initUserGrowthChart();
    this.initVisitSourceChart();
  },
  methods: {
    // 获取核心数据概览
    getOverviewData() {
      getCoreDataOverview().then((response) => {
        const { data } = response;
        this.coreData[0].value = data.totalUsers;
        this.coreData[1].value = data.todayVisits;
        this.coreData[2].value = data.messageCount;
        this.coreData[3].value = data.taskCount;
      });
    },
    // 获取最近活动数据
    getActivitiesData() {
      getRecentActivities().then((response) => {
        this.activities = response.data;
      });
    },
    // 初始化用户增长图表
    initUserGrowthChart() {
      getUserGrowthData().then((response) => {
        const { data } = response;
        this.$refs.userGrowthChart.setOptions({
          title: {
            text: "用户增长趋势",
          },
          xAxis: {
            data: data.months,
          },
          series: [
            {
              name: "用户数",
              data: data.values,
            },
          ],
        });
      });
    },
    // 初始化访问来源图表
    initVisitSourceChart() {
      getVisitSourceData().then((response) => {
        const { data } = response;
        this.$refs.visitSourceChart.setOptions({
          title: {
            text: "访问来源分布",
          },
          series: [
            {
              name: "访问来源",
              data: data.map((item) => ({
                name: item.name,
                value: item.value,
              })),
            },
          ],
        });
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
