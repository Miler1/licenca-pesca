import { translate } from "../helpers/internationalization";

const locale = key => translate(`validation.${key}`);

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
