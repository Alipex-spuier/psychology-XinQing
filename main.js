import Vue from 'vue'
import App from './App'

Vue.config.productionTip = false

App.mpType = 'app'
//
Vue.prototype.$baseURL = 'http://8.217.178.86:8080'; 
Vue.prototype.$baseFaceURL = 'http://8.217.178.86:8084'; 
Vue.prototype.$basePhotoURL = 'http://8.217.178.86:8081'; 
//Vue.prototype.$baseChatURL=
const app = new Vue({
    ...App
})
app.$mount()
