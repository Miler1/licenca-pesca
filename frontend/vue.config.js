// vue.config.js
module.exports = {
  // proxy todos os requests do webpack começando com /api
  // para o Spring Boot backend (localhost:6669) usando http-proxy-middleware
  // ver https://cli.vuejs.org/config/#devserver-proxy
  devServer: {
    proxy: {
      '/api': {
        target: 'http://localhost:6669',
        ws: true,
        changeOrigin: true
      }
    }
  },
  // Mudando rota do build para ser compatível com o Maven
  // ver https://cli.vuejs.org/config/
  outputDir: 'target/dist',
  assetsDir: 'static'
}