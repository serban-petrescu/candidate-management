--delete data from Tables
DELETE FROM CANDIDATE_NOTES;
DELETE FROM CANDIDATE_SKILLS;
DELETE FROM TAG;
DELETE FROM CANDIDATE;
DELETE FROM EDUCATION;

ALTER TABLE CANDIDATE_NOTES AUTO_INCREMENT = 1;
ALTER TABLE CANDIDATE_SKILLS AUTO_INCREMENT = 1;
ALTER TABLE TAG AUTO_INCREMENT = 1;
ALTER TABLE CANDIDATE AUTO_INCREMENT = 1;
ALTER TABLE EDUCATION AUTO_INCREMENT = 1;

-- EDUCATION(description, duration, education_type, provider) table:
INSERT INTO EDUCATION(description, duration, education_type, provider) VALUES ('Informatics',3,'High-School','Marie Curie');
INSERT INTO EDUCATION(description, duration, education_type, provider) VALUES ('Mathematics-Informatics',4,'Bachelor','UBB');
INSERT INTO EDUCATION(description, duration, education_type, provider) VALUES ('Information Technology',2,'Master','UTCN');
INSERT INTO EDUCATION(description, duration, education_type, provider) VALUES ('Information Technology',2,'Master','UTCN');
INSERT INTO EDUCATION(description, duration, education_type, provider) VALUES ('Informatics',3,'High-School','Marie Curie');
INSERT INTO EDUCATION(description, duration, education_type, provider) VALUES ('Mathematics-Informatics',4,'Bachelor','UBB');
INSERT INTO EDUCATION(description, duration, education_type, provider) VALUES ('Information Technology',2,'Master','UTCN');
INSERT INTO EDUCATION(description, duration, education_type, provider) VALUES ('Information Technology',2,'Master','UTCN');
INSERT INTO EDUCATION(description, duration, education_type, provider) VALUES ('Informatics',3,'High-School','Marie Curie');
INSERT INTO EDUCATION(description, duration, education_type, provider) VALUES ('Mathematics-Informatics',4,'Bachelor','UBB');
INSERT INTO EDUCATION(description, duration, education_type, provider) VALUES ('Information Technology',2,'Master','UTCN');
INSERT INTO EDUCATION(description, duration, education_type, provider) VALUES ('Information Technology',2,'Master','UTCN');
INSERT INTO EDUCATION(description, duration, education_type, provider) VALUES ('Informatics',3,'High-School','Marie Curie');
INSERT INTO EDUCATION(description, duration, education_type, provider) VALUES ('Mathematics-Informatics',4,'Bachelor','UBB');
INSERT INTO EDUCATION(description, duration, education_type, provider) VALUES ('Information Technology',2,'Master','UTCN');
INSERT INTO EDUCATION(description, duration, education_type, provider) VALUES ('Information Technology',2,'Master','UTCN');
INSERT INTO EDUCATION(description, duration, education_type, provider) VALUES ('Informatics',3,'High-School','Marie Curie');
INSERT INTO EDUCATION(description, duration, education_type, provider) VALUES ('Mathematics-Informatics',4,'Bachelor','UBB');
INSERT INTO EDUCATION(description, duration, education_type, provider) VALUES ('Information Technology',2,'Master','UTCN');
INSERT INTO EDUCATION(description, duration, education_type, provider) VALUES ('Information Technology',2,'Master','UTCN');
INSERT INTO EDUCATION(description, duration, education_type, provider) VALUES ('Informatics',3,'High-School','Marie Curie');
INSERT INTO EDUCATION(description, duration, education_type, provider) VALUES ('Mathematics-Informatics',4,'Bachelor','UBB');
INSERT INTO EDUCATION(description, duration, education_type, provider) VALUES ('Information Technology',2,'Master','UTCN');
INSERT INTO EDUCATION(description, duration, education_type, provider) VALUES ('Information Technology',2,'Master','UTCN');
INSERT INTO EDUCATION(description, duration, education_type, provider) VALUES ('Informatics',3,'High-School','Marie Curie');
INSERT INTO EDUCATION(description, duration, education_type, provider) VALUES ('Mathematics-Informatics',4,'Bachelor','UBB');
INSERT INTO EDUCATION(description, duration, education_type, provider) VALUES ('Information Technology',2,'Master','UTCN');
INSERT INTO EDUCATION(description, duration, education_type, provider) VALUES ('Information Technology',2,'Master','UTCN');
INSERT INTO EDUCATION(description, duration, education_type, provider) VALUES ('Informatics',3,'High-School','Marie Curie');
INSERT INTO EDUCATION(description, duration, education_type, provider) VALUES ('Mathematics-Informatics',4,'Bachelor','UBB');
INSERT INTO EDUCATION(description, duration, education_type, provider) VALUES ('Information Technology',2,'Master','UTCN');
INSERT INTO EDUCATION(description, duration, education_type, provider) VALUES ('Information Technology',2,'Master','UTCN');
INSERT INTO EDUCATION(description, duration, education_type, provider) VALUES ('Informatics',3,'High-School','Marie Curie');
INSERT INTO EDUCATION(description, duration, education_type, provider) VALUES ('Mathematics-Informatics',4,'Bachelor','UBB');
INSERT INTO EDUCATION(description, duration, education_type, provider) VALUES ('Information Technology',2,'Master','UTCN');
INSERT INTO EDUCATION(description, duration, education_type, provider) VALUES ('Informatics',3,'High-School','Marie Curie');
INSERT INTO EDUCATION(description, duration, education_type, provider) VALUES ('Mathematics-Informatics',4,'Bachelor','UBB');
INSERT INTO EDUCATION(description, duration, education_type, provider) VALUES ('Information Technology',2,'Master','UTCN');
INSERT INTO EDUCATION(description, duration, education_type, provider) VALUES ('Informatics',3,'High-School','Marie Curie');
INSERT INTO EDUCATION(description, duration, education_type, provider) VALUES ('Mathematics-Informatics',4,'Bachelor','UBB');
INSERT INTO EDUCATION(description, duration, education_type, provider) VALUES ('Information Technology',2,'Master','UTCN');
INSERT INTO EDUCATION(description, duration, education_type, provider) VALUES ('Informatics',3,'High-School','Marie Curie');
INSERT INTO EDUCATION(description, duration, education_type, provider) VALUES ('Mathematics-Informatics',4,'Bachelor','UBB');
INSERT INTO EDUCATION(description, duration, education_type, provider) VALUES ('Information Technology',2,'Master','UTCN');

-- CANDIDATE(first_name, last_name, email, event, phone, original_study_year, education_status, education_id, date_of_adding) table:
INSERT INTO CANDIDATE(first_name, last_name, email, event, phone, original_study_year, education_status, education_id, date_of_adding) VALUES ('Huberto','Moxon','hmoxon0@is.gd','conference','7-(474)351-1015',1,'master',2,'2015-09-23 00:00:00');
INSERT INTO CANDIDATE(first_name, last_name, email, event, phone, original_study_year, education_status, education_id, date_of_adding) VALUES ('Dorisa','Jope','djope1@amazonaws.com','conference','357-(157)662-7510',3,'student',3,'2016-10-25 00:00:00');
INSERT INTO CANDIDATE(first_name, last_name, email, event, phone, original_study_year, education_status, education_id, date_of_adding) VALUES ('Bernie','Ekins','bekins2@telegraph.co.uk','practice','62-(995)921-8492',4,'graduate',1,'2013-09-15 00:00:00');
INSERT INTO CANDIDATE(first_name, last_name, email, event, phone, original_study_year, education_status, education_id, date_of_adding) VALUES ('Vida','Richens','vrichens3@si.edu','conference','62-(537)401-7804',5,'student',2,'2016-10-12 00:00:00');
INSERT INTO CANDIDATE(first_name, last_name, email, event, phone, original_study_year, education_status, education_id, date_of_adding) VALUES ('Amelia','Whitten','awhitten4@mtv.com','internship','86-(852)525-7402',1,'graduate',3,'2016-10-20 00:00:00');
INSERT INTO CANDIDATE(first_name, last_name, email, event, phone, original_study_year, education_status, education_id, date_of_adding) VALUES ('Puff','Tapper','ptapper5@tiny.cc','internship','234-(447)239-5754',1,'graduate',2,'2015-03-23 00:00:00');
INSERT INTO CANDIDATE(first_name, last_name, email, event, phone, original_study_year, education_status, education_id, date_of_adding) VALUES ('Cris','Vigers','cvigers6@yahoo.co.jp','internship','62-(817)451-0295',3,'master',2,'2017-02-22 00:00:00');
INSERT INTO CANDIDATE(first_name, last_name, email, event, phone, original_study_year, education_status, education_id, date_of_adding) VALUES ('Madelin','Woodley','mwoodley7@weibo.com','practice','48-(819)925-4752',4,'master',3,'2013-05-01 00:00:00');
INSERT INTO CANDIDATE(first_name, last_name, email, event, phone, original_study_year, education_status, education_id, date_of_adding) VALUES ('Jeralee','Eakly','jeakly8@bandcamp.com','conference','62-(252)624-6880',5,'freelancer',1,'2014-10-02 00:00:00');
INSERT INTO CANDIDATE(first_name, last_name, email, event, phone, original_study_year, education_status, education_id, date_of_adding) VALUES ('Andy','Bickle','abickle9@vistaprint.com','conference','1-(706)857-5869',1,'graduate',2,'2015-10-07 00:00:00');
INSERT INTO CANDIDATE(first_name, last_name, email, event, phone, original_study_year, education_status, education_id, date_of_adding) VALUES ('Anne-marie','Mounsey','amounseya@tinyurl.com','internship','62-(217)309-3574',1,'freelancer',2,'2014-11-23 00:00:00');
INSERT INTO CANDIDATE(first_name, last_name, email, event, phone, original_study_year, education_status, education_id, date_of_adding) VALUES ('Anne','Hindmore','anne@tinyurl.com','internship','62-(217)409-3454',1,'freelancer',2,'2015-10-23 00:00:00');
INSERT INTO CANDIDATE(first_name, last_name, email, event, phone, original_study_year, education_status, education_id, date_of_adding) VALUES ('Audrey','Hindmore','ahindmorec@freewebs.com','practice','62-(682)731-4182',4,'freelancer',2,'2015-11-15 00:00:00');
INSERT INTO CANDIDATE(first_name, last_name, email, event, phone, original_study_year, education_status, education_id, date_of_adding) VALUES ('Judi','Colling','jcollingd@123-reg.co.uk','conference','504-(871)564-5610',5,'graduate',3,'2015-11-17 00:00:00');
INSERT INTO CANDIDATE(first_name, last_name, email, event, phone, original_study_year, education_status, education_id, date_of_adding) VALUES ('Marjory','Dunning','mdunninge@chicagotribune.com','internship','62-(616)408-5877',1,'freelancer',1,'2015-09-23 00:00:00');
INSERT INTO CANDIDATE(first_name, last_name, email, event, phone, original_study_year, education_status, education_id, date_of_adding) VALUES ('Lavena','Stelli','lstellif@wikispaces.com','conference','52-(859)536-0451',2,'freelancer',2,'2013-09-05 00:00:00');
INSERT INTO CANDIDATE(first_name, last_name, email, event, phone, original_study_year, education_status, education_id, date_of_adding) VALUES ('Charleen','Greatex','cgreatexg@lulu.com','conference','86-(680)647-3265',3,'graduate',3,'2014-01-29 00:00:00');
INSERT INTO CANDIDATE(first_name, last_name, email, event, phone, original_study_year, education_status, education_id, date_of_adding) VALUES ('Helenelizabeth','Gatecliff','hgatecliffh@time.com','practice','255-(199)904-3265',4,'freelancer',1,'2016-08-15 00:00:00');
INSERT INTO CANDIDATE(first_name, last_name, email, event, phone, original_study_year, education_status, education_id, date_of_adding) VALUES ('Ag','Simison','asimisoni@shutterfly.com','internship','86-(559)668-2899',5,'master',2,'2017-04-01 00:00:00');
INSERT INTO CANDIDATE(first_name, last_name, email, event, phone, original_study_year, education_status, education_id, date_of_adding) VALUES ('Sydney','Parslow','sparslowj@un.org','practice','86-(773)109-8876',1,'graduate',3,'2013-08-30 00:00:00');
INSERT INTO CANDIDATE(first_name, last_name, email, event, phone, original_study_year, education_status, education_id, date_of_adding) VALUES ('Anjela','Scaife','ascaifek@abc.net.au','conference','380-(462)246-7002',2,'master',1,'2017-01-02 00:00:00');
INSERT INTO CANDIDATE(first_name, last_name, email, event, phone, original_study_year, education_status, education_id, date_of_adding) VALUES ('Edith','Pfeuffer','epfeufferl@dell.com','conference','86-(179)615-0877',3,'master',2,'2014-10-10 00:00:00');
INSERT INTO CANDIDATE(first_name, last_name, email, event, phone, original_study_year, education_status, education_id, date_of_adding) VALUES ('Mureil','Eannetta','meannettam@apple.com','conference','62-(321)961-6705',4,'graduate',3,'2017-02-09 00:00:00');
INSERT INTO CANDIDATE(first_name, last_name, email, event, phone, original_study_year, education_status, education_id, date_of_adding) VALUES ('Hatty','Ferrino','hferrinon@friendfeed.com','practice','380-(686)196-7088',5,'student',1,'2013-06-22 00:00:00');
INSERT INTO CANDIDATE(first_name, last_name, email, event, phone, original_study_year, education_status, education_id, date_of_adding) VALUES ('Ernest','Doni','edonio@51.la','practice','62-(645)516-4249',1,'master',2,'2013-01-22 00:00:00');
INSERT INTO CANDIDATE(first_name, last_name, email, event, phone, original_study_year, education_status, education_id, date_of_adding) VALUES ('Joan','Baldini','jbaldinip@soup.io','internship','55-(390)483-7960',2,'graduate',3,'2014-05-02 00:00:00');
INSERT INTO CANDIDATE(first_name, last_name, email, event, phone, original_study_year, education_status, education_id, date_of_adding) VALUES ('Janifer','MacPike','jmacpikeq@dyndns.org','practice','49-(104)903-3070',3,'student',1,'2015-02-18 00:00:00');
INSERT INTO CANDIDATE(first_name, last_name, email, event, phone, original_study_year, education_status, education_id, date_of_adding) VALUES ('Baudoin','Bassett','bbassettr@smugmug.com','conference','1-(455)935-1192',4,'freelancer',2,'2015-07-06 00:00:00');
INSERT INTO CANDIDATE(first_name, last_name, email, event, phone, original_study_year, education_status, education_id, date_of_adding) VALUES ('Jackson','Haibel','jhaibels@domainmarket.com','practice','48-(186)902-2296',5,'student',3,'2015-06-20 00:00:00');
INSERT INTO CANDIDATE(first_name, last_name, email, event, phone, original_study_year, education_status, education_id, date_of_adding) VALUES ('Virgie','Trunchion','vtrunchiont@geocities.com','internship','963-(990)843-7908',1,'freelancer',1,'2017-03-30 00:00:00');
INSERT INTO CANDIDATE(first_name, last_name, email, event, phone, original_study_year, education_status, education_id, date_of_adding) VALUES ('Ardelis','Symones','asymonesu@wordpress.com','practice','232-(683)694-1933',2,'freelancer',2,'2013-03-06 00:00:00');
INSERT INTO CANDIDATE(first_name, last_name, email, event, phone, original_study_year, education_status, education_id, date_of_adding) VALUES ('Ozzy','Ruprechter','oruprechterv@youtube.com','conference','62-(120)226-5656',3,'student',3,'2014-04-07 00:00:00');
INSERT INTO CANDIDATE(first_name, last_name, email, event, phone, original_study_year, education_status, education_id, date_of_adding) VALUES ('Aggy','Eisikovitsh','aeisikovitshw@homestead.com','internship','420-(876)680-4944',4,'student',1,'2016-06-12 00:00:00');
INSERT INTO CANDIDATE(first_name, last_name, email, event, phone, original_study_year, education_status, education_id, date_of_adding) VALUES ('Vito','Charleston','vcharlestonx@skyrock.com','conference','995-(539)836-6356',5,'graduate',2,'2014-06-02 00:00:00');
INSERT INTO CANDIDATE(first_name, last_name, email, event, phone, original_study_year, education_status, education_id, date_of_adding) VALUES ('Averil','Scaice','ascaicey@springer.com','internship','57-(358)357-8148',1,'student',3,'2017-03-11 00:00:00');
INSERT INTO CANDIDATE(first_name, last_name, email, event, phone, original_study_year, education_status, education_id, date_of_adding) VALUES ('Ava','Candlish','acandlishz@friendfeed.com','conference','86-(188)765-8472',2,'student',1,'2014-10-26 00:00:00');
INSERT INTO CANDIDATE(first_name, last_name, email, event, phone, original_study_year, education_status, education_id, date_of_adding) VALUES ('Nealson','Jacox','njacox10@va.gov','internship','358-(317)654-2426',3,'master',2,'2016-05-21 00:00:00');
INSERT INTO CANDIDATE(first_name, last_name, email, event, phone, original_study_year, education_status, education_id, date_of_adding) VALUES ('Tallulah','Simoneschi','tsimoneschi11@mozilla.com','internship','7-(269)726-2474',4,'graduate',3,'2014-02-04 00:00:00');
INSERT INTO CANDIDATE(first_name, last_name, email, event, phone, original_study_year, education_status, education_id, date_of_adding) VALUES ('Brianne','Crat','bcrat12@unicef.org','conference','86-(531)585-8304',5,'student',1,'2014-06-22 00:00:00');
INSERT INTO CANDIDATE(first_name, last_name, email, event, phone, original_study_year, education_status, education_id, date_of_adding) VALUES ('Lonnie','McKernon','lmckernon13@shareasale.com','practice','86-(580)694-5570',1,'graduate',2,'2014-01-24 00:00:00');
INSERT INTO CANDIDATE(first_name, last_name, email, event, phone, original_study_year, education_status, education_id, date_of_adding) VALUES ('Kendall','Frith','kfrith14@e-recht24.de','internship','385-(308)619-3644',2,'master',3,'2013-11-16 00:00:00');
INSERT INTO CANDIDATE(first_name, last_name, email, event, phone, original_study_year, education_status, education_id, date_of_adding) VALUES ('Janela','Sharpley','jsharpley15@google.de','conference','216-(349)628-6663',3,'graduate',1,'2017-02-17 00:00:00');
INSERT INTO CANDIDATE(first_name, last_name, email, event, phone, original_study_year, education_status, education_id, date_of_adding) VALUES ('Dorie','Ballendine','dballendine16@state.tx.us','conference','233-(265)234-9734',4,'graduate',2,'2016-11-26 00:00:00');
INSERT INTO CANDIDATE(first_name, last_name, email, event, phone, original_study_year, education_status, education_id, date_of_adding) VALUES ('Silvain','Bellis','sbellis17@indiatimes.com','internship','371-(214)464-4694',5,'master',3,'2013-12-11 00:00:00');
INSERT INTO CANDIDATE(first_name, last_name, email, event, phone, original_study_year, education_status, education_id, date_of_adding) VALUES ('Dena','Kadwallider','dkadwallider18@amazonaws.com','internship','216-(508)891-6394',1,'freelancer',1,'2016-12-13 00:00:00');
INSERT INTO CANDIDATE(first_name, last_name, email, event, phone, original_study_year, education_status, education_id, date_of_adding) VALUES ('Dame','Prise','dprise19@mtv.com','practice','62-(632)161-7146',2,'freelancer',2,'2016-06-03 00:00:00');
INSERT INTO CANDIDATE(first_name, last_name, email, event, phone, original_study_year, education_status, education_id, date_of_adding) VALUES ('Devon','Woltman','dwoltman1a@wunderground.com','conference','234-(244)608-3805',3,'freelancer',3,'2015-05-05 00:00:00');
INSERT INTO CANDIDATE(first_name, last_name, email, event, phone, original_study_year, education_status, education_id, date_of_adding) VALUES ('Lilla','Hawkswood','lhawkswood1b@desdev.cn','internship','48-(380)718-5424',4,'graduate',1,'2016-11-24 00:00:00');
INSERT INTO CANDIDATE(first_name, last_name, email, event, phone, original_study_year, education_status, education_id, date_of_adding) VALUES ('Kristopher','Sooper','ksooper1c@lulu.com','practice','57-(167)409-7680',5,'master',2,'2015-04-19 00:00:00');
INSERT INTO CANDIDATE(first_name, last_name, email, event, phone, original_study_year, education_status, education_id, date_of_adding) VALUES ('Vaughan','Huniwall','vhuniwall1d@plala.or.jp','practice','62-(215)199-2364',1,'master',3,'2015-03-10 00:00:00');
INSERT INTO CANDIDATE(first_name, last_name, email, event, phone, original_study_year, education_status, education_id, date_of_adding) VALUES ('Cassy','Larby','clarby1e@state.tx.us','internship','86-(215)427-4053',2,'graduate',1,'2016-08-28 00:00:00');
INSERT INTO CANDIDATE(first_name, last_name, email, event, phone, original_study_year, education_status, education_id, date_of_adding) VALUES ('Pegeen','Keele','pkeele1f@merriam-webster.com','internship','232-(588)822-6940',3,'master',2,'2017-05-26 00:00:00');
INSERT INTO CANDIDATE(first_name, last_name, email, event, phone, original_study_year, education_status, education_id, date_of_adding) VALUES ('Aindrea','Baudinet','abaudinet1g@npr.org','conference','7-(735)163-8665',4,'master',3,'2013-01-22 00:00:00');
INSERT INTO CANDIDATE(first_name, last_name, email, event, phone, original_study_year, education_status, education_id, date_of_adding) VALUES ('Simon','Brealey','sbrealey1h@dailymail.co.uk','internship','30-(325)839-4912',5,'master',1,'2013-08-28 00:00:00');
INSERT INTO CANDIDATE(first_name, last_name, email, event, phone, original_study_year, education_status, education_id, date_of_adding) VALUES ('Phoebe','Gebbie','pgebbie1i@sina.com.cn','conference','420-(525)276-2255',1,'freelancer',2,'2016-12-21 00:00:00');
INSERT INTO CANDIDATE(first_name, last_name, email, event, phone, original_study_year, education_status, education_id, date_of_adding) VALUES ('Lucila','Scibsey','lscibsey1j@buzzfeed.com','internship','225-(497)496-3916',2,'graduate',3,'2016-04-13 00:00:00');
INSERT INTO CANDIDATE(first_name, last_name, email, event, phone, original_study_year, education_status, education_id, date_of_adding) VALUES ('Opalina','Vinnick','ovinnick1k@prweb.com','conference','255-(655)323-1410',3,'graduate',1,'2016-06-04 00:00:00');
INSERT INTO CANDIDATE(first_name, last_name, email, event, phone, original_study_year, education_status, education_id, date_of_adding) VALUES ('Melisse','Mauchline','mmauchline1l@biglobe.ne.jp','practice','507-(549)282-7982',4,'student',2,'2016-02-08 00:00:00');
INSERT INTO CANDIDATE(first_name, last_name, email, event, phone, original_study_year, education_status, education_id, date_of_adding) VALUES ('Gan','Gutridge','ggutridge1m@cisco.com','practice','420-(116)828-4249',5,'master',3,'2014-06-11 00:00:00');
INSERT INTO CANDIDATE(first_name, last_name, email, event, phone, original_study_year, education_status, education_id, date_of_adding) VALUES ('Regan','Yewdall','ryewdall1n@ed.gov','practice','358-(617)565-3384',1,'freelancer',1,'2014-08-04 00:00:00');
INSERT INTO CANDIDATE(first_name, last_name, email, event, phone, original_study_year, education_status, education_id, date_of_adding) VALUES ('Myrtice','Pavluk','mpavluk1o@yellowpages.com','internship','420-(327)277-7191',2,'student',2,'2017-04-17 00:00:00');
INSERT INTO CANDIDATE(first_name, last_name, email, event, phone, original_study_year, education_status, education_id, date_of_adding) VALUES ('Jere','Yoell','jyoell1p@nbcnews.com','conference','63-(538)937-6759',3,'graduate',3,'2013-01-30 00:00:00');
INSERT INTO CANDIDATE(first_name, last_name, email, event, phone, original_study_year, education_status, education_id, date_of_adding) VALUES ('Rickey','Atthow','ratthow1q@walmart.com','internship','1-(405)435-9967',4,'master',1,'2015-01-23 00:00:00');
INSERT INTO CANDIDATE(first_name, last_name, email, event, phone, original_study_year, education_status, education_id, date_of_adding) VALUES ('Nahum','Acres','nacres1r@freewebs.com','practice','92-(835)828-1199',5,'student',2,'2013-02-25 00:00:00');
INSERT INTO CANDIDATE(first_name, last_name, email, event, phone, original_study_year, education_status, education_id, date_of_adding) VALUES ('Reinold','Pogosian','rpogosian1s@tmall.com','conference','86-(974)941-3583',1,'master',3,'2013-04-30 00:00:00');
INSERT INTO CANDIDATE(first_name, last_name, email, event, phone, original_study_year, education_status, education_id, date_of_adding) VALUES ('Alethea','Klimentyev','aklimentyev1t@bandcamp.com','practice','86-(119)195-6805',2,'student',1,'2014-05-12 00:00:00');
INSERT INTO CANDIDATE(first_name, last_name, email, event, phone, original_study_year, education_status, education_id, date_of_adding) VALUES ('Odella','Arrington','oarrington1u@nymag.com','practice','86-(940)755-4889',3,'graduate',2,'2013-04-14 00:00:00');
INSERT INTO CANDIDATE(first_name, last_name, email, event, phone, original_study_year, education_status, education_id, date_of_adding) VALUES ('Fairfax','Andrault','fandrault1v@usda.gov','conference','46-(240)856-2774',4,'freelancer',3,'2013-02-15 00:00:00');
INSERT INTO CANDIDATE(first_name, last_name, email, event, phone, original_study_year, education_status, education_id, date_of_adding) VALUES ('Danit','Kas','dkas1w@nih.gov','practice','86-(489)994-9656',5,'graduate',1,'2016-06-07 00:00:00');
INSERT INTO CANDIDATE(first_name, last_name, email, event, phone, original_study_year, education_status, education_id, date_of_adding) VALUES ('Jemimah','Bodiam','jbodiam1x@tinypic.com','internship','351-(781)183-6969',1,'graduate',2,'2015-06-14 00:00:00');
INSERT INTO CANDIDATE(first_name, last_name, email, event, phone, original_study_year, education_status, education_id, date_of_adding) VALUES ('Bayard','Bambra','bbambra1y@who.int','conference','86-(575)442-3464',2,'student',3,'2014-05-23 00:00:00');
INSERT INTO CANDIDATE(first_name, last_name, email, event, phone, original_study_year, education_status, education_id, date_of_adding) VALUES ('Jess','Snelson','jsnelson1z@foxnews.com','internship','976-(248)455-4728',3,'master',1,'2015-01-31 00:00:00');
INSERT INTO CANDIDATE(first_name, last_name, email, event, phone, original_study_year, education_status, education_id, date_of_adding) VALUES ('Florella','Schild','fschild20@icq.com','conference','84-(460)599-0949',4,'graduate',2,'2013-04-26 00:00:00');
INSERT INTO CANDIDATE(first_name, last_name, email, event, phone, original_study_year, education_status, education_id, date_of_adding) VALUES ('Rozina','Blundel','rblundel21@gnu.org','internship','57-(449)637-7713',5,'student',3,'2013-03-05 00:00:00');
INSERT INTO CANDIDATE(first_name, last_name, email, event, phone, original_study_year, education_status, education_id, date_of_adding) VALUES ('Maryellen','Asher','masher22@blogger.com','practice','357-(941)359-2890',1,'master',1,'2013-11-02 00:00:00');
INSERT INTO CANDIDATE(first_name, last_name, email, event, phone, original_study_year, education_status, education_id, date_of_adding) VALUES ('Nicolle','Medley','nmedley23@ucsd.edu','conference','7-(801)413-7982',2,'graduate',2,'2013-07-26 00:00:00');
INSERT INTO CANDIDATE(first_name, last_name, email, event, phone, original_study_year, education_status, education_id, date_of_adding) VALUES ('Calla','Lamberto','clamberto24@comcast.net','practice','27-(909)446-8237',3,'student',3,'2013-12-10 00:00:00');
INSERT INTO CANDIDATE(first_name, last_name, email, event, phone, original_study_year, education_status, education_id, date_of_adding) VALUES ('Ranice','Bickerstasse','rbickerstasse25@hc360.com','internship','98-(494)566-3120',4,'student',1,'2015-06-17 00:00:00');
INSERT INTO CANDIDATE(first_name, last_name, email, event, phone, original_study_year, education_status, education_id, date_of_adding) VALUES ('Ericka','Josefer','ejosefer26@ezinearticles.com','practice','84-(133)783-3459',5,'student',2,'2015-03-28 00:00:00');
INSERT INTO CANDIDATE(first_name, last_name, email, event, phone, original_study_year, education_status, education_id, date_of_adding) VALUES ('Christophorus','Ludgrove','cludgrove27@facebook.com','conference','86-(890)992-3688',1,'freelancer',3,'2016-05-24 00:00:00');
INSERT INTO CANDIDATE(first_name, last_name, email, event, phone, original_study_year, education_status, education_id, date_of_adding) VALUES ('Yehudit','Willowby','ywillowby28@nifty.com','conference','7-(803)297-2309',2,'student',1,'2013-08-18 00:00:00');
INSERT INTO CANDIDATE(first_name, last_name, email, event, phone, original_study_year, education_status, education_id, date_of_adding) VALUES ('Jedd','Murkitt','jmurkitt29@cbc.ca','conference','685-(964)707-9933',3,'graduate',2,'2016-12-25 00:00:00');
INSERT INTO CANDIDATE(first_name, last_name, email, event, phone, original_study_year, education_status, education_id, date_of_adding) VALUES ('Dode','Raisbeck','draisbeck2a@sakura.ne.jp','conference','30-(841)504-3541',4,'graduate',3,'2015-07-02 00:00:00');
INSERT INTO CANDIDATE(first_name, last_name, email, event, phone, original_study_year, education_status, education_id, date_of_adding) VALUES ('Ned','Emmot','nemmot2b@cdbaby.com','conference','7-(131)604-7401',5,'graduate',1,'2016-09-07 00:00:00');
INSERT INTO CANDIDATE(first_name, last_name, email, event, phone, original_study_year, education_status, education_id, date_of_adding) VALUES ('Maxi','Harcourt','mharcourt2c@instagram.com','conference','54-(196)784-4351',1,'freelancer',2,'2013-06-18 00:00:00');
INSERT INTO CANDIDATE(first_name, last_name, email, event, phone, original_study_year, education_status, education_id, date_of_adding) VALUES ('Drud','Hansom','dhansom2d@noaa.gov','conference','351-(810)465-7523',2,'student',3,'2015-02-08 00:00:00');
INSERT INTO CANDIDATE(first_name, last_name, email, event, phone, original_study_year, education_status, education_id, date_of_adding) VALUES ('Lucilia','Trassler','ltrassler2e@unc.edu','conference','20-(323)900-3899',3,'student',1,'2016-04-17 00:00:00');
INSERT INTO CANDIDATE(first_name, last_name, email, event, phone, original_study_year, education_status, education_id, date_of_adding) VALUES ('Raine','Tite','rtite2f@xrea.com','conference','381-(731)220-6513',4,'freelancer',2,'2016-12-07 00:00:00');
INSERT INTO CANDIDATE(first_name, last_name, email, event, phone, original_study_year, education_status, education_id, date_of_adding) VALUES ('Ross','Braz','rbraz2g@angelfire.com','internship','62-(217)505-4178',5,'master',3,'2014-05-16 00:00:00');
INSERT INTO CANDIDATE(first_name, last_name, email, event, phone, original_study_year, education_status, education_id, date_of_adding) VALUES ('Nelle','Hagergham','nhagergham2h@accuweather.com','practice','372-(772)793-9594',1,'freelancer',1,'2013-04-17 00:00:00');
INSERT INTO CANDIDATE(first_name, last_name, email, event, phone, original_study_year, education_status, education_id, date_of_adding) VALUES ('Ambrosius','Joannet','ajoannet2i@dailymail.co.uk','internship','86-(237)500-3485',2,'freelancer',2,'2015-02-18 00:00:00');
INSERT INTO CANDIDATE(first_name, last_name, email, event, phone, original_study_year, education_status, education_id, date_of_adding) VALUES ('Inger','Hurst','ihurst2j@sciencedirect.com','internship','86-(294)243-0703',3,'freelancer',3,'2016-06-15 00:00:00');
INSERT INTO CANDIDATE(first_name, last_name, email, event, phone, original_study_year, education_status, education_id, date_of_adding) VALUES ('Shanon','Govier','sgovier2k@nsw.gov.au','conference','261-(703)323-9849',4,'student',1,'2014-03-02 00:00:00');
INSERT INTO CANDIDATE(first_name, last_name, email, event, phone, original_study_year, education_status, education_id, date_of_adding) VALUES ('Una','Stops','ustops2l@xinhuanet.com','practice','62-(826)845-1476',5,'master',2,'2013-08-11 00:00:00');
INSERT INTO CANDIDATE(first_name, last_name, email, event, phone, original_study_year, education_status, education_id, date_of_adding) VALUES ('Halley','Flatley','hflatley2m@networkadvertising.org','internship','63-(732)151-9761',1,'freelancer',3,'2013-08-22 00:00:00');
INSERT INTO CANDIDATE(first_name, last_name, email, event, phone, original_study_year, education_status, education_id, date_of_adding) VALUES ('Farly','Killick','fkillick2n@blogspot.com','conference','62-(517)208-4975',2,'graduate',1,'2016-09-25 00:00:00');
INSERT INTO CANDIDATE(first_name, last_name, email, event, phone, original_study_year, education_status, education_id, date_of_adding) VALUES ('Cami','Henner','chenner2o@hao123.com','internship','7-(817)724-3490',3,'freelancer',2,'2017-05-15 00:00:00');
INSERT INTO CANDIDATE(first_name, last_name, email, event, phone, original_study_year, education_status, education_id, date_of_adding) VALUES ('Elli','Spriggin','espriggin2p@feedburner.com','conference','86-(968)186-7011',4,'graduate',3,'2014-03-08 00:00:00');
INSERT INTO CANDIDATE(first_name, last_name, email, event, phone, original_study_year, education_status, education_id, date_of_adding) VALUES ('Wolfy','Thring','wthring2q@netscape.com','internship','86-(615)888-1619',5,'freelancer',1,'2017-06-16 00:00:00');
INSERT INTO CANDIDATE(first_name, last_name, email, event, phone, original_study_year, education_status, education_id, date_of_adding) VALUES ('Rafaelia','Rallings','rrallings2r@tinypic.com','conference','359-(392)463-4229',1,'graduate',2,'2016-12-14 00:00:00');

INSERT INTO TAG(description, tag_type) VALUES('german', 'Foreign');

INSERT INTO CANDIDATE_SKILLS(certifier, rating, candidate_id, tag_id) VALUES('certif', '3', 4, 1);