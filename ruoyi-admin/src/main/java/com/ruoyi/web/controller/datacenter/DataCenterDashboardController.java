package com.ruoyi.web.controller.datacenter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.CrossOrigin;

import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;

/**
 * 数据中心核心数据看板
 * 
 * @author ruoyi
 */
@RestController
@RequestMapping("/datacenter/dashboard")
@CrossOrigin // 添加跨域支持
public class DataCenterDashboardController extends BaseController {

    /**
     * 获取核心数据概览
     */
    @PreAuthorize("@ss.hasPermi('datacenter:dashboard:query')")
    @GetMapping("/overview")
    public AjaxResult getOverview() {
        Map<String, Object> data = new HashMap<>();
        // 模拟数据，实际项目中应该从服务层获取真实数据
        data.put("totalUsers", 2386);
        data.put("todayVisits", 368);
        data.put("messageCount", 12);
        data.put("taskCount", 25);
        return AjaxResult.success(data);
    }

    /**
     * 获取用户增长趋势数据
     */
    @PreAuthorize("@ss.hasPermi('datacenter:dashboard:query')")
    @GetMapping("/userGrowth")
    public AjaxResult getUserGrowth() {
        Map<String, Object> data = new HashMap<>();
        List<String> months = new ArrayList<>();
        List<Integer> values = new ArrayList<>();

        // 模拟数据，实际项目中应该从服务层获取真实数据
        months.add("1月");
        months.add("2月");
        months.add("3月");
        months.add("4月");
        months.add("5月");
        months.add("6月");

        values.add(1856);
        values.add(1956);
        values.add(2042);
        values.add(2156);
        values.add(2268);
        values.add(2386);

        data.put("months", months);
        data.put("values", values);

        return AjaxResult.success(data);
    }

    /**
     * 获取访问来源分布数据
     */
    @PreAuthorize("@ss.hasPermi('datacenter:dashboard:query')")
    @GetMapping("/visitSource")
    public AjaxResult getVisitSource() {
        List<Map<String, Object>> data = new ArrayList<>();

        // 模拟数据，实际项目中应该从服务层获取真实数据
        Map<String, Object> direct = new HashMap<>();
        direct.put("name", "直接访问");
        direct.put("value", 335);

        Map<String, Object> search = new HashMap<>();
        search.put("name", "搜索引擎");
        search.put("value", 310);

        Map<String, Object> referral = new HashMap<>();
        referral.put("name", "外部链接");
        referral.put("value", 234);

        Map<String, Object> social = new HashMap<>();
        social.put("name", "社交媒体");
        social.put("value", 135);

        Map<String, Object> email = new HashMap<>();
        email.put("name", "邮件营销");
        email.put("value", 148);

        data.add(direct);
        data.add(search);
        data.add(referral);
        data.add(social);
        data.add(email);

        return AjaxResult.success(data);
    }

    /**
     * 获取最近系统活动
     */
    @PreAuthorize("@ss.hasPermi('datacenter:dashboard:query')")
    @GetMapping("/recentActivities")
    public AjaxResult getRecentActivities() {
        List<Map<String, Object>> data = new ArrayList<>();

        // 模拟数据，实际项目中应该从服务层获取真实数据
        addActivity(data, "2023-03-22 10:30:24", "admin", "登录系统", "127.0.0.1", "成功");
        addActivity(data, "2023-03-22 10:25:42", "test", "修改用户配置", "192.168.1.5", "成功");
        addActivity(data, "2023-03-22 10:18:16", "wang", "上传文件", "192.168.1.36", "失败");
        addActivity(data, "2023-03-22 10:10:53", "admin", "添加新用户", "127.0.0.1", "成功");
        addActivity(data, "2023-03-22 10:05:38", "zhang", "导出数据", "192.168.1.42", "成功");
        addActivity(data, "2023-03-22 09:58:21", "li", "登录系统", "192.168.1.56", "成功");

        return AjaxResult.success(data);
    }

    private void addActivity(List<Map<String, Object>> data, String timestamp, String username,
            String operation, String ip, String status) {
        Map<String, Object> activity = new HashMap<>();
        activity.put("timestamp", timestamp);
        activity.put("username", username);
        activity.put("operation", operation);
        activity.put("ip", ip);
        activity.put("status", status);
        data.add(activity);
    }
}