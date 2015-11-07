/**
 *varad 
 */

var currentHost="http://"+window.location.host;

	function setupEventSource() {
	    var output = document.getElementById("output");
	    if (typeof(EventSource) !== "undefined") {
	      var msg = "varaerer";
	      var source = new EventSource("ConsumerOrderRequestListnerServlet?msg=" + msg);
	      source.onmessage = function(event) {
	    	  alert("request from user");
	        output.innerHTML += event.data + "<br>";
	      };
	      source.addEventListener('close', function(event) {
	        output.innerHTML += event.data + "<hr/>";
	        source.close();
	        }, false);
	    } else {
	      output.innerHTML = "Sorry, Server-Sent Events are not supported in your browser";
	    }
	    return false;
	  }



//setupEventSource();