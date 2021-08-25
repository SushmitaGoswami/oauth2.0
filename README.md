# Oauth2.0

OAuth is a authorization protocol and OAuth 2.0 is an authorization framework. 
OAuth = Open Authorization. 

OAuth is sometimes called delegated Authroization framework as it is possible for an application to delegate the authrization and authentication management to some external applications. For example, social logins uses oAuth authentication mechanism. Without oAuth, the user has to maintain  user credential for each authroized 3rd party resource they would use and moreover, it also limits the scope of a 3rd party application to access the user details.

## OAuth Actors

1. Resource Owner - User
2. Client - Web/Mobile application, postman
3. Resource Server - A server which exposes the service to be consumed by the user. ex: gmail, any custom developed service.
4. Authorization Server - A server which issues access token to client application after authorizing the user. ex: spring authrization server, keycloak, aws cognito, okta etc.


## Spring seccurity oAuth implementation



