# Alexa Skill Example in Java

This project demonstrate some of the functionality which can be achieved with an Alexa skill service. This services enables a user to:


## Overview

This app was also built on OSX using the Eclipse IDE. It should run fairly easily on Windows and Linux. This project is essentially a Spark project using Maven.

## Setup


**Run the application, which should start the server**


**Setup ngrok to expose the endpoint**

    ngrok http 4567

**Setup your skill interface**

* Go to [Amazon Developer's Portal](https://developer.amazon.com) and create a new Alexa skill
* Setup the interaction model using the provided `Intent Schema` and `Sample Utterance` which can be found in the `src/main/java/speechAssets` folder.
* Setup the endpoint to point to your ngrok tunnel, example: `https://81429dd5.ngrok.io/alexa`
