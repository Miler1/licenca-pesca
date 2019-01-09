export default class LoadingService {

  // static countServices = 0;

  static show(msg) {

    msg = msg || "Carregando";
    $.blockUI({
      message: "<div class=\"spinner\">" +
        "<div class=\"rect1\"></div>" +
        "<div class=\"rect2\"></div>" +
        "<div class=\"rect3\"></div>" +
        "<div class=\"rect4\"></div>" +
        "<div class=\"rect5\"></div>" +
        "</div>" +
        "<span class=\"spinner-text\">" + msg + "</span>",
      baseZ: 10000,
      overlayCSS: {
        backgroundColor: "#FFFFFF",
        opacity: 0.8,
        cursor: "wait"
      },
      css: {
        backgroundColor: "transparent",
        border: "none"
      }

    });
  }

  static hide() {
    $.unblockUI();
  }
}