package forevtechnologies.alegriauiux.models;
public enum
Events{
    RC("Robo Challenge (Island Robos)"),
    TPP("Technical Paper Presentation (The Mumbai letter)"),
    MFW("Make from waste ()"),
    CS("Code Surfer (Code Bombay)"),
    TR("Tech Roadies ()"),
    HT("Hackathon"),//0-5TECH
    NRIT("Classical Solo (Nrityasamrat)"),
    DH("Free Style Solo (Fatak se matak)"),
    BBy("B-Boying (Sadak Nach)"),
    FoG("Folk Group (Taandav)"),
    WoDJ("War of DJ's (Turntables)"),
    FSG("Free Style Group (Beat pe feat)"),
    SKT("Skit (Skecz)"),
    MA("Mono Acting"),
    RAP("Parappa"),
    DD("Duet Dancing (Gem of the City"),
    SoSi("Solo Singing (Alegrian Idol)"),
    OM("Open Mic"),
    BB("Beat Boxing"),//6-18PARTS
    FOA("The Face of Alegria"),
    MMA("Mr & Mrs Alegria"),
    FiP("Fitness Physique (Musclemania Mumbai)"),
    FS("Fashion Show (Fashion Street)"),
    TH("Treasure Hunt (Gem of the City)"),
    PhE("Photography (Fotothon)"),
    FFF("Fastest Finger First"),
    PTVR("PTV Roadies"),
    FAT("Food-A-Thon"),//19-27INF
    TD("3D Printing"),
    WD("Web Designing"),
    AA("Arduino Advance"),
    IOT("IOT"),
    PY("Python"),
    DP("Drone Piloting"),
    KT("Kotlin"),
    FG("Firebase by Google"),
    TW("Theatre Workshop"),
    CG("Calligraphy"),
    ZM("Zumba"),
    SA("Stippling Art"),
    DJW("DJ Workshop"),
    SSA("Salsa"),
    RCB("Rubiks Cube"),
    TT("Tutting"),
    PH("Photography Workshop"),
    RS("Rifle Shooting"),//28-45WS
    FIFA("FIFA"),
    NFS("NFS Most Wanted"),
    MM("Mini Militia"),
    VrFN("VR Fruit Ninja"),
    LuKi("Ludo King"),
    FB("Futsal Boys"),
    BBL("Basketball"),
    VB("Volleyball"),
    BC("Box Cricket"),
    TOW("Tug of War"),
    TTN("Table Tennis"),
    ARS("Air Rifle Shooting"),
    CHS("Chess"),
    FCQ("Football & Cricket Quiz"),
    CRMS("Carrom Singles"),
    CRMD("Carrom Doubles"),
    KBDI("Kabaddi"),
    NS("Neo Shootout"),
    BDTG("Badminton Girls"),
    BDTB("Badminton Boys"),
    NC("Neo Cricket"),
    CSGO("Counter Strike Global Offensive"),
    LDO("Ludo"),
    VRFN("VR Fruit Ninja"),//46-68SNG
    PM("Poster Mania (Phata Poster)"),
    CP("Canvas Painting (Rang De Mumbai)"),
    RM("Rangoli Making (Rang Manch)"),
    MD("Mehendi Designing (Atrangi Patterns)"),
    FP("Face Painting (Pehchan Kaun?)"),
    NA("Nail Art (Nail Lit)"),
    TsP("T-Shirt Painting (Dhobi Ghat)"),
    SkC("Sketching (Chitrakala"),//69-76FARTS
    ADMD("Admad(Ad-Central)"),
    MQ("Management Quiz (Corporate Buzz)"),
    BM("Best Manager (BKC-Bambai ka Corporate)"),
    MTH("Management Treasure Hunt (Gateway to Diamond Valley)"),
    CSTD("Case Study (Mumbai Manifest)"),
    LMS("Live Mock Stock (Dalal Street)"),//77-82MGMT
    ELOC("Elocution (Bol Bachchan)"),
    QZ("Quiz (Bombay HEAT)"),
    ESSY("Essay Writing (Bombai's Tale)"),
    SB("Spelling Bee (Word Of Nerds)"),
    EnDe("English Debate (Gupshup)"),
    PoWr("Poem Writing (An Ode To Mumbai)");//83-88LARTS


    private final String event;

    Events(String event) {this.event=event;}

    public String getEvents(){return event;}

}