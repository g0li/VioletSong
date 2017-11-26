package forevtechnologies.alegriauiux.models;
public enum     Events{
    RC("Robo Challenge"),
    TPP("Technical Paper Presentation"),
    JW("Junkyard War"),
    CS("Code Surfer"),
    TR("Tech Roadies"),
    HT("Hackathon"),//0-5TECH
    NRIT("Nrityanjali"),
    DH("Dance Hero"),
    MRO("Mario"),
    TDV("Taandav"),
    TTB("Turntables"),
    JDN("Just Dance Now"),
    SKZ("Skecz"),
    FC("Faces"),
    PPA("Parappa"),
    MYM("Make Your Move"),
    VOA("The Voice of Alegria"),
    OM("Open Mic"),
    BB("Beat Boxing"),//6-18PARTS
    FOA("The Face of Alegria"),
    MMA("Mr & Mrs Alegria"),
    BS("The Big Show"),
    RF("Ramp on Fire"),
    WH("The Witch Hunt"),
    SNP("Snapshot"),
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
    BW("Bokwa"),
    SA("Stippling Art"),
    DJW("DJ Workshop"),
    SSA("Salsa"),
    RCB("Rubiks Cube"),
    TT("Tutting"),
    RS("Rifle Shooting"),//28-44WS
    FIFA("FIFA"),
    NFS("NFS Most Wanted"),
    CS16("CS 1.6"),
    MM("Mini Militia"),
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
    VRFN("VR Fruit Ninja"),//45-67SNG
    PM("Poster Mania"),
    AF("Artistic Fantasy"),
    AC("Art of Colors"),
    IP("Intricate Patterns"),
    MME("Mask Me"),
    NF("Nail Fantasy"),
    BTT("Blot the Tee"),
    MNC("Monochrome"),//68-75FARTS
    ADMD("Admad"),
    MQ("Management Quiz"),
    BM("Best Manager"),
    MTH("Management Treasure Hunt"),
    CSTD("Case Study"),
    LMS("Live Mock Stock"),//76-81MGMT
    MIBI("Make It or Break It"),
    QZ("Quiz"),
    CNTSTP("Cant Stop"),
    ILB("I Love Bees"),
    COCO("Conquer and Command"),
    HPSC("High poets' society");//82-87LARTS


    private final String event;

    Events(String event) {this.event=event;}

    public String getEvents(){return event;}

}