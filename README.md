# Using MockWebServer for Android End to End tests

This project is an app that contains a button. When the button is pressed, a network call is made to an api to
fetch a random joke. The joke is then displayed on the screen.

The goal of this project is to demonstrate the use of mockwebserver in android end to end tests.

The project contains two important modules:
```:app``` and ```:feature:joke```

The end to end test class ```RequestAJokeFlowTest``` for fetching a joke from the api and displaying it on the screen is found in the ```:app```
but we also duplicated this test class in ```:feature:joke```. The aim of adding this duplicate is to demonstrate how to write end to end test in an ```:app``` module,
and also in a non ```:app```module that is part multi module application.
