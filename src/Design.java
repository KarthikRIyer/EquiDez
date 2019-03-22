import java.util.Scanner;

public class Design {

    public static void main(String args[]) {

        HeadDesign headDesign;
        NozzleCompensator nozzleCompensator;
        Properties prop = new Properties();
        ShellDesigner shellDesigner;
        double ringPadThickness = 0;
        double headThickness = 0;
        double shellThickness = 0;

        Scanner in = new Scanner(System.in);
        int liqhead = 0, headtype = 0;

        System.out.println("Enter your working Pressure.(in kPa)");
        prop.P = in.nextDouble();
        System.out.println("Is there any Liquid present?(Yes(1)/No(2))");
        liqhead = in.nextInt();
        if (liqhead == 1) {
            System.out.print("Enter the height of the head.....  ");
            prop.ht = in.nextDouble();
            System.out.println();
            System.out.print("Enter density of the liquid.....  ");
            prop.fluidDensity = in.nextDouble();
            System.out.println();
        }
        System.out.println("Enter your design Temperature.(in K)");
        prop.dT = in.nextDouble();
        System.out.println("Enter value of maximum allowable stress i.e. f.(in *10^8)");
        prop.f = in.nextDouble();
        System.out.println("Enter value of weld joint efficiency fator.");
        prop.J = in.nextDouble();
        System.out.println("Which type of Shell do you want to design?");
        System.out.println("1.     Cylindrical Shell (type: 1)");
        System.out.println("2.     Spherical Shell   (type: 2)");
        int shellType = in.nextInt();
        shellDesigner = new ShellDesigner(prop, shellType);
        shellDesigner.getTr();
        shellThickness = shellDesigner.standardt();

        System.out.println("Please select type of head...");
        System.out.println("1.     Flat Head............ (type: 1)");
        System.out.println("2.     Conical Head......... (type: 2)");
        System.out.println("3.     Torispherical Head... (type: 3)");
        headtype = in.nextInt();
        headDesign = new HeadDesign(prop);
        switch (headtype) {
            case 1:
                System.out.println("Select the type of head joint present...");
                for (int i = 0; i < prop.getHeadJointTypeLis().size(); i++){
                    System.out.println((i+1)+"."+prop.getHeadJointTypeLis().get(i)+"(type: "+(i+1)+")");
                }
                int j = in.nextInt();
                prop.setHeadJointType(j);
                headThickness = headDesign.flatHead();
                break;
            case 2:
                System.out.println("Select the value of alpha...");
                System.out.println("1.     20 degree    (type: 1)");
                System.out.println("2.     30 degree    (type: 2)");
                System.out.println("3.     45 degree    (type: 3)");
                System.out.println("4.     60 degree    (type: 4)");
                int i = in.nextInt();
                prop.setAlpha(i);
                headThickness = headDesign.conicalHead();
                break;
            case 3:
                headThickness = headDesign.torisphericalHead();
                break;

        }

        System.out.println("Is there a nozzle in the shell?(Yes(1)/No(2))");
        int nozzlePresent = in.nextInt();
        if (nozzlePresent == 1){
            System.out.println("Enter inner diameter of nozzle: (mm)");
            double Dni = in.nextDouble();
            System.out.println("Enter outer diameter of nozzle: (mm)");
            double Dno = in.nextDouble();
            prop.setDni(Dni);
            prop.setDno(Dno);
            nozzleCompensator = new NozzleCompensator(prop, shellDesigner, shellType);
            ringPadThickness = nozzleCompensator.getRingPadThickness();
        }
        System.out.println("\n\n");
        System.out.println("=================================================================");
        System.out.println("                            DESIGN                               ");
        System.out.println("=================================================================");
        if (shellThickness != 0 && shellType == 1){
            System.out.println("Cylindrical Shell Thickness = "+shellThickness+" mm");
        } else if (shellThickness != 0 && shellType == 2){
            System.out.println("Spherical Shell Thickness = "+shellThickness+" mm");
        }
        if (headThickness != 0){
            switch (headtype){
                case 1:
                    System.out.println("Flat Head Thickness = "+headThickness+" mm");
                    break;
                case 2:
                    System.out.println("Conical Head Thickness = "+headThickness+" mm");
                    break;
                case 3:
                    System.out.println("Torispherical Head Thickness = "+headThickness+" mm");
                    break;
            }
        }
        if (nozzlePresent == 1){
            System.out.println("Nozzle thickness = "+(prop.getDno()-prop.getDni())+" mm");
            System.out.println("Required ring pad thickness = "+ringPadThickness+" mm");
        }
        System.out.println("=================================================================");

    }

}
