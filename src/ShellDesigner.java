class ShellDesigner {

    private double ts, tstd;
    private Properties prop;
    int shellType = -1;

    public ShellDesigner(Properties properties, int shellType) {
        prop = properties;
        this.shellType = shellType;
    }

    public double getTr() {
        if (shellType == 1) {
            ts = cyl();
        } else if (shellType == 2) {
            ts = sph();
        }
        return ts;
    }

    public double cyl() {
        return ((prop.getDesignP()) * (prop.getDi()) / ((2 * prop.getF() * prop.getJ()) - (prop.getDesignP()))) / 1000;
    }

    public double sph() {
        return ((prop.getDesignP()) * (prop.getDi()) / ((4 * prop.getF() * prop.getJ()) - (prop.getDesignP()))) / 1000;
    }

    public double standardt() {
        tstd = StandardThickness.standardt(getTr(), prop);
        prop.setDo(tstd);
        return tstd;
    }
}