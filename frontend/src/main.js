import Vue from 'vue'
import App from './App.vue'
import router from './router'
import store from "./store"
import BootstrapVue from 'bootstrap-vue'
import MenuIcon from 'vue-material-design-icons/Menu.vue';
 
Vue.component('menu-icon', MenuIcon);
import "./assets/default.css";

Vue.use(BootstrapVue);
Vue.config.productionTip = false



new Vue({
  store,
  router,
  render: h => h(App)
}).$mount('#app')

