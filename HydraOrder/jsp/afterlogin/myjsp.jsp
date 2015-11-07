<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
  <body>
    <script>
      function setupEventSource() {
        var output = document.getElementById("output");
        if (typeof(EventSource) !== "undefined") {
          var msg = document.getElementById("textID").value;
          var source = new EventSource("sseasync?msg=" + msg);
          source.onmessage = function(event) {
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
    </script>

    <h2>SSE Echo Demo</h2>
    <div>
      <input type="text" id="textID" name="message" value="Hello World">
      <input type="button" id="sendID" value="Send" onclick="setupEventSource()"/>
    </div>
    <hr/> 
    <div id="output"></div>
  </body> 
</html>