import java.util.ArrayList;

class Properties {

    ArrayList<String> vesselClass, headJointType;
    double P = 0, dP = 0, dT = 0, f = 0, Pl = 0, fluidDensity = 0, ht = 0, alpha = 0, Di = 0, dni = 0, dno = 0, H1i = 0, H2i = 0, H1 = 0, H2 = 0, ca = 0, Do = 0, J = 0;
    int vesselClassCode = -1, headJointTypeCode = -1;

    Properties() {
        vesselClass = new ArrayList<>();
        headJointType = new ArrayList<>();

        vesselClass.add("Class I");
        vesselClass.add("Class II");
        vesselClass.add("Class III");
        vesselClassCode = 2;

        headJointType.add("Flanged flat head butt welded to shell");
        headJointType.add("Plates welded to the inside of the shell");
        headJointType.add("Plates welded to the end of the shell(no inside vessel)");
        headJointType.add("Plates welded to the end of the shell with an additional fillet weld on the inside");
        headJointTypeCode = 1;
    }

    public ArrayList<String> getVesselClass() {
        return vesselClass;
    }

    public ArrayList<String> getHeadJointTypeLis() {
        return headJointType;
    }

    public int getHeadJointType() {
        return headJointTypeCode;
    }

    public int getVesselClassCode() {
        return vesselClassCode;
    }

    public double getAlpha() {
        return alpha;
    }

    public void setCorrosionAllowance(double ca) {
        this.ca = ca;
    }

    public double getCorrosionAllowance() {

        return ca;

    }

    public void setVesselClassCode(int c) {
        vesselClassCode = c;
    }

    public void setFluidDensity(double fluidDensity) {
        this.fluidDensity = fluidDensity;
    }

    public void setOperatingPressure(double operatingPressure) {
        P = operatingPressure;
        calcDesignPressure();
    }

    public void setDesignPressure(double designPressure) {
        dP = P = designPressure;
    }

    public void setDesignTemperature(double T) {
        dT = T;
    }

    public void setF(double f) {
        this.f = f;
    }

    public double getF() {
        return (f * 100000.0);
    }

    public void setFluidHeight(double ht) {
        this.ht = ht;
        calcDesignPressure();
    }

    public void calcDesignPressure() {

        double waterHead = (fluidDensity * 9.81 * ht) / 1000.0;
        if (waterHead > 0.05 * P) {
            dP = P + waterHead;
        } else {
            dP = 1.05 * P;
        }

    }

    public void setH1(double H1) {
        this.H1i = H1;
        if (H1i > getBoundaryH1()) {
            H1 = getBoundaryH1();
        } else {
            H1 = H1i;
        }
    }

    public void setH2(double H2) {
        this.H2i = H2;
        if (H2i > getBoundaryH2()) {
            H2 = getBoundaryH2();
        } else {
            H2 = H2i;
        }
    }

    public void setDi(double Di) {
        this.Di = Di;
    }

    public void setDno(double Dno) {
        dno = Dno;
    }

    public void setDni(double Dni) {
        dni = Dni;
    }

    public double getDni() {
        return dni;
    }

    public double getDno() {
        return dno;
    }

    public double getBoundaryH1() {

        double tn = dno - dni;
        return Math.sqrt((dni + 2 * ca) * (tn - ca));

    }

    public double getBoundaryH2() {
        double tn = dno - dni;
        return Math.sqrt((dni + 2 * ca) * (tn - 2 * ca));
    }

    public double getDi() {
        return Di;
    }

    public double getDo() {
        return Do;
    }

    public void setDo(double t) {
        Do = Di + 2 * t * 0.001;
    }

    public void setJ(double J) {
        this.J = J;
    }

    public double getJ() {
        return J;
    }

    public double getDesignP() {
        return dP;
    }
}