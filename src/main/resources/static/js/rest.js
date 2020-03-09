/**
 * 
 */
$(document).ready(function() {

	/**
	 * Homepage clear user search form fields
	 */
	document.getElementById("clearUserSearchCriteriaForm").onclick = function(e) {
		e.preventDefault();

		document.getElementById("priceMin").value = null;
		document.getElementById("priceMax").value = null;
		document.getElementById("pricePerSquareMin").value = null;
		document.getElementById("pricePerSquareMax").value = null;
		document.getElementById("sizeMin").value = null;
		document.getElementById("sizeMax").value = null;
		document.getElementById("type").value = "";
		document.getElementById("buildType").value = "";
	};
	
	/**
	 * Homepage validate form and submit search criteria
	 */
	document.getElementById("userSearchCriteriaForm").addEventListener("submit", function(e){
		e.preventDefault();
				
		var form = document.createElement("form");
		
		form.action = "/";
		form.method = "GET";
		
		var search = document.createElement("input");
		
		search.setAttribute("type", "hidden");
		search.setAttribute("name", "search");
		search.setAttribute("value", true);
			
		form.append(search);
		
		if(document.getElementById("priceMin").value > 0){
			
			var priceMin = document.createElement("input");
			
			priceMin.setAttribute("type", "text");
			priceMin.setAttribute("name", "priceMin");
			priceMin.setAttribute("value", document.getElementById("priceMin").value);
			
			form.append(priceMin);
		}
		
		if(document.getElementById("priceMax").value > 0){
			
			var priceMax = document.createElement("input");
			
			priceMax.setAttribute("type", "text");
			priceMax.setAttribute("name", "priceMax");
			priceMax.setAttribute("value", document.getElementById("priceMax").value);
			
			form.append(priceMax);
		}
		
		if(document.getElementById("pricePerSquareMin").value > 0){
			
			var pricePerSquareMin = document.createElement("input");
			
			pricePerSquareMin.setAttribute("type", "text");
			pricePerSquareMin.setAttribute("name", "pricePerSquareMin");
			pricePerSquareMin.setAttribute("value", document.getElementById("pricePerSquareMin").value);
			
			form.append(pricePerSquareMin);
		}
		
		if(document.getElementById("pricePerSquareMax").value > 0){
			
			var pricePerSquareMax = document.createElement("input");
			
			pricePerSquareMax.setAttribute("type", "text");
			pricePerSquareMax.setAttribute("name", "pricePerSquareMax");
			pricePerSquareMax.setAttribute("value", document.getElementById("pricePerSquareMax").value);
			
			form.append(pricePerSquareMax);
		}
		
		if(document.getElementById("sizeMin").value > 0){
			
			var sizeMin = document.createElement("input");
			
			sizeMin.setAttribute("type", "text");
			sizeMin.setAttribute("name", "sizeMin");
			sizeMin.setAttribute("value", document.getElementById("sizeMin").value);
			
			form.append(sizeMin);
		}
		
		if(document.getElementById("sizeMax").value > 0){
			
			var sizeMax = document.createElement("input");
			
			sizeMax.setAttribute("type", "text");
			sizeMax.setAttribute("name", "sizeMax");
			sizeMax.setAttribute("value", document.getElementById("sizeMax").value);
			
			form.append(sizeMax);
		}
		
		if(document.getElementById("type").value > 0){
			
			var type = document.createElement("input");
			
			type.setAttribute("type", "text");
			type.setAttribute("name", "type");
			type.setAttribute("value", document.getElementById("type").value);
			
			form.append(type);
		}
		
		if(document.getElementById("buildType").value > 0){
			
			var buildType = document.createElement("input");
			
			buildType.setAttribute("type", "text");
			buildType.setAttribute("name", "buildType");
			buildType.setAttribute("value", document.getElementById("buildType").value);
			
			form.append(buildType);
		}
		
		document.body.append(form);
		form.submit();
		
	});	

})

	/**
	 * Send ajax request and mark crawled url for viewd by user
	 */
	function markCrawledUrlAsViewedByUser(crawled, user){

		var xhttp = new XMLHttpRequest();
		
		xhttp.onreadystatechange = function() {
		    if (this.readyState == 4 && this.status == 200) {
		      document.getElementById("crawledTableRow_" + crawled).classList.remove("table-secondary")
		    }
		};
		
		xhttp.open("GET", "/rest/crawled/markCrawledUrlAsViewedByUser?crawled=" + crawled + "&user=" + user, true);
		xhttp.send();
	};
