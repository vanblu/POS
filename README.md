# POS

## Project Overview 

WELCOME TO YALP! 
POS is our final project for UPENN CIT 594 class that allows user to receive a list of available restaurants.
User will select to filter and sort the list of restaurants by ratings, categories, and distance.
We are implementing a real life data by parsing Yelp's open source JSON, and also quad tree to properly search and filter out the list of restaurants based on the user's input.


## Technologies
Project is created with:
* Java

## Classes
* User: user puts input such as type of city, rating range, category, and max distance from current location. User can also decide if they want the list by ascending or descending in ratings, distance, and alphabetical.

* Main: runs user class and starts POS

* JSonParser : parses the large data JSON from Yelp to the data we need for this project

* IPos: interface for POS, it loads restaurant info, stores and sorts restaurants based on how the user wants to get the list of restaurants
* POS: contains restaurantSet, and creates head of the quad tree, and gets userCoordinates for location

* IQuadTree: interface for putting sorted restaurants to a quad tree. contains methods such as insert, rangeSearch, isLeaf, and depth
* QuadTree: implements IQuadTree, gets point from topLeft, botLeft, and puts restaurants in a treeSet

* IRestaurant: interface for Restaurant, utilizes interfaces of Comparator, DistanceComparator, NameComparator, and StarComparator
* Restaurant: gets name, address, latitude, longitude, category, and star ratings of the restaurants

* Address: sets address in terms of street, city, state, and zipcode - we use city in our project



