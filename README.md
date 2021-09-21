# Thoughtful
Still peicing it together.
Version 1.0.0

The Elevator Pitch - (and workflow):
Be more thoughtful in your gift giving! 
Ever wonder what to get someone special, or in many cases close friends or even co-workers?
With Thoughtful, you can make more informed gift decisions by keeping a private note on the Favorite things each person has in their life.

Thoughtful can be used as a reference:
What is Billy's favorite color for those awesome shirts? 
Wow, look at these rock pins, What is Jane's favorite band again?
Man, I just cannot think of anything to get Bob, let's type his favorites in Google and see what comes up!

Each Person can be imported when the app is first opened (or later via a menu) then given a list of favorite things, custom to each person.
Once there are Favorites set for a Person, a search for gifts on Amazon or Google will be avaible as well.
A search page planned with a chosen Contact and using Amazon API with favorites as keywords or Web with the same for a simple gift search.
User then lands on search results and has option to purchase through normal browser operation (maybe a webkit inlay? what are the kids doing now Research)

This project is inspired by Behavior Driven Development / Test Driven Development so the design is minimal and roughly as follows:
  
  Starting as a bottom navigation 3 main fragment with a Home, Contact Chooser and Selection results page (1-2 more for a login and maybe another Search Results Fragment) with ViewModels.the idea is to be as modular as possible.
Navigation graph, this is a good tool to help visualize workflow and help guage User Experience.
It also makes it easy to be modular and add/remove Fragments.
Regarding UX and design, I think BDD can really shine here if used in a creative way. 
Start with hopes and expectations... how do we dream of interfacing with it?
That tells us what to test for behavior wise then what to build etc.

The Data model includes 2 main Objects, Contacts and Favorites as documents in Firestore and may later include a SQLite counterpart using the new Room library mainly to show the Repository pattern implementation for data and the modularity of the application, but for now Firestore should have everything covered. There should be a local snapshot if offline, we will test for it:)
No dependancy Injection is planned as of yet but that may be something showcased here as well, we'll see if during the BDD process it can be refactored in or rather if it makes sense to use it.
