/*
  菜单命名规则：
  1. 一级菜单，6位数字表示，如101001
  2. 二级菜单，9位数字表示，如101002001
  3. 菜单从属关系，最好从菜单ID上能体现出来，如101002001从属于父菜单101002，菜单编号3位一级
  4. 每级菜单的树形关系事先设定好，上线后不允许变更。
 */
delete from t_auth_menu_resource where 1=1;

insert into t_auth_menu_resource (menu_id, menu_name, menu_url, menu_sort, menu_icon, parent_menu_id, uri_pattern, menu_type, menu_enabled, create_time, update_time)
values (101001, '欢迎', '/admin/dashboard/index', 0, 'fa-dashboard', null, '/admin/dashboard/**', '1', 1, sysdate(), sysdate());
insert into t_auth_menu_resource (menu_id, menu_name, menu_url, menu_sort, menu_icon, parent_menu_id, uri_pattern, menu_type, menu_enabled, create_time, update_time)
values (101002, '系统管理', '', 1, 'fa-gear', null, '', '0', 1, sysdate(), sysdate());
insert into t_auth_menu_resource (menu_id, menu_name, menu_url, menu_sort, menu_icon, parent_menu_id, uri_pattern, menu_type, menu_enabled, create_time, update_time)
values (101002001, '用户管理', '/admin/manageUser/users', 0, 'fa-user', 101002, '/admin/manageUser/**', '1', 1, sysdate(), sysdate());
insert into t_auth_menu_resource (menu_id, menu_name, menu_url, menu_sort, menu_icon, parent_menu_id, uri_pattern, menu_type, menu_enabled, create_time, update_time)
values (101002002, '角色管理', '/admin/manageRole/roles', 1, 'fa-users', 101002, '/admin/manageRole/**', '1', 1, sysdate(), sysdate());
insert into t_auth_menu_resource (menu_id, menu_name, menu_url, menu_sort, menu_icon, parent_menu_id, uri_pattern, menu_type, menu_enabled, create_time, update_time)
values (101002003, '权限查看', '/admin/manageAuthority/auth', 2, 'fa-cubes', 101002, '/admin/manageAuthority/**', '1', 1, sysdate(), sysdate());
insert into t_auth_menu_resource (menu_id, menu_name, menu_url, menu_sort, menu_icon, parent_menu_id, uri_pattern, menu_type, menu_enabled, create_time, update_time)
values (101002004, '菜单查看', '/admin/manageMenu/show', 3, 'fa-list', 101002, '/admin/manageMenu/**', '1', 0, sysdate(), sysdate());
insert into t_auth_menu_resource (menu_id, menu_name, menu_url, menu_sort, menu_icon, parent_menu_id, uri_pattern, menu_type, menu_enabled, create_time, update_time)
values (101002005, '接口查看', '/admin/manageApi/show', 4, 'fa-arrow-right', 101002, '/admin/manageApi/**', '1', 0, sysdate(), sysdate());

delete from t_auth_authority where 1=1;
insert into t_auth_authority (authority_id, authority_name, create_time, update_time)
values (100000, '管理员-仪表盘，拥有仪表盘查看权限', sysdate(), sysdate());
insert into t_auth_authority (authority_id, authority_name, create_time, update_time)
values (100001, '用户管理，包含新增/修改/删除系统用户，为用户分配角色的功能', sysdate(), sysdate());
insert into t_auth_authority (authority_id, authority_name, create_time, update_time)
values (100002, '角色管理，包含新增/修改/删除角色，为角色设置具备的权限等功能', sysdate(), sysdate());
insert into t_auth_authority (authority_id, authority_name, create_time, update_time)
values (100003, '系统权限查看，允许查看系统中定义的权限明细，包含权限中包含的资源（菜单或API）清单', sysdate(), sysdate());
insert into t_auth_authority (authority_id, authority_name, create_time, update_time)
values (100004, '菜单资源查看，允许查看系统中的菜单明细，菜单资源数据由系统开发人员负责维护', sysdate(), sysdate());
insert into t_auth_authority (authority_id, authority_name, create_time, update_time)
values (100005, '接口资源查看，允许查看系统中的API接口明细，数据由系统开发人员负责维护', sysdate(), sysdate());


/* 提供初始化数据，没使用sequence时，同步调整sequence的值 */
update t_sequence set t_sequence.VALUE = 100005 where t_sequence.NAME = 'SEQ_AUTH_AUTHORITY';

/*
  权限对应的菜单资源表，记录权限包含的菜单，允许对菜单目录的分配，但实际上只需要分配子菜单即可，一个权限对应一个或多个菜单。
 */
delete from t_auth_authority_resource where 1=1;
insert into t_auth_authority_resource (auth_resource_id, auth_type, authority_id, resource_id, create_time, update_time)
values (100000, '1', 100000, 101001, sysdate(), sysdate());
insert into t_auth_authority_resource (auth_resource_id, auth_type, authority_id, resource_id, create_time, update_time)
values (100001, '1', 100001, 101002, sysdate(), sysdate());
insert into t_auth_authority_resource (auth_resource_id, auth_type, authority_id, resource_id, create_time, update_time)
values (100002, '1', 100001, 101002001, sysdate(), sysdate());
insert into t_auth_authority_resource (auth_resource_id, auth_type, authority_id, resource_id, create_time, update_time)
values (100003, '1', 100002, 101002002, sysdate(), sysdate());
insert into t_auth_authority_resource (auth_resource_id, auth_type, authority_id, resource_id, create_time, update_time)
values (100004, '1', 100003, 101002, sysdate(), sysdate());
insert into t_auth_authority_resource (auth_resource_id, auth_type, authority_id, resource_id, create_time, update_time)
values (100005, '1', 100003, 101002003, sysdate(), sysdate());
insert into t_auth_authority_resource (auth_resource_id, auth_type, authority_id, resource_id, create_time, update_time)
values (100006, '1', 100004, 101002004, sysdate(), sysdate());
insert into t_auth_authority_resource (auth_resource_id, auth_type, authority_id, resource_id, create_time, update_time)
values (100007, '1', 100005, 101002005, sysdate(), sysdate());

update t_sequence set t_sequence.VALUE = 100008 where t_sequence.NAME = 'SEQ_AUTH_AUTHORITY_RESOURCE';

/*
  角色分配权限表，每个角色都拥有一个或多个权限
 */
delete from t_user_role_authority where role_id = 100001;
insert into t_user_role_authority (authorize_seq_id, role_id, authority_id, create_time, update_time)
values (100000, 100001, 100000, sysdate(), sysdate());
insert into t_user_role_authority (authorize_seq_id, role_id, authority_id, create_time, update_time)
values (100001, 100001, 100001, sysdate(), sysdate());
insert into t_user_role_authority (authorize_seq_id, role_id, authority_id, create_time, update_time)
values (100002, 100001, 100002, sysdate(), sysdate());
insert into t_user_role_authority (authorize_seq_id, role_id, authority_id, create_time, update_time)
values (100003, 100001, 100003, sysdate(), sysdate());
insert into t_user_role_authority (authorize_seq_id, role_id, authority_id, create_time, update_time)
values (100004, 100001, 100004, sysdate(), sysdate());
insert into t_user_role_authority (authorize_seq_id, role_id, authority_id, create_time, update_time)
values (100005, 100001, 100005, sysdate(), sysdate());

update t_sequence set t_sequence.VALUE = 100005 where t_sequence.NAME = 'SEQ_USER_ROLE_AUTHORITY';

/*
  角色表初始化数据，默认提供100001-管理员角色，其他角色通过界面新建
 */
delete from t_user_role where role_id = 100001;
insert into t_user_role (role_id, role_name, role_type, rank_number, is_valid, create_time, update_time)
values (100001, '管理员', 'SYS', 1, 1, sysdate(), sysdate());

update t_sequence set t_sequence.VALUE = 100002 where t_sequence.NAME = 'SEQ_USER_ROLE';

/*
  角色用户表，存储角色包含的用户信息，也可以视作用户拥有的角色信息。用户和角色组成唯一索引，不允许重复。
 */
delete from t_user_roleuser where user_id = 100001;
insert into t_user_roleuser (roleuser_seq_id, role_id, user_id, create_time, update_time)
values (100000, 100001, 100001, sysdate(), sysdate());

update t_sequence set t_sequence.VALUE = 100001 where t_sequence.NAME = 'SEQ_USER_ROLE_USER';

commit;