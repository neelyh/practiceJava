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
-