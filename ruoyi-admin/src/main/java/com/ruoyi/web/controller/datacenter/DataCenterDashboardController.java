package com.ruoyi.web.controller.datacenter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;

/**
 * 数据中心核心数据看板控制器
 * 
 * @author ruoyi
 */
@RestController
@RequestMapping("/datacenter/dashboard")
public class DataCenterDashboardController extends BaseController {
    /**
     * 获取核心数据总览
     */
    @PreAuthorize("@ss.hasPermi('datacenter:dashboard:list')")
    @GetMapping("/overview")
    public AjaxResult getOverview() {
        Map<String, Object> data = new HashMap<>();
        data.put("totalUserCount", 1285);
        data.put("todayVisits", 354);
        data.put("messageCount", 42);
        data.put("taskCount", 87);
        return AjaxResult.success(data);
    }

    /**
     * 获取用户增长趋势
     */
    @PreAuthorize("@ss.hasPermi('datacenter:dashboard:list')")
    @GetMapping("/userGrowth")
    public AjaxResult getUserGrowth() {
        Map<String, Object> data = new HashMap<>();
        List<String> months = new ArrayList<>();
        months.add("1月");
        months.add("2月");
        months.add("3月");
        months.add("4月");
        months.add("5月");
        months.add("6月");
        months.add("7月");
        months.add("8月");
        months.add("9月");
        months.add("10月");
        months.add("11月");
        months.add("12月");

        List<Integer> counts = new ArrayList<>();
        counts.add(820);
        counts.add(932);
        counts.add(901);
        counts.add(934);
        counts.add(1290);
        counts.add(1330);
        counts.add(1320);
        counts.add(1450);
        counts.add(1520);
        counts.add(1600);
        counts.add(1700);
        counts.add(1800);

        data.put("months", months);
        data.put("counts", counts);
        return AjaxResult.success(data);
    }

    /**
     * 获取访问来源分布
     */
    @PreAuthorize("@ss.hasPermi('datacenter:dashboard:list')")
    @GetMapping("/visitSource")
    public AjaxResult getVisitSource() {
        List<Map<String, Object>> data = new ArrayList<>();

        Map<String, Object> item1 = new HashMap<>();
        item1.put("name", "直接访问");
        item1.put("value", 335);
        data.add(item1);

        Map<String, Object> item2 = new HashMap<>();
        item2.put("name", "邮件营销");
        item2.put("value", 310);
        data.add(item2);

        Map<String, Object> item3 = new HashMap<>();
        item3.put("name", "联盟广告");
        item3.put("value", 234);
        data.add(item3);

        Map<String, Object> item4 = new HashMap<>();
        item4.put("name", "视频广告");
        item4.put("value", 135);
        data.add(item4);

        Map<String, Object> item5 = new HashMap<>();
        item5.put("name", "搜索引擎");
        item5.put("value", 1548);
        data.add(item5);

        return AjaxResult.success(data);
    }

    /**
     * 获取最近系统活动
     */
    @PreAuthorize("@ss.hasPermi('datacenter:dashboard:list')")
    @GetMapping("/recentActivities")
    public AjaxResult getRecentActivities() {
        List<Map<String, Object>> data = new ArrayList<>();

        Map<String, Object> item1 = new HashMap<>();
        item1.put("time", "2024-03-22 08:23:15");
        item1.put("user", "张经理");
        item1.put("activity", "登录系统");
        item1.put("ip", "192.168.1.100");
        item1.put("status", "成功");
        data.add(item1);

        Map<String, Object> item2 = new HashMap<>();
        item2.put("time", "2024-03-22 08:20:28");
        item2.put("user", "李主管");
        item2.put("activity", "更新部门信息");
        item2.put("ip", "192.168.1.101");
        item2.put("status", "成功");
        data.add(item2);

        Map<String, Object> item3 = new HashMap<>();
        item3.put("time", "2024-03-22 08:15:47");
        item3.put("user", "王工程师");
        item3.put("activity", "查看系统日志");
        item3.put("ip", "192.168.1.102");
        item3.put("status", "成功");
        data.add(item3);

        Map<String, Object> item4 = new HashMap<>();
        item4.put("time", "2024-03-22 08:10:32");
        item4.put("user", "赵分析师");
        item4.put("activity", "导出报表数据");
        item4.put("ip", "192.168.1.103");
        item4.put("status", "成功");
        data.add(item4);

        Map<String, Object> item5 = new HashMap<>();
        item5.put("time", "2024-03-22 08:05:16");
        item5.put("user", "周测试");
        item5.put("activity", "修改密码");
        item5.put("ip", "192.168.1.104");
        item5.put("status", "失败");
        data.add(item5);

        return AjaxResult.success(data);
    }
}