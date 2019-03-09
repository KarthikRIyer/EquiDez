import java.util.*;

class HeadDesign {
    double t;
    private Properties prop;

    HeadDesign(Properties properties) {
        prop = properties;
    }

    double flatHead() {
        double C;
        switch (prop.getHeadJointType) {
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
        t = C * (prop.getDi()) * Math.sqrt(prop.getDesignP() / prop.getF());
        return t;
    }

    double conicalHead() {
        double t1, t2, Z, l, Dk, t1std, t2std;
        switch (prop.getAlpha()) {
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
        t1 = ((prop.getDesignP()) * (prop.getDo()) * Z / ((2 * prop.getF() * prop.getJ()))) / 1000;
        l = 0.5 * Math.sqrt(1000 * (prop.getDo() * prop.getTStd()) / Math.cos(Math.toRadians(prop.getAlpha())));
        Dk = ((1000 * prop.getDi()) - (2 * l * math.sin(Math.toRadians(prop.getAlpha())))) / 1000;
        t2 = (prop.getDesignP() * Dk) / (((2 * prop.getF() * prop.getJ()) - prop.getDesignP()) * Math.cos(Math.toradians(prop.getAlpha())) * 1000);
        t1std = StandardThickness.standardT(t1, prop);
        t2std = StandardThickness.standardT(t2, prop);
        if (t1 > t2) {
            t = t1std;
        } else {
            t = t2std;
        }
    }

    double torisphericalHead() {
        double ri, Ri, trand, ro, Ro, param1, param2, param3, he, C;//param1=ho,param2=Do2/4*Ro,param3=sqrt(Do*ro/2)
        trand = prop.getTStd();
        ri = 0.06 * prop.getDi();
        Ri = 0.64 * prop.getDi();
        for (int i = 0; i < 3; i++) {
            ro = ri + 2 * trand;
            Ro = Ri + 2 * trand;
            param1 = Ro - Math.sqrt(((Ro - (prop.getDo() / 2)) * (Ro + ((prop.getDo()) / 2) - (2 * ro))));
            param2 = (Math.pow((prop.getDo()), 2)) / (2 * Ro);
            param3 = sqrt((prop.getDo()) * ro / 2);
            he = Math.min(Math.min(param1, param2), param3);
            C = prop.getC((he / prop.getDo()), (trand / prop.getDo()));
            trand = ((prop.getDesignP()) * (prop.getDo()) * C) / ((2 * (prop.getF())) * prop.getJ());
        }
        t = StandardThickness.standardT(trand, prop);

    }
}