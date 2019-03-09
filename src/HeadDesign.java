import java.util.*;
class HeadDesign{
    double t;
    private Properties prop;
    HeadDesign(Properties properties){
        prop= properties;
    }
    double flatHead(){
        double C;
        switch(prop.getHeadJointType){
            case 1:
                C=0.45;
                break;
            case 2:
                C=0.55;
                break;
            case 3:
                C=0.7;
                break;
            case 4:
                C=0.55;
                break;
        }
        t= C*(prop.getDi())* Math.sqrt(prop.getDesignP()/prop.getf());
        return t;
    }
    double conicalHead(){
        double t1,t2,Z;
        double arrayZ[]={};
        
    }
}