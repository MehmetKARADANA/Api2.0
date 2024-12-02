const asyncHanlder = require('express-async-handler');

const isAuthenticate = asyncHandler(async(req, res) => {
    res.json("access granted")
})

module.exports = {
    isAuthenticate
}