import Vue from 'vue'
import App from './App'
import share from "pages/content/share/share.vue"
Vue.config.productionTip = false

App.mpType = 'app'

const app = new Vue({
	...App
})
app.$mount()
