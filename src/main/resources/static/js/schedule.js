mapboxgl.accessToken = 'pk.eyJ1IjoiY2tjNTU5OSIsImEiOiJja24zZzM2M2Ywc3A5Mm9tcnoybDhhMWNhIn0.uKc_lvBhgVVnbn8IDZ8Odg';
var map = new mapboxgl.Map({
 container: 'map',
 style: 'mapbox://styles/mapbox/streets-v11',
 center: [-77.8695084, 40.8071263],
 zoom: 13
});

var geocoder = new MapboxGeocoder({
 accessToken: mapboxgl.accessToken,
 marker: {
 color: 'orange'
},
mapboxgl: mapboxgl
});


//map.on('load', function() {
//  map.addControl(geocoder);
//  geojson.features.forEach(function(marker) {
//
//    // create a HTML element for each feature
//    var el = document.createElement('div');
//    el.className = 'marker';
//
//    // make a marker for each feature and add to the map
//    new mapboxgl.Marker(el)
//      .setLngLat(marker.geometry.coordinates)
//      .setPopup(new mapboxgl.Popup({ offset: 25 }) // add popups
//          .setHTML('<b>' + marker.properties.name +
//          '</b><p>address: ' + marker.properties.address + '</br>' +
//          'address2: ' + marker.properties.address2 + '</br> ' +
//          'city/state: ' + marker.properties.city + ' ' + marker.properties.state + ', ' +
//          marker.properties.zip + '</br>' +
//          'county: ' + marker.properties.county + '</br>' +
//          'phone: ' + marker.properties.phone + '</br>' +
//          'link: <a href="' + marker.properties.link + '">' + marker.properties.link + '</p>'))
//      .addTo(map);
//  });
//})

map.on('data', function() {
  geojson.features.forEach(function(marker) {

      // create a HTML element for each feature
      var el = document.createElement('div');
      el.className = 'marker';

      // make a marker for each feature and add to the map
      new mapboxgl.Marker(el)
        .setLngLat(marker.geometry.coordinates)
        .setPopup(new mapboxgl.Popup({ offset: 25 }) // add popups
            .setHTML('<b>' + marker.properties.name +
            '</b><p>address: ' + marker.properties.address + '</br>' +
            'address2: ' + marker.properties.address2 + '</br> ' +
            'city/state: ' + marker.properties.city + ' ' + marker.properties.state + ', ' +
            marker.properties.zip + '</br>' +
            'county: ' + marker.properties.county + '</br>' +
            'phone: ' + marker.properties.phone + '</br>' +
            'link: <a href="' + marker.properties.link + '">' + marker.properties.link + '</p>'))
        .addTo(map);
    });
})


var geojson = {
  type: 'FeatureCollection',
  features: []
};

var latitude
var longitude
var coordinates = {
  latitude: 0,
  longitude: 0
};

console.log(geojson);

function findByZipCode(e) {
  if (e.preventDefault) e.preventDefault();

  var zip = searchForm.elements['zip'];
  var distance = searchForm.elements['distance'];
  var fetchedData = getDataFromDataSource(zip.value, distance.value);
  console.log(geojson);

  return false;
}

var searchForm = document.getElementById('search-form');
if (searchForm.attachEvent) {
  searchForm.attachEvent("submit", findByZipCode);
} else {
  searchForm.addEventListener("submit", findByZipCode);
}

function getDataFromDataSource(zip, distance) {

fetch('http://api.positionstack.com/v1/forward?access_key=8c9f5046580343cae7ff4a689594fd72' +
        '&query=' + zip)
  .then(response => {
    return response.json()
  })
  .then((data) => {
    data.forEach(obj => {
      if (obj.type == 'postalcode') {
        map.jumpTo({ 'center': [obj.longitude, obj.latitude]});
      }
      console.log(obj);
    })
  })
  .catch((err) => {
    console.log('There was an error retrieving JSON')
  })

fetch('/api/location/' + zip + '/' + distance)
    .then((response) => {
  return response.json()
  })
  .then((data) => {
    data.forEach(obj => {
      var innerObj = {
        type: 'Feature',
        geometry: {
          type: 'Point',
          coordinates: [obj.longitude, obj.latitude]
        },
        properties: {
          name: obj.name,
          address: obj.address,
          address2: obj.address2,
          city: obj.city,
          state: obj.state,
          zip: obj.zip,
          county: obj.county,
          phone: obj.phone,
          link: obj.link
        }
      }
      geojson.features.push(innerObj);
        });
  })
  .catch((err) => {
  console.log('There was an error retrieving JSON')
  })
}

function resetGeoJSON() {
  geojson = {
              type: 'FeatureCollection',
              features: []
            };
  return;
}




