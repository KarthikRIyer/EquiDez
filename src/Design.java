public class Design {

    HeadDesign headDesign;
    NozzleCompensator nozzleCompensator;
    Properties prop;
    ShellDesigner shellDesigner;

    public static void main(String args[]){
        Scanner in=new Scanner(System.in);
        int liqhead=0;

        System.out.println("Enter your working Pressure.(in kPa)");
        prop.P=in.nextDouble();
        System.out.println("Is there any Liquid present?(Yes(1)/No(2))");
        liqhead=in.nextInt();
        if(liqhead==1){
            System.out.print("Enter the height of the head.....  ");
            prop.ht=in.nextDouble();
            System.out.println();
            System.out.print("Enter density of the liquid.....  ");
            prop.fluidDensity=in.nextDouble();
            System.out.println();
        }
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

        System.out.println("Please select type of head...");
        System.out.println("1.     Flat Head............ (type: 1)");
        System.out.println("2.     Conical Head......... (type: 2)");
        System.out.println("3.     Torispherical Head... (type: 3)");


    }

}
