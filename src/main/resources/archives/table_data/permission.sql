INSERT INTO common_core.permission
(
  name,
  deleted,
  uri,
  TYPE,
  puri
)
VALUES
(
  'app',
  0,
  'app',
  1,
  NULL
);

INSERT INTO common_core.permission
(
  name,
  deleted,
  uri,
  TYPE,
  puri
)
VALUES
(
  '系统管理',
  0,
  'app.system',
  1,
  'app'
);

INSERT INTO common_core.permission
(
  name,
  deleted,
  uri,
  TYPE,
  puri
)
VALUES
(
  '用户管理',
  0,
  'app.system.user',
  1,
  'app.system.user'
);

INSERT INTO common_core.permission
(
  name,
  deleted,
  uri,
  TYPE,
  puri
)
VALUES
(
  '新增用户',
  0,
  'app.system.user.add',
  2,
  'app.system.user'
);

INSERT INTO common_core.permission
(
  name,
  deleted,
  uri,
  TYPE,
  puri
)
VALUES
(
  '编辑用户',
  0,
  'app.system.user.edit',
  2,
  'app.system.user'
);

INSERT INTO common_core.permission
(
  name,
  deleted,
  uri,
  TYPE,
  puri
)
VALUES
(
  '删除用户',
  0,
  'app.system.user.delete',
  2,
  'app.system.user'
);

INSERT INTO common_core.permission
(
  name,
  deleted,
  uri,
  TYPE,
  puri
)
VALUES
(
  '禁用/启用用户',
  0,
  'app.system.user.enabled',
  2,
  'app.system.user'
);

INSERT INTO common_core.permission
(
  name,
  deleted,
  uri,
  TYPE,
  puri
)
VALUES
(
  '权限管理',
  0,
  'app.system.permission',
  1,
  'app.system'
);

INSERT INTO common_core.permission
(
  name,
  deleted,
  uri,
  TYPE,
  puri
)
VALUES
(
  '新增权限',
  0,
  'app.system.permission.add',
  2,
  'app.system.permission'
);

INSERT INTO common_core.permission
(
  name,
  deleted,
  uri,
  TYPE,
  puri
)
VALUES
(
  '编辑权限',
  0,
  'app.system.permission.edit',
  2,
  'app.system.permission'
);

INSERT INTO common_core.permission
(
  name,
  deleted,
  uri,
  TYPE,
  puri
)
VALUES
(
  '删除权限',
  0,
  'app.system.permission.delete',
  2,
  'app.system.permission'
);

INSERT INTO common_core.permission
(
  name,
  deleted,
  uri,
  TYPE,
  puri
)
VALUES
(
  '角色管理',
  0,
  'app.system.role',
  1,
  'app.system'
);

INSERT INTO common_core.permission
(
  name,
  deleted,
  uri,
  TYPE,
  puri
)
VALUES
(
  '新增角色',
  0,
  'app.system.role.add',
  2,
  'app.system.role'
);

INSERT INTO common_core.permission
(
  name,
  deleted,
  uri,
  TYPE,
  puri
)
VALUES
(
  '编辑角色',
  0,
  'app.system.role.edit',
  2,
  'app.system.role'
);

INSERT INTO common_core.permission
(
  name,
  deleted,
  uri,
  TYPE,
  puri
)
VALUES
(
  '删除角色',
  0,
  'app.system.role.delete',
  2,
  'app.system.role'
);

INSERT INTO common_core.permission
(
  name,
  deleted,
  uri,
  TYPE,
  puri
)
VALUES
(
  '禁用/启用角色',
  0,
  'app.system.role.enabled',
  2,
  'app.system.role'
);

INSERT INTO common_core.permission
(
  name,
  deleted,
  uri,
  TYPE,
  puri
)
VALUES
(
  '常量配置',
  0,
  'app.system.constant',
  1,
  'app.system'
);

INSERT INTO common_core.permission
(
  name,
  deleted,
  uri,
  TYPE,
  puri
)
VALUES
(
  '新增常量',
  0,
  'app.system.constant.add',
  2,
  'app.system.constant'
);

INSERT INTO common_core.permission
(
  name,
  deleted,
  uri,
  TYPE,
  puri
)
VALUES
(
  '编辑常量',
  0,
  'app.system.constant.edit',
  2,
  'app.system.constant'
);

INSERT INTO common_core.permission
(
  name,
  deleted,
  uri,
  TYPE,
  puri
)
VALUES
(
  '删除常量',
  0,
  'app.system.constant.delete',
  2,
  'app.system.constant'
);

INSERT INTO common_core.permission
(
  name,
  deleted,
  uri,
  TYPE,
  puri
)
VALUES
(
  '禁用/启用常量',
  0,
  'app.system.constant.enabled',
  2,
  'app.system.constant'
);


COMMIT;
