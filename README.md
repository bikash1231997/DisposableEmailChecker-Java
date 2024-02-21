# Disposable Email Domain Checker

This Spring Boot application provides endpoints to check if a given email domain or email address is from a disposable email provider.

## Getting Started

To run the application locally, make sure you have Java and Gradle installed on your system. Then, follow these steps:

1. Clone this repository to your local machine.
2. Navigate to the project directory.
3. Run the following command to build the project:

```bash
./gradlew build
```

4. Once the build is successful, run the following command to start the application:

```bash
./gradlew bootRun
```

The application will start on the default port `8072`.

## Endpoints

### Check Disposable Domain

Endpoint: `/mail/check-disposable-domain/{domain}`

This endpoint checks if the given domain is disposable or not. Replace `{domain}` with the domain name you want to check.

Example Request:
```http
GET /mail/check-disposible-domain?domain=example.com HTTP/1.1
Host: localhost:8072
```

### Check Disposable Email

Endpoint: `/mail/check-disposable-email/{email}`

This endpoint checks if the domain of the given email address is disposable or not. Replace `{email}` with the email address you want to check.

Example Request:
```http
GET /mail/check-disposible-email?email=abc@example.com HTTP/1.1
Host: localhost:8072
```

## Technologies Used

- Java
- Spring Boot
- Gradle
- opencsv

## Contributing

Contributions are welcome! If you have any suggestions, improvements, or feature requests, please open an issue or submit a pull request.

## License

This project is licensed under the MIT License - see the [LICENSE](LICENSE) file for details.
