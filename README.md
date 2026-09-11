# 3Dolphins Priority Broadcast

A Java EE web application prototype for sending priority broadcasts to selected customers through a simulated WhatsApp integration.

## Tech Stack

* Java 11
* Jakarta EE 8
* JSF 2.3
* PrimeFaces 11
* Payara Micro 5
* Maven
* Docker

## Prerequisites

Make sure the following are installed:

* Docker
* Git

No local Java, Maven, or Payara installation is required when using Docker.

## Running the Application

### 1. Clone the repository

```bash
git clone https://github.com/qfwz/priority-broadcast-engine.git
cd priority-broadcast-engine
```

### 2. Build the Docker image

```bash
docker build -t priority-broadcast .
```

### 3. Run the application

```bash
docker run --rm --name priority-broadcast-app -p 8080:8080 priority-broadcast
```

### 4. Open the application

Open the following URL in your browser:

```text
http://localhost:8080/priority-broadcast/broadcast.xhtml
```

## How It Works

1. Select one or more customers from the table.
2. Click **Start Priority Broadcast**.
3. Each selected customer is processed asynchronously.
4. The customer status changes from:

    * `PENDING`
    * `SENDING`
    * `SENT` or `FAILED`
5. The progress indicator is updated in real time while the broadcast is running.

The WhatsApp integration is mocked and simulates a 1–2 second processing delay with a 10% failure rate.

## Stopping the Application

If the application is running in the foreground, press:

```text
Ctrl + C
```

Alternatively, from another terminal:

```bash
docker stop priority-broadcast-app
```

Because the container is started with `--rm`, the container is automatically removed after it is stopped.

## Challenge Answers

The answers to the challenge questions are provided in `CHALLENGE_ANSWERS.txt`.
