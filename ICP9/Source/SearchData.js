/*
 * Created by mnpw3d on 20/10/2016.
 */

var MongoClient = require('mongodb').MongoClient;
var assert = require('assert');
var url = 'mongodb://sirishasunkara:siri1206@ds121955.mlab.com:21955/sirishasunkara';
var findUser = function(db, callback) {
    var cursor =db.collection('icp9').find( );
    cursor.each(function(err, doc) {
        assert.equal(err, null);
        if (doc != null) {
            console.log(doc);
        } else {
            callback();
        }
    });
};
var findUserwithName = function(db,callback) {
    var cursor = db.collection('icp9').find({"fname":"Sirisha"});
    cursor.each(function(err,doc) {
        assert.equal(err,null);
        if(doc != null)
        {
            console.log("First Name:" + doc.fname);
            console.log("Last Name:" + doc.lname);
            console.log("city:" + doc.address.city);
        }
    });
}
var findUserwithPhone = function(db, callback) {
    var cursor = db.collection('icp9').find({"phone":"9132576976"});
    cursor.each(function(err,doc){
       assert.equal(err,null);
       if(doc != null)
       {
           console.log("First Name:" + doc.fname);
           console.log("Last Name:" + doc.lname);
           console.log("User ID:" + doc.userid);
           console.log("City:" + doc.city);
           console.log("Email:" + doc.mail);
       }
    });
}
MongoClient.connect(url, function(err, db) {
    assert.equal(null, err);
    findUserwithPhone(db, function() {
        db.close();
    });
});