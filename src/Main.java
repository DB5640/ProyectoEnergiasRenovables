public class Main {
    public static void main(String[] args) {

        EnergiaRenovable e1 = new EnergiaRenovable(800);
        EnergiaRenovable e2 = new EnergiaRenovable();
        EnergiaRenovable e3 = new EnergiaRenovable();
        System.out.println("e1 = " + e1.calcularProduccion());
        System.out.println("e2 = " + e2.calcularProduccion());
        System.out.println("e3 = " + e3.pais + " " + e3.tipo);

        EnergiaHidroelectrica eh1 = new EnergiaHidroelectrica();

        EnergiaRenovable es1 = new EnergiaRenovable("Colombia", "Solar", 2022);
    }
}