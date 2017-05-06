const EUS = [38.392017, -85.047551];
const WUS = [41.187449, -114.449540];
const QUEBEC = [50.206416, -75.586515];
const ONTARIO = [50.899997,  -87.521976];
const ALBERTA = [55.279171,  -113.971396];
const NWTERRITORIES = [65.199589,   -119.253051];
const ALASKA = [65.903817,   -154.899950];
const GREENLAND = [74.663742,  -41.234447]; 
const CA = [22.525567, -102.054754];
const VZ = [ 1.683763, -73.050849];
const PERU = [-15.185794,  -69.359442];
const BRAZIL = [-11.423151,-51.781319];
const ARGENTINA = [-32.756957,  -65.492254];
const ICELAND = [64.821171,  -18.807248];
const GB = [54.047766,  -1.398164];
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
let terrToTerrToLine = [];
let attackableLines = [];
let attackLine;


var targetSVG = "M9,0C4.029,0,0,4.029,0,9s4.029,9,9,9s9-4.029,9-9S13.971,0,9,0z M9,15.93 c-3.83,0-6.93-3.1-6.93-6.93S5.17,2.07,9,2.07s6.93,3.1,6.93,6.93S12.83,15.93,9,15.93 M12.5,9c0,1.933-1.567,3.5-3.5,3.5S5.5,10.933,5.5,9S7.067,5.5,9,5.5 S12.5,7.067,12.5,9z";
let idToData = {};
let EUSDATA = {
      "latitude": EUS[0], 
      "longitude": EUS[1],
      "selectable": true,
      "svgPath": targetSVG,
      "labelColor": "white",  
      "labelRollOverColor": "#000000",
      "scale": 1.5,
      "type": "circle",
      "bringForwardOnHover": true,
      "label": "E. US",
      "labelShiftY": 2,
      "name": "Eastern United States", 
      "title": "Eastern United States: Occupied by No One",
      "id": 3,
    };
idToData[3] = EUSDATA;
let WUSDATA = {
      "latitude": WUS[0],
      "longitude":  WUS[1],
      "svgPath": targetSVG,
      "selectable": true,
      "labelColor": "white",  
      "labelRollOverColor": "#000000",
      "scale": 1.5,
      "type": "circle",
      "bringForwardOnHover": true,
      "label": "W. US",
      "labelShiftY": 15,
      "labelShiftX": -50,
      "name": "Western United States",
      "title": "Western United States: Occupied by No One",
      "id": 4,
    };
idToData[4] = WUSDATA;
let QUEBECDATA =  {
      "latitude": QUEBEC[0],
      "longitude":   QUEBEC[1],
      "svgPath": targetSVG,
      "selectable": true,
      "labelColor": "white",  
      "labelRollOverColor": "#000000",
      "scale": 1.5,
      "type": "circle",
      "bringForwardOnHover": true,
      "label": "Quebec",
       "name": "Quebec",
      "labelShiftY": 2,
      "title": "Quebec: Occupied by No One",
      "id": 8,
    };
idToData[8] = QUEBECDATA;
let ONTARIODATA = {
      "latitude": ONTARIO[0], 
      "longitude":   ONTARIO[1],
      "svgPath": targetSVG,
      "selectable": true,
      "labelColor": "white",  
      "labelRollOverColor": "#000000",
      "scale": 1.5,
      "type": "circle",
      "bringForwardOnHover": true,
      "label": "Ontario",
      "name": "Ontario",
      "labelShiftY": -20,
      "labelShiftX": -30,
      "title": "Ontario: Occupied by No One",
      "id": 1,
    };
idToData[1] = ONTARIODATA;
let ALBERTADATA =  {
      "latitude": ALBERTA[0], 
      "longitude":  ALBERTA[1] ,
      "svgPath": targetSVG,
      "selectable": true,
      "labelColor": "white",
      "labelRollOverColor": "#000000",
      "scale": 1.5,
      "type": "circle",
      "bringForwardOnHover": true,
      "label": "Alberta",
       "name": "Alberta",
      "labelShiftY": 10,
      "labelShiftX": -60,
      "title": "Alberta: Occupied by No One",
      "id": 7,
    };
idToData[7] = ALBERTADATA;
let NWTERRITORIESDATA =  {
      "latitude": NWTERRITORIES[0],
      "longitude": NWTERRITORIES[1],
      "selectable": true,
      "svgPath": targetSVG,
      "labelColor": "white",  
      "labelRollOverColor": "#000000",
      "scale": 1.5,
      "type": "circle",
      "bringForwardOnHover": true,
      "label": "NW Territories",
      "labelShiftY": 2,
      "name": "Northwest Territories",
      "title": "Northwest Territories: Occupied by No One",
      "id": 6,
    };
idToData[6] = NWTERRITORIESDATA;
let ALASKADATA =  {
      "latitude": ALASKA[0],
      "longitude":   ALASKA[1],
      "svgPath": targetSVG,
      "labelColor": "white",  
      "labelRollOverColor": "#000000",
      "scale": 1.5,
      "type": "circle",
      "bringForwardOnHover": true,
      "label": "Alaska",
       "name": "Alaska",
      "labelShiftY": -10,
      "selectable": true,
      "title": "Alaska: Occupied by No One",
      "id": 0,
    };
idToData[0] = ALASKADATA;
let GREENLANDDATA =   {
      "latitude": GREENLAND[0],
      "longitude":    GREENLAND[1],
      "svgPath": targetSVG,
      "labelColor": "white",  
      "labelRollOverColor": "#000000",
      "scale": 1.5,
      "type": "circle",
      "bringForwardOnHover": true,
      "label": "Greenland",
      "name": "Greenland",
      "labelShiftY": 10,
      "labelShiftX": -5,
      "selectable": true,
      "title": "Greenland: Occupied by No One",
      "id": 5,
    };
idToData[5] = GREENLANDDATA;
let CADATA =  {
      "latitude": CA[0],
      "longitude": CA[1],
      "svgPath": targetSVG,
      "selectable": true,
      "labelColor": "white",  
      "labelRollOverColor": "#000000",
      "scale": 1.5,
      "type": "circle",
      "bringForwardOnHover": true,
      "label": "C. America",
      "labelShiftY": 2,
       "name": "Central America",
      "title": "Central America: Occupied by No One",
      "id": 2,
    };
idToData[2] = CADATA;
let VZDATA = {
      "latitude": VZ[0], 
      "longitude":    VZ[1],
      "svgPath": targetSVG,
      "labelColor": "white",  
      "labelRollOverColor": "#000000",
      "scale": 1.5,
      "type": "circle",
      "bringForwardOnHover": true,
      "label": "Venezuela",
      "labelShiftY": 2,
      "selectable": true,
      "name": "Venezuela",
      "title": "Venezuela: Occupied by No One",
      "id": 9,
    };
idToData[9] = VZDATA;
let PERUDATA = {
      "latitude": PERU[0],
      "longitude": PERU[1],
      "svgPath": targetSVG,
      "labelColor": "white",  
      "labelRollOverColor": "#000000",
      "scale": 1.5,
      "type": "circle",
      "bringForwardOnHover": true,
      "labelShiftY": 10,
      "labelShiftX": -40,
      "selectable": true,
      "name": "Peru",
      "label": "Peru",
      "title": "Peru: Occupied by No One",
      "id": 10,
    };
idToData[10] = PERUDATA;
let BRAZILDATA =  {
      "latitude": BRAZIL[0],
      "longitude":    BRAZIL[1],
      "svgPath": targetSVG,
      "labelColor": "white",  
      "labelRollOverColor": "#000000",
      "scale": 1.5,
      "type": "circle",
      "bringForwardOnHover": true,
      "label": "Brazil",
      "name": "Brazil",
      "labelShiftY": 2,
      "selectable": true,
      "title": "Brazil: Occupied by No One",
      "id": 11,
    };
idToData[11] = BRAZILDATA;
let ARGENTINADATA =   {
      "latitude": ARGENTINA[0],
      "longitude":  ARGENTINA[1],
      "svgPath": targetSVG,
      "labelColor": "white",  
      "labelRollOverColor": "#000000",
      "scale": 1.5,
      "type": "circle",
      "bringForwardOnHover": true,
      "label": "Argentina",
      "name": "Argentina",
      "labelShiftY": 2,
      "selectable": true,
      "title": "Argentina: Occupied by No One",
      "id": 12,
    };
idToData[12] = ARGENTINADATA;
let ICELANDDATA = {
      "latitude": ICELAND[0], 
      "longitude": ICELAND[1],
      "svgPath": targetSVG,
      "labelColor": "white",  
      "labelRollOverColor": "#000000",
      "scale": 1.5,
      "type": "circle",
      "bringForwardOnHover": true,
      "label": "Iceland",
        "name": "Iceland",
      "labelShiftY": -10,
      "selectable": true,
      "title": "Iceland: Occupied by No One",
      "id": 19,
    };
idToData[19] = ICELANDDATA;
let GBDATA =  {
      "latitude":   GB[0],
      "longitude":  GB[1],
      "svgPath": targetSVG,
      "labelColor": "white",  
      "labelRollOverColor": "#000000",
      "scale": 1.5,
      "type": "circle",
      "bringForwardOnHover": true,
      "label": "GB",
      "labelShiftY": 10,
      "labelShiftX": -40,
      "selectable": true,
       "name": "Great Britain",
      "title": "Great Britain: Occupied by No One",
      "id": 20,
    };
idToData[20] = GBDATA;
let WEUDATA =  {
      "latitude":    WEU[0],
      "longitude":  WEU[1],
      "svgPath": targetSVG,
      "labelColor": "white",  
      "labelRollOverColor": "#000000",
      "scale": 1.5,
      "type": "circle",
      "bringForwardOnHover": true,
      "label": "W EU",
      "selectable": true,
      "labelShiftY": 10,
      "labelShiftX": -50,
      "name": "Western Europe",
      "title": "Western Europe: Occupied by No One",
      "id": 21,
    };
idToData[21] = WEUDATA;
let NEUDATA = {
      "latitude":    NEU[0],
      "longitude":   NEU[1],
      "selectable": true,
      "svgPath": targetSVG,
      "labelColor": "white",  
      "labelRollOverColor": "#000000",
      "scale": 1.5,
      "type": "circle",
      "bringForwardOnHover": true,
      "label": "N EU",
      "labelShiftY": 2,
      "name": "Northern Europe",
      "title": "Northern Europe: Occupied by No One",
      "id": 22,
    };
idToData[22] = NEUDATA;
let SEUDATA =    {
      "latitude":    SEU[0],
      "longitude":   SEU[1],
      "selectable": true,
      "svgPath": targetSVG,
      "labelColor": "white",  
      "labelRollOverColor": "#000000",
      "scale": 1.5,
      "type": "circle",
      "bringForwardOnHover": true,
      "label": "S EU",
      "labelShiftY": 2,
      "name": "Southern Europe",
      "title": "Southern Europe: Occupied by No One",
      "id": 24,
    };
idToData[24] = SEUDATA;
let SCANDINAVIADATA =   {
      "latitude":    SCANDINAVIA[0], 
      "longitude":   SCANDINAVIA[1],
      "svgPath": targetSVG,
      "selectable": true,
      "labelColor": "white",  
      "labelRollOverColor": "#000000",
      "scale": 1.5,
      "type": "circle",
      "bringForwardOnHover": true,
      "label": "Scandinavia",
      "name": "Scandinavia",
      "labelShiftY": 2,
      "title": "Scandinavia: Occupied by No One",
      "id": 23,
    };
idToData[23] = SCANDINAVIADATA;
let RUSSIADATA =  {
      "latitude":    RUSSIA[0],
      "longitude":   RUSSIA[1],
      "svgPath": targetSVG,
      "labelColor": "white",  
      "labelRollOverColor": "#000000",
      "scale": 1.5,
      "type": "circle",
      "bringForwardOnHover": true,
      "label": "Russia",
      "name": "Russia",
      "selectable": true,
      "labelShiftY": 2,
      "title": "Russia: Occupied by No One",
      "id": 25,
    };
idToData[25] = RUSSIADATA;
let NAFDATA =  {
      "latitude": NAF[0],
      "longitude": NAF[1],
      "svgPath": targetSVG,
      "selectable": true,
      "labelColor": "white",  
      "labelRollOverColor": "#000000",
      "scale": 1.5,
      "type": "circle",
      "bringForwardOnHover": true,
      "label": "N. AF",
      "labelShiftY": 10,
      "labelShiftX": -50,
      "name": "Northern Africa",
      "title": "Northern Africa: Occupied by No One",
      "id": 13,
    };
idToData[13] = NAFDATA;
let EGYPTDATA =   {
      "latitude":   EGYPT[0],
      "longitude":   EGYPT[1],
      "svgPath": targetSVG,
      "labelColor": "white",  
      "labelRollOverColor": "#000000",
      "scale": 1.5,
      "type": "circle",
      "bringForwardOnHover": true,
      "label": "Egypt",
      "name": "Egypt",
      "labelShiftY": 5,
      "selectable": true,
      "title": "Egypt: Occupied by No One",
      "id": 14,
    };
idToData[14] = EGYPTDATA;
let CAFDATA =  {
      "latitude":     CAF[0],
      "longitude":    CAF[1],
      "svgPath": targetSVG,
      "labelColor": "white",  
      "labelRollOverColor": "#000000",
      "scale": 1.5,
      "type": "circle",
      "bringForwardOnHover": true,
      "label": "C. AF",
      "labelShiftY": -15,
      "labelShiftX": -50,
      "selectable": true,
      "title": "Central Africa: Occupied by No One",
      "name":  "Central Africa",
      "id": 16,
    };
idToData[16] = CAFDATA;
let EAFDATA = {
      "latitude":     EAF[0], 
      "longitude":    EAF[1],
      "svgPath": targetSVG,
      "labelColor": "white",  
      "labelRollOverColor": "#000000",
      "scale": 1.5,
      "type": "circle",
      "bringForwardOnHover": true,
      "label": "E. AF",
      "name": "Eastern Africa",
      "labelShiftY": 2,
      "selectable": true,
      "title": "Eastern Africa: Occupied by No One",
      "id": 15,
};
idToData[15] = EAFDATA;
let SAFDATA =   {
      "latitude":    SAF[0],
      "longitude":    SAF[1],
      "svgPath": targetSVG,
      "labelColor": "white",  
      "labelRollOverColor": "#000000",
      "scale": 1.5,
      "type": "circle",
      "bringForwardOnHover": true,
      "label": "S. AF",
      "labelShiftY": 10,
      "labelShiftX": -50,
      "selectable": true,
      "name": "Sourthern Africa",
      "title": "Southern Africa: Occupied by No One",
      "id": 17,
    };
idToData[17] = SAFDATA;
let MADAGASCARDATA =   {
      "latitude":   MADAGASCAR[0],
      "longitude":     MADAGASCAR[1],
      "svgPath": targetSVG,
      "labelColor": "white",  
      "labelRollOverColor": "#000000",
      "scale": 1.5,
      "type": "circle",
      "bringForwardOnHover": true,
      "label": "Madagascar",
      "labelShiftY": 2,
      "selectable": true,
      "name": "Madagascar",
      "title": "Madagascar: Occupied by No One",
      "id": 18,
    };
idToData[18] = MADAGASCARDATA;
let WAUDATA =  {
      "latitude":   WAU[0],
      "longitude":     WAU[1],
      "svgPath": targetSVG,
      "labelColor": "white",  
      "labelRollOverColor": "#000000",
      "scale": 1.5,
      "type": "circle",
      "bringForwardOnHover": true,
      "label": "W. AU",
      "labelShiftY": 10,
      "labelShiftX": -50,
      "selectable": true,
      "name": "Western Australia",
      "title": "Western Australia: Occupied by No One",
      "id": 41,
    };
idToData[41] = WAUDATA;
let EAUDATA = {
      "latitude":   EAU[0], 
      "longitude":     EAU[1],
      "svgPath": targetSVG,
      "labelColor": "white",  
      "labelRollOverColor": "#000000",
      "scale": 1.5,
      "type": "circle",
      "bringForwardOnHover": true,
      "label": "E. AU",
      "labelShiftY": 2,
      "selectable": true,
      "name": "Eastern Australia", 
      "title": "Eastern Australia: Occupied by No One",
      "id": 38,
    };
idToData[38] = EAUDATA;
let INDONESIADATA =  {
      "latitude":  INDONESIA[0], 
      "longitude":    INDONESIA[1],
      "svgPath": targetSVG,
      "labelColor": "white",  
      "labelRollOverColor": "#000000",
      "scale": 1.5,
      "type": "circle",
      "bringForwardOnHover": true,
      "label": "Indonesia",
      "name": "Indonesia",
      "labelShiftY": -7,
      "selectable": true,
      "title": "Indonesia: Occupied by No One",
      "id": 39,
    };
idToData[39] = INDONESIADATA;
let NEWGUINEADATA =   {
      "latitude":  NEWGUINEA[0],
      "longitude":   NEWGUINEA[1],
      "svgPath": targetSVG,
      "labelColor": "white",  
      "labelRollOverColor": "#000000",
      "scale": 1.5,
      "type": "circle",
      "bringForwardOnHover": true,   
      "label": "New Guinea",
       "name": "New Guinea",
      "labelShiftY": 2,
      "selectable": true,
      "title": "New Guinea: Occupied by No One",
      "id": 40
    };
idToData[40] = NEWGUINEADATA;
let MEDATA =  {
      "latitude":  ME[0], 
      "longitude":  ME[1],
      "svgPath": targetSVG,
      "labelColor": "white",  
      "labelRollOverColor": "#000000",
      "name": "Middle East",
      "scale": 1.5,
      "type": "circle",
      "bringForwardOnHover": true,
      "label": "Middle East",
      "labelShiftY": 2,
      "selectable": true,
      "title": "Middle East: Occupied by No One",
      "id": 32,
    };
idToData[32] = MEDATA;
let INDIADATA =  {
      "latitude":  INDIA[0],
      "longitude":  INDIA[1],
      "svgPath": targetSVG,
      "labelColor": "white",  
      "labelRollOverColor": "#000000",
      "scale": 1.5,
      "type": "circle",
      "bringForwardOnHover": true,
      "label": "India",
      "name": "India",
      "labelShiftY": -2,
      "selectable": true,
      "title": "India: Occupied by No One",
      "id": 28,
    };
idToData[28] = INDIADATA;
let SEASIADATA =   {
      "latitude": SEASIA[0], 
      "longitude":  SEASIA[1],
      "svgPath": targetSVG,
      "labelColor": "white",  
      "labelRollOverColor": "#000000",
      "scale": 1.5,
      "type": "circle",
      "bringForwardOnHover": true,
      "label": "SE Asia",
      "labelShiftY": -2,
      "selectable": true,
      "name": "SouthEast Asia",
      "title": "SouthEast Asia: Occupied by No One",
      "id": 34,
    };
idToData[34] = SEASIADATA;
let CHINADATA =   {
      "latitude": CHINA[0],
      "longitude":  CHINA[1],
      "svgPath": targetSVG,
      "labelColor": "white",  
      "labelRollOverColor": "#000000",
      "scale": 1.5,
      "type": "circle",
      "bringForwardOnHover": true,
      "label": "China",
      "labelShiftY": 10,
      "selectable": true,
      "title": "China",
      "name": "China",
      "id": 27
    };
idToData[27] = CHINADATA;
let MONGOLIADATA =   {
      "latitude": MONGOLIA[0],
      "longitude":  MONGOLIA[1],
      "svgPath": targetSVG,
      "labelColor": "white",  
      "labelRollOverColor": "#000000",
      "scale": 1.5,
      "type": "circle",
      "bringForwardOnHover": true,
      "label": "Mongolia",
      "name": "Mongolia",
      "labelShiftY": 2,
      "selectable": true,
      "title": "Mongolia: Occupied by No One",
      "id": 33,
    };
idToData[33] = MONGOLIADATA;
let JAPANDATA =    {
      "latitude": JAPAN[0],
      "longitude":  JAPAN[1],
      "svgPath": targetSVG,
      "labelColor": "white",  
      "labelRollOverColor": "#000000",
      "scale": 1.5,
      "type": "circle",
      "bringForwardOnHover": true,
      "label": "Japan",
      "name": "Japan",
      "labelShiftY": 2,
      "selectable": true,
      "title": "Japan: Occupied by No One",
      "id": 30,
    };
idToData[30] = JAPANDATA;
let KAMCHATKADATA =  {
      "latitude": KAMCHATKA[0],
      "longitude":   KAMCHATKA[1],
      "svgPath": targetSVG,
      "labelColor": "white",  
      "labelRollOverColor": "#000000",
      "scale": 1.5,
      "type": "circle",
      "bringForwardOnHover": true,
      "label": "Kamchatka",
      "name": "Kamchatka",
      "labelShiftY": 10,
      "selectable": true,
      "title": "Kamchatka: Occupied by No One",
      "id": 31,
    };
idToData[31] = KAMCHATKADATA;
let YAKUTSKDATA = {
      "latitude": YAKUTSK[0],
      "longitude":  YAKUTSK[1],
      "svgPath": targetSVG,
      "labelColor": "white",  
      "labelRollOverColor": "#000000",
      "scale": 1.5,
      "type": "circle",
      "bringForwardOnHover": true,
      "label": "Yakutsk",
      "name": "Yakutsk",
      "labelShiftY": -10,
      "selectable": true,
      "title": "Yakutsk: Occupied by No One",
      "id": 37
  };
idToData[37] = YAKUTSKDATA;
let IRKUSTKDATA =  {
      "latitude": IRKUSTK[0], 
      "longitude":  IRKUSTK[1],
      "svgPath": targetSVG,
      "labelColor": "white",  
      "labelRollOverColor": "#000000",
      "scale": 1.5,
      "type": "circle",
      "bringForwardOnHover": true,
      "label": "Irkustk",
      "name": "Irkustk",
      "labelShiftY": 2,
      "selectable": true,
      "title": "Irkustk: Occupied by No One",
      "id": 29,
    };
idToData[29] = IRKUSTKDATA;
let SIBERIADATA =   {
      "latitude": SIBERIA[0],
      "longitude": SIBERIA[1],
      "svgPath": targetSVG,
      "labelColor": "white",  
      "labelRollOverColor": "#000000",
      "scale": 1.5,
      "type": "circle",
      "bringForwardOnHover": true,
      "label": "Siberia",
      "name": "Siberia",
      "labelShiftY": -10,
      "labelShiftX": -10,
      "selectable": true,
      "title": "Siberia: Occupied by No One",
      "id": 35,
    };
idToData[35] = SIBERIADATA;
let URALDATA =   {
		"latitude": URAL[0],
      "longitude":  URAL[1],
      "svgPath": targetSVG,
      "labelColor": "white",  
      "labelRollOverColor": "#000000",
      "scale": 1.5,
      "type": "circle",
      "bringForwardOnHover": true,
      "label": "Ural",
      "name": "Ural",
      "labelShiftY": -10,
      "labelShiftX": -10,
      "selectable": true,
      "title": "Ural: Occupied by No One",
      "id": 36,
    };
idToData[36] = URALDATA;
let AFGHANISTANDATA = {
      "latitude": AFGHANISTAN[0],
      "longitude": AFGHANISTAN[1],
      "svgPath": targetSVG,
      "labelColor": "white",  
      "labelRollOverColor": "#000000",
      "scale": 1.5,
      "type": "circle",
      "bringForwardOnHover": true,
      "label": "Afghanistan",
      "name": "Afghanistan",
      "labelShiftY": -10,
      "selectable": true,
      "title": "Afghanistan: Occupied by No One",
      "id": 26,
    };
idToData[26] = AFGHANISTANDATA;
let EUS_WUS = {
      "latitudes": [ EUS[0], WUS[0]],
      "longitudes": [ EUS[1], WUS[1] ],
      "color": "black",
      "bringForwardOnHover": false,
    };

let EUS_QUEBEC = { "latitudes": [ EUS[0], QUEBEC[0]],
      "longitudes": [ EUS[1], QUEBEC[1] ],
      "color": "black",
          "bringForwardOnHover": false,
    };
let EUS_CA = {
      "latitudes": [ EUS[0], CA[0] ],
      "longitudes": [ EUS[1], CA[1]],
        "color": "black"
    };
let EUS_ONTARIO =  {
      "latitudes": [ EUS[0], ONTARIO[0] ],
      "longitudes": [ EUS[1], ONTARIO[1] ],
        "color": "black",
        "bringForwardOnHover": false,
        "id": "EUS_ONTARIO"
    };
let WUS_CA = {
      "latitudes": [ WUS[0], CA[0]],
      "longitudes": [ WUS[1], CA[1]],
        "color": "black",
        "bringForwardOnHover": false,
        "id": "WUS_CA"

    };
let WUS_ALBERTA = {
      "latitudes": [ WUS[0], ALBERTA[0] ],
      "longitudes": [ WUS[1], ALBERTA[1]],
        "color": "black",
        "bringForwardOnHover": false,
    };
let WUS_ONTARIO = {
      "latitudes": [ WUS[0], ONTARIO[0] ],
      "longitudes": [ WUS[1], ONTARIO[1]],
        "color": "black",
        "bringForwardOnHover": false,
    };
let ALBERTA_ONTARIO = {
      "latitudes": [ ALBERTA[0], ONTARIO[0]],
      "longitudes": [ ALBERTA[1], ONTARIO[1] ],
        "color": "black"
    };
let ALBERTA_NWTERRITORIES = {
      "latitudes": [ ALBERTA[0], NWTERRITORIES[0] ],
      "longitudes": [ ALBERTA[1], NWTERRITORIES[1] ],
        "color": "black",
        "bringForwardOnHover": false,
    };
let ALBERTA_ALASKA = {
      "latitudes": [ ALBERTA[0], ALASKA[0]],
      "longitudes": [ ALBERTA[1], ALASKA[1]],
        "color": "black",
        "bringForwardOnHover": false,
    };
let ONTARIO_QUEBEC = {
      "latitudes": [ ONTARIO[0], QUEBEC[0]],
      "longitudes": [ ONTARIO[1], QUEBEC[1] ],
        "color": "black",
        "bringForwardOnHover": false,
        "id": "ONTARIO_QUEBEC"
    };
let ONTARIO_NWTERRITORIES =  {
      "latitudes": [ ONTARIO[0], NWTERRITORIES[0]],
      "longitudes": [ ONTARIO[1], NWTERRITORIES[1]],
        "color": "black",
        "bringForwardOnHover": false,
    };
let ONTARIO_GREENLAND = {
      "latitudes": [ ONTARIO[0], GREENLAND[0]],
      "longitudes": [ ONTARIO[1], GREENLAND[1] ],
        "color": "black"
    } ;
let NWTERRITORIES_ALASKA = {
      "latitudes": [ ALASKA[0], NWTERRITORIES[0]],
      "longitudes": [ALASKA[1], NWTERRITORIES[1]],
        "color": "black",
        "bringForwardOnHover": false,
    };
let NWTERRITORIES_GREENLAND =   {
      "latitudes": [ GREENLAND[0], NWTERRITORIES[0]],
      "longitudes": [GREENLAND[1], NWTERRITORIES[1]],
        "color": "black",
        "bringForwardOnHover": false,
    }
let GREENLAND_ICELAND =  {
      "latitudes": [ GREENLAND[0], ICELAND[0]],
      "longitudes": [GREENLAND[1], ICELAND[1]],
        "color": "black",
        "bringForwardOnHover": false,
    };
let GREENLAND_QUEBEC =  {
      "latitudes": [ GREENLAND[0],  QUEBEC[0]],
      "longitudes": [GREENLAND[1], QUEBEC[1]],
        "color": "black",
        "bringForwardOnHover": false,
    };
let ICELAND_GB =   {
      "latitudes": [ ICELAND[0],  GB[0]],
      "longitudes": [ICELAND[1], GB[1]],
        "color": "black",
        "bringForwardOnHover": false,
    };
let SCANDINAVIA_ICELAND =   {
      "latitudes": [ ICELAND[0],  SCANDINAVIA[0]],
      "longitudes": [ICELAND[1], SCANDINAVIA[1]],
        "color": "black",
        "bringForwardOnHover": false,
    };
let SCANDINAVIA_GB =  {
      "latitudes": [SCANDINAVIA[0],  GB[0]],
      "longitudes": [SCANDINAVIA[1], GB[1]],
        "color": "black",
        "bringForwardOnHover": false,
    };
let NEU_GB =   {
      "latitudes": [NEU[0],  GB[0]],
      "longitudes": [NEU[1], GB[1]],
        "color": "black",
        "bringForwardOnHover": false,
    };
let WEU_GB =  {
      "latitudes": [WEU[0],  GB[0]],
      "longitudes": [WEU[1], GB[1]],
        "color": "black",
        "bringForwardOnHover": false,
    };
let WEU_SEU =  {
      "latitudes": [WEU[0],  SEU[0]],
      "longitudes": [WEU[1], SEU[1]],
        "color": "black",
        "bringForwardOnHover": false,
    };
let WEU_NAF =  {
      "latitudes": [WEU[0],  NAF[0]],
      "longitudes": [WEU[1], NAF[1]],
        "color": "black",
        "bringForwardOnHover": false,
    };
let WEU_NEU =  {
      "latitudes": [WEU[0],  NEU[0]],
      "longitudes": [WEU[1], NEU[1]],
        "color": "black",
        "bringForwardOnHover": false,
    };
let NEU_RUSSIA =  {
      "latitudes": [NEU[0],  RUSSIA[0]],
      "longitudes": [NEU[1], RUSSIA[1]],
        "color": "black",
        "bringForwardOnHover": false,
    };
let NEU_SEU =  {
      "latitudes": [NEU[0],  SEU[0]],
      "longitudes": [NEU[1], SEU[1]],
        "color": "black",
        "bringForwardOnHover": false,
    };
let SCANDINAVIA_NEU = {
      "latitudes": [NEU[0],  SCANDINAVIA[0]],
      "longitudes": [NEU[1], SCANDINAVIA[1]],
        "color": "black",
        "bringForwardOnHover": false,
    }
let SCANDINAVIA_RUSSIA = {
      "latitudes": [RUSSIA[0],  SCANDINAVIA[0]],
      "longitudes": [RUSSIA[1], SCANDINAVIA[1]],
          "color": "black",
          "bringForwardOnHover": false,
    };
let EGYPT_SEU =     {
      "latitudes": [EGYPT[0],  SEU[0]],
      "longitudes": [EGYPT[1], SEU[1]],
        "color": "black",
        "bringForwardOnHover": false,
    };
let ME_SEU =  {
      "latitudes": [ME[0],  SEU[0]],
      "longitudes": [ME[1], SEU[1]],
        "color": "black",
        "bringForwardOnHover": false,
    };
let NAF_SEU =  {
      "latitudes": [NAF[0],  SEU[0]],
      "longitudes": [NAF[1], SEU[1]],
        "color": "black",
        "bringForwardOnHover": false,
    };
let NAF_EGYPT =  {
      "latitudes": [NAF[0],  EGYPT[0]],
      "longitudes": [NAF[1], EGYPT[1]],
        "color": "black",
        "bringForwardOnHover": false,
    };
let NAF_CAF =  {
      "latitudes": [NAF[0],  CAF[0]],
      "longitudes": [NAF[1], CAF[1]],
        "color": "black",
        "bringForwardOnHover": false,
    };
let EAF_ME = {
	  "latitudes": [EAF[0], ME[0]],
	  "longitudes": [EAF[1], ME[1]],
	    "color": "black",
        "bringForwardOnHover": false,
}
let NAF_EAF = {
      "latitudes": [NAF[0],  EAF[0]],
      "longitudes": [NAF[1], EAF[1]],
        "color": "black",
        "bringForwardOnHover": false,
    };
let NAF_BRAZIL =    {
      "latitudes": [NAF[0],  BRAZIL[0]],
      "longitudes": [NAF[1], BRAZIL[1]],
        "color": "black",
        "bringForwardOnHover": false,
    };
let EAF_CAF =       {
      "latitudes": [EAF[0],  CAF[0]],
      "longitudes": [EAF[1], CAF[1]],
        "color": "black",
        "bringForwardOnHover": false,
    };
let SAF_CAF =   {
      "latitudes": [SAF[0],  CAF[0]],
      "longitudes": [SAF[1], CAF[1]],
        "color": "black",
        "bringForwardOnHover": false,
    };
let SAF_EAF =  {
      "latitudes": [SAF[0],  EAF[0]],
      "longitudes": [SAF[1], EAF[1]],
        "color": "black",
        "bringForwardOnHover": false,
    };
let MADAGASCAR_EAF =   {
      "latitudes": [EAF[0],  MADAGASCAR[0]],
      "longitudes": [EAF[1], MADAGASCAR[1]],
        "color": "black",
        "bringForwardOnHover": false,
    };
let MADAGASCAR_SAF =  {
      "latitudes": [SAF[0],  MADAGASCAR[0]],
      "longitudes": [SAF[1], MADAGASCAR[1]],
        "color": "black",
        "bringForwardOnHover": false,
    };
let EGYPT_EAF =  {
      "latitudes": [EGYPT[0],  EAF[0]],
      "longitudes": [EGYPT[1], EAF[1]],
        "color": "black",
        "bringForwardOnHover": false,
    };
let EGYPT_ME =  {
      "latitudes": [EGYPT[0],  ME[0]],
      "longitudes": [EGYPT[1], ME[1]],
        "color": "black",
        "bringForwardOnHover": false,
    };
let BRAZIL_PERU =   {
      "latitudes": [BRAZIL[0],  PERU[0]],
      "longitudes": [BRAZIL[1], PERU[1]],
        "color": "black",
        "bringForwardOnHover": false,
    };

let ARGENTINA_BRAZIL =   {
      "latitudes": [BRAZIL[0],  ARGENTINA[0]],
      "longitudes": [BRAZIL[1], ARGENTINA[1]],
        "color": "black",
        "bringForwardOnHover": false,
    };
let BRAZIL_VZ =   {
      "latitudes": [BRAZIL[0],  VZ[0]],
      "longitudes": [BRAZIL[1], VZ[1]],
        "color": "black",
        "bringForwardOnHover": false,
    };
  let VZ_CA =  {
      "latitudes": [VZ[0],  CA[0]],
      "longitudes": [VZ[1], CA[1]],
        "color": "black",
        "bringForwardOnHover": false,
    };
let VZ_PERU =  {
      "latitudes": [VZ[0],  PERU[0]],
      "longitudes": [VZ[1], PERU[1]],
        "color": "black",
        "bringForwardOnHover": false,
    };
let ARGENTINA_PERU =  {
      "latitudes": [ARGENTINA[0],  PERU[0]],
      "longitudes": [ARGENTINA[1], PERU[1]],
        "color": "black",
        "bringForwardOnHover": false,
    };
let ME_RUSSIA = {
      "latitudes": [ME[0],  RUSSIA[0]],
      "longitudes": [ME[1], RUSSIA[1]],
        "color": "black",
        "bringForwardOnHover": false,
    };
let AFGHANISTAN_ME = 
    {
      "latitudes": [ME[0],  AFGHANISTAN[0]],
      "longitudes": [ME[1], AFGHANISTAN[1]],
        "color": "black",
        "bringForwardOnHover": false,
    };
let ME_INDIA =  {
      "latitudes": [ME[0],  INDIA[0]],
      "longitudes": [ME[1], INDIA[1]],
        "color": "black",
        "bringForwardOnHover": false,
    };
let URAL_RUSSIA =  {
      "latitudes": [URAL[0],  RUSSIA[0]],
      "longitudes": [URAL[1], RUSSIA[1]],
        "color": "black",
        "bringForwardOnHover": false,
    };
let AFGHANISTAN_RUSSIA =   {
      "latitudes": [AFGHANISTAN[0],  RUSSIA[0]],
      "longitudes": [AFGHANISTAN[1], RUSSIA[1]],
        "color": "black",
        "bringForwardOnHover": false,
    };
let AFGHANISTAN_URAL =  {
      "latitudes": [URAL[0],  AFGHANISTAN[0]],
      "longitudes": [URAL[1], AFGHANISTAN[1]],
        "color": "black",
        "bringForwardOnHover": false,
    };
let URAL_CHINA =  {
      "latitudes": [URAL[0],  CHINA[0]],
      "longitudes": [URAL[1], CHINA[1]],
        "color": "black",
        "bringForwardOnHover": false,
    };
let URAL_SIBERIA =  {
      "latitudes": [URAL[0],  SIBERIA[0]],
      "longitudes": [URAL[1], SIBERIA[1]],
        "color": "black",
        "bringForwardOnHover": false,
    };
let AFGHANISTAN_CHINA = {
      "latitudes": [AFGHANISTAN[0],  CHINA[0]],
      "longitudes": [AFGHANISTAN[1], CHINA[1]],
        "color": "black",
        "bringForwardOnHover": false,
    };
let AFGHANISTAN_INDIA =   {
      "latitudes": [AFGHANISTAN[0],  INDIA[0]],
      "longitudes": [AFGHANISTAN[1], INDIA[1]],
        "color": "black",
        "bringForwardOnHover": false,
    };
let CHINA_INDIA =  {
      "latitudes": [CHINA[0],  INDIA[0]],
      "longitudes": [CHINA[1], INDIA[1]],
        "color": "black",
        "bringForwardOnHover": false,
    };
let SEASIA_INDIA =   {
      "latitudes": [SEASIA[0],  INDIA[0]],
      "longitudes": [SEASIA[1], INDIA[1]],
        "color": "black",
        "bringForwardOnHover": false,
    };
let CHINA_MONGOLIA =  {
      "latitudes": [CHINA[0],  MONGOLIA[0]],
      "longitudes": [CHINA[1], MONGOLIA[1]],
        "color": "black",
        "bringForwardOnHover": false,
    };
let CHINA_SEASIA =  {
      "latitudes": [CHINA[0],  SEASIA[0]],
      "longitudes": [CHINA[1], SEASIA[1]],
        "color": "black",
        "bringForwardOnHover": false,
    };
let IRKUSTK_MONGOLIA = {
      "latitudes": [IRKUSTK[0],  MONGOLIA[0]],
      "longitudes": [IRKUSTK[1], MONGOLIA[1]],
      "color": "black",
      "bringForwardOnHover": false,
    };
let JAPAN_MONGOLIA =   {
      "latitudes": [JAPAN[0],  MONGOLIA[0]],
      "longitudes": [JAPAN[1], MONGOLIA[1]], 
       "color": "black",
       "bringForwardOnHover": false,
    };
let KAMCHATKA_MONGOLIA =  {
      "latitudes": [KAMCHATKA[0],  MONGOLIA[0]],
      "longitudes": [KAMCHATKA[1], MONGOLIA[1]],
       "color": "black",
       "bringForwardOnHover": false,
    };
let YAKUTSK_IRKUSTK =  {
      "latitudes": [YAKUTSK[0],  IRKUSTK[0]],
      "longitudes": [YAKUTSK[1], IRKUSTK[1]],
       "color": "black",
       "bringForwardOnHover": false,
    };
let IRKUSTK_SIBERIA =  {
      "latitudes": [SIBERIA[0],  IRKUSTK[0]],
      "longitudes": [SIBERIA[1], IRKUSTK[1]],
       "color": "black",
       "bringForwardOnHover": false,
    };
let KAMCHATKA_JAPAN =  {
      "latitudes": [JAPAN[0],  KAMCHATKA[0]],
      "longitudes": [JAPAN[1], KAMCHATKA[1]],
       "color": "black",
       "bringForwardOnHover": false,
    };
let IRKUSTK_KAMCHATKRA = {
      "latitudes": [KAMCHATKA[0],  IRKUSTK[0]],
      "longitudes": [KAMCHATKA[1], IRKUSTK[1]],
       "color": "black",
       "bringForwardOnHover": false,
    };
let YAKUTSK_SIBERIA ={
      "latitudes": [SIBERIA[0],  YAKUTSK[0]],
      "longitudes": [SIBERIA[1], YAKUTSK[1]],
       "color": "black",
       "bringForwardOnHover": false,
    };
let KAMCHATKA_YAKUTSK = {
      "latitudes": [KAMCHATKA[0],  YAKUTSK[0]],
      "longitudes": [KAMCHATKA[1], YAKUTSK[1]],
       "color": "black",
       "bringForwardOnHover": false,
    };
let SIBERIA_CHINA =  {
      "latitudes": [SIBERIA[0],  CHINA[0]],
      "longitudes": [SIBERIA[1], CHINA[1]],
       "color": "black",
       "bringForwardOnHover": false,
    };
let SIBERIA_MONGOLIA = {
      "latitudes": [SIBERIA[0],  MONGOLIA[0]],
      "longitudes": [SIBERIA[1], MONGOLIA[1]],
       "color": "black",
       "bringForwardOnHover": false,
    };
let INDONESIA_SEASIA =   {
      "latitudes": [SEASIA[0],  INDONESIA[0]],
      "longitudes": [SEASIA[1], INDONESIA[1]],
       "color": "black",
       "bringForwardOnHover": false,
    };
let NEWGUINEA_INDONESIA =  {
      "latitudes": [NEWGUINEA[0],  INDONESIA[0]],
      "longitudes": [NEWGUINEA[1], INDONESIA[1]], 
       "color": "black",
       "bringForwardOnHover": false,
    };
  let NEWGUINEA_WAU =  {
      "latitudes": [NEWGUINEA[0],  WAU[0]],
      "longitudes": [NEWGUINEA[1], WAU[1]],
       "color": "black",
       "bringForwardOnHover": false,
    };
let NEWGUINEA_EAU =  {
      "latitudes": [NEWGUINEA[0],  EAU[0]],
      "longitudes": [NEWGUINEA[1], EAU[1]],
       "color": "black",
       "bringForwardOnHover": false,
    };
let WAU_EAU = {
      "latitudes": [WAU[0],  EAU[0]],
      "longitudes": [WAU[1], EAU[1]],
       "color": "black",
       "bringForwardOnHover": false,
    };
let INDONESIA_WAU =  {
      "latitudes": [WAU[0],  INDONESIA[0]],
      "longitudes": [WAU[1], INDONESIA[1]],
       "color": "black",
       "bringForwardOnHover": false,
    };
let ALASKA_INF = {
   "latitudes": [ALASKA[0], KAMCHATKA[0]],
      "longitudes": [ALASKA[1], -9999],
       "color": "black",
       "bringForwardOnHover": false,
};
let KAMCHATKA_INF = {
   "latitudes": [ALASKA[0], KAMCHATKA[0]],
      "longitudes": [9999, KAMCHATKA[1]],
       "color": "black",
       "bringForwardOnHover": false,
};
let SEU_RUSSIA = {
  "latitudes":[RUSSIA[0], SEU[0]],
    "longitudes":[RUSSIA[1], SEU[1]], 
    "color": "black",
    "bringForwardOnHover": false,
};
const ALASKAADJACENT = {"7": ALBERTA_ALASKA, "6": NWTERRITORIES_ALASKA, "31": ALASKA_INF};
terrToTerrToLine.push(ALASKAADJACENT);
const ONTARIOADJACENT = {"8": ONTARIO_QUEBEC, "6": ONTARIO_NWTERRITORIES, "7":ALBERTA_ONTARIO, "4":WUS_ONTARIO, "5": ONTARIO_GREENLAND, "1":EUS_ONTARIO};
terrToTerrToLine.push(ONTARIOADJACENT);
const CENTRALAMERICAADJACENT = {"3": EUS_CA, "9": VZ_CA, "4": WUS_CA}; 
terrToTerrToLine.push(CENTRALAMERICAADJACENT);
const EUSADJACENT = {"2": EUS_CA, "4": EUS_WUS, "8": EUS_QUEBEC, "1":EUS_ONTARIO};
terrToTerrToLine.push(EUSADJACENT);
const WUSADJACENT = {"3": EUS_WUS, "2": WUS_CA, "7":WUS_ALBERTA, "1": WUS_ONTARIO};
terrToTerrToLine.push(WUSADJACENT);
const GREENLANDADJACENT = {"1": ONTARIO_GREENLAND, "19":GREENLAND_ICELAND, "8": GREENLAND_QUEBEC, "6":NWTERRITORIES_GREENLAND};
terrToTerrToLine.push(GREENLANDADJACENT);
const NWTERRITORIESADJACENT = {"5":NWTERRITORIES_GREENLAND, "7":ALBERTA_NWTERRITORIES, "1":ONTARIO_NWTERRITORIES, "0":NWTERRITORIES_ALASKA};
terrToTerrToLine.push(NWTERRITORIESADJACENT);
const  ALBERTAADJACENT = {"0": ALBERTA_ALASKA, "1": ALBERTA_ONTARIO,"4":WUS_ALBERTA, "6": ALBERTA_NWTERRITORIES};
terrToTerrToLine.push(ALBERTAADJACENT);
const QUEBECADJACENT = {"3":EUS_QUEBEC, "1": ONTARIO_QUEBEC, "5": GREENLAND_QUEBEC};
terrToTerrToLine.push(QUEBECADJACENT);
const VZADJACENT = {"2": VZ_CA, "10": VZ_PERU, "11":BRAZIL_VZ};
terrToTerrToLine.push(VZADJACENT);
const PERUADJACENT = {"9": VZ_PERU, "11": BRAZIL_PERU, "12":ARGENTINA_PERU};
terrToTerrToLine.push(PERUADJACENT);
const BRAZILADJACENT = {"10": BRAZIL_PERU, "9": BRAZIL_VZ, "12": ARGENTINA_BRAZIL, "13": NAF_BRAZIL};
terrToTerrToLine.push(BRAZILADJACENT);
const ARGENTINAADJACENT = {"12": ARGENTINA_BRAZIL, "10": ARGENTINA_PERU};
terrToTerrToLine.push(ARGENTINAADJACENT);
const NAFADJACENT = {"12": NAF_BRAZIL, "24":NAF_SEU, "14": NAF_EGYPT, "16": NAF_CAF, "21": WEU_NAF,"15": NAF_EAF};
terrToTerrToLine.push(NAFADJACENT);
const EGYPTADJACENT = {"24": EGYPT_SEU, "15": EGYPT_EAF, "32":EGYPT_ME, "13": NAF_EGYPT};
terrToTerrToLine.push(EGYPTADJACENT);
const EAFADJACENT = {"32":EAF_ME, "16": EAF_CAF, "14": EGYPT_EAF, "13": NAF_EAF, "17": SAF_EAF, "18": MADAGASCAR_EAF};
terrToTerrToLine.push(EAFADJACENT);
const CAFADJACENT = {"15": EAF_CAF, "13":NAF_CAF, "17":SAF_CAF};
terrToTerrToLine.push(CAFADJACENT);
const SAFADJACENT = {"16": SAF_CAF, "15":SAF_EAF, "18": MADAGASCAR_SAF};
terrToTerrToLine.push(SAFADJACENT);
const MADAGASCARADJACENT = {"17": MADAGASCAR_SAF, "15": MADAGASCAR_EAF};
terrToTerrToLine.push(MADAGASCARADJACENT);
const ICELANDADJACENT = {"20": ICELAND_GB, "5":GREENLAND_ICELAND, "23": SCANDINAVIA_ICELAND};
terrToTerrToLine.push(ICELANDADJACENT);
const GBADJACENT = {"19": ICELAND_GB, "23": SCANDINAVIA_GB, "22": NEU_GB, "21":WEU_GB};
terrToTerrToLine.push(GBADJACENT);
const WEUADJACENT = {"20": WEU_GB, "13": WEU_NAF, "24": WEU_SEU, "22": WEU_NEU};
terrToTerrToLine.push(WEUADJACENT);
const NEUADJACENT = {"20": NEU_GB, "25": NEU_RUSSIA, "23": SCANDINAVIA_NEU, "21": WEU_NEU};
terrToTerrToLine.push(NEUADJACENT);
const SCANDINAVIAADJACENT = {"20": SCANDINAVIA_GB, "19": SCANDINAVIA_ICELAND, "22":SCANDINAVIA_NEU, "25": SCANDINAVIA_RUSSIA};
terrToTerrToLine.push(SCANDINAVIAADJACENT);
const SEUADJACENT = {"25": SEU_RUSSIA, "21": WEU_SEU, "14":EGYPT_SEU, "13":NAF_SEU, "32": ME_SEU, "22":NEU_SEU};
terrToTerrToLine.push(SEUADJACENT);
const RUSSIAADJACENT = {"24":SEU_RUSSIA, "23":SCANDINAVIA_RUSSIA, "32": ME_RUSSIA, "22":NEU_RUSSIA, "36": URAL_RUSSIA, "26":AFGHANISTAN_RUSSIA};
terrToTerrToLine.push(RUSSIAADJACENT);
const AFGHANISTANADJACENT = {"25": AFGHANISTAN_RUSSIA,"32": AFGHANISTAN_ME, "36": AFGHANISTAN_URAL, "27": AFGHANISTAN_CHINA, "28": AFGHANISTAN_INDIA};
terrToTerrToLine.push(AFGHANISTANADJACENT);
const CHINAADJACENT = {"28": CHINA_INDIA, "33": CHINA_MONGOLIA, "34": CHINA_SEASIA, "26":AFGHANISTAN_CHINA, "36": URAL_CHINA, "35":SIBERIA_CHINA};
terrToTerrToLine.push(CHINAADJACENT);
const INDIAADJACENT = {"27": CHINA_INDIA,"26": AFGHANISTAN_INDIA, "32": ME_INDIA, "34": SEASIA_INDIA };
terrToTerrToLine.push(INDIAADJACENT);
const IRKUSTKADJACENT = {"33": IRKUSTK_MONGOLIA, "35": IRKUSTK_SIBERIA, "31": IRKUSTK_KAMCHATKRA, "37":YAKUTSK_IRKUSTK};
terrToTerrToLine.push(IRKUSTKADJACENT);
const JAPANADJACENT = {"33":JAPAN_MONGOLIA, "31": KAMCHATKA_JAPAN};
terrToTerrToLine.push(JAPANADJACENT);
const KAMCHATKAADJACENT = {"0":ALASKA_INF, "30": KAMCHATKA_JAPAN, "37": KAMCHATKA_YAKUTSK, "33":KAMCHATKA_MONGOLIA, "29": IRKUSTK_KAMCHATKRA};
terrToTerrToLine.push(KAMCHATKAADJACENT);
const MEADJACENT = {"28":ME_INDIA, "24": ME_SEU,"25":  ME_RUSSIA, "26": AFGHANISTAN_ME, "15": EAF_ME};
terrToTerrToLine.push(MEADJACENT);
const MONGOLIAADJACENT = {"31": KAMCHATKA_MONGOLIA, "30": JAPAN_MONGOLIA, "27": CHINA_MONGOLIA, "29": IRKUSTK_MONGOLIA, "35":SIBERIA_MONGOLIA};
terrToTerrToLine.push(MONGOLIAADJACENT);
const SEASIAADJACENT = {"28": SEASIA_INDIA, "27":CHINA_SEASIA, "39": INDONESIA_SEASIA};
terrToTerrToLine.push(SEASIAADJACENT);
const SIBERIAADJACENT= {"33": SIBERIA_MONGOLIA, "27": SIBERIA_CHINA, "29": IRKUSTK_SIBERIA, "36":URAL_SIBERIA, "37": YAKUTSK_SIBERIA };
terrToTerrToLine.push(SIBERIAADJACENT);
const  URALADJACENT = {"35": URAL_SIBERIA, "27": URAL_CHINA, "25": URAL_RUSSIA, "26":AFGHANISTAN_URAL};
terrToTerrToLine.push(URALADJACENT);
const YAKUTSKADJACENT = {"35": YAKUTSK_SIBERIA, "29": YAKUTSK_IRKUSTK, "31": KAMCHATKA_YAKUTSK};
terrToTerrToLine.push(YAKUTSKADJACENT);
const EAUADJACENT = {"40": NEWGUINEA_EAU, "41": WAU_EAU};
terrToTerrToLine.push(EAUADJACENT);
const INDONESIAADJACENT = {"34": INDONESIA_SEASIA, "41": INDONESIA_WAU, "40": NEWGUINEA_INDONESIA};
terrToTerrToLine.push(INDONESIAADJACENT);
const NEWGUINEA_ADJACENT = {"39": NEWGUINEA_INDONESIA, "38": NEWGUINEA_EAU, "41": NEWGUINEA_WAU};
terrToTerrToLine.push(NEWGUINEA_ADJACENT);
const WAUADJACENT = {"38": WAU_EAU, "40": NEWGUINEA_WAU, "39": INDONESIA_WAU};
terrToTerrToLine.push(WAUADJACENT);
let lines  =  [EAF_ME, EUS_WUS, EUS_QUEBEC, EUS_CA,EUS_ONTARIO,WUS_CA, WUS_ALBERTA, WUS_ONTARIO, ALBERTA_ONTARIO, ALBERTA_NWTERRITORIES, ALBERTA_ALASKA, ONTARIO_QUEBEC,
ONTARIO_NWTERRITORIES, ONTARIO_GREENLAND, NWTERRITORIES_ALASKA,NWTERRITORIES_GREENLAND, GREENLAND_ICELAND, GREENLAND_QUEBEC, ICELAND_GB, SCANDINAVIA_ICELAND, 
SCANDINAVIA_GB, NEU_GB, WEU_GB,WEU_SEU, WEU_NAF,WEU_NEU, NEU_RUSSIA,NEU_SEU,SCANDINAVIA_NEU,SCANDINAVIA_RUSSIA,EGYPT_SEU,ME_SEU,NAF_SEU,NAF_EGYPT,NAF_CAF,NAF_EAF,
NAF_BRAZIL,EAF_CAF, SAF_CAF,SAF_EAF,MADAGASCAR_EAF,MADAGASCAR_SAF,EGYPT_EAF,EGYPT_ME,BRAZIL_PERU,ARGENTINA_BRAZIL, BRAZIL_VZ,VZ_CA,VZ_PERU,ARGENTINA_PERU,ME_RUSSIA,
AFGHANISTAN_ME,ME_INDIA,URAL_RUSSIA,AFGHANISTAN_RUSSIA,AFGHANISTAN_URAL,URAL_CHINA,URAL_SIBERIA,AFGHANISTAN_CHINA,AFGHANISTAN_INDIA,CHINA_INDIA,SEASIA_INDIA,
CHINA_MONGOLIA,CHINA_SEASIA,IRKUSTK_MONGOLIA,JAPAN_MONGOLIA,KAMCHATKA_MONGOLIA,YAKUTSK_IRKUSTK,IRKUSTK_SIBERIA,KAMCHATKA_JAPAN,IRKUSTK_KAMCHATKRA, YAKUTSK_SIBERIA,
KAMCHATKA_YAKUTSK,SIBERIA_CHINA,SIBERIA_MONGOLIA,INDONESIA_SEASIA, NEWGUINEA_INDONESIA,NEWGUINEA_WAU,NEWGUINEA_EAU,WAU_EAU,INDONESIA_WAU, ALASKA_INF, KAMCHATKA_INF, SEU_RUSSIA];


let game = [EUSDATA, WUSDATA, QUEBECDATA,ONTARIODATA,ALBERTADATA, NWTERRITORIESDATA,ALASKADATA,GREENLANDDATA,CADATA,VZDATA,PERUDATA,BRAZILDATA,ARGENTINADATA,ICELANDDATA,
    GBDATA,WEUDATA,NEUDATA, SEUDATA,SCANDINAVIADATA,RUSSIADATA,  NAFDATA, EGYPTDATA,CAFDATA,EAFDATA, SAFDATA,MADAGASCARDATA, WAUDATA,EAUDATA,INDONESIADATA, NEWGUINEADATA, 
  MEDATA, INDIADATA,SEASIADATA, CHINADATA, MONGOLIADATA, JAPANDATA, KAMCHATKADATA, YAKUTSKDATA, IRKUSTKDATA, SIBERIADATA, URALDATA,AFGHANISTANDATA ];
let map = AmCharts.makeChart( "mapdiv", {

  "type": "map",
  "projection": "mercator",
  "mouseWheelZoomEnabled": true,
  "linesSettings": {
    "arc": -0.7, // this makes lines curved. Use value from -1 to 1
    color: "white",
  	  "dashLength": 1,
	  "thickness": 1,
	  "selectable": false
  },
  "dataProvider": {
      "map": "continentsLow",
      "lines": lines,
      images:game,
      "zoomLatitude": 0,
      "zoomLongitude": 0,
      "areas": [
      {
        "id": "europe",
        "color": "grey",
        "outlineAlpha": "1",
        "outlineColor": "black",
        "outlineThickness": 1,
      },
      {
        "id": "asia",
        "color": "grey",
        "outlineAlpha": "1",
        "outlineColor": "black",
        "outlineThickness": 1,
      },
      {
        "id": "africa",
        "color": "grey",
        "outlineAlpha": "1",
        "outlineColor": "black",
        "outlineThickness": 1,
      },
      {
        "id": "south_america",
        "color": "grey",
        "outlineAlpha": "1",
        "outlineColor": "black",
        "outlineThickness": 1,
      },
      {
        "id": "north_america",
        "color": "grey",
        "outlineAlpha": "1",
        "outlineColor": "black",
        "outlineThickness": 1,
      },
      {
        "id": "australia",
        "color": "grey",
        "outlineAlpha": "1",
        "outlineColor": "black",
        "outlineThickness": 1,
      }
    ]
  },

"imageSettings":
    {
      "labelColor": "white",
      "selectable": "true",
    }
  ,
  "areasSettings": {
    "rollOverOutlineColor": 'white',
    zoomLevel: 0.5,
    zoomLongitude: -20.1341, // from the alert box...
    zoomLatitude: 49.1712 // here
  },

  "largeMap": {}
} );

function select_territory(event) {
  if (phase == "setup") {
    let mess = {"type": MESSAGE_TYPE.MOVE, "moveType": MOVE_TYPES.SETUP, "playerId": myId, "territoryId": event.mapObject.id};
    if (availableForClaim.includes(event.mapObject.id)) {
      availableForClaim = [];
      conn.send(JSON.stringify(mess));
    }
  } else if (phase == "setup_reinforce") {
    let mess = {"type": MESSAGE_TYPE.MOVE, "moveType": MOVE_TYPES.SETUP_REINFORCE, "playerId": myId, "territoryId": event.mapObject.id};
    if (availableForClaim.includes(event.mapObject.id)) {
      availableForClaim = [];
      conn.send(JSON.stringify(mess));
    }
  } else if (phase == "reinforce") {
	 if (availableForClaim.includes(event.mapObject.id)) {
	   bolstering = event.mapObject.id;
     document.getElementById("selecting").innerHTML = "Bolstering " + event.mapObject.name;
      addBlink($("#selecting"));
               setTimeout(function() {
                   removeBlink($("#selecting")); 
                }, 4000);
     if (placed < placeMax) {
        document.getElementById("reinforcer").disabled = false;
        $("#reinforcer").removeClass('disabled');
     }
     if (terToPlace.get(bolstering) != null) {
       if (terToPlace.get(bolstering) > 0) {
         document.getElementById("deinforcer").disabled = false;
         $("#deinforcer").removeClass('disabled');
       }
      } else {
        document.getElementById("deinforcer").disabled = true;
        $("#deinforcer").addClass('disabled');
      }     
    } else {
      document.getElementById("reinforcer").disabled = true;
      $("#reinforcer").addClass('disabled');
      document.getElementById("deinforcer").disabled = true;
      $("#deinforcer").addClass('disabled');
    }
  } else if (phase == "attacking") {
    if (availableForClaim.includes(event.mapObject.id.toString())) {
      if (event.mapObject.id != attackFrom) {
        $("#attack").disabled = true;
        $("#attack").addClass('disabled');
        reset_line_color();

        attackFrom = event.mapObject.id;
        attackTo = null;
        attackables = [];
        attackables = terToTar[attackFrom];
        let outer = terrToTerrToLine[attackFrom.toString()];
        console.log(attackables.toString());
        for (let i = 0; i<attackables.length; i++) {
          let currLine = outer[attackables[i].toString()];
          console.log(currLine);
          changeLines(colors[myId], currLine);
          attackableLines.push(currLine);
        }
        map.dataProvider.zoomLevel = map.zoomLevel();
        map.dataProvider.zoomLatitude = map.zoomLatitude();
        map.dataProvider.zoomLongitude = map.zoomLongitude();
        map.validateData();
        document.getElementById("available").innerHTML = "You Can Attack:";
        $("#clickList").empty();              
        selectTerritoriesInformation(attackables);
        $("#clickList").show();
        $("#available").show();
        $("#simSel").show();
        document.getElementById("attacking").style.display = "inline-block";
        document.getElementById("attacking").innerHTML = "What territory are you attacking?<br>";
        addBlink($("#attacking"));
        setTimeout(function() {
          removeBlink($("#attacking")); 
        }, 4000);
        $("#attackerNumberDie").empty();
        for (let index = 1; index <= terToDie[attackFrom.toString()]; index++) {
          if (index == terToDie[attackFrom.toString()]) {
            $("#attackerNumberDie").append("<option value=" + index.toString() +
            " selected='selected'>" + index.toString() + "</option>");
          } else {
            $("#attackerNumberDie").append("<option value=" + index.toString()
            + ">" + index.toString() + "</option>");
          }
        }
      }
      document.getElementById("bolsters").innerHTML = "Attacking from " + idToData[attackFrom].name + "!<br>";
      addBlink($("#bolsters"));
      setTimeout(function() {
        removeBlink($("#bolsters")); 
      }, 4000);
    } else if (attackFrom != null && attackables.includes(event.mapObject.id)) {
      attackTo = event.mapObject.id;
      let outer = terrToTerrToLine[attackFrom.toString()];
      let currLine = outer[attackTo.toString()];
      for (let i = 0; i<attackableLines.length; i++) {
        if (attackableLines[i] !==currLine) {
          changeLines("black", attackableLines[i]);
        }
        else {
          attackLine = attackableLines[i];
          changeLines(colors[myId], attackLine);
        }
      }
      map.dataProvider.zoomLevel = map.zoomLevel();
      map.dataProvider.zoomLatitude = map.zoomLatitude();
      map.dataProvider.zoomLongitude = map.zoomLongitude();
      map.validateData();
      document.getElementById("attacking").innerHTML = "Laying Seige to " + idToData[attackTo].name
        + "!<br> Select a Dice Number and Attack!<br>";
      addBlink($("#attacking"));
      setTimeout(function() {
        removeBlink($("#attacking")); 
      }, 4000);
      $("#clickList").hide();
      $("#available").hide();
      $("#simSel").hide();
      $("#attack").disabled = false;
      $("#attack").removeClass('disabled');
    }
  } else if (phase = "move_troops") {
      if (availableForClaim.includes(event.mapObject.id.toString())) {
        if (moveFrom == null) {
          moveFrom = event.mapObject.id;
          moveables = terrToReachableTerrs[moveFrom];
          document.getElementById("available").innerHTML = "You Can Move Troops To:";
          document.getElementById("bolsters").innerHTML = "Select A Territory To Move Troops To";
          $("#clickList").empty();              
          selectTerritoriesInformation(moveables);
          $("#moveTroopsNumber").empty();
          for (let index = 1; index <= terrToMaxTroopsMove[moveFrom.toString()]; index++) {
            if (index == terrToMaxTroopsMove[moveFrom.toString()]) {
              $("#moveTroopsNumber").append("<option value=" + index.toString()
               + " selected='selected'>" + index.toString() + "</option>");
            } else {
               $("#moveTroopsNumber").append("<option value=" + 
                  index.toString() + ">" + index.toString() + "</option>");
            }
          }
        } else if (moveFrom != null && moveables.includes(event.mapObject.id)) {
          moveTo = event.mapObject.id;
          $("#moveTroops").disabled = false;
          $("#moveTroops").removeClass('disabled');
        }
      } else if (moveFrom != null && moveables.includes(event.mapObject.id)) {
        moveTo = event.mapObject.id;
        $("#moveTroops").disabled = false;
        $("#moveTroops").removeClass('disabled');

      }
    }
  }


function reset_attack() {
  document.getElementById("bolsters").innerHTML = "Which of your Territories is going to Attack?<br>";
  document.getElementById("available").innerHTML = "You Can Attack From:";
  $("#clickList").empty();              
  selectTerritoriesInformation(availableForClaim);
  $("#clickList").show();
  $("#available").show();
  $("#simSel").show();
  addBlink($("#bolsters"));
  setTimeout(function() {
    removeBlink($("#bolsters")); 
  }, 4000);
  $("#attack").disabled = true;
  $("#attack").addClass('disabled');
  document.getElementById("attacking").innerHTML = "What territory are you attacking?<br>";
  document.getElementById("attacking").style.display = "none";
  
  $("#attackerNumberDie").empty();
  if (attackLine !=null) {
    let outer = terrToTerrToLine[attackFrom.toString()];
    if (outer != null) {
      if (outer[attackTo.toString()].id === attackLine.id) {
        attackLine.color = "black";
        map.dataProvider.zoomLevel = map.zoomLevel();
        map.dataProvider.zoomLatitude = map.zoomLatitude();
        map.dataProvider.zoomLongitude = map.zoomLongitude();
        map.validateData();
      }
    }
  }
  reset_line_color();
  attackLine = null;
  attackFrom = null;
  attackTo = null;
  attackables = [];
}

function reset_line_color() {
  for (let i = 0; i<attackableLines.length; i++) {
    let currLine = attackableLines[i];
    changeLines("black", currLine);
  }
  map.dataProvider.zoomLevel = map.zoomLevel();
  map.dataProvider.zoomLatitude = map.zoomLatitude();
  map.dataProvider.zoomLongitude = map.zoomLongitude();
  map.validateData();
}

function reset_move_troops() {
  $("#moveTroops").disabled = true;
  $("#moveTroops").addClass('disabled');
  $("#moveTroopsNumber").empty();
  document.getElementById("available").innerHTML = "You Can Move Troops From:";
  document.getElementById("bolsters").innerHTML = "Select A Territory From Which to Move Troops";
  $("#clickList").empty();              
  selectTerritoriesInformation(availableForClaim);
  $("#clickList").show();
  $("#available").show();
  $("#simSel").show();
  moveFrom = null;
  moveTo = null;
  moveables = [];
}


const place_troop = event => {
  event.preventDefault();
  if (phase == "reinforce" && bolstering != null && placed < placeMax) {
    if (terToPlace.get(bolstering) == null) {
      terToPlace.set(bolstering, 0);
    }
    terToPlace.set(bolstering, terToPlace.get(bolstering) + 1);
    placed++;
    document.getElementById("deinforcer").disabled = false;
    $("#deinforcer").removeClass('disabled');
    document.getElementById("bolsters").innerHTML = (placeMax - placed) + " Troops Left to Place";
    changeTerritoryStatus(idToName[myId], 1, idToData[bolstering], colors[myId]);
    if (placed === placeMax) {
      document.getElementById("reinforcer").disabled = true;
      $("#reinforcer").addClass('disabled');
      document.getElementById("confirm").disabled = false; 
      $("#confirm").removeClass('disabled');
    }
  }
}

const remove_troop = event => {
  event.preventDefault();
  if (phase == "reinforce" && bolstering != null && placed > 0) {
    if (terToPlace.get(bolstering) == null) {
      terToPlace.set(bolstering, 0);
    } 
    if (terToPlace.get(bolstering) == 0) {
      return;
    }
    terToPlace.set(bolstering, terToPlace.get(bolstering) - 1);
    placed--;
    document.getElementById("bolsters").innerHTML = placeMax - placed + " Troops Left to Place";
    changeTerritoryStatus(idToName[myId], -1, idToData[bolstering], colors[myId]);
    document.getElementById("reinforcer").disabled = false;
    $("#reinforcer").removeClass('disabled');
    document.getElementById("confirm").disabled = true;
    $("#confirm").addClass('disabled');
    if (terToPlace.get(bolstering) == 0) {
       document.getElementById("deinforcer").disabled = true;
       $("#deinforcer").addClass('disabled');
    }
  }
}

function make_selection(player, territory) {
  changeTerritoryStatus(idToName[player], 1, idToData[territory], colors[player]);
}

function sparcify() {
  let placements = terToPlace.entries();
  let currPlace = placements.next().value;
  while (currPlace != null) {
    if (currPlace[1] == 0) {
      terToPlace.delete(currPlace[0]);
    }
    currPlace = placements.next().value;
  }
}

function make_bolster(player, territories) {
  if (myId != player) {
    for (x in territories) {
      changeTerritoryStatus(idToName[player], territories[x], idToData[x], colors[player]);
    }
  }
}

function changeTerritoryStatus(player, numSoldier, territory, color) {
  let originalTitle = territory.title.split(":");
  let originalLabel = territory.label.split(" ");
  if (terToSol[territory.id] == null) {
    terToSol[territory.id] = 0;
  }
  terToSol[territory.id] = terToSol[territory.id] + numSoldier;
  
  territory.title = territory.name + " Occupied by " + player + " Soldiers: " + terToSol[territory.id];
  let string = "";
  if (isNaN(parseInt(originalLabel[originalLabel.length-1]))) {
    for (let i = 0; i <originalLabel.length; i++) {
      if (i + 1 !=originalLabel.length) {
        string += originalLabel[i] + " ";
      }
      else {
        string = string + originalLabel[i] + ": "
      }
    }
    string += terToSol[territory.id];
  }
  else {
    let modified = territory.label.split(":");
    string = modified[0] + ": " + terToSol[territory.id];
  } 

  territory.label = string;
  if (terToSol[territory.id] == 0) {
    territory.color = "black";
    territory.labelRollOverColor = "black";
  } else {
    territory.color = color;
    territory.labelRollOverColor = color;
  }
  map.dataProvider.zoomLevel = map.zoomLevel();
  map.dataProvider.zoomLatitude = map.zoomLatitude();
  map.dataProvider.zoomLongitude = map.zoomLongitude();
  map.validateData();
}

function changeLines(color, line) {
  console.log(line);
  if (line === ALASKA_INF) {
      KAMCHATKA_INF.color = color;
  }
  line.color = color;
}
