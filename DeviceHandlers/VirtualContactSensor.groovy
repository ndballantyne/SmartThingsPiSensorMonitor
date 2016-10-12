/* Virtual Contact Sensor */

metadata {
	definition (name: "Virtual Contact Sensor", author: "ndballantyne@gmail.com", namespace: "ndballantyne") {
		capability "Contact Sensor"
        capability "Refresh"
        
        command "open"
        command "close"
	}

    simulator {
    
    }

    tiles {
        standardTile("contact", "device.contact", width: 2, height: 2, canChangeIcon: true) {
            state "open", label: '${name}', icon: "st.contact.contact.open", backgroundColor: "#79b821"
            state "closed", label: '${name}', icon: "st.contact.contact.closed", backgroundColor: "#ffffff"
        }
		standardTile("refresh", "device.switch", inactiveLabel: false, decoration: "flat") {
            state "default", label:'Refresh', action:"device.refresh", icon: "st.secondary.refresh-icon"
        }

        main "contact"
        details(["contact","refresh"])
    }
}

def open() {
	log.debug "Contact Open"
    sendEvent (name: "contact", value: "open", isStateChange:true)
}

def close() {
	log.debug "Contact Closed"
    sendEvent (name: "contact", value: "closed", isStateChange:true)
}