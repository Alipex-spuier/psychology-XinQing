import axios from 'axios'

let base = 'http://8.217.178.86:8080'

export const requestLogin = params => { return axios.post(`${base}/api/v1/account/adminLogin`, params) }

export const getUserList = params => { return axios.post(`${base}/api/v1/user/indexPageByName`, params) }

export const getUserListPage = (params, auth) => { return axios.post(`${base}/api/v1/user/indexPage`, params, { headers: { Authorization: `${auth}` } }) }

export const getUsersListByName = (params, auth) => { return axios.post(`${base}/api/v1/user/indexPageByName`, params, { headers: { Authorization: `${auth}` } }) }

export const removeUser = params => { return axios.get(`${base}/user/remove`, { params: params }) }

export const batchRemoveUser = params => { return axios.get(`${base}/user/batchremove`, { params: params }) }

export const editUser = (params, auth) => { return axios.put(`${base}/api/v1/user/update`, params, { headers: { Authorization: `${auth}` } }) }

export const addUser = params => { return axios.get(`${base}/user/add`, { params: params }) }
