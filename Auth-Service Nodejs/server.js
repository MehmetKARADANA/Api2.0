const express = require('express');
const port = 8084;

const connectDB = require('./repository/repository');
const { errorHandler } = require('./middlewares/errorMiddleware');//!

const app = express();

connectDB().then(() => {
  console.log('Database connected successfully');
}).catch((err) => {
  console.error('Database connection failed', err);
});

app.use(express.json());
app.use(express.urlencoded({extended: false}));




app.use('/api/auth', require('./routers/authenticateRoutes'));
app.use('/api/users', require('./routers/userRoutes'));
app.use(errorHandler);

app.listen(port, () => {
    console.log(`Server started on port ${port}`);
  });
