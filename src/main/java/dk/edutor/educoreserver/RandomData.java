package dk.edutor.educoreserver;

import dk.edutor.educoreserver.model.App;
import dk.edutor.educoreserver.model.Enrollment;
import dk.edutor.educoreserver.model.Enrollment.EnrollmentId;
import dk.edutor.educoreserver.model.Grouping;
import dk.edutor.educoreserver.model.User;
import java.time.LocalDate;
import java.time.Month;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Random;
import javax.persistence.EntityManager;

/**
 The purpose of RandomData is to...

 @author kasper
 */
public class RandomData {
    
    static List<App> generateApps() {
        List<App> apps = new ArrayList<>();
        App a = new App();
        a.setName( "Autoret" );
        a.setDesc( "One mark to rule them all" );
        apps.add( a );
        a = new App();
        a.setName( "Studypoint" );
        a.setDesc( "They are all pointing at me" );
        apps.add( a );
        a = new App();
        a.setName( "Autofeedback" );
        a.setDesc( "Hey, you are ok as you are" );
        apps.add( a );
        return apps;
    }
    
    static void setAppmins( List<App> apps, List<User> users ) {
        Random rnd = new Random();
        for ( App app : apps ) {
            int noAppmins = 1 + rnd.nextInt( 2 );
            Collection<User> appmins = new ArrayList<>();
            for ( int i = 0; i < noAppmins; i++ ) {
                User u = users.get( rnd.nextInt( users.size() ) );
                appmins.add( u );
            }
            app.setUsers( appmins );
        }
    }
    
    static List<Enrollment> enroll( List<Grouping> groupings, List<User> users ) {
        Random rnd = new Random();
        List<Enrollment> enrollments = new ArrayList<>();
        for ( Grouping grp : groupings ) {
            int classSize = 10 + rnd.nextInt( 22 );
            List<User> ulist = new ArrayList( users );
            for ( int i = 0; i < classSize; i++ ) {
                User u = ulist.remove( rnd.nextInt( ulist.size() ) );
                Enrollment enroll = new Enrollment();
                EnrollmentId id = new Enrollment.EnrollmentId();
                id.setGrouping( grp );
                id.setUser(u);
                enroll.setId(id );
//                enroll.setGrouping( grp );
//                enroll.setUser( u );
                enroll.setRole( u.getRole() );
                enroll.setActive( rnd.nextFloat() > 0.05 );
                enrollments.add( enroll );
            }
        }
        return enrollments;
    }
    
    static List<User> generateUsers( int noUsers ) {
        List<User> lusr = new ArrayList<>();
        for ( int i = 0; i < noUsers; i++ ) {
            User user = new User();
            final String name = name();
            final String callName = name.substring( 0, 4 )
                    + name.substring( name.length() - 4 );
            user.setFullName( name );
            user.setCallName( callName );
            user.setCphEmail( "usr-" + i + "@cphbusiness.dk" );
            user.setPrefEmail( callName + "@goodmail.com" );
            user.setPwHash( "Urt" );
            user.setRole( role() );
            lusr.add( user );
        }
        return lusr;
    }
    
    static List<Grouping> generateGroups( int noGroups ) {
        final String[] abc = { "a", "b", "c" };
        Random rnd = new Random();
        List<Grouping> lgrp = new ArrayList<>();
        for ( int i = 0; i < noGroups; i++ ) {
            //CPH-l17dat1ae
            Grouping grp = new Grouping();
            int year = 10 + rnd.nextInt( 10 );
            String semester = rnd.nextBoolean() ? "f" : "e";
            String edu = rnd.nextBoolean() ? "dat" : "soft";
            String clazz = abc[ rnd.nextInt( 3 ) ];
            int semNo = rnd.nextInt( 3 ) + 1;
            String name = "CPH-l" + year + edu + semNo
                    + clazz + semester;
            LocalDate start = LocalDate.of( 2000 + year,
                    semester.equals( "f" ) ? Month.FEBRUARY : Month.SEPTEMBER, 1 );
            LocalDate end = start.plusMonths( 5 );
            String type = edu + semNo;
            grp.setName( name );
            grp.setStart( java.sql.Date.valueOf( start ) );
            grp.setEnd( java.sql.Date.valueOf( end ) );
            grp.setType( type );
            if ( rnd.nextFloat() < 0.1 && i > 10 ) {
                grp.setParent( lgrp.get( rnd.nextInt( i ) ) );
            }
            lgrp.add( grp );
        }
        return lgrp;
    }
    
    private static String name() {
        Random rnd = new Random();
        String first = FIRSTNAMES[ rnd.nextInt( FIRSTNAMES.length ) ];
        String last = LASTNAMES[ rnd.nextInt( LASTNAMES.length ) ];
        return first + " " + last;
    }
    
    private static String role() {
        Random rnd = new Random();
        float f = rnd.nextFloat();
        if ( f < 0.8 ) {
            return "student";
        }
        if ( f < 0.95 ) {
            return "teacher";
        }
        return "admin";
    }
    private static final String[] LASTNAMES = { "Ahmad", "Ahmed", "Ali", "Andersen", "Andersson", "Andreasen", "Andreassen", "Andresen", "Asmussen", "Bach", "Bak",
        "Bang", "Bech", "Beck", "Bendtsen", "Berg", "Bertelsen", "Berthelsen", "Bisgård", "Bisgaard", "Bjerre", "Bjerregård",
        "Bjerregaard", "Bonde", "Brandt", "Brodersen", "Bruun", "Buch", "Bundgård", "Bundgaard", "Carlsen", "Carstensen",
        "Christensen", "Christiansen", "Christoffersen", "Clausen", "Dahl", "Dalgård", "Dalgaard", "Dalsgård", "Dalsgaard", "Dam",
        "Damgård", "Damgaard", "Danielsen", "Davidsen", "Enevoldsen", "Eriksen", "Eskildsen", "Fischer", "Frandsen", "Frederiksen",
        "Friis", "Frost", "Gade", "Gregersen", "Hald", "Hansen", "Hassan", "Hedegård", "Hedegaard", "Hemmingsen", "Henningsen",
        "Henriksen", "Hermansen", "Hjorth", "Hoffmann", "Holm", "Holst", "Hougård", "Hougaard", "Ibsen", "Iversen", "Jacobsen",
        "Jakobsen", "Jensen", "Jeppesen", "Jepsen", "Jespersen", "Jessen", "Johannesen", "Johannsen", "Johansen", "Johansson",
        "Johnsen", "Juhl", "Justesen", "Juul", "Jønsson", "Jørgensen", "Karlsen", "Khan", "Kirkegård", "Kirkegaard", "Kjeldsen",
        "Kjær", "Kjærgård", "Kjærgaard", "Klausen", "Knudsen", "Koch", "Kofoed", "Kragh", "Kristensen", "Kristiansen",
        "Kristoffersen", "Krog", "Krogh", "Kruse", "Lange", "Larsen", "Lassen", "Lauridsen", "Lauritsen", "Lauritzen", "Laursen",
        "Laustsen", "Leth", "Lind", "Lorentzen", "Lorenzen", "Lund", "Madsen", "Markussen", "Mathiasen", "Mathiesen", "Meyer",
        "Michelsen", "Mikkelsen", "Mogensen", "Mohamed", "Mortensen", "Munch", "Munk", "Müller", "Mølgård", "Mølgaard", "Møller",
        "Nguyen", "Nielsen", "Nikolajsen", "Nilsson", "Nissen", "Nygård", "Nygaard", "Nørgård", "Nørgaard", "Olesen", "Olsen",
        "Olsson", "Overgård", "Overgaard", "Paulsen", "Pedersen", "Persson", "Petersen", "Poulsen", "Rasmussen", "Ravn", "Riis",
        "Schmidt", "Schou", "Schrøder", "Schultz", "Simonsen", "Skov", "Sloth", "Sommer", "Steffensen", "Svendsen", "Svensson",
        "Søgård", "Søgaard", "Søndergård", "Søndergaard", "Sørensen", "Thomassen", "Thomsen", "Thorsen", "Thygesen", "Thøgersen",
        "Toft", "Vestergård", "Vestergaard", "Villadsen", "Vinther", "Winther", "Østergård", "Østergaard", "Ågård", "Aagård",
        "Ågaard", "Aagaard" };
    static final String[] FIRSTNAMES = { "Aake", "Abdallah", "Abdel", "Abdul", "Abel", "Abraham", "Absalon", "Achim", "Achton",
        "Acton", "Adalina", "Adam", "Adane", "Adda", "Addie", "Addy", "Adea", "Adela", "Adelaide", "Adelbert", "Adele",
        "Adèle", "Adelena", "Adelfine", "Adelgunde", "Adelheid", "Adelhejd", "Adelina", "Adeline", "Adia", "Adie", "Adils",
        "Adina", "Adine", "Adolf", "Adolfe", "Adolfine", "Adolph", "Adolphine", "Adrian", "Adriana", "Adriane", "Adriano",
        "Adrienne", "Adser", "Adwan", "Agate", "Agatha", "Agathe", "Agda", "Agga", "Agge", "Aggi", "Agna", "Agnar", "Agne",
        "Agner", "Agnes", "Agnès", "Agneta", "Agnete", "Agnetha", "Agnethe", "Agny", "Ahmed", "Ahrendt", "Ahrent", "Aicha",
        "Aida", "Aies", "Aiko", "Aila", "Aili", "Aima", "Aimas", "Aimèe", "Aimi", "Aimy", "Aina", "Aino", "Aisha", "Aishah",
        "Ajatsa", "Ajes", "Akeleje", "Akim", "Akita", "Aksel", "Akseline", "Aksinja", "Akton", "Aladdin", "Alaia", "Alain",
        "Alaine", "Alan", "Alba", "Alban", "Albert", "Alberta", "Alberte", "Alberth", "Albertha", "Alberthine", "Albertina",
        "Albertine", "Alberto", "Albertus", "Albin", "Albina", "Albine", "Albinus", "Albrecht", "Albrekt", "Albret", "Aldo",
        "Alec ", "Aleia", "Alek", "Aleks", "Aleksandar", "Aleksander", "Aleksandra", "Aleksandrine", "Aleksej", "Aleksia",
        "Alessandra", "Aleta", "Alett", "Alette", "Alex", "Alexa", "Alexandar", "Alexander", "Alexandra", "Alexandrina",
        "Alexandrine", "Alexia", "Alexie", "Alexis", "Alexius", "Alfa", "Alfhild", "Alfred", "Alfrede", "Alfrida", "Alfride",
        "Alfrieda", "Alfriede", "Algea", "Alger", "Algot", "Algoth", "Algy", "Alia", "Alice", "Alicia", "Alida", "Aliena",
        "Alikka", "Alina", "Aline", "Alis", "Alisa", "Alise", "Alison", "Alita", "Alitha", "Alitta", "Alitza", "Aliz",
        "Allaine", "Allan", "Allen", "Allette", "Allice", "Allie", "Allis", "Ally", "Allydia", "Alma", "Almar", "Almer",
        "Almine", "Alois", "Aloisia", "Alona", "Aloysius", "Alryda", "Alva", "Alvar", "Alver", "Alvild", "Alvilda", "Alvilde",
        "Alvin", "Alvina", "Alvine", "Alvira", "Alwine", "Amadeus", "Amalia", "Amalie", "Amand", "Amanda", "Amandus",
        "Ambjørn", "Ambrosia", "Ambrosius", "Amdi", "Amelia", "Amelie", "Amélie", "Amilia", "Amilla", "Amina", "Aminah",
        "Amine", "Amira", "Ammund", "Amola", "Amund", "Anastaja", "Anastasia", "Anastasija", "Anastasja", "Anastassia",
        "Anastazia", "Anatol", "Anatole", "Anaya", "Ancher", "Ancia", "Anders", "Andersine", "Andi", "Andras", "Andre",
        "André", "Andrea", "Andreas", "Andrei", "Andrés", "Andrew", "Andrias", "Andriette", "Andrine", "Andry", "Andy", "Anée",
        "Anegrete", "Anegrethe", "Anelise", "Anella", "Anelle", "Anemarie", "Anemette", "Anemone", "Anete", "Anethe", "Anett",
        "Anetta", "Anette", "Anfred", "Angel", "Angela", "Angelica", "Angelie", "Angelika", "Angelina", "Angeline",
        "Angelique", "Angélique", "Angèlique", "Angelita", "Angelo", "Angul", "Angus", "Ania", "Aniara", "Anica", "Anick",
        "Anie", "Aniela", "Aniella", "Aniet", "Anika", "Aniki", "Anilla", "Anina", "Anine", "Aninna", "Anisa", "Anise",
        "Anisette", "Anissa", "Anita", "Anitha", "Anitta", "Anja", "Anjelica", "Anjila", "Anka", "Anke", "Anker", "Ankie",
        "Anli", "Anline", "Anna", "Annabell", "Annabella", "Annabelle", "Annabeth", "Annagrete", "Annagrethe", "Annakarin",
        "Annali", "Annaline", "Annalis", "Annalisa", "Annalise", "Annamarie", "Annamay", "Annamette", "Annamie", "Annasofie",
        "Annastasia", "Annbeth", "Annbrit", "Annbritt", "Anne", "Annebell", "Annebelle", "Annebet", "Annebeth", "Annebirgitte",
        "Annegitte", "Annegrete", "Annegrethe", "Anneke", "Anneken", "Annelene", "Anneli", "Annelie", "Anneliese", "Anneline",
        "Annelis", "Annelisa", "Annelise", "Annelouise", "Annemari", "Annemarie", "Annemette", "Annemie", "Annemona",
        "Annemone", "Annesofie", "Annet", "Annete", "Anneth", "Annethe", "Annett", "Annette", "Anngrethe", "Anni", "Annia",
        "Annica", "Annie", "Annielle", "Annik", "Annika", "Annike", "Annikki", "Annina", "Annine", "Annisette", "Annita",
        "Annitta", "Annizette", "Annli", "Annlil", "Annlisa", "Annlise", "Annmari", "Annmarie", "Annsofi", "Annsofie",
        "Annsophie", "Anny", "Anouk", "Anselm", "Ansgar", "Ansine", "Ante", "Anthon", "Anthonia", "Anthonie", "Anthonio",
        "Anthonius", "Anthony", "Antine", "Antje", "Antoine", "Antoinetta", "Antoinette", "Antomine", "Anton", "Antonella",
        "Antonelle", "Antonette", "Antoni", "Antonia", "Antonie", "Antonina", "Antonine", "Antonio", "Antonius", "Antony",
        "Anya", "Apollo", "Apollus", "Aprilia", "Aqqaluk", "Arabella", "Aram", "Aranka", "Archibald", "Archie", "Arendse",
        "Arendt", "Arense", "Arent", "Ariane", "Arielle", "Arik", "Arild", "Arina", "Arise", "Arkibald", "Arla", "Arlette",
        "Arletty", "Arly", "Armand", "Armin", "Arna", "Arnbjørn", "Arndt", "Arne", "Arnfred", "Arni", "Arnkil", "Arno",
        "Arnold", "Arnt", "Arnth", "Aron", "Arrild", "Arthur", "Artur", "Arved", "Arvid", "Asbjørn", "Asdis", "Asfred",
        "Asfrid", "Asgar", "Asger", "Asgerd", "Ashley", "Asja", "Aske", "Asker", "Askil", "Askild", "Asla", "Aslak", "Aslaug",
        "Asløg", "Asmind", "Asmine", "Asmira", "Asmund", "Asmus", "Asra", "Asser", "Assi", "Assia", "Assika", "Asta", "Asthor",
        "Astor", "Astrid", "Atalia", "Atalie", "Athalia", "Athalie", "Athena", "Athene", "Athina", "Atlanta", "Atle", "Atli",
        "Atos", "Atte", "Attila", "Audrey", "Audun", "Augo", "August", "Augusta", "Auguste", "Augustin", "Augustina",
        "Augustine", "Augustinus", "Augusto", "Augustus", "Aukje", "Aurelia", "Aurica", "Aurora", "Avgusta", "Aviaya", "Axel",
        "Axeline", "Aylil", "Ayma", "Ayna", "Ayoe", "Ayse", "Bagge", "Bahne", "Baki", "Balder", "Baldrian", "Balduin",
        "Baldur", "Balser", "Baltazar", "Balthasar", "Balthazar", "Baltser", "Baltzer", "Balzer", "Bane", "Barnabas", "Barney",
        "Barny", "Barry", "Bart", "Bartel", "Bartholemeus", "Bartholemæus", "Bartholomeus", "Bartolemæus", "Bartolomæus",
        "Bastian", "Behrend", "Behrendt", "Bendiks", "Bendix", "Bendt", "Bendy", "Benedict", "Benedikt", "Bengt", "Benito",
        "Benjamin", "Benn", "Benne", "Bennet", "Benni", "Bennie", "Benno", "Benny", "Benoni", "Bent", "Benth", "Bentt", "Berd",
        "Berend", "Berent", "Berild", "Bernard", "Bernd", "Berndt", "Bernhard", "Bernhardt", "Berni", "Bernino", "Berno",
        "Bernt", "Bernth", "Berny", "Berry", "Bert", "Bertel", "Berthel", "Berthold", "Bertil", "Berto", "Bertold", "Bertolt",
        "Bertram", "Bertrand", "Betino", "Bill", "Billy", "Bing", "Birge", "Birger", "Birk", "Bjarke", "Bjarki", "Bjarne",
        "Bjarni", "Bjarno", "Bjergi", "Bjerke", "Bjerne", "Bjork", "Bjørk", "Bjørke", "Bjørn", "Bjørno", "Blaise", "Blas",
        "Bobbi", "Bobby", "Bode", "Bodo", "Boie", "Boje", "Bonde", "Bone", "Bonne", "Bonnik", "Bonno", "Bonny", "Boris",
        "Borris", "Boyd", "Boye", "Bram", "Brandur", "Brett", "Brian", "Brion", "Broder", "Bror", "Bruce", "Bruno", "Bryan",
        "Bryde", "Brynjolf", "Brynjolfur", "Brynulf", "Bugge", "Buller", "Buris", "Buster", "Buur", "Byrge", "Byrial", "Bøje",
        "Børge", "Børre", "Baard", "Cajus", "Calle", "Calvin", "Camillas", "Camillo", "Carit", "Carl", "Carlis", "Carlo",
        "Carlos", "Carly", "Carsten", "Carter", "Carton", "Caspar", "Casper", "Cassius", "Castello", "Castor", "Cato", "Cecil",
        "Cedric", "Cendy", "Chano", "Charles", "Charley", "Charli", "Charlie", "Charly", "Chester", "Chico", "Chiko", "Chres",
        "Chresten", "Chrestian", "Chrilles", "Chris", "Christen", "Christer", "Christian", "Christiern", "Christof",
        "Christoffer", "Christoph", "Christophe", "Christopher", "Ciano", "Cilas", "Cilius", "Cimmi", "Claes", "Clarence",
        "Clark", "Clas", "Claude", "Claudi", "Claudio", "Claudius", "Claus", "Clavs", "Clemen", "Clemens", "Clement",
        "Clemmen", "Cliff", "Clifford", "Clint", "Clive", "Clyde", "Colin", "Columbus", "Conrad", "Constantin", "Cordt",
        "Corfits", "Corfitz", "Cornelis", "Cornelius", "Cort", "Corty", "Cosmus", "Crilles", "Cris", "Crister", "Cristian",
        "Cristoffer", "Cuno", "Curd", "Curt", "Curtis", "Cyril", "Cyrill", "Cæcilius", "Cæsar", "Daen", "Dagfin", "Dagfinn",
        "Dagh", "Damian", "Dani", "Daniel", "Daniell", "Danilo", "Danjal", "Dann", "Danner", "Danni", "Dannie", "Danny",
        "Dany", "Dargo", "Dario", "Darnell", "Darvin", "Darwin", "Dave", "David", "Davie", "Davy", "Dean", "Denis", "Deniz",
        "Denni", "Dennik", "Dennis", "Denny", "Derek", "Derri", "Derrick", "Derry", "Desmond", "Detlef", "Detlev", "Detmer",
        "Devin", "Dich", "Dick", "Dicky", "Diderich", "Diderik", "Didrich", "Didrik", "Diego", "Dieter", "Dietrich", "Dimitri",
        "Dimitrij", "Dimy", "Dines", "Dinny", "Dino", "Dion", "Dirch", "Dirk", "Ditlef", "Ditlev", "Ditmar", "Ditmer", "Ditz",
        "Dominic", "Donald", "Donni", "Donny", "Douglas", "Dres", "Dreves", "Drevs", "Dusinius", "Dusinus", "Dylan", "Dynes",
        "Earl", "Ebbe", "Ebbi", "Eber", "Eberhard", "Eberhardt", "Echardt", "Eckardt", "Eckhard", "Eckhardt", "Eddi", "Eddy",
        "Edelhard", "Edelhardt", "Edelhart", "Edgar", "Edgard", "Edgardo", "Edlef", "Edmond", "Edmund", "Edouard", "Eduard",
        "Eduardo", "Edvard", "Edvardt", "Edvin", "Edvind", "Edward", "Edwardt", "Edwin", "Efraim", "Egan", "Egbert", "Egert",
        "Eggert", "Egil", "Egild", "Egill", "Egmont", "Egmund", "Egmunt", "Egon", "Egun", "Egund", "Ehler", "Ehlert", "Eide",
        "Eigil", "Eigild", "Eigill", "Eilef", "Eiler", "Eilert", "Eilev", "Eilif", "Einar", "Einer", "Einert", "Eino", "Eirik",
        "Eitel", "Eivin", "Eivind", "Ejgil", "Ejgild", "Ejlar", "Ejlef", "Ejler", "Ejlert", "Ejlev", "Ejlif", "Ejnar", "Ejner",
        "Ejnert", "Ejno", "Ejolf", "Ejvin", "Ejvind", "Ekhard", "Ekhardt", "Elaf", "Elan", "Elav", "Elef", "Elert", "Elfred",
        "Elhardt", "Elias", "Elif", "Elimar", "Eliot", "Elisius", "Elit", "Elith", "Elius", "Elliott", "Elmar", "Elmer",
        "Elmo", "Elmund", "Elof", "Elon", "Elton", "Eluf", "Elvar", "Elvard", "Elver", "Elvin", "Elvind", "Elvinus", "Elvis",
        "Elwin", "Emanuel", "Emiel", "Emil", "Emile", "Emilio", "Emilius", "Emmanuel", "Emmerich", "Emmerik", "Emmert",
        "Emmery", "Emmik", "Emus", "Enevold", "Engelbert", "Engelbrecht", "Engelbrekt", "Engelhard `", "Engelhardt", "Enno",
        "Enoch", "Enok", "Enrico", "Enrique", "Enver", "Enzo", "Erasmus", "Erhard", "Erhardt", "Erhart", "Eric", "Erich",
        "Erick", "Erico", "Erik", "Eriko", "Erin", "Erkki", "Erlan", "Erland", "Erlin", "Erlind", "Erling", "Erly", "Ernest",
        "Ernfred", "Ernhard", "Ernhardt", "Erno", "Ernst", "Eron", "Errit", "Errol", "Ervin", "Ervind", "Erwill", "Erwin",
        "Erwind", "Erwing", "Esaias", "Esajas", "Esben", "Esbern", "Esbjørn", "Esge", "Esger", "Eske", "Eskil", "Eskild",
        "Espen", "Esper", "Ethan", "Etlar", "Eug#Ne", "Eugen", "Eugène", "Evald", "Evan", "Even", "Evert", "Evin", "Evind",
        "Ewald", "Ewan", "Ewert", "Eylef", "Eylev", "Eylif", "Eyolf", "Eyvin", "Eyvind", "Fabian", "Falentin", "Falk", "Falke",
        "Faustin", "Faustino", "Fedder", "Feliks", "Felix", "Feodor", "Ferdinand", "Fernando", "Ferry", "Filip", "Fillip",
        "Find", "Fini", "Finn", "Finni", "Finnur", "Fjodor", "Flemming", "Florian", "Fodor", "Folke", "Folker", "Folkvar",
        "Folkvard", "Folmar", "Folmer", "Francesco", "Franch", "Francis", "Francisco", "Franck", "Francois", "Frands", "Frank",
        "Franker", "Frankie", "Franklin", "Frans", "Fransisco", "Frants", "Frantz", "Franz", "Fred", "Freddi", "Freddie",
        "Freddy", "Frede", "Frederic", "Frédéric", "Frederich", "Frederick", "Frederik", "Fredi", "Fredrik", "Fredy",
        "Freilev", "Freilif", "Frej", "Frejlev", "Frevlif", "Frey", "Fribo", "Fridlev", "Fridolf", "Fridolin", "Fridthjof",
        "Fridtjof", "Friederich", "Friedrich", "Frilo", "Frithiof", "Frithjof", "Fritjof", "Frits", "Fritz", "Frode",
        "Gabriel", "Gary", "Gaston", "Gaute", "Gavin", "Geert", "Geir", "Gejr", "Geno", "Geoffrey", "Georg", "George",
        "Georges", "Georgios", "Gerald", "Geran", "Gerbrand", "Gerdt", "Gerhard", "Gerhardt", "Gerhart", "Gerlak", "Gerland",
        "Gerlef", "Gerluf", "German", "Germann", "Germind", "Germund", "Gerner", "Gerrit", "Gerrith", "Gerry", "Gerson",
        "Gert", "Gerth", "Gerulf", "Gervind", "Gian", "Giancarlo", "Gianni", "Giannino", "Gilbert", "Gino", "Giovanni",
        "Giulio", "Giuseppe", "Glen", "Glenn", "Glenny", "Gleve", "Godfred", "Godik", "Godske", "Godtfred", "Godvin", "Gomme",
        "Gordon", "Gorm", "Gotfred", "Gothard", "Gothardt", "Gotlieb", "Gottfred", "Gottfried", "Gotthard", "Gotthardt",
        "Gottlieb", "Gravers", "Graves", "Greg", "Greger", "Gregers", "Gregor", "Gregorius", "Greif", "Grejs", "Gudbjørn",
        "Gudik", "Gudman", "Gudmand", "Gudmar", "Gudmund", "Guido", "Gullak", "Gumme", "Gunder", "Gunnar", "Gunner", "Gunni",
        "Gunnulf", "Guno", "Gunter", "Gunther", "Gunvald", "Gustaf", "Gustav", "Gustave", "Guttorm", "Gynter", "Gynther",
        "Göran", "Gösta", "Gøtz", "Götz", "Hagbard", "Hagbart", "Hagbarth", "Hagen", "Haggi", "Hajo", "Hakki", "Hakon",
        "Haldor", "Haldur", "Halfdan", "Halfred", "Halldor", "Halvard", "Halvdan", "Halvor", "Hamid", "Hamund", "Hannibal",
        "Hanno", "Hans", "Hansmorten", "Harald", "Harding", "Hardy", "Harley", "Harly", "Harold", "Harri", "Harris", "Harro",
        "Harry", "Hartvig", "Hartwig", "Hasan", "Hassan", "Hasse", "Hassi", "Hauge", "Havard", "Hector", "Hegen", "Hegn",
        "Heikki", "Heiko", "Heile", "Heimer", "Hein", "Heine", "Heiner", "Heines", "Heini", "Heinike", "Heino", "Heinrich",
        "Heinrick", "Heinrik", "Heins", "Heintje", "Heinz", "Heise", "Hejne", "Hektor", "Helfred", "Helge", "Helgi", "Helgo",
        "Hellmut", "Hellmuth", "Helman", "Helmar", "Helmer", "Helmut", "Helmuth", "Hemming", "Hendrik", "Henning", "Henri",
        "Henrich", "Henrik", "Henry", "Herbert", "Heri", "Herluf", "Herman", "Hermann", "Hermod", "Herold", "Hervard",
        "Hieronimus", "Hieronymus", "Hilbert", "Hildebrand", "Hilding", "Hilmar", "Hilmer", "Hjalmar", "Hjalmer", "Hjalte",
        "Hjarn", "Hjarne", "Hoder", "Hold", "Holger", "Holm", "Holme", "Horst", "Hother", "Hovard", "Howard", "Hroar", "Hrolf",
        "Hubert", "Hugh", "Hugi", "Hugin", "Hugo", "Humphrey", "Høgne", "Hågen", "Haagen", "Håkon", "Haakon", "Håvard",
        "Haavard", "Ibrahim", "Ignatius", "Ignazio", "Igor", "Imanuel", "Immanuel", "Imran", "Ingbert", "Ingeman", "Ingemann",
        "Ingemar", "Ingemund", "Ingevald", "Ingi", "Ingmar", "Ingmund", "Ingo", "Ingolf", "Ingvald", "Ingvar", "Ingvard",
        "Ingvardt", "Ingvart", "Ingver", "Ingvert", "Ingvor", "Ioannis", "Irving", "Irwing", "Isaac", "Isac", "Isak",
        "Isegrim", "Isidor", "Istvan", "Ivan", "Ivar", "Iver", "Iwan", "Iwar", "Iwer", "Iørgen", "Jachim", "Jack", "Jackie",
        "Jacko", "Jacky", "Jacob", "Jacques", "Jafet", "Jahn", "Jaime", "Jais", "Jakob", "Jall", "Jalte", "Jamal", "James",
        "Janech", "Janek", "Janich", "Janick", "Jann", "Jannek", "Jannic", "Jannich", "Jannick", "Jannik", "Jannis", "Jano",
        "Janos", "Jansi", "Janus", "Jappe", "Jari", "Jaris", "Jarl", "Jarle", "Jarne", "Jarnik", "Jarnis", "Jarno", "Jaron",
        "Jason", "Jasper", "Jean", "Jeff", "Jeffi", "Jeffrey", "Jeffy", "Jegor", "Jegvan", "Jeko", "Jens", "Jeppe", "Jeremias",
        "Jeremy", "Jerik", "Jerk", "Jerome", "Jeronimus", "Jerri", "Jerrik", "Jerry", "Jesper", "Jess", "Jesse", "Jimi",
        "Jimm", "Jimmi", "Jimmy", "Joachim", "Joacim", "Joah", "Joakim", "Jochum", "Jock", "Joel", "Joël", "Joen", "Johan",
        "Johann", "Johannes", "John", "Johni", "Johnni", "Johnny", "Johny", "Johs", "Jokum", "Jonas", "Jonatan", "Jonathan",
        "Jonny", "Joos", "Joram", "Joran", "Jordy", "Jorge", "Jorn", "Jorry", "Jorst", "José", "Josef", "Joseph", "Josh",
        "Joshua", "Joshva", "Josiah", "Josias", "Jost", "Josua", "Josva", "Jovan", "Jozef", "Juan", "Jules", "Julian",
        "Julius", "Juri", "Jussi", "Just", "Justian", "Justin", "Justus", "Jøgvan", "Jøren", "Jørg", "Jørgen", "Jørk",
        "Jørlief", "Jørn", "Jaan", "Kain", "Kaino", "Kaleb", "Kali", "Kalle", "Kamillo", "Kamo", "Karel", "Kari", "Karim",
        "Karl", "Karle", "Karlis", "Karljohan", "Karlo", "Karnis", "Karno", "Karol", "Karolus", "Karsten", "Kasimir", "Kasmir",
        "Kaspar", "Kasper", "Kastor", "Kean", "Keen", "Kees", "Keith", "Keld", "Kell", "Kelli", "Kelly", "Kelvin", "Kemal",
        "Kenan", "Kenian", "Kenn", "Kennart", "Kennert", "Kennerth", "Kennet", "Kenneth", "Kennett", "Kenney", "Kenni",
        "Kennith", "Kenno", "Kenny", "Kent", "Kenth", "Kento", "Kenwin", "Kermit", "Kern", "Kerner", "Ketel", "Ketil", "Kevin",
        "Kevyn", "Kewan", "Kewin", "Kian", "Kield", "Kiljon", "Killian", "Kimm", "Kimmo", "Kimmy", "Kimo", "Kinn", "Kion",
        "Kirstein", "Kirstejn", "Kito", "Kivin", "Kiwin", "Kjartan", "Kjeld", "Kjell", "Kjertan", "Klaes", "Klas", "Klaudi",
        "Klaudius", "Klaus", "Klaves", "Klavs", "Klemen", "Klemens", "Klement", "Klemmen", "Knud", "Knut", "Kolbjørn",
        "Konrad", "Konstantin", "Kord", "Kore", "Korfits", "Korfitz", "Kornelius", "Kort", "Kosmus", "Krei", "Kren", "Kresten",
        "Krester", "Krestian", "Krestoffer", "Krilles", "Kris", "Kristen", "Krister", "Kristian", "Kristjan", "Kristof",
        "Kristofer", "Kristoffer", "Kræn", "Kræsten", "Krøj", "Kuan", "Kuno", "Kunz", "Kurd", "Kurt", "Kurth", "Kåre", "Kaare",
        "Lage", "Lambert", "Lance", "Landry", "Lanni", "Lari", "Larry", "Lars", "Lasarus", "Lass", "Lasse", "Laue", "Lauge",
        "Launy", "Laurens", "Laurentius", "Lauri", "Laurids", "Laurits", "Lauritz", "Laurs", "Laus", "Laust", "Lavard", "Lave",
        "Lavrids", "Lavrits", "Lavritz", "Lavrs", "Lavst", "Lean", "Leander", "Leck", "Legart", "Leib", "Leif", "Leiff",
        "Lejf", "Lejff", "Lemmy", "Lenarth", "Lennard", "Lennart", "Lennarth", "Lennert", "Lenni", "Lennon", "Lenny", "Leon",
        "Leonard", "Leonhard", "Leonhardt", "Leonhart", "Leopold", "Leslie", "Lester", "Levi", "Levy", "Lewi", "Liam", "Lindy",
        "Linus", "Lionel", "Lorens", "Lorents", "Lorentz", "Lorentzo", "Lorenz", "Lorenzo", "Lorry", "Lothar", "Loui", "Louie",
        "Louis", "Lucas", "Lucien", "Ludolf", "Ludolph", "Ludvig", "Ludwig", "Ludwik", "Luis", "Lukas", "Luke", "Lyder",
        "Lydolf", "Lynge", "Mack", "Mads", "Magal", "Magne", "Magni", "Magnus", "Mahmoud", "Mahmud", "Majid", "Maks",
        "Malakias", "Malcolm", "Malte", "Malthe", "Manderup", "Mandrup", "Manfred", "Manly", "Manne", "Manneke", "Mannfred",
        "Manuel", "Marc", "Marcel", "Marcelius", "Marcell", "Marcello", "Marchel", "Marck", "Marco", "Marcos", "Marcus",
        "Mariano", "Marinus", "Mario", "Marius", "Mark", "Markild", "Marko", "Markus", "Markvar", "Markvard", "Marni", "Marno",
        "Marthen", "Marthin", "Marthinus", "Marti", "Martin", "Martinez", "Martinius", "Martinus", "Martti", "Marty", "Marvin",
        "Marx", "Mass", "Massimo", "Matfus", "Mathfus", "Mathias", "Mathies", "Mathinus", "Mathis", "Matias", "Matis", "Mats",
        "Matt", "Matthes", "Matthfus", "Matthias", "Matthies", "Matthis", "Matthæus", "Matti", "Mattias", "Mattis", "Matts",
        "Mattæus", "Matæus", "Mauri", "Maurice", "Maurits", "Mauritz", "Maxim", "Maxime", "Maximilian", "Maximillian", "Meck",
        "Meik", "Meikey", "Meiner", "Meinert", "Meinhard", "Meino", "Meir", "Mejner", "Mejnert", "Melchior", "Melkior",
        "Melvin", "Meno", "Merlan", "Micael", "Micas", "Mich", "Michael", "Michaell", "Michal", "Michel", "Michell", "Michlas",
        "Mick", "Mickei", "Mickey", "Micki", "Micklas", "Micky", "Mico", "Miguel", "Mihail", "Mikael", "Mikal", "Mike",
        "Mikelis", "Mikey", "Miki", "Mikk", "Mikkel", "Mikki", "Mikko", "Mikllas", "Miklos", "Milan", "Milius", "Millo",
        "Milo", "Milos", "Milter", "Milton", "Mingo", "Minik", "Mirko", "Miro", "Mitchell", "Mogens", "Mohamed", "Molte",
        "Momme", "Monne", "Monty", "Morgan", "Morits", "Moritz", "Morten", "Morthen", "Mortimer", "Morton", "Moses", "Mourids",
        "Mourits", "Mouritz", "Moust", "Muhammad", "Mylius", "Mårten", "Napoleon", "Natan", "Natanael", "Nataniel", "Nathan",
        "Nathanael", "Nathaniel", "Neal", "Neil", "Nelas", "Nels", "Nevil", "Neville", "Niall", "Nichi", "Nichlas", "Nicholai",
        "Nicholaj", "Nicholas", "Nici", "Nick", "Nicki", "Nicklas", "Nicklaus", "Nicklavs", "Nicko", "Nickolai", "Nickolaj",
        "Nickolass", "Nickolei", "Nicky", "Niclas", "Nico", "Nicodemus", "Nicol", "Nicolai", "Nicolaj", "Nicolas", "Nicolaus",
        "Nicolay", "Nicolei", "Nicolej", "Nicolo", "Niel", "Niels", "Nigel", "Nikas", "Nikhil", "Niki", "Nikki", "Niklas",
        "Niklaus", "Niko", "Nikolai", "Nikolaj", "Nikolas", "Nikolaus", "Nikolej", "Nilas", "Nilaus", "Nilavs", "Nils", "Nino",
        "Niss", "Nivan", "Njal", "Noah", "Noal", "Noel", "Noël", "Norbert", "Norman", "Normann", "Obert", "Octavio",
        "Octavius", "Oddvar", "Odger", "Odin", "Odlev", "Odmar", "Odsten", "Olaf", "Olai", "Olaj", "Olander", "Olau", "Olaus",
        "Olav", "Olavur", "Oleg", "Olen", "Olfert", "Oliver", "Olle", "Olof", "Olov", "Oluf", "Omar", "Orla", "Orlando",
        "Oscar", "Oskar", "Osman", "Osmann", "Osmar", "Osmund", "Ossian", "Osvald", "Oswald", "Othar", "Othmar", "Otmann",
        "Otmar", "Ottar", "Otte", "Otto", "Ovard", "Owen", "Oyvind", "Paavo", "Pablo", "Paco", "Paick", "Paie", "Pall",
        "Palle", "Palmer", "Parly", "Parmo", "Pascal", "Patrich", "Patrick", "Patrik", "Pauel", "Paul", "Pauli", "Paulli",
        "Paulo", "Paulus", "Pawel", "Peder", "Pedro", "Peer", "Pehr", "Peik", "Peiter", "Pejk", "Pele", "Pelle", "Penti",
        "Pentti", "Percy", "Perry", "Pete", "Peter", "Petri", "Petro", "Petrus", "Petter", "Philip", "Philippe", "Phillip",
        "Pierre", "Piet", "Pieter", "Pietro", "Piotr", "Pitter", "Pius", "Pouel", "Poul", "Pouli", "Poulus", "Povel", "Povl",
        "Preben", "Predbjørn", "Prosper", "Quintus", "Qvan", "Qvintus", "Racid", "Radolf", "Radulf", "Rafael", "Ragn",
        "Ragnar", "Ragner", "Ragnvald", "Raimo", "Raimond", "Raimund", "Rainar", "Rainer", "Raini", "Rainier", "Raki", "Ralf",
        "Ralff", "Ralph", "Rami", "Ramon", "Randolf", "Randolph", "Randy", "Rane", "Ranulf", "Raphael", "Rasmus", "Rass",
        "Raul", "Rauno", "Ravn", "Raymond", "Raymund", "Rayner", "Regin", "Reginald", "Regnar", "Regner", "Regnvald",
        "Reichard", "Reidar", "Reider", "Reier", "Reimar", "Reimer", "Reimert", "Reimond", "Reinar", "Reiner", "Reinert",
        "Reinhard", "Reinhardt", "Reinhold", "Reinholdt", "Reinholt", "Reino", "Reinold", "Reinvald", "Rejer", "Rejmar",
        "Rejmer", "Rejnar", "Rejner", "Rejnert", "Rejnhard", "Rejnhardt", "Rejnhart", "Rejnhold", "Rejnholdt", "Rejnholt",
        "Remi", "Remo", "Remon", "Remond", "Remus", "Remy", "Renato", "Renatus", "Rene", "Rennie", "Renny", "Reno", "Reto",
        "Reymond", "Reynold", "Riber", "Ricard", "Ricardo", "Ricardt", "Ricco", "Richard", "Richardo", "Richardt", "Richart",
        "Richo", "Rickard", "Rickey", "Ricki", "Rickie", "Ricko", "Ricky", "Rico", "Rief", "Riff", "Rigo", "Rikard", "Rikardt",
        "Rikki", "Rikko", "Riko", "Rino", "Roald", "Roall", "Roan", "Roar", "Robbert", "Robbi", "Robbin", "Robby", "Robert",
        "Roberto", "Robin", "Rocco", "Rocko", "Rocky", "Roer", "Roger", "Rogert", "Rokil", "Roland", "Rolf", "Rolff", "Rollo",
        "Rom-O", "Roman", "Romann", "Romano", "Romanus", "Romeo", "Roméo", "Ronald", "Ronaldo", "Rone", "Ronn", "Ronni",
        "Ronnie", "Ronny", "Rousdi", "Royni", "Ruar", "Ruben", "Rubert", "Ruddi", "Ruddy", "Rudi", "Rudolf", "Rudolph", "Rudy",
        "Rufus", "Rukan", "Rumle", "Runar", "Rune", "Runi", "Runo", "Rupert", "Rurik", "Rustan", "Ryan", "Ryolf", "Sacho",
        "Sakarias", "Sakse", "Sakso", "Salomon", "Salum", "Salvatore", "Sammi", "Sammy", "Samo", "Samson", "Samuel", "Sander",
        "Sandi", "Sandor", "Sandro", "Sandy", "Sanny", "Santino", "Sarino", "Sasser", "Saxe", "Saxo", "Scott", "Sean",
        "Sebald", "Sebastian", "Sebbe", "Seier", "Seir", "Sejer", "Sejner", "Sejr", "Sekstus", "Selgen", "Selius", "Selmar",
        "Selmer", "Semmy", "Senius", "Sennik", "Sepp", "Seppo", "Serge", "Sergei", "Seth", "Sevald", "Sevart", "Severin",
        "Sextus", "Sfmund", "Shahid", "Shane", "Shaun", "Shawn", "Sidney", "Siegfrid", "Siegfried", "Siegmund", "Sievert",
        "Sigbert", "Sigbjørn", "Sigbrand", "Sigbrandt", "Siger", "Sigfred", "Sigfrid", "Sigfried", "Sigfus", "Sigge", "Sigger",
        "Siggi", "Sigismund", "Sigmund", "Sigtryg", "Sigurd", "Sigurt", "Sigvald", "Sigvard", "Sigvardt", "Sigvart", "Sikker",
        "Sikstus", "Silas", "Siliam", "Silius", "Sillas", "Simen", "Simeon", "Simion", "Simoen", "Simon", "Sinius", "Sinus",
        "Sivald", "Sivart", "Sivel", "Sivert", "Sixten", "Sixtus", "Skafte", "Skanni", "Skanny", "Skat", "Skjalm", "Skjold",
        "Skuli", "Snorre", "Snorri", "Sofus", "Sonni", "Sonnich", "Sonny", "Sophus", "Staffan", "Staffen", "Stan", "Stanislau",
        "Stanislav", "Stanislaw", "Stanley", "Stanly", "Stanny", "Steen", "Stefan", "Stefano", "Steff", "Steffan", "Steffen",
        "Steinar", "Steinbjørn", "Stellan", "Sten", "Stener", "Stephan", "Stephen", "Stevan", "Steve", "Steven", "Stig",
        "Stiig", "Stinus", "Stiven", "Stone", "Strange", "Stuart", "Stubbe", "Sture", "Sune", "Suni", "Svante", "Svein",
        "Sveinn", "Sven", "Svend", "Svende", "Svenege", "Svenn", "Svenne", "Svenning", "Svenåge", "Svenaage", "Sverke",
        "Sverkel", "Sverre", "Sverri", "Sylvest", "Sylvester", "Syrach", "Syrak", "Sæmund", "Sølver", "Sønnich", "Sønnik",
        "Søren", "Sørinus", "Tade", "Tage", "Tago", "Taico", "Tais", "Tajco", "Tajs", "Tamas", "Tammes", "Tano", "Tapio",
        "Tarben", "Tarek", "Tarik", "Taus", "Tave", "Tavs", "Teddi", "Teddy", "Teis", "Teit", "Tejn", "Tejs", "Temme", "Temmi",
        "Teobald", "Teodor", "Terje", "Terkel", "Terkil", "Terkild", "Terman", "Termann", "Terri", "Terry", "Teys", "Thadæus",
        "Thage", "Thais", "Tharben", "Theis", "Theiss", "Thejs", "Them", "Theo", "Theobald", "Theodor", "Theon", "Therkel",
        "Therkil", "Therkild", "Therman", "Thim", "Thimotheus", "Thinus", "Thio", "Thoke", "Thom", "Thomas", "Thommas",
        "Thommy", "Thonny", "Thony", "Thor", "Thorald", "Thoralf", "Thorben", "Thorbjørn", "Thord", "Thore", "Thorer",
        "Thorfin", "Thorfinn", "Thorgny", "Thorgot", "Thorgrim", "Thorkel", "Thorkil", "Thorkild", "Thorlak", "Thorleif",
        "Thorlejf", "Thorlejv", "Thorlek", "Thormod", "Thormund", "Thorolf", "Thorstein", "Thorsteinn", "Thorsten", "Thorulf",
        "Thorvald", "Thorvil", "Thorvild", "Thorwald", "Thue", "Thune", "Thure", "Thyge", "Thøge", "Thøger", "Tias", "Tibor",
        "Ticho", "Till", "Tilo", "Timm", "Timme", "Timmi", "Timmo", "Timmy", "Timo", "Timoteus", "Timothy", "Tinny", "Tino",
        "Tinus", "Tito", "Tjalfe", "Tjelle", "Tjørk", "Tjørn", "Tobby", "Tobias", "Toby", "Togi", "Toivo", "Toke", "Toki",
        "Tolvar", "Tolver", "Tomas", "Tomasz", "Tomm", "Tommas", "Tommi", "Tommy", "Tonnes", "Tonni", "Tonnis", "Tonny",
        "Tony", "Toralf", "Torben", "Torbjørn", "Tord", "Tordur", "Tore", "Torfin", "Torfinn", "Torgni", "Torgny", "Torgot",
        "Torgrim", "Torin", "Torjus", "Torke", "Torkel", "Torkil", "Torkild", "Torlak", "Torleif", "Torlek", "Torlif", "Torm",
        "Tormod", "Torre", "Torry", "Torstein", "Torsteinn", "Torsten", "Torulf", "Torvald", "Torvil", "Torvild", "Torvind",
        "Toste", "Tristan", "Troels", "Trohn", "Trond", "Truels", "Truls", "Trygve", "Trym", "Tuco", "Tume", "Tune", "Ture",
        "Turf", "Turmund", "Tycho", "Tyge", "Tøger", "Tønnes", "Tørk", "Ubbe", "Uffe", "Uffo", "Ulfbjørn", "Ulff", "Ulric",
        "Ulrich", "Ulrick", "Ulrik", "Ulver", "Unne", "Urban", "Vaclav", "Vagn", "Vagnar", "Vagner", "Vaino", "Valdemar",
        "Valder", "Valdo", "Valdus", "Valentin", "Valfred", "Valfrid", "Valter", "Valther", "Vandy", "Varny", "Veijo",
        "Veikko", "Venzel", "Vermund", "Verner", "Vernon", "Vesti", "Vesty", "Vicente", "Victor", "Vidar", "Vidrik", "Vifil",
        "Vigan", "Vigand", "Vigandt", "Vigant", "Vigfus", "Vigge", "Viggo", "Vigil", "Viking", "Viktor", "Vilars", "Vilbert",
        "Vildfred", "Vilfred", "Vilhelm", "Villads", "Villars", "Villi", "Villiam", "Villum", "Villy", "Vilmar", "Vilmer",
        "Vilni", "Vincens", "Vincent", "Vinfred", "Virgil", "Virtus", "Viste", "Visti", "Vithar", "Vithard", "Vithardt",
        "Vithus", "Vito", "Vittus", "Vitus", "Vladimir", "Volmar", "Volmer", "Våge", "Vaage", "Waclaw", "Wagn", "Wagner",
        "Wahid", "Waldemar", "Walder", "Walentin", "Walenty", "Walid", "Walter", "Walther", "Wandy", "Warny", "Wenzel",
        "Werner", "Wernon", "Wictor", "Widrik", "Wigan", "Wiggo", "Wiktor", "Wilbert", "Wildfred", "Wilfred", "Wilfried",
        "Wilhelm", "Willads", "Willem", "Willi", "William", "Willum", "Willy", "Wilmar", "Wilmer", "Winn", "Wisti", "Witte",
        "Wladimir", "Wladislaw", "Wladyslaw", "Wolfgang", "Wolmer", "Wåge", "Waage", "Xenius", "Yann", "Yannick", "Yannik",
        "Yngvar", "Yngvard", "Yngve", "Younes", "Yuri", "Yvan", "Yves", "Zacharias", "Zakarias", "Zaki", "Zanno", "Zapp",
        "Zean", "Zenius", "Zeth", "Ziad", "Zier", "Zilas", "Zimon", "Zoltan", "Zoran", "Zygmund", "Øivind", "Øjvind", "Ørni",
        "Ørnulf", "Øssur", "Østen", "Øvli", "Øystein", "Øyvind", "Åbjørn", "Aabjørn", "Aage", "Åmund", "Aamund", "Aaron",
        "Åsmund", "Aasmund", "Abbelone", "Abbie", "Abeline", "Abelone", "Abigael", "Abigail", "Achena" };
    
}
