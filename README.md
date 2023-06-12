<p align="center">
<a href="https://dscvit.com">
	<img width="400" src="https://user-images.githubusercontent.com/56252312/159312411-58410727-3933-4224-b43e-4e9b627838a3.png#gh-light-mode-only" alt="GDSC VIT"/>
</a>
	 <h1 align="center">  Interrux </h1>
	
</p>
<p align="center">
<a href="https://dscvit.com">
	<img width="200" src="https://github.com/rudrankbasant/interrux/assets/85751479/184ab411-7e2b-4340-8b45-690c71a47e36" alt="GDSC VIT"/>
</a>
	<h6 align="center"> Interrux is an Android Library which provides a comprehensive collection of interceptors designed to enhance the functionality of Android applications. It offers a wide range of interceptors that seamlessly integrate into Android projects to intercept and modify network requests and responses. These interceptors empower developers to implement essential features like authentication, logging, caching, error handling, and more, in a modular and reusable manner. <h6>
</p>

## Key Features
- [ ]  Modularity: The library offers a set of independent interceptors, allowing developers to selectively choose and combine the interceptors that suit their specific requirements. Each interceptor can be used individually or in combination with other interceptors as per the desired functionality.
- [ ]  Ease of Integration: The interceptors are designed to integrate seamlessly with popular HTTP client libraries used in Android, such as OkHttp or Retrofit. Developers can easily add the interceptors to their existing network stacks without major modifications.
- [ ]  Customizability: The interceptors provide configurable options, allowing developers to customize their behavior based on application-specific needs. Parameters such as logging levels, cache durations, error handling strategies, and authentication mechanisms can be tailored according to the requirements of the application.
- [ ]  Error Handling: The interceptors include error handling mechanisms to capture and process network errors in a structured way. Developers can define error handling strategies to handle exceptions, timeouts, and other network-related issues efficiently.
	

<br>
	
## Download

Gradle:
```bash
dependencies {
  implementation '  '
}
```

Maven:

```bash

```
	
	
## Usage

To start using Interrux, just plug in an interceptor to your OkHttp Client Builder:
```kotlin
val client = OkHttpClient.Builder()
                .addInterceptor(/*Any of the Included Interceptors here*/)
		.addInterceptor(/*Another Interceptor*/)
                .build()
```

 
## Included Interceptors
### 1. AuthInterceptor: 
- An interceptor for handling authentication by adding authentication headers to outgoing requests. It intercepts network requests and adds an authorization header to the request using an authentication token. This interceptor also provides an optional token refresh functionality through a TokenRefreshListener interface.
	
#### Parameters

The `AuthInterceptor` class accepts the following parameters:

- `authToken` (required): A string representing the authentication token to be used in the authorization header.

- `tokenRefreshListener` (optional): An implementation of the `TokenRefreshListener` interface. This listener is responsible for refreshing the authentication token when needed.


#### Example	
```kotlin
// Create an instance of AuthInterceptor with the authentication token
val authToken = "YOUR_AUTH_TOKEN"
val myAuthInterceptor = AuthInterceptor(authToken, null)

// Add the interceptor to your OkHttp client
val client = OkHttpClient.Builder()
    .addInterceptor(myAuthInterceptor)
    .build()
```
#### Token Refresh Feature
```kotlin
// Implement the TokenRefreshListener
class MyTokenRefreshListener : AuthInterceptor.TokenRefreshListener {
    override fun onTokenRefresh(): String {
        // Logic to refresh the authentication token and return the new token
    }
}

// Create an instance of AuthInterceptor with the authentication token and token refresh listener
val authToken = "YOUR_AUTH_TOKEN"
val tokenRefreshListener = MyTokenRefreshListener()
val myAuthInterceptor = AuthInterceptor(authToken, tokenRefreshListener)

// Add the interceptor to your OkHttp client
val client = OkHttpClient.Builder()
    .addInterceptor(myAuthInterceptor)
    .build()

```
	
### 2. LoggingInterceptor

The `LoggingInterceptor` class is an interceptor for logging network requests and responses. It captures and logs information about the request URL, method, headers, and body, as well as the response code, headers, and body.

#### Example

```kotlin
// Create an instance of LoggingInterceptor 
val loggingInterceptor = LoggingInterceptor()

// Add the interceptor to your OkHttp client
val client = OkHttpClient.Builder()
    .addInterceptor(loggingInterceptor)
    .build()
```
- Please note that the LoggingInterceptor relies on the HttpLoggingInterceptor from the OkHttp library for logging functionality.
	
### 3. CacheInterceptor

The `CacheInterceptor` class is an interceptor that provides caching functionality for network requests. It allows you to control the caching behavior of responses by adding the appropriate `Cache-Control` header to the outgoing requests.

#### Parameters

The `CacheInterceptor` class accepts the following parameters:

- `days` (optional): An integer representing the number of days for which the response should be cached. It defaults to `1` day.

#### Example

```kotlin
// Create an instance of CacheInterceptor with the desired caching duration
val cachingDays = 1
val cacheInterceptor = CacheInterceptor(cachingDays)

// Add the interceptor to your OkHttp client
val client = OkHttpClient.Builder()
    .addInterceptor(cacheInterceptor)
    .build()
```
	
### 4. ErrorInterceptor

The `ErrorInterceptor` class is an interceptor that handles common HTTP error responses and provides error logging functionality. It intercepts network requests and their corresponding responses, checks the response code, and logs the appropriate error message based on the code.

#### Parameters

The `ErrorInterceptor` class accepts the following parameter:

- `errorHandler` (optional): An implementation of the `ErrorHandler` interface. This handler is responsible for handling the errors and providing a custom error response. If not provided, the errors will only be logged using Android's `Log` class.

#### Example

```kotlin
// Create an implementation of the ErrorHandler interface
class MyErrorHandler : ErrorInterceptor.ErrorHandler {
    override fun onError(errorCode: Int, errorMessage: String) {
        // Handle the error based on the error code and message
    }
}

// Create an instance of ErrorInterceptor with the custom error handler
val errorHandler = MyErrorHandler()
val errorInterceptor = ErrorInterceptor(errorHandler)

// Add the interceptor to your OkHttp client
val client = OkHttpClient.Builder()
    .addInterceptor(errorInterceptor)
    .build()
```

### 5. RequestInterceptor

The `RequestInterceptor` class is an interceptor which allows modification of HTTP requests and their corresponding responses. It is used to add a custom header to outgoing requests.

#### Parameters

The `RequestInterceptor` class accepts the following parameters:

- `headerName`: The name of the header to be added to the request. 
- `headerValue`: The value of the header to be added to the request.

### Example

```kotlin
// Create an instance of RequestInterceptor
val requestInterceptor = RequestInterceptor("CustomHeader", "CustomValue")

// Add the interceptor to your OkHttp client
val client = OkHttpClient.Builder()
    .addInterceptor(requestInterceptor)
    .build()
```
### 6. ResponseInterceptor

The `ResponseInterceptor` class is an interceptor which allows modification of HTTP responses. It is used to add a custom header to the response.

#### Parameters

The `ResponseInterceptor` class accepts the following parameters:

- `headerName`: The name of the header to be added to the response. 
- `headerValue`: The value of the header to be added to the response. 

#### Example

```kotlin
// Create an instance of ResponseInterceptor
val responseInterceptor = ResponseInterceptor("CustomHeader", "CustomValue")

// Add the interceptor to your OkHttp client
val client = OkHttpClient.Builder()
    .addInterceptor(responseInterceptor)
    .build()
```

### 7. RequestResponseInterceptor

The `RequestResponseInterceptor` class is an interface which allows modification of both HTTP requests and responses. It is used to add custom headers to both outgoing requests and incoming responses.

#### Parameters

The `RequestResponseInterceptor` class accepts the following parameters:

- `requestHeaderName`: The name of the header to be added to the request.
- `requestHeaderValue`: The value of the header to be added to the request. 
- `responseHeaderName`: The name of the header to be added to the response. 
- `responseHeaderValue`: The value of the header to be added to the response. 

#### Example

```kotlin
// Create an instance of RequestResponseInterceptor
val requestResponseInterceptor = RequestResponseInterceptor(
    "RequestHeader", "RequestValue",
    "ResponseHeader", "ResponseValue"
)

// Add the interceptor to your OkHttp client
val client = OkHttpClient.Builder()
    .addInterceptor(requestResponseInterceptor)
    .build()

```
## Developer

<table>
	<tr align="center">
		<td>
		Rudrank Basant
		<p align="center">
			<img src = "https://avatars.githubusercontent.com/u/85751479?s=400&u=5d8d2bc673f919c15b1c7940eb147dafab0bb4b9&v=4" width="150" height="150" alt="Rudrank Basant">
		</p>
			<p align="center">
				<a href = "https://github.com/rudrankbasant">
					<img src = "http://www.iconninja.com/files/241/825/211/round-collaboration-social-github-code-circle-network-icon.svg" width="36" height = "36" alt="GitHub"/>
				</a>
				<a href = "https://www.linkedin.com/in/rudrankbasant/">
					<img src = "http://www.iconninja.com/files/863/607/751/network-linkedin-social-connection-circular-circle-media-icon.svg" width="36" height="36" alt="LinkedIn"/>
				</a>
			</p>
		</td>
	</tr>
</table>

<p align="center">
	Made with ❤ by <a href="https://dscvit.com">GDSC-VIT</a>
</p>
