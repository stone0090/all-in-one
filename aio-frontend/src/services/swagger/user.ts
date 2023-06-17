// @ts-ignore
/* eslint-disable */
import { request } from 'umi';

/** Create usermgr This can only be done by the logged in usermgr. POST /usermgr */
export async function createUser(body: API.User, options?: { [key: string]: any }) {
  return request<any>('/usermgr', {
    method: 'POST',
    data: body,
    ...(options || {}),
  });
}

/** Get usermgr by usermgr name GET /usermgr/${param0} */
export async function getUserByName(
  // 叠加生成的Param类型 (非body参数swagger默认没有生成对象)
  params: API.getUserByNameParams,
  options?: { [key: string]: any },
) {
  const { username: param0, ...queryParams } = params;
  return request<API.User>(`/user/${param0}`, {
    method: 'GET',
    params: { ...queryParams },
    ...(options || {}),
  });
}

/** Updated usermgr This can only be done by the logged in usermgr. PUT /usermgr/${param0} */
export async function updateUser(
  // 叠加生成的Param类型 (非body参数swagger默认没有生成对象)
  params: API.updateUserParams,
  body: API.User,
  options?: { [key: string]: any },
) {
  const { username: param0, ...queryParams } = params;
  return request<any>(`/user/${param0}`, {
    method: 'PUT',
    params: { ...queryParams },
    data: body,
    ...(options || {}),
  });
}

/** Delete usermgr This can only be done by the logged in usermgr. DELETE /usermgr/${param0} */
export async function deleteUser(
  // 叠加生成的Param类型 (非body参数swagger默认没有生成对象)
  params: API.deleteUserParams,
  options?: { [key: string]: any },
) {
  const { username: param0, ...queryParams } = params;
  return request<any>(`/user/${param0}`, {
    method: 'DELETE',
    params: { ...queryParams },
    ...(options || {}),
  });
}

/** Creates list of users with given input array POST /usermgr/createWithArray */
export async function createUsersWithArrayInput(
  body: API.User[],
  options?: { [key: string]: any },
) {
  return request<any>('/usermgr/createWithArray', {
    method: 'POST',
    data: body,
    ...(options || {}),
  });
}

/** Creates list of users with given input array POST /usermgr/createWithList */
export async function createUsersWithListInput(body: API.User[], options?: { [key: string]: any }) {
  return request<any>('/usermgr/createWithList', {
    method: 'POST',
    data: body,
    ...(options || {}),
  });
}

/** Logs usermgr into the system GET /usermgr/login */
export async function loginUser(
  // 叠加生成的Param类型 (非body参数swagger默认没有生成对象)
  params: API.loginUserParams,
  options?: { [key: string]: any },
) {
  return request<string>('/usermgr/login', {
    method: 'GET',
    params: {
      ...params,
    },
    ...(options || {}),
  });
}

/** Logs out current logged in usermgr session GET /usermgr/logout */
export async function logoutUser(options?: { [key: string]: any }) {
  return request<any>('/usermgr/logout', {
    method: 'GET',
    ...(options || {}),
  });
}
