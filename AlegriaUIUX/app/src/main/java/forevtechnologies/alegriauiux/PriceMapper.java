package forevtechnologies.alegriauiux;
import forevtechnologies.alegriauiux.models.Events;
import forevtechnologies.alegriauiux.models.EventsPaisa;
/**
 * Created by ziyad on 2/12/17.
 */

public class PriceMapper {
    static int price;

    public static int getPrice(String name){
        switch (name){
            case  "Rangoli Making (Rang Manch)":
            case "Mehendi Designing (Atrangi Patterns)":
            case "Face Painting (Pehchan Kaun?)":
            case "Nail Art (Nail Lit)":
            case "Sketching (Chitrakala":
            case "Elocution (Bol Bachchan)":
            case "Essay Writing (Bombai's Tale)":
            case "Spelling Bee (Word Of Nerds)":
            case "Poem Writing (An Ode To Mumbai)":
            case "Fastest Finger First":
            case "VR Fruit Ninja":
            case "Ludo King":
            case "Neo Shootout":
                price=50;
                break;
            case "Arduino Advance":
            case "IOT":
            case "Zumba":
            case "Stippling Art":
            case "Rifle Shooting":
            case "Admad(Ad-Central)":
            case "Management Quiz (Corporate Buzz)":
            case "Best Manager (BKC-Bambai ka Corporate)":
            case "case  Study (Mumbai Manifest)":
            case "Code Surfer (Code Bombay)":
            case "Tech Roadies ()":
            case "Poster Mania (Phata Poster)":
            case "Quiz (Bombay HEAT)":
            case "English Debate (Gupshup)":
            case "Open Mic":
            case "Beat Boxing":
            case "Food-A-Thon":
            case "FIFA":
            case "NFS Most Wanted":
            case "Badminton Girls":
            case "Badminton Boys":
            case "Chess":
            case "Table Tennis":
            case "Carrom Singles":
            case "Football & Cricket Quiz":
                price=100;
                break;
            case "Kotlin":
            case "Theatre Workshop":
            case "Calligraphy":
            case "Salsa":
            case "Rubiks Cube":
            case "Tutting":
            case "Photography":
            case "Management Treasure Hunt (Gateway to Diamond Valley)":
            case "Live Mock Stock (Dalal Street)":
            case "B-Boying (Sadak Nach)":
            case "Mono Acting":
            case "Parappa":
            case "Photography(Fotothon)":
            case "PTV Roadies":
            case "Mini Militia":
            case "Air Rifle Shooting":
                price=150;
                break;
            case "Web Designing":
            case "Python":
            case "Firebase by Google":
            case "DJ Workshop":
            case "Technical Paper Presentation (The Mumbai letter)":
            case "Canvas Painting (Rang De Mumbai)":
            case "T-Shirt Painting (Dhobi Ghat)":
            case "Classical Solo (Nrityasamrat)":
            case "Skit (Skecz)":
            case "Mr & Mrs Alegria":
            case "Fitness Physique (Musclemania Mumbai)":
            case "Carrom Doubles":
                price=200;
                break;
            case "3D Printing":
            case "Free Style Solo (Fatak se matak)":
            case "Solo Singing (Alegrian Idol)":
            case "Tug of War":
                price=250;
                break;
            case "Drone Piloting":
            case "Robo Challenge (Island Robos)":
            case "Make from waste ()":
            case "War of DJ's (Turntables)":
            case "Duet Dancing (Gem of the City":
            case "The Face of Alegria":
            case "Treasure Hunt (Gem of the City)":
            case "Basketball":
                price=300;
                break;
            case "Counter Strike Global Offensive":
                price=350;
                break;
            case "Folk Group (Taandav)":
            case "Volleyball":
                price=500;
                break;
            case "Kabaddi":
                price=600;
                break;
            case "Free Style Group (Beat pe feat)":
            case "Futsal Boys":
            case "Box Cricket":
                price=1000;
                break;
            case "Fashion Show (Fashion Street)":
                price=2500;
                break;
            case "Hackathon":
                price=0;
                break;
            default:
                price=0;
                break;
        }
        return price;
    }
}
