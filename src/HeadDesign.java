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
        double t1, t2, Z, l, Dk,t1std,t2std;
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
        t1std=StandardThickness.standardT(t1,prop);
        t2std=StandardThickness.standardT(t2,prop);
        if(t1>t2){
            t=t1std;
        }
        else{
            t=t2std;
        }
    }
}