package lab1;

public class Expression {
        public static void main(String[] args) {
                Reader r = new Reader();
                r.Scan();
                r.i = count(r.i);
                r.k = count(r.k);
                System.out.println(r.i);
                System.out.println(r.k);
        }
        private static int count(int x) {
                x = x + 1;
                return x;
        }
}


