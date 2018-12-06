// CORE Imports
import Vue from "vue";
import App from "./components/App.vue";
import router from "./configs/router";
import validator from "./configs/validator";
import i18n from "./configs/i18n";
import store from "./configs/store";
import ApiService from "./services/shared/ApiService";
import "./utils/registerServiceWorker";

// 3th Imports
import VeeElement from "vee-element";
import VueTheMask from "vue-the-mask";
import Vue2Filters from "vue2-filters";
import Element from "element-ui";
// CSS Imports
import "./assets/linearicons/style.css";
import "mdi/scss/materialdesignicons.scss";
import "@/theme/main.sass";

// Vue Inits & Configs
Vue.use(Vue2Filters);
Vue.use(VueTheMask);
Vue.use(Element, { i18n: (key, value) => i18n.t(key, value) });
Vue.use(VeeElement, validator, false);
Vue.prototype.$bus = new Vue();
ApiService.init();

// Instância Vue
new Vue({
  router,
  store,
  i18n,
  render: h => h(App)
}).$mount("#app");
