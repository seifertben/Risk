const EUS = [38.392017, -85.047551];
const WUS =  [41.187449, -114.449540];
const QUEBEC =  [50.206416, -75.586515];
const ONTARIO = [50.899997,  -87.521976];
const ALBERTA =   [55.279171,  -113.971396];
const NWTERRITORIES = [65.199589,   -119.253051];
const ALASKA =  [65.903817,   -154.899950];
const GREENLAND = [74.663742,  -41.234447]; 
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
// var planeSVG = d="M 1072.8956,639.08362 L 1561.5008,639.08362";
var planeSVG = "m2,106h28l24,30h72l-44,-133h35l80,132h98c21,0 21,34 0,34l-98,0 -80,134h-35l43,-133h-71l-24,30h-28l15,-47";

var targetSVG = "M9,0C4.029,0,0,4.029,0,9s4.029,9,9,9s9-4.029,9-9S13.971,0,9,0z M9,15.93 c-3.83,0-6.93-3.1-6.93-6.93S5.17,2.07,9,2.07s6.93,3.1,6.93,6.93S12.83,15.93,9,15.93 M12.5,9c0,1.933-1.567,3.5-3.5,3.5S5.5,10.933,5.5,9S7.067,5.5,9,5.5 S12.5,7.067,12.5,9z";
let idToData = {};
let EUSDATA = {
      "latitude": EUS[0], 
      "longitude": EUS[1],
      "selectable": true,
      "svgPath": targetSVG,
      "color": "#000000",  
      "labelRollOverColor": "#000000",
      "scale": 0.5,
      "label": "E. US",
      "labelShiftY": 2,
      "title": "Eastern United States: Occupied by No One",
      "id": 3,
    };
idToData[3] = EUSDATA;
let WUSDATA = {
      "latitude": WUS[0],
      "longitude":  WUS[1],
      "svgPath": targetSVG,
      "selectable": true,
      "color": "#000000",  
      "labelRollOverColor": "#000000",
      "scale": 0.5,
      "label": "W. US",
      "labelShiftY": 2,
      "title": "Western United States: Occupied by No One",
      "id": 4,
    };
idToData[4] = WUSDATA;
let QUEBECDATA =  {
      "latitude": QUEBEC[0],
      "longitude":   QUEBEC[1],
      "svgPath": targetSVG,
      "selectable": true,
      "color": "#000000",  
      "labelRollOverColor": "#000000",
      "scale": 0.5,
      "label": "Quebec ",
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
      "color": "#000000",  
      "labelRollOverColor": "#000000",
      "scale": 0.5,
      "label": "Ontario",
      "labelShiftY": 2,
      "title": "Ontario: Occupied by No One",
      "id": 1,
    };
idToData[1] = ONTARIODATA;
let ALBERTADATA =  {
      "latitude": ALBERTA[0], 
      "longitude":  ALBERTA[1] ,
      "svgPath": targetSVG,
      "selectable": true,
      "color": "#000000",
      "labelRollOverColor": "#000000",
      "scale": 0.5,
      "label": "Alberta",
      "labelShiftY": 2,
      "title": "Alberta: Occupied by No One",
      "id": 7,
    };
idToData[7] = ALBERTADATA;
let NWTERRITORIESDATA =  {
      "latitude": NWTERRITORIES[0],
      "longitude": NWTERRITORIES[1],
      "selectable": true,
      "svgPath": targetSVG,
      "color": "#000000",  
      "labelRollOverColor": "#000000",
      "scale": 0.5,
      "label": "NW Territories",
      "labelShiftY": 2,
      "title": "Northwest Territories: Occupied by No One",
      "id": 6,
    };
idToData[6] = NWTERRITORIESDATA;
let ALASKADATA =  {
      "latitude": ALASKA[0],
      "longitude":   ALASKA[1],
      "svgPath": targetSVG,
      "color": "#000000",  
      "labelRollOverColor": "#000000",
      "scale": 0.5,
      "label": "Alaska",
      "labelShiftY": 2,
      "selectable": true,
      "title": "Alaska: Occupied by No One",
      "id": 0,
    };
idToData[0] = ALASKADATA;
let GREENLANDDATA =   {
      "latitude": GREENLAND[0],
      "longitude":    GREENLAND[1],
      "svgPath": targetSVG,
      "color": "#000000",  
      "labelRollOverColor": "#000000",
      "scale": 0.5,
      "label": "Greenland",
      "labelShiftY": 2,
      "selectable": true,
      "title": "Greenland: Occupied by No One",
      "id": 5,
    };
idToData[5] = GREENLANDDATA;
let CADATA =  {
      "latitude": CA[0],
      "longitude":     CA[1],
      "svgPath": targetSVG,
      "color": "#000000",  
      "labelRollOverColor": "#000000",
      "scale": 0.5,
      "label": "C. America",
      "labelShiftY": 2,
      "selectable": true,
      "title": "Central America: Occupied by No One",
      "id": 2,
    };
idToData[2] = CADATA;
let VZDATA = {
      "latitude": VZ[0], 
      "longitude":    VZ[1],
      "svgPath": targetSVG,
      "color": "#000000",  
      "labelRollOverColor": "#000000",
      "scale": 0.5,
      "label": "Venezuela",
      "labelShiftY": 2,
      "selectable": true,
      "title": "Venezuela: Occupied by No One",
      "id": 9,
    };
idToData[9] = VZDATA;
let PERUDATA= {
      "latitude": PERU[0],
      "longitude":   PERU[1],
      "svgPath": targetSVG,
      "color": "#000000",  
      "labelRollOverColor": "#000000",
      "scale": 0.5,
      "label": "Peru",
      "labelShiftY": 2,
      "selectable": true,
      "title": "Peru: Occupied by No One",
      "id": 10,
    };
idToData[10] = PERUDATA;
let BRAZILDATA =  {
      "latitude": BRAZIL[0],
      "longitude":    BRAZIL[1],
      "svgPath": targetSVG,
      "color": "#000000",  
      "labelRollOverColor": "#000000",
      "scale": 0.5,
      "label": "Brazil",
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
      "color": "#000000",  
      "labelRollOverColor": "#000000",
      "scale": 0.5,
      "label": "Argentina",
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
      "color": "#000000",  
      "labelRollOverColor": "#000000",
      "scale": 0.5,
      "label": "Iceland",
      "labelShiftY": 2,
      "selectable": true,
      "title": "Iceland: Occupied by No One",
      "id": 19,
    };
idToData[19] = ICELANDDATA;
let GBDATA =  {
      "latitude":   GB[0],
      "longitude":  GB[1],
      "svgPath": targetSVG,
      "color": "#000000",  
      "labelRollOverColor": "#000000",
      "scale": 0.5,
      "label": "GB",
      "labelShiftY": 2,
      "selectable": true,
      "title": "Great Britain: Occupied by No One",
      "id": 20,
    };
idToData[20] = GBDATA;
let WEUDATA =  {
      "latitude":    WEU[0],
      "longitude":  WEU[1],
      "svgPath": targetSVG,
      "color": "#000000",  
      "labelRollOverColor": "#000000",
      "scale": 0.5,
      "label": "W EU",
      "selectable": true,
      "labelShiftY": 2,
      "title": "Western Europe: Occupied by No One",
      "id": 21,
    };
idToData[21] = WEUDATA;
let NEUDATA = {
      "latitude":    NEU[0],
      "longitude":   NEU[1],
      "selectable": true,
      "svgPath": targetSVG,
      "color": "#000000",  
      "labelRollOverColor": "#000000",
      "scale": 0.5,
      "label": "N EU",
      "labelShiftY": 2,
      "title": "Northern Europe: Occupied by No One",
      "id": 22,
    };
idToData[22] = NEUDATA;
let SEUDATA =    {
      "latitude":    SEU[0],
      "longitude":   SEU[1],
      "selectable": true,
      "svgPath": targetSVG,
      "color": "#000000",  
      "labelRollOverColor": "#000000",
      "scale": 0.5,
      "label": "S EU",
      "labelShiftY": 2,
      "title": "Southern Europe: Occupied by No One",
      "id": 24,
    };
idToData[24] = SEUDATA;
let SCANDINAVIADATA =   {
      "latitude":    SCANDINAVIA[0], 
      "longitude":   SCANDINAVIA[1],
      "svgPath": targetSVG,
      "selectable": true,
      "color": "#000000",  
      "labelRollOverColor": "#000000",
      "scale": 0.5,
      "label": "Scandinavia",
      "labelShiftY": 2,
      "title": "Scandinavia: Occupied by No One",
      "id": 23,
    };
idToData[23] = SCANDINAVIADATA;
let RUSSIADATA =  {
      "latitude":    RUSSIA[0],
      "longitude":   RUSSIA[1],
      "svgPath": targetSVG,
      "color": "#000000",  
      "labelRollOverColor": "#000000",
      "scale": 0.5,
      "label": "Russia",
      "selectable": true,
      "labelShiftY": 2,
      "title": "Russia: Occupied by No One",
      "id": 25,
    };
idToData[25] = RUSSIADATA;
let NAFDATA =  {
      "latitude":    NAF[0],
      "longitude":   NAF[1],
      "svgPath": targetSVG,
      "selectable": true,
      "color": "#000000",  
      "labelRollOverColor": "#000000",
      "scale": 0.5,
      "label": "N. AF",
      "labelShiftY": 2,
      "title": "Northern Africa: Occupied by No One",
      "id": 13,
    };
idToData[13] = NAFDATA;
let EGYPTDATA =   {
      "latitude":   EGYPT[0],
      "longitude":   EGYPT[1],
      "svgPath": targetSVG,
      "color": "#000000",  
      "labelRollOverColor": "#000000",
      "scale": 0.5,
      "label": "Egypt",
      "labelShiftY": 2,
      "selectable": true,
      "title": "Egypt: Occupied by No One",
      "id": 14,
    };
idToData[14] = EGYPTDATA;
let CAFDATA =  {
      "latitude":     CAF[0],
      "longitude":    CAF[1],
      "svgPath": targetSVG,
      "color": "#000000",  
      "labelRollOverColor": "#000000",
      "scale": 0.5,
      "label": "C. AF",
      "labelShiftY": 2,
      "selectable": true,
      "title": "Central Africa",
      "id": 16,
      "description": "Occupied by No One"
    };
idToData[16] = CAFDATA;
let EAFDATA = {
      "latitude":     EAF[0], 
      "longitude":    EAF[1],
      "svgPath": targetSVG,
      "color": "#000000",  
      "labelRollOverColor": "#000000",
      "scale": 0.5,
      "label": "E. AF",
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
      "color": "#000000",  
      "labelRollOverColor": "#000000",
      "scale": 0.5,
      "label": "S. AF",
      "labelShiftY": 2,
      "selectable": true,
      "title": "Southern Africa: Occupied by No One",
      "id": 17,
    };
idToData[17] = SAFDATA;
let MADAGASCARDATA =   {
      "latitude":   MADAGASCAR[0],
      "longitude":     MADAGASCAR[1],
      "svgPath": targetSVG,
      "color": "#000000",  
      "labelRollOverColor": "#000000",
      "scale": 0.5,
      "label": "Madagascar",
      "labelShiftY": 2,
      "selectable": true,
      "title": "Madagascar: Occupied by No One",
      "id": 18,
    };
idToData[18] = MADAGASCARDATA;
let WAUDATA =  {
      "latitude":   WAU[0],
      "longitude":     WAU[1],
      "svgPath": targetSVG,
      "color": "#000000",  
      "labelRollOverColor": "#000000",
      "scale": 0.5,
      "label": "W. AU",
      "labelShiftY": 2,
      "selectable": true,
      "title": "Western Australia: Occupied by No One",
      "id": 41,
    };
idToData[41] = WAUDATA;
let EAUDATA = {
      "latitude":   EAU[0], 
      "longitude":     EAU[1],
      "svgPath": targetSVG,
      "color": "#000000",  
      "labelRollOverColor": "#000000",
      "scale": 0.5,
      "label": "E. AU",
      "labelShiftY": 2,
      "selectable": true,
      "title": "Eastern Australia: Occupied by No One",
      "id": 38,
    };
idToData[38] = EAUDATA;
let INDONESIADATA =  {
      "latitude":  INDONESIA[0], 
      "longitude":    INDONESIA[1],
      "svgPath": targetSVG,
      "color": "#000000",  
      "labelRollOverColor": "#000000",
      "scale": 0.5,
      "label": "Indonesia",
      "labelShiftY": 2,
      "selectable": true,
      "title": "Indonesia: Occupied by No One",
      "id": 39,
    };
idToData[39] = INDONESIADATA;
let NEWGUINEADATA =   {
      "latitude":  NEWGUINEA[0],
      "longitude":   NEWGUINEA[1],
      "svgPath": targetSVG,
      "color": "#000000",  
      "labelRollOverColor": "#000000",
      "scale": 0.5,
      "label": "New Guinea",
      "labelShiftY": 2,
      "selectable": true,
      "title": "New Guinea",
      "id": 40,
      "description": "Occupied by No One"
    };
idToData[40] = NEWGUINEADATA;
let MEDATA =  {
      "latitude":  ME[0], 
      "longitude":  ME[1],
      "svgPath": targetSVG,
      "color": "#000000",  
      "labelRollOverColor": "#000000",
      "scale": 0.5,
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
      "color": "#000000",  
      "labelRollOverColor": "#000000",
      "scale": 0.5,
      "label": "India",
      "labelShiftY": 2,
      "selectable": true,
      "title": "India: Occupied by No One",
      "id": 28,
    };
idToData[28] = INDIADATA;
let SEASIADATA =   {
      "latitude": SEASIA[0], 
      "longitude":  SEASIA[1],
      "svgPath": targetSVG,
      "color": "#000000",  
      "labelRollOverColor": "#000000",
      "scale": 0.5,
      "label": "SE Asia",
      "labelShiftY": 2,
      "selectable": true,
      "title": "SouthEast Asia: Occupied by No One",
      "id": 34,
    };
idToData[34] = SEASIADATA;
let CHINADATA =   {
      "latitude": CHINA[0],
      "longitude":  CHINA[1],
      "svgPath": targetSVG,
      "color": "#000000",  
      "labelRollOverColor": "#000000",
      "scale": 0.5,
      "label": "China",
      "labelShiftY": 2,
      "selectable": true,
      "title": "China",
      "id": 27,
      "description": "Occupied by No One"
    };
idToData[27] = CHINADATA;
let MONGOLIADATA =   {
      "latitude": MONGOLIA[0],
      "longitude":  MONGOLIA[1],
      "svgPath": targetSVG,
      "color": "#000000",  
      "labelRollOverColor": "#000000",
      "scale": 0.5,
      "label": "Mongolia",
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
      "color": "#000000",  
      "labelRollOverColor": "#000000",
      "scale": 0.5,
      "label": "Japan",
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
      "color": "#000000",  
      "labelRollOverColor": "#000000",
      "scale": 0.5,
      "label": "Kamchatka",
      "labelShiftY": 2,
      "selectable": true,
      "title": "Kamchatka: Occupied by No One",
      "id": 31,
    };
idToData[31] = KAMCHATKADATA;
let YAKUTSKDATA = {
      "latitude": YAKUTSK[0],
      "longitude":  YAKUTSK[1],
      "svgPath": targetSVG,
      "color": "#000000",  
      "labelRollOverColor": "#000000",
      "scale": 0.5,
      "label": "Yakutsk",
      "labelShiftY": 2,
      "selectable": true,
      "title": "Yakutsk: Occupied by No One",
      "id": 37,
      "description": "Occupied by No One"
	};
idToData[37] = YAKUTSKDATA;
let IRKUSTKDATA =  {
      "latitude": IRKUSTK[0], 
      "longitude":  IRKUSTK[1],
      "svgPath": targetSVG,
      "color": "#000000",  
      "labelRollOverColor": "#000000",
      "scale": 0.5,
      "label": "Irkustk",
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
      "color": "#000000",  
      "labelRollOverColor": "#000000",
      "scale": 0.5,
      "label": "Siberia",
      "labelShiftY": 2,
      "selectable": true,
      "title": "Siberia: Occupied by No One",
      "id": 35,
    };
idToData[35] = SIBERIADATA;
let URALDATA =   {
      "latitude": URAL[0],
      "longitude":  URAL[1],
      "svgPath": targetSVG,
      "color": "#000000",  
      "labelRollOverColor": "#000000",
      "scale": 0.5,
      "label": "Ural",
      "labelShiftY": 2,
      "selectable": true,
      "title": "Ural: Occupied by No One",
      "id": 36,
    };
idToData[36] = URALDATA;
let AFGHANISTANDATA = {
      "latitude": AFGHANISTAN[0],
      "longitude": AFGHANISTAN[1],
      "svgPath": targetSVG,
      "color": "#000000",  
      "labelRollOverColor": "#000000",
      "scale": 0.5,
      "label": "Afghanistan",
      "labelShiftY": 2,
      "selectable": true,
      "title": "Afghanistan: Occupied by No One",
      "id": 26,
    };
idToData[26] = AFGHANISTANDATA;
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
        "color": "black",
        "id": "EUS_ONTARIO"
    };
let WUS_CA = {
      "latitudes": [ WUS[0], CA[0]],
      "longitudes": [ WUS[1], CA[1]],
        "color": "black", 
        "id": "WUS_CA"

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
        "color": "black",
        "id": "ONTARIO_QUEBEC"
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
let ALASKA_INF = {
   "latitudes": [ALASKA[0], KAMCHATKA[0]],
      "longitudes": [ALASKA[1], -9999],
       "color": "black"
}
let KAMCHATKA_INF = {
   "latitudes": [ALASKA[0], KAMCHATKA[0]],
      "longitudes": [9999, KAMCHATKA[1]],
       "color": "black"
}
let lines  =  [EUS_WUS, EUS_QUEBEC, EUS_CA,EUS_ONTARIO,WUS_CA, WUS_ALBERTA, WUS_ONTARIO, ALBERTA_ONTARIO, ALBERTA_NWTERRITORIES, ALBERTA_ALASKA, ONTARIO_QUEBEC,
ONTARIO_NWTERRITORIES, ONTARIO_GREENLAND, NWTERRITORIES_ALASKA,NWTERRITORIES_GREENLAND, GREENLAND_ICELAND, GREENLAND_QUEBEC, ICELAND_GB, SCANDINAVIA_ICELAND, 
SCANDINAVIA_GB, NEU_GB, WEU_GB,WEU_SEU, WEU_NAF,WEU_NEU, NEU_RUSSIA,NEU_SEU,SCANDINAVIA_NEU,SCANDINAVIA_RUSSIA,EGYPT_SEU,MEU_SEU,NAF_SEU,NAF_EGYPT,NAF_CAF,NAF_EAF,
NAF_BRAZIL,EAF_CAF, SAF_CAF,SAF_EAF,MADAGASCAR_EAF,MADAGASCAR_SAF,EGYPT_EAF,EGYPT_ME,BRAZIL_PERU,ARGENTINA_BRAZIL, BRAZIL_VZ,VZ_CA,VZ_PERU,ARGENTINA_PERU,ME_RUSSIA,
AFGHANISTAN_ME,ME_INDIA,URAL_RUSSIA,AFGHANISTAN_RUSSIA,AFGHANISTAN_URAL,URAL_CHINA,URAL_SIBERIA,AFGHANISTAN_CHINA,AFGHANISTAN_INDIA,CHINA_INDIA,SEASIA_INDIA,
CHINA_MONGOLIA,CHINA_SEASIA,IRKUSTK_MONGOLIA,JAPAN_MONGOLIA,KAMCHATKA_MONGOLIA,YAKUTSK_IRKUSTK,IRKUSTK_SIBERIA,KAMCHATKA_JAPAN,IRKUSTK_KAMCHATKRA, YAKUTSK_SIBERIA,
KAMCHATKA_YAKUTSK,SIBERIA_CHINA,SIBERIA_MONGOLIA,INDONESIA_SEASIA, NEWGUINEA_INDONESIA,NEWGUINEA_WAU,NEWGUINEA_EAU,WAU_EAU,INDONESIA_WAU, ALASKA_INF, KAMCHATKA_INF
// ,
// {
//       "svgPath": planeSVG,
//       "positionOnLine": 0,
//       "color": "#000000",  
//       "alpha": 0.1,
//       "animateAlongLine": true,
//       "lineId": "EUS_ONTARIO",
//       "flipDirection": true,
//       "loop": true,
//       "scale": 0.03,
//       "positionScale": 1.3
//     }
    // , {
    //   "svgPath": planeSVG,
    //   "positionOnLine": 0,
    //   "color": "#585869",
    //   "animateAlongLine": true,
    //   "lineId": "ONTARIO_QUEBEC",
    //   "flipDirection": true,
    //   "loop": true,
    //   "scale": 0.03,
    //   "positionScale": 1.8
    // }
    ];


let game = [EUSDATA, WUSDATA, QUEBECDATA,ONTARIODATA,ALBERTADATA, NWTERRITORIESDATA,ALASKADATA,GREENLANDDATA,CADATA,VZDATA,PERUDATA,BRAZILDATA,ARGENTINADATA,ICELANDDATA,
    GBDATA,WEUDATA,NEUDATA, SEUDATA,SCANDINAVIADATA,RUSSIADATA,  NAFDATA, EGYPTDATA,CAFDATA,EAFDATA, SAFDATA,MADAGASCARDATA, WAUDATA,EAUDATA,INDONESIADATA, NEWGUINEADATA, 
  MEDATA, INDIADATA,SEASIADATA, CHINADATA, MONGOLIADATA, JAPANDATA, KAMCHATKADATA, YAKUTSKDATA, IRKUSTKDATA, SIBERIADATA, URALDATA,AFGHANISTANDATA ];
let map = AmCharts.makeChart( "mapdiv", {

  "type": "map",
  "mouseWheelZoomEnabled": true,

  "dataProvider": {
      "map": "continentsLow",
      images:game,
      "lines": lines,
      "zoomLatitude": 0,
      "zoomLongitude": 0,
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
   

  "areasSettings": {
    "rollOverOutlineColor": 'grey',
    "selectedColor": undefined
  },

  /**
   * let's say we want a small map to be displayed, so let's create it
   */
  "largeMap": {}
} );

function select_territory(event) {
  console.log("CLICKED");
  let mess = {"type": MESSAGE_TYPE.MOVE, "moveType": MOVE_TYPES.CLAIM_TERRITORY, "playerId": myId, "territoryId": event.mapObject.id};
  console.log(availableForClaim.length);
  console.log(event.mapObject.id);
  if (availableForClaim.includes(event.mapObject.id)) {
	  console.log("IMIN");
	  conn.send(JSON.stringify(mess));
  }
}

function make_selection(player, territory) {
  console.log(myId);
  console.log(currentPlayer);
  if (myId == currentPlayer) {
    changeTerritoryStatus(idToName[player], 1, idToData[territory], colors[idToName[player]], colors[idToName[player]]);
    map.dataProvider.zoomLevel = map.zoomLevel();
    map.dataProvider.zoomLatitude = map.zoomLatitude();
    map.dataProvider.zoomLongitude = map.zoomLongitude();
    map.validateData();
  }
}

// // changeLines("blue", ONTARIO_GREENLAND);
// changeTerritoryStatus("Player 1", 5, NWTERRITORIESDATA, "blue");
// changeTerritoryStatus("Player 1", 5, NWTERRITORIESDATA, "blue");

function changeTerritoryStatus(player, numSoldier, territory, color, labelColor) {
  let originalTitle = territory.title.split(":");
  let originalLabel = territory.label.split(" ");

  territory.title = originalTitle[0] + " Occupied by " + player + " Soldiers: " + numSoldier;
   let string = "";
  //   for (let i = 0; i <originalLabel.length-1; i++) {
  //       string += originalLabel[i] + " ";
  //   }
  //   string += numSoldier.toString();
  // }
  console.log(isNaN(parseInt(originalLabel[originalLabel.length-1])));
  if (isNaN(parseInt(originalLabel[originalLabel.length-1]))) {
    console.log("here");
    for (let i = 0; i <originalLabel.length; i++) {
      if (i + 1 !=originalLabel.length) {
        string += originalLabel[i] + " ";
      }
      else {
        string = string + originalLabel[i] + ": "
      }
    }
    string += numSoldier.toString();
  }
  else {
    let modified = territory.label.split(":");
    string = modified[0] + ": " + numSoldier.toString();
  } 

  territory.label = string;
  territory.color = color;
  territory.labelRollOverColor = color;
}

function mapClick() {

}
function changeLines(color, line) {
  line.color = color;
}