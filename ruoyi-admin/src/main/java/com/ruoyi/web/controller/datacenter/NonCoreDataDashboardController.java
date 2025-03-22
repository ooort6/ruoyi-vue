package com.ruoyi.web.controller.datacenter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.CrossOrigin;

import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;

/**
 * 数据中心非核心数据看板
 *
 * @author ruoyi
 */
@RestController
@CrossOrigin
@RequestMapping("/datacenter/nondashboard")
public class NonCoreDataDashboardController extends BaseController {

    /**
     * 获取非核心数据概览
     */
    // @PreAuthorize("@ss.hasPermi('datacenter:nondashboard:query')")
    @GetMapping("/overview")
    public AjaxResult getOverviewData() {
        Map<String, Object> data = new HashMap<>();
        data.put("totalDepartments", 336);
        data.put("totalAmount", 34711.74);
        data.put("pendingTasks", 18);
        data.put("totalOrders", 596);
        return AjaxResult.success(data);
    }

    /**
     * 获取二级单位业务量趋势数据
     */
    // @PreAuthorize("@ss.hasPermi('datacenter:nondashboard:query')")
    @GetMapping("/departmentTrend")
    public AjaxResult getDepartmentTrendData() {
        Map<String, Object> data = new HashMap<>();

        List<String> months = new ArrayList<>();
        months.add("1月");
        months.add("2月");
        months.add("3月");
        months.add("4月");
        months.add("5月");
        months.add("6月");

        List<Integer> values = new ArrayList<>();
        values.add(10);
        values.add(15);
        values.add(12);
        values.add(18);
        values.add(14);
        values.add(20);

        data.put("months", months);
        data.put("values", values);

        return AjaxResult.success(data);
    }

    /**
     * 获取区域分布数据
     */
    // @PreAuthorize("@ss.hasPermi('datacenter:nondashboard:query')")
    @GetMapping("/regionDistribution")
    public AjaxResult getRegionDistributionData() {
        List<Map<String, Object>> data = new ArrayList<>();

        Map<String, Object> region1 = new HashMap<>();
        region1.put("name", "广州");
        region1.put("value", 35);
        data.add(region1);

        Map<String, Object> region2 = new HashMap<>();
        region2.put("name", "深圳");
        region2.put("value", 28);
        data.add(region2);

        Map<String, Object> region3 = new HashMap<>();
        region3.put("name", "佛山");
        region3.put("value", 16);
        data.add(region3);

        Map<String, Object> region4 = new HashMap<>();
        region4.put("name", "东莞");
        region4.put("value", 12);
        data.add(region4);

        Map<String, Object> region5 = new HashMap<>();
        region5.put("name", "珠海");
        region5.put("value", 9);
        data.add(region5);

        return AjaxResult.success(data);
    }

    /**
     * 获取二级单位数据
     */
    // @PreAuthorize("@ss.hasPermi('datacenter:nondashboard:query')")
    @GetMapping("/departmentData")
    public AjaxResult getDepartmentData() {
        List<Map<String, Object>> data = new ArrayList<>();

        Map<String, Object> dept1 = new HashMap<>();
        dept1.put("name", "广州分部");
        dept1.put("count", 325);
        dept1.put("amount", 23456.78);
        dept1.put("ratio", 87);
        dept1.put("status", "正常");
        data.add(dept1);

        Map<String, Object> dept2 = new HashMap<>();
        dept2.put("name", "深圳分部");
        dept2.put("count", 208);
        dept2.put("amount", 18765.43);
        dept2.put("ratio", 92);
        dept2.put("status", "正常");
        data.add(dept2);

        Map<String, Object> dept3 = new HashMap<>();
        dept3.put("name", "佛山分部");
        dept3.put("count", 156);
        dept3.put("amount", 12345.67);
        dept3.put("ratio", 76);
        dept3.put("status", "正常");
        data.add(dept3);

        Map<String, Object> dept4 = new HashMap<>();
        dept4.put("name", "东莞分部");
        dept4.put("count", 142);
        dept4.put("amount", 9876.54);
        dept4.put("ratio", 68);
        dept4.put("status", "正常");
        data.add(dept4);

        Map<String, Object> dept5 = new HashMap<>();
        dept5.put("name", "珠海分部");
        dept5.put("count", 98);
        dept5.put("amount", 7654.32);
        dept5.put("ratio", 45);
        dept5.put("status", "异常");
        data.add(dept5);

        return AjaxResult.success(data);
    }
}