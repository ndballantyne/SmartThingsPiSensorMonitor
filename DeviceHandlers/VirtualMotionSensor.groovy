/* Virtual Motion Sensor */

metadata {
	definition (name: "Virtual Motion Sensor", author: "ndballantyne@gmail.com", namespace: "ndballantyne") {
		capability "Motion Sensor"
        capability "Refresh"
        
        command "active"
        command "inactive"
	}

    simulator {

    }

    tiles {
        standardTile("motion", "device.motion", width: 2, height: 2, canChangeIcon: true) {
            state "active", label: 'Motion', icon: "st.motion.motion.active", backgroundColor: "#79b821"
            state "inactive", label: 'No Motion', icon: "st.motion.motion.active", backgroundColor: "#ffffff"
        }
		standardTile("refresh", "device.switch", inactiveLabel: false, decoration: "flat") {
            state "default", label:'Refresh', action:"device.refresh", icon: "st.secondary.refresh-icon"
        }

        main "motion"
        details(["motion","refresh"])
    }
}

def active() {
	log.debug "Motion Active"
    sendEvent (name: "motion", value: "active", isStateChange:true)
}

def inactive() {
	log.debug "Motion Inactive"
    sendEvent (name: "motion", value: "inactive", isStateChange:true)
}