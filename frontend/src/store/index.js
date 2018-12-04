import Vue from "vue";
import Vuex from "vuex";

import createLogger from "../utils/helpers/logger";

import global from "./modules/global";
import acessar from "./modules/acessar";
import consultar from "./modules/consultar";
import registrar from "./modules/registrar";

Vue.use(Vuex);

const debug = process.env.NODE_ENV !== "production";

export default new Vuex.Store({
  modules: {
    global,
    acessar,
    consultar,
    registrar
  },
  strict: debug,
  plugins: debug ? [createLogger()] : []
});
