<p align="center">
  <a href="https://www.youtube.com/" rel="noopener">
 <img width=200px height=200px src="https://upload.wikimedia.org/wikipedia/commons/b/b8/YouTube_Logo_2017.svg" alt="YouTube logo"></a>
</p>

<h3 align="center">Video-Streaming-Service</h3>


---

<p align="center">
    The goal of this project was to deepen my knowledge of Java and Spring framework,      
    learn frontend framework like Angular and to try to build a fullstack application.
    <br> 
</p>


## 🧐 About

This application recreates basic functionality of a YouTube application.  
When user starts the application he will see a home screen of videos.    

![home.png](Frontend/video-streaming-service-ui/src/assets/pictures/home.png)

He can then of course watch this video. With full experience.  
User can subscribe to the author of the video. Like (or dislike) this video and leave a comment.  

![video-details.png](Frontend/video-streaming-service-ui/src/assets/pictures/video-details.png)

Before that he must login into the application.  

![auth0.png](Frontend/video-streaming-service-ui/src/assets/pictures/auth0.png)

After watching a few videos user can look into his video history as well to his liked videos or check users he is subscribed to.  

![liked.png](Frontend/video-streaming-service-ui/src/assets/pictures/liked.png)

And if the user also wants to share his own videos he can do so.

![liked.png](Frontend/video-streaming-service-ui/src/assets/pictures/upload-video.png)
![liked.png](Frontend/video-streaming-service-ui/src/assets/pictures/save-video-details.png)



## 🏗️ Architecture

This is a high level diagram of my application

![diagram.png](Frontend/video-streaming-service-ui/src/assets/pictures/diagram.png)

User interacts with the frontend part which sends and receives request from Spring REST API.
The Server side communicates with database (it saves metadata there) and saves the actual images and videos to cloud.
Also user authentication is implemented from the frontend and backend.

## 🗃️ Database

User model:  
![user_model.png](Frontend/video-streaming-service-ui/src/assets/pictures/user_model.png)  
Video model:  
![video_model.png](Frontend/video-streaming-service-ui/src/assets/pictures/video_model.png)
Comment model:  
![comment_model.png](Frontend/video-streaming-service-ui/src/assets/pictures/comment_model.png)

## ⛏️ Built Using
-   ![Angular](https://img.shields.io/badge/Angular-%23DD0031.svg?style=for-the-badge&logo=angular&logoColor=white)
-   ![Spring](https://img.shields.io/badge/Spring-%236DB33F.svg?&style=for-the-badge&logo=spring&logoColor=white)
-    ![Spring Boot](https://img.shields.io/badge/Spring_Boot-%236DB33F.svg?&style=for-the-badge&logo=spring-boot&logoColor=white)
-   ![MongoDB](https://img.shields.io/badge/MongoDB-%2347A248.svg?&style=for-the-badge&logo=mongodb&logoColor=white)
