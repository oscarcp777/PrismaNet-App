
var app =angular.module('prismaApp', ["highcharts-ng",'ngSanitize']);
 
 app.controller('ReportTweetCtrl', function($scope,$rootScope,$http){
	 $rootScope.dateReport=dateReport;
	    var self=this;
	 	$http.get('../hourlyConceptStats/statsForUser?dateReport='+dateReport).success(function(data) {
	        $scope.chartConfig = getCharColumn(data.charData);
	        self.concepts=data.concepts
	        self.mapTweets=data.topTweets
	        $scope.listTweets=data.listTweets
	        self.checkConcept=data.defConcept
	    });
	 	
	 	this.isSet = function(checkConcept) {
            return this.checkConcept === checkConcept;
        };
        this.setConcept = function(concept) {
            this.checkConcept = concept;
            $rootScope.concept=concept;
        };
	  });
 app.directive("conceptTab", function() {
	    return {
	      restrict: 'E',
	      templateUrl: "conceptTab",
	      controller: function() {
	    	  
	    	  
              this.tab = 1;
              this.isSet = function(checkTab) {
                  return this.tab === checkTab;
              };

              this.setTab = function(activeTab) {
                  this.tab = activeTab;
              };
          },
          controllerAs: "tab"
	    };
	});
 app.directive("listTweets", function() {
	    return {
	      restrict: 'E',
	      templateUrl: "listTweets"
	    };
	});	    
	    
function getCharColumn(data){
	var config={
    		options: {
  		      chart: {
  		        type: 'column'
  		      },
  		      plotOptions: {
  		            series: {
  		                borderWidth: 0,
  		                dataLabels: {
  		                    enabled: true,
  		                    format: '{point.y:.1f}'
  		                }
  		            }
  		        }
  		    },
          title: {
              text: data.title
          },
          subtitle: {
              text: 'Source: <a href="http://prisma-net.com.ar"/>prisma-net.com.ar</a>'
          },
          xAxis: {
              categories: data.categories,
              title: {
                  text: data.xAxis
              }
          },
          yAxis: {
              min: 0,
              title: {
                  text: data.yAxis
              }
          },
          legend: {
              layout: 'vertical',
              align: 'right',
              verticalAlign: 'top',
              x: -40,
              y: 40,
              floating: true,
              borderWidth: 1,
              backgroundColor:  '#FFFFFF',
              shadow: true
          },
          credits: {
              enabled: false
          },
          series: data.series
	  };
	return config;
}