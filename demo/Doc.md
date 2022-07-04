This backend has been built using SpringBoot framework.<br><br>
To be honest this is the first time I am building a Spring Boot API or rather working with Spring Boot. That is why it took more than 6 days for me to develop this API with Spring Boot.
So for that I would like to say a big Thanks to the lovely people and the recruiting team at Bayzat for considering my application and providing me with this great opportunity to learn and develop a BackEnd API system with SpringBoot.

<i>On a side note: Even though I would be really disappointed if I don't get shortlisted for further consideration, I am very happy right now since I got to learn Spring Boot and I see this as a great learning curve for me.
It has now opened borders for me to explore in my career.</i>

<br>
<u>So here it is about the system and how to use it:</u>

Design Decisions:
<ol>
    <li>The classes identified were:
        <ol>
            <li>AppUser : as users of the system</li>
            <li>Alert : as alerts that could be created by users</li>
            <li>Currency : as currencies listed by the company</li>
            <li>ScheduleTasks : to run the background job </li>
        </ol>
    </li>
    <li>One User can have may alerts created</li>
    <li>By default when you run the application, there are pre-defined users,alerts,currencies defined.</li>
</ol>


<u>To Setup the project:</u>

Pre-conditions:
<ul>
    <li>Have Postgres installed</li>
    <li>Have IntelliJ installed</li>
    <li>Have Postman installed</li>
</ul>
<br>
Steps
<ol>
    <li>Clone the project</li>
    <li>Open the Project using IntelliJ</li>
    <li>Perform all other necessary configuration when prompted</li>
    <li>Build the project</li>
    <li>Run the project
    <br>Note: As soon as you run the project, the background job will start running.</li>
</ol>
<br>
Sending requests through PostMan:
<ol>
    <li>Open Postman</li>
    <li>Import the json file named as "BayzTrackerApp.postman_collection.json", as a collection to your PostMan Application</li>
    <li>Try out different requests and see how they work</li>
</ol>

<br>

<u>Tips</u>
<ul>
    <li>Try creating a new user with "Create New User" request</li>
    <li>Then view the getUsers end point by simply browsing into http://localhost:8080/api/v1/user in your favorite web browser. This will give you a list of all the users in the Database</li>
    <li>Do the same to other requests and see how they behave</li>
    <li>Here are the links to some of the {GET} end points:</li>
    <ol>
        <li>http://localhost:8080/api/v1/user</li>
        <li>http://localhost:8080/api/v1/currency</li>
        <li>http://localhost:8080/api/v1/alert</li>
    </ol>
</ul>

<br>Note:
Again due to the time constrain and great learning curve, I was not able to write UnitTests as expected, even though I attempted to write a few.
I am hoping to improvise on this and fine tune the entire system by working continuously on this. 
<br>

Once again, thank you so much for this great opportunity!
