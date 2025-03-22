import request from '@/utils/request'

// 获取核心数据概览
export function getCoreDataOverview() {
  return request({
    url: '/datacenter/dashboard/overview',
    method: 'get'
  })
}

// 获取用户增长趋势数据
export function getUserGrowthData() {
  return request({
    url: '/datacenter/dashboard/userGrowth',
    method: 'get'
  })
}

// 获取访问来源分布数据
export function getVisitSourceData() {
  return request({
    url: '/datacenter/dashboard/visitSource',
    method: 'get'
  })
}

// 获取最近活动记录
export function getRecentActivities() {
  return request({
    url: '/datacenter/dashboard/recentActivities',
    method: 'get'
  })
} 