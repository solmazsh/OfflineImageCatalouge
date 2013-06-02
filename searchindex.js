window.onload = loadIndex;

function loadIndex()
{
	if (window.XMLHttpRequest)
  	{// code for IE7+, Firefox, Chrome, Opera, Safari
  		xmlhttp=new XMLHttpRequest();
  	}
	else
  	{// code for IE6, IE5
  		xmlhttp=new ActiveXObject("Microsoft.XMLHTTP");
  	}
	xmlhttp.open("GET","sample.xml",false);
	xmlhttp.send();
	xmlDoc=xmlhttp.responseXML;
}


function searchIndex()
{
	if (!xmlDoc)
	{
		loadIndex();
	}
    
	// get the search term from a form field with id 'searchme'
	var searchterm = document.getElementById("searchme").value;
	if (searchterm.length < 3)
	{
		alert("Enter at least three characters");
	}
	
	var allitems = xmlDoc.getElementsByTagName("filename");
	results = new Array;
    
    // see if the XML entry matches the search term, and (if so) store it in an array
	for (var i=0; i<allitems.length; i++){
		var attribute = allitems[i].getElementsByTagName("attribute");
        var description = allitems[i].getElementsByTagName("description");
        var category = allitems[i].getElementsByTagName("category");
        var use = allitems[i].getElementsByTagName("use");
        
		for (var j=0;j<=attribute.length;j++)
		{
			var attName = attribute[j].childNodes[0].nodeValue;
			var exp = new RegExp(searchterm,"i");
			if ( attName.match(exp) != null)
			{
				results.push(allitems[i]);
			}
			break;
		}
        /*
        for (var j=0;j<=description.length;j++){
        var descName = description[j].childNodes[0].nodeValue;
        var exp = new RegExp(searchterm,"i");
			if ( descName.match(exp) != null)
			{
				results.push(allitems[i]);
			}
			break;
        }
        
        for (var j=0;j<=category.length;j++){
        var catName = category[j].childNodes[0].nodeValue;
        var exp = new RegExp(searchterm,"i");
			if ( catName.match(exp) != null)
			{
				results.push(allitems[i]);
			}
			break;
        }
        
        for (var j=0;j<=use.length;j++){
        var useName = use[j].childNodes[0].nodeValue;
        var exp = new RegExp(searchterm,"i");
			if ( useName.match(exp) != null)
			{
				results.push(allitems[i]);
			}
			break;
        }*/
	}
	// send the results to another function that displays them to the user
		showResults(results, searchterm);
}

// The following is just an example of how you could handle the search results
function showResults(results, searchterm)
{
	if (results.length > 0) {
// if there are any results, put them in a list inside the "resultshere" div
		var resultshere = document.getElementById("resultshere");
		var header = document.createElement("h3");
		var list = document.createElement("ul");
		var searchedfor = document.createTextNode("You've searched for "+searchterm);
		resultshere.appendChild(header);
		header.appendChild(searchedfor);
		resultshere.appendChild(list);
		for (var i=0;i<results.length;i++) {
			var listitem = document.createElement("li");
			var name = document.createTextNode(results[i].getAttribute("name"));
            var attribute = document.createTextNode(results[i].getAttribute("attribute"));
            var description = document.createTextNode(results[i].getAttribute("description"));
            var category = document.createTextNode(results[i].getAttribute("category"));
            var use = document.createTextNode(results[i].getAttribute("use"));
			list.appendChild(listitem);
            listitem.appendChild(name);
			listitem.appendChild(attribute);
            listitem.appendChild(description);
            listitem.appendChild(category);
            listitem.appendChild(use);
		}
	} else {
// else tell the user no matches were found
		var resultshere = document.getElementById("resultshere");
		var para = document.createElement("p");
		var notfound = document.createTextNode("Sorry, I couldn't find anything like "+searchterm +"!");
		resultshere.appendChild(para);
		para.appendChild(notfound);
	}
}
