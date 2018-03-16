var MongoClient = require('mongodb').MongoClient;
var assert = require('assert');
var url = 'mongodb://sirishasunkara:siri1206@ds121955.mlab.com:21955/sirishasunkara';
MongoClient.connect(url, function(err, db) {
  assert.equal(null, err);
  console.log("Connected correctly to server.");
  db.close();
});
