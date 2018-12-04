// CORE Imports
import Vue from "vue";
import App from "./components/App.vue";
import router from "./router";
import store from "./store";
import "./utils/registerServiceWorker";
import ApiService from "./services/shared/ApiService";
import messages from "./utils/messages/messages";

// 3th Imports
import VueI18n from "vue-i18n";
import ElementUI from "element-ui";
import VueTheMask from "vue-the-mask";
import Vue2Filters from "vue2-filters";

// CSS Imports
import "./assets/linearicons/style.css";
import "mdi/scss/materialdesignicons.scss";
import "@/theme/main.sass";

// Vue Inits & Configs
Vue.config.productionTip = false;
Vue.use(Vue2Filters);
Vue.use(VueI18n);
Vue.use(VueTheMask);

ApiService.init();
const i18n = new VueI18n({
  locale: "PT-BR",
  messages
});
Vue.use(ElementUI, {
  i18n: (key, value) => i18n.t(key, value)
});

Vue.prototype.$bus = new Vue();
Vue.prototype.$translator = i18n;

// Instância Vue
new Vue({
  router,
  store,
  i18n,
  render: h => h(App)
}).$mount("#app");
