# Offline first app to browse anime series

## ScreenShots :- 
|   |   |   |
|---|---|---|
| <img src="https://github.com/user-attachments/assets/e0fc9293-0f2b-4499-80e0-8e692d5509b2" width="250"/> | <img src="https://github.com/user-attachments/assets/37a67d52-290f-4a9a-822b-e0969ab6b2c5" width="250"/> | <img src="https://github.com/user-attachments/assets/d9a9d4a1-69d2-4968-b254-b2d8aec2804a" width="250"/> |

## Video :- 

<p align="center">
  <video src="https://github.com/user-attachments/assets/8e547455-1be1-47e3-ad44-1880c419af0e" width="600" controls />
</p>


## Core Features :- 
1. Displays top anime list with pagination using Paging 3
2. Play Trailer of Animes :- Implemented via YoutubeVideoPlayer
3. When the app is used in offline mode , trailer option is removed and is replaced with banner
4. ### Offline-first architecture:
   - **Cached data stored locally using Room**.
   - Previously loaded content available without internet.
5. Connectivity monitoring
6. Supports Both Dark and Night Mode
7. App Survives Process Death

## Architecture Used :- 
### MVVM + Clean
Clean architecture separation:
- Data layer (API + Room)
- Domain mapping
- ViewModel + StateFlow
- UI using JetPack Compose

- The data layer is designed to be modular and abstracted. The networking implementation is decoupled from the rest of the application, making it easy to replace Retrofit with another HTTP client (e.g., Ktor) without impacting other layers of the app.

## Assumptions that were made :- 
1. Even though pagination wasn’t clearly required, I felt it made sense to include it because the API can return a large dataset. Implementing pagination ensures better performance and a smoother user experience.

## Known Limitations :- 
- ~~On the first launch, only the first page is loaded automatically. From the second launch onward, pagination functions correctly and additional pages are fetched smoothly during scrolling.~~ - Fixed




