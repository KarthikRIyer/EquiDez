public class StandardThickness {

    public static double standardt(double ts, Properties prop) {
        double tnew = 0;
        double tstd = 0;
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
            for (int i = 0; i <= 24; i++) {
                if (tnew > tstdarray[i] && tnew < tstdarray[i + 1]) {
                    tstd = tstdarray[i + 1];
                    break;
                }
            }
        }
        return tstd;
    }

}
