public class Design {

    HeadDesign headDesign;
    NozzleCompensator nozzleCompensator;
    Properties prop;
    ShellDesigner shellDesigner;

    public static void main(String args[]){
        Scanner in=new Scanner(System.in);

        System.out.println("Enter your working Pressure.(in kPa)");
        prop.P=in.nextDouble();
        System.out.println("Enter your design Temperature.(in K)");
        prop.dT=in.nextDouble();
        System.out.println("Enter value of maximum allowable stress i.e. f.(in *10^8)");
        prop.f=in.nextDouble();
        System.out.println("Enter value of weld joint efficiency fator.");
        prop.J=in.nextDouble();
        System.out.println("Which type of Shell do you want to design?");
        System.out.println("1.     Cylindrical Shell (type: 1)");
        System.out.println("2.     Spherical Shell   (type: 2)");
        shellDesigner.shellType=in.nextDouble();
        shellDesigner.getTr();
        shellDesigner.standardt();
        

    }

}
