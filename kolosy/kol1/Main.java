import java.nio.file.Path;

public class Main {

    public static void main(String[] args) {

        Path zgony = Path.of("zgony.csv");
        Path icd = Path.of("icd10.txt");

        DeathCauseStatisticList list = new DeathCauseStatisticList();
        list.repopulate(zgony);

        ICDCodeTabular tab1 = new ICDCodeTabularOptimizedForMemory(icd);
        ICDCodeTabular tab2 = new ICDCodeTabularOptimizedForTime(icd);
        int i = 1;
        long start = System.currentTimeMillis();
        for (DeathCauseStatistic deathCauseStatistic : list.mostDeadlyDiseases(20, 10)) {
            System.out.println(i + ". " + deathCauseStatistic.getIdc10Code());
            System.out.println(tab1.getDescription(deathCauseStatistic.getIdc10Code()));
            i++;
        }
        System.out.println("start - System.currentTimeMillis() = " + (System.currentTimeMillis() - start) + "ms");

        i = 1;
        start = System.currentTimeMillis();
        for (DeathCauseStatistic deathCauseStatistic : list.mostDeadlyDiseases(20, 10)) {
            System.out.println(i + ". " + deathCauseStatistic.getIdc10Code());
            System.out.println(tab2.getDescription(deathCauseStatistic.getIdc10Code()));
            i++;
        }
        System.out.println("start - System.currentTimeMillis() = " + (System.currentTimeMillis() - start) + "ms");

    }
}