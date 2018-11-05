-- delete data from Tables
DELETE FROM candidate_notes;
DELETE FROM candidate_skills;
DELETE FROM tag;
DELETE FROM candidate;

SELECT pg_catalog.setval(pg_get_serial_sequence('candidate_notes', 'id'), 1) FROM candidate_notes;
SELECT pg_catalog.setval(pg_get_serial_sequence('candidate_skills', 'id'), 1) FROM candidate_skills;
SELECT pg_catalog.setval(pg_get_serial_sequence('tag', 'id'), 1) FROM tag;
SELECT pg_catalog.setval(pg_get_serial_sequence('candidate', 'id'), 1) FROM candidate;

ALTER SEQUENCE candidate_id_seq RESTART WITH 1;
ALTER SEQUENCE tag_id_seq RESTART WITH 1;
ALTER SEQUENCE candidate_skills_id_seq RESTART WITH 1;
ALTER SEQUENCE candidate_notes_id_seq RESTART WITH 1;

-- candidate(first_name, last_name, email, event, phone, original_study_year, education_status, education_id, date_of_adding) table:
INSERT INTO candidate(first_name, last_name, email, event, phone, original_study_year, education_status, education_id, date_of_adding, check_candidate) VALUES ('Huberto','Moxon','hmoxon0@is.gd','conference','+40729 123 456',1,'master',1001,'2015-09-23 00:00:00', 'NOT_YET_VALIDATED');
INSERT INTO candidate(first_name, last_name, email, event, phone, original_study_year, education_status, education_id, date_of_adding, check_candidate) VALUES ('Dorisa','Jope','djope1@amazonaws.com','conference','+40729 123 456',3,'student',1001,'2016-10-25 00:00:00', 'NOT_YET_VALIDATED');
INSERT INTO candidate(first_name, last_name, email, event, phone, original_study_year, education_status, education_id, date_of_adding, check_candidate) VALUES ('Bernie','Ekins','bekins2@telegraph.co.uk','practice','+40729 123 456',4,'graduate',1001,'2013-09-15 00:00:00', 'NOT_YET_VALIDATED');
INSERT INTO candidate(first_name, last_name, email, event, phone, original_study_year, education_status, education_id, date_of_adding, check_candidate) VALUES ('Vida','Richens','vrichens3@si.edu','conference','+40729 123 456',5,'student',1002,'2016-10-12 00:00:00', 'NOT_YET_VALIDATED');
INSERT INTO candidate(first_name, last_name, email, event, phone, original_study_year, education_status, education_id, date_of_adding, check_candidate) VALUES ('Amelia','Whitten','awhitten4@mtv.com','internship','+40729 123 456',1,'graduate',1011,'2016-10-20 00:00:00', 'NOT_YET_VALIDATED');
INSERT INTO candidate(first_name, last_name, email, event, phone, original_study_year, education_status, education_id, date_of_adding, check_candidate) VALUES ('Puff','Tapper','ptapper5@tiny.cc','internship','+40729 123 456',1,'graduate',1002,'2015-03-23 00:00:00', 'NOT_YET_VALIDATED');
INSERT INTO candidate(first_name, last_name, email, event, phone, original_study_year, education_status, education_id, date_of_adding, check_candidate) VALUES ('Cris','Vigers','cvigers6@yahoo.co.jp','internship','+40729 123 456',3,'master',1002,'2017-02-22 00:00:00', 'NOT_YET_VALIDATED');
INSERT INTO candidate(first_name, last_name, email, event, phone, original_study_year, education_status, education_id, date_of_adding, check_candidate) VALUES ('Madelin','Woodley','mwoodley7@weibo.com','practice','+40729 123 456',4,'master',1011,'2013-05-01 00:00:00', 'NOT_YET_VALIDATED');
INSERT INTO candidate(first_name, last_name, email, event, phone, original_study_year, education_status, education_id, date_of_adding, check_candidate) VALUES ('Jeralee','Eakly','jeakly8@bandcamp.com','conference','+40729 123 456',5,'freelancer',1012,'2014-10-02 00:00:00', 'NOT_YET_VALIDATED');
INSERT INTO candidate(first_name, last_name, email, event, phone, original_study_year, education_status, education_id, date_of_adding, check_candidate) VALUES ('Andy','Bickle','abickle9@vistaprint.com','conference','+40729 123 456',1,'graduate',1002,'2015-10-07 00:00:00', 'NOT_YET_VALIDATED');
INSERT INTO candidate(first_name, last_name, email, event, phone, original_study_year, education_status, education_id, date_of_adding, check_candidate) VALUES ('Anne-marie','Mounsey','amounseya@tinyurl.com','internship','+40729 123 456',1,'freelancer',1002,'2014-11-23 00:00:00', 'NOT_YET_VALIDATED');
INSERT INTO candidate(first_name, last_name, email, event, phone, original_study_year, education_status, education_id, date_of_adding, check_candidate) VALUES ('Anne','Hindmore','anne@tinyurl.com','internship','+40729 123 456',1,'freelancer',1002,'2015-10-23 00:00:00', 'NOT_YET_VALIDATED');
INSERT INTO candidate(first_name, last_name, email, event, phone, original_study_year, education_status, education_id, date_of_adding, check_candidate) VALUES ('Audrey','Hindmore','ahindmorec@freewebs.com','practice','+40729 123 456',4,'freelancer',1002,'2015-11-15 00:00:00', 'NOT_YET_VALIDATED');
INSERT INTO candidate(first_name, last_name, email, event, phone, original_study_year, education_status, education_id, date_of_adding, check_candidate) VALUES ('Judi','Colling','jcollingd@123-reg.co.uk','conference','+40729 123 456',5,'graduate',1011,'2015-11-17 00:00:00', 'NOT_YET_VALIDATED');
INSERT INTO candidate(first_name, last_name, email, event, phone, original_study_year, education_status, education_id, date_of_adding, check_candidate) VALUES ('Marjory','Dunning','mdunninge@chicagotribune.com','internship','+40729 123 456',1,'freelancer',1012,'2015-09-23 00:00:00', 'NOT_YET_VALIDATED');
INSERT INTO candidate(first_name, last_name, email, event, phone, original_study_year, education_status, education_id, date_of_adding, check_candidate) VALUES ('Lavena','Stelli','lstellif@wikispaces.com','conference','+40729 123 456',2,'freelancer',1002,'2013-09-05 00:00:00', 'NOT_YET_VALIDATED');
INSERT INTO candidate(first_name, last_name, email, event, phone, original_study_year, education_status, education_id, date_of_adding, check_candidate) VALUES ('Charleen','Greatex','cgreatexg@lulu.com','conference','+40729 123 456',3,'graduate',1011,'2014-01-29 00:00:00', 'NOT_YET_VALIDATED');
INSERT INTO candidate(first_name, last_name, email, event, phone, original_study_year, education_status, education_id, date_of_adding, check_candidate) VALUES ('Helenelizabeth','Gatecliff','hgatecliffh@time.com','practice','+40729 123 456',4,'freelancer',1012,'2016-08-15 00:00:00', 'NOT_YET_VALIDATED');
INSERT INTO candidate(first_name, last_name, email, event, phone, original_study_year, education_status, education_id, date_of_adding, check_candidate) VALUES ('Ag','Simison','asimisoni@shutterfly.com','internship','+40729 123 456',5,'master',1002,'2017-04-01 00:00:00', 'NOT_YET_VALIDATED');
INSERT INTO candidate(first_name, last_name, email, event, phone, original_study_year, education_status, education_id, date_of_adding, check_candidate) VALUES ('Sydney','Parslow','sparslowj@un.org','practice','+40729 123 456',1,'graduate',1011,'2013-08-30 00:00:00', 'NOT_YET_VALIDATED');
INSERT INTO candidate(first_name, last_name, email, event, phone, original_study_year, education_status, education_id, date_of_adding, check_candidate) VALUES ('Anjela','Scaife','ascaifek@abc.net.au','conference','+40729 123 456',2,'master',1012,'2017-01-02 00:00:00', 'NOT_YET_VALIDATED');
INSERT INTO candidate(first_name, last_name, email, event, phone, original_study_year, education_status, education_id, date_of_adding, check_candidate) VALUES ('Edith','Pfeuffer','epfeufferl@dell.com','conference','+40729 123 456',3,'master',1002,'2014-10-10 00:00:00', 'NOT_YET_VALIDATED');
INSERT INTO candidate(first_name, last_name, email, event, phone, original_study_year, education_status, education_id, date_of_adding, check_candidate) VALUES ('Mureil','Eannetta','meannettam@apple.com','conference','+40729 123 456',4,'graduate',1011,'2017-02-09 00:00:00', 'NOT_YET_VALIDATED');
INSERT INTO candidate(first_name, last_name, email, event, phone, original_study_year, education_status, education_id, date_of_adding, check_candidate) VALUES ('Hatty','Ferrino','hferrinon@friendfeed.com','practice','+40729 123 456',5,'student',1012,'2013-06-22 00:00:00', 'NOT_YET_VALIDATED');
INSERT INTO candidate(first_name, last_name, email, event, phone, original_study_year, education_status, education_id, date_of_adding, check_candidate) VALUES ('Ernest','Doni','edonio@51.la','practice','+40729 123 456',1,'master',1002,'2013-01-22 00:00:00', 'NOT_YET_VALIDATED');
INSERT INTO candidate(first_name, last_name, email, event, phone, original_study_year, education_status, education_id, date_of_adding, check_candidate) VALUES ('Joan','Baldini','jbaldinip@soup.io','internship','+40729 123 456',2,'graduate',1011,'2014-05-02 00:00:00', 'NOT_YET_VALIDATED');
INSERT INTO candidate(first_name, last_name, email, event, phone, original_study_year, education_status, education_id, date_of_adding, check_candidate) VALUES ('Janifer','MacPike','jmacpikeq@dyndns.org','practice','+40729 123 456',3,'student',1012,'2015-02-18 00:00:00', 'NOT_YET_VALIDATED');
INSERT INTO candidate(first_name, last_name, email, event, phone, original_study_year, education_status, education_id, date_of_adding, check_candidate) VALUES ('Baudoin','Bassett','bbassettr@smugmug.com','conference','+40729 123 456',4,'freelancer',1002,'2015-07-06 00:00:00', 'NOT_YET_VALIDATED');
INSERT INTO candidate(first_name, last_name, email, event, phone, original_study_year, education_status, education_id, date_of_adding, check_candidate) VALUES ('Jackson','Haibel','jhaibels@domainmarket.com','practice','+40729 123 456',5,'student',1011,'2015-06-20 00:00:00', 'NOT_YET_VALIDATED');
INSERT INTO candidate(first_name, last_name, email, event, phone, original_study_year, education_status, education_id, date_of_adding, check_candidate) VALUES ('Virgie','Trunchion','vtrunchiont@geocities.com','internship','+40729 123 456',1,'freelancer',1012,'2017-03-30 00:00:00', 'NOT_YET_VALIDATED');
INSERT INTO candidate(first_name, last_name, email, event, phone, original_study_year, education_status, education_id, date_of_adding, check_candidate) VALUES ('Ardelis','Symones','asymonesu@wordpress.com','practice','+40729 123 456',2,'freelancer',1002,'2013-03-06 00:00:00', 'NOT_YET_VALIDATED');
INSERT INTO candidate(first_name, last_name, email, event, phone, original_study_year, education_status, education_id, date_of_adding, check_candidate) VALUES ('Ozzy','Ruprechter','oruprechterv@youtube.com','conference','+40729 123 456',3,'student',1011,'2014-04-07 00:00:00', 'VALIDATED');
INSERT INTO candidate(first_name, last_name, email, event, phone, original_study_year, education_status, education_id, date_of_adding, check_candidate) VALUES ('Aggy','Eisikovitsh','aeisikovitshw@homestead.com','internship','+40729 123 456',4,'student',1012,'2016-06-12 00:00:00', 'VALIDATED');
INSERT INTO candidate(first_name, last_name, email, event, phone, original_study_year, education_status, education_id, date_of_adding, check_candidate) VALUES ('Vito','Charleston','vcharlestonx@skyrock.com','conference','+40729 123 456',5,'graduate',1002,'2014-06-02 00:00:00', 'VALIDATED');
INSERT INTO candidate(first_name, last_name, email, event, phone, original_study_year, education_status, education_id, date_of_adding, check_candidate) VALUES ('Averil','Scaice','ascaicey@springer.com','internship','0040723 10 10 10',1,'student',1011,'2017-03-11 00:00:00', 'VALIDATED');
INSERT INTO candidate(first_name, last_name, email, event, phone, original_study_year, education_status, education_id, date_of_adding, check_candidate) VALUES ('Ava','Candlish','acandlishz@friendfeed.com','conference','0040723111111',2,'student',1012,'2014-10-26 00:00:00', 'VALIDATED');
INSERT INTO candidate(first_name, last_name, email, event, phone, original_study_year, education_status, education_id, date_of_adding, check_candidate) VALUES ('Nealson','Jacox','njacox10@va.gov','internship','0040723111111',3,'master',1002,'2016-05-21 00:00:00', 'VALIDATED');
INSERT INTO candidate(first_name, last_name, email, event, phone, original_study_year, education_status, education_id, date_of_adding, check_candidate) VALUES ('Tallulah','Simoneschi','tsimoneschi11@mozilla.com','internship','0040723111111',4,'graduate',1011,'2014-02-04 00:00:00', 'VALIDATED');
INSERT INTO candidate(first_name, last_name, email, event, phone, original_study_year, education_status, education_id, date_of_adding, check_candidate) VALUES ('Brianne','Crat','bcrat12@unicef.org','conference','0040723111111',5,'student',1012,'2014-06-22 00:00:00', 'VALIDATED');
INSERT INTO candidate(first_name, last_name, email, event, phone, original_study_year, education_status, education_id, date_of_adding, check_candidate) VALUES ('Lonnie','McKernon','lmckernon13@shareasale.com','practice','0040723111111',1,'graduate',1002,'2014-01-24 00:00:00', 'VALIDATED');
INSERT INTO candidate(first_name, last_name, email, event, phone, original_study_year, education_status, education_id, date_of_adding, check_candidate) VALUES ('Kendall','Frith','kfrith14@e-recht24.de','internship','0040723111111',2,'master',1011,'2013-11-16 00:00:00', 'VALIDATED');
INSERT INTO candidate(first_name, last_name, email, event, phone, original_study_year, education_status, education_id, date_of_adding, check_candidate) VALUES ('Janela','Sharpley','jsharpley15@google.de','conference','0040723111111',3,'graduate',1012,'2017-02-17 00:00:00', 'VALIDATED');
INSERT INTO candidate(first_name, last_name, email, event, phone, original_study_year, education_status, education_id, date_of_adding, check_candidate) VALUES ('Dorie','Ballendine','dballendine16@state.tx.us','conference','0040723111111',4,'graduate',1002,'2016-11-26 00:00:00', 'VALIDATED');
INSERT INTO candidate(first_name, last_name, email, event, phone, original_study_year, education_status, education_id, date_of_adding, check_candidate) VALUES ('Silvain','Bellis','sbellis17@indiatimes.com','internship','0040723111111',5,'master',1011,'2013-12-11 00:00:00', 'VALIDATED');
INSERT INTO candidate(first_name, last_name, email, event, phone, original_study_year, education_status, education_id, date_of_adding, check_candidate) VALUES ('Dena','Kadwallider','dkadwallider18@amazonaws.com','internship','0040723111111',1,'freelancer',1012,'2016-12-13 00:00:00', 'VALIDATED');
INSERT INTO candidate(first_name, last_name, email, event, phone, original_study_year, education_status, education_id, date_of_adding, check_candidate) VALUES ('Dame','Prise','dprise19@mtv.com','practice','0040723111111',2,'freelancer',1002,'2016-06-03 00:00:00', 'VALIDATED');
INSERT INTO candidate(first_name, last_name, email, event, phone, original_study_year, education_status, education_id, date_of_adding, check_candidate) VALUES ('Devon','Woltman','dwoltman1a@wunderground.com','conference','0040723111111',3,'freelancer',1011,'2015-05-05 00:00:00', 'VALIDATED');
INSERT INTO candidate(first_name, last_name, email, event, phone, original_study_year, education_status, education_id, date_of_adding, check_candidate) VALUES ('Lilla','Hawkswood','lhawkswood1b@desdev.cn','internship','0040723 11 11 11',4,'graduate',1012,'2016-11-24 00:00:00', 'VALIDATED');
INSERT INTO candidate(first_name, last_name, email, event, phone, original_study_year, education_status, education_id, date_of_adding, check_candidate) VALUES ('Kristopher','Sooper','ksooper1c@lulu.com','practice','0040723 111 111',5,'master',1002,'2015-04-19 00:00:00', 'VALIDATED');
INSERT INTO candidate(first_name, last_name, email, event, phone, original_study_year, education_status, education_id, date_of_adding, check_candidate) VALUES ('Vaughan','Huniwall','vhuniwall1d@plala.or.jp','practice','0040723 111111',1,'master',1011,'2015-03-10 00:00:00', 'VALIDATED');
INSERT INTO candidate(first_name, last_name, email, event, phone, original_study_year, education_status, education_id, date_of_adding, check_candidate) VALUES ('Cassy','Larby','clarby1e@state.tx.us','internship','0040723 111111',2,'graduate',1012,'2016-08-28 00:00:00', 'VALIDATED');
INSERT INTO candidate(first_name, last_name, email, event, phone, original_study_year, education_status, education_id, date_of_adding, check_candidate) VALUES ('Pegeen','Keele','pkeele1f@merriam-webster.com','internship','0040723 111 111',3,'master',1002,'2017-05-26 00:00:00', 'VALIDATED');
INSERT INTO candidate(first_name, last_name, email, event, phone, original_study_year, education_status, education_id, date_of_adding, check_candidate) VALUES ('Aindrea','Baudinet','abaudinet1g@npr.org','conference','0040723111 111',4,'master',1011,'2013-01-22 00:00:00', 'VALIDATED');
INSERT INTO candidate(first_name, last_name, email, event, phone, original_study_year, education_status, education_id, date_of_adding, check_candidate) VALUES ('Simon','Brealey','sbrealey1h@dailymail.co.uk','internship','0264111111',5,'master',1012,'2013-08-28 00:00:00', 'VALIDATED');
INSERT INTO candidate(first_name, last_name, email, event, phone, original_study_year, education_status, education_id, date_of_adding, check_candidate) VALUES ('Phoebe','Gebbie','pgebbie1i@sina.com.cn','conference','0264111111',1,'freelancer',1002,'2016-12-21 00:00:00', 'VALIDATED');
INSERT INTO candidate(first_name, last_name, email, event, phone, original_study_year, education_status, education_id, date_of_adding, check_candidate) VALUES ('Lucila','Scibsey','lscibsey1j@buzzfeed.com','internship','0264111111',2,'graduate',1011,'2016-04-13 00:00:00', 'VALIDATED');
INSERT INTO candidate(first_name, last_name, email, event, phone, original_study_year, education_status, education_id, date_of_adding, check_candidate) VALUES ('Opalina','Vinnick','ovinnick1k@prweb.com','conference','0264111111',3,'graduate',1012,'2016-06-04 00:00:00', 'VALIDATED');
INSERT INTO candidate(first_name, last_name, email, event, phone, original_study_year, education_status, education_id, date_of_adding, check_candidate) VALUES ('Melisse','Mauchline','mmauchline1l@biglobe.ne.jp','practice','0264111111',4,'student',1002,'2016-02-08 00:00:00', 'VALIDATED');
INSERT INTO candidate(first_name, last_name, email, event, phone, original_study_year, education_status, education_id, date_of_adding, check_candidate) VALUES ('Gan','Gutridge','ggutridge1m@cisco.com','practice','0040723110011',5,'master',1011,'2014-06-11 00:00:00', 'VALIDATED');
INSERT INTO candidate(first_name, last_name, email, event, phone, original_study_year, education_status, education_id, date_of_adding, check_candidate) VALUES ('Regan','Yewdall','ryewdall1n@ed.gov','practice','0040723110011',1,'freelancer',1012,'2014-08-04 00:00:00', 'VALIDATED');
INSERT INTO candidate(first_name, last_name, email, event, phone, original_study_year, education_status, education_id, date_of_adding, check_candidate) VALUES ('Myrtice','Pavluk','mpavluk1o@yellowpages.com','internship','0040723110011',2,'student',1002,'2017-04-17 00:00:00', 'VALIDATED');
INSERT INTO candidate(first_name, last_name, email, event, phone, original_study_year, education_status, education_id, date_of_adding, check_candidate) VALUES ('Jere','Yoell','jyoell1p@nbcnews.com','conference','0040723110011',3,'graduate',1011,'2013-01-30 00:00:00', 'VALIDATED');
INSERT INTO candidate(first_name, last_name, email, event, phone, original_study_year, education_status, education_id, date_of_adding, check_candidate) VALUES ('Rickey','Atthow','ratthow1q@walmart.com','internship','0040723110011',4,'master',1012,'2015-01-23 00:00:00', 'VALIDATED');
INSERT INTO candidate(first_name, last_name, email, event, phone, original_study_year, education_status, education_id, date_of_adding, check_candidate) VALUES ('Nahum','Acres','nacres1r@freewebs.com','practice','0040723110011',5,'student',1002,'2013-02-25 00:00:00', 'VALIDATED');
INSERT INTO candidate(first_name, last_name, email, event, phone, original_study_year, education_status, education_id, date_of_adding, check_candidate) VALUES ('Reinold','Pogosian','rpogosian1s@tmall.com','conference','0040723110011',1,'master',1011,'2013-04-30 00:00:00', 'VALIDATED');
INSERT INTO candidate(first_name, last_name, email, event, phone, original_study_year, education_status, education_id, date_of_adding, check_candidate) VALUES ('Alethea','Klimentyev','aklimentyev1t@bandcamp.com','practice','0040723110011',2,'student',1012,'2014-05-12 00:00:00', 'VALIDATED');
INSERT INTO candidate(first_name, last_name, email, event, phone, original_study_year, education_status, education_id, date_of_adding, check_candidate) VALUES ('Odella','Arrington','oarrington1u@nymag.com','practice','0040723110011',3,'graduate',1002,'2013-04-14 00:00:00', 'VALIDATED');
INSERT INTO candidate(first_name, last_name, email, event, phone, original_study_year, education_status, education_id, date_of_adding, check_candidate) VALUES ('Fairfax','Andrault','fandrault1v@usda.gov','conference','0040723110011',4,'freelancer',1011,'2013-02-15 00:00:00', 'VALIDATED');
INSERT INTO candidate(first_name, last_name, email, event, phone, original_study_year, education_status, education_id, date_of_adding, check_candidate) VALUES ('Danit','Kas','dkas1w@nih.gov','practice','0040723110011',5,'graduate',1012,'2016-06-07 00:00:00', 'VALIDATED');
INSERT INTO candidate(first_name, last_name, email, event, phone, original_study_year, education_status, education_id, date_of_adding, check_candidate) VALUES ('Jemimah','Bodiam','jbodiam1x@tinypic.com','internship','0040723110011',1,'graduate',1002,'2015-06-14 00:00:00', 'VALIDATED');
INSERT INTO candidate(first_name, last_name, email, event, phone, original_study_year, education_status, education_id, date_of_adding, check_candidate) VALUES ('Bayard','Bambra','bbambra1y@who.int','conference','0040723110011',2,'student',1011,'2014-05-23 00:00:00', 'VALIDATED');
INSERT INTO candidate(first_name, last_name, email, event, phone, original_study_year, education_status, education_id, date_of_adding, check_candidate) VALUES ('Jess','Snelson','jsnelson1z@foxnews.com','internship','0040723110011',3,'master',1012,'2015-01-31 00:00:00', 'VALIDATED');
INSERT INTO candidate(first_name, last_name, email, event, phone, original_study_year, education_status, education_id, date_of_adding, check_candidate) VALUES ('Florella','Schild','fschild20@icq.com','conference','0040723110011',4,'graduate',1002,'2013-04-26 00:00:00', 'VALIDATED');
INSERT INTO candidate(first_name, last_name, email, event, phone, original_study_year, education_status, education_id, date_of_adding, check_candidate) VALUES ('Rozina','Blundel','rblundel21@gnu.org','internship','0040723110011',5,'student',1011,'2013-03-05 00:00:00', 'VALIDATED');
INSERT INTO candidate(first_name, last_name, email, event, phone, original_study_year, education_status, education_id, date_of_adding, check_candidate) VALUES ('Maryellen','Asher','masher22@blogger.com','practice','0040723110011',1,'master',1012,'2013-11-02 00:00:00', 'VALIDATED');
INSERT INTO candidate(first_name, last_name, email, event, phone, original_study_year, education_status, education_id, date_of_adding, check_candidate) VALUES ('Nicolle','Medley','nmedley23@ucsd.edu','conference','0040723110011',2,'graduate',1002,'2013-07-26 00:00:00', 'VALIDATED');
INSERT INTO candidate(first_name, last_name, email, event, phone, original_study_year, education_status, education_id, date_of_adding, check_candidate) VALUES ('Calla','Lamberto','clamberto24@comcast.net','practice','0040723110011',3,'student',1011,'2013-12-10 00:00:00', 'VALIDATED');
INSERT INTO candidate(first_name, last_name, email, event, phone, original_study_year, education_status, education_id, date_of_adding, check_candidate) VALUES ('Ranice','Bickerstasse','rbickerstasse25@hc360.com','internship','0040723110011',4,'student',1012,'2015-06-17 00:00:00', 'VALIDATED');
INSERT INTO candidate(first_name, last_name, email, event, phone, original_study_year, education_status, education_id, date_of_adding, check_candidate) VALUES ('Ericka','Josefer','ejosefer26@ezinearticles.com','practice','0040723110011',5,'student',1002,'2015-03-28 00:00:00', 'VALIDATED');
INSERT INTO candidate(first_name, last_name, email, event, phone, original_study_year, education_status, education_id, date_of_adding, check_candidate) VALUES ('Christophorus','Ludgrove','cludgrove27@facebook.com','conference','0040723110011',1,'freelancer',1011,'2016-05-24 00:00:00', 'VALIDATED');
INSERT INTO candidate(first_name, last_name, email, event, phone, original_study_year, education_status, education_id, date_of_adding, check_candidate) VALUES ('Yehudit','Willowby','ywillowby28@nifty.com','conference','0040723110011',2,'student',1012,'2013-08-18 00:00:00', 'VALIDATED');
INSERT INTO candidate(first_name, last_name, email, event, phone, original_study_year, education_status, education_id, date_of_adding, check_candidate) VALUES ('Jedd','Murkitt','jmurkitt29@cbc.ca','conference','0040723110011',3,'graduate',1002,'2016-12-25 00:00:00', 'VALIDATED');
INSERT INTO candidate(first_name, last_name, email, event, phone, original_study_year, education_status, education_id, date_of_adding, check_candidate) VALUES ('Dode','Raisbeck','draisbeck2a@sakura.ne.jp','conference','0040723110011',4,'graduate',1011,'2015-07-02 00:00:00', 'VALIDATED');
INSERT INTO candidate(first_name, last_name, email, event, phone, original_study_year, education_status, education_id, date_of_adding, check_candidate) VALUES ('Ned','Emmot','nemmot2b@cdbaby.com','conference','0040723110011',5,'graduate',1012,'2016-09-07 00:00:00', 'VALIDATED');
INSERT INTO candidate(first_name, last_name, email, event, phone, original_study_year, education_status, education_id, date_of_adding, check_candidate) VALUES ('Maxi','Harcourt','mharcourt2c@instagram.com','conference','0040723110011',1,'freelancer',1002,'2013-06-18 00:00:00', 'VALIDATED');
INSERT INTO candidate(first_name, last_name, email, event, phone, original_study_year, education_status, education_id, date_of_adding, check_candidate) VALUES ('Drud','Hansom','dhansom2d@noaa.gov','conference','0040723110011',2,'student',1011,'2015-02-08 00:00:00', 'VALIDATED');
INSERT INTO candidate(first_name, last_name, email, event, phone, original_study_year, education_status, education_id, date_of_adding, check_candidate) VALUES ('Lucilia','Trassler','ltrassler2e@unc.edu','conference','0040723110011',3,'student',1012,'2016-04-17 00:00:00', 'VALIDATED');
INSERT INTO candidate(first_name, last_name, email, event, phone, original_study_year, education_status, education_id, date_of_adding, check_candidate) VALUES ('Raine','Tite','rtite2f@xrea.com','conference','0040723110011',4,'freelancer',1002,'2016-12-07 00:00:00', 'VALIDATED');
INSERT INTO candidate(first_name, last_name, email, event, phone, original_study_year, education_status, education_id, date_of_adding, check_candidate) VALUES ('Ross','Braz','rbraz2g@angelfire.com','internship','0040723110011',5,'master',1011,'2014-05-16 00:00:00', 'VALIDATED');
INSERT INTO candidate(first_name, last_name, email, event, phone, original_study_year, education_status, education_id, date_of_adding, check_candidate) VALUES ('Nelle','Hagergham','nhagergham2h@accuweather.com','practice','0040723110011',1,'freelancer',1012,'2013-04-17 00:00:00', 'VALIDATED');
INSERT INTO candidate(first_name, last_name, email, event, phone, original_study_year, education_status, education_id, date_of_adding, check_candidate) VALUES ('Ambrosius','Joannet','ajoannet2i@dailymail.co.uk','internship','0040723110011',2,'freelancer',1002,'2015-02-18 00:00:00', 'VALIDATED');
INSERT INTO candidate(first_name, last_name, email, event, phone, original_study_year, education_status, education_id, date_of_adding, check_candidate) VALUES ('Inger','Hurst','ihurst2j@sciencedirect.com','internship','0040723110011',3,'freelancer',1011,'2016-06-15 00:00:00', 'VALIDATED');
INSERT INTO candidate(first_name, last_name, email, event, phone, original_study_year, education_status, education_id, date_of_adding, check_candidate) VALUES ('Shanon','Govier','sgovier2k@nsw.gov.au','conference','0040723 11 00 11',4,'student',1012,'2014-03-02 00:00:00', 'VALIDATED');
INSERT INTO candidate(first_name, last_name, email, event, phone, original_study_year, education_status, education_id, date_of_adding, check_candidate) VALUES ('Una','Stops','ustops2l@xinhuanet.com','practice','004072311 00 11',5,'master',1002,'2013-08-11 00:00:00', 'VALIDATED');
INSERT INTO candidate(first_name, last_name, email, event, phone, original_study_year, education_status, education_id, date_of_adding, check_candidate) VALUES ('Halley','Flatley','hflatley2m@networkadvertising.org','internship','004072311 0011',1,'freelancer',1011,'2013-08-22 00:00:00', 'VALIDATED');
INSERT INTO candidate(first_name, last_name, email, event, phone, original_study_year, education_status, education_id, date_of_adding, check_candidate) VALUES ('Farly','Killick','fkillick2n@blogspot.com','conference','004 0723 11 00 11',2,'graduate',1012,'2016-09-25 00:00:00', 'VALIDATED');
INSERT INTO candidate(first_name, last_name, email, event, phone, original_study_year, education_status, education_id, date_of_adding, check_candidate) VALUES ('Cami','Henner','chenner2o@hao123.com','internship','004 0723 110 011',3,'freelancer',1002,'2017-05-15 00:00:00', 'VALIDATED');
INSERT INTO candidate(first_name, last_name, email, event, phone, original_study_year, education_status, education_id, date_of_adding, check_candidate) VALUES ('Elli','Spriggin','espriggin2p@feedburner.com','conference','004 0723 110011',4,'graduate',1011,'2014-03-08 00:00:00', 'VALIDATED');
INSERT INTO candidate(first_name, last_name, email, event, phone, original_study_year, education_status, education_id, date_of_adding, check_candidate) VALUES ('Wolfy','Thring','wthring2q@netscape.com','internship','0040723 11 11 11',5,'freelancer',1012,'2017-06-16 00:00:00', 'VALIDATED');
INSERT INTO candidate(first_name, last_name, email, event, phone, original_study_year, education_status, education_id, date_of_adding, check_candidate) VALUES ('Rafaelia','Rallings','rrallings2r@tinypic.com','conference','0040723 111 111',1,'graduate',1002,'2016-12-14 00:00:00', 'VALIDATED');

INSERT INTO tag(description, tag_type) VALUES('german', 'FOREIGN_LANGUAGE');

INSERT INTO candidate_skills(certifier, rating, candidate_id, tag_id) VALUES('certif', '3', 1, 1);
INSERT INTO candidate_skills(certifier, rating, candidate_id, tag_id) VALUES('certif', '3', 2, 1);
INSERT INTO candidate_skills(certifier, rating, candidate_id, tag_id) VALUES('certif', '3', 3, 1);
INSERT INTO candidate_skills(certifier, rating, candidate_id, tag_id) VALUES('certif', '3', 4, 1);
INSERT INTO candidate_skills(certifier, rating, candidate_id, tag_id) VALUES('certif', '3', 34, 1);
INSERT INTO candidate_skills(certifier, rating, candidate_id, tag_id) VALUES('certif', '3', 35, 1);
INSERT INTO candidate_skills(certifier, rating, candidate_id, tag_id) VALUES('certif', '3', 36, 1);
INSERT INTO candidate_skills(certifier, rating, candidate_id, tag_id) VALUES('certif', '3', 37, 1);