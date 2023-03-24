 -- CSD 4464 – Programming Java EE
 -- Term Project - Airline Reservation System
 --
 -- Host: 127.0.0.1    Database: Flyme
 -- ------------------------------------------------------
 -- MySQL Server version	8.0.27

/*!40103 SET TIME_ZONE='+00:00' */;

--
-- Inserts  for table `bag_fares`
--

INSERT INTO `bag_fares` VALUES (1, 70.00);
INSERT INTO `bag_fares` VALUES (2, 120.00);
INSERT INTO `bag_fares` VALUES (3, 180.00);
INSERT INTO `bag_fares` VALUES (4, 230.00);

--
-- Inserts for table `classes`
--

INSERT INTO `classes` VALUES (1, 'First', 2, 2.0);
INSERT INTO `classes` VALUES (2, 'Business', 1, 1.5);
INSERT INTO `classes` VALUES (3, 'Economy', 0, 1.0);

--
-- Inserts for table `countries`
--

INSERT INTO `countries` VALUES ('CA', 'Canada');
INSERT INTO `countries` VALUES ('AF', 'Afghanistan');
INSERT INTO `countries` VALUES ('AL', 'Albania');
INSERT INTO `countries` VALUES ('DZ', 'Algeria');
INSERT INTO `countries` VALUES ('DS', 'American Samoa');
INSERT INTO `countries` VALUES ('AD', 'Andorra');
INSERT INTO `countries` VALUES ('AO', 'Angola');
INSERT INTO `countries` VALUES ('AI', 'Anguilla');
INSERT INTO `countries` VALUES ('AQ', 'Antarctica');
INSERT INTO `countries` VALUES ('AG', 'Antigua and Barbuda');
INSERT INTO `countries` VALUES ('AR', 'Argentina');
INSERT INTO `countries` VALUES ('AM', 'Armenia');
INSERT INTO `countries` VALUES ('AW', 'Aruba');
INSERT INTO `countries` VALUES ('AU', 'Australia');
INSERT INTO `countries` VALUES ('AT', 'Austria');
INSERT INTO `countries` VALUES ('AZ', 'Azerbaijan');
INSERT INTO `countries` VALUES ('BS', 'Bahamas');
INSERT INTO `countries` VALUES ('BH', 'Bahrain');
INSERT INTO `countries` VALUES ('BD', 'Bangladesh');
INSERT INTO `countries` VALUES ('BB', 'Barbados');
INSERT INTO `countries` VALUES ('BY', 'Belarus');
INSERT INTO `countries` VALUES ('BE', 'Belgium');
INSERT INTO `countries` VALUES ('BZ', 'Belize');
INSERT INTO `countries` VALUES ('BJ', 'Benin');
INSERT INTO `countries` VALUES ('BM', 'Bermuda');
INSERT INTO `countries` VALUES ('BT', 'Bhutan');
INSERT INTO `countries` VALUES ('BO', 'Bolivia');
INSERT INTO `countries` VALUES ('BA', 'Bosnia and Herzegovina');
INSERT INTO `countries` VALUES ('BW', 'Botswana');
INSERT INTO `countries` VALUES ('BV', 'Bouvet Island');
INSERT INTO `countries` VALUES ('BR', 'Brazil');
INSERT INTO `countries` VALUES ('IO', 'British Indian Ocean Territory');
INSERT INTO `countries` VALUES ('BN', 'Brunei Darussalam');
INSERT INTO `countries` VALUES ('BG', 'Bulgaria');
INSERT INTO `countries` VALUES ('BF', 'Burkina Faso');
INSERT INTO `countries` VALUES ('BI', 'Burundi');
INSERT INTO `countries` VALUES ('KH', 'Cambodia');
INSERT INTO `countries` VALUES ('CM', 'Cameroon');
INSERT INTO `countries` VALUES ('CV', 'Cape Verde');
INSERT INTO `countries` VALUES ('KY', 'Cayman Islands');
INSERT INTO `countries` VALUES ('CF', 'Central African Republic');
INSERT INTO `countries` VALUES ('TD', 'Chad');
INSERT INTO `countries` VALUES ('CL', 'Chile');
INSERT INTO `countries` VALUES ('CN', 'China');
INSERT INTO `countries` VALUES ('CX', 'Christmas Island');
INSERT INTO `countries` VALUES ('CC', 'Cocos (Keeling) Islands');
INSERT INTO `countries` VALUES ('CO', 'Colombia');
INSERT INTO `countries` VALUES ('KM', 'Comoros');
INSERT INTO `countries` VALUES ('CG', 'Congo');
INSERT INTO `countries` VALUES ('CK', 'Cook Islands');
INSERT INTO `countries` VALUES ('CR', 'Costa Rica');
INSERT INTO `countries` VALUES ('HR', 'Croatia (Hrvatska)');
INSERT INTO `countries` VALUES ('CU', 'Cuba');
INSERT INTO `countries` VALUES ('CY', 'Cyprus');
INSERT INTO `countries` VALUES ('CZ', 'Czech Republic');
INSERT INTO `countries` VALUES ('DK', 'Denmark');
INSERT INTO `countries` VALUES ('DJ', 'Djibouti');
INSERT INTO `countries` VALUES ('DM', 'Dominica');
INSERT INTO `countries` VALUES ('DO', 'Dominican Republic');
INSERT INTO `countries` VALUES ('TP', 'East Timor');
INSERT INTO `countries` VALUES ('EC', 'Ecuador');
INSERT INTO `countries` VALUES ('EG', 'Egypt');
INSERT INTO `countries` VALUES ('SV', 'El Salvador');
INSERT INTO `countries` VALUES ('GQ', 'Equatorial Guinea');
INSERT INTO `countries` VALUES ('ER', 'Eritrea');
INSERT INTO `countries` VALUES ('EE', 'Estonia');
INSERT INTO `countries` VALUES ('ET', 'Ethiopia');
INSERT INTO `countries` VALUES ('FK', 'Falkland Islands (Malvinas)');
INSERT INTO `countries` VALUES ('FO', 'Faroe Islands');
INSERT INTO `countries` VALUES ('FJ', 'Fiji');
INSERT INTO `countries` VALUES ('FI', 'Finland');
INSERT INTO `countries` VALUES ('FR', 'France');
INSERT INTO `countries` VALUES ('FX', 'France, Metropolitan');
INSERT INTO `countries` VALUES ('GF', 'French Guiana');
INSERT INTO `countries` VALUES ('PF', 'French Polynesia');
INSERT INTO `countries` VALUES ('TF', 'French Southern Territories');
INSERT INTO `countries` VALUES ('GA', 'Gabon');
INSERT INTO `countries` VALUES ('GM', 'Gambia');
INSERT INTO `countries` VALUES ('GE', 'Georgia');
INSERT INTO `countries` VALUES ('DE', 'Germany');
INSERT INTO `countries` VALUES ('GH', 'Ghana');
INSERT INTO `countries` VALUES ('GI', 'Gibraltar');
INSERT INTO `countries` VALUES ('GK', 'Guernsey');
INSERT INTO `countries` VALUES ('GR', 'Greece');
INSERT INTO `countries` VALUES ('GL', 'Greenland');
INSERT INTO `countries` VALUES ('GD', 'Grenada');
INSERT INTO `countries` VALUES ('GP', 'Guadeloupe');
INSERT INTO `countries` VALUES ('GU', 'Guam');
INSERT INTO `countries` VALUES ('GT', 'Guatemala');
INSERT INTO `countries` VALUES ('GN', 'Guinea');
INSERT INTO `countries` VALUES ('GW', 'Guinea-Bissau');
INSERT INTO `countries` VALUES ('GY', 'Guyana');
INSERT INTO `countries` VALUES ('HT', 'Haiti');
INSERT INTO `countries` VALUES ('HM', 'Heard and Mc Donald Islands');
INSERT INTO `countries` VALUES ('HN', 'Honduras');
INSERT INTO `countries` VALUES ('HK', 'Hong Kong');
INSERT INTO `countries` VALUES ('HU', 'Hungary');
INSERT INTO `countries` VALUES ('IS', 'Iceland');
INSERT INTO `countries` VALUES ('IN', 'India');
INSERT INTO `countries` VALUES ('IM', 'Isle of Man');
INSERT INTO `countries` VALUES ('ID', 'Indonesia');
INSERT INTO `countries` VALUES ('IR', 'Iran, Islamic Republic of');
INSERT INTO `countries` VALUES ('IQ', 'Iraq');
INSERT INTO `countries` VALUES ('IE', 'Ireland');
INSERT INTO `countries` VALUES ('IL', 'Israel');
INSERT INTO `countries` VALUES ('IT', 'Italy');
INSERT INTO `countries` VALUES ('CI', 'Ivory Coast');
INSERT INTO `countries` VALUES ('JE', 'Jersey');
INSERT INTO `countries` VALUES ('JM', 'Jamaica');
INSERT INTO `countries` VALUES ('JP', 'Japan');
INSERT INTO `countries` VALUES ('JO', 'Jordan');
INSERT INTO `countries` VALUES ('KZ', 'Kazakhstan');
INSERT INTO `countries` VALUES ('KE', 'Kenya');
INSERT INTO `countries` VALUES ('KI', 'Kiribati');
INSERT INTO `countries` VALUES ('KP', 'Korea, Democratic People''s Republic of');
INSERT INTO `countries` VALUES ('KR', 'Korea, Republic of');
INSERT INTO `countries` VALUES ('XK', 'Kosovo');
INSERT INTO `countries` VALUES ('KW', 'Kuwait');
INSERT INTO `countries` VALUES ('KG', 'Kyrgyzstan');
INSERT INTO `countries` VALUES ('LA', 'Lao People''s Democratic Republic');
INSERT INTO `countries` VALUES ('LV', 'Latvia');
INSERT INTO `countries` VALUES ('LB', 'Lebanon');
INSERT INTO `countries` VALUES ('LS', 'Lesotho');
INSERT INTO `countries` VALUES ('LR', 'Liberia');
INSERT INTO `countries` VALUES ('LY', 'Libyan Arab Jamahiriya');
INSERT INTO `countries` VALUES ('LI', 'Liechtenstein');
INSERT INTO `countries` VALUES ('LT', 'Lithuania');
INSERT INTO `countries` VALUES ('LU', 'Luxembourg');
INSERT INTO `countries` VALUES ('MO', 'Macau');
INSERT INTO `countries` VALUES ('MK', 'Macedonia');
INSERT INTO `countries` VALUES ('MG', 'Madagascar');
INSERT INTO `countries` VALUES ('MW', 'Malawi');
INSERT INTO `countries` VALUES ('MY', 'Malaysia');
INSERT INTO `countries` VALUES ('MV', 'Maldives');
INSERT INTO `countries` VALUES ('ML', 'Mali');
INSERT INTO `countries` VALUES ('MT', 'Malta');
INSERT INTO `countries` VALUES ('MH', 'Marshall Islands');
INSERT INTO `countries` VALUES ('MQ', 'Martinique');
INSERT INTO `countries` VALUES ('MR', 'Mauritania');
INSERT INTO `countries` VALUES ('MU', 'Mauritius');
INSERT INTO `countries` VALUES ('TY', 'Mayotte');
INSERT INTO `countries` VALUES ('MX', 'Mexico');
INSERT INTO `countries` VALUES ('FM', 'Micronesia, Federated States of');
INSERT INTO `countries` VALUES ('MD', 'Moldova, Republic of');
INSERT INTO `countries` VALUES ('MC', 'Monaco');
INSERT INTO `countries` VALUES ('MN', 'Mongolia');
INSERT INTO `countries` VALUES ('ME', 'Montenegro');
INSERT INTO `countries` VALUES ('MS', 'Montserrat');
INSERT INTO `countries` VALUES ('MA', 'Morocco');
INSERT INTO `countries` VALUES ('MZ', 'Mozambique');
INSERT INTO `countries` VALUES ('MM', 'Myanmar');
INSERT INTO `countries` VALUES ('NA', 'Namibia');
INSERT INTO `countries` VALUES ('NR', 'Nauru');
INSERT INTO `countries` VALUES ('NP', 'Nepal');
INSERT INTO `countries` VALUES ('NL', 'Netherlands');
INSERT INTO `countries` VALUES ('AN', 'Netherlands Antilles');
INSERT INTO `countries` VALUES ('NC', 'New Caledonia');
INSERT INTO `countries` VALUES ('NZ', 'New Zealand');
INSERT INTO `countries` VALUES ('NI', 'Nicaragua');
INSERT INTO `countries` VALUES ('NE', 'Niger');
INSERT INTO `countries` VALUES ('NG', 'Nigeria');
INSERT INTO `countries` VALUES ('NU', 'Niue');
INSERT INTO `countries` VALUES ('NF', 'Norfolk Island');
INSERT INTO `countries` VALUES ('MP', 'Northern Mariana Islands');
INSERT INTO `countries` VALUES ('NO', 'Norway');
INSERT INTO `countries` VALUES ('OM', 'Oman');
INSERT INTO `countries` VALUES ('PK', 'Pakistan');
INSERT INTO `countries` VALUES ('PW', 'Palau');
INSERT INTO `countries` VALUES ('PS', 'Palestine');
INSERT INTO `countries` VALUES ('PA', 'Panama');
INSERT INTO `countries` VALUES ('PG', 'Papua New Guinea');
INSERT INTO `countries` VALUES ('PY', 'Paraguay');
INSERT INTO `countries` VALUES ('PE', 'Peru');
INSERT INTO `countries` VALUES ('PH', 'Philippines');
INSERT INTO `countries` VALUES ('PN', 'Pitcairn');
INSERT INTO `countries` VALUES ('PL', 'Poland');
INSERT INTO `countries` VALUES ('PT', 'Portugal');
INSERT INTO `countries` VALUES ('PR', 'Puerto Rico');
INSERT INTO `countries` VALUES ('QA', 'Qatar');
INSERT INTO `countries` VALUES ('RE', 'Reunion');
INSERT INTO `countries` VALUES ('RO', 'Romania');
INSERT INTO `countries` VALUES ('RU', 'Russian Federation');
INSERT INTO `countries` VALUES ('RW', 'Rwanda');
INSERT INTO `countries` VALUES ('KN', 'Saint Kitts and Nevis');
INSERT INTO `countries` VALUES ('LC', 'Saint Lucia');
INSERT INTO `countries` VALUES ('VC', 'Saint Vincent and the Grenadines');
INSERT INTO `countries` VALUES ('WS', 'Samoa');
INSERT INTO `countries` VALUES ('SM', 'San Marino');
INSERT INTO `countries` VALUES ('ST', 'Sao Tome and Principe');
INSERT INTO `countries` VALUES ('SA', 'Saudi Arabia');
INSERT INTO `countries` VALUES ('SN', 'Senegal');
INSERT INTO `countries` VALUES ('RS', 'Serbia');
INSERT INTO `countries` VALUES ('SC', 'Seychelles');
INSERT INTO `countries` VALUES ('SL', 'Sierra Leone');
INSERT INTO `countries` VALUES ('SG', 'Singapore');
INSERT INTO `countries` VALUES ('SK', 'Slovakia');
INSERT INTO `countries` VALUES ('SI', 'Slovenia');
INSERT INTO `countries` VALUES ('SB', 'Solomon Islands');
INSERT INTO `countries` VALUES ('SO', 'Somalia');
INSERT INTO `countries` VALUES ('ZA', 'South Africa');
INSERT INTO `countries` VALUES ('GS', 'South Georgia South Sandwich Islands');
INSERT INTO `countries` VALUES ('ES', 'Spain');
INSERT INTO `countries` VALUES ('LK', 'Sri Lanka');
INSERT INTO `countries` VALUES ('SH', 'St. Helena');
INSERT INTO `countries` VALUES ('PM', 'St. Pierre and Miquelon');
INSERT INTO `countries` VALUES ('SD', 'Sudan');
INSERT INTO `countries` VALUES ('SR', 'Suriname');
INSERT INTO `countries` VALUES ('SJ', 'Svalbard and Jan Mayen Islands');
INSERT INTO `countries` VALUES ('SZ', 'Swaziland');
INSERT INTO `countries` VALUES ('SE', 'Sweden');
INSERT INTO `countries` VALUES ('CH', 'Switzerland');
INSERT INTO `countries` VALUES ('SY', 'Syrian Arab Republic');
INSERT INTO `countries` VALUES ('TW', 'Taiwan');
INSERT INTO `countries` VALUES ('TJ', 'Tajikistan');
INSERT INTO `countries` VALUES ('TZ', 'Tanzania, United Republic of');
INSERT INTO `countries` VALUES ('TH', 'Thailand');
INSERT INTO `countries` VALUES ('TG', 'Togo');
INSERT INTO `countries` VALUES ('TK', 'Tokelau');
INSERT INTO `countries` VALUES ('TO', 'Tonga');
INSERT INTO `countries` VALUES ('TT', 'Trinidad and Tobago');
INSERT INTO `countries` VALUES ('TN', 'Tunisia');
INSERT INTO `countries` VALUES ('TR', 'Turkey');
INSERT INTO `countries` VALUES ('TM', 'Turkmenistan');
INSERT INTO `countries` VALUES ('TC', 'Turks and Caicos Islands');
INSERT INTO `countries` VALUES ('TV', 'Tuvalu');
INSERT INTO `countries` VALUES ('UG', 'Uganda');
INSERT INTO `countries` VALUES ('UA', 'Ukraine');
INSERT INTO `countries` VALUES ('AE', 'United Arab Emirates');
INSERT INTO `countries` VALUES ('GB', 'United Kingdom');
INSERT INTO `countries` VALUES ('US', 'United States');
INSERT INTO `countries` VALUES ('UM', 'United States minor outlying islands');
INSERT INTO `countries` VALUES ('UY', 'Uruguay');
INSERT INTO `countries` VALUES ('UZ', 'Uzbekistan');
INSERT INTO `countries` VALUES ('VU', 'Vanuatu');
INSERT INTO `countries` VALUES ('VA', 'Vatican City State');
INSERT INTO `countries` VALUES ('VE', 'Venezuela');
INSERT INTO `countries` VALUES ('VN', 'Vietnam');
INSERT INTO `countries` VALUES ('VG', 'Virgin Islands (British)');
INSERT INTO `countries` VALUES ('VI', 'Virgin Islands (U.S.)');
INSERT INTO `countries` VALUES ('WF', 'Wallis and Futuna Islands');
INSERT INTO `countries` VALUES ('EH', 'Western Sahara');
INSERT INTO `countries` VALUES ('YE', 'Yemen');
INSERT INTO `countries` VALUES ('YU', 'Yugoslavia');
INSERT INTO `countries` VALUES ('ZR', 'Zaire');
INSERT INTO `countries` VALUES ('ZM', 'Zambia');
INSERT INTO `countries` VALUES ('ZW', 'Zimbabwe');

--
-- Inserts for table `distance_fares`
--

INSERT INTO `distance_fares` VALUES (1, 500, 1.10);
INSERT INTO `distance_fares` VALUES (501, 1000, 1.50);
INSERT INTO `distance_fares` VALUES (1001, 2500, 1.75);
INSERT INTO `distance_fares` VALUES (2501, 5000, 2.00);
INSERT INTO `distance_fares` VALUES (5001, 10000, 2.50);
INSERT INTO `distance_fares` VALUES (10001, 20000, 3.50);

--
-- Inserts for table `airports`
--

INSERT INTO `airports` VALUES ('YYZ', 'Toronto Pearson International Airport', 'Toronto', 'CA', 43.6777176, -79.6248196);
INSERT INTO `airports` VALUES ('SDQ', 'Las Americas International Airport', 'Santo Domingo', 'DO', 18.4302189, -69.6771733);
INSERT INTO `airports` VALUES ('BOG', 'El Dorado International Airport', 'Bogota', 'CO', 4.7009693, -74.1466885);
INSERT INTO `airports` VALUES ('DXB', 'Dubai International Airport', 'Dubai', 'AE', 25.2518578, 55.3649996);
INSERT INTO `airports` VALUES ('JFK', 'John F. Kennedy International Airport', 'New York', 'US', 40.6413286,-73.778342);
INSERT INTO `airports` VALUES ('LHR', 'Heathrow Airport', 'London', 'GB', 51.4702756,-0.4554869);

--
-- Inserts for table `planes`
--

INSERT INTO `planes` VALUES (1, 'C-FAAA','Boeing', '747', 1.0);
INSERT INTO `planes` VALUES (2, 'N1001A','Airbus', 'A320', 1.0);
INSERT INTO `planes` VALUES (3, 'EI-DYR','Boeing', '737', 1.0);
INSERT INTO `planes` VALUES (4, 'C-FZZZ','Embraer', '175', 1.0);

--
-- Inserts for table `flights`
--

INSERT INTO `flights` VALUES (NULL, 'FM1112', 'YYZ', 'SDQ', 1, '2023-03-30 10:30:00','2023-03-30 14:50:00');
INSERT INTO `flights` VALUES (NULL, 'FM1211', 'SDQ', 'YYZ', 1, '2023-03-30 18:30:00','2023-03-30 22:50:00');
INSERT INTO `flights` VALUES (NULL, 'FM1516', 'JFK', 'LHR', 3, '2023-03-31 06:10:00','2023-03-30 13:15:00');
INSERT INTO `flights` VALUES (NULL, 'FM1614', 'LHR', 'DXB', 2, '2023-03-31 09:00:00','2023-03-30 16:45:00');
INSERT INTO `flights` VALUES (NULL, 'FM1113', 'YYZ', 'BOG', 1, '2023-03-30 08:05:00','2023-03-30 14:25:00');
INSERT INTO `flights` VALUES (NULL, 'FM1511', 'JFK', 'YYZ', 4, '2023-03-30 14:00:00','2023-03-30 13:45:00');

--
-- Inserts for table `seats`
--

INSERT INTO `seats` VALUES (NULL, 1, 1, 'A', 1);
INSERT INTO `seats` VALUES (NULL, 1, 1, 'C', 1);
INSERT INTO `seats` VALUES (NULL, 1, 1, 'H', 1);
INSERT INTO `seats` VALUES (NULL, 1, 1, 'K', 1);
INSERT INTO `seats` VALUES (NULL, 1, 2, 'A', 1);
INSERT INTO `seats` VALUES (NULL, 1, 2, 'C', 1);
INSERT INTO `seats` VALUES (NULL, 1, 2, 'H', 1);
INSERT INTO `seats` VALUES (NULL, 1, 2, 'K', 1);
INSERT INTO `seats` VALUES (NULL, 1, 21, 'A', 2);
INSERT INTO `seats` VALUES (NULL, 1, 21, 'B', 2);
INSERT INTO `seats` VALUES (NULL, 1, 21, 'C', 2);
INSERT INTO `seats` VALUES (NULL, 1, 21, 'D', 2);
INSERT INTO `seats` VALUES (NULL, 1, 21, 'E', 2);
INSERT INTO `seats` VALUES (NULL, 1, 21, 'F', 2);
INSERT INTO `seats` VALUES (NULL, 1, 21, 'G', 2);
INSERT INTO `seats` VALUES (NULL, 1, 21, 'H', 2);
INSERT INTO `seats` VALUES (NULL, 1, 21, 'J', 2);
INSERT INTO `seats` VALUES (NULL, 1, 21, 'K', 2);
INSERT INTO `seats` VALUES (NULL, 1, 22, 'A', 2);
INSERT INTO `seats` VALUES (NULL, 1, 22, 'B', 2);
INSERT INTO `seats` VALUES (NULL, 1, 22, 'C', 2);
INSERT INTO `seats` VALUES (NULL, 1, 22, 'D', 2);
INSERT INTO `seats` VALUES (NULL, 1, 22, 'E', 2);
INSERT INTO `seats` VALUES (NULL, 1, 22, 'F', 2);
INSERT INTO `seats` VALUES (NULL, 1, 22, 'G', 2);
INSERT INTO `seats` VALUES (NULL, 1, 22, 'H', 2);
INSERT INTO `seats` VALUES (NULL, 1, 22, 'J', 2);
INSERT INTO `seats` VALUES (NULL, 1, 22, 'K', 2);
INSERT INTO `seats` VALUES (NULL, 1, 29, 'A', 3);
INSERT INTO `seats` VALUES (NULL, 1, 29, 'B', 3);
INSERT INTO `seats` VALUES (NULL, 1, 29, 'C', 3);
INSERT INTO `seats` VALUES (NULL, 1, 29, 'D', 3);
INSERT INTO `seats` VALUES (NULL, 1, 29, 'E', 3);
INSERT INTO `seats` VALUES (NULL, 1, 29, 'F', 3);
INSERT INTO `seats` VALUES (NULL, 1, 29, 'G', 3);
INSERT INTO `seats` VALUES (NULL, 1, 29, 'H', 3);
INSERT INTO `seats` VALUES (NULL, 1, 29, 'J', 3);
INSERT INTO `seats` VALUES (NULL, 1, 29, 'K', 3);
INSERT INTO `seats` VALUES (NULL, 1, 30, 'A', 3);
INSERT INTO `seats` VALUES (NULL, 1, 30, 'B', 3);
INSERT INTO `seats` VALUES (NULL, 1, 30, 'C', 3);
INSERT INTO `seats` VALUES (NULL, 1, 30, 'D', 3);
INSERT INTO `seats` VALUES (NULL, 1, 30, 'E', 3);
INSERT INTO `seats` VALUES (NULL, 1, 30, 'F', 3);
INSERT INTO `seats` VALUES (NULL, 1, 30, 'G', 3);
INSERT INTO `seats` VALUES (NULL, 1, 30, 'H', 3);
INSERT INTO `seats` VALUES (NULL, 1, 30, 'J', 3);
INSERT INTO `seats` VALUES (NULL, 1, 30, 'K', 3);

--
-- Inserts for table `services`
--

INSERT INTO `services` VALUES (1,'Meals');
INSERT INTO `services` VALUES (2,'Snacks');
INSERT INTO `services` VALUES (3,'Complementary Beverage');
INSERT INTO `services` VALUES (4,'Wi-Fi');
INSERT INTO `services` VALUES (5,'In-seat power outlet');
INSERT INTO `services` VALUES (6,'Media streaming');
INSERT INTO `services` VALUES (7,'Extra Comfort seating');
INSERT INTO `services` VALUES (8,'Sleep Amenities');
INSERT INTO `services` VALUES (9,'Separate boarding');

--
-- Inserts for table `plane_services`
--

INSERT INTO `plane_services` VALUES (1, 1, 1);
INSERT INTO `plane_services` VALUES (1, 1, 2);
INSERT INTO `plane_services` VALUES (1, 1, 3);
INSERT INTO `plane_services` VALUES (1, 1, 4);
INSERT INTO `plane_services` VALUES (1, 1, 5);
INSERT INTO `plane_services` VALUES (1, 1, 6);
INSERT INTO `plane_services` VALUES (1, 1, 7);
INSERT INTO `plane_services` VALUES (1, 1, 8);
INSERT INTO `plane_services` VALUES (1, 1, 9);
INSERT INTO `plane_services` VALUES (1, 2, 1);
INSERT INTO `plane_services` VALUES (1, 2, 2);
INSERT INTO `plane_services` VALUES (1, 2, 4);
INSERT INTO `plane_services` VALUES (1, 2, 5);
INSERT INTO `plane_services` VALUES (1, 2, 6);
INSERT INTO `plane_services` VALUES (1, 2, 7);
INSERT INTO `plane_services` VALUES (1, 3, 2);
INSERT INTO `plane_services` VALUES (1, 3, 4);
INSERT INTO `plane_services` VALUES (1, 3, 6);
