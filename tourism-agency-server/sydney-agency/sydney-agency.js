const express = require('express');
const app = express();
const port = 3004;
const distance_km=15000;
const delay = distance_km / 10;

app.use((req, res, next) => setTimeout(next, delay))
app.get('/rooms', (req, res) => {
  res.json({
    agency: 'Sydney Agency',
    rooms: [
      { id: 1, name: 'Harbor View Suite', price: 350, available: true },
      { id: 2, name: 'City View Room', price: 180, available: true },
      { id: 3, name: 'Backpacker Room', price: 60, available: true }
    ]
  });
});

app.listen(port, () => {
  console.log(`Sydney Agency server running at http://localhost:${port}`);
});
