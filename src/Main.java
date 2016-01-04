/**
 * Created by ubuntu on 15-12-24.
 */
public class Main {

    private String var;

    public Main(String var) {
        this.var = var;
    }

    public static void main(String args[]){
        JVMemory.NEW().e();
    }

    private static String method(String abc) {
        System.out.println("--> " + abc);
        return abc;
    }
}
