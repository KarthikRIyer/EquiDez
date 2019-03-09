import java.util.*;

class Properties {

    private final

    ArrayList<String> vesselClass;
    double P, dP, T, dT, f, Pl, fluidDensity;
    int vesselClassCode = 2;
    Properties() {
        vesselClass = new ArrayList<>();
        vesselClass.add("Class I");
        vesselClass.add("Class II");
        vesselClass.add("Class III");
    }

}