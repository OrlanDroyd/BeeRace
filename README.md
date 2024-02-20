# Bee-Race

App that simulates a bee race using an online API as a data source.

## Project structure:
Clean Architecture + MVVM in a simplified way.

<a href="https://postimg.cc/6TLmk8kx" target="_blank"><img src="https://i.postimg.cc/6TLmk8kx/bee06.jpg" alt="bee06"/></a>
<a href="https://postimg.cc/jCr13Hkh" target="_blank"><img src="https://i.postimg.cc/jCr13Hkh/bee05.jpg" alt="bee05"/></a>
<a href="https://postimg.cc/xNYLGT5K" target="_blank"><img src="https://i.postimg.cc/xNYLGT5K/bee04.jpg" alt="bee04"/></a>
<a href="https://postimg.cc/BPgHm97R" target="_blank"><img src="https://i.postimg.cc/BPgHm97R/bee01.jpg" alt="bee01"/></a> <a href="https://postimg.cc/SXB6sGz1" target="_blank"><img src="https://i.postimg.cc/SXB6sGz1/bee02.jpg" alt="bee02"/></a> <a href="https://postimg.cc/LYjzZZt9" target="_blank"><img src="https://i.postimg.cc/LYjzZZt9/bee03.jpg" alt="bee03"/></a> 



### The ideal:
The app is created in 4 hours of work and therefore not all the ideal clean code can be implemented.
- It would be perfect to create a multi-modular app made up of 5 main layers: 1-Domain, 2-Presentation, 3-UI, 4-Data, 5-DataSource and finally a "dirty main" `app` for Dependency Injection and Navigation
- Also create `Bases` files for `ViewModel`, generics `UseCase`, `UseCaseExecutors` or `CoroutinesProviders` to depend of abstractions and be able to test the code more easily.
- Use the `contentDescriptions` in a correct way, facilitating accessibility.
- Eliminate hard coded texts and use `string.xml` resources to facilitate i18n.
- Add animations to increase the user experience (UX).
- If independent models were used for the UI layer and the Data layer, it would be correct to use, for example, `@Stable` and `keys` to increase the performance of `LazyColumn`.
- Efficiently control the reCaptcha callback.
- Include `tests`
- Add a Internet Access observer.

### What was created:
- A functional app divided into 3 main layers (Domain, Data, Presentation) but in a single module separated by features.
- In `core` : Navigation and Dependency Injection.
- Using Jetpack Compose.



### API Features:
- Limit of 30 calls per minute
- Random endpoint from a reCaptcha