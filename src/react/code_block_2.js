const express = require("express");
const mongoose = require("mongoose");
const cors = require("cors");
const bcrypt = require("bcrypt");

const User = require("./models/User");

const app = express();
app.use(cors());
app.use(express.json());

mongoose.connect("mongodb://localhost:27017/test", {
  useNewUrlParser: true,
  useUnifiedTopology: true,
});

app.post("/register", (req, res) => {
  const { username, password } = req.body;
  const salt = bcrypt.genSaltSync(10);
  const hash = bcrypt.hashSync(password, salt);

  const newUser = new User({ username, password: hash });

  newUser.save((err) => {
    if (err) return res.status(500).send(err);
    return res.status(200).send("User Created");
  });
});

const port = 4000;

app.listen(port, () => {
  console.log(`Server running on port ${port}`);
});