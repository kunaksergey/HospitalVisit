package ua.shield.del;

/**
 * Created by sa on 16.12.17.
 */
public class Test {
    public static void main(String[] args) {
        new Test().go();
    }

     void go() {
      A m=new B();
         System.out.println(m.name+m.test());
    }


}

class A{
    String name="a";
    String test(){
        return name;
    }
}

class B extends A{
    String name="b";
    String test(){
        return name;
    }
}


