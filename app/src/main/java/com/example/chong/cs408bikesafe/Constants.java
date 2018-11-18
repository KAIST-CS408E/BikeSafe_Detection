package com.example.chong.cs408bikesafe;

public class Constants {
    static final long DETECTION_INTERVAL_IN_MILLISECONDS = 500;
    static final long LOCATION_INTERVAL_IN_MILLISECONDS = 2000;
    static final long FASTEST_LOCATION_INTERVAL_IN_MILLISECONDS = 500;
    static final String[] intersections_string = new String[] {
            "Intersection from the above road to Endless,36.364302,127.360604",
            "Intersection into Bio Building,36.364707,127.361367",
            "Intersection connecting the two carparks,36.364903,127.360098",
            "First intersection into bio buildings to the left of main gate,36.36569,127.362965",
            "Intersection to KISTI,36.365882,127.35774",
            "Intersection into KISTI carpark,36.365965,127.357923",
            "Intersection at the Main Gate,36.36616,127.363492",
            "First intersection into Galilei carpark,36.366326,127.358604",
            "Second intersection into Galilei carpark,36.366375,127.359408",
            "Intersection into back of E2,36.366557,127.364939",
            "Intersection from E2 to the road to Main Gate,36.366957,127.365559",
            "Intersection to Nano Fab,36.367512,127.36676",
            "Intersection in front of Heemang,36.367911,127.356409",
            "Intersection to behind Heemang,36.368607,127.355952",
            "Zebra crossing to Dunkin,36.36863,127.364238",
            "Intersection on the road in front of Dunkin,36.368727,127.364374",
            "Intersection to the Round Field,36.368812,127.367144",
            "Intersection to Startup Village,36.369281,127.355531",
            "Intersection to East Bio B/D,36.369602,127.36647",
            "Intersection on the road of Creative Admin B/D and W8 (nearest to W8),36.369834,127.36083",
            "Second intersection to Creative carpark,36.369844,127.363581",
            "Big intersection to Sejong,36.369939,127.366243",
            "Intersection to International Village,36.370113,127.355236",
            "Intersection into W8,36.370256,127.360324",
            "First intersection to Creative carpark,36.37029,127.363202",
            "Intersection into Admin B/D carpark,36.370458,127.360096",
            "Intersection right in front of Creative,36.370615,127.36225",
            "Intersection next to Lab B/D,36.370663,127.363164",
            "Second intersection at bottom of Taxi Stand,36.370721,127.359735",
            "Third intersection of carpark,36.370783,127.355254",
            "Intersection on the road of Creative Admin B/D and W8 (nearest to Creative),36.370827,127.362524",
            "First Intersection at bottom of Taxi Stand,36.370855,127.359507",
            "Zebra crossing from Sports Complex to road towards Creative ,36.371314,127.360726",
            "Intersection in front of carpark in Endless Road,36.371424,127.356201",
            "Second intersection of carpark,36.37144,127.355533",
            "The dangerous shortcut thing at the crossing,36.371512,127.36104",
            "Intersection between Jung Moon Sool and Sports Complex,36.371563,127.361355",
            "Intersection from Taxi Stand to the road towards Creative,36.371725,127.359577",
            "Intersection at the Aerospace Lab,36.371765,127.357098",
            "The roundabout next to Sports Complex to Creative,36.372142,127.362185",
            "The roundabout next to Sports Complex to N1,36.372411,127.362618",
            "The roundabout next to Sports Complex to North,36.372579,127.36216",
            "Intersection from Taxi Stand to ME B/D,36.372971,127.359558",
            "Intersection to President's House,36.373017,127.364912",
            "Intersection on top of Taxi Stand,36.373395,127.359555",
            "Intersection between Sarang and Kaimaru,36.373425,127.358853",
            "First intersection from East Gate (in front of N1),36.373438,127.365388",
            "The 5-way intersection between N10 and N4,36.373455,127.361033",
            "Intersection into Jilli,36.3742155,127.359494",
            "Intersection between N10 and the building with Hubo,36.37444,127.361036",
            "Intersection into Silloe,36.374482,127.359876",
            "Intersection to downhill area on road from N10 to N1,36.374487,127.363183",
            "First intersection after going downhill,36.374958,127.361829",
            "Bottom middle intersection to right after downhill,36.375034,127.362069",
            "Bottom rightmost intersection to right after downhill,36.375038,127.362996",
            "Bottommost intersection to left roundabout after downhill,36.375065,127.361088",
            "The other intersection of left roundabout,36.375278,127.36109",
            "Top middle intersection to right after downhill,36.375664,127.362065"
    };

    // horizontal roads must have matching longitudes. vertical roads must have matching latitudes.
    // if there is a right angle within the area, the area must be a rectangle.
    static final String[] roads_string = new String[] {
            "North Dorm road,36.373458,127.359421,36.373478,127.356279,36.373380,127.359421,36.373385,127.356279,H",
            "N1 road,36.374468,127.363939,36.374480,127.361155,36.374403,127.363939,36.374401,127.361155,H",
            "Sarang/KAIMARU,36.373945,127.358830,36.373540,127.358800,36.373945,127.358910,36.373540,127.358893,V",
            "Behind CB,36.374500,127.360890,36.374533,127.359914,36.374405,127.360890,36.374437,127.359914,H",
            "Lotteria road,36.373510,127.360920,36.373510,127.359925,36.373420,127.360920,36.373420,127.359925,H"
    };
}
