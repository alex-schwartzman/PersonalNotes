# HOMEWORK FOR ANDROID DEVELOPERS

Implement simple native Android application for management of personal notes. The application should be ready to build and run right after clone of your repository.

The application should integrate API, which is ready to use at URL: https://private-9aad-note10.apiary-mock.com/ (Feel free to use any alternative you want, but integration with network services is mandatory.)
Methods:

* `GET /notes`
* `GET /notes/{id}`
* `POST /notes`
* `PUT /notes/{id}`
* `DELETE /notes/{id}`

# BUILD INSTRUCTIONS

* Install jdk8. Don't try it with Amazon JDK 11 Corretto - compilation will fail on Kotlin Annotation processor. OpenJDK 1.8 is the way to go
* clone the repository
* `gradlew installDebug` - if you get error `com.android.builder.testing.api.DeviceException: No connected devices!` - maybe you need to install Android SDK and get `adb` to work
* after the application was installed - three options:
1. manually tap on the newly installed app in the launcher
2. use Activity Manager from commandline `adb shell am start -n com.example.personalnotes/.MainActivity`
3. run instrumentation test from commandline `gradlew connectedAndroidTest`

* don't forget to enjoy reading the logcat - there will be all debug output from Retrofit, so you'll know what was sent to the API endpoint, and what was received back

# Implementation considerations and shortcuts

* In this implementation I opted for AndroidX and Retrofit. Therefore, no Dagger, no Messagebus, no god objects.  Simply code. Annotation processors and binding code generators did all the heavylifting for me.
* No local storage for the application state. It is as transparent as possible, and does not face the consistency problems. The only challenge here - if backend returns crap, application will indicate crap. That's very useful for debugging: garbage in - garbate out. That's partially the reason why the application behaves weird - does not delete, does not update, does not create. _It's all backend's fault._ and _Works on my machine._ :)
* Layout for notes is intentionally left untidy and straightforward. Just because the note itself looks like it lacks some data.
* Provided that there is no hand-made code in this application and almost everything is auto-generated, there is very subtle surface of attack for unit testing. Therefore, I added only instrumentation tests here.

