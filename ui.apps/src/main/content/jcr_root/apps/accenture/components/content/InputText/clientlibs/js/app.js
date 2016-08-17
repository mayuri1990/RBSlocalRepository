angular.
module(' ').
component(' ', {
templateUrl: '///.template.html',
controller: function SampleController($http) {
var self = this;
 $http.get('temps/temps.json').then(function(response) {
self.temp = response.data;
 });
 }
 });