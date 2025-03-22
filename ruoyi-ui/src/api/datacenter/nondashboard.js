import request from '@/utils/request'

// 获取非核心数据概览
export function getNonCoreData() {
  return request({
    url: '/datacenter/nondashboard/overview',
    method: 'get'
  })
}

// 获取二级单位业务量趋势数据
export function getDepartmentTrendData() {
  return request({
    url: '/datacenter/nondashboard/departmentTrend',
    method: 'get'
  })
}

// 获取区域分布数据
export function getRegionDistributionData() {
  return request({
    url: '/datacenter/nondashboard/regionDistribution',
    method: 'get'
  })
}

// 获取二级单位数据
export function getDepartmentData() {
  return request({
    url: '/datacenter/nondashboard/departmentData',
    method: 'get'
  })
} 