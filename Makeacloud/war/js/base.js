/**
 * @fileoverview
 * Provides methods for the Hello Endpoints sample UI and interaction with the
 * Hello Endpoints API.
 */

/** google global namespace for Google projects. */
var google = google || {};

/** appengine namespace for Google Developer Relations projects. */
google.appengine = google.appengine || {};

/** samples namespace for App Engine sample code. */
google.appengine.samples = google.appengine.samples || {};

/** hello namespace for this sample. */
google.appengine.samples.hello = google.appengine.samples.hello || {};

/** Impostazioni per l'autenticazione ***********************************************************/

/** Caricamento ed inizializzazione API *********************************************************/

/**
 * Initializes the application.
 * @param {string} apiRoot Root of the API's path.
 */
google.appengine.samples.hello.init = function(apiRoot) {
  // Loads the OAuth and helloworld APIs asynchronously, and triggers login
  // when they have completed.
  var apisToLoad;
  var callback = function() {
    if (--apisToLoad == 0) {
      google.appengine.samples.hello.enableButtons();
    }
  }

  apisToLoad = 1; // must match number of calls to gapi.client.load()
  gapi.client.load('makeacoffee', 'v1', callback, apiRoot);
};




/** Una volta caricate le API (per ora solo una), abilitare i bottoni ****************************/

/**
 * Enables the button callbacks in the UI.
 */
google.appengine.samples.hello.enableButtons = function() {
  var getUser = document.querySelector('#getUser');
  getUser.addEventListener('click', function(e) {
    google.appengine.samples.hello.getUser(
        document.querySelector('#id').value);
  });

  var fireEvent = document.querySelector('#fireEvent');
  fireEvent.addEventListener('click', function(e) {
    google.appengine.samples.hello.fireEvent(
        document.querySelector('#device').value,
        document.querySelector('#event').value);
  });

};





/** Funzioni JavaScript collegate ai bottoni "Submit".
 *  Invocano le API con gli opportuni parametri di ingresso
 *************************************************************************************************/

/**
 * Prints a greeting to the greeting log.
 * param {Object} greeting Greeting to print.
 */
google.appengine.samples.hello.printUser = function(user) {
  document.querySelector('#userHouseDevice').innerHTML = "";

  // Dati utente
  var element = document.createElement('div');
  //element.classList.add('row');
  element.innerHTML = user.id + " (admin=" + user.admin + ")";
  document.querySelector('#userHouseDevice').appendChild(element);
  document.querySelector('#userName').innerHTML = user.id;
  
  // Ciclo sulle case
  var houses = user.houses;
  for (i = 0; i < houses.length; i++) {
    var house = houses[i];
    element = document.createElement('div');
    element.innerHTML = "- " + house.nickname;
    document.querySelector('#userHouseDevice').appendChild(element);
    
    // Creo l'elemento rappresentante la casa
    element = document.createElement('a');
    element.setAttribute('class', 'list-group-item');
    element.setAttribute('href', '#');
    element.innerHTML = "<span class=\"glyphicon glyphicon-home\"></span> " +
    	house.nickname + "<span class=\"glyphicon glyphicon-chevron-right pull-right\"></span>";
    document.querySelector('#housesList').appendChild(element);

//	<a href="#" class="list-group-item">
//	<span class="glyphicon glyphicon-home"></span> Casa ABC<span class="glyphicon glyphicon-chevron-right pull-right"></span>
//	</a>
    
    
    // Salvo l'id del primo device nel form per lanciare l'evento
    var firstDevice = house.devices[0];
    document.querySelector('#device').value = firstDevice.id;

    // Ciclo sui dispositivi
    var devices = house.devices;
    for (j = 0; j < devices.length; j++) {
        var device = devices[j];
        element = document.createElement('div');
        element.innerHTML = "-- " + device.id;
        document.querySelector('#userHouseDevice').appendChild(element);
        document.querySelector('#deviceName').innerHTML = device.id;

        // Creo l'elemento rappresentante il device
        element = document.createElement('a');
        element.setAttribute('class', 'list-group-item');
        element.setAttribute('href', '#');
        element.innerHTML = "<span class=\"glyphicon glyphicon-paperclip\"></span> " +
        	device.id + "<span class=\"glyphicon glyphicon-chevron-right pull-right\"></span>";
        document.querySelector('#devicesList').appendChild(element);

//		<a href="#" class="list-group-item">
//		<span class="glyphicon glyphicon-paperclip"></span> Alicia-1234567890<span class="glyphicon glyphicon-chevron-right pull-right"></span>
//		</a>

		// Stato corrente
        google.appengine.samples.hello.printState(device.currentState);
    }
  }
};

google.appengine.samples.hello.printState = function(state) {
    document.querySelector('#currentStateP').innerHTML = "";
    document.querySelector('#currentState').innerHTML = state.id;        
    document.querySelector('#eventsList').innerHTML = "";

    var element = document.createElement('div');
    element.innerHTML = "--- " + state.id;
    document.querySelector('#currentStateP').appendChild(element);
    
    // Ciclo sugli eventi dello stato corrente
    var events = state.events;
    for (k = 0; k < events.length; k++) {
        var event = events[k];
        element = document.createElement('div');
        element.innerHTML = "---- " + event.id + " --\> " + event.toState;
//        element.innerHTML = "---- <a onclick=\"javascript:google.appengine.samples.hello.fireEvent(document.querySelector('#device').value,'" + event.id + "');\">" + event.id + "</a> --\> " + event.toState;
        document.querySelector('#currentStateP').appendChild(element);        

        // Creo l'elemento rappresentante l'evento
        element = document.createElement('a');
        element.setAttribute('class', 'list-group-item');
        element.setAttribute('onclick', 'javascript:google.appengine.samples.hello.fireEvent(document.querySelector(\'#device\').value,\'' + event.id + '\');');
        element.innerHTML = "<span class=\"glyphicon glyphicon-fire\"></span> " +
        	event.id + "<span class=\"glyphicon glyphicon-chevron-right pull-right\"></span>";
        document.querySelector('#eventsList').appendChild(element);
        
//		<a href="#" class="list-group-item">
//		<span class="glyphicon glyphicon-fire"></span> Make-a-coffee<span class="glyphicon glyphicon-chevron-right pull-right"></span>
//		</a>
    }
};

/**
 * Carica le informazioni legate ad un utente tramite API.
 * @param {string} id Identificativo univoco dell'utente da caricare.
 */
google.appengine.samples.hello.getUser = function(id) {
  gapi.client.makeacoffee.makeACoffee.getUser({'id': id}).execute(
      function(resp) {
        if (!resp.code) {
          google.appengine.samples.hello.printUser(resp);
        }
      });
};

/**
 * Lancia l'evento indicato sul device in oggetto tramite API.
 * @param {string} device Identificativo univoco del device.
 * @param {string} event  Identificativo univoco dell'evento da lanciare.
 */
google.appengine.samples.hello.fireEvent = function(device, event) {
  console.log("Device:"+device+", Event:"+event);
  // Invocazione dell'API
  gapi.client.makeacoffee.makeACoffee.fireEvent({
	  'device': device,
	  'event': event
  	}).execute(
	  // Dopo la sua invocazione, gestione della response
      function(resp) {
        if (!resp.code) {
          google.appengine.samples.hello.printState(resp);
        }
      });
};
