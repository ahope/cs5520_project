---
layout: default
title: Mobile App Design and Development, Fall 2021
---

This is the website I'll use to make it easy to find code snippets and various resources for class. 

Information about assignments and assignment submission is on Canvas; this is more for examples from class and other interesting things. 

## Posts

<ul class="posts">

	  {% for post in site.posts %}
	    <li><span>{{ post.date | date_to_string }}</span> » <a href="{{ post.url | relative_url }}" title="{{ post.title }}">{{ post.title | escape }}</a> {{post.url | relative_url}}</li>
	  {% endfor %}
	</ul>
  
# Mobile Design and Development

My goals for this class: 

* **Help you develop as an engineer.** You may or may not continue on as a mobile developer, but in any case, there 
are many ways you can develop as an engineer by learning mobile development. Part of it is pure technical details: becoming 
more comfortable with Github, learning how to use SDKs and frameworks in your projects. 
* **Help you communicate as a student and developer.** 

