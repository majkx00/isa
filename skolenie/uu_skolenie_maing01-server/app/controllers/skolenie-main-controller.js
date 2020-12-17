"use strict";
const SkolenieMainModel = require("../models/skolenie-main-model.js");

class SkolenieMainController {

  init(ucEnv) {
    return SkolenieMainModel.init(ucEnv.getUri().getAwid(), ucEnv.getDtoIn());
  }

}

module.exports = new SkolenieMainController();
