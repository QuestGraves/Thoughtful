# Thoughtful
The Elevator Pitch - (and workflow):
Be more thoughtful in your gift giving! 
Ever wonder what to get someone special, or in many cases close friends or even co-workers?
With Thoughtful, you can make more informed gift decisions by keeping a private note on the Favorite things each person has in their life.

Thoughtful can be used as a reference:
What is Billy's favorite color for those awesome shirts? 
Wow, look at these rock pins, What is Jane's favorite band again?
Man, I just cannot think of anything to get Bob, let's type his favorites in Google and see what comes up!

Each Person can be imported when the app is first opened (or later via menu) then given a list of favorite things, custom to each person.
Once there are Favorites set for a Person, a search for gifts on Amazon or Google will be avaible as well.
A search page planned with a chosen Contact and using Amazon API with favorites as keywords or Web with the same for a simple gift search.
User then lands on search results and has option to purchase through normal browser operation (maybe a webkit inlay? what are the kids doing now Research)

This project is inspired by Behavior Driven Development / Test Driven Development application so the design is minimal and roughly as follows.
Starting as a bottom navigation 3 main fragment (1-2 more for a login and maybe another Search Results Fragment) with ViewModels.
The Data model uses Contacts and Favorites as documents in Firestore as well as an SQLite counterpart mainly to show a Repository implementation of data. 
It will have a fragment that shows a login/User account using Firebase Firestore (document DB just makes perfect sense here)
As we write test, and especially after the data model is done the architecture will show itself a bit better
