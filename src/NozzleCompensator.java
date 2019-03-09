public class NozzleCompensator {

    Properties properties;
    ShellDesigner shellDesigner;
    boolean isCompensationRequired = false;
    double A = 0, Aprime = 0, As = 0, An = 0, Ao = 0;


    NozzleCompensator(Properties properties, ShellDesigner shellDesigner) {
        this.properties = properties;
        this.shellDesigner = shellDesigner;
    }

    public boolean isCompensationRequired() {

        A = getA();
        return false;
    }

    private double getA() {
        return (properties.getDni() + 2 * properties.getCorrosionAllowance()) * (shellDesigner.getTr());
    }

}
