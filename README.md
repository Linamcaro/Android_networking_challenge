Android NetworkConnect Challenge
===================================

This sample demonstrates how to connect to the network and fetch raw HTML using
HttpsURLConnection. AsyncTask is used to perform the fetch on a background thread.

Introduction
------------

This sample demonstrates how to connect to the network and fetch raw HTML using
[`HttpsURLConnection`][4]. Since API 11, it is required by default that all network
operations run on a background thread in order to avoid hanging on the UI thread. Only
when the network response is ready should the work return to the main thread to update
the UI. An [`AsyncTask`][3] is a viable background task manager that is used to perform
the network operation and return to the UI thread upon completion.

The sample also utilizes the [`ConnectivityManager`][1] to determine if you have
a network connection, and if so, what type of connection it is.

Using an [`AsyncTaskLoader`][6] or an [`IntentService`][5] are two common alternatives
for managing longer running background work.

[1]: https://developer.android.com/reference/android/net/ConnectivityManager.html
[2]: https://developer.android.com/reference/android/net/NetworkInfo.html
[3]: https://developer.android.com/reference/android/os/AsyncTask.html
[4]: https://developer.android.com/reference/javax/net/ssl/HttpsURLConnection.html
[5]: https://developer.android.com/reference/android/app/IntentService.html
[6]: https://developer.android.com/reference/android/content/AsyncTaskLoader.html

Pre-requisites
--------------

- Android SDK Min 26 - Target latest
- Android Build Tools latest
- Android Support Repository

Screenshots
-------------

<img src="screenshots/main.png" height="400" alt="Screenshot"/> 

Getting Started
---------------

The real challenge for you is to fetch the Rick and Morty API
````
{
  "characters": "https://rickandmortyapi.com/api/character",
  "locations": "https://rickandmortyapi.com/api/location",
  "episodes": "https://rickandmortyapi.com/api/episode"
}
````
To fetch the first page of the characters and modify the app as needed to show the name of the character, and the image of the character.
All this in the background thread in order to avoid hanging on the UI thread. Only
when the network response is ready should the work return to the main thread to update
the UI.

Support
-------

- Stack Overflow: http://stackoverflow.com/questions/tagged/android
- Talk to your DevSavant contact.
