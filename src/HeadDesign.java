import java.util.*;

class HeadDesign {
    double t;
    private Properties prop;

    public HeadDesign(Properties properties) {
        prop = properties;
    }

    public double flatHead() {
        double C = 0,t1=0;
        switch (prop.getHeadJointType()) {
            case 1:
                C = 0.45;
                break;
            case 2:
                C = 0.55;
                break;
            case 3:
                C = 0.7;
                break;
            case 4:
                C = 0.55;
                break;
        }
        t1 = C * (prop.getDi()) * Math.sqrt(prop.getDesignP() / prop.getF())/1000.0;
        t = StandardThickness.standardt(t1, prop);
        return t;
    }

    public double conicalHead() {
        double t1, t2, Z = 0, l, Dk, t1std, t2std;
        switch ((int) prop.getAlpha()) {
            case 20:
                Z = 1.00;
                break;
            case 30:
                Z = 1.35;
                break;
            case 45:
                Z = 2.05;
                break;
            case 60:
                Z = 3.2;
                break;
        }
        t1 = ((prop.getDesignP()) * (prop.getDo()) * Z / ((2 * prop.getF() * prop.getJ()))) / 1000.0;
        l = 0.5 * Math.sqrt(1000.0 * (prop.getDo() * prop.getTStd()) / Math.cos(Math.toRadians(prop.getAlpha())));
        Dk = ((1000.0 * prop.getDi()) - (2 * l * Math.sin(Math.toRadians(prop.getAlpha())))) / 1000.0;
        t2 = (prop.getDesignP() * Dk) / (((2 * prop.getF() * prop.getJ()) - prop.getDesignP()) * Math.cos(Math.toRadians(prop.getAlpha())) * 1000.0);
        t1std = StandardThickness.standardt(t1, prop);
        t2std = StandardThickness.standardt(t2, prop);
        if (t1 > t2) {
            t = t1std;
        }
        else {
            t = t2std;
        }
        return t;
    }

    public double torisphericalHead() {
        double ri, Ri, trand, ro, Ro, param1, param2, param3, he, C;//param1=ho,param2=Do2/4*Ro,param3=sqrt(Do*ro/2)
        trand = prop.getTStd();
        ri = 0.06 * prop.getDi();
        Ri = 0.64 * prop.getDi();
        for (int i = 0; i < 3; i++) {
            ro = ri + 2 * trand;
            Ro = Ri + 2 * trand;
            param1 = Ro - Math.sqrt(((Ro - (prop.getDo() / 2)) * (Ro + ((prop.getDo()) / 2) - (2 * ro))));
            param2 = (Math.pow((prop.getDo()), 2)) / (2 * Ro);
            param3 = Math.sqrt((prop.getDo()) * ro / 2);
            he = Math.min(Math.min(param1, param2), param3);
            C = getTorisphericalC((he / prop.getDo()), (trand / prop.getDo()));
            trand = ((prop.getDesignP()) * (prop.getDo()) * C) / ((2 * (prop.getF())) * prop.getJ() * 1000.0);
        }
        t = StandardThickness.standardt(trand, prop);
        return t;
    }

    private double getTorisphericalC(double heByDo, double tByDo) {

        double xHeadings[] = {0.002, 0.005, 0.01, 0.02, 0.04};//t/Do
        double yHeadings[] = {0.15, 0.20, 0.25, 0.30, 0.40, 0.50};//hE/Do

        int xi = 0, yi = 0;

        double cData[][] = {{4.55, 2.66, 2.15, 1.95, 1.75},
                {2.30, 1.70, 1.45, 1.37, 1.32},
                {1.38, 1.14, 1.00, 1.00, 1.00},
                {0.92, 0.77, 0.77, 0.77, 0.77},
                {0.59, 0.59, 0.59, 0.59, 0.59},
                {0.55, 0.55, 0.55, 0.55, 0.55}};


        for (int i = 0; i < xHeadings.length - 1; i++) {
            if (tByDo > xHeadings[i] && tByDo < xHeadings[i + 1]) {
                xi = i + 1;
                break;
            } else if (tByDo < xHeadings[0]) {
                xi = 0;
            } else {
                xi = xHeadings.length - 1;
            }
        }
        for (int j = 0; j < yHeadings.length - 1; j++) {
            if (heByDo > xHeadings[j] && heByDo < xHeadings[j + 1]) {
                yi = j + 1;
                break;
            } else if (heByDo < yHeadings[0]) {
                yi = 0;
            } else {
                yi = yHeadings.length - 1;
            }
        }

        return cData[xi][yi];

    }
}