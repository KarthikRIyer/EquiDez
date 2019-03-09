import java.util.*;
class ShellDesign {
    private Properties prop;

    ShellDesign(Properties properties) {
        prop = properties;
    }
    double ts;
    double cyl(){
        ts= ((prop.getDesignP())*(prop.getDi())/((2*prop.getf()*prop.getJ())-(prop.getDesignP())));
        return ts;
    }
    double sph(){
        ts= ((prop.getDesignP())*(prop.getDi())/((4*prop.getf()*prop.getJ())-(prop.getDesignP())));
        return ts;
    }
}