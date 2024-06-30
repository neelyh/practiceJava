# Welcome
Welcome to the iManage Sales System. This is a demo application meant to simulate a new sales system being developed for 
iManage. It's a work in progress and as a new developer joining the team you've been asked to complete some work.

# Your tasks

1. The application doesn't currently compile due to bug somewhere - fixing that is step 1.
2. Now you've got it compiling we are going to add a new feature - The ability to create a Sale!
   1. A sale is represented by an Entity called iSale - which can be found in the entities folder.
   2. We need to add a new end point that takes a list of objects representing sales. This endpoint should:
      1. Validate that the sales being created do not already exist.
      2. It should return errors if key information is missing.
   3. We've added code so now we need to test the code - Add some coverage.
3. Add new endpoint to fetch all sales for a given sales person which can be filtered to specific customers.
4. There are potential performance issues with the application. Identify where they may occur and add a comment starting with //TODO: FIX PERFORMANCE along with a short summary on why it could have performance issues

# Stretch goals

- Create new endpoint to handle deleting Customers or Salespeople from the DB and any necessary tidy up. 
- Suggest an authentication model that could be implemented for this project
- Suggest any improvements/current issues to project or technologies used. 

# Tips

- Do not assume any of the code here is the best way of doing something - if you want to change it then change it
- There are no gotchas - if something looks like a bug it probably is so feel free to fix
- Annotate your code with comments - As this is a technical exercise feel free to add as much context to your code as you wish
- The application runs on 8080 by default and has no authentication so 

## Login to the H2 console to see the data being persisted
On your choice of browser, enter [http://localhost:{serverport}/h2-console]()
and use the following to login to the console:
```
  JDBC URL: jdbc:h2:mem:SEC
  User Name: sa
  Pasword: <leave this empty>
```

# Hannah Neely Responses
`N.B: Apologies, the only files familiar in this repo were the README and .gitignore so forgive me if some java/spring boot best practices have been overlooked.`

## Application not running
- Gradle version needed updated and then mapping the same column twice in `iSale.java`

## Adding sales creation
- Added the endpoint
- Checked if any info was missing and if it existed in the db
- Wanted to return useful errors so created some that would throw a useful response
- Added a test for sale already existing

## Endpoint to fetch sales for a salesperson
- Create some sales first
- Test by sending a GET request to:
- `http://localhost:8080/api/salesForSalesperson?salespersonId=2`
- To filter send request to
- `http://localhost:8080/api/salesForSalesperson?salespersonId=2&customerId=1`

## Performance issues
- I query for the customer and salesperson when linking them to a sale, not good as it can lead to N+1 query problem. I'm unfamiliar with persistence and hibernate and stuck for time, so this was the workaround to get the functionality working! Going forward, maybe there's some better cascade settings, or I could write a better query or even fetching the data in bulk etc should the application continue to scale.
- Enabling caching is also a good one for the api call that gathers all the sales by a sales person

## Considerations when deleting customers/salesperson
- First off I'm not a big fan of hard deletion in the db because human error is a thing, however there's always ways to minimise that by writing scripts
- When deleting entities its important to consider dependencies as we run the risk of orphaning records, so we would need to delete associated sales
- Maybe we decide to only allow deletion if no sales are associated to a customer etc
- How the deletion could affect other parts of the application, eg front end etc, is there defaults set up on the front end
- Take a log of whats been deleted with key fields, enable audit trails on the DB

## Authentication Model
- Since it's just an API right now, anyone could send a request to it. Keeping it super simple by adding an authorisation header (API key)
- I can see that spring has security out the box (`org.springframework.security.authentication`) so that makes things easier!
- Also of course adding dedicated users and passwords for the H2 console
- You could also put the API behind a gateway

## Suggestions for improving the application
- Currently the application is using HTTP, really it should use HTTPS for security purposes.
- Also of course its important to implement some authentication
- Useful error handling throughout the application with correct HTTP codes being returned when it comes to API's.
