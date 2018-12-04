import Vue from "vue";

const locale = key => Vue.prototype.$translator.t(`validation.${key}`);

export const required = (messageKey, trigger = "blur") =>
  Object.assign(
    {},
    {
      required: true,
      message: locale(`${messageKey}.required`),
      trigger: trigger
    }
  );

export const validate = (expression, messageKey, trigger = "blur") =>
  Object.assign(
    {},
    {
      validator: expression,
      message: locale(`${messageKey}`),
      trigger: trigger
    }
  );
