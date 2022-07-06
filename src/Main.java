import entities.Contract;
import entities.Installment;
import jdk.swing.interop.SwingInterOpUtils;
import services.ContractService;
import services.OnlinePaymentService;
import services.PayPalService;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.Scanner;
import java.util.regex.PatternSyntaxException;

//O que foi aplicado neste Checkpoint?
//  - Composição de entidades
//  - Composição de serviços
//  - Inversão de valores / injeção de dependência

public class Main {
    public static void main(String[] args) throws ParseException {

        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yy");

        Locale.setDefault(Locale.US);
        Scanner sc = new Scanner(System.in);

        System.out.println("Enter contract data");
        System.out.print("Number: ");
        Integer number = sc.nextInt();
        System.out.print("Date (dd/MM/yy): ");
        Date date = sdf.parse(sc.next());
        System.out.print("Contract value: ");
        Double totalValue = sc.nextDouble();

        Contract contract = new Contract(number, date, totalValue);

        System.out.print("Enter number of installments: ");
        int N = sc.nextInt();

        ContractService cs = new ContractService(new PayPalService());

        cs.processContract(contract, N);

        System.out.println("=========================");
        System.out.println("Installments:");
        for (Installment it : contract.getInstallments()) {
            System.out.println(it);
        }
        System.out.println("=========================");

        System.out.println("this is a loan simulation");


        sc.close();
    }
}