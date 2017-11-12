# telldus-local
Simple small Java Spring Boot library to communicate with Telldus TellStick ZNet local server API

# Supported commands
## Devices
* Get list of devices
* Get device info
* Turn device off
* Turn device on

## Sensors
* Get list of sensors
* Get sensor info

# Configuration
You will need to get a valid token yourself for now. Read how to do it here: http://api.telldus.net/localapi/api.html
 
In application.properties and add the following properties:
```
telldus.bearer-token=[token from telldus]
telldus.url=[ip-to-your-telldus]/api
```

