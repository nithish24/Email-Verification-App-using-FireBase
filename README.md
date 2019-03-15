# Email-Verification-App-using-FireBase
This app is used to verify that the  email address entered by the user is valid or invalid.
The user interface is very minimalistic. 
The user just has to enter the email and password to login if the email is already verified.
Else user has to create an account to get access.
The corresponding information is tossed up using toast.
Inordered to be notified about the errors that might occur, the errors are also directly displayed using toast.
The RESULTS folder contains the Screenshots.

**THE FIREBASE PART**

Since the app uses FIREBASE to verify the email account, It is necesary to have the firebase account for the developer and one must also add the necessary DEPENDENCY files to the APP_MODULE.
The MANIFEST file must include INTERNET permission, which is also provided.

**WORKING**

Launching the app will take the user to the LOGIN_ACTIVITY page (XML FILE IS ALSO INCLUDED), if the user already has an account he can login into the app and the MAIN_ACTIVITY page is displayed.
Else user must register, on registering the user will recieve the VERIFICATION email, only after he verifies his email account he gets access.
If the user no longer wants to use the app then he can either LOGOUT OR DELETE ACCOUNT.
If the user is loged out he can login again.
If the user deletes his account he has to go through the entire registration process again.

**ONE CAN PING ME AT nithish24499@gmail.com TO GET THE APK**

""I might be slow to reply""
