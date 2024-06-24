USE atletik;

-- //////////////////// Deltager //////////////////// --
INSERT INTO deltager (navn, køn, alder, klub) VALUES ('Markus Ingerslev', 'mand', 23, 'Stenløse Atletik');
INSERT INTO deltager (navn, køn, alder, klub) VALUES ('Mikkel Brygman', 'mand', 18, 'Væksø Atletik');
INSERT INTO deltager (navn, køn, alder, klub) VALUES ('Freja Debora', 'kvinde', 21, 'Ølstykke Atletik');

-- //////////////////// Disciplines //////////////////// --
INSERT INTO disciplin (navn, resultattype) VALUES ('Højdespring', 'meter');
INSERT INTO disciplin (navn, resultattype) VALUES ('Længdespring', 'meter');
INSERT INTO disciplin (navn, resultattype) VALUES ('400-meterløb', 'sekunder');
INSERT INTO disciplin (navn, resultattype) VALUES ('Hammerkast', 'meter');

-- //////////////////// Resultater //////////////////// --
INSERT INTO resultat (resultattype, dato, resultatvalue, deltager_id, disciplin_id) VALUES ('Højdespring', '2024-06-20', 2.03, 1, 1);
INSERT INTO resultat (resultattype, dato, resultatvalue, deltager_id, disciplin_id) VALUES ('Højdespring', '2021-06-20', 1.83, 2, 1);
INSERT INTO resultat (resultattype, dato, resultatvalue, deltager_id, disciplin_id) VALUES ('Højdespring', '2021-06-20', 1.96, 3, 1);

INSERT INTO resultat (resultattype, dato, resultatvalue, deltager_id, disciplin_id) VALUES ('Længdespring', '2021-06-20', 6.23, 1, 2);
INSERT INTO resultat (resultattype, dato, resultatvalue, deltager_id, disciplin_id) VALUES ('Længdespring', '2021-06-20', 5.65, 2, 2);
INSERT INTO resultat (resultattype, dato, resultatvalue, deltager_id, disciplin_id) VALUES ('Længdespring', '2021-06-20', 5.23, 3, 2);

INSERT INTO resultat (resultattype, dato, resultatvalue, deltager_id, disciplin_id) VALUES ('400-meterløb', '2021-06-20', 61.54, 1, 3);
INSERT INTO resultat (resultattype, dato, resultatvalue, deltager_id, disciplin_id) VALUES ('400-meterløb', '2021-06-20', 52.67, 2, 3);
INSERT INTO resultat (resultattype, dato, resultatvalue, deltager_id, disciplin_id) VALUES ('400-meterløb', '2021-06-20', 55.23, 3, 3);

INSERT INTO resultat (resultattype, dato, resultatvalue, deltager_id, disciplin_id) VALUES ('Hammerkast', '2021-06-20', 23.23, 1, 4);
INSERT INTO resultat (resultattype, dato, resultatvalue, deltager_id, disciplin_id) VALUES ('Hammerkast', '2021-06-20', 23.23, 2, 4);
INSERT INTO resultat (resultattype, dato, resultatvalue, deltager_id, disciplin_id) VALUES ('Hammerkast', '2021-06-20', 23.23, 3, 4);