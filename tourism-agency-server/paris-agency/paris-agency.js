const express = require('express');
const app = express();
const port = 3001;
const distance_km=2005;
const delay = distance_km / 10;

app.use((req, res, next) => setTimeout(next, delay))
app.get('/rooms', (req, res) => {
  res.json({
    agency: 'Paris Agency',
    rooms: [
      { id: 1, name: 'Luxe Suite', price: 300, available: true },
      { id: 2, name: 'Standard Room', price: 150, available: true }
    ]
  });
});

app.listen(port, () => {
  console.log(`Paris Agency server running at http://localhost:${port}`);
});
