// CORE Imports
import Vue from "vue";
import App from "./components/App.vue";
import router from "./router";
import store from "./store";
import "./utils/registerServiceWorker";
import ApiService from "./services/shared/ApiService";
import messages from "./utils/messages/messages";

// 3th Imports
import ElementUI from "element-ui";
import locale from "element-ui/lib/locale/lang/pt-br";
import Vue2Filters from "vue2-filters";
import VueI18n from "vue-i18n";
import VueTheMask from "vue-the-mask";

// CSS Imports
import "./assets/linearicons/style.css";
import "mdi/scss/materialdesignicons.scss";
import "@/theme/main.sass";

// Vue Inits & Configs
Vue.prototype.$bus = new Vue();
Vue.config.productionTip = false;
Vue.use(ElementUI, { locale });
Vue.use(Vue2Filters);
Vue.use(VueI18n);
Vue.use(VueTheMask);
ApiService.init();
const i18n = new VueI18n({
  locale: "PT-BR",
  messages
});
Vue.prototype.$translator = i18n;

// Instância Vue
new Vue({
  router,
  store,
  i18n,
  render: h => h(App)
}).$mount("#app");
