const express = require('express');
const bodyParser = require('body-parser');
const path = require('path');

const app = express();

app.use(bodyParser.urlencoded({ extended: true }));

// Serve static files from the root directory
app.use(express.static(path.join(__dirname, '')));

// Serve page1.html
app.get('/', (req, res) => {
    res.sendFile(path.join(__dirname, 'page1.html'));
});

// Serve page2.html
app.get('/page2', (req, res) => {
    res.sendFile(path.join(__dirname, 'page2.html'));
});

// Start the server on port 3000
app.listen(3000, () => {
    console.log('Server is running on port 3000');
});
