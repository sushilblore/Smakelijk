# Food & Drinks Ordering System - Smakelijk

This is demo app for restraunt food and drinks ordering system. All the information about food and drinks are fetched from the system.
The app has 3 screens:
- HomeScreen : It allows the user to choose between food or drinks menu
- Food/Drink List Screen : Once clicked on any of the menu, it launches the options for corresponding menu (food/drinks)
- Food Details Screen - Once cliked on any of the options, it launches the detail page for that item.

The app has navigation drawer to launch food or drinks menu from any screen in the app.

# App Design:

# MVP
The app is based on MVP architecture (Model, View, Presenter) which is better than MVC when it comes to coupling. MVP makes your view code way cleaner than when using MVC, since the views, activities and the fragments will only have the code related to rendering the customizing the UI and no interactions with the controllers. MVP (Model View Presenter) is a pattern thats allows separate the presentation layer from the logic, so that everything about how the interface works is separated from how we represent it on screen.

# Dagger 2
The app also uses Dependency Injection pattern using Dagger 2. You can see more details about Dagger in http://square.github.io/dagger/

# RxJava
This app also uses RxJava for making asynchronous event calls in aclean way. RxJava is a Java VM implementation of Reactive Extensions: a library for composing asynchronous and event-based programs by using observable sequences.

# Retrofit
The app uses Retrofit as REST client. Retrofit is a REST Client for Android and Java. It makes it relatively easy to retrieve and upload JSON. Retrofit uses the OkHttp library for HTTP requests.

# Glide
As this app deals with lot of images, so the app uses Glide for image management
