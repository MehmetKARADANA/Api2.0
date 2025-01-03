const express = require('express');
const app = express();
const port = 3003;
const distance_km=9000;
const delay = distance_km / 10;

app.use((req, res, next) => setTimeout(next, delay))
app.get('/rooms', (req, res) => {
  res.json({
    agency: 'Tokyo Agency',
    rooms: [
      { id: 1, name: 'Shinjuku Suite', price: 400, available: true },
      { id: 2, name: 'Tokyo Tower View', price: 250, available: true },
      { id: 3, name: 'Budget Room', price: 90, available: false }
    ]
  });
});

app.listen(port, () => {
  console.log(`Tokyo Agency server running at http://localhost:${port}`);
});
