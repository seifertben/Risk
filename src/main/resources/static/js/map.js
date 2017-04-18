const EUS = [38.392017, -85.047551];
const WUS =  [41.187449, -114.449540];
const QUEBEC =  [50.206416, -75.586515];
const ONTARIO = [50.899997,  -87.521976];
const ALBERTA =   [55.279171,  -113.971396];
const NWTERRITORIES = [65.199589,   -119.253051];
const ALASKA =  [65.903817,   -154.899950];
const  GREENLAND = [74.663742,  -41.234447]; 
const CA =  [22.525567, -102.054754];
const VZ = [ 1.683763, -73.050849];
const PERU = [-15.185794,  -69.359442];
const BRAZIL =[-11.423151,-51.781319];
const ARGENTINA  = [-32.756957,  -65.492254];
const ICELAND = [64.821171,  -18.807248];
const GB =  [54.047766,  -1.398164];
const WEU = [ 43.295002,  -0.071574];
const NEU = [53.111401, 15.536295];
const SEU = [44.646422, 21.718624];
const SCANDINAVIA = [ 62.869291, 14.505908];
const RUSSIA = [56.551044, 39.372611];
const NAF = [21.054596, 0.659746];
const EGYPT = [26.520551, 23.687088];
const CAF = [ -0.075992, 19.292558];
const EAF = [ -0.954854, 36.519119];
const SAF = [ -17.883791, 22.456620];
const MADAGASCAR = [-19.073939, 46.428030];
const WAU = [-24.755328, 123.346503];
const EAU = [ -25.384764, 139.468350];
const INDONESIA = [ 1.954392, 113.200858];
const NEWGUINEA = [ -4.849519, 140.997146];
const ME = [29.866106, 41.663581];
const INDIA = [ 21.539913, 77.344727];
const SEASIA = [ 17.900297, 101.426756];
const CHINA = [36.897568, 103.375284];
const MONGOLIA = [49.846997, 124.554928];
const JAPAN = [ 37.222275,139.220748];
const KAMCHATKA = [66.569164, 153.670575];
const YAKUTSK = [ 67.092513, 131.116421];
const IRKUSTK = [58.664724, 127.776577];
const SIBERIA = [67.160839,103.342985];
const URAL = [66.666218,67.958187];
const AFGHANISTAN = [37.768380, 66.896086];
let EUSDATA = {
      "latitude": EUS[0], 
      "longitude": EUS[1],
      // "svgPath": icon,
      "type": "circle",
     "color": "#000000",
      "scale": 0.5,
      "label": "E. US",
      "labelShiftY": 2,
      "zoomLevel": 5,
      "title": "Eastern United States",
        "id": "Eastern United States",
      "description": "Occupied by Player 1. Soldiers: 5"
    };
let WUSDATA = {
      "latitude": WUS[0],
      "longitude":  WUS[1],
      "type": "circle" ,
     "color": "#000000",
      "scale": 0.5,
      "label": "W. US",
      "labelShiftY": 2,
      "zoomLevel": 5,
      "title": "Western United States",
        "id": "Western United States",
      "description": "Occupied by Player 1. Soldiers: 5"
    };
let QUEBECDATA =  {
      "latitude": QUEBEC[0],
      "longitude":   QUEBEC[1],
      "type": "circle" ,
     "color": "#000000",
      "scale": 0.5,
      "label": "Quebec",
      "labelShiftY": 2,
      "zoomLevel": 5,
      "title": "Quebec",
        "id": "Quebec",
      "description": "Occupied by Player 1. Soldiers: 5"
    };
let ONTARIODATA =  {
      "latitude": ONTARIO[0], 
      "longitude":   ONTARIO[1],
      "type": "circle" ,
     "color": "#000000",
      "scale": 0.5,
      "label": "Ontario",
      "labelShiftY": 2,
      "zoomLevel": 5,
      "title": "Ontario",
      "id": "Northwest Territories",
      "description": "Occupied by Player 1. Soldiers: 5"
    };
let ALBERTADATA =  {
      "latitude": ALBERTA[0], 
      "longitude":  ALBERTA[1] ,
      "type": "circle" ,
     "color": "#000000",
      "scale": 0.5,
      "label": "Alberta",
      "labelShiftY": 2,
      "zoomLevel": 5,
      "title": "Alberta",
      "id": "Northwest Territories",
      "description": "Occupied by Player 1. Soldiers: 5"
    };
let NWTERRITORIESDATA =  {
      "latitude": NWTERRITORIES[0],
      "longitude":   NWTERRITORIES[1],
      "type": "circle" ,
     "color": "#000000",
      "scale": 0.5,
      "label": "NW Territories",
      "labelShiftY": 2,
      "zoomLevel": 5,
      "title": "Northwest Territories",
      "id": "Northwest Territories",
      "description": "Occupied by Player 1. Soldiers: 5"
    };
let ALASKADATA =  {
      "latitude": ALASKA[0],
      "longitude":   ALASKA[1],
      "type": "circle" ,
     "color": "#000000",
      "scale": 0.5,
      "label": "Alaska",
      "labelShiftY": 2,
      "zoomLevel": 5,
      "title": "Alaska",
      "id": "Alaska",
      "description": "Occupied by Player 1. Soldiers: 5"
    };
let GREENLANDDATA =   {
      "latitude": GREENLAND[0],
      "longitude":    GREENLAND[1],
      "type": "circle" ,
     "color": "#000000",
      "scale": 0.5,
      "label": "Greenland",
      "labelShiftY": 2,
      "zoomLevel": 5,
      "title": "Greenland",
       "id": "Greenland",
      "description": "Occupied by Player 1. Soldiers: 5"
    };
let CADATA =  {
      "latitude": CA[0],
      "longitude":     CA[1],
      "type": "circle" ,
     "color": "#000000",
      "scale": 0.5,
      "label": "C. America",
      "labelShiftY": 2,
      "zoomLevel": 5,
      "title": "Central America",
      "id": "Central America",
      "description": "Occupied by Player 1. Soldiers: 5"
    };

let VZDATA = {
      "latitude": VZ[0], 
      "longitude":    VZ[1],
      "type": "circle" ,
     "color": "#000000",
      "scale": 0.5,
      "label": "Venezuela",
      "labelShiftY": 2,
      "zoomLevel": 5,
      "title": "Venezuela",
      "id": "Venezuela",
      "description": "Occupied by Player 1. Soldiers: 5"
    };
let PERUDATA= {
      "latitude": PERU[0],
      "longitude":   PERU[1],
      "type": "circle" ,
     "color": "#000000",
      "scale": 0.5,
      "label": "Peru",
      "labelShiftY": 2,
      "zoomLevel": 5,
      "title": "Peru",
      "id": "Peru",
      "description": "Occupied by Player 1. Soldiers: 5"
    };
    let BRAZILDATA =  {
      "latitude": BRAZIL[0],
      "longitude":    BRAZIL[1],
      "type": "circle" ,
     "color": "#000000",
      "scale": 0.5,
      "label": "Brazil",
      "labelShiftY": 2,
      "zoomLevel": 5,
      "title": "Brazil",
      "id": "Brazil",
      "description": "Occupied by Player 1. Soldiers: 5"
    };
    let ARGENTINADATA =   {
      "latitude": ARGENTINA[0],
      "longitude":  ARGENTINA[1],
      "type": "circle" ,
     "color": "#000000",
      "scale": 0.5,
      "label": "Argentina",
      "labelShiftY": 2,
      "zoomLevel": 5,
      "title": "Argentina",
      "description": "Occupied by Player 1. Soldiers: 5",
      "id": "Argentina"
    };
let ICELANDDATA = {
      "latitude": ICELAND[0], 
      "longitude": ICELAND[1],
      "type": "circle" ,
     "color": "#000000",
      "scale": 0.5,
      "label": "Iceland",
      "labelShiftY": 2,
      "zoomLevel": 5,
      "title": "Iceland",
      "id": "Iceland",
      "description": "Occupied by Player 1. Soldiers: 5"
    };
let GBDATA =  {
      "latitude":   GB[0],
      "longitude":  GB[1],
      "type": "circle" ,
     "color": "#000000",
      "scale": 0.5,
      "label": "GB",
      "labelShiftY": 2,
      "zoomLevel": 5,
      "title": "Great Britain",
        "id": "Great Britain",
      "description": "Occupied by Player 1. Soldiers: 5"
    };
let WEUDATA =  {
      "latitude":    WEU[0],
      "longitude":  WEU[1],
      "type": "circle" ,
     "color": "#000000",
      "scale": 0.5,
      "label": "W EU",
      "labelShiftY": 2,
      "zoomLevel": 5,
      "title": "Western Europe",
      "id": "Western Europe",
      "description": "Occupied by Player 1. Soldiers: 5"
    };
let NEUDATA = {
      "latitude":    NEU[0],
      "longitude":   NEU[1],
      "type": "circle" ,
     "color": "#000000",
      "scale": 0.5,
      "label": "N EU",
      "labelShiftY": 2,
      "zoomLevel": 5,
      "title": "Northern Europe",
       "id": "Northern Europe",
      "description": "Occupied by Player 1. Soldiers: 5"
    };
let SEUDATA =    {
      "latitude":    SEU[0],
      "longitude":   SEU[1],
      "type": "circle" ,
     "color": "#000000",
      "scale": 0.5,
      "label": "S EU",
      "labelShiftY": 2,
      "zoomLevel": 5,
      "title": "Southern Europe",
       "id": "Southern Europe",
      "description": "Occupied by Player 1. Soldiers: 5"
    };
    let SCANDINAVIADATA =   {
      "latitude":    SCANDINAVIA[0], 
      "longitude":   SCANDINAVIA[1],
      "type": "circle" ,
     "color": "#000000",
      "scale": 0.5,
      "label": "Scandinavia",
      "labelShiftY": 2,
      "zoomLevel": 5,
      "title": "Scandinavia",
       "id": "Scandinavia",
      "description": "Occupied by Player 1. Soldiers: 5"
    };
let RUSSIADATA =  {
      "latitude":    RUSSIA[0],
      "longitude":   RUSSIA[1],
      "type": "circle" ,
     "color": "#000000",
      "scale": 0.5,
      "label": "Russia",
      "labelShiftY": 2,
      "zoomLevel": 5,
      "title": "Russia",
       "id": "Russia",
      "description": "Occupied by Player 1. Soldiers: 5"
    };
let NAFDATA =  {
      "latitude":    NAF[0],
      "longitude":   NAF[1],
      "type": "circle" ,
     "color": "#000000",
      "scale": 0.5,
      "label": "N. AF",
      "labelShiftY": 2,
      "zoomLevel": 5,
      "title": "Northern Africa",
       "id": "Northern Africa",
      "description": "Occupied by Player 1. Soldiers: 5"
    };
let EGYPTDATA =   {
      "latitude":   EGYPT[0],
      "longitude":   EGYPT[1],
      "type": "circle" ,
     "color": "#000000",
      "scale": 0.5,
      "label": "Egypt",
      "labelShiftY": 2,
      "zoomLevel": 5,
      "title": "Egypt",
       "id": "Egypt",
      "description": "Occupied by Player 1. Soldiers: 5"
    };
let CAFDATA =  {
      "latitude":     CAF[0],
      "longitude":    CAF[1],
      "type": "circle" ,
     "color": "#000000",
      "scale": 0.5,
      "label": "C. AF",
      "labelShiftY": 2,
      "zoomLevel": 5,
      "title": "Central Africa",
      "id": "Central Africa",
      "description": "Occupied by Player 1. Soldiers: 5"
    };
  let EAFDATA = {
      "latitude":     EAF[0], 
      "longitude":    EAF[1],
      "type": "circle" ,
     "color": "#000000",
      "scale": 0.5,
      "label": "E. AF",
      "labelShiftY": 2,
      "zoomLevel": 5,
      "title": "Eastern Africa",
       "id": "Eastern Africa",
      "description": "Occupied by Player 1. Soldiers: 5"
  };
  let SAFDATA =   {
      "latitude":    SAF[0],
      "longitude":    SAF[1],
      "type": "circle" ,
     "color": "#000000",
      "scale": 0.5,
      "label": "S. AF",
      "labelShiftY": 2,
      "zoomLevel": 5,
      "title": "Southern Africa",
       "id": "Southern Africa",
      "description": "Occupied by Player 1. Soldiers: 5"
    };
let MADAGASCARDATA =   {
      "latitude":   MADAGASCAR[0],
      "longitude":     MADAGASCAR[1],
      "type": "circle" ,
     "color": "#000000",
      "scale": 0.5,
      "label": "Madagascar",
      "labelShiftY": 2,
      "zoomLevel": 5,
      "title": "Madagascar",
       "id": "Madagascar",
      "description": "Occupied by Player 1. Soldiers: 5"
    };
let WAUDATA =  {
      "latitude":   WAU[0],
      "longitude":     WAU[1],
      "type": "circle" ,
     "color": "#000000",
      "scale": 0.5,
      "label": "W. AU",
      "labelShiftY": 2,
      "zoomLevel": 5,
      "title": "Western Australia",
       "id": "Western Australia",
      "description": "Occupied by Player 1. Soldiers: 5"
    };
let EAUDATA = {
   
      "latitude":   EAU[0], 
      "longitude":     EAU[1],
      "type": "circle" ,
     "color": "#000000",
      "scale": 0.5,
      "label": "E. AU",
      "labelShiftY": 2,
      "zoomLevel": 5,
      "title": "Eastern Australia",
       "id": "Eastern Australia",
      "description": "Occupied by Player 1. Soldiers: 5"
    
};
let INDONESIADATA =  {
      "latitude":  INDONESIA[0], 
      "longitude":    INDONESIA[1],
      "type": "circle" ,
     "color": "#000000",
      "scale": 0.5,
      "label": "Indonesia",
      "labelShiftY": 2,
      "zoomLevel": 5,
      "title": "Indonesia",
       "id": "Indonesia",
      "description": "Occupied by Player 1. Soldiers: 5"
    };
let NEWGUINEADATA =   {
      "latitude":  NEWGUINEA[0],
      "longitude":   NEWGUINEA[1],
      "type": "circle" ,
     "color": "#000000",
      "scale": 0.5,
      "label": "New Guinea",
      "labelShiftY": 2,
      "zoomLevel": 5,
      "title": "New Guinea",
        "id": "New Guinea",
      "description": "Occupied by Player 1. Soldiers: 5"
    };
let MEDATA =  {
      "latitude":  ME[0], 
      "longitude":  ME[1],
      "type": "circle" ,
     "color": "#000000",
      "scale": 0.5,
      "label": "Middle East",
      "labelShiftY": 2,
      "zoomLevel": 5,
      "title": "Middle East",
        "id": "Middle East",
      "description": "Occupied by Player 1. Soldiers: 5"
    };
let INDIADATA =  {
      "latitude":  INDIA[0],
      "longitude":  INDIA[1],
      "type": "circle" ,
     "color": "#000000",
      "scale": 0.5,
      "label": "India",
      "labelShiftY": 2,
      "zoomLevel": 5,
      "title": "India",
       "id": "India",
      "description": "Occupied by Player 1. Soldiers: 5"
    };
let SEASIADATA =   {
      "latitude": SEASIA[0], 
      "longitude":  SEASIA[1],
      "type": "circle" ,
     "color": "#000000",
      "scale": 0.5,
      "label": "SE Asia",
      "labelShiftY": 2,
      "zoomLevel": 5,
      "title": "SouthEast Asia",
        "id": "SouthEast Asia",
      "description": "Occupied by Player 1. Soldiers: 5"
    };
let CHINADATA =   {
      "latitude": CHINA[0],
      "longitude":  CHINA[1],
      "type": "circle" ,
     "color": "#000000",
      "scale": 0.5,
      "label": "China",
      "labelShiftY": 2,
      "zoomLevel": 5,
      "title": "China",
      "id": "China",
      "description": "Occupied by Player 1. Soldiers: 5"
    };
let MONGOLIADATA =   {
      "latitude": MONGOLIA[0],
      "longitude":  MONGOLIA[1],
      "type": "circle" ,
     "color": "#000000",
      "scale": 0.5,
      "label": "Mongolia",
      "labelShiftY": 2,
      "zoomLevel": 5,
      "title": "Mongolia",
      "id": "Mongolia",
      "description": "Occupied by Player 1. Soldiers: 5"
    };
let JAPANDATA =    {
      "latitude": JAPAN[0],
      "longitude":  JAPAN[1],
      "type": "circle" ,
     "color": "#000000",
      "scale": 0.5,
      "label": "Japan",
      "labelShiftY": 2,
      "zoomLevel": 5,
      "title": "Japan",
        "id": "Japan",
      "description": "Occupied by Player 1. Soldiers: 5"
    };
let KAMCHATKADATA =  {
      "latitude": KAMCHATKA[0],
      "longitude":   KAMCHATKA[1],
      "type": "circle" ,
     "color": "#000000",
      "scale": 0.5,
      "label": "Kamchatka",
      "labelShiftY": 2,
      "zoomLevel": 5,
      "title": "Kamchatka",
       "id": "Kamchatka",
      "description": "Occupied by Player 1. Soldiers: 5"
    };
let YAKUTSKDATA = {
     "latitude": YAKUTSK[0],
      "longitude":  YAKUTSK[1],
      "type": "circle" ,
     "color": "#000000",
      "scale": 0.5,
      "label": "Yakutsk",
      "labelShiftY": 2,
      "zoomLevel": 5,
      "title": "Yakutsk",
      "id": "Yakutsk",
      "description": "Occupied by Player 1. Soldiers: 5"
};
let IRKUSTKDATA =  {
      "latitude": IRKUSTK[0], 
      "longitude":  IRKUSTK[1],
      "type": "circle" ,
     "color": "#000000",
      "scale": 0.5,
      "label": "Irkustk",
      "labelShiftY": 2,
      "zoomLevel": 5,
      "title": "Irkustk",
      "id": "Irkustk",
      "description": "Occupied by Player 1. Soldiers: 5"
    };
let SIBERIADATA =   {
      "latitude": SIBERIA[0],
      "longitude": SIBERIA[1],
      "type": "circle" ,
     "color": "#000000",
      "scale": 0.5,
      "label": "Siberia",
      "labelShiftY": 2,
      "zoomLevel": 5,
      "title": "Siberia",
       "id": "Siberia",
      "description": "Occupied by Player 1. Soldiers: 5"
    };
let URALDATA =   {
      "latitude": URAL[0],
      "longitude":  URAL[1],
      "type": "circle" ,
     "color": "#000000",
      "scale": 0.5,
      "label": "Ural",
      "labelShiftY": 2,
      "zoomLevel": 5,
      "title": "Ural",
       "id": "Ural",
      "description": "Occupied by Player 1. Soldiers: 5"
    };
let AFGHANISTANDATA =    {
      "latitude": AFGHANISTAN[0],
      "longitude": AFGHANISTAN[1],
      "type": "circle" ,
     "color": "#000000",
      "scale": 0.5,
      "label": "Afghanistan",
      "labelShiftY": 2,
      "zoomLevel": 5,
      "title": "Afghanistan",
       "id": "Afghanistan",
      "description": "Occupied by Player 1. Soldiers: 5"
    }
let EUS_WUS = {
      "latitudes": [ EUS[0], WUS[0]],
      "longitudes": [ EUS[1], WUS[1] ],
      "color": "black"
    };
let EUS_QUEBEC = { "latitudes": [ EUS[0], QUEBEC[0]],
      "longitudes": [ EUS[1], QUEBEC[1] ],
      "color": "black"
    };
let EUS_CA = {
      "latitudes": [ EUS[0], CA[0] ],
      "longitudes": [ EUS[1], CA[1]],
        "color": "black"
    };
let EUS_ONTARIO =  {
      "latitudes": [ EUS[0], ONTARIO[0] ],
      "longitudes": [ EUS[1], ONTARIO[1] ],
        "color": "black"
    };
let WUS_CA = {
      "latitudes": [ WUS[0], CA[0]],
      "longitudes": [ WUS[1], CA[1]],
        "color": "black"

    };
let WUS_ALBERTA = {
      "latitudes": [ WUS[0], ALBERTA[0] ],
      "longitudes": [ WUS[1], ALBERTA[1]],
        "color": "black"
    };
let WUS_ONTARIO = {
      "latitudes": [ WUS[0], ONTARIO[0] ],
      "longitudes": [ WUS[1], ONTARIO[1]],
        "color": "black"
    };
let ALBERTA_ONTARIO = {
      "latitudes": [ ALBERTA[0], ONTARIO[0]],
      "longitudes": [ ALBERTA[1], ONTARIO[1] ],
        "color": "black"
    };
let ALBERTA_NWTERRITORIES = {
      "latitudes": [ ALBERTA[0], NWTERRITORIES[0] ],
      "longitudes": [ ALBERTA[1], NWTERRITORIES[1] ],
        "color": "black"
    };
let ALBERTA_ALASKA = {
      "latitudes": [ ALBERTA[0], ALASKA[0]],
      "longitudes": [ ALBERTA[1], ALASKA[1]],
        "color": "black"
    };
let ONTARIO_QUEBEC = {
      "latitudes": [ ONTARIO[0], QUEBEC[0]],
      "longitudes": [ ONTARIO[1], QUEBEC[1] ],
        "color": "black"
    };
let ONTARIO_NWTERRITORIES =  {
      "latitudes": [ ONTARIO[0], NWTERRITORIES[0]],
      "longitudes": [ ONTARIO[1], NWTERRITORIES[1]],
        "color": "black"
    };
let ONTARIO_GREENLAND = {
      "latitudes": [ ONTARIO[0], GREENLAND[0]],
      "longitudes": [ ONTARIO[1], GREENLAND[1] ],
        "color": "black"
    } ;
let NWTERRITORIES_ALASKA = {
      "latitudes": [ ALASKA[0], NWTERRITORIES[0]],
      "longitudes": [ALASKA[1], NWTERRITORIES[1]],
        "color": "black"
    };
let NWTERRITORIES_GREENLAND =   {
      "latitudes": [ GREENLAND[0], NWTERRITORIES[0]],
      "longitudes": [GREENLAND[1], NWTERRITORIES[1]],
        "color": "black"
    }
let GREENLAND_ICELAND =  {
      "latitudes": [ GREENLAND[0], ICELAND[0]],
      "longitudes": [GREENLAND[1], ICELAND[1]],
        "color": "black"
    };
let GREENLAND_QUEBEC =  {
      "latitudes": [ GREENLAND[0],  QUEBEC[0]],
      "longitudes": [GREENLAND[1], QUEBEC[1]],
        "color": "black"
    };
let ICELAND_GB =   {
      "latitudes": [ ICELAND[0],  GB[0]],
      "longitudes": [ICELAND[1], GB[1]],
        "color": "black"
    };
let SCANDINAVIA_ICELAND =   {
      "latitudes": [ ICELAND[0],  SCANDINAVIA[0]],
      "longitudes": [ICELAND[1], SCANDINAVIA[1]],
        "color": "black"
    };
let SCANDINAVIA_GB =  {
      "latitudes": [SCANDINAVIA[0],  GB[0]],
      "longitudes": [SCANDINAVIA[1], GB[1]],
        "color": "black"
    };
let NEU_GB =   {
      "latitudes": [NEU[0],  GB[0]],
      "longitudes": [NEU[1], GB[1]],
        "color": "black"
    };
let WEU_GB =  {
      "latitudes": [WEU[0],  GB[0]],
      "longitudes": [WEU[1], GB[1]],
        "color": "black"
    };
let WEU_SEU =  {
      "latitudes": [WEU[0],  SEU[0]],
      "longitudes": [WEU[1], SEU[1]],
        "color": "black"
    };
let WEU_NAF =  {
      "latitudes": [WEU[0],  NAF[0]],
      "longitudes": [WEU[1], NAF[1]],
        "color": "black"
    };
let WEU_NEU =  {
      "latitudes": [WEU[0],  NEU[0]],
      "longitudes": [WEU[1], NEU[1]],
        "color": "black"
    };
let NEU_RUSSIA =  {
      "latitudes": [NEU[0],  RUSSIA[0]],
      "longitudes": [NEU[1], RUSSIA[1]],
        "color": "black"
    };
let NEU_SEU =  {
      "latitudes": [NEU[0],  SEU[0]],
      "longitudes": [NEU[1], SEU[1]],
        "color": "black"
    };
let SCANDINAVIA_NEU = {
      "latitudes": [NEU[0],  SCANDINAVIA[0]],
      "longitudes": [NEU[1], SCANDINAVIA[1]],
        "color": "black"
    }
let SCANDINAVIA_RUSSIA = {
      "latitudes": [RUSSIA[0],  SCANDINAVIA[0]],
      "longitudes": [RUSSIA[1], SCANDINAVIA[1]],
          "color": "black"
    };
let EGYPT_SEU =     {
      "latitudes": [EGYPT[0],  SEU[0]],
      "longitudes": [EGYPT[1], SEU[1]],
        "color": "black"
    };
let MEU_SEU =  {
      "latitudes": [ME[0],  SEU[0]],
      "longitudes": [ME[1], SEU[1]],
        "color": "black"
    };
let NAF_SEU =  {
      "latitudes": [NAF[0],  SEU[0]],
      "longitudes": [NAF[1], SEU[1]],
        "color": "black"
    };
let NAF_EGYPT =  {
      "latitudes": [NAF[0],  EGYPT[0]],
      "longitudes": [NAF[1], EGYPT[1]],
        "color": "black"
    };
let NAF_CAF =  {
      "latitudes": [NAF[0],  CAF[0]],
      "longitudes": [NAF[1], CAF[1]],
        "color": "black"
    };
let NAF_EAF = {
      "latitudes": [NAF[0],  EAF[0]],
      "longitudes": [NAF[1], EAF[1]],
        "color": "black"
    };
let NAF_BRAZIL =    {
      "latitudes": [NAF[0],  BRAZIL[0]],
      "longitudes": [NAF[1], BRAZIL[1]],
        "color": "black"
    };
let EAF_CAF =       {
      "latitudes": [EAF[0],  CAF[0]],
      "longitudes": [EAF[1], CAF[1]],
        "color": "black"
    };
let SAF_CAF =   {
      "latitudes": [SAF[0],  CAF[0]],
      "longitudes": [SAF[1], CAF[1]],
        "color": "black"
    };
let SAF_EAF =  {
      "latitudes": [SAF[0],  EAF[0]],
      "longitudes": [SAF[1], EAF[1]],
        "color": "black"
    };
let MADAGASCAR_EAF =   {
      "latitudes": [EAF[0],  MADAGASCAR[0]],
      "longitudes": [EAF[1], MADAGASCAR[1]],
        "color": "black"
    };
let MADAGASCAR_SAF =  {
      "latitudes": [SAF[0],  MADAGASCAR[0]],
      "longitudes": [SAF[1], MADAGASCAR[1]],
        "color": "black"
    };
let EGYPT_EAF =  {
      "latitudes": [EGYPT[0],  EAF[0]],
      "longitudes": [EGYPT[1], EAF[1]],
        "color": "black"
    };
let EGYPT_ME =  {
      "latitudes": [EGYPT[0],  ME[0]],
      "longitudes": [EGYPT[1], ME[1]],
        "color": "black"
    };
let BRAZIL_PERU =   {
      "latitudes": [BRAZIL[0],  PERU[0]],
      "longitudes": [BRAZIL[1], PERU[1]],
        "color": "black"
    };
let ARGENTINA_BRAZIL =   {
      "latitudes": [BRAZIL[0],  ARGENTINA[0]],
      "longitudes": [BRAZIL[1], ARGENTINA[1]],
        "color": "black"
    };
let BRAZIL_VZ =   {
      "latitudes": [BRAZIL[0],  VZ[0]],
      "longitudes": [BRAZIL[1], VZ[1]],
        "color": "black"
    };
  let VZ_CA =  {
      "latitudes": [VZ[0],  CA[0]],
      "longitudes": [VZ[1], CA[1]],
        "color": "black"
    };
let VZ_PERU =  {
      "latitudes": [VZ[0],  PERU[0]],
      "longitudes": [VZ[1], PERU[1]],
        "color": "black"
    };
let ARGENTINA_PERU =  {
      "latitudes": [PERU[0],  PERU[0]],
      "longitudes": [ARGENTINA[1], ARGENTINA[1]],
        "color": "black"
    };
let ME_RUSSIA = {
      "latitudes": [ME[0],  RUSSIA[0]],
      "longitudes": [ME[1], RUSSIA[1]],
        "color": "black"
    };
let AFGHANISTAN_ME = 
    {
      "latitudes": [ME[0],  AFGHANISTAN[0]],
      "longitudes": [ME[1], AFGHANISTAN[1]],
        "color": "black"
    };
let ME_INDIA =  {
      "latitudes": [ME[0],  INDIA[0]],
      "longitudes": [ME[1], INDIA[1]],
        "color": "black"
    };
let URAL_RUSSIA =  {
      "latitudes": [URAL[0],  RUSSIA[0]],
      "longitudes": [URAL[1], RUSSIA[1]],
        "color": "black"
    };
let AFGHANISTAN_RUSSIA =   {
      "latitudes": [AFGHANISTAN[0],  RUSSIA[0]],
      "longitudes": [AFGHANISTAN[1], RUSSIA[1]],
        "color": "black"
    };
let AFGHANISTAN_URAL =  {
      "latitudes": [URAL[0],  AFGHANISTAN[0]],
      "longitudes": [URAL[1], AFGHANISTAN[1]],
        "color": "black"
    };
let URAL_CHINA =  {
      "latitudes": [URAL[0],  CHINA[0]],
      "longitudes": [URAL[1], CHINA[1]],
        "color": "black"
    };
let URAL_SIBERIA =  {
      "latitudes": [URAL[0],  SIBERIA[0]],
      "longitudes": [URAL[1], SIBERIA[1]],
        "color": "black"
    };
let AFGHANISTAN_CHINA = {
      "latitudes": [AFGHANISTAN[0],  CHINA[0]],
      "longitudes": [AFGHANISTAN[1], CHINA[1]],
        "color": "black"
    };
let AFGHANISTAN_INDIA =   {
      "latitudes": [AFGHANISTAN[0],  INDIA[0]],
      "longitudes": [AFGHANISTAN[1], INDIA[1]],
        "color": "black"
    };
let CHINA_INDIA =  {
      "latitudes": [CHINA[0],  INDIA[0]],
      "longitudes": [CHINA[1], INDIA[1]],
        "color": "black"
    };
let SEASIA_INDIA =   {
      "latitudes": [SEASIA[0],  INDIA[0]],
      "longitudes": [SEASIA[1], INDIA[1]],
        "color": "black"
    };
let CHINA_MONGOLIA =  {
      "latitudes": [CHINA[0],  MONGOLIA[0]],
      "longitudes": [CHINA[1], MONGOLIA[1]],
        "color": "black"
    };
let CHINA_SEASIA =  {
      "latitudes": [CHINA[0],  SEASIA[0]],
      "longitudes": [CHINA[1], SEASIA[1]],
        "color": "black"
    };
let IRKUSTK_MONGOLIA = {
      "latitudes": [IRKUSTK[0],  MONGOLIA[0]],
      "longitudes": [IRKUSTK[1], MONGOLIA[1]],
      "color": "black"
    };
let JAPAN_MONGOLIA =   {
      "latitudes": [JAPAN[0],  MONGOLIA[0]],
      "longitudes": [JAPAN[1], MONGOLIA[1]], 
       "color": "black"
    };
let KAMCHATKA_MONGOLIA =  {
      "latitudes": [KAMCHATKA[0],  MONGOLIA[0]],
      "longitudes": [KAMCHATKA[1], MONGOLIA[1]],
       "color": "black"
    };
let YAKUTSK_IRKUSTK =  {
      "latitudes": [YAKUTSK[0],  IRKUSTK[0]],
      "longitudes": [YAKUTSK[1], IRKUSTK[1]],
       "color": "black"
    };
let IRKUSTK_SIBERIA =  {
      "latitudes": [SIBERIA[0],  IRKUSTK[0]],
      "longitudes": [SIBERIA[1], IRKUSTK[1]],
       "color": "black"
    };
let KAMCHATKA_JAPAN =  {
      "latitudes": [JAPAN[0],  KAMCHATKA[0]],
      "longitudes": [JAPAN[1], KAMCHATKA[1]],
       "color": "black"
    };
let IRKUSTK_KAMCHATKRA = {
      "latitudes": [KAMCHATKA[0],  IRKUSTK[0]],
      "longitudes": [KAMCHATKA[1], IRKUSTK[1]],
       "color": "black"
    };
let YAKUTSK_SIBERIA ={
      "latitudes": [SIBERIA[0],  YAKUTSK[0]],
      "longitudes": [SIBERIA[1], YAKUTSK[1]],
       "color": "black"
    };
let KAMCHATKA_YAKUTSK = {
      "latitudes": [KAMCHATKA[0],  YAKUTSK[0]],
      "longitudes": [KAMCHATKA[1], YAKUTSK[1]],
       "color": "black"
    };
let SIBERIA_CHINA =  {
      "latitudes": [SIBERIA[0],  CHINA[0]],
      "longitudes": [SIBERIA[1], CHINA[1]],
       "color": "black"
    };
let SIBERIA_MONGOLIA = {
      "latitudes": [SIBERIA[0],  MONGOLIA[0]],
      "longitudes": [SIBERIA[1], MONGOLIA[1]],
       "color": "black"
    };
let INDONESIA_SEASIA =   {
      "latitudes": [SEASIA[0],  INDONESIA[0]],
      "longitudes": [SEASIA[1], INDONESIA[1]],
       "color": "black"
    };
let NEWGUINEA_INDONESIA =  {
      "latitudes": [NEWGUINEA[0],  INDONESIA[0]],
      "longitudes": [NEWGUINEA[1], INDONESIA[1]], 
       "color": "black"
    };
  let NEWGUINEA_WAU =  {
      "latitudes": [NEWGUINEA[0],  WAU[0]],
      "longitudes": [NEWGUINEA[1], WAU[1]],
       "color": "black"
    };
let NEWGUINEA_EAU =  {
      "latitudes": [NEWGUINEA[0],  EAU[0]],
      "longitudes": [NEWGUINEA[1], EAU[1]],
       "color": "black"
    };
let WAU_EAU = {
      "latitudes": [WAU[0],  EAU[0]],
      "longitudes": [WAU[1], EAU[1]],
       "color": "black"
    };
let INDONESIA_WAU =  {
      "latitudes": [WAU[0],  INDONESIA[0]],
      "longitudes": [WAU[1], INDONESIA[1]],
       "color": "black"
    };
let lines  =  [EUS_WUS, EUS_QUEBEC, EUS_CA,EUS_ONTARIO,WUS_CA, WUS_ALBERTA, WUS_ONTARIO, ALBERTA_ONTARIO, ALBERTA_NWTERRITORIES, ALBERTA_ALASKA, ONTARIO_QUEBEC,
ONTARIO_NWTERRITORIES, ONTARIO_GREENLAND, NWTERRITORIES_ALASKA,NWTERRITORIES_GREENLAND, GREENLAND_ICELAND, GREENLAND_QUEBEC, ICELAND_GB, SCANDINAVIA_ICELAND, 
SCANDINAVIA_GB, NEU_GB, WEU_GB,WEU_SEU, WEU_NAF,WEU_NEU, NEU_RUSSIA,NEU_SEU,SCANDINAVIA_NEU,SCANDINAVIA_RUSSIA,EGYPT_SEU,MEU_SEU,NAF_SEU,NAF_EGYPT,NAF_CAF,NAF_EAF,
NAF_BRAZIL,EAF_CAF, SAF_CAF,SAF_EAF,MADAGASCAR_EAF,MADAGASCAR_SAF,EGYPT_EAF,EGYPT_ME,BRAZIL_PERU,ARGENTINA_BRAZIL, BRAZIL_VZ,VZ_CA,VZ_PERU,ARGENTINA_PERU,ME_RUSSIA,
AFGHANISTAN_ME,ME_INDIA,URAL_RUSSIA,AFGHANISTAN_RUSSIA,AFGHANISTAN_URAL,URAL_CHINA,URAL_SIBERIA,AFGHANISTAN_CHINA,AFGHANISTAN_INDIA,CHINA_INDIA,SEASIA_INDIA,
CHINA_MONGOLIA,CHINA_SEASIA,IRKUSTK_MONGOLIA,JAPAN_MONGOLIA,KAMCHATKA_MONGOLIA,YAKUTSK_IRKUSTK,IRKUSTK_SIBERIA,KAMCHATKA_JAPAN,IRKUSTK_KAMCHATKRA, YAKUTSK_SIBERIA,
KAMCHATKA_YAKUTSK,SIBERIA_CHINA,SIBERIA_MONGOLIA,INDONESIA_SEASIA, NEWGUINEA_INDONESIA,NEWGUINEA_WAU,NEWGUINEA_EAU,WAU_EAU,INDONESIA_WAU];
let game = [EUSDATA, WUSDATA, QUEBECDATA,ONTARIODATA,ALBERTADATA, NWTERRITORIESDATA,ALASKADATA,GREENLANDDATA,CADATA,VZDATA,PERUDATA,BRAZILDATA,ARGENTINADATA,ICELANDDATA,
    GBDATA,WEUDATA,NEUDATA, SEUDATA,SCANDINAVIADATA,RUSSIADATA,  NAFDATA, EGYPTDATA,CAFDATA,EAFDATA, SAFDATA,MADAGASCARDATA, WAUDATA,EAUDATA,INDONESIADATA, NEWGUINEADATA, 
  MEDATA,  INDIADATA,SEASIADATA, CHINADATA, MONGOLIADATA, JAPANDATA, KAMCHATKADATA, YAKUTSKDATA, IRKUSTKDATA, SIBERIADATA, URALDATA,AFGHANISTANDATA ];
AmCharts.makeChart( "mapdiv", {
  /**
   * this tells amCharts it's a map
   */
  "type": "map",
  "mouseWheelZoomEnabled": true,
  /**
   * create data provider object
   * map property is usually the same as the name of the map file.
   * getAreasFromMap indicates that amMap should read all the areas available
   * in the map data and treat them as they are included in your data provider.
   * in case you don't set it to true, all the areas except listed in data
   * provider will be treated as unlisted.
   */

  "dataProvider": {
    "map": "continentsLow",
     images:game,
      "lines": lines,
    "areas": [
      {
        "id": "europe",
        "color": "grey"
      },
      {
        "id": "asia",
        "color": "grey"
      },
      {
        "id": "africa",
        "color": "grey"
      },
      {
        "id": "south_america",
        "color": "grey"
      },
      {
        "id": "north_america",
        "color": "grey"
      },
      {
        "id": "australia",
        "color": "grey"
      }
    ]
  },

  /**
   * create areas settings
   * autoZoom set to true means that the map will zoom_in when clicked on the area
   * selectedColor indicates color of the clicked area.
   */
  "areasSettings": {
    "autoZoom": true,
    
  },
  

  /**
   * let's say we want a small map to be displayed, so let's create it
   */
  "largeMap": {}
} );
function changeTerritory() {
  
}