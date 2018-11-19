// CORE Imports
import Vue from "vue";
import App from "./components/App.vue";
import router from "./router";
import store from "./store";
import "./utils/registerServiceWorker";
import ApiService from "./services/shared/ApiService";
// 3th Imports
import ElementUI from "element-ui";
import locale from "element-ui/lib/locale/lang/pt-br";
import Vue2Filters from "vue2-filters";
// CSS Imports
import "mdi/scss/materialdesignicons.scss";
import "@/theme/main.sass";

// Vue Inits & Configs
Vue.prototype.$bus = new Vue();
Vue.config.productionTip = false;
Vue.use(ElementUI, { locale });
Vue.use(Vue2Filters);
ApiService.init();

// Instância Vue
new Vue({
  router,
  store,
  render: h => h(App)
}).$mount("#app");
