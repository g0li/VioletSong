package forevtechnologies.alegriauiux.models;
public enum EventsPaisa {
    RM("Rangoli Making (Rang Manch)"),//50 0-13
    MD("Mehendi Designing (Atrangi Patterns)"),
    FP("Face Painting (Pehchan Kaun?)"),
    NA("Nail Art (Nail Lit)"),
    SkC("Sketching (Chitrakala"),
    ELOC("Elocution (Bol Bachchan)"),
    ESSY("Essay Writing (Bombai's Tale)"),
    SB("Spelling Bee (Word Of Nerds)"),
    PoWr("Poem Writing (An Ode To Mumbai)"),
    FFF("Fastest Finger First"),
    VRFN("VR Fruit Ninja"),
    LuKi("Ludo King"),
    NS("Neo Shootout"),
    AA("Arduino Advance"),//100 14-38
    IOT("IOT"),
    ZM("Zumba"),
    SA("Stippling Art"),
    RS("Rifle Shooting"),
    ADMD("Admad(Ad-Central)"),
    MQ("Management Quiz (Corporate Buzz)"),
    BM("Best Manager (BKC-Bambai ka Corporate)"),
    CSTD("Case Study (Mumbai Manifest)"),
    CS("Code Surfer (Code Bombay)"),
    TR("Tech Roadies ()"),
    PM("Poster Mania (Phata Poster)"),
    QZ("Quiz (Bombay HEAT)"),
    EnDe("English Debate (Gupshup)"),
    OM("Open Mic"),
    BB("Beat Boxing"),
    FAT("Food-A-Thon"),
    FIFA("FIFA"),
    NFS("NFS Most Wanted"),
    BDTG("Badminton Girls"),
    BDTB("Badminton Boys"),
    CHS("Chess"),
    TTN("Table Tennis"),
    CRMS("Carrom Singles"),
    FCQ("Football & Cricket Quiz"),
    KT("Kotlin"),//150 39-54
    TW("Theatre Workshop"),
    CG("Calligraphy"),
    SSA("Salsa"),
    RCB("Rubiks Cube"),
    TT("Tutting"),
    PH("Photography"),
    MTH("Management Treasure Hunt (Gateway to Diamond Valley)"),
    LMS("Live Mock Stock (Dalal Street)"),
    BBy("B-Boying (Sadak Nach)"),
    MA("Mono Acting"),
    RAP("Parappa"),
    PhE("Photography(Fotothon)"),
    PTVR("PTV Roadies"),
    MM("Mini Militia"),
    ARS("Air Rifle Shooting"),
    WD("Web Designing"), //200 55-66
    PY("Python"),
    FG("Firebase by Google"),
    DJW("DJ Workshop"),
    TPP("Technical Paper Presentation (The Mumbai letter)"),
    CP("Canvas Painting (Rang De Mumbai)"),
    TsP("T-Shirt Painting (Dhobi Ghat)"),
    NRIT("Classical Solo (Nrityasamrat)"),
    SKT("Skit (Skecz)"),
    MMA("Mr & Mrs Alegria"),
    FiP("Fitness Physique (Musclemania Mumbai)"),
    CRMD("Carrom Doubles"),
    TD("3D Printing"),//250 67-70
    DH("Free Style Solo (Fatak se matak)"),
    SoSi("Solo Singing (Alegrian Idol)"),
    TOW("Tug of War"),
    DP("Drone Piloting"),//300 71-78
    RC("Robo Challenge (Island Robos)"),
    MFW("Make from waste ()"),
    WoDJ("War of DJ's (Turntables)"),
    DD("Duet Dancing (Gem of the City"),
    FOA("The Face of Alegria"),
    TH("Treasure Hunt (Gem of the City)"),
    BBL("Basketball"),
    CSGO("Counter Strike Global Offensive"), //350 79
    FoG("Folk Group (Taandav)"),//500 80-81
    VB("Volleyball"),
    KBDI("Kabaddi"), //600 82
    FSG("Free Style Group (Beat pe feat)"),//1000 83-85
    FB("Futsal Boys"),
    BC("Box Cricket"),
    FS("Fashion Show (Fashion Street)"),//2500  86
    HT("Hackathon");//0 87




    private final String event;

    EventsPaisa(String event) {this.event=event;}

    public String getEvents(){return event;}

}