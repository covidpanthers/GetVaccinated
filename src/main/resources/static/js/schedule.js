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
 
map.addControl(geocoder);