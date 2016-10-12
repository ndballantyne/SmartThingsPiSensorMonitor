# SmartThingsPiSensorMonitor

The aim of this project was to re-use wired sensors from an existing home alarm system. In my case the sensors have been attached to a Raspberry Pi, but this can be adapted to handle updates from anything capable of sending HTTP POST messages.

Each physical sensor attached to the Pi has a matching virtual sensor in SmartThings than can then be used by other smart apps and routines.

## Installation

In the SmartThings IDE:

1) Create device handlers for VirtualContactSensor and VirtualMotionSensor
2) Create devices for each sensor
3) Create device handler for Pi using Nicholas Wilde's device handler - https://github.com/nicholaswilde/berryio-smartthings/blob/master/devicetypes/nicholaswilde/raspberry-pi.src/raspberry-pi.groovy
4) Add the following code to the Parse() method of the Pi device handler

```
if (result.containsKey("zone")) {
    def actions = ['on','off','off','off']
    sendEvent (name:result.zone, value:actions[result.state], isStateChange:true)
}
```
5) Create smart app for Pi Sensor Monitor
6) Add device for the Pi and link to SmartThings
7) Set Device Network Id to the mac address of the Pi and comment out line 209 of the Pi device handler (which resets the device network id)
8) Configure Pi to send HTTP Post messages to SmartThings when zone status changes  
This can be done via the cloud, UPnP, or directly to the ST hub via HTTP. For simplicity I am posting directly to the hub. For this to work you need the IP and port of the hub, whhich can be retrieved from the IDE. The post is picked up by the device handler's Parse method so long as the device network id matches the MAC address that the request is coming from.  
The data sent should be a json string in the format { zone: "zone1", state: 0 }  
The device handler currently maps state 0 to open, and states 1, 2 & 3 to closed. States 2 & 3 are intended for future use to indicate faults.


## Limitations:
Currently each zone has to be specified as the correct sensor type in the smart app

## Related Links:
https://github.com/nicholaswilde/berryio-smartthings  
https://community.smartthings.com/t/modify-state-of-virtual-contact-open-closed/1508  
https://github.com/kholloway/smartthings-dsc-alarm  
https://github.com/jwsf/device-type.arduino-8-way-relay  