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
	for (var i=0; i<allitems.length; i++)
	{
		// see if the XML entry matches the search term,
		// and (if so) store it in an array
		var lName = allitems[i].getAttribute("name");
		var exp = new RegExp(searchterm,"i");
		if (lName.match(exp) != null)
		{
			results.push(allitems[i]);
			continue;
		}
		
		var lChildren = allitems[i].getElementsByTagName("attribute");
		for (var j=0;j<lChildren.length;j++)
		{
			var attName = lChildren[j].childNodes[0].nodeValue;
			var exp = new RegExp(searchterm,"i");
			if (attName.match(exp) != null)
			{
				results.push(allitems[i]);
				break;
			}
		}
	}
	// send the results to another function that displays them to the user
	showResults(results, searchterm);
}

// The following is just an example of how you could handle the search results
function showResults(results, searchterm)
{
	var resultshere = document.getElementById("resultshere");
	resultshere.innerHTML = '';
	if (results.length > 0)
	{	
		for(var i=0; i<results.length; i++)
		{
			var box = document.createElement("div");
			box.className = "resultBox";
			resultshere.appendChild(box);
			var node = results[i].getElementsByTagName("attribute");
			var nodeName = document.createTextNode("Catalog ID: "+results[i].getAttribute("name"));
			box.appendChild(nodeName);
			var img = document.createElement("img");
			img.className = "imgBox";
			img.src = "img/" + results[i].getAttribute("filename");
			box.appendChild(img);
			var list = document.createElement("ul");
			for (var j=0; j<node.length; j++)
			{
				var listItem = document.createElement("li");
				var li = document.createTextNode(node[j].childNodes[0].nodeValue);
				list.appendChild(listItem);
				listItem.appendChild(li);
			}
			box.appendChild(list);
		}
	}
	else
	{
	// else tell the user no matches were found
		var para = document.createElement("p");
		var notfound = document.createTextNode("Sorry, I couldn't find anything like "+searchterm +"!");
		resultshere.appendChild(para);
		para.appendChild(notfound);
	}
}