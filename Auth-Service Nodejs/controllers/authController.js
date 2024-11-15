const asyncHanlder = require('express-async-handler');

const isAuthenticate = asyncHanlder(async(req, res) => {
    res.json("access granted")
})

module.exports = {
    isAuthenticate
}