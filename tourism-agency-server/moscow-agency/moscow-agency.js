const express = require('express');
const app = express();
const port = 3005;
const distance_km=3000;
const delay = distance_km / 10;

app.use((req, res, next) => setTimeout(next, delay))
app.get('/rooms', (req, res) => {
  res.json({
    agency: 'Moscow Agency',
    rooms: [
      { id: 1, name: 'Kremlin Suite', price: 450, available: true },
      { id: 2, name: 'Red Square Room', price: 200, available: true },
      { id: 3, name: 'Economy Room', price: 70, available: false }
    ]
  });
});

app.listen(port, () => {
  console.log(`Moscow Agency server running at http://localhost:${port}`);
});