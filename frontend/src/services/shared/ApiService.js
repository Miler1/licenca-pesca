import Vue from "vue";
import axios from "axios";
import VueAxios from "vue-axios";
import qs from "qs";
import { HttpException } from "./handling/HttpException";

/**
 * Serviço base para chamadas HTTP.
 *
 * @author Highlander Paiva
 * @since 1.0
 */
const ApiService = {
  /**
   * Configuração inicial da API.
   */
  init() {
    Vue.use(VueAxios, axios);
    this.setHeader();
  },

  /**
   * Adiciona o cabeçalho a todas as requisições HTTP.
   */
  setHeader() {
    Vue.axios.defaults.headers.post["Content-Type"] =
      "application/x-www-form-urlencoded";
  },

  /**
   * Aciona o HTTP request GET, no entanto para chamadas de query
   *
   * @param resource
   * @param params
   * @return {Promise<*>}
   */
  async query(resource, params) {
    try {
      return await Vue.axios.get(resource, {
        params
      });
    } catch (error) {
      return Promise.reject(new HttpException.init(error, true));
    }
  },

  /**
   * Aciona o HTTP request GET.
   *
   * @param resource
   * @param slug
   * @return {Promise<*>}
   */
  async get(resource, slug = "") {
    try {
      return await Vue.axios.get(`${resource}/${slug}`);
    } catch (error) {
      return Promise.reject(new HttpException.init(error, true));
    }
  },

  /**
   * Aciona o HTTP request POST.
   *
   * @param resource
   * @param params
   * @return {Promise<*>}
   */
  async post(resource, params) {
    try {
      return await Vue.axios.post(`${resource}`, params);
    } catch (error) {
      return Promise.reject(new HttpException.init(error, true));
    }
  },

  /**
   * Aciona o HTTP request UPDATE.
   *
   * @param resource
   * @param slug
   * @param params
   * @return {Promise<*>}
   */
  async update(resource, slug, params) {
    try {
      return await Vue.axios.put(`${resource}/${slug}`, params);
    } catch (error) {
      return Promise.reject(new HttpException.init(error, true));
    }
  },

  /**
   * Aciona o HTTP request PUT.
   *
   * @param resource Parâmetro do recurso
   * @param params Opções extras
   * @return {Promise<*>}
   */
  async put(resource, params) {
    try {
      return await Vue.axios.put(`${resource}`, params);
    } catch (error) {
      return Promise.reject(new HttpException.init(error, true));
    }
  },

  /**
   * Aciona o HTTP request DELETE.
   *
   * @param resource Recurso a ser deletado
   * @return {Promise<never>}
   */
  async delete(resource) {
    try {
      return await Vue.axios.delete(resource);
    } catch (error) {
      return Promise.reject(new HttpException.init(error, true));
    }
  }
};

export default ApiService;
