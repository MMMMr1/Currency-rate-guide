# Description
 Service for receiving and displaying exchange rate data from the NBRB website (API of the national bank: https://www.nbrb.by/apihelp/exrates).


### Prerequisites
- Java 21 

### Technology Stack

#### Backend:
- Java
- Spring Boot
- Spring Data JPA
- Liquibase  

#### Database
- H2 Database

#### Testing
- JUnit 5
- Spring Boot Test 
- WireMock

#### Documentation
- Swagger (OpenAPI 3.0)


## Usage

Swagger:  http://localhost:8080/swagger-ui/index.html#/



POST http://localhost:8080/api/v1/rates?periodicity=0&ondate=2024-06-10


##### Request parameters:
| Parameter     | Type        | Description                                                                                                    |
|---------------|-------------|----------------------------------------------------------------------------------------------------------------|
| `periodicity` | `String`    | frequency of setting the rate (0 - daily, 1 - monthly) (default = 0)                                           |
| `ondate`      | `String`    | the date on which the rate is requested (default = current day)                                                |

##### Response parameters:
| Parameter | Type     | Description                                                                                                           |
|-----------|----------|-----------------------------------------------------------------------------------------------------------------------|
| `logref ` | `String` | Error type (intended for machine processing): `error` - Indication that the error is not bound to a field             |
| `message` | `String` | Error message                                                                                                         |



GET http://localhost:8080/api/v1/rates/{curId}?ondate=2024-07-01&parammode=0


##### Request parameters:
| Parameter     | Type           | Description                                                                                                  |
|---------------|----------------|--------------------------------------------------------------------------------------------------------------|
| `curId`       | `String`       | inner code(cur_id). Required                                                                                 |
| `parammode`   | `int`          | cur_id argument format: 0 - internal currency code,/n                                                        |
|               |                | 1 - three-digit numeric currency code according to ISO 4217 standard,                                        |
|               |                | 2 - three-digit alphabetic currency code (ISO 4217). Default = 0                                             |
| `ondate`      | `string(date)` | the date on which the rate is requested                                                                      |

##### Response parameters:
| Parameter         | Type           | Description                                                                                                |
|-------------------|----------------|------------------------------------------------------------------------------------------------------------|
| `curId`           | `String`       | inner code(cur_id). Required                                                                               |
| `date`            | `string(date)` | the date on which the rate is requested (default = current day)                                            |
| `curOfficialRate` | `string`       | exchange rate                                                                                              |
| `logref `         | `String`       | Error type (intended for machine processing)                                                               |
| `message`         | `String`       | Error message                                                                                              



Example API Requests: 

1. **Save the exchange rates:**

```bash
curl -X 'POST' \
  'http://localhost:8080/api/v1/rates' \
  -H 'accept: application/json' \
  -d ''
```

```bash
curl -X 'POST' \
  'http://localhost:8080/api/v1/rates?periodicity=1&ondate=2024-06-06' \
  -H 'accept: application/json' \
  -d ''
```

2. **Get the exchange rate for the day on which the rate is requested:**

```bash
curl -X 'GET' \
  'http://localhost:8080/api/v1/rates/431?ondate=2024-07-01' \
  -H 'accept: application/json'
```

```bash
curl -X 'GET' \
  'http://localhost:8080/api/v1/rates/840?ondate=2024-07-01&parammode=1' \
  -H 'accept: application/json'
```

```bash
curl -X 'GET' \
  'http://localhost:8080/api/v1/rates/USD?ondate=2024-07-01&parammode=2' \
  -H 'accept: application/json'
```


### Installation

**Clone the repository:**
    ```bash
    git https://github.com/MMMMr1/currency-rate-guide.git
    ```

