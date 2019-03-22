public class NozzleCompensator {

    Properties properties;
    ShellDesigner shellDesigner;
    double A = 0, Aprime = 0, As = 0, An = 0, Ao = 0, Ar = 0;
    int shellType = -1;


    public NozzleCompensator(Properties properties, ShellDesigner shellDesigner, int shellType) {
        this.properties = properties;
        this.shellDesigner = shellDesigner;
        this.shellType = shellType;
    }

    private boolean isCompensationRequired() {

        A = getA();
        Aprime = getAs() + getAn();
        Ar = A - Aprime;
        if (Ar <= 0) {
            return false;
        } else {
            return true;
        }
    }

    private double getA() {
        return (properties.getDni() + 2 * properties.getCorrosionAllowance()) * 0.001 * (shellDesigner.getTr() * 0.001);
    }

    private double getAs() {
        return (properties.getDni() + 2 * properties.getCorrosionAllowance()) * 0.001 * (shellDesigner.standardt() - shellDesigner.getTr() - properties.getCorrosionAllowance()) * .0001;
    }

    private double getAn() {
        return getAo() + getAi();
    }

    private double getAo() {
        return 2 * properties.getH1() * 0.001 * (properties.getNozzleThickness() - getNozzleRequiredThickness() - properties.getCorrosionAllowance()) * 0.001;
    }

    private double getAi() {
        return 2 * properties.getH2() * 0.001 * (properties.getNozzleThickness() - 2 * properties.getCorrosionAllowance()) * 0.001;
    }

    private double getNozzleRequiredThickness() {

        Properties nozzleProps = new Properties();
        nozzleProps = properties;
        nozzleProps.setDo(properties.getDno());
        nozzleProps.setDi(properties.getDni());
        nozzleProps.setJ(1);
        ShellDesigner nozzleDesigner = new ShellDesigner(nozzleProps, shellType);
        return nozzleDesigner.getTr();

    }

    public double getRingPadThickness() {
        double tp = 0;
        if (isCompensationRequired()) {
            tp = Ar / (2 * (properties.getDni() * 0.001 + 2 * properties.getCorrosionAllowance() * 0.001) - (properties.getDni() * 0.001 + 2 * properties.getNozzleThickness() * 0.001));
        }
        return tp / 1000.0;
    }

}
