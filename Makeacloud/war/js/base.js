/**
 * @fileoverview
 * Provides methods for the Hello Endpoints sample UI and interaction with the
 * Hello Endpoints API.
 */

/** google global namespace for Google projects. */
//var google = google || {};
var makeacoffee = makeacoffee || {};

/** appengine namespace for Google Developer Relations projects. */
//google.appengine = google.appengine || {};
makeacoffee.endpoint = makeacoffee.endpoint || {};



/** samples namespace for App Engine sample code. */
//google.appengine.samples = google.appengine.samples || {};

/** hello namespace for this sample. */
//google.appengine.samples.hello = google.appengine.samples.hello || {};

/** Impostazioni per l'autenticazione ***********************************************************/

/** Caricamento ed inizializzazione API *********************************************************/

/**
 * Initializes the application.
 * @param {string} apiRoot Root of the API's path.
 */
makeacoffee.endpoint.init = function(apiRoot) {
  // Loads the OAuth and helloworld APIs asynchronously, and triggers login
  // when they have completed.
  var apisToLoad;
  var callback = function() {
    if (--apisToLoad == 0) {
      makeacoffee.endpoint.enableButtons();
    }
  }

  apisToLoad = 1; // must match number of calls to gapi.client.load()
  gapi.client.load('makeacoffee', 'v1', callback, apiRoot);
};




/** Una volta caricate le API (per ora solo una), abilitare i bottoni ****************************/

/**
 * Enables the button callbacks in the UI.
 */
makeacoffee.endpoint.enableButtons = function() {
  var getUser = document.querySelector('#getUser');
  getUser.addEventListener('click', function(e) {
    makeacoffee.endpoint.login(
        document.querySelector('#email').value);
  });

  var fireEvent = document.querySelector('#fireEvent');
  fireEvent.addEventListener('click', function(e) {
    makeacoffee.endpoint.fireEvent(
        document.querySelector('#device').value,
        document.querySelector('#event').value);
  });

};

makeacoffee.endpoint.enableLink




/** Funzioni JavaScript collegate ai bottoni "Submit".
 *  Invocano le API con gli opportuni parametri di ingresso
 *************************************************************************************************/

/**
 * Stampa la schermata dell'utente.
 * param {Object} user L'utente da visualizzare.
 */
makeacoffee.endpoint.printUser = function(user) {
  document.querySelector('#userHouseDevice').innerHTML = "";
  document.querySelector('#housesList').innerHTML = "";

  // Dati utente
  var element = document.createElement('div');
  //element.classList.add('row');
  element.innerHTML = user.email;
  //document.querySelector('#userHouseDevice').appendChild(element);
  document.querySelector('#userEmail').innerHTML = user.email;
  
  // Ciclo sulle case
//  console.log(user.houses[0]);
  var houses = user.houses;
  for (var house in houses) {
    var houseId = houses[house];
    element = document.createElement('div');
    element.innerHTML = "- " + house + "[" + houseId + "]";
    //document.querySelector('#userHouseDevice').appendChild(element);
    
    // Creo l'elemento rappresentante la casa
    element = document.createElement('a');
    element.setAttribute('class', 'list-group-item');
    element.setAttribute('href', '#');
    element.setAttribute('onclick', 'javascript:makeacoffee.endpoint.getHouse(\'' + houseId + '\');');
    element.innerHTML = "<span class=\"glyphicon glyphicon-home\"></span> " +
    	house + "<span class=\"glyphicon glyphicon-chevron-right pull-right\"></span>";
    document.querySelector('#housesList').appendChild(element);

//	<a href="#" class="list-group-item">
//	<span class="glyphicon glyphicon-home"></span> Casa ABC<span class="glyphicon glyphicon-chevron-right pull-right"></span>
//	</a>
};    

makeacoffee.endpoint.printHouse = function(house) {
	// Cancello la lista dei dispositivi
    document.querySelector('#devicesList').innerHTML = "";
    
    // Nome della casa
    document.querySelector('#houseName').innerHTML = house.name;

    // Ciclo sui dispositivi
    var devices = house.devices;
    for (var device in devices) {
        var deviceId = devices[device];
        element = document.createElement('div');
        element.innerHTML = "-- " + device + "[" + deviceId + "]";
        //document.querySelector('#userHouseDevice').appendChild(element);
        //document.querySelector('#deviceName').innerHTML = device;

        // Creo l'elemento rappresentante il device
        element = document.createElement('a');
        element.setAttribute('class', 'list-group-item');
        element.setAttribute('href', '#');
        element.setAttribute('onclick', 'javascript:makeacoffee.endpoint.getDevice(\'' + deviceId + '\');');
        element.innerHTML = "<span class=\"glyphicon glyphicon-paperclip\"></span> " +
        	device + "<span class=\"glyphicon glyphicon-chevron-right pull-right\"></span>";
        document.querySelector('#devicesList').appendChild(element);
    }
  }
};

makeacoffee.endpoint.printDevice = function(device) {
	// Imposto il nome del dispositivo
	document.querySelector('#deviceName').innerHTML = device.name;
	// Imposto l'id del dispositivo
	document.querySelector('#deviceId').innerHTML = device.id;

	// Imposto l'icona di refresh dello stato del dispositivo
	element = document.querySelector('#deviceRefresh');
    element.setAttribute('onclick', 'javascript:makeacoffee.endpoint.getDevice(\'' + device.id + '\');');

    // Stampo lo stato corrente
	makeacoffee.endpoint.printState(device.currentState);
};


makeacoffee.endpoint.printState = function(state) {
	// Cancello l'elenco degli eventi
	document.querySelector('#eventsList').innerHTML = "";
	// Recupero il device id
	var deviceId = document.querySelector('#deviceId').innerHTML;

	// Controllo che non sia uno stato intermedio
	if (state.intermediate) {
		// Stato intermedio
		// Estraggo il nome dello stato di partenza
		var from = state.id.substring(0, state.id.indexOf(';'));
		// Imposto il nome dello stato
		document.querySelector('#currentState').innerHTML = from;
		// Animazione guru-guru
        element = document.createElement('div');
        element.setAttribute('class', 'list-group-item');
        element.innerHTML = "<span class=\"glyphicon glyphicon-flash\"></span> E' in corso un evento </span>";
        document.querySelector('#eventsList').appendChild(element);
	} else {
		// Stato base
		// Imposto il nome dello stato
		document.querySelector('#currentState').innerHTML = state.id;

		// Ciclo sugli eventi
		var events = state.events;
	    for (i = 0; i < events.length; i++) {
	        var event = events[i];
	        // Creo l'elemento rappresentante l'evento
	        element = document.createElement('a');
	        element.setAttribute('class', 'list-group-item');
	        element.setAttribute('href', '#');
	        element.setAttribute('onclick', 'javascript:makeacoffee.endpoint.fireEvent(\'' + deviceId + '\', \'' + event.id + '\');');
	        element.innerHTML = "<span class=\"glyphicon glyphicon-fire\"></span> " +
	        	event.id + "<span class=\"glyphicon glyphicon-chevron-right pull-right\"></span>";
	        document.querySelector('#eventsList').appendChild(element);
	    }
	}
}


/** Invocazione delle API - collegati a buttoni / link ********************************************/

/**
 * Carica le informazioni legate ad un utente tramite API.
 * @param {string} email Mail dell'utente utilizzata per caricarlo.
 */
makeacoffee.endpoint.login = function(email) {
  gapi.client.makeacoffee.login({'email': email}).execute(
      function(resp) {
        if (!resp.code) {
          makeacoffee.endpoint.printUser(resp);
        }
      });
};

/**
 * Carica le informazioni legate ad una casa tramite API.
 * @param {string} id Identificativo univoco della casa.
 */
makeacoffee.endpoint.getHouse = function(id) {
  gapi.client.makeacoffee.houses.get({'id': id}).execute(
      function(resp) {
        if (!resp.code) {
          makeacoffee.endpoint.printHouse(resp);
        }
      });
};

/**
 * Carica le informazioni legate ad un dispositivo tramite API.
 * @param {string} id Identificativo univoco del dispositivo.
 */
makeacoffee.endpoint.getDevice = function(id) {
  gapi.client.makeacoffee.devices.get({'id': id}).execute(
      function(resp) {
        if (!resp.code) {
          makeacoffee.endpoint.printDevice(resp);
        }
      });
};

/**
 * Lancia l'evento indicato sul device in oggetto tramite API.
 * @param {string} device Identificativo univoco del device.
 * @param {string} event  Identificativo univoco dell'evento da lanciare.
 */
makeacoffee.endpoint.fireEvent = function(device, event) {
  console.log("Device:"+device+", Event:"+event);
  // Invocazione dell'API
  gapi.client.makeacoffee.devices.fire({
	  'device': device,
	  'event': event
  	}).execute(
	  // Dopo la sua invocazione, gestione della response
      function(resp) {
        if (!resp.code) {
          makeacoffee.endpoint.printState(resp);
        }
      });
};
