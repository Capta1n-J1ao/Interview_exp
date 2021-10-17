package tiktok;

public class adding2numbers {
    public int add(float a, float b){
        float res = a + b;
        return (int) res;
    }

    public static void main(String[] args) {
        System.out.println(new adding2numbers().add(1.2f,3.9f));
    }
}
