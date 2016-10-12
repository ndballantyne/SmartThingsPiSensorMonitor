/**
 *  PiSensorMonitor
 *
 *  Copyright 2016 Neil ballantyne
 *
 *  Monitor legacy home alarm sensors attached to Raspberry Pi through SmartThings <https://github.com/ndballantyne/SmartThingsPiAlarm>
 *
 *  Contributors:
 *  
 *  
 *  Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except
 *  in compliance with the License. You may obtain a copy of the License at:
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software distributed under the License is distributed
 *  on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the License
 *  for the specific language governing permissions and limitations under the License.
 *
 */

 definition(
    name: "Pi Sensor Monitor",
    version: "0.6",
    namespace: "ndballantyne",
    author: "Neil Ballantyne",
    description: "SmartApp to manage integration with RPi controlling legacy home alarm sensors",
    category: "My Apps",
    iconUrl: "https://s3.amazonaws.com/smartapp-icons/Convenience/Cat-Convenience.png",
    iconX2Url: "https://s3.amazonaws.com/smartapp-icons/Convenience/Cat-Convenience@2x.png",
    iconX3Url: "https://s3.amazonaws.com/smartapp-icons/Convenience/Cat-Convenience@2x.png")

preferences {
	section("Assign virtual sensors") {
		input "sensor1", title: "Select sensor for Zone 1", "capability.contactSensor"
        input "sensor2", title: "Select sensor for Zone 2", "capability.motionSensor", required: false
        input "sensor3", title: "Select sensor for Zone 3", "capability.contactSensor", required: false
        input "sensor4", title: "Select sensor for Zone 4", "capability.motionSensor", required: false 
        input "sensor5", title: "Select sensor for Zone 5", "capability.motionSensor", required: false
        input "sensor6", title: "Select sensor for Zone 6", "capability.motionSensor", required: false
        input "sensor7", title: "Select sensor for Zone 7", "capability.motionSensor", required: false
        input "sensor8", title: "Select sensor for Zone 8", "capability.contactSensor", required: false
	}
    section("Assign Pi") {
		input "rpi", "device.raspberryPi"
    }    
}

def installed() {
	log.debug "Installed with settings: ${settings}"
	subscribe()
}

def updated() {
	log.debug "Updated with settings: ${settings}"
	unsubscribe()
	subscribe()
}


def subscribe() {
    subscribe(rpi, "zone1.on", zoneUpdate)
    subscribe(rpi, "zone1.off", zoneUpdate)
    subscribe(rpi, "zone2.on", zoneUpdate)
    subscribe(rpi, "zone2.off", zoneUpdate)
    subscribe(rpi, "zone3.on", zoneUpdate)
    subscribe(rpi, "zone3.off", zoneUpdate)
    subscribe(rpi, "zone4.on", zoneUpdate)
    subscribe(rpi, "zone4.off", zoneUpdate)
    subscribe(rpi, "zone5.on", zoneUpdate)
    subscribe(rpi, "zone5.off", zoneUpdate)
    subscribe(rpi, "zone6.on", zoneUpdate)
    subscribe(rpi, "zone6.off", zoneUpdate)
    subscribe(rpi, "zone7.on", zoneUpdate)
    subscribe(rpi, "zone7.off", zoneUpdate)
    subscribe(rpi, "zone8.on", zoneUpdate)
    subscribe(rpi, "zone8.off", zoneUpdate)
}

def zoneUpdate(evt) {
	log.debug "${evt.Name} was turned ${evt.Value}"

	switch (evt.Name) {
		case "zone1":
			if (evt.Value == "on") {
				zone1.open()
			}
			else
			{
				zone1.close()
			}
			break
		case "zone2":
			if (evt.Value == "on") {
				zone2.active()
			}
			else
			{
				zone2.inactive()
			}
			break
		case "zone3":
			if (evt.Value == "on") {
				zone3.open()
			}
			else
			{
				zone3.close()
			}
			break
		case "zone4":
			if (evt.Value == "on") {
				zone4.active()
			}
			else
			{
				zone4.inactive()
			}
			break
		case "zone5":
			if (evt.Value == "on") {
				zone5.active()
			}
			else
			{
				zone5.inactive()
			}
			break
		case "zone6":
			if (evt.Value == "on") {
				zone6.active()
			}
			else
			{
				zone6.inactive()
			}
			break
		case "zone7":
			if (evt.Value == "on") {
				zone7.active()
			}
			else
			{
				zone7.inactive()
			}
			break
		case "zone8":
			if (evt.Value == "on") {
				zone8.open()
			}
			else
			{
				zone8.close()
			}
			break
		default:
	}
}
