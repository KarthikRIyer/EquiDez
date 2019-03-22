import java.util.ArrayList;

class Properties {

    public static ArrayList<String> vesselClass, headJointType;
    double P = 0;
    double dP = 0;
    double dT = 0;
    double f = 0;
    double Pl = 0;
    double fluidDensity = 0;
    double ht = 0;
    double alpha = 0;
    double Di = 0;
    double dni = 0;
    double dno = 0;
    double H1i = 0;
    double H2i = 0;
    double H1 = 0;
    double H2 = 0;
    double ca = 0;
    double Do = 0;
    double J = 0.8;
    double tstd = 0;
    double tr = 0;
    int vesselClassCode = -1, headJointTypeCode = -1;

    public void setTStd(double tstd) {
        this.tstd = tstd;
    }

    public void setTr(double tr) {
        this.tr = tr;
    }

    public double getTStd() {
        return tstd;
    }

    public double getTr() {
        return tr;
    }

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

    public double getH1() {
        return H1;
    }

    public double getH2() {
        return H2;
    }

    public double getdesignTemperature() {
        return dT;
    }

    public double getNozzleThickness() {
        return dno - dni;
    }

    public ArrayList<String> getHeadJointTypeLis() {
        return headJointType;
    }

    public int getHeadJointType() {
        return headJointTypeCode;
    }

    public void setHeadJointType(int j){
        headJointTypeCode = j;
    }

    public int getVesselClassCode() {
        return vesselClassCode;
    }

    public double getAlpha() {
        return alpha;
    }

    public void setAlpha(int i){
        switch(i){
            case 1:
                alpha = 20;
                break;
            case 2:
                alpha = 30;
                break;
            case 3:
                alpha = 45;
                break;
            case 4:
                alpha = 60;
                break;
        }
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