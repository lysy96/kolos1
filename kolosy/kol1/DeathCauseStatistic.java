public class DeathCauseStatistic {
    private String idc10Code;

    //var
    //soutv
    //if
    //par ()
    //fori
    public static DeathCauseStatistic fromCsvLine(String string) {
        //A02.1          ,5,-,-,-,-,-,-,-,-,-,-,-,-,1,2,-,1,1,-,-,-
        String[] split = string.split(",");
        //A02.1          !
        // usuwamy spacje
        String code = split[0].replace(" ", "");
        // dowolne v albo ^
        code = code.trim();

        int[] deaths = new int[split.length - 1];
        for (int i = 0; i < deaths.length; i++) {
            if (split[i + 1].equals("-")) {
                deaths[i] = 0;
            } else deaths[i] = Integer.parseInt(split[i + 1]);
        }
        return new DeathCauseStatistic(code, deaths);
    }

    public String getIdc10Code() {
        return idc10Code;
    }

    public DeathCauseStatistic(String idc10Code, int[] deathsByAgeGroup) {
        this.idc10Code = idc10Code;
        this.deathsByAgeGroup = deathsByAgeGroup;
    }

    //

    public class AgeBracketDeaths {
        public final int young, old, deathCount;

        public AgeBracketDeaths(int young, int old, int deathCount) {
            this.young = young;
            this.old = old;
            this.deathCount = deathCount;
        }
    }

    //CTRL + ALT + L
    private final int[] deathsByAgeGroup;

    //0     , 1     ,2
    //0 – 4 , 5 - 9 ,10 - 14
    //7
    public AgeBracketDeaths getAgeBracket(int age) {
        int index = age / 5;
        int young = index * 5;
        int old = young + 4;
        return new AgeBracketDeaths(young, old, deathsByAgeGroup[index]);
    }


}







