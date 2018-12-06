import Vue from "vue";
import Vuex from "vuex";

import { NOT_PRODUCTION } from "../configs";
import createLogger from "../utils/helpers/logger";

import * as modules from "../store";

Vue.use(Vuex);

export default new Vuex.Store({
  modules: modules,
  strict: NOT_PRODUCTION,
  plugins: NOT_PRODUCTION ? [createLogger()] : []
});
