# FreeNowTask

<b>Idea of this Project :-</b>

Write an app containing a list. The list should show all the vehicle data in the bounds of Hamburg (53.694865,
9.757589 & 53.394655, 10.099891).
Use this endpoint to get the vehicles: https://fake-poi-api.mytaxi.com/?p1Lat={Latitude1}&p1Lon={Longitude1}&p2Lat={Latitude2}&p2Lon={Longit
ude2}

Implement a map Activity/Fragment with Google Maps. Show all available vehicles on the map. Use the bounds of the map to request the vehicles.
The map should zoom and center on a specific vehicle when it is selected in the previously implemented list.


<b>Design :-</b> 

Implemented by following MVVM Clean architecture by using Coroutines, HILT dependency injection & Retrofit.

<p align="center">
<img src="https://user-images.githubusercontent.com/16395251/156012770-498ef019-ea34-4576-b2e1-0eaa1df829a3.png" width="500" height="1000">
</p>

<p align="center">
<img src="https://user-images.githubusercontent.com/16395251/156012792-c05a3794-3bd7-49f8-9e24-89411a328c7f.png" width="500" height="1000">
</p>

<h1>Release Notes:-</h1> 

1) Developed Application by using Android JetPack Components like HILT, Navigation & UI, ViewModel ,
   Livedata, DataBinding and Lifecycle-Aware Components.

2) To Update data to RecyclerView adapter used DiffUtil logic. It’s an is a utility class that can
   calculate the difference between two lists and output a list of update operations that converts
   the first list into the second one.

3) Used "com.intuit.ssp:ssp-android:1.0.6" & "com.intuit.sdp:sdp-android:1.0.6" libraries

SSP : An android SDK that provides a new size unit - ssp (scalable sp). This size unit scales with
the screen size based on the sp size unit (for texts). It can help Android developers with
supporting multiple screens.

SDP : An android SDK that provides a new size unit - sdp (scalable dp). This size unit scales with
the screen size. It can help Android developers with supporting multiple screens.

4) Used "Data Binding" for faster access and performance improvement. The Data Binding Library is a
   support library that allows you to bind UI components in your layouts to data sources in your app
   using a declarative format rather than programmatically.

5) Used Android Coroutines. It mainly simplify code that executes asynchronously & help to manage
   long-running tasks in background without block the main thread.

6) Developed application by using HILT dependency injection. It's an opinionated dependency
   injection library for Android that reduces the boilerplate of using manual DI in your project.

7) Used Retrofit to make API calls.

8) Added Unit-tests for Business logic. Such as ViewModel and Repository.

9) Added Unit-tests for Retrofit Service API's by mocking OkHttp MockWebServer.

10) Added comments for fun's in Kdoc format.
