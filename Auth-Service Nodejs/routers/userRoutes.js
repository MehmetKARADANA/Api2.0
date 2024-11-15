const express = require("express");
const router = express.Router();//farklı router modülleri için
const {
  registerUser,
  loginUser,
} = require("../controllers/userController");

router.post("/register", registerUser);
router.post("/login", loginUser);


module.exports = router;
