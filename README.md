<p align="center">
<a href="https://dscvit.com">
	<img width="400" src="https://user-images.githubusercontent.com/56252312/159312411-58410727-3933-4224-b43e-4e9b627838a3.png#gh-light-mode-only" alt="GDSC VIT"/>
</a>
	<h2 align="center"> Interrux </h2>
	<h4 align="center"> Interrux is an Android Library which provides a comprehensive collection of interceptors designed to enhance the functionality of Android applications. It offers a wide range of interceptors that seamlessly integrate into Android projects to intercept and modify network requests and responses. These interceptors empower developers to implement essential features like authentication, logging, caching, error handling, and more, in a modular and reusable manner. <h4>
</p>

---



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
```bash
val client = OkHttpClient.Builder()
                .addInterceptor(LoggingInterceptor())
                .build()
```

 
## Included Interceptors
1. **AuthInterceptor**: An interceptor for handling authentication by adding authentication headers to outgoing requests.
2. **LoggingInterceptor**: An interceptor for logging network requests and responses. It provides detailed logs for debugging, performance analysis, and troubleshooting purposes.
3. **CacheInterceptor**: An interceptor for implementing caching mechanisms to store and retrieve responses from the cache. It reduces network traffic and improves performance by serving cached responses when appropriate.
4. **ErrorHandlingInterceptor**: An interceptor for handling network errors and exceptions in a structured manner. It allows developers to define custom error handling logic for different types of errors.



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
