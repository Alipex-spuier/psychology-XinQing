import axios from 'axios'

let base = 'http://8.217.178.86:8080'

/** *********user***********/
export const requestLogin = params => { return axios.post(`${base}/api/v1/account/adminLogin`, params) }

export const getUserList = params => { return axios.post(`${base}/api/v1/user/indexPageByName`, params) }

export const getUserListPage = (params, auth) => { return axios.post(`${base}/api/v1/user/indexPage`, params, { headers: { Authorization: `${auth}` } }) }

export const getUsersListByName = (params, auth) => { return axios.post(`${base}/api/v1/user/indexPageByName`, params, { headers: { Authorization: `${auth}` } }) }

export const removeUser = params => { return axios.get(`${base}/user/remove`, { params: params }) }

export const batchRemoveUser = params => { return axios.get(`${base}/user/batchremove`, { params: params }) }

export const editUser = (params, auth) => { return axios.put(`${base}/api/v1/user/update`, params, { headers: { Authorization: `${auth}` } }) }

export const addUser = (params, auth) => { return axios.post(`${base}/api/v1/user/save`, params, { headers: { Authorization: `${auth}` } }) }

/** *********appointment***********/
export const getAppointmentListPage = (params, auth) => { return axios.post(`${base}/api/v1/appointment/indexPage`, params, { headers: { Authorization: `${auth}` } }) }

export const getAppointmentListByExpertId = (params, auth) => { return axios.post(`${base}/api/v1/appointment/indexPageByExpertId`, params, { headers: { Authorization: `${auth}` } }) }

export const addAppointment = (params, auth) => { return axios.post(`${base}/api/v1/appointment/appoint`, params, { headers: { Authorization: `${auth}` } }) }

export const removeAppointment = (auth, aptId) => { return axios.delete(`${base}/api/v1/appointment/delete/${aptId}`, { headers: { Authorization: `${auth}` } }) }

export const editAppointment = (params, auth) => { return axios.put(`${base}/api/v1/appointment/update`, params, { headers: { Authorization: `${auth}` } }) }

/** *********expert***********/
export const getExpertsListPage = (params, auth) => { return axios.post(`${base}/api/v1/expert/indexPage`, params, { headers: { Authorization: `${auth}` } }) }

export const getExpertsListByName = (params, auth) => { return axios.post(`${base}/api/v1/expert/indexPageByName`, params, { headers: { Authorization: `${auth}` } }) }

export const getExpertsListByChecked_1 = (params, auth) => { return axios.post(`${base}/api/v1/expert/indexPageCheck1`, params, { headers: { Authorization: `${auth}` } }) }
export const getExpertsListByChecked_0 = (params, auth) => { return axios.post(`${base}/api/v1/expert/indexPageCheck0`, params, { headers: { Authorization: `${auth}` } }) }

export const addExpert = (params, auth) => { return axios.post(`${base}/api/v1/expert/save`, params, { headers: { Authorization: `${auth}` } }) }

export const editExperts = (params, auth) => { return axios.put(`${base}/api/v1/expert/update`, params, { headers: { Authorization: `${auth}` } }) }
