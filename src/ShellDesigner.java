class ShellDesigner {
  
    private Properties prop;

    ShellDesigner(Properties properties) {
        prop = properties;
    }

    public double ts, tnew, tstd;

    double cyl() {
        ts = ((prop.getDesignP()) * (prop.getDi()) / ((2 * prop.getf() * prop.getJ()) - (prop.getDesignP()))) / 1000;
        return ts;
    }

    double sph() {
        ts = ((prop.getDesignP()) * (prop.getDi()) / ((4 * prop.getf() * prop.getJ()) - (prop.getDesignP()))) / 1000;
        return ts;
    }

    private double getTr() {
        return ts;
    }

    public double cyl() {
        ts = ((prop.getDesignP()) * (prop.getDi()) / ((2 * prop.getF() * prop.getJ()) - (prop.getDesignP()))) / 1000;
        return ts;
    }

    public double sph() {
        ts = ((prop.getDesignP()) * (prop.getDi()) / ((4 * prop.getF() * prop.getJ()) - (prop.getDesignP()))) / 1000;
        return ts;
    }

    public double standardt() {
        if (ts > 30) {
            tnew = ts;
        } else {
            tnew = ts + prop.getCorrosionAllowance();
        }
        double tstdarray[] = {5.0, 5.5, 6.0, 7.0, 8.0, 9.0, 10.0, 11.0, 12.0, 14.0, 16.0, 18.0, 20.0, 22.0, 25.0, 28.0, 32.0, 36.0, 40.0, 45.0, 50.0, 56.0, 63.0, 71.0, 80.0};

        if (tnew < tstdarray[0]) {
            tstd = tstdarray[0];
        } else if (tnew > tstdarray[24]) {
            tstd = tnew;
        } else {
            for (int i=0; i<=24; i++){
                if (tnew > tstdarray[i] && tnew < tst[i + 1]) {
                    tstd = tstdarray[i + 1];
                    break;
                }
            }
        }
        prop.setDo(tstd);
        return tstd;
    }
}