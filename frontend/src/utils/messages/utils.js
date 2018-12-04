import { find } from "../helpers/util";

export const handleMessage = (obj, languageIndex, cache = []) => {
  if (obj === null || typeof obj !== "object") {
    return obj;
  }

  if (Array.isArray(obj)) {
    return obj[languageIndex];
  }

  const hit = find(cache, c => c.original === obj);
  if (hit) {
    return hit.copy;
  }

  const copy = Array.isArray(obj) ? [] : {};

  cache.push({
    original: obj,
    copy
  });

  Object.keys(obj).forEach(key => {
    copy[key] = handleMessage(obj[key], languageIndex, cache);
  });

  return copy;
};

export const merge = (obj, ...moreObj) =>
  Object.assign(
    {},
    obj,
    moreObj.reduce((obj, red) => Object.assign({}, obj, red))
  );
