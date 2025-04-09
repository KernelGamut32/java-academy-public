# Registration Manager (with AuthN/AuthZ)

## Configuring Keycloak to add security to API

* Execute `docker-compose --env-file .env up -d` using the updated docker-compose.yml file which includes Keycloak configuration detail
* Navigate to <http://localhost:8180> and login to Keycloak using `admin` or username and password (or alternative credentials if you used something different in docker-compose.yml.
* Click the dropdown in the left navigation menu (click the "hamburger" menu if the left navigation menu is not showing)
* Choose "Create realm"
* Under "Realm name" enter something like "RegistrationRealm", make sure "Enabled" is set to "On", and click "Create"
* Make sure your newly created realm is selected from the dropdown and navigate to "Clients" and click "Create client"
* For "Client type", select "OpenID Connect"
* For "Client ID", use something like "registration-client" and click "Next"
* Set "Client authentication" to "On", set "Authorization" to "On", and leave "Authentication flow" settings at their defaults; click "Next"
* Set "Root URL" to <http://localhost:8080> (to reference your locally running Spring Boot application)
* Leave all other settings at their defaults and click "Save"
* Make note of the Client ID ("registration-client") and the provided "Client Secret" - these will be used in your API configuration
* In the left navigation menu, select "Realm roles" and click "Create role"
* Use a role name like "registration-reader"
* Repeat the same step, creating a role called "registration-writer"
* In the left navigation menu, select "Users"
* Create a test user with a username like "user_one"
* Toggler "Email verified" to "On"
* Fill out the user information for email, first name, and last name (e.g., "<user.one@test.com>", "User", and "One")
* Click "Create"
* Make sure "Enabled" is toggled on
* Click "Credentials" and click "Set password" - specify a value that you can remember (e.g., "user_one_pwd!")
* Set "Temporary" to "Off" and click "Save"
* Click "Role mapping", click "Assign role"
* Click the dropdown on the left and select "Filter by realm roles", select "registration-reader", and click "Assign"
* Repeat that same process to create a second test user and assign that user to the "registration-writer" role
* Review your configuration definitions and update if necessary to match your setup
