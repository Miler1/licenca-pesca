import Vue from "vue";

export const NOT_PRODUCTION = process.env.NODE_ENV !== "production";

Vue.config.devtools = NOT_PRODUCTION;
Vue.config.productionTip = false;
