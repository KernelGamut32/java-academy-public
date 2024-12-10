package expeditors.week05.exploration.loading;

public class Child extends Parent {
    public Child() {
        super();
        System.out.println("Child constructor executed...");
    }

    static {
        System.out.println("Child static block executed...");
    }

    {
        System.out.println("Child non-static block executed...");
    }

    public static void ChildMethod() {
        System.out.println("Child static method executed...");
    }
}
