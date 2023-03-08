import java.util.ArrayList;
import java.util.Arrays;
import java.util.Objects;
import java.util.function.Function;

public class HomeWork6 {
    static ArrayList<String> strings = new ArrayList<>();
    static ArrayList<String> strings1 = new ArrayList<>();
    static ArrayList<String> strings2 = new ArrayList<>();

    public interface Playable {
        static ArrayList<String> play() {
            return null;
        }
        static ArrayList<String> playWith(Playable playable) {
            return null;
        }
    }

    public static abstract class Instrument implements Playable{

        Instrument() {}
        public String getSound() {
            return sound;
        }

        public int getTimes() {
            return times;
        }

        private static String sound;
        private static int times;
        public static ArrayList<String> play() {String a = "";
            strings.clear();
            for (int i = 1; i <= times; i++) {
                if (i != times) a = a + sound + " ";
            else a = a + sound;}
            strings.add(a);
        return strings;}
        static ArrayList<String> playWith(Playable playable) {strings1.addAll(strings);
            return strings1;
        }
    }

    public static class Guitar extends Instrument{
        private static final String sound = "Трунь";
        private static final int times = 2;
        public static ArrayList<String> play() {Instrument.sound = sound;
            Instrument.times = times;
            Instrument.play();
            return strings;}
        static ArrayList<String> playWith(Playable playable) {strings1.clear();
            play();
            Instrument.playWith(playable);
            Drum.play();
            Instrument.playWith(playable);
            return strings1;
        }
    }

    public static class Drum extends Instrument{
        private static final String sound = "Бац";
        private static final int times = 3;
        public static ArrayList<String> play() {Instrument.sound = sound;
            Instrument.times = times;
            Instrument.play();
            return strings;}
        static ArrayList<String> playWith(Playable playable) {strings1.clear();
            play();
            Instrument.playWith(playable);
            Guitar.play();
            Instrument.playWith(playable);
            return strings1;
        }
    }

    public static class Orchestra implements Playable{
        Orchestra(Instrument... instruments) {instruments2.addAll(Arrays.asList(instruments));
            System.out.println(instruments2);}

        public ArrayList<Instrument> getInstruments() {
            return instruments2;
        }
        ArrayList<Instrument> instruments2 = new ArrayList<>();

        public ArrayList<String> play() {for (int i = 0; i < 2; i++)
        {Guitar.play();
            strings2.addAll(strings);
            Drum.play();
            strings2.addAll(strings);}
            return strings2;
        }
        public ArrayList<String> playWith(Playable playable) {strings2.clear();
            play();
            Guitar.play();
            strings2.addAll(strings);
            return strings2;
        }
    }

    public static void main(String[] args) {
        var guitar = new Guitar();
        var drum = new Drum();
        print("Guitar: Гитара создалась", true);
        print("Drum:   Барабан создался", true);
        print("Guitar: play Guitar должно быть " + GUITAR_R, Objects.equals(guitar.play().get(0), GUITAR_R));
        print("Drum:   play Drum должно быть " + DRUM_R, Objects.equals(drum.play().get(0), DRUM_R));
        print("Guitar: playWith Drum первая гитара", Objects.equals(guitar.playWith(drum).get(0), GUITAR_R));
        print("Guitar: playWith Drum последний барабан", Objects.equals(guitar.playWith(drum).get(1), DRUM_R));
        print("Drum:   playWith Guitar первый барабан", Objects.equals(drum.playWith(guitar).get(0), DRUM_R));
        print("Drum:   playWith Guitar последняя гитара", Objects.equals(drum.playWith(guitar).get(1), GUITAR_R));

        var emptyOrchestra = new Orchestra();
        var orchestra = new Orchestra(new Guitar(), new Drum(), new Guitar(), new Drum());
        print("EmptyOrchestra: Пустой оркестр создался", true);
        print("EmptyOrchestra: Инструменты должны быть пустым списком", emptyOrchestra.getInstruments() != null);
        print("Orchestra: Оркестр создался", true);
        print("Orchestra: В оркестре должно быть 4 инструмента", orchestra.getInstruments().size() == 4);
        print("Orchestra: Должны сыграть 4 инструмента", orchestra.play().size() == 4);
        print("Orchestra: Гитара играет первая", Objects.equals(orchestra.play().get(0), GUITAR_R));
        print("Orchestra: Барабан играет второй", Objects.equals(orchestra.play().get(1), DRUM_R));
        print("Orchestra: Гитара играет третья", Objects.equals(orchestra.play().get(2), GUITAR_R));
        print("Orchestra: Барабан играет четвертый", Objects.equals(orchestra.play().get(3), DRUM_R));
        print("Orchestra: Должны сыграть 5 инструментов", orchestra.playWith(new Guitar()).size() == 5);
        print("Orchestra: Гитара играет последняя", Objects.equals(orchestra.playWith(new Guitar()).get(4), GUITAR_R));
    }

    /* Техническая секция - сюда писать ничего не надо */

    private static void print(String condition, Boolean act) {
        Function<String, String> yellow = str -> "\u001B[33m" + str + "\u001B[0m";
        System.out.print( "TEST CASE " + yellow.apply(constLen(condition, 55)));
        if (act) System.out.print("✅"); else System.out.print("❌");
        System.out.println();
    }

    private static String constLen(String str, int len) {
        StringBuilder sb = new StringBuilder(str);
        while (len-- - str.length() > 0) sb.append(" ");
        return sb.toString();
    }

    private static final String GUITAR_R = "Трунь Трунь";
    private static final String DRUM_R = "Бац Бац Бац";
}