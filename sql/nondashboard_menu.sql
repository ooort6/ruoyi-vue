-- 非核心数据看板菜单SQL
INSERT INTO sys_menu(menu_id, menu_name, parent_id, order_num, path, component, is_frame, is_cache, menu_type, visible, status, perms, icon, create_by, create_time, update_by, update_time, remark) 
VALUES (2003, '非核心数据看板', 2000, 2, 'nondashboard', 'datacenter/nondashboard/index', 1, 0, 'C', '0', '0', 'datacenter:nondashboard:query', 'chart', 'admin', sysdate(), '', NULL, '非核心数据看板菜单');

-- 非核心数据看板按钮权限
INSERT INTO sys_menu(menu_id, menu_name, parent_id, order_num, path, component, is_frame, is_cache, menu_type, visible, status, perms, icon, create_by, create_time, update_by, update_time, remark) 
VALUES (2004, '非核心数据看板查询', 2003, 1, '#', '', 1, 0, 'F', '0', '0', 'datacenter:nondashboard:query', '#', 'admin', sysdate(), '', NULL, ''); 