# Oauth2.0

OAuth is a authorization protocol and OAuth 2.0 is an authorization framework. 
OAuth = Open Authorization. 

OAuth is sometimes called delegated Authroization framework as it is possible for an application to delegate the authrization and authentication management to some external applications. For example, social logins uses oAuth authentication mechanism. Without oAuth, the user has to maintain  user credential for each authroized 3rd party resource they would use and moreover, it also limits the scope of a 3rd party application to access the user details.

## OAuth Actors

1. Resource Owner - User
2. Client - Web/Mobile application, postman
3. Resource Server - A server which exposes the service to be consumed by the user. ex: gmail, any custom developed service.
4. Authorization Server - A server which issues access token to client application after authorizing the user. ex: spring authrization server, keycloak, aws cognito, okta etc.


## Different types of client

1. **Confidential client** - This type of client can store its one client id and password. Example:- app deployed in a secured server.
2. **Public client** - This type of client can not store the client credentials in its local. Example:- single paged javascript application, native mobile or desktop application.

## Different Types of access token

1. **Identifier type access token** - This token doesn't contain any authroization information. It is used to refer to authroization information (e.g. access token) in a database.
2. **Access Token** - This token contains the authorization information which basically contains 3 parts 
                      a. header
                      b. payload or claim
                      c. signature.
   Now ideally, it should not contain any information regarding the currently authenticated user. It is usually obtained using another endpoint like /user-info. However, OpenID Connect 1.0 is a simple identity layer on top of the OAuth 2.0 protocol. It allows Clients to verify the identity of the End-User based on the authentication performed by an Authorization Server, as well as to obtain basic profile information. Hence, it is also called an identity provider. Openid 2.0 makes it more usable by native and mobile applications. 
   
   
## Grant Type
Grant type specifies the way a client receives it access  token. Depending on the client type, grant types differ.

1. Server side application - which can store the client credential - Authroization code grant type.
2. Server side application with no UI - which has no interface to interact with the user - Client credential grant type.
3. Single paged javascript application - which can't store its own cred - 
    a. PKCE enhanced authorization grant type
    b. Implicit grant type - where access token is fetched directly.
4. Native application - Authorization grant type.
5. Device - device code grant type.


1. **Authorization-code grant** - Please check this collection. Here client will be confidential.
2. **PKCE Enhanced authorization-code grant** -  In this case, client needs to provide code_verifier and code_challenge values. Following is the process to generate both.
     
     - **Code_verifier** = high-entropy cryptographic random STRING using the unreserved characters [A-Z] / [a-z] / [0-9] / "-" / "." / "_" / "~" from Section 2.3 of [RFC3986], with a minimum length of 43 characters and a maximum length of 128 characters. -- according to https://datatracker.ietf.org/doc/html/rfc7636#section-4.1 
   
   - **Code_challenge** 
     
     plain
      code_challenge = code_verifier

     S256
      code_challenge = BASE64URL-ENCODE(SHA256(ASCII(code_verifier))).
      
     Here, client will be public. Please check this collection

3. **Client to client/ machine to machine/ service to service** - In this case, client application will directly get the access token from the authorization server.
4. **Password grant type** - This is applicable when there is no redirection allowed. For example native or mobile application. 
    
   



