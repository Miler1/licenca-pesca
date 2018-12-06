import Vue from "vue";
import VueI18n from "vue-i18n";

import Properties from "../properties";
import messages from "../utils/messages/messages";

Vue.use(VueI18n);

// PT-BR
export const default_lang = Object
  .getOwnPropertyNames(Properties.LANGUAGE_INDEX)[Properties.LANGUAGE_INDEX.PT_BR]
  .replace("_", "-")
  .toString();

export default new VueI18n({
  locale: default_lang,
  messages
});
