# UserApplication
Application for user onboarding and image upload

The application has endpoints to register a user, upload images using the user credentials, view the images uploaded by the user and delete selected image.

We need to configure below property values in 'application.properties' file.
1. image.upload-dir ## Path to store images locally before uploading them to imgur
2. image.api-token ## Access token shared by Imgure for the given client credentials.


Below is the link of a public collection in Postman for the APIs.
NOTE: All the APIs, apart from registration API are authenticated via Basic Auth and the credentials are the same as the userID and password used during registration.

https://elements.getpostman.com/redirect?entityId=16472175-cd838dbc-c9cd-418a-ae42-58e8a2142e8f&entityType=collection