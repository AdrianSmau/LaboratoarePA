package com.pa_lab11.Exceptions;

import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class RestControllerAdvisor {

    @ExceptionHandler(NameTakenException.class)
    public ResponseMessage handleNameTakenException(NameTakenException ex) {
        ResponseMessage responseMessage = new ResponseMessage("Name already taken!...");
        return responseMessage;
    }

    @ExceptionHandler(UnexistentPersonException.class)
    public ResponseMessage handleUnexistentPersonException(UnexistentPersonException ex) {
        ResponseMessage responseMessage = new ResponseMessage("Person does not exist in the DataBase!...");
        return responseMessage;
    }
/*
PUT http://localhost:8088/api/v2/persons/update/3?name=Jon

Transfer-Encoding: chunked
Date: Wed, 19 May 2021 16:48:40 GMT
Keep-Alive: timeout=60
Connection: keep-alive

{
  "message": "Person does not exist in the DataBase!..."
}

Response code: 200; Time: 132ms; Content length: 55 bytes

-------------------------------------------------------------------------------

DELETE http://localhost:8088/api/v2/persons/delete/Jon

HTTP/1.1 200
Content-Type: application/json
Transfer-Encoding: chunked
Date: Wed, 19 May 2021 16:49:15 GMT
Keep-Alive: timeout=60
Connection: keep-alive

{
  "message": "Person does not exist in the DataBase!..."
}

Response code: 200; Time: 46ms; Content length: 55 bytes

--------------------------------------------------------------------------------
POST http://localhost:8088/api/v2/persons/obj
Content-Type: application/json

{
  "name": "BeanPerson1"
}

HTTP/1.1 200
Content-Type: application/json
Transfer-Encoding: chunked
Date: Wed, 19 May 2021 16:51:19 GMT
Keep-Alive: timeout=60
Connection: keep-alive

{
  "message": "Name already taken!..."
}

Response code: 200; Time: 79ms; Content length: 36 bytes
*/

}
