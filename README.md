# Application Layer
## Business Layer
- Provides the implementation for the business logic of the application, including such things as business rules, validations, and calculation logic.
Testing
- Our Service layer code is dependent on our DAO/Repository.
- To test the Service layer, we do not need to know or care about how the persistence layer is implemented.
- We should be able to write and test our Service layer code without wiring in our full persistence layer.
- Using mockito to help in the testing process.
### Mockito
- Open-source mocking framework for Java. 
- Is a mocking framework for unit tests in Java.
- It has been designed to be intuitive to use when the test needs mock.
More information:
https://github.com/mockito/mockito
https://github.com/mockito/mockito/wiki