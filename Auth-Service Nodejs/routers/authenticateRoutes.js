const express = require("express");
const router = express.Router();
const { protect } = require("../middlewares/authMiddleware");

const {
    isAuthenticate
  } = require("../controllers/authController");

  
router.get("/authenticate", protect,isAuthenticate);

module.exports = router;
  