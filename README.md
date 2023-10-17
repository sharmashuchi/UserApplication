# UserApplication
Application for user onboarding and image upload

The application has endpoints to register a user, upload images using the user credentials, view the images uploaded by the user and delete selected image.

We need to configure below property values in 'application.properties' file.
1. image.upload-dir ## Path to store images locally before uploading them to imgur
2. image.api-token ## Access token shared by Imgure for the given client credentials.