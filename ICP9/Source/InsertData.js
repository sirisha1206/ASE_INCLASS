/**
 * Created by mnpw3d on 20/10/2016.
 */
var MongoClient = require('mongodb').MongoClient;
var assert = require('assert');
var url = 'mongodb://sirishasunkara:siri1206@ds121955.mlab.com:21955/sirishasunkara';
var insertDocument = function(db, callback) {
    db.collection('icp9').insertOne( {
        "userid" : "djfndj",
        "fname" : "Naga",
        "lname" : "Sunkara",
        "city":"Kansas City",
        "mail":"djfndj@mail.umkc.edu",
        "phone":"9834875747"
    }, function(err, result) {
        assert.equal(err, null);
        console.log("Inserted a document into the icp9 collection.");
        callback();
    });
};
MongoClient.connect(url, function(err, db) {
    assert.equal(null, err);
    insertDocument(db, function() {
        db.close();
    });
});