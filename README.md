# Video Generation Jobs Service

A simple backend application to manage **video generation jobs**.

A client sends a product image URL and chooses a template.  
This application creates and manages **job entries** according to inputs by client.

We can directly run the application using Intellij (preferred) or EclispseLink.


## Tech Stack

- Java 21
- Spring Boot 3.8.4(Web, Validation)
- In-memory storage using HashMap
- Postman for testing APIs


## Sample API Calls

### POST http://localhost:8080/jobs

Input

```bash
{
  "productImageUrl": "https://example.com/shoe.png",
  "template": "zoom_in"
}

```
Output

`Returns the particular job with all fields populated`

Note: Validation checks of 'Not Null' is provided for both fields.

---
### GET http://localhost:8080/jobs/{id}

Output

`Fetches the job with particular id or if not present, throws 404 not found error`

---
### GET http://localhost:8080/jobs

Output

`Fetches all the jobs present in-memory with ordered as latest created`

---
### PATCH http://localhost:8080/jobs/{id}

Input

```bash
{
  "status":"PROCESSING"
}
```
Output

`Returns the particular job with updated status`

Note: The updation of status follows the sequence 

**PENDING → PROCESSING → COMPLETED → FAILED**
That means, if job is in PENDING status, it can only shift to PROCESSING and nothing else. Same for all. Also, input of status should be in capital letters
