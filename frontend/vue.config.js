var webpack = require('webpack');

// vue.config.js
module.exports = {
  // proxy todos os requests do webpack começando com /api
  // para o Spring Boot backend (localhost:6669) usando http-proxy-middleware
  // ver https://cli.vuejs.org/config/#devserver-proxy
  devServer: {
    proxy: {
      "^/api": {
        target: "http://localhost:9666",
        ws: true,
        changeOrigin: true
      }
    }
  },
  configureWebpack: {
    plugins: [
      new webpack.ProvidePlugin({
        '$': 'jquery',
        jQuery: 'jquery',
      })
    ]
  },

  // Mudando rota do build para ser compatível com o Maven
  // ver https://cli.vuejs.org/config/
  outputDir: "target/dist",
  assetsDir: "static",
  baseUrl: process.env.NODE_ENV === 'production' ? '/carteira-pesca/' : '/'
};
