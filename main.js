import Vue from 'vue'
import App from './App'

Vue.config.productionTip = false

App.mpType = 'app'

Vue.prototype.$baseURL = 'http://170.106.183.24:8080'; 
Vue.prototype.$baseFaceURL = 'http://170.106.183.24:8084'; 
Vue.prototype.$basePhotoURL = 'http://170.106.183.24:8081'; 
const app = new Vue({
    ...App
})
app.$mount()
