-- 数据中心主菜单
INSERT INTO sys_menu(menu_id, menu_name, parent_id, order_num, path, component, is_frame, is_cache, menu_type, visible, status, perms, icon, create_by, create_time, update_by, update_time, remark) 
VALUES (2000, '数据中心', 0, 5, 'datacenter', NULL, 1, 0, 'M', '0', '0', '', 'datacenter', 'admin', sysdate(), '', NULL, '数据中心菜单');

-- 核心数据看板子菜单
INSERT INTO sys_menu(menu_id, menu_name, parent_id, order_num, path, component, is_frame, is_cache, menu_type, visible, status, perms, icon, create_by, create_time, update_by, update_time, remark) 
VALUES (2001, '核心数据看板', 2000, 1, 'dashboard', 'datacenter/dashboard/index', 1, 0, 'C', '0', '0', 'datacenter:dashboard:query', 'dashboard-data', 'admin', sysdate(), '', NULL, '核心数据看板菜单');

-- 核心数据看板按钮
INSERT INTO sys_menu(menu_id, menu_name, parent_id, order_num, path, component, is_frame, is_cache, menu_type, visible, status, perms, icon, create_by, create_time, update_by, update_time, remark) 
VALUES (2002, '核心数据看板查询', 2001, 1, '#', '', 1, 0, 'F', '0', '0', 'datacenter:dashboard:query', '#', 'admin', sysdate(), '', NULL, '');
