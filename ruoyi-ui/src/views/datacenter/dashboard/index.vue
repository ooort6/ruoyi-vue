<template>
  <div class="app-container">
    <el-row :gutter="20">
      <el-col :span="6">
        <el-card class="box-card">
          <div slot="header" class="clearfix">
            <span>总用户数</span>
          </div>
          <div class="data-item">
            <count-to
              :start-val="0"
              :end-val="totalUserCount"
              :duration="2000"
              class="data-count"
            ></count-to>
            <div class="data-icon">
              <svg-icon
                icon-class="user"
                style="font-size: 40px; color: #409eff"
              ></svg-icon>
            </div>
          </div>
        </el-card>
      </el-col>
      <el-col :span="6">
        <el-card class="box-card">
          <div slot="header" class="clearfix">
            <span>今日访问量</span>
          </div>
          <div class="data-item">
            <count-to
              :start-val="0"
              :end-val="todayVisits"
              :duration="2000"
              class="data-count"
            ></count-to>
            <div class="data-icon">
              <svg-icon
                icon-class="peoples"
                style="font-size: 40px; color: #f56c6c"
              ></svg-icon>
            </div>
          </div>
        </el-card>
      </el-col>
      <el-col :span="6">
        <el-card class="box-card">
          <div slot="header" class="clearfix">
            <span>系统消息</span>
          </div>
          <div class="data-item">
            <count-to
              :start-val="0"
              :end-val="messageCount"
              :duration="2000"
              class="data-count"
            ></count-to>
            <div class="data-icon">
              <svg-icon
                icon-class="message"
                style="font-size: 40px; color: #67c23a"
              ></svg-icon>
            </div>
          </div>
        </el-card>
      </el-col>
      <el-col :span="6">
        <el-card class="box-card">
          <div slot="header" class="clearfix">
            <span>总任务数</span>
          </div>
          <div class="data-item">
            <count-to
              :start-val="0"
              :end-val="taskCount"
              :duration="2000"
              class="data-count"
            ></count-to>
            <div class="data-icon">
              <svg-icon
                icon-class="job"
                style="font-size: 40px; color: #e6a23c"
              ></svg-icon>
            </div>
          </div>
        </el-card>
      </el-col>
    </el-row>

    <el-row :gutter="20" style="margin-top: 20px">
      <el-col :span="12">
        <el-card class="box-card">
          <div slot="header" class="clearfix">
            <span>用户增长趋势</span>
          </div>
          <div class="chart-container">
            <line-chart
              id="userGrowthChart"
              height="350px"
              ref="userGrowthChart"
            ></line-chart>
          </div>
        </el-card>
      </el-col>
      <el-col :span="12">
        <el-card class="box-card">
          <div slot="header" class="clearfix">
            <span>访问来源分布</span>
          </div>
          <div class="chart-container">
            <pie-chart
              id="visitSourceChart"
              height="350px"
              ref="visitSourceChart"
            ></pie-chart>
          </div>
        </el-card>
      </el-col>
    </el-row>

    <el-row :gutter="20" style="margin-top: 20px">
      <el-col :span="24">
        <el-card class="box-card">
          <div slot="header" class="clearfix">
            <span>最近系统活动</span>
          </div>
          <el-table :data="recentActivities" stripe style="width: 100%">
            <el-table-column
              prop="time"
              label="时间"
              width="180"
            ></el-table-column>
            <el-table-column
              prop="user"
              label="用户"
              width="120"
            ></el-table-column>
            <el-table-column prop="activity" label="活动内容"></el-table-column>
            <el-table-column
              prop="ip"
              label="IP地址"
              width="150"
            ></el-table-column>
            <el-table-column prop="status" label="状态" width="100">
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
  name: "CoreDataDashboard",
  components: {
    CountTo,
    LineChart,
    PieChart,
  },
  data() {
    return {
      totalUserCount: 0,
      todayVisits: 0,
      messageCount: 0,
      taskCount: 0,
      recentActivities: [],
    };
  },
  created() {
    this.getOverviewData();
    this.getActivitiesData();
  },
  mounted() {
    this.initCharts();
  },
  methods: {
    // 获取数据概览
    getOverviewData() {
      getCoreDataOverview().then((response) => {
        const data = response.data;
        this.totalUserCount = data.totalUserCount;
        this.todayVisits = data.todayVisits;
        this.messageCount = data.messageCount;
        this.taskCount = data.taskCount;
      });
    },
    // 获取系统活动数据
    getActivitiesData() {
      getRecentActivities().then((response) => {
        this.recentActivities = response.data;
      });
    },
    // 初始化图表
    initCharts() {
      this.initUserGrowthChart();
      this.initVisitSourceChart();
    },
    // 初始化用户增长趋势图
    initUserGrowthChart() {
      getUserGrowthData().then((response) => {
        const data = response.data;
        this.$refs.userGrowthChart.setOptions({
          title: {
            text: "近30天用户增长趋势",
          },
          xAxis: {
            data: data.months,
          },
          series: [
            {
              name: "用户数",
              data: data.counts,
            },
          ],
        });
      });
    },
    // 初始化访问来源分布图
    initVisitSourceChart() {
      getVisitSourceData().then((response) => {
        this.$refs.visitSourceChart.setOptions({
          title: {
            text: "访问来源分布",
          },
          series: [
            {
              name: "访问来源",
              data: response.data,
            },
          ],
        });
      });
    },
  },
};
</script>

<style scoped>
.data-item {
  display: flex;
  justify-content: space-between;
  align-items: center;
  padding: 10px 0;
}
.data-count {
  font-size: 28px;
  font-weight: bold;
  color: #303133;
}
.chart-container {
  position: relative;
  width: 100%;
  height: 350px;
}
</style>
